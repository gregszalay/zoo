package hu.gergelyszalay.zoo.adoption.desktop.desktopui.mvccontroller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;
import hu.gergelyszalay.zoo.adoption.desktop.animal.mvccontroller.AnimalController;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {

//
@FXML
TableView<Adoption> myTableView;

    public ObservableList<Adoption> adoptionList = FXCollections.observableArrayList();

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

    @FXML
    private void initialize() {
//
//    @FXML
//    private VBox pnItems = null;



//        TableView tableView = new TableView();
//
//        TableColumn<Person, String> firstNameColumn = TableColumn<>("First Name");
//        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//
//        TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last Name");
//        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//
//        this.tableView.setRowFactory(tableView -> {
//            TableRow<Adoptation> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if (event.getClickCount() == 2 && (!row.isEmpty())) {
//                    MainController.currentlyViewedProblem = row.getItem();
//                    CustomWindow reviewWindow = new CustomWindow("review.fxml", null, "Probléma felülvizsgálata", 800, 500);
//                    reviewWindow.getCustomStage().setOnCloseRequest(closeEvent -> {
//                        MainController.mainWindowReload();
//                        CustomWindow oldMainWindow = Main.mainWindow;
//                        Main.mainWindow = new CustomWindow("main-menu.fxml", null, "Bugzilla", 1200, 700);
//                        oldMainWindow.close();
//                    });
//
//                }
////            });
//            return row;
//        });


  }


    private Animal animal;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {



        column1.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        column2.setCellValueFactory(new PropertyValueFactory<>("adopterId"));
        column3.setCellValueFactory(new PropertyValueFactory<>("adoptionDate"));
        column4.setCellValueFactory(new PropertyValueFactory<>("supportType"));
        column5.setCellValueFactory(new PropertyValueFactory<>("supportAmount"));


        Adoption adoption1 = new Adoption();
        adoption1.setAdopterId(2645363);
        adoption1.setId(23542354);
        adoption1.setAnimalId(23542354);
        adoption1.setAdoptionDate(LocalDate.now());
        adoptionList.add(adoption1);
//        adoptionList.add(new Adoption(
//                "Gergő", "Gizmo",
//                LocalDate.now().toString(), "penz"
//        ));
//        adoptionList.add(new Adoption(
//                "Gergő", "Gizmo",
//                LocalDate.now().toString(), "penz"
//        ));

        myTableView.getItems().addAll(adoptionList);

//        Node[] nodes = new Node[10];
//        for (int i = 0; i < nodes.length; i++) {
//            try {
//
//                final int j = i;
//                nodes[i] = FXMLLoader.load(getClass().getResource("/animalItems.fxml"));
//
//                //give the items some effect
//
//                nodes[i].setOnMouseEntered(event -> {
//                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
//                    ((Label)nodes[j].lookup("#keeptolabel")).setText("keeptolabel");
//                });
//                nodes[i].setOnMouseExited(event -> {
//                    nodes[j].setStyle("-fx-background-color : #02030A");
//                });
//                pnItems.getChildren().add(nodes[i]);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


    }

    @FXML
    public void onExit(){
        Platform.exit();
    }

    private void editAnimal(Animal animal) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/animal" + ".fxml"));
        AnimalController animalController = fxmlLoader.getController();
        animalController.setAnimal(animal);
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
}
