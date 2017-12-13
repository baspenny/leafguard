package com.leafguard.leafguard;


import org.junit.Test;

import static org.junit.Assert.*;

public class ArduinoTest {


    private SerialConnector serialConnector;


    public ArduinoTest(SerialConnector serialConnector) {
        this.serialConnector = serialConnector;
        serialConnector.initialize();
    }


    // deze test is nog niet af, nog testen met Arduino!
    @Test
    public void controlPump() throws Exception {

        int pumpState = 1; // input
        Arduino arduino = new Arduino(serialConnector);

        String expect = "Pomp staat al aan!";
        String expect2 = "Pomp aan!"; // verwachting, nog controleren
        String actual = arduino.controlPump(pumpState);

        assertEquals(expect, expect2, actual);
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
