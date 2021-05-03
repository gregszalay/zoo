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
    private final StringProperty supportFrequency = new SimpleStringProperty(this, "supportFrequency");

    public Adoption() {
        id.set(-1);
        animalId.set(-1);
        adopterId.set(-1);
        adoptionDate.set(LocalDate.now());
        supportType.set("Nincs");
        supportAmount.set(0);
        supportFrequency.set("Nincs");
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

    public int getAnimalId() {
        return animalId.get();
    }

    public String getSupportFrequency() {
        return supportFrequency.get();
    }

    public StringProperty supportFrequencyProperty() {
        return supportFrequency;
    }

    public void setSupportFrequency(String supportFrequency) {
        this.supportFrequency.set(supportFrequency);
    }

    public void setAnimalId(int animalId) {
        this.animalId.set(animalId);
    }

    public IntegerProperty animalIdProperty() {
        return animalId;
    }

    public int getAdopterId() {
        return adopterId.get();
    }

    public void setAdopterId(int adopterId) {
        this.adopterId.set(adopterId);
    }

    public IntegerProperty adopterIdProperty() {
        return adopterId;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate.get();
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate.set(adoptionDate);
    }

    public ObjectProperty<LocalDate> adoptionDateProperty() {
        return adoptionDate;
    }

    public String getSupportType() {
        return supportType.get();
    }

    public void setSupportType(String supportType) {
        this.supportType.set(supportType);
    }

    public StringProperty supportTypeProperty() {
        return supportType;
    }

    public int getSupportAmount() {
        return supportAmount.get();
    }

    public void setSupportAmount(int supportAmount) {
        this.supportAmount.set(supportAmount);
    }

    public IntegerProperty supportAmountProperty() {
        return supportAmount;
    }

}
