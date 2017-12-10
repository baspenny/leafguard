package com.leafguard.companyserver;

import com.leafguard.Log;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CompanyServer
{
    private ServerSocket serverSocket;

    public CompanyServer()
    {
        // Create new serversocket
        try {

            this.serverSocket = new ServerSocket(3101);
            Log.info("Serversocket on COMPANY-SERVER started");
            this.listenToIncomingConnection();

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

    private void listenToIncomingConnection()
    {
        Log.info("Listening for client connections...");

        while(true) {
            try {

                //Socket socket = ;
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

}
