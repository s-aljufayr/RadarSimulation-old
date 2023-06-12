package com.example.chattingapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RadarSimulationApplication extends Application {
    UDPSender udpSender;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RadarSimulationApplication.class.getResource("radarSimulation-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Radar Simulation");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
