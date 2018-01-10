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
        this.uniqueID       = "";

        try {
            this.input = new DataInputStream(this.socket.getInputStream());
            this.output = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {

        }
    }

    @Override
    public void run() {

        try {
            String messageFromCompanyServer = input.readUTF();
            String returnVal = "";


            if(messageFromCompanyServer.equals("moisture")) {
                returnVal = Integer.toString(homeServer.arduino.getMoisturePercentage());
            }

            if(messageFromCompanyServer.equals("waterplant")) {
                returnVal = homeServer.arduino.controlPump(1);
            }

            this.output.writeUTF(Integer.toString(homeServer.arduino.getMoisturePercentage()));
           // this.output.writeUTF(returnVal);
            this.output.flush();
            Log.info("HomeServerWorker: disconnecting from CompanyServer and cleaning up!");
            // Cleaning up
            this.input.close();
            this.output.close();
            this.socket.close();

        } catch (IOException e) {
            Log.critical(e.getMessage());
        }
    }

    public String getUniqueID() {
        return uniqueID;
    }
}
