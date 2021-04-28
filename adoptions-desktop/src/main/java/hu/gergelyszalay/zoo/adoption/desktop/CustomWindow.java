package hu.gergelyszalay.zoo.adoption.desktop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomWindow {

    private Parent root = new AnchorPane();
    private final Stage customStage;

    public CustomWindow(String FXMLResourceName, Stage priorStage, String windowTitle, double width, double height) {
        try {
            this.root = FXMLLoader.load(getClass().getResource(FXMLResourceName));
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

//    public void open() {
//        customStage.show();
//    }

    public void close() {
        customStage.close();
    }

    public Stage getCustomStage() {
        return customStage;
    }
}
