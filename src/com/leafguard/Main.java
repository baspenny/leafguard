package com.leafguard;

import com.leafguard.connectors.SerialConnector;
import com.leafguard.models.Arduino;

import java.util.Scanner;


public class Main
{

    public static void main(String[] args) {
        try {



            SerialConnector.main(null);


            SerialConnector serialConnector = new SerialConnector();
//
//
//            Scanner sc = new Scanner(System.in);
//            String i = sc.next();
//            SerialConnector sconn = new SerialConnector();
//            sconn.sendMessage(i);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
