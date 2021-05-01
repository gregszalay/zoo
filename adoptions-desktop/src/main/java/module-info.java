module hu.gergelyszalay {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

//    opens hu.gergelyszalay to javafx.fxml;
//    opens hu.gergelyszalay.zoo.adoption.desktop to javafx.fxml;
    exports hu.gergelyszalay.zoo.adoption.desktop.adopter;
    opens hu.gergelyszalay.zoo.adoption.desktop.adopter to javafx.fxml;
    exports hu.gergelyszalay.zoo.adoption.desktop.adoption;
    opens hu.gergelyszalay.zoo.adoption.desktop.adoption to javafx.fxml;
    exports hu.gergelyszalay.zoo.adoption.desktop.animal;
    opens hu.gergelyszalay.zoo.adoption.desktop.animal to javafx.fxml;
    exports hu.gergelyszalay.zoo.adoption.desktop.adopter.mvccontroller;
    opens hu.gergelyszalay.zoo.adoption.desktop.adopter.mvccontroller to javafx.fxml;
    exports hu.gergelyszalay.zoo.adoption.desktop.adoption.mvccontroller;
    opens hu.gergelyszalay.zoo.adoption.desktop.adoption.mvccontroller to javafx.fxml;
    exports hu.gergelyszalay.zoo.adoption.desktop.animal.mvccontroller;
    opens hu.gergelyszalay.zoo.adoption.desktop.animal.mvccontroller to javafx.fxml;
    exports hu.gergelyszalay.zoo.adoption.desktop.desktopui;
    opens hu.gergelyszalay.zoo.adoption.desktop.desktopui to javafx.fxml;
    exports hu.gergelyszalay.zoo.adoption.desktop.desktopui.mvccontroller;
    opens hu.gergelyszalay.zoo.adoption.desktop.desktopui.mvccontroller to javafx.fxml;
    // opens hu.gergelyszalay.zoo.adoption.desktop.desktopui.mvccontroller to javafx.fxml;
   // exports hu.gergelyszalay.zoo.adoption.desktop.desktopui.mvccontroller;
}