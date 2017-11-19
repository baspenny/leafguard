package com.leafguard.connectors;

import com.leafguard.connectors.ConnectorInterface;

public class AppServerConnector implements ConnectorInterface
{
    @Override
    public void sendData(String data) { }

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
