package com.leafguard.leafguard;


import org.junit.Test;

import static org.junit.Assert.*;

public class ArduinoTest {

    private int id;
    private int pumpState;
    private int moisturePercentage;
    private SerialConnectorTest serialConnector = new SerialConnectorTest();





    public void getDataFromSerial() {
        this.parseReturnValue(this.serialConnector.receiveData());
    }


}
