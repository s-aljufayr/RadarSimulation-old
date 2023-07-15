package com.example.simulationApp.Infantry;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class RadarSimulationController {
//    @FXML
//    private WebView mapWebView;
    @FXML
    private ImageView logoImageView;
    @FXML
    private Label deviceStatusLabel;
    @FXML
    private Label deviceAltitudeLable, deviceIdLable, deviceLatitudeLable,deviceLongitudeLable,ipAddressLabel,portLabel;
    @FXML
    public void initialize() {

        // Here you can add your link
//        mapWebView.getEngine().load("https://www.google.com/maps/@24.8012483,46.641581,14z?authuser=0&entry=ttu");
    }
    public void setRadarPane(RadarModel radar) {
        // print the radar information on radar labels
        if(!radar.isStatus()){
            deviceIdLable.setText("Unknown");
            deviceLatitudeLable.setText("Unknown");
            deviceLongitudeLable.setText("Unknown");
            deviceAltitudeLable.setText("Unknown");
            deviceStatusLabel.setText("Offline");
        }else{
            deviceIdLable.setText(String.valueOf(radar.getId()));
            deviceLatitudeLable.setText(String.valueOf(radar.getLatitude()));
            deviceLongitudeLable.setText(String.valueOf(radar.getLongitude()));
            deviceAltitudeLable.setText(String.valueOf(radar.getAltitude()));
            deviceStatusLabel.setText("Online");
        }
    }

}
