package com.leafguard.client;

import com.leafguard.Log;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientGuiController implements Initializable
{
    private String uuid = "";
    private int moisture = 0;

    @FXML
    private Circle waterButton;
    @FXML
    private Rectangle buttonStartPump;
    @FXML
    private Rectangle buttonStopPump;
    @FXML
    private StackPane buttonGetStatus;
    @FXML
    private Arc moistureGauge;
    @FXML
    private Text moistureValue;
    @FXML
    private ImageView face;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setUuid("df309914-e898-11e7-80c1-9a214cf093ag");
        //this.clickDashButton3();
        this.reInitGui();
    }

    @FXML
    private void giveWater() {
//        this.moisture = 89;
        this.getDataFromServer("waterplant");
        this.reInitGui();
    }

    @FXML
    private void buttonStartPump() {
        System.out.println(this.getDataFromServer("startPump"));
        this.moisture = (parseArduinoString(this.getDataFromServer("getMoistureLevel")) /2);
        this.reInitGui();
    }

    @FXML
    private void buttonStopPump(){
        this.getDataFromServer("stopPump");
        this.reInitGui();
    }

    @FXML
    private void buttonGetStatus()
    {
        this.moisture = (parseArduinoString(this.getDataFromServer("getMoistureLevel")) /2);
        this.reInitGui();
    }

    private String getDataFromServer(String message)
    {
        // Prepare a new client with the uuid
        Client client = new Client(this.uuid);
        // Send data and receive response
        return client.sendMessage(message);
    }

    private int parseArduinoString(String message)
    {
        String[] ret = message.split(":");
        return this.moisture = Integer.parseInt(ret[1]);
    }

    private void reInitGui()
    {
        int moisture = this.moisture;

        // For now, the levels are low, neutral and high
        String level = "";
        if(moisture < 20 && moisture >= 0) {
            level = "low";
        } else if (moisture < 55 && moisture >= 20) {
            level = "neutral";
        } else {
            level = "high";
        }
        this.setGauge(moisture);
        this.setGaugeColor(level);
        this.setPlantEmotion(level);
    }

    private void setGauge(int moisture) {

        int arcValueLength = (int)Math.floor(moisture * 2.4);
        int arcValueStartAngle= (int)Math.ceil(-30 + (240 - arcValueLength));

        moistureGauge.setStartAngle(arcValueStartAngle);
        moistureGauge.setLength(arcValueLength);
        moistureValue.setText(Integer.toString(moisture)+ " %");
    }

    private void setGaugeColor(String level) {


        Color arcValueColor = new Color(0,0,0,1);

        if(level.equals("low")) {
            arcValueColor = arcValueColor.web("#ff3300");
        } else if (level.equals("neutral")) {
            arcValueColor = arcValueColor.web("#ffdb4d");
        } else  if(level.equals("high")){
            arcValueColor = arcValueColor.web("#37df8b");
        }
        this.moistureGauge.setStroke(arcValueColor);
    }

    private void setPlantEmotion(String level)
    {
        if(level.equals("low")) {
            face.setImage(new Image("com/leafguard/client/img/sad.png"));
        } else if (level.equals("neutral")) {
            face.setImage(new Image("com/leafguard/client/img/neutral.png"));
        } else  if(level.equals("high")){
            face.setImage(new Image("com/leafguard/client/img/happy.png"));
        }
    }

    /**
     * Getters and setters
     */
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
