package com.leafguard.client;

import com.leafguard.Log;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class Client
{
    private Boolean run = true;
    private final UUID uuid = UUID.randomUUID();
    private DataOutputStream out;
    private DataInputStream in;

    public Client()
    {
        try {
            Socket socket   = new Socket("127.0.0.1", 3101);
            this.in         = new DataInputStream(socket.getInputStream());
            this.out        = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(this.uuid.toString());
            out.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String sendMessage(String message)
    {
        String ret = "";

        try {
            out.writeUTF(message);
            out.flush();
            Log.info("Sending from uuid " + this.uuid);
            ret = in.readUTF();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    public void disconnect() {
        try {
            out.writeUTF("stop");
            out.flush();
            Log.info(this.uuid + " disconnecting");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkCredentials(String username, String password) {

        return true;
    }

}
