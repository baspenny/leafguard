package com.leafguard.homeserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

public class ClientThread extends Thread
{
    private Socket  socket;
    private String  uniqueID;
    private Boolean keepGoing = true;

    DataInputStream  input;
    DataOutputStream output;

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.uniqueID = UUID.randomUUID().toString();
    }

    @Override
    public void run() {
        while (keepGoing) {
            // Run the code to communicate with client
            try {
                this.input = new DataInputStream(socket.getInputStream());
                this.output = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                System.out.println("Could not create Datastream(s)" + e.getMessage());
            }

        }
        // We want to close the thread.
        try {
            System.out.println("Thread with UUID "+ this.getUniqueID() + " disconnecting and cleaning up!");
            socket.close();
            this.input.close();
            this.output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage() + this.getClass().getName());
        }

    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void stopThread() {
        this.keepGoing = false;
    }



}


