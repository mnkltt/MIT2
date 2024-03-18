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
    public void NotBetweenInterval550() {
        when(controller.getReferenceSpeed()).thenReturn(400);

        ts.overrideSpeedLimit(550);

        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void BetweenInterval() {
        when(controller.getReferenceSpeed()).thenReturn(250);

        ts.overrideSpeedLimit(300);

        verify(user, times(1)).setAlarmState(false);
    }

    @Test
    public void RelativeSpeedTest() {
        when(controller.getReferenceSpeed()).thenReturn(150);

        ts.overrideSpeedLimit(50);

        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void RelativeSpeedTest50p() {
        when(controller.getReferenceSpeed()).thenReturn(100);

        ts.overrideSpeedLimit(50);

        verify(user, times(1)).setAlarmState(false);
    }

    @Test
    public void NotBetweenInterval490() {
        when(controller.getReferenceSpeed()).thenReturn(450);

        ts.overrideSpeedLimit(490);

        verify(user, times(1)).setAlarmState(false);
    }
}