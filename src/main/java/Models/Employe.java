package Models;

public class Employe {
    private String nom;
    private String prenom;
    private String adresse;
    private String numeroTelephone;
    private String email;
    private String poste;
    private double salaire;

    public Employe(String nom, String prenom, String adresse, String numeroTelephone, String email, String poste, double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numeroTelephone = numeroTelephone;
        this.email = email;
        this.poste = poste;
        this.salaire = salaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }


    public void attribuerPoste(String nouveauPoste) {
        this.poste = nouveauPoste;
    }

    public void ajusterSalaire(double nouveauSalaire) {
        this.salaire = nouveauSalaire;
    }
}
