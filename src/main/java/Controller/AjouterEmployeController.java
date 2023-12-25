package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AjouterEmployeController implements Initializable {

    @FXML
    private TextField Nom;

    @FXML
    private TextField Prenom;

    @FXML
    private TextField Telephone;

    @FXML
    private ChoiceBox<Integer>  depa;

    @FXML
    private ChoiceBox<Integer> Type;
    public void AjouterBtn(MouseEvent mouseEvent) {


    }

    public void Ajouter(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
