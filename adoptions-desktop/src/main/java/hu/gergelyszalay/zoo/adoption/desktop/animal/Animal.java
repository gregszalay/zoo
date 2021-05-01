package hu.gergelyszalay.zoo.adoption.desktop.animal;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Animal {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private final StringProperty species = new SimpleStringProperty(this, "species");
    private final StringProperty introduction = new SimpleStringProperty(this, "introduction");
    private final IntegerProperty birthYear = new SimpleIntegerProperty(this, "birthYear");

    public Animal() {
        id.set(-1);
        name.set("Kérjük, adja meg az állat becenevét!");
        species.set("Kérjük, adja meg az állat fajtáját!");
        introduction.set("Kérjük, adja meg a példány rövid ismertetését!");
        birthYear.set(0);
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

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getSpecies() {
        return species.get();
    }

    public void setSpecies(String species) {
        this.species.set(species);
    }

    public StringProperty speciesProperty() {
        return species;
    }

    public String getIntroduction() {
        return introduction.get();
    }

    public void setIntroduction(String introduction) {
        this.introduction.set(introduction);
    }

    public StringProperty introductionProperty() {
        return introduction;
    }

    public int getBirthYear() {
        return birthYear.get();
    }

    public void setBirthYear(int birthYear) {
        this.birthYear.set(birthYear);
    }

    public IntegerProperty birthYearProperty() {
        return birthYear;
    }
}
