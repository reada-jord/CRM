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


import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;


public class AjouterClientController implements Initializable {


    @FXML
    private TextField Id;
    @FXML
    private TextField Nom;

    @FXML
    private TextField Prenom;

    @FXML
    private TextField Adresse;

    @FXML
    private TextField Telephone;

    @FXML
    private TextField Email;

    @FXML
    public ChoiceBox<Integer> Etat;


    public void AjouterBtn(MouseEvent mouseEvent) {

        int id = Integer.parseInt(Id.getText());
        String nom = Nom.getText();
        String prenom = Prenom.getText();
        String adresse = Adresse.getText();
        String telephone = Telephone.getText();
        String email = Email.getText();
        int et = Etat.getValue();
        int etat = getEtat(et);
        System.out.print(etat);


        try {
            Connection cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate("INSERT INTO client VALUES('"+id+"','"+nom+"','"+prenom+"','"+telephone+"','"+adresse+"','"+email+"','"+etat+"')");
            Node source = (Node) mouseEvent.getSource();
            Stage ajtEmp = (Stage) source.getScene().getWindow();
            ajtEmp.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public int getEtat(int etat) {
        switch (etat) {
            case 0:
                etat = 0;
                break;
            case 1:
                etat = 1;
                break;
        }
        return etat;
    }

    public void Ajouter(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Etat.getItems().addAll(0,1);
    }


}