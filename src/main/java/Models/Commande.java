package Models;

import java.util.Date;
import java.util.List;

public class Commande {
    private Client client;
    private List<Produit> produits;
    private Date dateCommande;
    private String statut; //
    private double montantTotal;

    public Commande(Client client, List<Produit> produits, Date dateCommande, String statut, double montantTotal) {
        this.client = client;
        this.produits = produits;
        this.dateCommande = dateCommande;
        this.statut = statut;
        this.montantTotal = montantTotal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

}