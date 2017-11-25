package com.leafguard.homeserver;

import java.io.IOException;
import java.net.Socket;

public class Client
{
    private Socket socket;

    public Client() {
        try {
            this.socket = new Socket("127.0.0.1", 3101);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.run();
    }

    private void run() {
        while(true) {
            try {
                Thread.sleep(3000);
                this.disconnectFromServer();

            } catch (InterruptedException e) {
                break;
            }


        }
    }

    private void createStreams() {

    }


    // Send message to server that we are going to disconnect.
    private void disconnectFromServer() {
        System.exit(0);
    }



    public static void main(String[] args) {
        Client client = new Client();
    }

}
