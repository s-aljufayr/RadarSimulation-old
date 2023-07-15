module com.example.chattingapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires javafx.web;

    opens com.example.simulationApp to javafx.fxml;
    exports com.example.simulationApp;
    exports com.example.simulationApp.Infantry;
    opens com.example.simulationApp.Infantry to javafx.fxml;
}