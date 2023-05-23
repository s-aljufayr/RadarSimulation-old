module com.example.chattingapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.chattingapp to javafx.fxml;
    exports com.example.chattingapp;
}