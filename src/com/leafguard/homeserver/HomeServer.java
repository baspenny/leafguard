package com.leafguard.homeserver;

import com.leafguard.Log;
import com.leafguard.leafguard.ArduinoInterface;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class HomeServer {
    private ServerSocket serverSocket;
    /**
     * This is the array with arduinos ho have been instantiated
     * All these connections are ment to be statefull
     */
    private ArrayList<ArduinoInterface> arduinos = new ArrayList<ArduinoInterface>();

    public HomeServer()
    {
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
        Log.info("Listening for client connections...");
        while (true) {
            try {

                this.startThread(this.serverSocket.accept());
                Log.info("New incoming connection");

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

//    private void handleIncomingConnection()
//    {
//        Log.info("Listening for client connections...");
//
//        while(true) {
//            try {
//                this.startThread(this.serverSocket.accept());
//                Log.info("Resuming listening for new connections...");
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                System.exit(1);
//            }
//        }
//    }

    public void printMessage() {
        System.out.println("hello");
    }
    public static void main(String[] args) {
        HomeServer homeServer = new HomeServer();
    }
}
