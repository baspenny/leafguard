package com.leafguard.models;

public class ArduinoController
{
    private int id;
    private int pumpState;


    public ArduinoController(int id) {
        this.id = id;
    }

    public double getMoistureLevel() {
        return 0;
    }

    /**
     * This activates or deactivates the pump
     * 1 = activate
     * 0 = deactivate
     */
    public void controlPump(int state) {
        // Set a fresh value in the pumpState property
        this.getPumpState();

        if(this.pumpState == 1 && state == 1) {
            togglePump(1);

        } else if (this.pumpState == 0 && state == 1) {
            togglePump(1);

        } else if (this.pumpState == 1 && state == 0) {
            togglePump(0);

        }
        togglePump(0);
    }

    /**
     * @return id from the Arduino instance
     */
    public int getId() {
        return this.id;
    }

    /**
     * Loads the fresh state of the pump from the Arduino
     */

    private void getPumpState() {
        // add code to get the status of the pump form Arduino
        // pumpState = realtime data from Arduino
        this.pumpState = pumpState;
    }


    /**
     * The actual pump switch action on the Arduino
     * @param state
     */
    private void togglePump(int state) {
        if(state == 1) {
            // toggle on
            return;
        }
        // toggle off
        return;
    }



}

