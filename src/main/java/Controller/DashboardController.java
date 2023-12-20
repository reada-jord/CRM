package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private Button logout;

    @FXML
    private void initialize() {
    }
    public void onlogout(){
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }

}
