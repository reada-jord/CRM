package Models;

public class Achat {

        private Produit produit;
        private int quantite;
        private double montantTotal;

    public Achat(Produit produit, int quantite, double montantTotal) {
        this.produit = produit;
        this.quantite = quantite;
        this.montantTotal = montantTotal;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

}
