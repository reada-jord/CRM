package Controller;

import com.example.gestql.AjouterClients;
import com.example.gestql.ModifierClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Models.Client;
import Models.SharedConnection;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


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


public class ClientController implements Initializable {

    @FXML
    protected TableView table;

    @FXML
    private TableColumn<Client, Integer> IdCol;

    @FXML
    private TableColumn<Client, String> NomCol;

    @FXML
    private TableColumn<Client, String> PrenomCol;

    @FXML
    private TableColumn<Client, String> AdresseCol;

    @FXML
    private TableColumn<Client, String> TelephoneCol;

    @FXML
    private TableColumn<Client, String> EmailCol;

    @FXML
    private TableColumn<Client, Integer> EtatCol;

    @FXML
    private TextField search;
    public void find(MouseEvent mouseEvent) {
        String id = search.getText().toString();
        date.setText(formatter.format(curDate).toUpperCase());
        ObservableList<Client> data = FXCollections.observableArrayList();
        Connection cnx = null;
        for(int i=0; i<table.getItems().size();i++){
            table.getItems().clear();
        }
        try {
            cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Client WHERE ID LIKE '"+id+"%' OR nom LIKE '"+id+"%' OR prenom LIKE '"+id+"%';");

            while (rs.next()) {
                data.add(new Client(
                        rs.getInt("Id"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Adresse"),
                        rs.getString("NumeroTelephone"),
                        rs.getString("Email"),
                        rs.getInt("Etat")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        IdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        PrenomCol.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        AdresseCol.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        TelephoneCol.setCellValueFactory(new PropertyValueFactory<>("NumeroTelephone"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        EtatCol.setCellValueFactory(new PropertyValueFactory<>("Etat"));

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(data);
    }





    // ****************************************************************************
    public void AjouterButton(MouseEvent mouseEvent) throws IOException {
        AjouterClients ajCl = new AjouterClients();
        ajCl.start(new Stage());
    }

    // ****************************************************************************
    public void export(MouseEvent mouseEvent) {
        try {
            // Create a new workbook
            Workbook workbook = new XSSFWorkbook();

            // Create a sheet in the workbook
            Sheet sheet = workbook.createSheet("Client Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nom");
            headerRow.createCell(2).setCellValue("Prenom");
            headerRow.createCell(3).setCellValue("Adresse");
            headerRow.createCell(3).setCellValue("NumeroTelephone");
            headerRow.createCell(4).setCellValue("Email");

            headerRow.createCell(7).setCellValue("Etat");

            // Populate data rows
            ObservableList<Client> data = (ObservableList<Client>) table.getItems();
            for (int i = 0; i < data.size(); i++) {
                Client Client = data.get(i);
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(Client.getId());
                dataRow.createCell(1).setCellValue(Client.getNom());
                dataRow.createCell(2).setCellValue(Client.getPrenom());
                dataRow.createCell(3).setCellValue(Client.getAdresse());
                dataRow.createCell(4).setCellValue(Client.getNumeroTelephone());
                dataRow.createCell(5).setCellValue(Client.getEmail());
                dataRow.createCell(6).setCellValue(Client.getEtat());
            }

            // Save the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream("ClientData.xlsx")) {
                workbook.write(fileOut);
            }

            // Close the workbook
            workbook.close();

            // Provide feedback to the user (optional)
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Successful");
            alert.setHeaderText(null);
            alert.setContentText("Client data exported to Excel successfully!");
            alert.showAndWait();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

    // ****************************************************************************

    public void ModifierButton(MouseEvent mouseEvent) throws IOException {
        ModifierClient MoEm = new ModifierClient();
        MoEm.start(new Stage());
    }

    // *************************************************************************
    public void deleteButton(MouseEvent mouseEvent) {
        Client em = (Client) table.getSelectionModel().getSelectedItem();
        int id = em.idProperty().getValue();
        try {
            Connection cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate("DELETE FROM Client WHERE id ='"+id+"'; ");
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
        ObservableList<Client> data = FXCollections.observableArrayList();
        Connection cnx = null;
        try {
            cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM client;");

            while (rs.next()) {
                data.add(new Client(
                        rs.getInt("Id"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Adresse"),
                        rs.getString("NumeroTelephone"),
                        rs.getString("Email"),
                        rs.getInt("Etat")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        IdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        PrenomCol.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        AdresseCol.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        TelephoneCol.setCellValueFactory(new PropertyValueFactory<>("NumeroTelephone"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        EtatCol.setCellValueFactory(new PropertyValueFactory<>("Etat"));

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(data);
    }
}