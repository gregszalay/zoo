package hu.gergelyszalay.zoo.adoption.desktop.desktopui.mvccontroller;

import hu.gergelyszalay.zoo.adoption.desktop.SupportTypeValues;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.Adopter;
import hu.gergelyszalay.zoo.adoption.desktop.adopter.AdopterDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.AdoptionDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;
import hu.gergelyszalay.zoo.adoption.desktop.animal.AnimalDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.CustomWindow;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdoptionsHomeController implements Initializable {

    public static Adoption currentlyViewedAdoption;
    public static Adopter currentlyViewedAdopter;
    public static Animal currentlyViewedAnimal;
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
    TableColumn<Adoption, String> adoptionsColumn6;
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
    RadioButton adopterNameSearchButton;
    @FXML
    RadioButton adoptedAnimalNameSearchButton;
    @FXML
    RadioButton adoptionYearSearchButton;
    @FXML
    RadioButton SupportTypeSearchButton;
    @FXML
    Button submitSearchButton;
    @FXML
    TextField searchedTermField;
    @FXML
    ToggleGroup group;
    @FXML
    HBox searchBox;
    @FXML
    ChoiceBox<String> supportTypeSearchChoice;
    private CustomWindow addOrEditWindow;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeToggleGroup();

        initializeAdoptionsTable();
        initializeAdoptersTable();
        initializeAnimalsTable();

        initializeTotalLabels();

        refreshAdoptionsTable();
        refreshAdoptersTable();
        refreshAnimalsTable();

        this.supportTypeSearchChoice.getItems().add(SupportTypeValues.FOOD.getValue());
        this.supportTypeSearchChoice.getItems().add(SupportTypeValues.FORAGE.getValue());
        this.supportTypeSearchChoice.getItems().add(SupportTypeValues.MEDICINE.getValue());


    }

    private void initializeToggleGroup() {
        group = new ToggleGroup();
        adopterNameSearchButton.setToggleGroup(group);
        adoptedAnimalNameSearchButton.setToggleGroup(group);
        adoptionYearSearchButton.setToggleGroup(group);
        SupportTypeSearchButton.setToggleGroup(group);
    }

    private void initializeTotalLabels() {

        Integer totalAdopters = new AdoptionDAOImpl().countAdopters();
        Integer totalAdoptedAnimals = new AdoptionDAOImpl().countAdoptedAnimals();
        Integer totalAnimals = new AnimalDAOImpl().countAnimals();

        this.adoptersTotal.setText(totalAdopters.toString());
        this.adoptedAnimalsOverTotalAnimals.setText(totalAdoptedAnimals.toString() + "/" + totalAnimals.toString());

    }

    private void initializeAdoptionsTable() {
        adoptionsColumn1.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        adoptionsColumn2.setCellValueFactory(new PropertyValueFactory<>("adopterId"));
        adoptionsColumn3.setCellValueFactory(new PropertyValueFactory<>("adoptionDate"));
        adoptionsColumn4.setCellValueFactory(new PropertyValueFactory<>("supportType"));
        adoptionsColumn5.setCellValueFactory(new PropertyValueFactory<>("supportAmount"));
        adoptionsColumn6.setCellValueFactory(new PropertyValueFactory<>("supportFrequency"));
        adoptionsTable.setRowFactory(tableView -> {
            TableRow<Adoption> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    currentlyViewedAdoption = row.getItem();
                    this.addOrEditWindow = new CustomWindow("/fxml/adoption.fxml", null,
                            "Örökbefogadás", 800, 500);
                }
            });
            return row;
        });
    }

    private void initializeAdoptersTable() {
        adoptersColumn1.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        adoptersColumn2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        adoptersColumn3.setCellValueFactory(new PropertyValueFactory<>("email"));
        adoptersTable.setRowFactory(adoptersTableView -> {
            TableRow<Adopter> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    currentlyViewedAdopter = row.getItem();
                    this.addOrEditWindow = new CustomWindow("/fxml/adopter.fxml", null,
                            "Örökbefogadó", 800, 500);
                }
            });
            return row;
        });
    }

    private void initializeAnimalsTable() {
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
                            "Állat", 800, 500);
                }
            });
            return row;
        });
    }

    public void handleClicks(ActionEvent actionEvent) {

        if (actionEvent.getSource() == adoptionsButton) {
            searchBox.visibleProperty().setValue(true);
            adoptions.toFront();
            refreshAdoptionsTable();
        }
        if (actionEvent.getSource() == adoptersButton) {
            searchBox.visibleProperty().setValue(false);
            adopters.toFront();
            refreshAdoptersTable();
        }
        if (actionEvent.getSource() == animalsButton) {
            searchBox.visibleProperty().setValue(false);
            animals.toFront();
            refreshAnimalsTable();
        }
    }

    public void handleToggleSearch() {
        if (this.searchedTermField.getText() == null || this.searchedTermField.getText().equals("")) return;
        String searchedTerm = this.searchedTermField.getText();

        List<Adoption> searchResults;

        if (group.getSelectedToggle() == this.adopterNameSearchButton) {
            List<Adopter> adopterResults = new AdopterDAOImpl().findByName(searchedTerm);
            if (adopterResults == null || adopterResults.isEmpty()) return;
            Adopter adopter = adopterResults.get(0);
            searchResults = new AdoptionDAOImpl().findByAdopterId(adopter.getId());
        } else if (group.getSelectedToggle() == this.adoptedAnimalNameSearchButton) {
            List<Animal> animalResults = new AnimalDAOImpl().findByName(searchedTerm);
            if (animalResults == null || animalResults.isEmpty()) return;
            Animal animal = animalResults.get(0);
            searchResults = new AdoptionDAOImpl().findByAnimalId(animal.getId());
        } else if (group.getSelectedToggle() == this.adoptionYearSearchButton) {
            searchedTerm = searchedTerm
                    .trim()
                    .replaceAll("[ ]+", "")
                    .replaceAll("[^0-9]+", "");
            if (searchedTerm.length() != 4) {
                Alert a = new Alert(Alert.AlertType.WARNING, "4 számjegyet adhat meg évszám keresésnél!",
                        ButtonType.OK);
                a.setContentText("4 számjegyet adhat meg évszám keresésnél!");
                a.show();
                return;
            }
            Integer year = Integer.parseInt(searchedTerm);
            searchResults = new AdoptionDAOImpl().searchByYear(year);
        } else if (group.getSelectedToggle() == this.SupportTypeSearchButton &&
                this.supportTypeSearchChoice.valueProperty().getValue() != null) {
            String supportType = this.supportTypeSearchChoice.valueProperty().getValue();
            searchResults = new AdoptionDAOImpl().findBySupportType(supportType);
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING, "Nem választott keresési szempontot!",
                    ButtonType.OK);
            a.setContentText("Nem választott keresési szempontot!");
            a.show();
            return;
        }
        adoptions.toFront();
        refreshAdoptionsTable(searchResults);
    }


    public void refreshAdoptionsTable() {
        List<Adoption> adoptionList = new AdoptionDAOImpl().findAll();
        Platform.runLater(() -> {
            adoptionsTable.getItems().setAll(adoptionList);
            adoptionsTable.refresh();
        });
    }

    public void refreshAdoptionsTable(List<Adoption> freshAdoptionsList) {
        Platform.runLater(() -> {
            adoptionsTable.getItems().setAll(freshAdoptionsList);
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
        ObservableList<Animal> animalObservableList = FXCollections.observableArrayList(animalList);
        Platform.runLater(() -> {
            animalsTable.getItems().setAll(animalObservableList);
            animalsTable.refresh();
        });
    }

    @FXML
    public void openNewAdoption() {
        currentlyViewedAdoption = new Adoption();
        this.addOrEditWindow = new CustomWindow("/fxml/adoption.fxml", null,
                "Új Örökbefogadás", 900, 500);
    }

    @FXML
    public void openNewAdopter() {
        currentlyViewedAdopter = new Adopter();
        this.addOrEditWindow = new CustomWindow("/fxml/adopter.fxml", null,
                "Új Örökbefogadó", 900, 500);
    }

    @FXML
    public void openNewAnimal() {
        currentlyViewedAnimal = new Animal();
        this.addOrEditWindow = new CustomWindow("/fxml/animal.fxml", null,
                "Új Állat", 900, 500);

    }


}
