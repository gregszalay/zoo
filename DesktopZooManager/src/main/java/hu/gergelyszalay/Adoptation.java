package hu.gergelyszalay;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
public class Adoptation implements Serializable {

    private StringProperty adopter = new SimpleStringProperty(this, "adopter");
    private StringProperty animal = new SimpleStringProperty(this, "animal");
    private StringProperty adoptionDate = new SimpleStringProperty(this, "adoptionDate");
    private StringProperty support = new SimpleStringProperty(this, "support");

    public Adoptation(String adopter, String animal, String adoptionDate, String support) {
        this.adopter.setValue(adopter);
        this.animal.setValue(animal);
        this.adoptionDate.setValue(adoptionDate);
        this.support.setValue(support);
    }

    public String getAdopter() {
        return adopter.get();
    }

    public StringProperty adopterProperty() {
        return adopter;
    }

    public void setAdopter(String adopter) {
        this.adopter.set(adopter);
    }

    public String getAnimal() {
        return animal.get();
    }

    public StringProperty animalProperty() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal.set(animal);
    }

    public String getAdoptionDate() {
        return adoptionDate.get();
    }

    public StringProperty adoptionDateProperty() {
        return adoptionDate;
    }

    public void setAdoptionDate(String adoptionDate) {
        this.adoptionDate.set(adoptionDate);
    }

    public String getSupport() {
        return support.get();
    }

    public StringProperty supportProperty() {
        return support;
    }

    public void setSupport(String support) {
        this.support.set(support);
    }
}
