package com.example.gestql;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifierProduit extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(ModifierProduit.class.getResource("ModifierProduit.fxml"));
        Parent root = loader.load();

        stage.setTitle("Modifier un produit");
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));

        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
