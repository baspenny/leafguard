package com.leafguard.client;

import com.leafguard.Log;
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

public class ClientGui extends Application
{
    protected Boolean run = true;
    private LoginDialog dialog;
    private Client      client;
    private Stage       stage;
    private String      response;
    private int         moisture = 7;
    private String      uuid = "";

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ClientGui.fxml"));
        stage.setTitle("LeafGuard");
        stage.setScene(new Scene(root, 405, 712));
        stage.show();
    }
}
