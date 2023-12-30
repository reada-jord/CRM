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


import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;


public class AjouterEmployeController implements Initializable {


    @FXML
    private TextField Id;
    @FXML
    private TextField Nom;

    @FXML
    private TextField Prenom;

    @FXML
    private TextField Telephone;

    @FXML
    private TextField Email;
    @FXML
    private TextField Poste;
    @FXML
    private TextField Salaire;

    @FXML
    public ChoiceBox<Integer> Abs;


    public void AjouterBtn(MouseEvent mouseEvent) {

        int id = Integer.parseInt(Id.getText());
        String nom = Nom.getText();
        String prenom = Prenom.getText();
        String telephone = Telephone.getText();
        String email = Email.getText();
        String poste = Poste.getText();
        double salaire = Double.parseDouble(Salaire.getText());
        int abs = Abs.getValue();
        int absent = getAbs(abs);
        System.out.print(absent);


        try {
            Connection cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate("INSERT INTO employee VALUES('"+id+"','"+nom+"','"+prenom+"','"+telephone+"','"+email+"','"+poste+"','"+salaire+"','"+absent+"')");
            Node source = (Node) mouseEvent.getSource();
            Stage ajtEmp = (Stage) source.getScene().getWindow();
            ajtEmp.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public int getAbs(int absent) {
        switch (absent) {
            case 0:
                absent = 0;
                break;
            case 1:
                absent = 1;
                break;
        }
        return absent;
    }

    public void Ajouter(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Abs.getItems().addAll(0,1);
    }


}
