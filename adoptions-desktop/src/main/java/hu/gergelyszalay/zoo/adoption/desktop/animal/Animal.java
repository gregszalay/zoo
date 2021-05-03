package hu.gergelyszalay.zoo.adoption.desktop.animal;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class Animal {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private final StringProperty species = new SimpleStringProperty(this, "species");
    private final StringProperty introduction = new SimpleStringProperty(this, "introduction");
    private final IntegerProperty birthYear = new SimpleIntegerProperty(this, "birthYear");
    private final StringProperty picture = new SimpleStringProperty(this, "picture");


    public Animal() {
        id.set(-1);
        name.set("");
        species.set("");
        introduction.set("");
        birthYear.set(0);
        picture.set("");
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
        this.name.set(name.toLowerCase());
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

    public String getPicture() {
        return picture.get();
    }

    public StringProperty pictureProperty() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture.set(picture);
    }

    public void setBirthYear(int birthYear) {
        this.birthYear.set(birthYear);
    }

    public IntegerProperty birthYearProperty() {
        return birthYear;
    }

    private ImageView base64decode(String base64String){
        byte[] imageBytes= Base64.getDecoder().decode(base64String.getBytes());
        ByteArrayInputStream is=new ByteArrayInputStream(imageBytes);
        return new ImageView(new Image(is));
    }

    public ImageView getPictureImageView(){
        return base64decode(this.getPicture());
    }


}
