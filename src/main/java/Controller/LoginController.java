package Controller;

import com.example.gestql.Index;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import Models.SharedConnection;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;


public class LoginController {

    // Strings which hold css elements to easily re-use in the application
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");

    // Import the application's controls

    @FXML
    private Button cancelButton;
    @FXML
    private TextField loginUsernameTextField;
    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private Button LoginButton;


    // Creation of methods which are activated on events in the forms
    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent E) {

            Window owner = LoginButton.getScene().getWindow();

            if (loginUsernameTextField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Erreur!",
                        "Veuillez insérer le nom d'utilisateur!");
                return;
            }



            String userNameT = loginUsernameTextField.getText();
            String passwordT = loginPasswordField.getText();

            SharedConnection sharedConnection = new SharedConnection(userNameT, passwordT);

            try {
                sharedConnection.createConnection();
                Index in = new Index();
                in.start(new Stage());
                Node source = (Node) E.getSource();
                Stage login  = (Stage) source.getScene().getWindow();
                login.close();
            } catch(SQLException | IOException Ex){
                System.out.println(Ex.getMessage());
                showAlert(Alert.AlertType.ERROR, owner, "ERREUR!", "LES DONNEES SONT INCORRECTES!");
            }
        }

        private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(owner);
            alert.show();
        }

    }


