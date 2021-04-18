package hu.gergelyszalay;

import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TableView<Adoptation> myTableView;

    @FXML
    ChoiceBox<String> choiceBox;

    public ObservableList<Adoptation> adoptationList = FXCollections.observableArrayList();

    @FXML
    TableColumn<Adoptation, String> column1;
    @FXML
    TableColumn<Adoptation, String> column2;
    @FXML
    TableColumn<Adoptation, String> column3;

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

        adoptationList.add(new Adoptation(
                "Gergő", "Gizmo",
                LocalDate.now().toString(), "penz"
        ));
        adoptationList.add(new Adoptation(
                "Gergő", "Gizmo",
                LocalDate.now().toString(), "penz"
        ));
        adoptationList.add(new Adoptation(
                "Gergő", "Gizmo",
                LocalDate.now().toString(), "penz"
        ));

        myTableView.getItems().addAll(adoptationList);


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
}
