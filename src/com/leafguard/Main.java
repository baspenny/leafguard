package com.leafguard;

import com.leafguard.test.SerialTest;


public class Main
{

    public static void main(String[] args) {
        try {
            SerialTest.main(null);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
