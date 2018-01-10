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

    private String targetHomeServer;
    /**
     * Arraylist with the allowed clients
     */
    private ArrayList<String> allowedClients;

    /**
     * Arraylist with all the homeservers registered at LeafGuard
     */
    private HashMap<String, String> homeServers = new HashMap<>();

    /**
     * Constructor for CompanyServer()
     */
    public CompanyServer()
    {
        this.registerAllowedClients();
        this.registerHomeservers();

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
     * When a incoming connection is detected,
     * handle it
     */
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

            // Create new worker and pass it to a thread along
            // with the input- and output stream, the uuid from
            // the requesting client and self
            CompanyServerWorker csw = new CompanyServerWorker(in, out, uuid, this);
            Thread t = new Thread(csw);
            // Start the thread
            t.start();

        } catch (IOException e) {
            Log.critical("There was an error starting a CompanyServerWorker: " + e.getCause());
        }

    }

    /**
     * Make a connection to the homeserver.
     * This is a stateful connection
     */
    public String connectToHomeServer(String uuid, String message)
    {

        // Do we know this client?
        if(!this.checkIfClientIsAllowed(uuid)) {
            return "error:client " +uuid+ "is not allowed";
        }
        // Map the uuid from requester to an (ip) address -> target plant
        if(!this.setTargetHomeServer(uuid)) {
            return "error:could not resolve destination for client" + uuid;
        }

        String ret = "";

        try {
            Socket socket           = new Socket(this.targetHomeServer, 6201);
            DataInputStream in      = new DataInputStream(socket.getInputStream());
            DataOutputStream out    = new DataOutputStream(socket.getOutputStream());


            // Send message to HomeServer
            // eg: df309914-e898-11e7-80c1-9a214cf093af#givewater
            out.writeUTF(uuid + "#" + message);
            out.flush();
            ret = in.readUTF();

        } catch (IOException e) {
            Log.critical("Could not connect to Home-Server: " +e.getMessage());
        }
        return ret;
    }

    public ArrayList<String> getAllowedClients() {
        return allowedClients;
    }

    /**
     * Register all the allowed clients
     * @todo consideration: make an user-interface so a company admin can grant acces to a device
     */
    private void registerAllowedClients()
    {
        this.allowedClients = new ArrayList<>();
        this.allowedClients.add("df309914-e898-11e7-80c1-9a214cf093ag");
        this.allowedClients.add("df309914-e898-11e7-80c1-9a214cf093ae");
        this.allowedClients.add("df309914-e898-11e7-80c1-9a214cf093af");
    }

    /**
     * Register all the Homeservers and corresponding LeafGuard units
     */
    private void registerHomeservers()
    {
        this.homeServers = new HashMap<>();
        this.homeServers.put("df309914-e898-11e7-80c1-9a214cf093ag", "127.0.0.1");
        this.homeServers.put("df309914-e898-11e7-80c1-9a214cf093ae", "localhost");
        this.homeServers.put("df309914-e898-11e7-80c1-9a214cf093af", "nobody.none.com");
    }


    private boolean checkIfClientIsAllowed(String uuid)
    {
        for (String client: this.getAllowedClients()) {
            if(client.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    private boolean setTargetHomeServer(String uuid)
    {
        for (Map.Entry homeserver : homeServers.entrySet()) {
            if(homeserver.getKey().equals(uuid)) {
                this.targetHomeServer = homeserver.getValue().toString();
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args)
    {
        CompanyServer server = new CompanyServer();
    }

}
