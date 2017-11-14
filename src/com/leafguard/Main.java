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
            Scanner scanner = new Scanner(System.in);

            while(scanner.hasNext()) {
                System.out.println(serialConnector.sendMessage(scanner.next()));
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
