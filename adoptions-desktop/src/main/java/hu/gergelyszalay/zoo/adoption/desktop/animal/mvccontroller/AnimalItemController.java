package hu.gergelyszalay.zoo.adoption.desktop.animal.mvccontroller;

import hu.gergelyszalay.zoo.adoption.desktop.desktopui.mvccontroller.AdoptionsHomeController;
import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;
import hu.gergelyszalay.zoo.adoption.desktop.animal.impl.AnimalDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnimalItemController implements Initializable {


    private Animal animal;


    @FXML
    private Button saveBtn;

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
    private TextArea picture;



    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAnimal(AdoptionsHomeController.currentlyViewedAnimal);
        createBinds();
    }

    private void createBinds() {
        this.id.textProperty().bindBidirectional(this.animal.idProperty(), new NumberStringConverter());
        this.name.textProperty().bindBidirectional(this.animal.nameProperty());
        this.species.textProperty().bindBidirectional(this.animal.speciesProperty());
        this.introduction.textProperty().bindBidirectional(this.animal.introductionProperty());
        this.birthYear.textProperty().bindBidirectional(this.animal.birthYearProperty(), new NumberStringConverter());
        this.picture.textProperty().bindBidirectional(this.animal.pictureProperty());
    }

    @FXML
    public void onSave() {
        animal = new AnimalDAOImpl().save(animal);
        loadHome();
    }

    @FXML
    public void onDelete() {
        new AnimalDAOImpl().delete(animal);
       loadHome();
    }

    @FXML
    public void onCancel() {
        loadHome();
    }

    private void loadHome(){
        Platform.runLater(()->{
            try {
                App.loadFXML("/fxml/home");
            } catch (IOException e) {
                e.printStackTrace();
            }
            closeWindow();
        });
    }

    private void closeWindow() {
        ((Stage) this.saveBtn.getScene().getWindow()).close();
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }


}
