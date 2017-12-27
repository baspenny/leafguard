package com.leafguard.leafguard;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

/**
 * Class SerialConnectorMock
 *
 * The purpose of this class is to mock/mimmick an serial connection to a Arduino
 * This allows us to test more than one Arduino instances because this
 * could be the case in a real world scenario
 */

public class SerialConnectorMock implements SerialConnectorInterface
{
    private static final int ON_STATE = 1;
    private static final int OFF_STATE = 0;

    private String response;
    private int greenLed        = OFF_STATE;
    private int yellowLed       = OFF_STATE;
    private int redLed          = ON_STATE;
    private int pumpState       = OFF_STATE;
    private int moistureValue   = 56;

    public void initialize()
    {
        //System.out.println("Started");
    }

    public void sendData(String message)
    {
        switch (message.toString()) {
            case "pumpon":
                this.response = "switching on";
                this.pumpState = ON_STATE;
                break;
            case "pumpoff":
                this.response = "switching off";
                this.pumpState = OFF_STATE;
                break;
            case "data":
                this.response =
                        "moisture="+this.moistureValue+"&pumpstate="+this.pumpState;
                break;
            default: this.response = "";

        }
    }

    public String receiveData()
    {
        return this.response;
    }

    /**
     * No need to implement close method
     */
    public synchronized void close()
    {
        this.moistureValue = 0;
        // No implementation needed for mock class
    }

    /**
     * No need to implement serial event
     */
    public synchronized void serialEvent(SerialPortEvent event)
    {
        // No implementation needed for mock class
    }

    public int getMoistureValue() {
        return this.moistureValue;
    }

}
