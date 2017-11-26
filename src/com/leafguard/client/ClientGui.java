package com.leafguard.client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class ClientGui extends Application
{
    LoginDialog dialog;
    Stage stage;
    String response;
    Boolean run = true;

    @Override
    public void start(Stage stage)
    {
        this.stage = stage;
//        this.showLoginDialog();


        if(run) {
            this.getDataFromServer();
            this.showMainWindow();
        }
    }

    public void showLoginDialog() {
        this.dialog = new LoginDialog();
        dialog.display(this);
    }

    public void showMainWindow()
    {
        stage.setTitle("LeafGuard App");
        stage.setResizable(false);

        GridPane pane  = new GridPane();
        pane.setStyle("-fx-background-color: #e5e5e5;");
        HBox logocontainer = new HBox();
        Image headerFile = new Image("com/leafguard/client/header.png");
        ImageView header = new ImageView(headerFile);
        header.setPreserveRatio(true);
        header.setFitWidth(405);
        logocontainer.getChildren().add(header);
        pane.add(logocontainer,0,0,3,1);

        HBox dashboard = new HBox();
        Rectangle item1 = new Rectangle(135,60);
        item1.setFill(Color.WHITE);
        Rectangle item2 = new Rectangle(135,60);
        item2.setFill(Color.WHITE);
        Rectangle item3 = new Rectangle(135,60);
        item3.setFill(Color.WHITE   );

        dashboard.getChildren().addAll(item1,item2,item3);
        pane.add(dashboard, 0,1,3,1);

        HBox graphContainer = new HBox();
        graphContainer.setPadding(new Insets(50,50,50,50));

        Group group = new Group();
        int moistureValue = 65;
        Color arcValueColor = new Color(0,0,0,1);
        String imageString = "";

        if(moistureValue < 20 && moistureValue >= 0) {
            arcValueColor = arcValueColor.web("#ff3300");
            imageString = "com/leafguard/client/sad.png";
        } else if (moistureValue < 55 && moistureValue >= 20) {
            arcValueColor = arcValueColor.web("#ffdb4d");
            imageString = "com/leafguard/client/neutral.png";
        } else {
            arcValueColor = arcValueColor.web("#37df8b");
            imageString = "com/leafguard/client/happy.png";
        }

        // Calculate the length and startangle of the arc
        int arcValueLength = (int)Math.floor(moistureValue * 2.4);
        int arcValueStartAngle= (int)Math.ceil(-30 + (240 - arcValueLength));
        // What you subtract from the length, you add on the startAngle == total has to be 210
        Arc arcValue = new Arc(47,50,150,150, arcValueStartAngle, arcValueLength);
        arcValue.setType(ArcType.OPEN);
        arcValue.setStrokeWidth(17);
        arcValue.setStroke(arcValueColor);
        arcValue.setStrokeType(StrokeType.CENTERED);
        arcValue.setFill(null);

        Arc arcFull = new Arc(47,50,150,150, -30,240);
        arcFull.setType(ArcType.OPEN);
        arcFull.setStrokeWidth(14);
        arcFull.setStroke(Color.web("#6f6f6f"));
        arcFull.setStrokeType(StrokeType.CENTERED);
        arcFull.setFill(null);

        Image plantFile = new Image("com/leafguard/client/plant.png");
        ImageView plant = new ImageView(plantFile);
        plant.setPreserveRatio(true);
        plant.setFitWidth(130);
        plant.setX(-20);
        plant.setY(50);

        Image happyFile = new Image(imageString);
        ImageView happy = new ImageView(happyFile);
        happy.setPreserveRatio(true);
        happy.setFitWidth(50);
        happy.setY(200);
        happy.setX(20);

        Text text = new Text();
        text.setFont(Font.font("verdana", 36));
        text.setFill(Color.web("#6f6f6f"));
        text.setText(moistureValue + "%");
        text.setY(10);
        text.setX(10);


        Circle waterButton = new Circle();
        waterButton.setRadius(40);
        waterButton.setCenterX(47);
        waterButton.setCenterY(320);
        waterButton.setFill(Color.web("#37df8b"));


        Text buttonText = new Text();
        buttonText.setFont(Font.font("Avenir next", FontWeight.BOLD, 14));
        buttonText.setFill(Color.web("#252525"));
        buttonText.setText("TO WATER");
        buttonText.setX(10);
        buttonText.setY(390);

        Image dropsFile = new Image("com/leafguard/client/drops.png");
        ImageView drops = new ImageView(dropsFile);
        drops.setPreserveRatio(true);
        drops.setFitWidth(50);
        drops.setY(298);
        drops.setX(22);



        group.getChildren().addAll(arcFull,arcValue, plant, happy,text, waterButton, buttonText, drops);
        graphContainer.getChildren().addAll(group);
        pane.add(graphContainer,0,2,3,1);


        waterButton.setOnMousePressed(event -> {
            System.out.println("Water???");
        });



        Scene scene = new Scene(pane, 405, 712);
        scene.getStylesheets().add("com/leafguard/client/style.css");


        stage.setScene(scene);
        stage.show();
    }

    private void getDataFromServer() {
        Client client = new Client();
        client.run();


        // Hier ga ik bijvoorbeeld
    }


    protected void request(String username, String password)
    {
        if(username.equals("leaf") && password.equals("guard")) {
            this.response = "Login succesfull";
            dialog.stop();
        } else {
            this.response = "ERROR";
        }
    }

    protected String response() {
        return this.response;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
