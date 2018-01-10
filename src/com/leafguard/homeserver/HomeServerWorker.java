package com.leafguard.homeserver;

import com.leafguard.Log;
import com.leafguard.homeserver.HomeServer;
import com.leafguard.leafguard.Arduino;
import com.leafguard.leafguard.SerialConnectorInterface;
import com.leafguard.leafguard.SerialConnectorMock;

import java.io.*;
import java.net.Socket;

/**
 * HomeServerWorker
 * A thread class for handling the socket connection
 * This is where the magic happens!
 */
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
            this.input  = new DataInputStream(this.socket.getInputStream());
            this.output = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {

        }
    }

    @Override
    public void run() {

        try {
            String messageFromCompanyServer = input.readUTF();
            String returnVal = "";

            // Explode the message from CompanyServer
            String[] request = messageFromCompanyServer.split("#");
            String uuid     = request[0];
            String message  = request[1];

            // Check wich command to run
            // @todo: Is it realy the responsibility of the thread to do things on the Arduino.?
            // I think not.
            if(message.equals("moisture")) {
                String moisturePercentage = Integer.toString(homeServer.arduino.getMoisturePercentage());
                returnVal = "succes:" + moisturePercentage;
            } else if (message.equals("waterplant")) {
                String ret = homeServer.arduino.controlPump(1);
                returnVal = "succes:" + ret;

            } else {
                returnVal = "error:unknown command: " + message;
            }

            // Return response
            this.output.writeUTF(returnVal);
            this.output.flush();

            // Cleaning up
            Log.info("HomeServerWorker: disconnecting from CompanyServer and cleaning up!");
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
