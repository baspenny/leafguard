package com.leafguard.client;

import com.leafguard.Log;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class Client
{
    private Boolean run = true;
    private String uuid;
    private DataOutputStream out;
    private DataInputStream in;

    public Client(String uuid)
    {
        try {
            this.setUuid(uuid);
        } catch (InvalidObjectException e) {
            Log.warning("Client.java: There was a problem: "+e.getMessage()+"... Exiting");
            System.exit(1);
        }

        try {
            Socket socket   = new Socket("127.0.0.1", 3101);
            this.in         = new DataInputStream(socket.getInputStream());
            this.out        = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(this.uuid);
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
            Log.info("Client.java: Sending from uuid " + this.uuid);
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
            Log.info("Client.java: "+this.uuid + " disconnecting");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkCredentials(String username, String password) {

        return true;
    }

    public String getUuid() {
        return uuid;
    }

    private void setUuid(String uuid) throws InvalidObjectException
    {
        if(uuid.isEmpty() || uuid.length() <= 0 || uuid.equals(null)) {
            throw new InvalidObjectException("No uuid given");
        }
        this.uuid = uuid;
    }
}
