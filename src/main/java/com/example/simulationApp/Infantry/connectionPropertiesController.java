package com.example.simulationApp.Infantry;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.UnknownHostException;

public class connectionPropertiesController {
    UDPSender udpSender = new UDPSender();
    UDPReceiver udpReceiver = new UDPReceiver();
    RadarModel radar = new RadarModel();
    @FXML
    private Label deviceStatusLabel;
    @FXML
    private TextField deviceAltitudeField,deviceIdField,deviceLatitudeField,deviceLongitudeField,ipAddressField,portField;
    @FXML
    private Label deviceAltitudeLable, deviceIdLable, deviceLatitudeLable,deviceLongitudeLable,ipAddressLabel,portLabel;

    public connectionPropertiesController() throws UnknownHostException {
    }

    @FXML
    void setConnectionProperties(ActionEvent event) throws UnknownHostException {
        String ipAddress = ipAddressField.getText();
        int port = Integer.parseInt(portField.getText());
        ipAddressLabel.setText(ipAddress);
        portLabel.setText(String.valueOf(port));
        udpSender.setPort(Integer.parseInt(String.valueOf(port)));
        udpSender.setIp_Address(ipAddress);
    }
    @FXML
    void setRadarButton(ActionEvent event) throws IOException {
        // get the radar from the UI
        this.setRadarProperties(radar);
        udpReceiver.startReceiving();
        this.startSending(radar);
    }
    private void setRadarProperties(RadarModel radar) {
        radar.setId(Integer.parseInt(deviceIdField.getText()));
        radar.setLatitude(Double.parseDouble(deviceLatitudeField.getText()));
        radar.setLongitude(Double.parseDouble(deviceLongitudeField.getText()));
        radar.setAltitude(Double.parseDouble(deviceAltitudeField.getText()));
        radar.setStatus(true);
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
    private void startSending(RadarModel radar) {
        radar.setStatus(true);

        Thread sendStatus = new Thread(() -> {
            while (radar.isStatus()) {
                // to send the radar information
                try {
                    udpSender.sendData(radar);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // set Radar Pane On UI
                Platform.runLater(() -> {
                    setRadarPane(radar);
                });
                if(!udpReceiver.isOnline())
                    radar.setStatus(false);
                if(!radar.isStatus())
                    break;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        sendStatus.start();
    }

}
