package com.leafguard.leafguard;

public interface ArduinoInterface {

    public void sendData(String data);

    public String receiveData();

    public boolean openConnection();

    public boolean closeConnection();

}
