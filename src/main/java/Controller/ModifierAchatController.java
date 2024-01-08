
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
        import javafx.stage.Window;


        import java.net.URL;
        import java.sql.Connection;
        import java.sql.Statement;
        import java.util.ResourceBundle;
        import java.util.concurrent.ThreadLocalRandom;

public class ModifierAchatController implements Initializable {


    @FXML
    private TextField EditId;

    @FXML
    private TextField EditAchatNom;
    @FXML
    private TextField EditProduitId;
    @FXML
    private TextField EditQuantite;
    @FXML
    private TextField EditMontantTotal;

    
    

    public void EditButton(MouseEvent mouseEvent) {
        int id = Integer.parseInt(EditId.getText());
        String achatNom = EditAchatNom.getText();
        int produitId = Integer.parseInt(EditProduitId.getText());
        int quantite = Integer.parseInt(EditQuantite.getText());
        int montantTotal = Integer.parseInt(EditMontantTotal.getText());


        if (!achatNom.equals("") && !EditProduitId.getText().equals("") && !EditQuantite.getText().equals("") && !EditMontantTotal.getText().equals("") ) {
            try {
                Connection cnx = SharedConnection.createConnection();
                Statement stmt = cnx.createStatement();
                stmt.executeUpdate("UPDATE Achat SET achat_nom='" +achatNom+ "', produit_id ='" +produitId+ "' , quantite ='" +quantite+ "' ,montantTotal='" +montantTotal+ "' WHERE ID ='" + id + "'; ");
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
    public void setTextField(int id, String achatMame ,int ProduitId,int quantite, int montantTotal){
        EmId = id;
        EditAchatNom.setText(achatMame);
        EditProduitId.setText(String.valueOf(ProduitId));
        EditQuantite.setText(String.valueOf(quantite));
        EditMontantTotal.setText(String.valueOf(montantTotal));

    }
    void setUpdate(boolean b) {
        this.update = b;

    }
}
