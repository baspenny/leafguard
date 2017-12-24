package com.leafguard.homeserver;

import com.leafguard.Log;
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
    private HomeServer      homeServer;
    private String          uniqueID;

    private DataInputStream   input;
    private DataOutputStream  output;

    public HomeServerWorker(Socket socket, HomeServer server) {
        this.socket         = socket;
        this.homeServer  = server;
        this.uniqueID       = UUID.randomUUID().toString();
    }

    @Override
    public void run() {

        Log.info("hello from HomeServerWorker");
        homeServer.printMessage();
//        SerialConnectorInterface sc = new SerialConnectorMock();
//        Arduino arduino = new Arduino(sc);
//
//        System.out.println(arduino.getMoisturePercentage());


    }

    public String getUniqueID() {
        return uniqueID;
    }




}


