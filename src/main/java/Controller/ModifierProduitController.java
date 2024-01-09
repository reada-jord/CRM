package Controller;

import Models.SharedConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ModifierProduitController implements Initializable {

    @FXML
    private TextField EditId;

    @FXML
    private TextField EditNom;

    @FXML
    private TextField EditDescription;

    @FXML
    private TextField EditPrix;

    @FXML
    private TextField EditStock;

    public void EditButton(MouseEvent mouseEvent) {
        int id = Integer.parseInt(EditId.getText());
        String nom = EditNom.getText();
        String description = EditDescription.getText();
        double prix = Double.parseDouble(EditPrix.getText());
        int stock = Integer.parseInt(EditStock.getText());

        if (!nom.equals("") && !description.equals("")) {
            try {
                Connection cnx = SharedConnection.createConnection();
                Statement stmt = cnx.createStatement();
                stmt.executeUpdate("UPDATE Produit SET nom='" + nom + "', description ='" + description + "', prix ='" + prix + "', stock ='" + stock + "' WHERE id ='" + id + "'; ");
                Node source = (Node) mouseEvent.getSource();
                Stage ModProd = (Stage) source.getScene().getWindow();
                ModProd.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } else {
            Window owner = EditId.getScene().getWindow();
            showAlert(Alert.AlertType.ERROR, owner, "ERREUR!", "VEUILLEZ COMPLETER LES DONNEES!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    private int EmId;
    private boolean update;

    public void setTextField(int id, String nom, String description, double prix, int stock) {
        EmId = id;
        EditId.setText(String.valueOf(id));
        EditNom.setText(nom);
        EditDescription.setText(description);
        EditPrix.setText(String.valueOf(prix));
        EditStock.setText(String.valueOf(stock));
    }

    public void setUpdate(boolean b) {
        this.update = b;
    }
}
