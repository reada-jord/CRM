package com.example.gestql;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Index extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader indexLoader = new FXMLLoader(Index.class.getResource("dash.fxml"));
        Scene scene = new Scene(indexLoader.load());
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
