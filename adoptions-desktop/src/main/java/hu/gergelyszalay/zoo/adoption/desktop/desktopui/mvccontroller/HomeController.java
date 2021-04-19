package hu.gergelyszalay.zoo.adoption.desktop.desktopui.mvccontroller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import hu.gergelyszalay.zoo.adoption.desktop.adoption.Adoption;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeController implements Initializable {


    @FXML
    TableView<Adoption> myTableView;

    @FXML
    ChoiceBox<String> choiceBox;

    public ObservableList<Adoption> adoptionList = FXCollections.observableArrayList();

    @FXML
    TableColumn<Adoption, String> column1;
    @FXML
    TableColumn<Adoption, String> column2;
    @FXML
    TableColumn<Adoption, String> column3;

//    @FXML
//    private void initialize() {



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
////                    MainController.currentlyViewedProblem = row.getItem();
////                    CustomWindow reviewWindow = new CustomWindow("review.fxml", null, "Probléma felülvizsgálata", 800, 500);
////                    reviewWindow.getCustomStage().setOnCloseRequest(closeEvent -> {
////                        MainController.mainWindowReload();
////                        CustomWindow oldMainWindow = Main.mainWindow;
////                        Main.mainWindow = new CustomWindow("main-menu.fxml", null, "Bugzilla", 1200, 700);
////                        oldMainWindow.close();
//                    });
//
//                }
////            });
//            return row;
//        });


//    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {



        column1.setCellValueFactory(new PropertyValueFactory<>("adopter"));
        column2.setCellValueFactory(new PropertyValueFactory<>("animal"));
        column3.setCellValueFactory(new PropertyValueFactory<>("adoptionDate"));

//        adoptionList.add(new Adoption(
//                "Gergő", "Gizmo",
//                LocalDate.now().toString(), "penz"
//        ));
//        adoptionList.add(new Adoption(
//                "Gergő", "Gizmo",
//                LocalDate.now().toString(), "penz"
//        ));
//        adoptionList.add(new Adoption(
//                "Gergő", "Gizmo",
//                LocalDate.now().toString(), "penz"
//        ));

        myTableView.getItems().addAll(adoptionList);


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
