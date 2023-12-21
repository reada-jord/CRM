package Controller;

import Models.*;
import com.example.gestql.Dashboard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    Node node = null;
    @FXML
    public AnchorPane viewToDisplay;

    @FXML
    public void setDashboard(MouseEvent E) throws IOException {
        node = FXMLLoader.load(Dashboard.class.getResource("Dashboard.fxml"));
        loadView(node);
    }

    public void loadView(Node node) throws IOException {
        viewToDisplay.getChildren().setAll(node);
    }
    @FXML
    public void setEmployes(MouseEvent E) throws IOException {
        node = FXMLLoader.load(Employes.class.getResource("Employes.fxml"));
        loadView(node);
    }

    @FXML
    private void setClient(MouseEvent E) throws IOException {
        node = FXMLLoader.load(Client.class.getResource("Departements.fxml"));
        loadView(node);
    }

    @FXML
    private void setAchats(MouseEvent E) throws IOException {
        node = FXMLLoader.load(Achats.class.getResource("Vehicules.fxml"));
        loadView(node);
    }

    @FXML
    private void setProduits(MouseEvent E) throws IOException {
        node = FXMLLoader.load(Produits.class.getResource("Machines.fxml"));
        loadView(node);
    }

    @FXML
    private void setCommandes(MouseEvent E) throws IOException {
        node = FXMLLoader.load(Commandes.class.getResource("Vaches.fxml"));
        loadView(node);
    }

    @FXML
    private void setStock(MouseEvent E) throws IOException {
        node = FXMLLoader.load(Stock.class.getResource("Produits.fxml"));
        loadView(node);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            node = FXMLLoader.load(Dashboard.class.getResource("Dashboard.fxml"));
            loadView(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public Button closeButton;



    @FXML
    public void closeStage(MouseEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}