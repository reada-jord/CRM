package Models;

import javafx.beans.property.*;

public class Achat {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty achatNom = new SimpleStringProperty();
    private final IntegerProperty produit = new SimpleIntegerProperty();
    private final IntegerProperty quantite = new SimpleIntegerProperty();
    private final DoubleProperty montantTotal = new SimpleDoubleProperty();

    public Achat(int id, String achatNom, int produit, int quantite, double montantTotal) {
        this.id.set(id);
        this.achatNom.set(achatNom);
        this.produit.set(produit);
        this.quantite.set(quantite);
        this.montantTotal.set(montantTotal);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty achatNomProperty() {
        return achatNom;
    }

    public IntegerProperty produitProperty() {
        return produit;
    }

    public IntegerProperty quantiteProperty() {
        return quantite;
    }

    public DoubleProperty montantTotalProperty() {
        return montantTotal;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getAchatNom() {
        return achatNom.get();
    }

    public void setAchatNom(String achatNom) {
        this.achatNom.set(achatNom);
    }

    public int getProduit() {
        return produit.get();
    }

    public void setProduit(int produit) {
        this.produit.set(produit);
    }

    public int getQuantite() {
        return quantite.get();
    }

    public void setQuantite(int quantite) {
        this.quantite.set(quantite);
    }

    public double getMontantTotal() {
        return montantTotal.get();
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal.set(montantTotal);
    }
}
