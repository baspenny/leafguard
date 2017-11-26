package com.leafguard.homeserver;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class ClientThread extends Thread
{
    private Socket  socket;
    private String  uniqueID;

    private ObjectInputStream   input;		// to read from the socket
    private ObjectOutputStream  output;		// to write on the socket

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.uniqueID = UUID.randomUUID().toString();
    }

    @Override
    public void run() {



        // We want to close the thread.
        try {
            System.out.println("Thread with UUID "+ this.getUniqueID() + " disconnecting and cleaning up!");

            socket.close();

        } catch (IOException e) {
            System.out.println(e.getMessage() + this.getClass().getName());
        }

    }

    public String getUniqueID() {
        return uniqueID;
    }




}


