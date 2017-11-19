package com.leafguard.connectors;

public class HomeUnitServerConnector implements ConnectorInterface{
    @Override
    public void sendData(String data) {
    }

    @Override
    public String receiveData() {
        return null;
    }

    @Override
    public boolean openConnection() {
        return false;
    }

    @Override
    public boolean closeConnection() {
        return false;
    }
}
