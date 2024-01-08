package com.example.gestql;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifierCommande extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader indexLoader = new FXMLLoader(ModifierEmploye.class.getResource("ModifierCommande.fxml"));
        Scene scene = new Scene(indexLoader.load());
        stage.setTitle("Modifier une Commande");
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
