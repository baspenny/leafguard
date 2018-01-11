package leafguard;


import com.leafguard.leafguard.Arduino;
import com.leafguard.leafguard.SerialConnectorInterface;
import com.leafguard.leafguard.SerialConnectorMock;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArduinoTest {

    SerialConnectorInterface serialConnector = new SerialConnectorMock();
    Arduino arduino = new Arduino(serialConnector);

    @Test
    public void getMoisturePercentage() {
        serialConnector.initialize();

        int expectMoisture = 71;
        int actualMoisture = arduino.getMoisturePercentage();
        assertEquals(expectMoisture, actualMoisture);
        serialConnector.close();
    }

    @Test
    public void controlPump() throws Exception {
        serialConnector.initialize();
        int pumpState = 1; // input

        String expect = "pump_turned_on";
        String actual = arduino.controlPump(pumpState);
        assertEquals(expect, actual);
        serialConnector.close();
    }

    @Test
    public void togglePump() {
        int state = 1;
        String expect = "pump_turned_on";
        String actual = arduino.togglePump(state);
        assertEquals(expect, actual);
        serialConnector.close();
    }

    @Test
    public void closeSerialConnection() {
        // start serial connection
        serialConnector.initialize();
        // now close serial connection
        arduino.closeSerialConnection();

        int expect = 0;
        int actual = arduino.getMoisturePercentage();
        assertEquals(expect, actual);
    }
}



