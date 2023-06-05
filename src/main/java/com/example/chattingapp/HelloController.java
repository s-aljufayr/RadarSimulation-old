package com.example.chattingapp;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HelloController {

    ObservableList<Track> tracksList;
    UDPSender udpSender = new UDPSender();
    private int minute;
    private int hour;
    private int second;
    private double latitude;
    private double longitude;
    private double altitude;
    double startLatitude, startLongitude, startAltitude, endLatitude, endLongitude, endAltitude, speed, changeLatitude, changeLongitude, changeAltitude;
    long trackerFrequency;
    private int trackPerSecond;
    boolean latitudeTracker;
    boolean longitudeTracker;
    boolean altitudeTracker;
    String trackTime;

    double trackLatitude;
    double trackLongitude;
    double trackAltitude;
    @FXML
    private Button AutoNewTrackSender;
    @FXML
    private TextField trackIdField;
    @FXML
    private Button AutoTrackSender;
    @FXML
    private TextField trakIdField;
    @FXML
    private TextField trackFrequencyField;
    @FXML
    private TextField altitudeChangeField;
    @FXML
    private Tab autoTrackControl;
    @FXML
    private TextField autoTrackEndAltitudeField;
    @FXML
    private TextField autoTrackEndLatitudeField;
    @FXML
    private TextField autoTrackEndLongitudeField;
    @FXML
    private TextField autoTrackIdField;
    @FXML
    private TextField autoTrackSpeedField;
    @FXML
    private TextField autoTrackStartAltitudeField;
    @FXML
    private TextField autoTrackStartLatitudeField;
    @FXML
    private TextField autoTrackStartLongitudeField;
    @FXML
    private Label enemyMilitarySymbol;
    @FXML
    private Label enemyPv1;
    @FXML
    private Label enemyPv2;
    @FXML
    private Label enemyRadarId;
    @FXML
    private Label enemyRcs;
    @FXML
    private Label enemyTime;
    @FXML
    private Label enemyType;
    @FXML
    private Label enemyV1;
    @FXML
    private Label enemyV2;
    @FXML
    private TextField latitudeChangeField;
    @FXML
    private TextField longitudeChangeField;
    @FXML
    private TextField numberOfTrackPerSecond;
    @FXML
    private Button sendConnectionProperties;
    @FXML
    private Button sendEnemiesTrackButton;
    @FXML
    private TextField timeOfTracking;
    @FXML
    private ChoiceBox<?> trackIdCheckBox;
    //////////////////////////////////////////////////
//    @FXML
//    private Spinner<Integer> enemyTimeField;
//    @FXML
//    private Spinner<Double> enemyAltitudeField;
//    @FXML
//    private Spinner<Double> enemyLatitudeField;
//    @FXML
//    private Spinner<Double> enemyLongitudeField;
//    @FXML
//    private Spinner<Double> enemySpeedField;
//    @FXML
//    private Spinner<?> enemyTimeField1;
    @FXML
    private TextField numberOfTrack;
    @FXML
    private TextField enemyTimeField;
    @FXML
    private TextField enemySpeedField;
    @FXML
    private TableView<Track> enemyTable;
    @FXML
    private TableColumn<Track, String> militarySymbolColumn;
    @FXML
    private Button deleteEnemy;
    @FXML
    private TableColumn<Track, Double> pV1Column;
    @FXML
    private TableColumn<Track, Double> pV2Column;
    @FXML
    private TableColumn<Track, Integer> radarIdColumn;
    @FXML
    private TableColumn<Track, Double> rcsColumn;
    @FXML
    private TableColumn<Track, Integer> timeColumn;
    @FXML
    private TableColumn<Track, Integer> typeColumn;
    @FXML
    private TableColumn<Track, Double> v1Column;
    @FXML
    private TableColumn<Track, Double> v2Column;
    @FXML
    private TableColumn<Track, Double> enemyLongitudeColumn;
    @FXML
    private TableColumn<Track, Integer> enemyIdColumn;
    @FXML
    private TableColumn<Track, Double> enemyLatitudeColumn;
    @FXML
    private TableColumn<Track, Double> enemyAltitudeColumn;
    @FXML
    private TableColumn<Track, Double> speedColumn;
    /////////////////////////////////////////////////////////
    @FXML
    private Label deviceAltitude;
    @FXML
    private Label deviceAltitude1;
    @FXML
    private TextField deviceAltitudeField;
    @FXML
    private Label deviceAltitudeLable;
    @FXML
    private Label deviceId;
    @FXML
    private Label deviceId1;
    @FXML
    private TextField deviceIdField;
    @FXML
    private Label deviceIdLable;
    @FXML
    private Label deviceLatitude;
    @FXML
    private Label deviceLatitude1;
    @FXML
    private TextField deviceLatitudeField;
    @FXML
    private Label deviceLatitudeLable;
    @FXML
    private Label deviceLongitude;
    @FXML
    private Label deviceLongitude1;
    @FXML
    private TextField deviceLongitudeField;
    @FXML
    private Label deviceLongitudeLable;
    @FXML
    private Label enemyAltitude1;
    @FXML
    private TextField enemyAltitudeField;
    @FXML
    private Label enemyAltitudeLable, trackV2Lable, trackPv2Lable, trackPv1Lable, trackV1Lable, trackRcsLabel,trackTypeLabel;
    @FXML
    private Label enemyId;
    @FXML
    private Label enemyId1, trackRadarIdLabel;
    @FXML
    private TextField enemyIdField;
    @FXML
    private Label enemyIdLable;
    @FXML
    private Label enemyLatitude;
    @FXML
    private Label enemyLatitude1;
    @FXML
    private TextField enemyLatitudeField;
    @FXML
    private Label enemyLatitudeLable;
    @FXML
    private Label trackMilitarySymbolLabel;
    @FXML
    private Label enemyLongitude;
    @FXML
    private Label enemyLongitude1;
    @FXML
    private TextField enemyLongitudeField;
    @FXML
    private Label enemyLongitudeLable;
    @FXML
    private TextField enemyMilitarySymbolField;
    @FXML
    private TextField enemyRadarIdField;
    @FXML
    private TextField enemyV1Field;
    @FXML
    private TextField enemyV2Field;
    @FXML
    private TextField enemyPv1Field;
    @FXML
    private TextField enemyPv2Field;
    @FXML
    private TextField enemyRcsField;
    @FXML
    private TextField enemyTypeField;
    @FXML
    private TextField ipAddressField;
    @FXML
    private TextField portField;
    @FXML
    private Label ipAddressLabel;
    @FXML
    private Label portLabel;
    @FXML
    private Button sendDevice;
    @FXML
    private Button sendEnemy;
    @FXML
    private Label enemySpeed;

    public HelloController() throws UnknownHostException {
    }


    @FXML
    void deleteTrackButton(ActionEvent event) {
        int selectedEnemy = enemyTable.getSelectionModel().getSelectedIndex();
        enemyTable.getItems().remove(selectedEnemy);
        }
    @FXML
    void resetSimulationButton(ActionEvent event) {
        enemyTable.getItems().clear();
    }
    @FXML
    void AutoTrackSenderButton(ActionEvent event) {
        // call the select row on the table
        Track selectedTrack = enemyTable.getSelectionModel().getSelectedItem();

        trakIdField.setText(String.valueOf(selectedTrack.getId()));
        String trackId = String.valueOf(selectedTrack.getId());
        latitude = Double.parseDouble(String.valueOf(selectedTrack.getLatitude()));
        longitude = Double.parseDouble(String.valueOf(selectedTrack.getLongitude()));
        altitude = Double.parseDouble(String.valueOf(selectedTrack.getAltitude()));

        double changeLatitude = Double.parseDouble(latitudeChangeField.getText());
        double changeLongitude = Double.parseDouble(longitudeChangeField.getText());
        double changeAltitude = Double.parseDouble(altitudeChangeField.getText());
        trackPerSecond = Integer.parseInt(numberOfTrackPerSecond.getText());
        long timeOfTrack = Long.parseLong(timeOfTracking.getText()) * 1000;

        long startTime = System.currentTimeMillis();

        long minuteInMillis = (trackPerSecond * 60) * 1000; // 1 minute in milliseconds

        Thread send = new Thread() {
            public void run() {
                while (true) {
                    long elapsedTime;
                    // Perform loop operations here
                    latitude = latitude + changeLatitude;
                    longitude = longitude + changeLongitude;
                    altitude = altitude + changeAltitude;

                    Track tracker = new Track(Integer.parseInt(trackId),latitude,longitude,altitude);

                    tracksList = enemyTable.getItems();
                    tracksList.add(tracker);
                    enemyTable.setItems(tracksList);

                    try {
                        udpSender.sendData(tracker);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    // Calculate elapsed time
                    elapsedTime = System.currentTimeMillis() - startTime;

                    // Check if a minute has passed
                    if (elapsedTime >= minuteInMillis) {
                        break; // Exit the loop
                    }
                    try {
                        Thread.sleep(timeOfTrack); // Delay for 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        send.start();
//        enemyRadarIdField.setText(deviceIdField.getText());

//        enemyLatitudeField.setText(String.valueOf(selectedTrack.getLatitude()));
//        enemyLongitudeField.setText(String.valueOf(selectedTrack.getLongitude()));
//        enemyAltitudeField.setText(String.valueOf(selectedTrack.getAltitude()));
//        enemyRadarIdField.setText(String.valueOf(selectedTrack.getRadarId()));
    }
    @FXML
    void AutoNewTrackSenderButton(ActionEvent event){
        int id;

        id = Integer.parseInt(autoTrackIdField.getText());
        speed = Double.parseDouble(autoTrackSpeedField.getText());
        startLatitude = Double.parseDouble(autoTrackStartLatitudeField.getText());
        startLongitude = Double.parseDouble(autoTrackStartLongitudeField.getText());
        startAltitude = Double.parseDouble(autoTrackStartAltitudeField.getText());
        endLatitude = Double.parseDouble(autoTrackEndLatitudeField.getText());
        endLongitude = Double.parseDouble(autoTrackEndLongitudeField.getText());
        endAltitude = Double.parseDouble(autoTrackEndAltitudeField.getText());
        trackerFrequency = Long.parseLong(trackFrequencyField.getText());
        changeLatitude = Double.parseDouble(latitudeChangeField.getText());
        changeLongitude = Double.parseDouble(longitudeChangeField.getText());
        changeAltitude = Double.parseDouble(altitudeChangeField.getText());

//        trackLatitude = startLatitude;
//        trackLongitude = startLongitude;
//        trackAltitude = startAltitude;

        latitudeTracker = false;
        longitudeTracker = false;
        altitudeTracker = false;

        long startTime = System.currentTimeMillis();

        long minuteInMillis = (trackerFrequency * 60) * 1000; // 1 minute in milliseconds

        Thread send = new Thread() {
            public void run() {
                while (!(latitudeTracker && longitudeTracker && altitudeTracker)) {
                    long elapsedTime;
                    // Perform loop operations here
                    if(!latitudeTracker){
                        if(startLatitude < endLatitude){
                            startLatitude += changeLatitude;
                            if(startLatitude >= endLatitude)
                                latitudeTracker = true;
                        }else{
                            startLatitude -= changeLatitude;
                            if(startLatitude <= endLatitude)
                                latitudeTracker = true;
                        }
                    }
                    if(!longitudeTracker){
                        if(startLongitude < endLongitude){
                            startLongitude += changeLongitude;
                            if(startLongitude >= endLongitude)
                                longitudeTracker = true;
                        }else {
                            startLongitude -= changeLongitude;
                            if(startLongitude <= endLongitude)
                                longitudeTracker = true;
                        }

                    }
                    if(!altitudeTracker){
                        if(startAltitude < endAltitude){
                            startAltitude += changeAltitude;
                            if(startAltitude >= endAltitude)
                                altitudeTracker = true;
                        }else{
                            startAltitude -= changeAltitude;
                            if(startAltitude <= endAltitude)
                                altitudeTracker = true;
                        }
                    }
                    // Calculate elapsed time
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    Calendar cal = Calendar.getInstance();

                    second = cal.get(Calendar.SECOND);
                    minute = cal.get(Calendar.MINUTE);
                    hour = cal.get(Calendar.HOUR);
                    //System.out.println(hour + ":" + (minute) + ":" + second);
                    trackTime = hour + ":" + (minute) + ":" + second;
//                    enemyTimeField.setText(hour + ":" + (minute) + ":" + second);


                    // fill the track model
                    Track tracker = new Track(Integer.parseInt(autoTrackIdField.getText()),trackTime
                            ,startLatitude,startLongitude,startAltitude,Double.parseDouble(autoTrackSpeedField.getText())
                            ,trackMilitarySymbolLabel.getText(),Integer.parseInt(trackRadarIdLabel.getText())
                            ,Double.parseDouble(trackV1Lable.getText()),Double.parseDouble(trackV2Lable.getText())
                            ,Double.parseDouble(trackPv1Lable.getText()),Double.parseDouble(trackPv2Lable.getText())
                            ,Double.parseDouble(trackRcsLabel.getText()),Integer.parseInt(trackTypeLabel.getText()));
                    // fill the tableView
                    tracksList = enemyTable.getItems();
                    tracksList.add(tracker);
                    enemyTable.setItems(tracksList);
                    // sending the json body through UDPSender



                    try {
                        Thread.sleep(1000); // Delay for 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        send.start();


    }
    @FXML
    void startSimulationButton(ActionEvent event) throws IOException {
//        udpSender = new UDPSender(Integer.parseInt(portField.getText()),ipAddressField.getText());
        udpSender.sendData(tracksList);
    }

    @FXML
    void sendConnectionProperties(ActionEvent event) throws UnknownHostException {
        String ipAddress = ipAddressField.getText();
        int port = Integer.parseInt(portField.getText());

        ipAddressLabel.setText(ipAddress);
        portLabel.setText(String.valueOf(port));

//        udpSender = new UDPSender(Integer.parseInt(portField.getText()),ipAddressField.getText());
        udpSender.setPort(Integer.parseInt(String.valueOf(port)));
        udpSender.setIp_Address(ipAddress);

    }

    @FXML
    void sendDeviceButton(ActionEvent event) throws IOException {
        String deviceId = deviceIdField.getText();
        String deviceLatitude = deviceLatitudeField.getText();
        String deviceLongitude = deviceLongitudeField.getText();
        String deviceAltitude = deviceAltitudeField.getText();

        Device device = new Device(Integer.parseInt(deviceIdField.getText()),Double.parseDouble(deviceLatitudeField.getText())
                ,Double.parseDouble(deviceLongitudeField.getText()),Double.parseDouble(deviceAltitudeField.getText()));


        deviceIdLable.setText(deviceId);
        deviceLatitudeLable.setText(deviceLatitude);
        deviceLongitudeLable.setText(deviceLongitude);
        deviceAltitudeLable.setText(deviceAltitude);

//        udpSender = new UDPSender(Integer.parseInt(portLabel.getText()),ipAddressLabel.getText());
//        udpSender.setIp_Address(ipAddressField.getText());
//        udpSender.setPort(Integer.parseInt(portField.getText()));
        udpSender.sendData(device);

    }

    @FXML
    void sendEnemyButton(ActionEvent event) throws IOException {
        Track tracker = new Track(Integer.parseInt(enemyIdField.getText()),enemyTimeField.getText()
                ,Double.parseDouble(enemyLatitudeField.getText()),Double.parseDouble(enemyLongitudeField.getText())
                ,Double.parseDouble(enemyAltitudeField.getText()),Double.parseDouble(enemySpeedField.getText())
                ,enemyMilitarySymbolField.getText(),Integer.parseInt(enemyRadarIdField.getText())
                ,Double.parseDouble(enemyV1Field.getText()),Double.parseDouble(enemyV2Field.getText())
                ,Double.parseDouble(enemyPv1Field.getText()),Double.parseDouble(enemyPv2Field.getText())
                ,Double.parseDouble(enemyRcsField.getText()),Integer.parseInt(enemyTypeField.getText()));

        tracksList = enemyTable.getItems();
        tracksList.add(tracker);
        enemyTable.setItems(tracksList);

//        int currentEnemyId = Integer.parseInt(enemyIdField.getText());
//
//        for (Animal animal : currentTableData) {
//            if(animal.getId() == currentAnimalId) {
//                animal.setType(inputType.getText());
//                animal.setName(inputName.getText());
//
//                animals.setItems(currentTableData);
//                animals.refresh();
//                break;
//            }
//        }

//        UDPSender udpSender = new UDPSender();
//        udpSender = new UDPSender(Integer.parseInt(portField.getText()),ipAddressField.getText());
        udpSender.sendData(tracker);



    }

//    @FXML
//    void rowClicked(MouseEvent event) {
//
//        int selectedEnemy = enemyTable.getSelectionModel().getSelectedIndex();
//        Track selectedTrack = enemyTable.getSelectionModel().getSelectedItem();
//        trakIdField.setText(String.valueOf(selectedTrack.getId()));
////        enemyLatitudeField.setText(String.valueOf(selectedTrack.getLatitude()));
////        enemyLongitudeField.setText(String.valueOf(selectedTrack.getLongitude()));
////        enemyAltitudeField.setText(String.valueOf(selectedTrack.getAltitude()));
////        enemyRadarIdField.setText(String.valueOf(selectedTrack.getRadarId()));
//
//
////        Track track = enemyTable.getSelectionModel().getSelectedItem();
////        trackIdField.setText(String.valueOf(track.getId()));
////        latitudeChangeField.setText(String.valueOf(track.getLatitude()));
////        longitudeChangeField.setText(String.valueOf(track.getLongitude()));
////        altitudeChangeField.setText(String.valueOf(track.getAltitude()));
//    }

    @FXML
    public void initialize() {

//        Thread clock = new Thread() {
//            public void run() {
//                for (;;) {
//                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
//                    Calendar cal = Calendar.getInstance();
//
//                    second = cal.get(Calendar.SECOND);
//                    minute = cal.get(Calendar.MINUTE);
//                    hour = cal.get(Calendar.HOUR);
//                    //System.out.println(hour + ":" + (minute) + ":" + second);
//                    enemyTimeField.setText(hour + ":" + (minute) + ":" + second);
//
//                    try {
//                        sleep(1000);
//                    } catch (InterruptedException ex) {
//                        //...
//                    }
//                }
//            }
//        };
//        clock.start();
//        enemyRadarIdField.setText(deviceIdField.getText());

        militarySymbolColumn.setCellValueFactory(new PropertyValueFactory<Track, String>("militarySymbol"));
        pV1Column.setCellValueFactory(new PropertyValueFactory<Track, Double>("p_v1"));
        pV2Column.setCellValueFactory(new PropertyValueFactory<Track, Double>("p_v2"));
        radarIdColumn.setCellValueFactory(new PropertyValueFactory<Track, Integer>("radarId"));
        rcsColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("rcs"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Track, Integer>("time"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Track, Integer>("type"));
        v1Column.setCellValueFactory(new PropertyValueFactory<Track, Double>("v1"));
        v2Column.setCellValueFactory(new PropertyValueFactory<Track, Double>("v2"));
        enemyLongitudeColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("longitude"));
        enemyIdColumn.setCellValueFactory(new PropertyValueFactory<Track, Integer>("id"));
        enemyLatitudeColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("latitude"));
//        speedColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("speed"));
        enemyAltitudeColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("altitude"));


    }


    ///////////////////////////////////////////////


    public ObservableList<Track> getTracksList() {
        return tracksList;
    }

    public void setTracksList(ObservableList<Track> tracksList) {
        this.tracksList = tracksList;
    }

    public UDPSender getUdpSender() {
        return udpSender;
    }

    public void setUdpSender(UDPSender udpSender) {
        this.udpSender = udpSender;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public TextField getEnemyTimeField() {
        return enemyTimeField;
    }

    public void setEnemyTimeField(TextField enemyTimeField) {
        this.enemyTimeField = enemyTimeField;
    }

    public TextField getEnemySpeedField() {
        return enemySpeedField;
    }

    public void setEnemySpeedField(TextField enemySpeedField) {
        this.enemySpeedField = enemySpeedField;
    }

    public TableView<Track> getEnemyTable() {
        return enemyTable;
    }

    public void setEnemyTable(TableView<Track> enemyTable) {
        this.enemyTable = enemyTable;
    }

    public TableColumn<Track, String> getMilitarySymbolColumn() {
        return militarySymbolColumn;
    }

    public void setMilitarySymbolColumn(TableColumn<Track, String> militarySymbolColumn) {
        this.militarySymbolColumn = militarySymbolColumn;
    }

    public Button getDeleteEnemy() {
        return deleteEnemy;
    }

    public void setDeleteEnemy(Button deleteEnemy) {
        this.deleteEnemy = deleteEnemy;
    }

    public TableColumn<Track, Double> getpV1Column() {
        return pV1Column;
    }

    public void setpV1Column(TableColumn<Track, Double> pV1Column) {
        this.pV1Column = pV1Column;
    }

    public TableColumn<Track, Double> getpV2Column() {
        return pV2Column;
    }

    public void setpV2Column(TableColumn<Track, Double> pV2Column) {
        this.pV2Column = pV2Column;
    }

    public TableColumn<Track, Integer> getRadarIdColumn() {
        return radarIdColumn;
    }

    public void setRadarIdColumn(TableColumn<Track, Integer> radarIdColumn) {
        this.radarIdColumn = radarIdColumn;
    }

    public TableColumn<Track, Double> getRcsColumn() {
        return rcsColumn;
    }

    public void setRcsColumn(TableColumn<Track, Double> rcsColumn) {
        this.rcsColumn = rcsColumn;
    }

    public TableColumn<Track, Integer> getTimeColumn() {
        return timeColumn;
    }

    public void setTimeColumn(TableColumn<Track, Integer> timeColumn) {
        this.timeColumn = timeColumn;
    }

    public TableColumn<Track, Integer> getTypeColumn() {
        return typeColumn;
    }

    public void setTypeColumn(TableColumn<Track, Integer> typeColumn) {
        this.typeColumn = typeColumn;
    }

    public TableColumn<Track, Double> getV1Column() {
        return v1Column;
    }

    public void setV1Column(TableColumn<Track, Double> v1Column) {
        this.v1Column = v1Column;
    }

    public TableColumn<Track, Double> getV2Column() {
        return v2Column;
    }

    public void setV2Column(TableColumn<Track, Double> v2Column) {
        this.v2Column = v2Column;
    }

    public TableColumn<Track, Double> getEnemyLongitudeColumn() {
        return enemyLongitudeColumn;
    }

    public void setEnemyLongitudeColumn(TableColumn<Track, Double> enemyLongitudeColumn) {
        this.enemyLongitudeColumn = enemyLongitudeColumn;
    }

    public TableColumn<Track, Integer> getEnemyIdColumn() {
        return enemyIdColumn;
    }

    public void setEnemyIdColumn(TableColumn<Track, Integer> enemyIdColumn) {
        this.enemyIdColumn = enemyIdColumn;
    }

    public TableColumn<Track, Double> getEnemyLatitudeColumn() {
        return enemyLatitudeColumn;
    }

    public void setEnemyLatitudeColumn(TableColumn<Track, Double> enemyLatitudeColumn) {
        this.enemyLatitudeColumn = enemyLatitudeColumn;
    }

    public TableColumn<Track, Double> getEnemyAltitudeColumn() {
        return enemyAltitudeColumn;
    }

    public void setEnemyAltitudeColumn(TableColumn<Track, Double> enemyAltitudeColumn) {
        this.enemyAltitudeColumn = enemyAltitudeColumn;
    }

    public TableColumn<Track, Double> getSpeedColumn() {
        return speedColumn;
    }

    public void setSpeedColumn(TableColumn<Track, Double> speedColumn) {
        this.speedColumn = speedColumn;
    }

    public Label getDeviceAltitude() {
        return deviceAltitude;
    }

    public void setDeviceAltitude(Label deviceAltitude) {
        this.deviceAltitude = deviceAltitude;
    }

    public Label getDeviceAltitude1() {
        return deviceAltitude1;
    }

    public void setDeviceAltitude1(Label deviceAltitude1) {
        this.deviceAltitude1 = deviceAltitude1;
    }

    public TextField getDeviceAltitudeField() {
        return deviceAltitudeField;
    }

    public void setDeviceAltitudeField(TextField deviceAltitudeField) {
        this.deviceAltitudeField = deviceAltitudeField;
    }

    public Label getDeviceAltitudeLable() {
        return deviceAltitudeLable;
    }

    public void setDeviceAltitudeLable(Label deviceAltitudeLable) {
        this.deviceAltitudeLable = deviceAltitudeLable;
    }

    public Label getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Label deviceId) {
        this.deviceId = deviceId;
    }

    public Label getDeviceId1() {
        return deviceId1;
    }

    public void setDeviceId1(Label deviceId1) {
        this.deviceId1 = deviceId1;
    }

    public TextField getDeviceIdField() {
        return deviceIdField;
    }

    public void setDeviceIdField(TextField deviceIdField) {
        this.deviceIdField = deviceIdField;
    }

    public Label getDeviceIdLable() {
        return deviceIdLable;
    }

    public void setDeviceIdLable(Label deviceIdLable) {
        this.deviceIdLable = deviceIdLable;
    }

    public Label getDeviceLatitude() {
        return deviceLatitude;
    }

    public void setDeviceLatitude(Label deviceLatitude) {
        this.deviceLatitude = deviceLatitude;
    }

    public Label getDeviceLatitude1() {
        return deviceLatitude1;
    }

    public void setDeviceLatitude1(Label deviceLatitude1) {
        this.deviceLatitude1 = deviceLatitude1;
    }

    public TextField getDeviceLatitudeField() {
        return deviceLatitudeField;
    }

    public void setDeviceLatitudeField(TextField deviceLatitudeField) {
        this.deviceLatitudeField = deviceLatitudeField;
    }

    public Label getDeviceLatitudeLable() {
        return deviceLatitudeLable;
    }

    public void setDeviceLatitudeLable(Label deviceLatitudeLable) {
        this.deviceLatitudeLable = deviceLatitudeLable;
    }

    public Label getDeviceLongitude() {
        return deviceLongitude;
    }

    public void setDeviceLongitude(Label deviceLongitude) {
        this.deviceLongitude = deviceLongitude;
    }

    public Label getDeviceLongitude1() {
        return deviceLongitude1;
    }

    public void setDeviceLongitude1(Label deviceLongitude1) {
        this.deviceLongitude1 = deviceLongitude1;
    }

    public TextField getDeviceLongitudeField() {
        return deviceLongitudeField;
    }

    public void setDeviceLongitudeField(TextField deviceLongitudeField) {
        this.deviceLongitudeField = deviceLongitudeField;
    }

    public Label getDeviceLongitudeLable() {
        return deviceLongitudeLable;
    }

    public void setDeviceLongitudeLable(Label deviceLongitudeLable) {
        this.deviceLongitudeLable = deviceLongitudeLable;
    }

    public Label getEnemyAltitude1() {
        return enemyAltitude1;
    }

    public void setEnemyAltitude1(Label enemyAltitude1) {
        this.enemyAltitude1 = enemyAltitude1;
    }

    public TextField getEnemyAltitudeField() {
        return enemyAltitudeField;
    }

    public void setEnemyAltitudeField(TextField enemyAltitudeField) {
        this.enemyAltitudeField = enemyAltitudeField;
    }

    public Label getEnemyAltitudeLable() {
        return enemyAltitudeLable;
    }

    public void setEnemyAltitudeLable(Label enemyAltitudeLable) {
        this.enemyAltitudeLable = enemyAltitudeLable;
    }

    public Label getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(Label enemyId) {
        this.enemyId = enemyId;
    }

    public Label getEnemyId1() {
        return enemyId1;
    }

    public void setEnemyId1(Label enemyId1) {
        this.enemyId1 = enemyId1;
    }

    public TextField getEnemyIdField() {
        return enemyIdField;
    }

    public void setEnemyIdField(TextField enemyIdField) {
        this.enemyIdField = enemyIdField;
    }

    public Label getEnemyIdLable() {
        return enemyIdLable;
    }

    public void setEnemyIdLable(Label enemyIdLable) {
        this.enemyIdLable = enemyIdLable;
    }

    public Label getEnemyLatitude() {
        return enemyLatitude;
    }

    public void setEnemyLatitude(Label enemyLatitude) {
        this.enemyLatitude = enemyLatitude;
    }

    public Label getEnemyLatitude1() {
        return enemyLatitude1;
    }

    public void setEnemyLatitude1(Label enemyLatitude1) {
        this.enemyLatitude1 = enemyLatitude1;
    }

    public TextField getEnemyLatitudeField() {
        return enemyLatitudeField;
    }

    public void setEnemyLatitudeField(TextField enemyLatitudeField) {
        this.enemyLatitudeField = enemyLatitudeField;
    }

    public Label getEnemyLatitudeLable() {
        return enemyLatitudeLable;
    }

    public void setEnemyLatitudeLable(Label enemyLatitudeLable) {
        this.enemyLatitudeLable = enemyLatitudeLable;
    }

    public Label getEnemyLongitude() {
        return enemyLongitude;
    }

    public void setEnemyLongitude(Label enemyLongitude) {
        this.enemyLongitude = enemyLongitude;
    }

    public Label getEnemyLongitude1() {
        return enemyLongitude1;
    }

    public void setEnemyLongitude1(Label enemyLongitude1) {
        this.enemyLongitude1 = enemyLongitude1;
    }

    public TextField getEnemyLongitudeField() {
        return enemyLongitudeField;
    }

    public void setEnemyLongitudeField(TextField enemyLongitudeField) {
        this.enemyLongitudeField = enemyLongitudeField;
    }

    public Label getEnemyLongitudeLable() {
        return enemyLongitudeLable;
    }

    public void setEnemyLongitudeLable(Label enemyLongitudeLable) {
        this.enemyLongitudeLable = enemyLongitudeLable;
    }

    public TextField getEnemyMilitarySymbolField() {
        return enemyMilitarySymbolField;
    }

    public void setEnemyMilitarySymbolField(TextField enemyMilitarySymbolField) {
        this.enemyMilitarySymbolField = enemyMilitarySymbolField;
    }

    public TextField getEnemyRadarIdField() {
        return enemyRadarIdField;
    }

    public void setEnemyRadarIdField(TextField enemyRadarIdField) {
        this.enemyRadarIdField = enemyRadarIdField;
    }

    public TextField getEnemyV1Field() {
        return enemyV1Field;
    }

    public void setEnemyV1Field(TextField enemyV1Field) {
        this.enemyV1Field = enemyV1Field;
    }

    public TextField getEnemyV2Field() {
        return enemyV2Field;
    }

    public void setEnemyV2Field(TextField enemyV2Field) {
        this.enemyV2Field = enemyV2Field;
    }

    public TextField getEnemyPv1Field() {
        return enemyPv1Field;
    }

    public void setEnemyPv1Field(TextField enemyPv1Field) {
        this.enemyPv1Field = enemyPv1Field;
    }

    public TextField getEnemyPv2Field() {
        return enemyPv2Field;
    }

    public void setEnemyPv2Field(TextField enemyPv2Field) {
        this.enemyPv2Field = enemyPv2Field;
    }

    public TextField getEnemyRcsField() {
        return enemyRcsField;
    }

    public void setEnemyRcsField(TextField enemyRcsField) {
        this.enemyRcsField = enemyRcsField;
    }

    public TextField getEnemyTypeField() {
        return enemyTypeField;
    }

    public void setEnemyTypeField(TextField enemyTypeField) {
        this.enemyTypeField = enemyTypeField;
    }

    public TextField getIpAddressField() {
        return ipAddressField;
    }

    public void setIpAddressField(TextField ipAddressField) {
        this.ipAddressField = ipAddressField;
    }

    public int getPortField() {
        return Integer.parseInt(String.valueOf(portField));
    }

    public void setPortField(TextField portField) {
        this.portField = portField;
    }

    public Label getIpAddressLabel() {
        return ipAddressLabel;
    }

    public void setIpAddressLabel(Label ipAddressLabel) {
        this.ipAddressLabel = ipAddressLabel;
    }

    public Label getPortLabel() {
        return portLabel;
    }

    public void setPortLabel(Label portLabel) {
        this.portLabel = portLabel;
    }

    public Button getSendDevice() {
        return sendDevice;
    }

    public void setSendDevice(Button sendDevice) {
        this.sendDevice = sendDevice;
    }

    public Button getSendEnemy() {
        return sendEnemy;
    }

    public void setSendEnemy(Button sendEnemy) {
        this.sendEnemy = sendEnemy;
    }

    public Label getEnemySpeed() {
        return enemySpeed;
    }

    public void setEnemySpeed(Label enemySpeed) {
        this.enemySpeed = enemySpeed;
    }
}
