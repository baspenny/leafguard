package com.leafguard.homeserver;

import com.leafguard.Log;
import com.leafguard.homeserver.HomeServer;
import com.leafguard.leafguard.Arduino;
import com.leafguard.leafguard.SerialConnectorInterface;
import com.leafguard.leafguard.SerialConnectorMock;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

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
        this.homeServer     = server;
        this.uniqueID       = UUID.randomUUID().toString();
        try {

            this.input = new DataInputStream(this.socket.getInputStream());
            this.output = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {

        }
    }

    @Override
    public void run() {

        Log.info("hello from HomeServerWorker");
        homeServer.printMessage();
        SerialConnectorInterface sc = new SerialConnectorMock();
        Arduino arduino = new Arduino(sc);

        try {

            output.writeUTF(Integer.toString(arduino.getMoisturePercentage()));
            output.flush();
        } catch (IOException e) {

        }


    }

    public String getUniqueID() {
        return uniqueID;
    }




}


