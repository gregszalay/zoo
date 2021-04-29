package hu.gergelyszalay.zoo.adoption.desktop.adoption.mvccontroller;

import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.api.impl.AdoptionDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdoptionItemsController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField animalId;
    @FXML
    private TextField adopterId;
    @FXML
    private DatePicker adoptionDate;
    @FXML
    private TextField supportType;
    @FXML
    private TextField supportAmount;

    @FXML
    private Button saveBtn;

    private Adoption adoption;

    @FXML
    public void onSave() {
        adoption = new AdoptionDAOImpl().save(adoption);
        try {
            App.loadFXML("/home");
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeWindow();
    }

    private void closeWindow() {
        ((Stage) this.saveBtn.getScene().getWindow()).close();
    }


    @FXML
    public void onCancel() {
        try {
            App.loadFXML("/home");
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeWindow();
    }

    public void setAdoption(Adoption adoption) {
        this.adoption = adoption;
    }


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAdoption(AdoptionsHomeController.currentlyViewedAdoption);

        id.textProperty().bindBidirectional(adoption.idProperty(), new NumberStringConverter());
        animalId.textProperty().bindBidirectional(adoption.animalIdProperty(), new NumberStringConverter());
        adopterId.textProperty().bindBidirectional(adoption.adopterIdProperty(), new NumberStringConverter());
        adoptionDate.valueProperty().bindBidirectional(adoption.adoptionDateProperty());
        supportType.textProperty().bindBidirectional(adoption.supportTypeProperty());
        supportAmount.textProperty().bindBidirectional(adoption.supportAmountProperty(), new NumberStringConverter());

    }
}