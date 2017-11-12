package com.leafguard.models;

import java.util.ArrayList;

public class HomeUnit {
    private ArrayList<ArduinoController> arduinoControllers;


    public void sendMessage(String message) {

    }

    public String receiveMessage() {
        return null;
    }

    public boolean addArduino(ArduinoController arduinoController) {
        if( this.arduinoControllers.add(arduinoController)) {
            return true;
        }
        return false;
    }

 }
