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


import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;


public class AjouterCommandeController implements Initializable {


    @FXML
    private TextField Id;

    @FXML
    private TextField ClientId;
    @FXML
    private TextField DateCommande;
    @FXML
    private TextField Status;
    @FXML
    private TextField MontantTotal;




    public void AjouterBtn(MouseEvent mouseEvent) {

        int id = Integer.parseInt(Id.getText());
        int clientid = Integer.parseInt(ClientId.getText());
        String dateCommande = DateCommande.getText();
        String status = Status.getText();
        int montantTotal = Integer.parseInt(MontantTotal.getText());


        try {
            Connection cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate("INSERT INTO Commande VALUES('"+id+"','"+clientid+"','"+dateCommande+"','"+status+"','"+montantTotal+"');");
            Node source = (Node) mouseEvent.getSource();
            Stage ajtEmp = (Stage) source.getScene().getWindow();
            ajtEmp.close();
        }catch (Exception e){
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