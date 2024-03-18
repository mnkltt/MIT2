package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorTest {
	TrainController controller;
    TrainSensorImpl ts;
    TrainUser user;
	
	@Before
	public void before() {
		controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        
        ts = new TrainSensorImpl(controller, user);
	}

    @Test
    public void NotBetweenInterval() {
        when(user.getAlarmState()).thenReturn(true);

        ts.overrideSpeedLimit(600);
        Assert.assertEquals(true, user.getAlarmState());

        verify(user, times(1)).getAlarmState();
    }

    @Test
    public void BetweenInterval() {
        when(user.getAlarmState()).thenReturn(false);

        ts.overrideSpeedLimit(300);
        Assert.assertEquals(false, user.getAlarmState());

        verify(user, times(1)).getAlarmState();
    }

    @Test
    public void RelativeSpeedTest() {
        when(user.getAlarmState()).thenReturn(false);

        ts.overrideSpeedLimit(300);
        Assert.assertEquals(false, user.getAlarmState());

        verify(user, times(1)).getAlarmState();
    }