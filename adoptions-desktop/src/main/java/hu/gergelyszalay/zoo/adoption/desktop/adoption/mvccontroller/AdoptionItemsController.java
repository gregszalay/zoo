package hu.gergelyszalay.zoo.adoption.desktop.adoption.mvccontroller;

import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.api.impl.AdoptionDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.NumberStringConverter;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.MouseEvent;

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

    public Button getSaveBtn() {
        return saveBtn;
    }



    @FXML
    public void onSave() {
        adoption = new AdoptionDAOImpl().save(adoption);
        try {
            App.loadFXML("/home");
            System.out.println("loading");
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
        //createBinds();
    }


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        setAdoption(new Adoption());
        setAdoption(AdoptionsHomeController.currentlyViewedAdoption);
        createBinds();
//        this.saveBtn.setOnAction(event -> {
//            adoption = new AdoptionDAOImpl().save(adoption);
//        });
    }

    private void createBinds(){
        this.id.textProperty().bindBidirectional(this.adoption.idProperty(), new NumberStringConverter());
        this.animalId.textProperty().bindBidirectional(this.adoption.animalIdProperty(), new NumberStringConverter());
        this.adopterId.textProperty().bindBidirectional(this.adoption.adopterIdProperty(), new NumberStringConverter());
        this.adoptionDate.valueProperty().bindBidirectional(this.adoption.adoptionDateProperty());
        this.supportType.textProperty().bindBidirectional(this.adoption.supportTypeProperty());
        this.supportAmount.textProperty().bindBidirectional(this.adoption.supportAmountProperty(), new NumberStringConverter());
    }
}