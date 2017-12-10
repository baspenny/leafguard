package com.leafguard.homeserver;

import com.leafguard.Log;
import com.leafguard.companyserver.HomeServerWorker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HomeServer {
    private ServerSocket serverSocket;

    public HomeServer()
    {
        try {
            this.serverSocket = new ServerSocket(3201);
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
                this.createThread(this.serverSocket.accept());
                Log.info("New incoming connection");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    private void createThread(Socket socket) {
        HomeServerWorker homeServerWorker = new HomeServerWorker(socket, this);
        Thread t = new Thread(homeServerWorker);
        t.start();
        Log.info("New HomeServerWorker dispatched");
    }

    public static void main(String[] args) {
        HomeServer homeServer = new HomeServer();
    }

}
