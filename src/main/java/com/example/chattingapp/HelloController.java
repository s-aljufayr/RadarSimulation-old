package com.example.chattingapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }

    @FXML
    public TextField enemyIdField;
    @FXML
    public TextField enemyLatitudeField;
    @FXML
    public TextField enemyAltitudeField;
    @FXML
    public TextField enemyLongitudeField;

    @FXML
    public TextField deviceIdField;
    @FXML
    public TextField deviceLatitudeField;
    @FXML
    public TextField deviceLongitudeField;
    @FXML
    public TextField deviceAltitudeField;

    ////////////////////////////////////////////////////////
    @FXML
    public Label enemyIdLabel;
    @FXML
    public Label enemyLatitudeLabel;

    @FXML
    public Label enemyLongitudeLabel;

    @FXML
    public Label enemyAltitudeLabel;

    @FXML
    public Button sendEnemyButton;

    /////////////////////////////////////////////////////////

    @FXML
    public Label deviceIdLabel;

    @FXML
    public Label deviceLatitudeLabel;

    @FXML
    public Label deviceLongitudeLabel;

    @FXML
    public Label deviceAltitudeLabel;

    @FXML
    public Button sendDeviceButton;



//    @FXML
//    public void initialize() {
//        enemyIdLabel.setText("Enemy Id");
//        enemyLatitudeLabel.setText("Enemy Latitude");
//        enemyLongitudeLabel.setText("Enemy Longitude");
//        enemyAltitudeLabel.setText("Enemy Altitude");
//        sendEnemyButton.setText("Send Enemy");
//        sendDeviceButton.setText("Send Device");
//    }

    @FXML
    public void sendEnemy() {
        String enemyId = enemyIdField.getText();
        String enemyLatitude = enemyLatitudeField.getText();
        String enemyLongitude = enemyLongitudeField.getText();
        System.out.println(enemyLongitudeField);
        String enemyAltitude = enemyAltitudeField.getText();

        enemyIdLabel.setText(enemyId);
        enemyLatitudeLabel.setText(enemyLatitude);
//        enemyLongitudeLabel.setText(enemyLongitude);
        enemyAltitudeLabel.setText(enemyAltitude);

//        System.out.println("Sending enemy with id " + enemyId + ", latitude " + enemyLatitude + ", longitude " + enemyLongitude + ", and altitude " + enemyAltitude);
    }
    @FXML
    public void sendDevice(){

        String deviceId = deviceIdField.getText();
        String deviceLatitude = deviceLatitudeField.getText();
        String deviceLongitude = deviceLongitudeField.getText();
        String deviceAltitude = deviceAltitudeField.getText();

        deviceIdLabel.setText(deviceId);
        deviceLatitudeLabel.setText(deviceLatitude);
        deviceLongitudeLabel.setText(deviceLongitude);
        deviceAltitudeLabel.setText(deviceAltitude);


    }
}