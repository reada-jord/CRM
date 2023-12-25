package Models;

import javafx.beans.property.*;

public class Employe {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty prenom = new SimpleStringProperty();
    private final StringProperty numeroTelephone = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty poste = new SimpleStringProperty();
    private final DoubleProperty salaire = new SimpleDoubleProperty();
    private final IntegerProperty absent = new SimpleIntegerProperty();

    public Employe(int id, String nom, String prenom, String numeroTelephone, String email, String poste, double salaire, int absent) {
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setNumeroTelephone(numeroTelephone);
        setEmail(email);
        setPoste(poste);
        setSalaire(salaire);
        setAbsent(absent);
    }

    public Employe() {
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

    public String getPoste() {
        return poste.get();
    }

    public StringProperty posteProperty() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste.set(poste);
    }

    public double getSalaire() {
        return salaire.get();
    }

    public DoubleProperty salaireProperty() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire.set(salaire);
    }

    public int getAbsent() {
        return absent.get();
    }

    public IntegerProperty absentProperty() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent.set(absent);
    }
}
