package com.leafguard.client;

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
            Socket socket           = new Socket("127.0.0.1", 3101);
            this.in  = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

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
            ret = in.readUTF();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
}
