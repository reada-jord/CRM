package Controller;

import com.example.gestql.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
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
        node = FXMLLoader.load(Dashboard.class.getResource("dash.fxml"));
        loadView(node);
    }

    public void loadView(Node node) throws IOException {
        viewToDisplay.getChildren().setAll(node);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            node = FXMLLoader.load(Dashboard.class.getResource("dash.fxml"));
            loadView(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}