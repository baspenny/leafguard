package com.leafguard.leafguard;

import gnu.io.SerialPortEvent;


public interface SerialConnectorInterface {

    public void initialize();

    public void sendData(String message);

    public String receiveData();

    public void close();

    public void serialEvent(SerialPortEvent oEvent);
}




