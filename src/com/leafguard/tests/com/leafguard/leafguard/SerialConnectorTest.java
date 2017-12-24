package com.leafguard.leafguard;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerialConnectorTest  {

    SerialConnectorInterface serialConnector = new SerialConnectorMock();
    Arduino arduino = new Arduino(serialConnector);

    @Test
    public void sendData()  {
        serialConnector.initialize();
        serialConnector.sendData("pumpon");

        String expecting = "switching on";
        String actual = serialConnector.receiveData();
        assertEquals(expecting, actual);
        serialConnector.close();
    }

    @Test
    public void receiveData()  {
        serialConnector.initialize();
        serialConnector.sendData("pumpoff");

        String expecting = "switching off";
        String actual = serialConnector.receiveData();
        assertEquals(expecting, actual);
        serialConnector.close();
    }
}