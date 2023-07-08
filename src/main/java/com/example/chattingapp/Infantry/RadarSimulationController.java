package com.example.chattingapp.Infantry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class RadarSimulationController {
    RadarModel radar = new RadarModel();
    @FXML
    private Label deviceStatusLabel;
    @FXML
    private Label deviceAltitudeLable, deviceIdLable, deviceLatitudeLable,deviceLongitudeLable,ipAddressLabel,portLabel;
    @FXML
    private TableView<TrackSeries> trackMissionTable;
    @FXML
    private TableColumn<TrackSeries, Integer> trackMissionIdColumn;
    @FXML
    private TableColumn<TrackSeries, Double[]> trackMissionAltitudeColumn;
    @FXML
    private TableColumn<TrackSeries, Double[]> trackMissionLatitudeColumn;
    @FXML
    private TableColumn<TrackSeries, Double[]> trackMissionLongitudeColumn;
    @FXML
    private TableColumn<TrackSeries, String> trackMissionTimeColumn;
    @FXML
    void stopSendingButton(ActionEvent event) {
        radar.setStatus(false);
        this.setRadarPane(radar);
    }
    @FXML
    public void initialize() {
        trackMissionIdColumn.setCellValueFactory(new PropertyValueFactory<TrackSeries, Integer>("id"));
        trackMissionLatitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackSeries, Double[]>("latitude"));
        trackMissionLongitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackSeries, Double[]>("longitude"));
        trackMissionAltitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackSeries, Double[]>("altitude"));
        trackMissionTimeColumn.setCellValueFactory(new PropertyValueFactory<TrackSeries, String>("time"));
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
