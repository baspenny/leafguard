package com.leafguard.companyserver;

import com.leafguard.Log;
import com.leafguard.client.Client;
import com.leafguard.homeserver.HomeServer;
import com.leafguard.leafguard.Arduino;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class CompanyServer
{
    private ServerSocket serverSocket;
    private ArrayList<HomeServer> homeServers;
    private ArrayList<String> allowedClients;

    public CompanyServer()
    {
        this.registerAllowedClients();
        try {
            this.serverSocket = new ServerSocket(3101);
            Log.info("COMPANY-SERVER started");

            this.handleIncomingConnection();

        } catch (IOException e) {
            System.out.println(e.getMessage() );
            System.exit(1);
        }
     }

    /**
     * Starts a new Thread
     * @param socket
     */
    private void startThread(Socket socket)
    {
        CompanyServerWorker ct = new CompanyServerWorker(socket, this);
        Thread t = new Thread(ct);
        t.start();
    }


    private void connectToHomeUnit()
    {
        try {
            Socket socket   = new Socket("127.0.0.1", 6201);
        } catch (IOException e) {

        }
    }






    private void handleIncomingConnection()
    {
        Log.info("Listening for client connections...");

        while(true) {
            try {
                this.startThread(this.serverSocket.accept());
                Log.info("Resuming listening for new connections...");

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    public static void main(String[] args)
    {
        CompanyServer server = new CompanyServer();
    }

    /**
     * Make a connection to the homeserver.
     * This is a stateful connection
     */
    public void connectToHomeServer()
    {
        boolean stayConnected = true;
        // Op basis van bekende ip-adressen
        // Zoek in een lijst van bekende ip adressen de juiste bestemming voor de client
        // (localhost wordt dan ip van de homeserver waat we verbinding naar toe willen maken )
        try {
            Socket socket           = new Socket("localhost", 6201);
//            DataOutputStream out    = new DataOutputStream(socket.getOutputStream());
//            DataInputStream in      = new DataInputStream(socket.getInputStream());
//

//            while (stayConnected) {
//
//            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> getAllowedClients() {
        return allowedClients;
    }

    /**
     * This method registeres all the allowed clients
     * @todo consideration: make an interface so a company admin can grant acces to a device
     */
    private void registerAllowedClients()
    {
        this.allowedClients = new ArrayList<>();
        this.allowedClients.add("df309914-e898-11e7-80c1-9a214cf093ae");
        this.allowedClients.add("df309914-e898-11e7-80c1-9a214cf093af");


        Log.info("Registred clientIds: " + this.allowedClients);
    }
}
