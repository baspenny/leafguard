package com.leafguard.leafguard;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerialConnectorTest  {

    SerialConnector serialConnector = new SerialConnector();

    @Test
    public void initialize()  {
    }

    @Test
    public void sendData()  {
        serialConnector.initialize();
        serialConnector.sendData("pumpon");

        String expecting = "switching on";
        String actual = serialConnector.getResponse();
        assertEquals(expecting, actual);
    }

    @Test
    public void receiveData()  {
    }

    @Test
    public void close()  {
    }

    @Test
    public void serialEvent()  {
    }


}