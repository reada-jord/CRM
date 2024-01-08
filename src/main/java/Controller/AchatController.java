package Controller;

import com.example.gestql.AjouterAchats;
import com.example.gestql.ModifierAchat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Models.Achat;
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


public class AchatController implements Initializable {

    @FXML
    protected TableView table;

    @FXML
    private TableColumn<Achat, Integer> IdCol;

    @FXML
    private TableColumn<Achat, String> AchatNomCol;

    @FXML
    private TableColumn<Achat, Integer> ProduitIdCol;

    @FXML
    private TableColumn<Achat, Integer> QuantiteCol;

    @FXML
    private TableColumn<Achat, Integer> MontantTotalCol;



    @FXML
    private TextField search;
    public void find(MouseEvent mouseEvent) {
        String id = search.getText().toString();
        date.setText(formatter.format(curDate).toUpperCase());
        ObservableList<Achat> data = FXCollections.observableArrayList();
        Connection cnx = null;
        for(int i=0; i<table.getItems().size();i++){
            table.getItems().clear();
        }
        try {
            cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Achat WHERE ID LIKE '"+id+"%' OR achat_nom LIKE '"+id+"%' OR produit_id LIKE '"+id+"%';");

            while (rs.next()) {
                data.add(new Achat(
                        rs.getInt("Id"),
                        rs.getString("Achat_Nom"),
                        rs.getInt("Produit_Id"),
                        rs.getInt("Quantite"),
                        rs.getInt("MontantTotal")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        IdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        AchatNomCol.setCellValueFactory(new PropertyValueFactory<>("AchatNom"));
        ProduitIdCol.setCellValueFactory(new PropertyValueFactory<>("Produit"));
        QuantiteCol.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        MontantTotalCol.setCellValueFactory(new PropertyValueFactory<>("MontantTotal"));


        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(data);
    }





    // ****************************************************************************
    public void AjouterButton(MouseEvent mouseEvent) throws IOException {
        AjouterAchats ajCl = new AjouterAchats();
        ajCl.start(new Stage());
    }

    // ****************************************************************************
    public void export(MouseEvent mouseEvent) {
        try {
            // Create a new workbook
            Workbook workbook = new XSSFWorkbook();

            // Create a sheet in the workbook
            Sheet sheet = workbook.createSheet("Achat Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("AchatNom");
            headerRow.createCell(2).setCellValue("Produit");
            headerRow.createCell(3).setCellValue("Quantite");
            headerRow.createCell(3).setCellValue("MontantTotal");


            // Populate data rows
            ObservableList<Achat> data = (ObservableList<Achat>) table.getItems();
            for (int i = 0; i < data.size(); i++) {
                Achat Achat = data.get(i);
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(Achat.getId());
                dataRow.createCell(1).setCellValue(Achat.getAchatNom());
                dataRow.createCell(2).setCellValue(Achat.getProduit());
                dataRow.createCell(3).setCellValue(Achat.getQuantite());
                dataRow.createCell(4).setCellValue(Achat.getMontantTotal());

            }

            // Save the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream("AchatData.xlsx")) {
                workbook.write(fileOut);
            }

            // Close the workbook
            workbook.close();

            // Provide feedback to the user (optional)
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Successful");
            alert.setHeaderText(null);
            alert.setContentText("Achat data exported to Excel successfully!");
            alert.showAndWait();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

    // ****************************************************************************

    public void ModifierButton(MouseEvent mouseEvent) throws IOException {
        ModifierAchat MoEm = new ModifierAchat();
        MoEm.start(new Stage());
    }

    // *************************************************************************
    public void deleteButton(MouseEvent mouseEvent) {
        Achat em = (Achat) table.getSelectionModel().getSelectedItem();
        int id = em.idProperty().getValue();
        try {
            Connection cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate("DELETE FROM Achat WHERE id ='"+id+"'; ");
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
        ObservableList<Achat> data = FXCollections.observableArrayList();
        Connection cnx = null;
        try {
            cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM achat;");

            while (rs.next()) {
                data.add(new Achat(
                        rs.getInt("Id"),
                        rs.getString("achat_nom"),
                        rs.getInt("Produit_id"),
                        rs.getInt("Quantite"),
                        rs.getInt("MontantTotal")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        IdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        AchatNomCol.setCellValueFactory(new PropertyValueFactory<>("achatNom"));
        ProduitIdCol.setCellValueFactory(new PropertyValueFactory<>("Produit"));
        QuantiteCol.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        MontantTotalCol.setCellValueFactory(new PropertyValueFactory<>("MontantTotal"));


        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(data);
    }
}