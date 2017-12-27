package com.leafguard.homeserver;

import com.leafguard.Log;
import com.leafguard.leafguard.Arduino;
import com.leafguard.leafguard.ArduinoInterface;
import com.leafguard.leafguard.SerialConnectorMock;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;

public class HomeServer
{

    private ServerSocket serverSocket;
    private ArrayList<Thread> threads = new ArrayList<>();
    public Arduino arduino;

    /**
     * This is the array with arduinos ho have been instantiated
     * All these connections are ment to be statefull
     */
    private ArrayList<ArduinoInterface> arduinos = new ArrayList<ArduinoInterface>();

    public HomeServer()
    {
        this.arduino = new Arduino(new SerialConnectorMock());


        try {
            this.serverSocket = new ServerSocket(6201);
            Log.info("HOME-SERVER started");
            this.handleIncomingConnection();

        } catch (IOException e ) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

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



    public void printMessage() {
        Log.info("This is printmessage in Homeserver invoked by HomeServerWorker");
    }
    public static void main(String[] args) {
        HomeServer homeServer = new HomeServer();
    }
}
