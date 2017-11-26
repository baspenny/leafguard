package com.leafguard.companyserver;

import com.leafguard.companyserver.ClientThread;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Server
{
    private ServerSocket serverSocket;

    public Server()
    {
        // Create new serversocket
        try {

            this.serverSocket = new ServerSocket(3101);
            this.log("Serversocket on COMPANY-SERVER started");
            this.listenToIncomingConnection();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
     }

    /**
     * Starts a new Thread and saves it to arraylist so we can keep track of them
     * @param socket
     */
    private void startThread(Socket socket)
    {
        ClientThread ct = new ClientThread(socket);
        ct.start();
    }

    private void listenToIncomingConnection()
    {
        this.log("Listening for client connections...");

        while(true) {
            try {

                Socket socket = this.serverSocket.accept();
                this.startThread(socket);
                this.log("Resuming listening for new connections...");


            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    public static void main(String[] args)
    {
        Server server = new Server();
    }

    private void log(String message) {
        System.out.println(new Date()+" - "+message);
    }
}
