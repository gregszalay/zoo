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

    private Parent root = new AnchorPane();
    private final Stage customStage;


    public CustomWindow(String FXMLResourceName, Stage priorStage, String windowTitle, double width, double height,
                        boolean inEditMode) {

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(FXMLResourceName)));
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (priorStage == null){
            this.customStage = new Stage();
        } else this.customStage = priorStage;
        customStage.setTitle(windowTitle);
        customStage.setScene(new Scene(root, width, height));
        customStage.show();
    }

    public void close() {
        customStage.close();
    }

    public Stage getCustomStage() {
        return customStage;
    }
}
