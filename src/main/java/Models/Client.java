package Models;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Client {

    public StringProperty cin = new SimpleStringProperty();
    public StringProperty nom = new SimpleStringProperty();
    public StringProperty password = new SimpleStringProperty();


    public StringProperty cinProperty() {
        return cin;
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public Client(String cin, String nom, String password) {
        this.cin.set(cin);
        this.nom.set(nom);
        this.password.set(password);
    }

    public Client() {
    }

}
