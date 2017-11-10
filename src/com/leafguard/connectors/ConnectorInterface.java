package com.leafguard.connectors;

public interface ConnectorInterface {

    public String sendData();

    public String receiveData();

    public boolean open();

    public boolean close();

}
