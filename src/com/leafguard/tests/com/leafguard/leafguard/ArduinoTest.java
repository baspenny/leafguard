package com.leafguard.leafguard;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArduinoTest {

    SerialConnector serialConnector = new SerialConnector();
    Arduino arduino = new Arduino(serialConnector);


    @Test
    public void getMoisturePercentage() {
        serialConnector.initialize();

        int expectMoisture = -48;
        int actualMoisture = arduino.getMoisturePercentage();

        assertEquals(expectMoisture, actualMoisture);
        System.out.println("Current moisturePercentage: " + actualMoisture);
        serialConnector.close();
    }

    @Test
    public void controlPump() throws Exception {
        serialConnector.initialize();
        int pumpState = 1; // input

        String expect = "ok";
        String actual = arduino.controlPump(pumpState);
        assertEquals(expect, actual);
        serialConnector.close();
    }

    @Test
    public void togglePump() {
        int state = 1;
        String expect = "ok";
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



