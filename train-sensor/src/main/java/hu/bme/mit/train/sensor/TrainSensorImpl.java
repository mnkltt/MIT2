package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import java.time.*;
import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;
	private Table<LocalTime,Integer,Integer> tachograph = HashBasedTable.create();

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
		double percentage = controller.getReferenceSpeed()*0.5;

		if(speedLimit > 500 || speedLimit < 0 || speedLimit < percentage) {
			user.setAlarmState(true);
		}
		else {
			user.setAlarmState(false);
		}
	}

	@Override
	public void setTachograph() {
		tachograph.put(LocalTime.now(), user.getJoystickPosition(), controller.getReferenceSpeed());
	}

	@Override
	public Table<LocalTime,Integer,Integer> getTachograph() {
		return tachograph;
	}

}
