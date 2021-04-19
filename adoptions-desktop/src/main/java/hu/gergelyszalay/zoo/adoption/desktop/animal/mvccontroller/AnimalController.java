package hu.gergelyszalay.zoo.adoption.desktop.animal.mvccontroller;

import java.io.IOException;

import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.fxml.FXML;

public class AnimalController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}