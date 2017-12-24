package com.leafguard.companyserver;

import com.leafguard.Log;
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

            // If client is not allowed, end this thread
            if(!this.checkIfClientIsAllowed(this.uuid)) {
                out.writeUTF("Client not allowed... Terminating thread");
                out.flush();
                in.close();
                out.close();
                socket.close();
                keepRunning = false;
            }

            while (keepRunning)
            {

                this.clientMessage = in.readUTF();

                if(this.clientMessage.equals("hit_home_server")) {
                    companyServer.connectToHomeServer();
                }


                if(this.clientMessage.equals("stop")) {
                    out.writeUTF("Stopping");
                    out.flush();
                    this.keepRunning = false;
                    break;
                } else {
                    out.writeUTF(this.clientMessage + " From thread" );
                    out.flush();
                }
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
    private boolean checkIfClientIsAllowed(String uuid)
    {
        for (String client: companyServer.getAllowedClients()) {
            if(client.equals(uuid)) {
                return true;
            }
        }
        return false;
    }



    public String getClientID() {
        return this.uuid;
    }

    public void setKeepRunning(boolean keepRunning) {
        this.keepRunning = keepRunning;
    }
}


