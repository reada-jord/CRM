package Controller;

import Models.SharedConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Label;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label date;
    @FXML
    private PieChart pieChart;
    @FXML
    private StackedBarChart<String, Number> sb;
    @FXML
    private Label totalEmployes;
    @FXML
    private Label totalClient;
    @FXML
    private Label totalProduit;
    @FXML
    private Label ContactDisp;
    @FXML
    private Label absentEmp;
    @FXML
    private Label noComm;



    SimpleDateFormat formatter = new SimpleDateFormat("E dd MMMM yyyy",  Locale.FRANCE);
    SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd",  Locale.FRANCE);
    SimpleDateFormat monthAndDay = new SimpleDateFormat("MMM dd", Locale.FRANCE);
    Date curDate = new Date();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        date.setText(formatter.format(curDate).toUpperCase());
        try {
            Connection conn = SharedConnection.createConnection();
            Statement stmt = conn.createStatement();

            totalEmployes.setText(countEmployes(stmt));
            totalClient.setText(countClients(stmt));
            totalProduit.setText(countProduits(stmt));
            ContactDisp.setText(countContact(stmt));
            absentEmp.setText(countEmployeAbs(stmt));
            noComm.setText(countCommande(stmt));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




    public String countEmployes(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM employee;");
        rs.next();
        return Integer.toString(rs.getInt("total"));
    }

    public String countClients(Statement stmt) throws SQLException{
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM client;");
        rs.next();
        return Integer.toString(rs.getInt("total"));
    }

    public String countProduits(Statement stmt) throws SQLException{
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as total FROM produit;");
        rs.next();
        return Integer.toString(rs.getInt("total"));
    }

    public String countContact(Statement stmt) throws SQLException{
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as total FROM client WHERE etat = 1;");
        rs.next();
        return Integer.toString(rs.getInt("total"));
    }
    public String countEmployeAbs(Statement stmt) throws SQLException{
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as total FROM employee WHERE absent = 1;");
        rs.next();
        return Integer.toString(rs.getInt("total"));
    }
    public String countCommande(Statement stmt) throws SQLException{
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as total FROM commande;");
        rs.next();
        return Integer.toString(rs.getInt("total"));
    }
}
