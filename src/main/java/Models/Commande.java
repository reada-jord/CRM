package Models;

import javafx.beans.property.*;

import java.util.Date;

public class Commande {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty client = new SimpleIntegerProperty();

    private final ObjectProperty<Date> dateCommande = new SimpleObjectProperty<>();
    private final StringProperty statut = new SimpleStringProperty();
    private final IntegerProperty montantTotal = new SimpleIntegerProperty();


    public Commande() {
        // Default constructor
    }


    public Commande(int id, int client, Date dateCommande, String statut, int montantTotal) {
        setId(id);
        setClient(client);

        setDateCommande(dateCommande);
        setStatut(statut);
        setMontantTotal(montantTotal);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getClient() {
        return client.get();
    }

    public void setClient(int client) {
        this.client.set(client);
    }

    public IntegerProperty clientProperty() {
        return client;
    }


    public Date getDateCommande() {
        return dateCommande.get();
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande.set(dateCommande);
    }

    public ObjectProperty<Date> dateCommandeProperty() {
        return dateCommande;
    }

    public String getStatut() {
        return statut.get();
    }

    public void setStatut(String statut) {
        this.statut.set(statut);
    }

    public StringProperty statutProperty() {
        return statut;
    }

    public double getMontantTotal() {
        return montantTotal.get();
    }

    public void setMontantTotal(int montantTotal) {
        this.montantTotal.set(montantTotal);
    }

    public IntegerProperty montantTotalProperty() {
        return montantTotal;
    }
}
