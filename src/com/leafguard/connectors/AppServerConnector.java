package com.leafguard.connectors;

import com.leafguard.connectors.ConnectorInterface;

public class AppServerConnector implements ConnectorInterface
{
    @Override
    public String sendData() {
        return null;
    }

    @Override
    public String receiveData() {
        return null;
    }

    @Override
    public boolean open() {
        return false;
    }

    @Override
    public boolean close() {
        return false;
    }
}
