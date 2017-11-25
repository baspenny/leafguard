package com.leafguard.client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class LoginDialog
{
    String username;
    String password;
    Stage stage;


    public void display(ClientGui parent) {

        this.stage = new Stage();
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.setTitle("LeafGuard");
        this.stage.setResizable(false);
        this.stage.setOnCloseRequest(event -> {
            event.consume();
            parent.run = false;
            this.stop();
        });
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        // Logo
        HBox logoContainer = new HBox();
        logoContainer.setPadding(new Insets(0,0,20,0));
        Image logoFile = new Image("com/leafguard/client/logo-small.png");
        ImageView logo = new ImageView(logoFile);
        logo.setPreserveRatio(true);
        logo.setFitWidth(100);
        logo.setImage(logoFile);
        logoContainer.getChildren().add(logo);
        logoContainer.setAlignment(Pos.CENTER);
        grid.add(logoContainer, 0, 0, 2, 1);

        // Username label
        Label usernameLabel = new Label("Gebruikersnaam:");
        grid.add(usernameLabel, 0,1);
        // Username input
        TextField usernameTextField = new TextField("leaf");
        grid.add(usernameTextField, 1,1);

        // Password label
        Label passwordLabel = new Label("Wachtwoord");
        grid.add(passwordLabel, 0,2);
        // Password input
        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        // Submit
        Button submit = new Button("Inloggen");
        HBox hbSubmitButton = new HBox();
        hbSubmitButton.setAlignment(Pos.BOTTOM_RIGHT);
        hbSubmitButton.getChildren().add(submit);
        grid.add(hbSubmitButton, 1,3);


        submit.setOnAction(event -> {
            System.out.println("Submit is clicked");
            username = usernameTextField.getText();
            password = passwordField.getText();

            parent.request(username, password);
            System.out.println(parent.response());
        });
        // Add grid to the scene, scene to the stage and show the stage
        Scene scene = new Scene(grid, 340, 310);
        scene.getStylesheets().add("com/leafguard/client/style.css");
        submit.setId("green");
        this.stage.setScene(scene);
        this.stage.showAndWait();
    }

    public void stop() {
        this.stage.close();
    }
}
