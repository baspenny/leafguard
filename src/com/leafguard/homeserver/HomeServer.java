package com.leafguard.homeserver;

import com.leafguard.Log;
import com.leafguard.leafguard.Arduino;
import com.leafguard.leafguard.SerialConnector;
import com.leafguard.leafguard.SerialConnectorMock;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * HomeServer class
 *
 * Handles connections from the network (internet) via multithreading
 * Handles serial connection between this and Arduino
 */

public class HomeServer
{
    private ServerSocket serverSocket;

    /**
     * @todo: consideration : Implement system to facilitate more than one LeafGuard system
     * This is the array with arduinos ho have been instantiated
     * All these connections are ment to be statefull
     * private ArrayList<ArduinoInterface> arduinos = new ArrayList<ArduinoInterface>();
     * For now just one Arduino object
     */
    public Arduino arduino; // Statefull object

    /**
     * El constructori!!
     */
    public HomeServer()
    {
        this.arduino = new Arduino(new SerialConnector());

        try {
            this.serverSocket = new ServerSocket(6201);
            Log.info("HOME-SERVER started");
            this.handleIncomingConnection();

        } catch (IOException e ) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * When a incoming connection is detected,
     * handle it
     */
    private void handleIncomingConnection()
    {
        Log.info("HomeServer: Listening for Company-server connections...");
        while (true) {
            try {

                this.startThread(this.serverSocket.accept());
                Log.info("HomeServer: New incoming connection");

            } catch (IOException e) {
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
        HomeServerWorker ct = new HomeServerWorker(socket, this);
        Thread t = new Thread(ct);
        t.start();
    }

    /**
     * Runner
     * @param args
     */
    public static void main(String[] args) {
        HomeServer homeServer = new HomeServer();
    }
}
