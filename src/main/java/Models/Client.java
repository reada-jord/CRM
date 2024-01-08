package Models;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Client {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty prenom = new SimpleStringProperty();

    private final StringProperty adresse = new SimpleStringProperty();
    private final StringProperty numeroTelephone = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();

    private final IntegerProperty etat = new SimpleIntegerProperty();

    public Client(int id, String nom, String prenom, String adresse, String numeroTelephone, String email, int etat) {
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setAdresse(adresse);
        setNumeroTelephone(numeroTelephone);
        setEmail(email);
        setEtat(etat);
    }

    public Client() {
    }

    public final int getId() {
        return id.get();
    }

    public final void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public StringProperty prenomProperty() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }


    public String getAdresse() {
        return adresse.get();
    }

    public StringProperty adresseProperty() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public String getNumeroTelephone() {
        return numeroTelephone.get();
    }

    public StringProperty numeroTelephoneProperty() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone.set(numeroTelephone);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
    public final int getEtat() {
        return etat.get();
    }

    public final void setEtat(int etat) {
        this.etat.set(etat);
    }

    public IntegerProperty etatProperty() {
        return etat;
    }


}
