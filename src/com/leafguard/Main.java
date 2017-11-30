package com.leafguard;

import com.leafguard.leafguard.SerialConnector;
import com.leafguard.leafguard.Arduino;

import java.util.Scanner;

public class Main
{
    Arduino arduino;
    SerialConnector ser;

    public void run()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            this.arduino = new Arduino(new SerialConnector());
            Thread.sleep(400);

            while(scanner.hasNext()) {
                String input = scanner.next();
                if(input.equals("aan")) {
                    System.out.println(arduino.controlPump(1));
                }
                if(input.equals("uit")) {
                    System.out.println(arduino.controlPump(0));
                }
                if(input.equals("vocht")) {
                    System.out.println(this.arduino.getMoisturePercentage());
                }
                if(input.equals("stop")) {
                    arduino.closeSerialConnection();
                    System.exit(0);
                }
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
