package com.example.gestql;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AjouterProduit extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(AjouterProduit.class.getResource("AjouterProduit.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Ajouter un Produit");
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
