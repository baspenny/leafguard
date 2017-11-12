package com.leafguard;

import com.leafguard.connectors.SerialConnector;


public class Main
{

    public static void main(String[] args) {
        try {
            SerialConnector.main(null);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
