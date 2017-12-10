package com.leafguard.companyserver;

import java.io.*;
import java.net.Socket;

public class CompanyServerWorker implements Runnable
{
    private Socket          socket;
    private CompanyServer   companyServer;
    private String          uuid;
    private String          clientMessage;
    private boolean         keepRunning = true;


    public CompanyServerWorker(Socket socket, CompanyServer companyServer)
    {
        this.socket         = socket;
        this.companyServer  = companyServer;
    }

    public void run() {
        try {
            // Prepare in and output streams
            DataInputStream  in  = new DataInputStream(this.socket.getInputStream());
            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());

            // Get the uuid as the first message from the Server
            this.uuid = in.readUTF();

            while (keepRunning)
            {
                this.clientMessage = in.readUTF();


                out.writeUTF(this.clientMessage + " From thread" );
                out.flush();

//                if(this.clientMessage.equals("stop")) {
//                    out.writeUTF("Stopping");
//                    out.flush();
//                    this.keepRunning = false;
//                    break;
//                } else {
//                    out.writeUTF("Unknown command...");
//                    out.flush();
//                }
            }
            // Log of message
            System.out.println("Thread with UUID "+ this.getUniqueID() + " disconnecting and cleaning up!");
            // Cleaning up
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage() + this.getClass().getName());
        }

    }

    public String getUniqueID() {
        return this.uuid;
    }

    public void setKeepRunning(boolean keepRunning) {
        this.keepRunning = keepRunning;
    }
}


