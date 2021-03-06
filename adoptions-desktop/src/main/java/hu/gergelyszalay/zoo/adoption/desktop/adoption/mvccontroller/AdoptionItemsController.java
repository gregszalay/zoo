package hu.gergelyszalay.zoo.adoption.desktop.adoption.mvccontroller;

import hu.gergelyszalay.zoo.adoption.desktop.SupportFrequencyValues;
import hu.gergelyszalay.zoo.adoption.desktop.SupportTypeValues;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.AdoptionDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.mvccontroller.AdoptionsHomeController;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdoptionItemsController implements Initializable {

    private Adoption adoption;

    @FXML
    private TextField id;
    @FXML
    private TextField animalId;
    @FXML
    private TextField adopterId;
    @FXML
    private DatePicker adoptionDate;
    @FXML
    private ChoiceBox<String> supportType;
    @FXML
    private TextField supportAmount;
    @FXML
    private ChoiceBox<String> supportFrequency;

    @FXML
    private Button saveBtn;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAdoption(AdoptionsHomeController.currentlyViewedAdoption);
        this.supportType.getItems().add(SupportTypeValues.FOOD.getValue());
        this.supportType.getItems().add(SupportTypeValues.FORAGE.getValue());
        this.supportType.getItems().add(SupportTypeValues.MEDICINE.getValue());
        this.supportFrequency.getItems().add(SupportFrequencyValues.WEEKLY.getValue());
        this.supportFrequency.getItems().add(SupportFrequencyValues.MONTHLY.getValue());
        this.supportFrequency.getItems().add(SupportFrequencyValues.YEARLY.getValue());
        createBinds();
    }

    private void createBinds() {
        this.id.textProperty().bindBidirectional(this.adoption.idProperty(), new NumberStringConverter());
        this.animalId.textProperty().bindBidirectional(this.adoption.animalIdProperty(), new NumberStringConverter());
        this.adopterId.textProperty().bindBidirectional(this.adoption.adopterIdProperty(), new NumberStringConverter());
        this.adoptionDate.valueProperty().bindBidirectional(this.adoption.adoptionDateProperty());
        this.supportType.valueProperty().bindBidirectional(this.adoption.supportTypeProperty());
        this.supportAmount.textProperty().bindBidirectional(this.adoption.supportAmountProperty(), new NumberStringConverter());
        this.supportFrequency.valueProperty().bindBidirectional(this.adoption.supportFrequencyProperty());
    }

    @FXML
    public void onSave() {

        adoption = new AdoptionDAOImpl().save(adoption);
        loadHome();
    }

    @FXML
    public void onDelete() {
        new AdoptionDAOImpl().delete(adoption);
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

    public void setAdoption(Adoption adoption) {
        this.adoption = adoption;
    }


}