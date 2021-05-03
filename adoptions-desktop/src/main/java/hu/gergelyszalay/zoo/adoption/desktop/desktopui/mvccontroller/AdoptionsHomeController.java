package hu.gergelyszalay.zoo.adoption.desktop.desktopui.mvccontroller;

import hu.gergelyszalay.zoo.adoption.desktop.desktopui.CustomWindow;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.Adopter;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.impl.AdopterDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.impl.AdoptionDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;
import hu.gergelyszalay.zoo.adoption.desktop.animal.impl.AnimalDAOImpl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Base64;
import java.util.List;
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
    TableView<Adoption> adoptionsTable;
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
    TableView<Adopter> adoptersTable;
    @FXML
    TableColumn<Adopter, String> adoptersColumn1;
    @FXML
    TableColumn<Adopter, String> adoptersColumn2;
    @FXML
    TableColumn<Adopter, String> adoptersColumn3;

    @FXML
    TableView<Animal> animalsTable;
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

        initializeAdoptionsTable();
        initializeAdoptersTable();
        initializeAnimalsTable();

        initializeTotalLabels();

        refreshAdoptionsTable();
        refreshAdoptersTable();
        refreshAnimalsTable();



        System.out.println("initialize");

    }

    private void initializeTotalLabels(){

        Integer totalAdopters = new AdoptionDAOImpl().countAdopters();
        Integer totalAdoptedAnimals= new AdoptionDAOImpl().countAdoptedAnimals();
                Integer totalAnimals = new AnimalDAOImpl().countAnimals();

        this.adoptersTotal.setText(totalAdopters.toString());
        this.adoptedAnimalsOverTotalAnimals.setText(totalAdoptedAnimals.toString() + "/" + totalAnimals.toString());

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
                    this.addOrEditWindow = new CustomWindow("/fxml/adoption.fxml", null,
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
                    this.addOrEditWindow = new CustomWindow("/fxml/adopter.fxml", null,
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
                    this.addOrEditWindow = new CustomWindow("/fxml/animal.fxml", null,
                            "Állat", 800, 500, true);
                }
            });
            return row;
        });
    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == adoptionsButton) {
            //adopters.setStyle("-fx-background-color : #1620A1");
            adoptions.toFront();
            refreshAdoptionsTable();
        }
        if (actionEvent.getSource() == adoptersButton) {
            //pnlMenus.setStyle("-fx-background-color : #53639F");
            adopters.toFront();
            refreshAdoptersTable();
        }
        if (actionEvent.getSource() == animalsButton) {
           // pnlOverview.setStyle("-fx-background-color : #02030A");
            animals.toFront();
            refreshAnimalsTable();
        }
    }


    public void refreshAdoptionsTable() {
        //adoptionsTable.getItems().clear();
        List<Adoption> adoptionList = new AdoptionDAOImpl().findAll();

        Platform.runLater(()->{
            adoptionsTable.getItems().setAll(adoptionList);
            adoptionsTable.refresh();
        });
    }

    public void refreshAdoptersTable() {
        adoptersTable.getItems().clear();

        adoptersTable.getItems().setAll(new AdopterDAOImpl().findAll());
        adoptersTable.refresh();
    }

    public void refreshAnimalsTable() {
        animalsTable.getItems().clear();
        List<Animal> animalList = new AnimalDAOImpl().findAll();
        ObservableList<Animal> animalObservableList =
        FXCollections.observableArrayList(new AnimalDAOImpl().findAll());

        Platform.runLater(()->{
            animalsTable.getItems().setAll(animalObservableList);
            animalsTable.refresh();
        });
    }

    @FXML
    public void openNewAdoption(){
        currentlyViewedAdoption = new Adoption();
        this.addOrEditWindow = new CustomWindow("/fxml/adoption.fxml", null,
                "Új Örökbefogadás", 800, 500, false);
    }

    @FXML
    public void openNewAdopter(){
        currentlyViewedAdopter = new Adopter();
        this.addOrEditWindow = new CustomWindow("/fxml/adopter.fxml", null,
                "Új Örökbefogadó", 800, 500, false);
    }

    @FXML
    public void openNewAnimal(){
        currentlyViewedAnimal = new Animal();
        this.addOrEditWindow = new CustomWindow("/fxml/animal.fxml", null,
                "Új Állat", 800, 500, false);

    }



}
