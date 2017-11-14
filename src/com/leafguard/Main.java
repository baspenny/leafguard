package com.leafguard;

import com.leafguard.connectors.SerialConnector;
import com.leafguard.models.Arduino;

import java.util.Scanner;


public class Main
{

    private int moisture;
    private int pumpstate;

    public void run() {
        try {
            SerialConnector serialConnector = new SerialConnector();
            serialConnector.initialize();
            Scanner scanner = new Scanner(System.in);

            while(scanner.hasNext()) {
                String result = serialConnector.sendMessage(scanner.next());

                String[] explodedresults = result.split("&");

                for(String ff : explodedresults) {
                    String[] kv = ff.split("=");

                    if(kv[0].equals("moisture")) {
                        moisture = Integer.parseInt(kv[1]);
                    }
                    if(kv[0].equals("pumpstate")) {
                        pumpstate = Integer.parseInt(kv[1]);
                    }

                }

                System.out.println("moisture is " + this.moisture + " and the pump is " + (this.pumpstate == 1 ? "on" : "off"));

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
