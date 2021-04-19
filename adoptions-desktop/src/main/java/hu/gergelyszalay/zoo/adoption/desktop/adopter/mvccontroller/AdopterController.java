package hu.gergelyszalay.zoo.adoption.desktop.adopter.mvccontroller;

import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class AdopterController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}