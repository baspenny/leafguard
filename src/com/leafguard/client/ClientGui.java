package com.leafguard.client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
        this.showLoginDialog();

        if(run) {
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
        HBox logocontainer = new HBox();
        Image headerFile = new Image("com/leafguard/client/header.png");
        ImageView header = new ImageView(headerFile);
        header.setPreserveRatio(true);
        header.setFitWidth(405);
        logocontainer.getChildren().add(header);
        pane.add(logocontainer,0,0,3,1);

        Scene scene = new Scene(pane, 405, 712);
        scene.getStylesheets().add("com/leafguard/client/style.css");


        stage.setScene(scene);
        stage.show();
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
