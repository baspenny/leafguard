package com.leafguard;

import com.leafguard.connectors.SerialConnector;
import com.leafguard.models.Arduino;

import java.util.Scanner;


public class Main
{

    public static void main(String[] args) {
        try {
            SerialConnector serialConnector = new SerialConnector();
            serialConnector.initialize();
            System.out.println(serialConnector.getResponse());


            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNext()) {
                serialConnector.sendMessage(scanner.next());
                System.out.println(serialConnector.getResponse());
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
