package Controller;

import Models.SharedConnection;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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

            setChartBar(stmt, "Portable", "Phone");
            setChartBar(stmt, "Desktop", "Computer");
            setChartBar(stmt, "Gaming", "Gaming Laptop");

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Portable", 37),
                            new PieChart.Data("Desktop", 33),
                            new PieChart.Data("Gaming", 30));

            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(
                                    data.getName(), " : ", data.pieValueProperty(), "%"
                            )
                    )
            );

            pieChart.getData().addAll(pieChartData);


            totalEmployes.setText(countEmployes(stmt));
            totalClient.setText(countClients(stmt));
            totalProduit.setText(countProduits(stmt));
            ContactDisp.setText(countContact(stmt));
            absentEmp.setText(countEmployeAbs(stmt));
            noComm.setText(countCommande(stmt));


            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }



    public void setChartBar(Statement stmt, String prodName, String prodCode) throws SQLException, ParseException {

        XYChart.Series series = new XYChart.Series<>();
        series.setName(prodName);

        ResultSet rs = stmt.executeQuery("SELECT stock, date_production FROM produit WHERE nom = '" + prodCode + "' ORDER BY date_production ASC;");
        while (rs.next()) {
            Date pro = simpleFormatter.parse(rs.getString("date_production"));
            Double stock = rs.getDouble("stock");
            series.getData().add(new XYChart.Data<>(monthAndDay.format(pro).toUpperCase(), stock));
        }

        sb.getData().add(series);

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
