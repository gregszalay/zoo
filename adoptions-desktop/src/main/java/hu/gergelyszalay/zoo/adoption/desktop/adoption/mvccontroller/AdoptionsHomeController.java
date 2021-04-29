package hu.gergelyszalay.zoo.adoption.desktop.adoption.mvccontroller;

import hu.gergelyszalay.zoo.adoption.desktop.CustomWindow;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.api.impl.AdoptionDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;
import hu.gergelyszalay.zoo.adoption.desktop.animal.api.impl.AnimalDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.animal.mvccontroller.AnimalsHomeController;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdoptionsHomeController implements Initializable {

    public ObservableList<Adoption> adoptionList = FXCollections.observableArrayList();
    @FXML
    TableView<Adoption> myTableView;
    @FXML
    TableColumn<Adoption, String> column1;
    @FXML
    TableColumn<Adoption, String> column2;
    @FXML
    TableColumn<Adoption, String> column3;
    @FXML
    TableColumn<Adoption, String> column4;
    @FXML
    TableColumn<Adoption, String> column5;
    @FXML
    Label adoptedAnimalsOverTotalAnimals;
    @FXML
    Label adoptersTotal;
    @FXML
    Integer numberOfAdoptations;
    private Animal animal;
    public static Adoption currentlyViewedAdoption;


    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.adoptersTotal.setText(new AdoptionDAOImpl().countAdopters().toString());
        this.adoptedAnimalsOverTotalAnimals.setText(
                new AdoptionDAOImpl().countAdopters().toString() + "/"
                + new AnimalDAOImpl().countAnimals().toString()        );

        column1.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        column2.setCellValueFactory(new PropertyValueFactory<>("adopterId"));
        column3.setCellValueFactory(new PropertyValueFactory<>("adoptionDate"));
        column4.setCellValueFactory(new PropertyValueFactory<>("supportType"));
        column5.setCellValueFactory(new PropertyValueFactory<>("supportAmount"));


        adoptionList.addAll(new AdoptionDAOImpl().findAll());

        myTableView.getItems().addAll(adoptionList);

        myTableView.setRowFactory(tableView -> {
            TableRow<Adoption> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    currentlyViewedAdoption = row.getItem();
                    CustomWindow reviewWindow = new CustomWindow("/adoption.fxml", null, "Örökbefogadás", 800, 500);
                    reviewWindow.getCustomStage().setOnCloseRequest(closeEvent -> {
                        this.refreshTable();
//                        CustomWindow oldMainWindow = Main.mainWindow;
//                        Main.mainWindow = new CustomWindow("main-menu.fxml", null, "Bugzilla", 1200, 700);
//                        oldMainWindow.close();
                    });

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
//        if (actionEvent.getSource() == btnCustomers) {
//            pnlCustomer.setStyle("-fx-background-color : #1620A1");
//            pnlCustomer.toFront();
//        }
//        if (actionEvent.getSource() == btnMenus) {
//            pnlMenus.setStyle("-fx-background-color : #53639F");
//            pnlMenus.toFront();
//        }
//        if (actionEvent.getSource() == btnOverview) {
//            pnlOverview.setStyle("-fx-background-color : #02030A");
//            pnlOverview.toFront();
//        }
//        if (actionEvent.getSource() == btnOrders) {
//            pnlOrders.setStyle("-fx-background-color : #464F67");
//            pnlOrders.toFront();
//        }
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
}

    private void refreshTable() {
        //myTableView.getItems().setAll(dao.findAll());
    }

    }
