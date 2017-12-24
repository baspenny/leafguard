package com.leafguard.companyserver;

import com.leafguard.homeserver.HomeServer;
import com.leafguard.leafguard.Arduino;
import com.leafguard.leafguard.SerialConnectorInterface;
import com.leafguard.leafguard.SerialConnectorMock;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class HomeServerWorker implements Runnable
{
    private Socket          socket;
    private CompanyServer   companyServer;
    private String          uniqueID;

    private DataInputStream   input;
    private DataOutputStream  output;

    public HomeServerWorker(Socket socket, CompanyServer server) {
        this.socket         = socket;
        this.companyServer  = server;
        this.uniqueID       = UUID.randomUUID().toString();
    }

    @Override
    public void run() {
        SerialConnectorInterface sc = new SerialConnectorMock();
        Arduino arduino = new Arduino(sc);

        System.out.println(arduino.getMoisturePercentage());


    }

    public String getUniqueID() {
        return uniqueID;
    }




}


