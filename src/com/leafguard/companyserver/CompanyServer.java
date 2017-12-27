package com.leafguard.companyserver;

import com.leafguard.Log;
import com.leafguard.client.Client;
import com.leafguard.homeserver.HomeServer;
import com.leafguard.leafguard.Arduino;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.scene.Parent;
import org.omg.CORBA.DATA_CONVERSION;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CompanyServer
{
    private ServerSocket serverSocket;
    private ArrayList<String> allowedClients;

    /**
     * Store the created workers
     * K = uuid
     * V = CompanyServerWorker Object
     */
    private HashMap<String, CompanyServerWorker> workers = new HashMap<>();

    // @todo: bind client to specific homeserver
    private ArrayList<HomeServer> homeServers;

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

    private void handleIncomingConnection()
    {
        Log.info("Listening for client connections...");
        while(true) {
            try {
                this.startThread(this.serverSocket.accept());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    /**
     * Starts a new Thread
     * @param socket
     */
    private void startThread(Socket socket)
    {
        try {
            // Create the in and output streams
            DataInputStream  in  = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // The client who connects, sends it's uuid
            String uuid = in.readUTF();

            // Create new worker and pass it to a thread
            CompanyServerWorker csw = new CompanyServerWorker(in, out, uuid, this);
            Thread t = new Thread(csw);
            // Start the thread
            t.start();

            // Add the worker to the HashMap
            this.workers.put(uuid, csw);

        } catch (IOException e) {

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
    public String connectToHomeServer(String uuid, String message)
    {
        // Map the request uuid to an IP address / target plant
        String ret = "";
        // Op basis van bekende ip-adressen
        // Zoek in een lijst van bekende ip adressen de juiste bestemming voor de client
        // (localhost wordt dan ip van de homeserver waat we verbinding naar toe willen maken )
        try {
            Socket socket           = new Socket("localhost", 6201);
            DataInputStream in      = new DataInputStream(socket.getInputStream());
            DataOutputStream out    = new DataOutputStream(socket.getOutputStream());


            // Send message to HomeServer
            out.writeUTF(uuid + " # " + message);
            out.flush();
            ret = in.readUTF();
            System.out.println("Im here: " + ret);

        } catch (IOException e) {
            Log.critical("Could not connect to Home-Server: " +e.getMessage());
        }
        return ret;
    }

    public ArrayList<String> getAllowedClients() {
        return allowedClients;
    }

    /**
     * This method registeres all the allowed clients
     * @todo consideration: make an user-interface so a company admin can grant acces to a device
     */
    private void registerAllowedClients()
    {
        this.allowedClients = new ArrayList<>();
        this.allowedClients.add("df309914-e898-11e7-80c1-9a214cf093ae");
        this.allowedClients.add("df309914-e898-11e7-80c1-9a214cf093af");
    }
}
