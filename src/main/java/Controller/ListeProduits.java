package Controller;

import Models.Employe;
import com.example.gestql.AjouterProduit;
import com.example.gestql.ModifierProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Models.Produit;
import Models.SharedConnection;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ListeProduits implements Initializable {

    @FXML
    protected TableView<Produit> table;

    @FXML
    private TableColumn<Produit, Integer> idCol;

    @FXML
    private TableColumn<Produit, String> nomCol;

    @FXML
    private TableColumn<Produit, String> descriptionCol;

    @FXML
    private TableColumn<Produit, Double> prixCol;

    @FXML
    private TableColumn<Produit, Integer> stockCol;

    @FXML
    private TextField search;

    public void find(MouseEvent mouseEvent) {
        String searchTerm = search.getText().toString();
        ObservableList<Produit> data = FXCollections.observableArrayList();

        try {
            Connection cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produit WHERE id LIKE '" + searchTerm + "%' OR nom LIKE '" + searchTerm + "%';");

            while (rs.next()) {
                data.add(new Produit(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getInt("stock")));
            }

            // Assurez-vous de lier les résultats à votre TableView
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
            stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

            table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            table.setItems(data);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void AjouterButton(MouseEvent mouseEvent) throws IOException {
        AjouterProduit ajCl = new AjouterProduit();
        ajCl.start(new Stage());
    }

    @FXML
    private void exportAction(MouseEvent mouseEvent) {
        TableView<Produit> table = null ;
        export(mouseEvent);
    }
 public void export(MouseEvent mouseEvent) {
     try {
         // Create a new workbook
         Workbook workbook = new XSSFWorkbook();

         // Create a sheet in the workbook
         Sheet sheet = workbook.createSheet("Produit Data");

         // Create header row
         Row headerRow = sheet.createRow(0);
         headerRow.createCell(0).setCellValue("ID");
         headerRow.createCell(1).setCellValue("Nom");
         headerRow.createCell(2).setCellValue("description");
         headerRow.createCell(3).setCellValue("prix");
         headerRow.createCell(4).setCellValue("stock");

         // Populate data rows
         ObservableList<Produit> data = (ObservableList<Produit>) table.getItems();
         for (int i = 0; i < data.size(); i++) {
             Produit produit = data.get(i);
             Row dataRow = sheet.createRow(i + 1);
             dataRow.createCell(0).setCellValue(produit.getId());
             dataRow.createCell(1).setCellValue(produit.getNom());
             dataRow.createCell(2).setCellValue(produit.getDescription());
             dataRow.createCell(3).setCellValue(produit.getPrix());
             dataRow.createCell(4).setCellValue(produit.getStock());
             }

         // Save the workbook to a file
         try (FileOutputStream fileOut = new FileOutputStream("ProduitsData.xlsx")) {
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

    public void ModifierButton(MouseEvent mouseEvent) throws IOException {
        ModifierProduit MoEm = new ModifierProduit();
        MoEm.start(new Stage());
    }


    public void deleteButton(MouseEvent mouseEvent) {
        Produit produit = table.getSelectionModel().getSelectedItem();
        int id = produit.idProperty().getValue();

        try {
            Connection cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();

            stmt.executeUpdate("DELETE FROM stock WHERE produit_id ='" + id + "'; ");

            stmt.executeUpdate("DELETE FROM Produit WHERE id ='" + id + "'; ");

            int selected = table.getSelectionModel().getSelectedIndex();
            table.getItems().remove(selected);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Produit> data = FXCollections.observableArrayList();
        Connection cnx = null;
        try {
            cnx = SharedConnection.createConnection();
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produit;");

            while (rs.next()) {
                data.add(new Produit(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getInt("stock")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(data);
    }
}
