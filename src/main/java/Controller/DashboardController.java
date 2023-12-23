package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Label;


import java.net.URL;
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
    private Label totalVaches;
    @FXML
    private Label totalLitres;
    @FXML
    private Label vachesToVacc;
    @FXML
    private Label absentEmp;
    @FXML
    private Label noMachines;

    @FXML
    private Label avgThisMonth;

    @FXML
    private Label avgLastMonth;

    @FXML
    private Label avgAlways;

    SimpleDateFormat formatter = new SimpleDateFormat("E dd MMMM yyyy",  Locale.FRANCE);
    SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd",  Locale.FRANCE);
    SimpleDateFormat monthAndDay = new SimpleDateFormat("MMM dd", Locale.FRANCE);
    Date curDate = new Date();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        date.setText(formatter.format(curDate).toUpperCase());

    }

    public String countEmployes(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM employes;");
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
}
