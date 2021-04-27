package hu.gergelyszalay.zoo.adoption.desktop.animal.mvccontroller;

import hu.gergelyszalay.zoo.adoption.desktop.adoption.api.AdoptionDAO;
import hu.gergelyszalay.zoo.adoption.desktop.adoption.api.impl.AdoptionDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.animal.Animal;
import hu.gergelyszalay.zoo.adoption.desktop.animal.api.AnimalDAO;
import hu.gergelyszalay.zoo.adoption.desktop.animal.api.impl.AnimalDAOImpl;
import hu.gergelyszalay.zoo.adoption.desktop.desktopui.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AnimalItemController {

    @FXML
    private TextField id;
    @FXML
    private TextField name;

    @FXML
    private TextField species;

    @FXML
    private TextField introduction;

    @FXML
    private TextField birthYear;



}
