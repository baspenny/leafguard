package com.leafguard.companyserver;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class ClientThread extends Thread
{
    private Socket  socket;
    private String  uniqueID;
    boolean keepRunning = true;

    private BufferedReader  in;		// to read from the socket
    private PrintWriter     out;		// to write on the socket

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.uniqueID = UUID.randomUUID().toString();
    }

    @Override
    public void run() {
        try {

            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);


            while (keepRunning) {

                DataStore db = new DataStore();
                String res = db.getData();
                db.closeConnection();

                // Read incoming stream
                String clientCommand = in.readLine();
                //System.out.println("Client says: " + clientCommand);

                out.println("Server says: " + res);
                out.flush();
                //Thread.sleep(4000);
                keepRunning = false;

            }

            System.out.println("Thread with UUID "+ this.getUniqueID() + " disconnecting and cleaning up!");
            socket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage() + this.getClass().getName());
        }

    }

    public String getUniqueID() {
        return uniqueID;
    }




}


