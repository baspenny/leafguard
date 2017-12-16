package com.leafguard.leafguard;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerialConnectorTest  {

    SerialConnector serialConnector = new SerialConnector();
    Arduino arduino = new Arduino(serialConnector);

    @Test
    public void sendData()  {
        serialConnector.initialize();
        serialConnector.sendData("pumpon");

        String expecting = "switching on";
        String actual = serialConnector.getResponse();
        assertEquals(expecting, actual);
        serialConnector.close();
    }

    @Test
    public void receiveData()  {
        serialConnector.initialize();
        serialConnector.sendData("pumpoff");

        String expecting = "switching off";
        String actual = serialConnector.getResponse();
        assertEquals(expecting, actual);
        serialConnector.close();
    }
}