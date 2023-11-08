module com.gr2343.ui {
    requires com.fasterxml.jackson.databind;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    requires com.gr2343.core;

    opens gr2343.ui to javafx.graphics, javafx.fxml;
}
