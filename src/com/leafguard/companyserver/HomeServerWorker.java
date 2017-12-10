package com.leafguard.companyserver;

import com.leafguard.homeserver.HomeServer;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class HomeServerWorker implements Runnable
{
    private Socket      socket;
    private HomeServer homeServer;
    private String      uniqueID;

    private DataInputStream   input;
    private DataOutputStream  output;

    public HomeServerWorker(Socket socket, HomeServer server) {
        this.socket = socket;
        this.homeServer = server;
        this.uniqueID = UUID.randomUUID().toString();
    }

    @Override
    public void run() {



    }

    public String getUniqueID() {
        return uniqueID;
    }




}


