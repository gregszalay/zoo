module hu.gergelyszalay {
    requires javafx.controls;
    requires javafx.fxml;

    opens hu.gergelyszalay to javafx.fxml;
    exports hu.gergelyszalay;
}