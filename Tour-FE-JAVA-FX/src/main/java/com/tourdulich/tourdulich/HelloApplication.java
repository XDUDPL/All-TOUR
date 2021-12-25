package com.tourdulich.tourdulich;

import com.sun.jersey.api.client.ClientHandlerException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;


public class HelloApplication extends Application {
    private static Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
            mainStage = stage;
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            }catch (LoadException e){
                root = FXMLLoader.load(getClass().getResource("404.fxml"));
            }
            initComboBox();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.setResizable(false);
            mainStage.show();

    }
    public static Stage getMainStage(){
        return mainStage;
    }
    public void initComboBox(){
    }

    public static void main(String[] args) {
        launch();
    }
}