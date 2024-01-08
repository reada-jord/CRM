package Controller;

import Models.SharedConnection;
import com.example.gestql.Clients;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import Models.Client;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class ModifierClientController implements Initializable {


    @FXML
    private TextField EditId;
    @FXML
    private TextField EditNom;

    @FXML
    private TextField EditPrenom;

    @FXML
    private TextField EditAdresse;
    @FXML
    public TextField EditTele;

    @FXML
    private TextField EditEmail;

    @FXML
    public CheckBox EditEtat;

    int idClient;
    public int Etat(CheckBox pr) {
        if (pr.isSelected()) {
            return 1;
        } else return 0;
    }

    public void EditButton(MouseEvent mouseEvent) {
        int id = Integer.parseInt(EditId.getText());
        String nom = EditNom.getText();
        String prenom = EditPrenom.getText();
        String adresse = EditAdresse.getText();
        String telephone = EditTele.getText();
        String email = EditEmail.getText();
        
        int pre = Etat(EditEtat);
        if (!nom.equals("") && !prenom.equals("") && !telephone.equals("") && !email.equals("") && !adresse.equals("") ) {
            try {
                Connection cnx = SharedConnection.createConnection();
                Statement stmt = cnx.createStatement();
                stmt.executeUpdate("UPDATE Client SET nom='" +nom+ "', prenom ='" +prenom+ "' , numeroTelephone ='" +telephone+ "' ,email='" +email+ "', etat ='" +pre+ "' WHERE ID ='" + id + "'; ");
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
    public void setTextField(int id, String fname ,String lname,String adresse, String tele, String email, int Etat  ){
        EmId = id;
        EditNom.setText(lname);
        EditPrenom.setText(fname);
        EditAdresse.setText(adresse);
        EditTele.setText(tele);
        EditEmail.setText(email);
        
        if (Etat == 1){
            EditEtat.setSelected(true);
        }else {
            EditEtat.setSelected(false);
        }


    }
    void setUpdate(boolean b) {
        this.update = b;

    }
}
