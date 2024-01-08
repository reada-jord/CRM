package Controller;

import Models.SharedConnection;
import com.example.gestql.Achats;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import Models.Achat;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;


public class AjouterAchatController implements Initializable {


    @FXML
    private TextField Id;

    @FXML
    private TextField AchatNom;
    @FXML
    private TextField ProduitId;
    @FXML
    private TextField Quantite;
    @FXML
    private TextField MontantTotal;




    public void AjouterBtn(MouseEvent mouseEvent) {

        int id = Integer.parseInt(Id.getText());
        String achatNom = AchatNom.getText();
        int produitId = Integer.parseInt(ProduitId.getText());
        int quantite = Integer.parseInt(Quantite.getText());
        int montantTotal = Integer.parseInt(MontantTotal.getText());


        try {
            Connection cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate("INSERT INTO Achat VALUES('"+id+"','"+achatNom+"','"+produitId+"','"+quantite+"','"+montantTotal+"');");
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