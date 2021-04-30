package hu.gergelyszalay.zoo.adoption.desktop.adoption.mvccontroller;

import hu.gergelyszalay.zoo.adoption.desktop.CustomWindow;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.Adopter;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.api.impl.AdopterDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.api.impl.AdoptionDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;
import hu.gergelyszalay.zoo.adoption.desktop.animal.api.impl.AnimalDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.animal.mvccontroller.AnimalsHomeController;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdoptionsHomeController implements Initializable {

    public static Adoption currentlyViewedAdoption;
    public static Adopter currentlyViewedAdopter;
    public static Animal currentlyViewedAnimal;
    private CustomWindow addOrEditWindow;

    @FXML
    Label adoptedAnimalsOverTotalAnimals;
    @FXML
    Label adoptersTotal;

    @FXML
    Button adoptionsButton;
    @FXML
    Button adoptersButton;
    @FXML
    Button animalsButton;

    @FXML
    Pane adoptions;
    @FXML
    Pane adopters;
    @FXML
    Pane animals;

    @FXML
    private TableView<Adoption> adoptionsTable;
    @FXML
    TableColumn<Adoption, String> adoptionsColumn1;
    @FXML
    TableColumn<Adoption, String> adoptionsColumn2;
    @FXML
    TableColumn<Adoption, String> adoptionsColumn3;
    @FXML
    TableColumn<Adoption, String> adoptionsColumn4;
    @FXML
    TableColumn<Adoption, String> adoptionsColumn5;

    @FXML
    private TableView<Adopter> adoptersTable;
    @FXML
    TableColumn<Adopter, String> adoptersColumn1;
    @FXML
    TableColumn<Adopter, String> adoptersColumn2;
    @FXML
    TableColumn<Adopter, String> adoptersColumn3;

    @FXML
    private TableView<Animal> animalsTable;
    @FXML
    TableColumn<Animal, String> animalsColumn1;
    @FXML
    TableColumn<Animal, String> animalsColumn2;
    @FXML
    TableColumn<Animal, String> animalsColumn3;
    @FXML
    TableColumn<Animal, String> animalsColumn4;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeTotalLabels();

        refreshAdoptionsTable();
        refreshAdoptersTable();
        refreshAnimalsTable();

        initializeAdoptionsTable();
        initializeAdoptersTable();
        initializeAnimalsTable();

    }

    private void initializeTotalLabels(){

        Integer totalAdopters = new AdoptionDAOImpl().countAdopters();
        Integer totalAnimals = new AnimalDAOImpl().countAnimals();

        this.adoptersTotal.setText(new AdoptionDAOImpl().countAdopters().toString());
        this.adoptedAnimalsOverTotalAnimals.setText(totalAdopters.toString() + "/" + totalAnimals.toString());

    }

    private void initializeAdoptionsTable(){
        adoptionsColumn1.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        adoptionsColumn2.setCellValueFactory(new PropertyValueFactory<>("adopterId"));
        adoptionsColumn3.setCellValueFactory(new PropertyValueFactory<>("adoptionDate"));
        adoptionsColumn4.setCellValueFactory(new PropertyValueFactory<>("supportType"));
        adoptionsColumn5.setCellValueFactory(new PropertyValueFactory<>("supportAmount"));
        adoptionsTable.setRowFactory(tableView -> {
            TableRow<Adoption> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    currentlyViewedAdoption = row.getItem();
                    this.addOrEditWindow = new CustomWindow("/adoption.fxml", null,
                            "Örökbefogadás", 800, 500, true);
                }
            });
            return row;
        });
    }

    private void initializeAdoptersTable(){
        adoptersColumn1.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        adoptersColumn2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        adoptersColumn3.setCellValueFactory(new PropertyValueFactory<>("email"));
        adoptersTable.setRowFactory(adoptersTableView -> {
            TableRow<Adopter> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    currentlyViewedAdopter = row.getItem();
                    this.addOrEditWindow = new CustomWindow("/adopter.fxml", null,
                            "Örökbefogadó", 800, 500, true);
                }
            });
            return row;
        });
    }

    private void initializeAnimalsTable(){
        animalsColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        animalsColumn2.setCellValueFactory(new PropertyValueFactory<>("species"));
        animalsColumn3.setCellValueFactory(new PropertyValueFactory<>("introduction"));
        animalsColumn3.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
        animalsTable.setRowFactory(adoptersTableView -> {
            TableRow<Animal> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    currentlyViewedAnimal = row.getItem();
                    this.addOrEditWindow = new CustomWindow("/animal.fxml", null,
                            "Állat", 800, 500, true);
                }
            });
            return row;
        });
    }


    @FXML
    public void onExit() {
        Platform.exit();
    }

    private void editAnimal(Animal animal) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/animal" + ".fxml"));
        AnimalsHomeController animalsHomeController = fxmlLoader.getController();
        animalsHomeController.setAnimal(animal);
    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == adoptionsButton) {
            //adopters.setStyle("-fx-background-color : #1620A1");
            adoptions.toFront();
        }
        if (actionEvent.getSource() == adoptersButton) {
            //pnlMenus.setStyle("-fx-background-color : #53639F");
            adopters.toFront();
        }
        if (actionEvent.getSource() == animalsButton) {
           // pnlOverview.setStyle("-fx-background-color : #02030A");
            animals.toFront();
        }
    }


    public void refreshAdoptionsTable() {
        adoptionsTable.getItems().setAll(new AdoptionDAOImpl().findAll());
        adoptionsTable.refresh();
    }

    public void refreshAdoptersTable() {
        adoptersTable.getItems().setAll(new AdopterDAOImpl().findAll());
        adoptersTable.refresh();
    }

    public void refreshAnimalsTable() {
        animalsTable.getItems().setAll(new AnimalDAOImpl().findAll());
        animalsTable.refresh();
    }

    @FXML
    public void openNewAdoption(){
        currentlyViewedAdoption = new Adoption();
        this.addOrEditWindow = new CustomWindow("/adoption.fxml", null,
                "Új Örökbefogadás", 800, 500, false);
    }

}
