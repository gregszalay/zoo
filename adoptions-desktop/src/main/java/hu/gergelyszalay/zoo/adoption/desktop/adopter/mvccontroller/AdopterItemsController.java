package hu.gergelyszalay.zoo.adoption.desktop.adopter.mvccontroller;

import hu.gergelyszalay.zoo.adoption.desktop.adopter.Adopter;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.api.impl.AdopterDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.mvccontroller.AdoptionsHomeController;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdopterItemsController implements Initializable {


    private Adopter adopter;


    @FXML
    private Button saveBtn;


    @FXML
    private TextField id;
    @FXML
    private TextField lastname;
    @FXML
    private TextField firstname;

    @FXML
    private TextField email;


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAdopter(AdoptionsHomeController.currentlyViewedAdopter);
        createBinds();
    }

    private void createBinds() {
        this.id.textProperty().bindBidirectional(this.adopter.idProperty(), new NumberStringConverter());
        this.lastname.textProperty().bindBidirectional(this.adopter.lastNameProperty());
        this.firstname.textProperty().bindBidirectional(this.adopter.firstNameProperty());
        this.email.textProperty().bindBidirectional(this.adopter.emailProperty());
    }

    @FXML
    public void onSave() {
        adopter = new AdopterDAOImpl().save(adopter);
        loadHome();
    }

    @FXML
    public void onDelete() {
        new AdopterDAOImpl().delete(adopter);
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

    public void setAdopter(Adopter adopter) {
        this.adopter = adopter;
    }


}