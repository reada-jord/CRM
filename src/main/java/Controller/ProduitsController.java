package Controller;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

import Models.SharedConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProduitsController {
    @FXML
    private AreaChart<String, Number> produitschart;
    @FXML
    private Label nomProduit1;
    @FXML
    private Label prixProduit1;
    @FXML
    private Label nomProduit2;
    @FXML
    private Label prixProduit2;
    @FXML
    private Label nomProduit3;
    @FXML
    private Label prixProduit3;

    public void initialize() {
        try {
            Connection conn = SharedConnection.createConnection();

            String query = "SELECT nom, prix FROM produit LIMIT 3"; // Limite à 3 produits ici
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            int i = 1;
            while (rs.next() && i <= 3) {
                switch (i) {
                    case 1:
                        nomProduit1.setText(rs.getString("nom"));
                        prixProduit1.setText(rs.getString("prix"));
                        break;
                    case 2:
                        nomProduit2.setText(rs.getString("nom"));
                        prixProduit2.setText(rs.getString("prix"));
                        break;
                    case 3:
                        nomProduit3.setText(rs.getString("nom"));
                        prixProduit3.setText(rs.getString("prix"));
                        break;
                    default:
                        break;
                }
                i++;
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        genererGraphique();

    }

    private void genererGraphique() {
        // Initialisez les axes et le graphique
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        produitschart.setTitle("Production");
        produitschart.setLegendVisible(false);
        produitschart.setCreateSymbols(false);
        produitschart.setHorizontalGridLinesVisible(true);
        xAxis.setLabel("Produit");
        yAxis.setLabel("Stock");

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        try {
            Connection conn = SharedConnection.createConnection();

            String query = "SELECT nom, stock FROM produit";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String nomProduit = rs.getString("nom");
                double stockProduit = rs.getDouble("stock");
                series.getData().add(new XYChart.Data<>(nomProduit, stockProduit));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        produitschart.getData().add(series);
    }

    @FXML
    private void modifierPrixProduit1() {
        afficherDialogueModificationPrix(nomProduit1.getText(), prixProduit1);
    }

    @FXML
    private void modifierPrixProduit2() {
        afficherDialogueModificationPrix(nomProduit2.getText(), prixProduit2);
    }

    @FXML
    private void modifierPrixProduit3() {
        afficherDialogueModificationPrix(nomProduit3.getText(), prixProduit3);
    }

    private void afficherDialogueModificationPrix(String nomProduit, Label labelPrix) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modifier le prix");
        dialog.setHeaderText("Nouveau prix pour " + nomProduit);
        dialog.setContentText("Nouveau prix:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newPrice -> {
            // Mettre à jour la base de données avec le nouveau prix
            mettreAJourPrixDansDB(nomProduit, newPrice);

            // Mettre à jour l'affichage dans la page
            labelPrix.setText(newPrice);
        });
    }

    private void mettreAJourPrixDansDB(String nomProduit, String nouveauPrix) {
        try {
            Connection conn = SharedConnection.createConnection();

            String query = "UPDATE produit SET prix = ? WHERE nom = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nouveauPrix);
            statement.setString(2, nomProduit);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Prix mis à jour pour " + nomProduit + " : " + nouveauPrix);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
