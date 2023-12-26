package Controller;

import Models.SharedConnection;
import com.example.gestql.Employes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import Models.Employe;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class ModifierEmployeController implements Initializable {


    @FXML
    private TextField EditId;
    @FXML
    private TextField EditNom;

    @FXML
    private TextField EditPrenom;

    @FXML
    public TextField EditTele;

    @FXML
    private TextField EditEmail;
    @FXML
    private TextField EditPoste;
    @FXML
    private TextField EditSalaire;

    @FXML
    public CheckBox EditAbs;

    int idEmploye;
    public int abs(CheckBox pr) {
        if (pr.isSelected()) {
            return 1;
        } else return 0;
    }

    public void EditButton(MouseEvent mouseEvent) {
        int id = Integer.parseInt(EditId.getText());
        String nom = EditNom.getText();
        String prenom = EditPrenom.getText();
        String telephone = EditTele.getText();
        String email = EditEmail.getText();
        String poste = EditPoste.getText();
        double salaire = Double.parseDouble(EditSalaire.getText());
        int pre = abs(EditAbs);
        if (!nom.equals("") && !prenom.equals("") && !telephone.equals("") && !email.equals("") && !poste.equals("") && salaire >= 0) {
            try {
                Connection cnx = SharedConnection.createConnection();
                Statement stmt = cnx.createStatement();
                stmt.executeUpdate("UPDATE employee SET nom='" +nom+ "', prenom ='" +prenom+ "' , numeroTelephone ='" +telephone+ "' ,email='" +email+ "', poste ='" +poste+ "', salaire ='" +salaire+ "' ,absent ='" +pre+ "' WHERE ID ='" + id + "'; ");
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
    public void setTextField(int id, String fname ,String lname, String tele, String email, String poste, String salaire, int abs  ){
        EmId = id;
        EditNom.setText(lname);
        EditPrenom.setText(fname);
        EditTele.setText(tele);
        EditEmail.setText(email);
        EditPoste.setText(poste);
        EditSalaire.setText(salaire);
        if (abs == 1){
            EditAbs.setSelected(true);
        }else {
            EditAbs.setSelected(false);
        }


    }
    void setUpdate(boolean b) {
        this.update = b;

    }
    }
