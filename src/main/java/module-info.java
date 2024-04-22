module com.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.game to javafx.fxml;
    exports com.game;
    exports com.game.controllers;
    exports com.game.models;
    exports com.game.utils;
    exports com.game.view;
}