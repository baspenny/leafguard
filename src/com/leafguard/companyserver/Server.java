package com.leafguard.companyserver;

import com.leafguard.homeserver.ClientThread;

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
    private ArrayList<ClientThread> clientThreads = new ArrayList<ClientThread>();
    private HashMap<String, ClientThread> clients = new HashMap<String, ClientThread>();

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
        //this.clientThreads.add(ct);
        this.clients.put(ct.getUniqueID(), ct);
        ct.start();

        this.log("ClientThread with UUID "+ct.getUniqueID()+ " is started");
        this.log("The current number of clients is: "+this.clients.size());

    }

    private synchronized void stopThread(ClientThread clientThread) {
        // Loop trough the clientThreads and match it to the clientThread in the param
        // Then invoke the stop method on it
        for (String uuid: this.clients.keySet()) {

            if(clientThread.getUniqueID().equals(uuid)) {
                ClientThread client = this.clients.get(uuid);
                client.stopThread();
            }
        }
    }


    private void listenToIncomingConnection()
    {
        this.log("Listening for client connections...");
        // @todo: call a method that loops trough the array and removes the disconnected clients
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
