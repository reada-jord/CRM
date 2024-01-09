package Controller;

import Models.SharedConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AjouterProduitController implements Initializable {

    @FXML
    private TextField idField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField prixField;

    @FXML
    private TextField stockField;

    public void AjouterBtn(MouseEvent mouseEvent) {
        int id = Integer.parseInt(idField.getText());
        String nom = nomField.getText();
        String description = descriptionField.getText();
        double prix = Double.parseDouble(prixField.getText());
        int stock = Integer.parseInt(stockField.getText());


        try {
            Connection cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate("INSERT INTO produit VALUES('" + id + "','" + nom + "','" + description + "','" + prix + "','" + stock + "')");

            Node source = (Node) mouseEvent.getSource();
            Stage ajoutProduitStage = (Stage) source.getScene().getWindow();
            ajoutProduitStage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Ajouter(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
