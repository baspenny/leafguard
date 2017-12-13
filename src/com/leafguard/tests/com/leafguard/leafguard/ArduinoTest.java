package com.leafguard.leafguard;


import org.junit.Test;

import static org.junit.Assert.*;

public class ArduinoTest {

    @Test
    public void controlPump() throws Exception {

        SerialConnector serialConnector = new SerialConnector();
        serialConnector.initialize();

        int pumpState = 1; // input
        Arduino arduino = new Arduino(serialConnector);

        String expect = "ok";
        String actual = arduino.controlPump(pumpState);
        assertEquals(expect, actual);
    }


    @Test
    public void getDataFromSerial1() throws Exception {
    }

    @Test
    public void closeSerialConnection() throws Exception {
    }

    @Test
    public void sleep() throws Exception {
    }

    @Test
    public void sendData() throws Exception {
    }

    @Test
    public void receiveData() throws Exception {
    }

    @Test
    public void openConnection() throws Exception {
    }

    @Test
    public void closeConnection() throws Exception {
    }


}
