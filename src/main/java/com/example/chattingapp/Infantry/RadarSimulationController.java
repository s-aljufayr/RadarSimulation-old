package com.example.chattingapp.Infantry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class RadarSimulationController {
    RadarModel radar = new RadarModel();
    @FXML
    private WebView mapWebView;
    @FXML
    private Label deviceStatusLabel;
    @FXML
    private Label deviceAltitudeLable, deviceIdLable, deviceLatitudeLable,deviceLongitudeLable,ipAddressLabel,portLabel;
    private TableColumn<TrackSeries, String> trackMissionTimeColumn;
    @FXML
    void stopSendingButton(ActionEvent event) {
        radar.setStatus(false);
        this.setRadarPane(radar);
    }
    @FXML
    public void initialize() {
        // Here you can add your link
        mapWebView.getEngine().load("Here you can add your link");
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
