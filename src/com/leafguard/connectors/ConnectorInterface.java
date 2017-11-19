package com.leafguard.connectors;

public interface ConnectorInterface {

    public void sendData(String data);

    public String receiveData();

    public boolean openConnection();

    public boolean closeConnection();

}
