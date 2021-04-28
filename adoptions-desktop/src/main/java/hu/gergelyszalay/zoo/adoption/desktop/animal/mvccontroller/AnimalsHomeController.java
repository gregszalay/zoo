package hu.gergelyszalay.zoo.adoption.desktop.animal.mvccontroller;

import java.io.IOException;

import hu.gergelyszalay.zoo.adoption.desktop.adoption.api.AdoptionDAO;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.api.impl.AdoptionDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;
import hu.gergelyszalay.zoo.adoption.desktop.animal.api.AnimalDAO;
import hu.gergelyszalay.zoo.adoption.desktop.animal.api.impl.AnimalDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AnimalsHomeController {



    @FXML
    private TextField id;
    @FXML
    private TextField name;

    @FXML
    private TextField species;

    @FXML
    private TextField introduction;

    @FXML
    private TextField birthYear;




    @FXML
    private Button saveBtn;

//    private Animal animal;
//    private AnimalDAO animalDAO = new AnimalDAOImpl();
//    private AdoptionDAO adoptionDAO = new AdoptionDAOImpl();
//


    @FXML
    public void onSave(){
        //animal = animalDAO.save(animal);
        try {
            App.loadFXML("/home.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void onCancel(){
        try {
            App.loadFXML("/home.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }    }


    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    public void setAnimal(Animal animal) {
        //this.animal = animal;
    }


}