package Models;

public class Stock {
    private Produit produit;
    private int quantiteDisponible;
    private int quantiteMinimale;

    public Stock(Produit produit, int quantiteDisponible, int quantiteMinimale) {
        this.produit = produit;
        this.quantiteDisponible = quantiteDisponible;
        this.quantiteMinimale = quantiteMinimale;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

    public int getQuantiteMinimale() {
        return quantiteMinimale;
    }

    public void setQuantiteMinimale(int quantiteMinimale) {
        this.quantiteMinimale = quantiteMinimale;
    }
}
