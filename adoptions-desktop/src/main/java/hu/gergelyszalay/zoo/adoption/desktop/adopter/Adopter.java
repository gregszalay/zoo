package hu.gergelyszalay.zoo.adoption.desktop.adopter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Adopter {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName");
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName");
    private final StringProperty email = new SimpleStringProperty(this, "email");

    public Adopter() {
        id.set(-1);
        lastName.set("Kérjük, adja meg az örökbefogadó vezetéknevét!");
        firstName.set("Kérjük, adja meg az örökbefogadó keresztknevét!");
        email.set("Kérjük, adja meg az örökbefogadó email címét!");
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

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }
}

