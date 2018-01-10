package com.leafguard.leafguard;

public class Arduino
{
    private int id;
    private int pumpState;
    private int moisturePercentage;

    private SerialConnectorInterface serialConnector;

    /**
     * Constructor
     * Set the serialconnector and load the object with data from Arduino
     * @param serialConnector
     */
    public Arduino(SerialConnectorInterface serialConnector)
    {
        this.serialConnector = serialConnector;
        serialConnector.initialize();
    }

    /**
     * @return id from the Arduino instance
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Load fresh data from Arduino and
     * @return the current state of the pump
     */
    private int getPumpState()
    {
        this.getDataFromSerial();
        return this.pumpState;
    }

    /**
     * Load fresh data from Arduino and
     * @return the current moisturelevel
     */
    public int getMoisturePercentage()
    {
        this.getDataFromSerial();
        return this.moisturePercentage;
    }

    /**
     * This activates or deactivates the pump
     * 1 = active
     * 0 = inactive
     */
    public synchronized String controlPump(int state) {
        // Set a fresh value in the pumpState property
        this.getPumpState();

        if(this.pumpState == 1 && state == 1) {
            return "Pomp staat al aan!";

        } else if (this.pumpState == 0 && state == 1) {
            return togglePump(1);

        } else if (this.pumpState == 1 && state == 0) {

            return togglePump(0);
        }
        return "Pomp staat al uit!";
    }


    /**
     * The actual pump switch action on the Arduino
     * @param state
     */
    public String togglePump(int state)
    {
        if(state == 1) {
            this.serialConnector.sendData("pumpon");
            return "ok";
        }

        this.serialConnector.sendData("pumpoff");
        return "ok";
    }

    /**
     * Get the full data string from Arduino
     * and pass it to parseReturnValue()
     */
    public void getDataFromSerial()
    {
        this.serialConnector.sendData("data");
        this.sleep();
        this.parseReturnValue(this.serialConnector.receiveData());
    }

    /**
     * Parse the returned string from Arduino
     * e.g. : moisture={value}&pumpstate={value}
     */
    private void parseReturnValue(String returnstring)
    {
        String[] explodedReturnstring = returnstring.split("&");

        for(String returnstringPart : explodedReturnstring) {
            String[] kv = returnstringPart.split("=");

            if(kv[0].equals("moisture")) {
                moisturePercentage = Integer.parseInt(kv[1]);
            }
            if(kv[0].equals("pumpstate")) {
                pumpState = Integer.parseInt(kv[1]);
            }
        }
    }

    /**
     * Close the current serial connection
     */
    public void closeSerialConnection()
    {
        this.serialConnector.close();
    }

    public void sleep() {
        try{
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }


    public void sendData(String data) {

    }


    public String receiveData() {
        return null;
    }


    public boolean openConnection() {
        return false;
    }


    public boolean closeConnection() {
        return false;
    }
}

