package hu.gergelyszalay.zoo.adoption.desktop.desktopui;

import hu.gergelyszalay.zoo.adoption.desktop.adoption.mvccontroller.AdoptionItemsController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class CustomWindow extends Window {

    public CustomWindow(String FXMLResourceName, Stage priorStage, String windowTitle, double width, double height) {
        Parent root = new AnchorPane();
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(FXMLResourceName)));
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage customStage = Objects.requireNonNullElseGet(priorStage, Stage::new);
        customStage.setTitle(windowTitle);
        customStage.setScene(new Scene(root, width, height));
        customStage.show();
    }
}
