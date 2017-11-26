package com.leafguard.client;

import sun.awt.windows.ThemeReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Boolean run = true;


    public Client() {
        try {
            this.socket = new Socket("127.0.0.1", 3101);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        while(run) {
            try {
                out.println("Hello from client....");
//                /Thread.sleep(10000);
                String response = in.readLine();
                System.out.println(response);
                //this.disconnectFromServer();
                run = false;

            } catch (Exception e) {
                break;
            }


        }
    }

    // Send message to server that we are going to disconnect.
    private void disconnectFromServer() {
        // Clean up streams
        this.run = false;
        out.println("DISCONNECT");
        //System.exit(0);
    }

}
