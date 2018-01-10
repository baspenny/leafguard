package com.leafguard.companyserver;

import com.leafguard.Log;
import java.io.*;
import java.net.Socket;

/**
 * CompanyServerWorker
 *
 * A thread class for handling the socket connection
 * Nothing much to see here.
 */
public class CompanyServerWorker implements Runnable
{
    private Socket              socket;
    private DataInputStream     in;
    private DataOutputStream    out;
    private CompanyServer       companyServer;
    private String              uuid;
    private boolean             keepRunning = true;

    /**
     * Construction of the worker needs:
     *
     * @param in
     * @param out
     * @param uuid
     * @param companyServer
     */
    public CompanyServerWorker(DataInputStream in , DataOutputStream out, String uuid, CompanyServer companyServer)
    {
        this.socket         = socket;
        this.in             = in;
        this.out            = out;
        this.companyServer  = companyServer;
        this.uuid           = uuid;
    }

    public void run() {
        try {

            String clientMessage = "";
            String ret = "";

            while (keepRunning) {
                // Read the message from Client.java
                clientMessage = in.readUTF();
                // Pass the message to the HomeServer trough companyserver;
                out.writeUTF(companyServer.connectToHomeServer(this.uuid, clientMessage));
                out.flush();
                break;
            }
            // Log of message
            Log.info("CompanyServerWorker: Client with UUID " + this.getClientID() + " disconnecting and cleaning up!");
            // Cleaning up
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            //System.out.println(e.getMessage() + this.getClass().getName());
        }

    }


    public String getClientID() {
        return this.uuid;
    }

    public void setKeepRunning(boolean keepRunning) {
        this.keepRunning = keepRunning;
    }
}


