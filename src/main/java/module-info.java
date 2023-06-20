module com.example.chattingapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;

    opens com.example.chattingapp to javafx.fxml;
    exports com.example.chattingapp;
    exports com.example.chattingapp.Infantry;
    opens com.example.chattingapp.Infantry to javafx.fxml;
}