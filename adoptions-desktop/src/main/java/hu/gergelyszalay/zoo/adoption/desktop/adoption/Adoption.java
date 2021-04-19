package hu.gergelyszalay.zoo.adoption.desktop.adoption;

import javafx.beans.property.*;

import java.io.Serializable;
import java.time.LocalDate;

public class Adoption implements Serializable {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final IntegerProperty animalId = new SimpleIntegerProperty(this, "animalId");
    private final IntegerProperty adopterId = new SimpleIntegerProperty(this, "adopterId");
    private final ObjectProperty<LocalDate> adoptionDate = new SimpleObjectProperty<>(this, "adoptionDate");
    private final StringProperty supportType = new SimpleStringProperty(this, "supportType");
    private final IntegerProperty supportAmount = new SimpleIntegerProperty(this, "supportAmount");

    public Adoption() {
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getAnimalId() {
        return animalId.get();
    }

    public IntegerProperty animalIdProperty() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId.set(animalId);
    }

    public int getAdopterId() {
        return adopterId.get();
    }

    public IntegerProperty adopterIdProperty() {
        return adopterId;
    }

    public void setAdopterId(int adopterId) {
        this.adopterId.set(adopterId);
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate.get();
    }

    public ObjectProperty<LocalDate> adoptionDateProperty() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate.set(adoptionDate);
    }

    public String getSupportType() {
        return supportType.get();
    }

    public StringProperty supportTypeProperty() {
        return supportType;
    }

    public void setSupportType(String supportType) {
        this.supportType.set(supportType);
    }

    public int getSupportAmount() {
        return supportAmount.get();
    }

    public IntegerProperty supportAmountProperty() {
        return supportAmount;
    }

    public void setSupportAmount(int supportAmount) {
        this.supportAmount.set(supportAmount);
    }

}
