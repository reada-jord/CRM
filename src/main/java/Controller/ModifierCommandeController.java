package Controller;

import Models.SharedConnection;
import com.example.gestql.Commandes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import Models.Commande;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class ModifierCommandeController implements Initializable {


    @FXML
    private TextField EditId;
    @FXML
    private TextField ClientId;
    @FXML
    private TextField DateCommande;
    @FXML
    private TextField Status;
    @FXML
    private TextField montantTotal;



    public void EditButton(MouseEvent mouseEvent) {
        int id = Integer.parseInt(EditId.getText());
        int clientid = Integer.parseInt(ClientId.getText());
        String dateCommande = DateCommande.getText();
        String status = Status.getText();
        int montanttotal = Integer.parseInt(montantTotal.getText());

        if (!(ClientId.getText()).equals("") && !dateCommande.equals("") && !status.equals("") && !(montantTotal.getText()).equals("")  ) {
            try {
                Connection cnx = SharedConnection.createConnection();
                Statement stmt = cnx.createStatement();
                stmt.executeUpdate("UPDATE Commande SET client_id='" +clientid+ "', dateCommande ='" +dateCommande+ "' , statut ='" +status+ "' ,montantTotal='" +montanttotal+ "' WHERE ID ='" + id + "'; ");
                Node source = (Node) mouseEvent.getSource();
                Stage ModEmp = (Stage) source.getScene().getWindow();
                ModEmp.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }else{
            Window owner = EditId.getScene().getWindow();
            showAlert(Alert.AlertType.ERROR, owner, "ERREUR!", "VEUILLEZ COMPLETER LES DONNEES!");
        }
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){

    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


    private int  EmId;
    private boolean update;
    public void setTextField(int id, int clientId ,String dateCommande,String status, int MontantTotal ){
        EmId = id;
        ClientId.setText(String.valueOf(clientId));
        DateCommande.setText(dateCommande);
        Status.setText(status);
        montantTotal.setText(String.valueOf(MontantTotal));





    }
    void setUpdate(boolean b) {
        this.update = b;

    }
}

