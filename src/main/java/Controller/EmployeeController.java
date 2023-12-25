package Controller;


import com.example.gestql.AjouterEmployes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Models.Employe;
import Models.SharedConnection;


import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;


public class EmployeeController implements Initializable {

    @FXML
    protected TableView table;

    @FXML
    private TableColumn<Employe, Integer> IdCol;

    @FXML
    private TableColumn<Employe, String> NomCol;

    @FXML
    private TableColumn<Employe, String> PrenomCol;

    @FXML
    private TableColumn<Employe, String> TelephoneCol;

    @FXML
    private TableColumn<Employe, String> EmailCol;

    @FXML
    private TableColumn<Employe, String> PosteCol;
    @FXML
    private TableColumn<Employe, Double> SalaireCol;
    @FXML
    private TableColumn<Employe, Integer> AbsentCol;


    public void find(MouseEvent mouseEvent) {
    }

    public void AjouterButton(MouseEvent mouseEvent) throws IOException {
        AjouterEmployes ajEm = new AjouterEmployes();
        ajEm.start(new Stage());
    }

    public void export(MouseEvent mouseEvent) {
    }

    public void ModifierButton(MouseEvent mouseEvent) {
    }

    public void deleteButton(MouseEvent mouseEvent) {
        Employe em = (Employe) table.getSelectionModel().getSelectedItem();
        int id = em.idProperty().getValue();
        try {
            Connection cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate("DELETE FROM employee WHERE id ='"+id+"'; ");
        }catch (Exception e){
            e.printStackTrace();
        }
        int selected = table.getSelectionModel().getSelectedIndex();
        table.getItems().remove(selected);
    }

    // ********************************************************************************************
    @FXML
    private Label date;
    SimpleDateFormat formatter = new SimpleDateFormat("E dd MMMM yyyy",  Locale.FRANCE);
    Date curDate = new Date();

    // *********************************************************************************************

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        date.setText(formatter.format(curDate).toUpperCase());
        ObservableList<Employe> data = FXCollections.observableArrayList();
        Connection cnx = null;
        try {
            cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee;");

            while (rs.next()) {
                data.add(new Employe(
                        rs.getInt("Id"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("NumeroTelephone"),
                        rs.getString("Email"),
                        rs.getString("Poste"),
                        rs.getDouble("Salaire"),
                        rs.getInt("Absent")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        IdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        PrenomCol.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        TelephoneCol.setCellValueFactory(new PropertyValueFactory<>("NumeroTelephone"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        PosteCol.setCellValueFactory(new PropertyValueFactory<>("Poste"));
        SalaireCol.setCellValueFactory(new PropertyValueFactory<>("Salaire"));
        AbsentCol.setCellValueFactory(new PropertyValueFactory<>("Absent"));

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(data);
    }
}
