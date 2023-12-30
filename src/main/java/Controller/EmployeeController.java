package Controller;


import com.example.gestql.AjouterEmployes;
import com.example.gestql.ModifierEmploye;
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

    @FXML
    private TextField search;
    public void find(MouseEvent mouseEvent) {
        String id = search.getText().toString();
        date.setText(formatter.format(curDate).toUpperCase());
        ObservableList<Employe> data = FXCollections.observableArrayList();
        Connection cnx = null;
        for(int i=0; i<table.getItems().size();i++){
            table.getItems().clear();
        }
        try {
            cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee WHERE ID LIKE '"+id+"%' OR nom LIKE '"+id+"%' OR prenom LIKE '"+id+"%';");

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





    // ****************************************************************************
    public void AjouterButton(MouseEvent mouseEvent) throws IOException {
        AjouterEmployes ajEm = new AjouterEmployes();
        ajEm.start(new Stage());
    }

    // ****************************************************************************
    public void export(MouseEvent mouseEvent) {
        try {
            // Create a new workbook
            Workbook workbook = new XSSFWorkbook();

            // Create a sheet in the workbook
            Sheet sheet = workbook.createSheet("Employee Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nom");
            headerRow.createCell(2).setCellValue("Prenom");
            headerRow.createCell(3).setCellValue("NumeroTelephone");
            headerRow.createCell(4).setCellValue("Email");
            headerRow.createCell(5).setCellValue("Poste");
            headerRow.createCell(6).setCellValue("Salaire");
            headerRow.createCell(7).setCellValue("Absent");

            // Populate data rows
            ObservableList<Employe> data = (ObservableList<Employe>) table.getItems();
            for (int i = 0; i < data.size(); i++) {
                Employe employee = data.get(i);
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(employee.getId());
                dataRow.createCell(1).setCellValue(employee.getNom());
                dataRow.createCell(2).setCellValue(employee.getPrenom());
                dataRow.createCell(3).setCellValue(employee.getNumeroTelephone());
                dataRow.createCell(4).setCellValue(employee.getEmail());
                dataRow.createCell(5).setCellValue(employee.getPoste());
                dataRow.createCell(6).setCellValue(employee.getSalaire());
                dataRow.createCell(7).setCellValue(employee.getAbsent());
            }

            // Save the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream("EmployeeData.xlsx")) {
                workbook.write(fileOut);
            }

            // Close the workbook
            workbook.close();

            // Provide feedback to the user (optional)
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Successful");
            alert.setHeaderText(null);
            alert.setContentText("Employee data exported to Excel successfully!");
            alert.showAndWait();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

    // ****************************************************************************

    public void ModifierButton(MouseEvent mouseEvent) throws IOException {
        ModifierEmploye MoEm = new ModifierEmploye();
        MoEm.start(new Stage());
    }

    // *************************************************************************
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
