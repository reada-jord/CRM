package com.example.gestql;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {



    @Override
    public void start(Stage primaryStage) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            primaryStage.setTitle("Login!");
            primaryStage.setScene(scene);
            primaryStage.show();


    }


    public static void main(String[] args) {
        launch();
    }
}