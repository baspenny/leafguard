package com.leafguard.client;

import com.leafguard.Log;
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
    private int moisture = 35;



    @FXML
    private Circle waterButton;

    @FXML
    private Rectangle DashButton1;

    @FXML
    private Rectangle DashButton2;

    @FXML
    private Rectangle DashButton3;

    @FXML
    private Arc arcValue;

    @FXML
    private Text moistureValue;

    @FXML
    private ImageView face;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setArc();
        moistureValue.setText(Integer.toString(moisture)+ " %");

    }




    @FXML
    private void giveWater() {
        this.moisture = 60;
        this.setArc();
        moistureValue.setText(Integer.toString(moisture)+ " %");
        face.setImage(new Image("com/leafguard/client/img/sad.png"));
        Log.info("Method giveWater invoked");
    }

    @FXML
    private void clickDashButton1() {
        Log.info("Method clickDashButton1 invoked");
    }

    @FXML
    private void clickDashButton2(){
        Log.info("Method clickDashButton2 invoked");
    }

    @FXML
    private void clickDashButton3(){
        Log.info("Method clickDashButton3 invoked");
    }



    private void setArc() {
        int moistureValue = this.moisture;
        int arcValueLength = (int)Math.floor(moistureValue * 2.4);
        int arcValueStartAngle= (int)Math.ceil(-30 + (240 - arcValueLength));


        arcValue.setStroke(this.setArcColor());
        arcValue.setStartAngle(arcValueStartAngle);
        arcValue.setLength(arcValueLength);
    }

    private Color setArcColor() {

        int moistureValue = this.moisture;
        Color arcValueColor = new Color(0,0,0,1);


        if(moistureValue < 20 && moistureValue >= 0) {
            arcValueColor = arcValueColor.web("#ff3300");

        } else if (moistureValue < 55 && moistureValue >= 20) {
            arcValueColor = arcValueColor.web("#ffdb4d");

        } else {
            arcValueColor = arcValueColor.web("#37df8b");

        }
        return arcValueColor;
    }

    private void setArcValue()
    {

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
