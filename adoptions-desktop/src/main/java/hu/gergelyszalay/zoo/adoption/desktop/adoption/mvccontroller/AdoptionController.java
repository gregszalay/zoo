package hu.gergelyszalay.zoo.adoption.desktop.adoption.mvccontroller;

import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class AdoptionController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}