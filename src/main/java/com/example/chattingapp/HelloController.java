package com.example.chattingapp;

import javafx.collections.FXCollections;
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
import java.util.*;

public class HelloController {

    ObservableList<Track> tracksList;
    UDPSender udpSender = new UDPSender();
    private int minute;
    private int hour;
    private int second;
    static boolean latitudeTracker;
    static boolean longitudeTracker;
    static boolean altitudeTracker;
    static boolean trackExists;
    String trackTime;
    @FXML
    private Button AutoNewTrackSender;
    @FXML
    private TextField trackFrequencyField;
    @FXML
    private TextField altitudeChangeField;
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
    private TextField latitudeChangeField;
    @FXML
    private TextField longitudeChangeField;
    @FXML
    private Button sendConnectionProperties;
    @FXML
    private Button sendEnemiesTrackButton;
    @FXML
    private TableView<Track> enemyTable;
    @FXML
    TableColumn<Track, Double> speedColumn;
    @FXML
    private TableColumn<Track, String> militarySymbolColumn;
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
    private TableColumn<Track, Double> enemyLongitudeColumn, endLongitudeColumn, changeInLongitudeColumn;
    @FXML
    private TableColumn<Track, Integer> enemyIdColumn;
    @FXML
    private TableColumn<Track, Double> enemyLatitudeColumn, endLatitudeColumn, changeInLatitudeColumn;
    @FXML
    private TableColumn<Track, Double> enemyAltitudeColumn, endAltitudeColumn, changeInAltitudeColumn;
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
    private Label trackV2Lable, trackPv2Lable, trackPv1Lable, trackV1Lable, trackRcsLabel, trackTypeLabel;
    @FXML
    private Label trackRadarIdLabel;
    @FXML
    private Label trackMilitarySymbolLabel;
    @FXML
    private TextField ipAddressField;
    @FXML
    private TextField portField;
    @FXML
    private Label ipAddressLabel;
    @FXML
    private Label portLabel;
    @FXML
    private Label deviceStatusLabel;
    @FXML
    private Button sendDevice;

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
    void AutoNewTrackSenderButton(ActionEvent event) {
        Track track = new Track();

        track = this.getTrackFromUi();
//
//        // 1 minute in milliseconds
//        trackTime = this.getLocalTime();
//
//        Track tracker = new Track(Integer.parseInt(autoTrackIdField.getText()), trackTime, latitude, longitude, altitude
//                , trackMilitarySymbolLabel.getText(), Integer.parseInt(trackRadarIdLabel.getText()), Double.parseDouble(trackV1Lable.getText())
//                , Double.parseDouble(trackV2Lable.getText()), Double.parseDouble(trackPv1Lable.getText()), Double.parseDouble(trackPv2Lable.getText())
//                , Double.parseDouble(trackRcsLabel.getText()), Integer.parseInt(trackTypeLabel.getText()), Double.parseDouble(autoTrackSpeedField.getText())
//                , endLongitude, endLatitude, endAltitude, changeLatitude, changeLongitude, changeAltitude);

        this.updateTable(track);

    }
    @FXML
    void startSimulationButton(ActionEvent event) throws IOException {

        latitudeTracker = false;
        longitudeTracker = false;
        altitudeTracker = false;

        List<Track> OneStepTrackingList = new ArrayList<>();

        // this counter to now the #round on the loop
        int loopCpunter = 0;

        while (!(latitudeTracker && longitudeTracker && altitudeTracker)) {

            tracksList = enemyTable.getItems();

            for(int rowIndex = 0; rowIndex < tracksList.size(); rowIndex++){

                Track track = tracksList.get(rowIndex);

                this.getTrackFromTable(track);

                System.out.println(("Row Index : " + rowIndex));
                System.out.println("loop Counter: "+loopCpunter);

                // Perform loop operations here
                if(loopCpunter == 0){
                    HashMap<String,Object> resultOfLLA = new HashMap<>();
                    resultOfLLA = this.countLLA(track.getStartLatitude(),track.getEndLatitude(),track.getChangeInLatitude(),latitudeTracker);
                    track.setLatitude((Double) resultOfLLA.get("geographicCoordinatesValue"));
                    latitudeTracker = (boolean) resultOfLLA.get("breakLoopValue");
                    resultOfLLA = this.countLLA(track.getStartLongitude(), track.getEndLongitude(), track.getChangeInLongitude(),longitudeTracker);
                    track.setLatitude((Double) resultOfLLA.get("geographicCoordinatesValue"));
                    longitudeTracker = (boolean) resultOfLLA.get("breakLoopValue");
                    resultOfLLA = this.countLLA(track.getStartAltitude(), track.getEndAltitude(), track.getChangeInAltitude(),altitudeTracker);
                    track.setLatitude((Double) resultOfLLA.get("geographicCoordinatesValue"));
                    altitudeTracker = (boolean) resultOfLLA.get("breakLoopValue");
//                    track.setLatitude(this.countLLA(track.getStartLatitude(),track.getEndLatitude(),track.getChangeInLatitude(),latitudeTracker));
//                    track.setLongitude(this.countLLA(track.getStartLongitude(), track.getEndLongitude(), track.getChangeInLongitude(),longitudeTracker));
//                    track.setAltitude(this.countLLA(track.getStartAltitude(), track.getEndAltitude(), track.getChangeInAltitude(),altitudeTracker));
                }else{
                    HashMap<String,Object> resultOfLLA = new HashMap<>();
                    resultOfLLA = this.countLLA(track.getLatitude(),track.getEndLatitude(),track.getChangeInLatitude(),latitudeTracker);
                    track.setLatitude((Double) resultOfLLA.get("geographicCoordinatesValue"));
                    latitudeTracker = (boolean) resultOfLLA.get("breakLoopValue");
                    resultOfLLA = this.countLLA(track.getLongitude(), track.getEndLongitude(), track.getChangeInLongitude(),longitudeTracker);
                    track.setLatitude((Double) resultOfLLA.get("geographicCoordinatesValue"));
                    longitudeTracker = (boolean) resultOfLLA.get("breakLoopValue");
                    resultOfLLA = this.countLLA(track.getAltitude(), track.getEndAltitude(), track.getChangeInAltitude(),altitudeTracker);
                    track.setLatitude((Double) resultOfLLA.get("geographicCoordinatesValue"));
                    altitudeTracker = (boolean) resultOfLLA.get("breakLoopValue");
//                    track.setLatitude(this.countLLA(track.getLatitude(),track.getEndLatitude(),track.getChangeInLatitude(),latitudeTracker));
//                    track.setLongitude(this.countLLA(track.getLongitude(), track.getEndLongitude(), track.getChangeInLongitude(),longitudeTracker));
//                    track.setAltitude(this.countLLA(track.getAltitude(), track.getEndAltitude(), track.getChangeInAltitude(),altitudeTracker));
                }

                // to brake the while loop
//                this.breakWhileLoop(track);

                // Calculate track time
                trackTime = this.getLocalTime();
                track.setTime(trackTime);

                // Check the track id on the list or not, will update if  exists, will add new if not
                trackExists = false;
                for(int i = 0; i < OneStepTrackingList.size(); i++){
                    Track ExistingTrack = OneStepTrackingList.get(i);
                    if(ExistingTrack.getId() == track.getId()){
                        OneStepTrackingList.remove(i);
                        OneStepTrackingList.add(track);
                        trackExists = true;
                        break;
                    }
                }
                if(!trackExists){
                    OneStepTrackingList.add(track);
                }

                // Delay for TrackFrequency second
                try {
                    Thread.sleep(track.getTrackFrequency()*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            // send the track list
            udpSender.sendData(OneStepTrackingList);
            System.out.println(OneStepTrackingList);
            loopCpunter++;
        }


    }

    @FXML
    void sendConnectionProperties(ActionEvent event) throws UnknownHostException {
        String ipAddress = ipAddressField.getText();
        int port = Integer.parseInt(portField.getText());

        ipAddressLabel.setText(ipAddress);
        portLabel.setText(String.valueOf(port));

        udpSender.setPort(Integer.parseInt(String.valueOf(port)));
        udpSender.setIp_Address(ipAddress);

    }
    @FXML
    void sendDeviceButton(ActionEvent event) throws IOException {
        // get the radar from the UI
        String radarId = deviceIdField.getText();
        String radarLatitude = deviceLatitudeField.getText();
        String radarLongitude = deviceLongitudeField.getText();
        String radarAltitude = deviceAltitudeField.getText();

        Device radar = new Device();
        radar.setId(Integer.parseInt(deviceIdField.getText()));
        radar.setLatitude(Double.parseDouble(deviceLatitudeField.getText()));
        radar.setLongitude(Double.parseDouble(deviceLongitudeField.getText()));
        radar.setAltitude(Double.parseDouble(deviceAltitudeField.getText()));

        // print the radar information on radar labels
        deviceIdLable.setText(radarId);
        deviceLatitudeLable.setText(radarLatitude);
        deviceLongitudeLable.setText(radarLongitude);
        deviceAltitudeLable.setText(radarAltitude);

        // to send the radar information
        udpSender.sendData(radar);

    }
    @FXML
    public void initialize() {

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
        speedColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("speed"));
        enemyAltitudeColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("altitude"));
        endLatitudeColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("endLatitude"));
        endLongitudeColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("endLongitude"));
        endAltitudeColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("endAltitude"));
        changeInLatitudeColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("changeInLatitude"));
        changeInLongitudeColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("changeInLongitude"));
        changeInAltitudeColumn.setCellValueFactory(new PropertyValueFactory<Track, Double>("changeInAltitude"));

    }
    private String getLocalTime(){

        Calendar cal = Calendar.getInstance();
        second = cal.get(Calendar.SECOND);
        minute = cal.get(Calendar.MINUTE);
        hour = cal.get(Calendar.HOUR);

        String localTime = hour + ":" + (minute) + ":" + second;

        return localTime;

    }
    private HashMap<String,Object> countLLA(Double startLLA, Double endLLA,Double changeInLLA, boolean breakLoop){

//        HashMap<String, Object> geographicCoordinatesContinarMap = new HashMap<>();
//        HashMap<String, Double> geographicCoordinatesValueMap = new HashMap<>();
//        HashMap<String, Boolean> breakingLoopMap = new HashMap<>();
        HashMap<String, Object> geographicCoordinatesContinarMap =new HashMap<>();

        if(!breakLoop){
            if(startLLA < endLLA){
                startLLA += changeInLLA;
                if(startLLA >= endLLA)
                    breakLoop = true;

            }else{
                startLLA -= changeInLLA;
                if(startLLA <= endLLA)
                    breakLoop = true;
            }
        }
//        geographicCoordinatesValueMap.put("valueOfLLA",startLLA);
//        breakingLoopMap.put("breakingLoop",breakLoop);
        geographicCoordinatesContinarMap.put("geographicCoordinatesValue",startLLA);
        geographicCoordinatesContinarMap.put("breakLoopValue",breakLoop);
        return geographicCoordinatesContinarMap;
    }
    private void getTrackFromTable(Track track){

        track.setId(Integer.parseInt(String.valueOf(enemyIdColumn.getCellData(track))));
        track.setSpeed(Double.parseDouble(String.valueOf(speedColumn.getCellData(track))));
        track.setStartLatitude(Double.parseDouble(String.valueOf(enemyLatitudeColumn.getCellData(track))));
        track.setStartLongitude(Double.parseDouble(String.valueOf(enemyLongitudeColumn.getCellData(track))));
        track.setStartAltitude(Double.parseDouble(String.valueOf(enemyAltitudeColumn.getCellData(track))));
        track.setEndLatitude(Double.parseDouble(String.valueOf(endLatitudeColumn.getCellData(track))));
        track.setEndLongitude(Double.parseDouble(String.valueOf(endLongitudeColumn.getCellData(track))));
        track.setEndAltitude(Double.parseDouble(String.valueOf(endAltitudeColumn.getCellData(track))));
        track.setChangeInLatitude(Double.parseDouble(String.valueOf(changeInLatitudeColumn.getCellData(track))));
        track.setChangeInLongitude(Double.parseDouble(String.valueOf(changeInLongitudeColumn.getCellData(track))));
        track.setChangeInAltitude(Double.parseDouble(String.valueOf(changeInAltitudeColumn.getCellData(track))));
        track.setTrackFrequency(Long.parseLong(trackFrequencyField.getText()));

    }
    private Track getTrackFromUi(){

        Track track = new Track();
        track.setId(Integer.parseInt(autoTrackIdField.getText()));
        track.setSpeed(Double.parseDouble(autoTrackSpeedField.getText()));
        track.setTime(this.getLocalTime());
        track.setLatitude(Double.parseDouble(autoTrackStartLatitudeField.getText()));
        track.setLongitude(Double.parseDouble(autoTrackStartLongitudeField.getText()));
        track.setAltitude(Double.parseDouble(autoTrackStartAltitudeField.getText()));
        track.setStartLatitude(Double.parseDouble(autoTrackStartLatitudeField.getText()));
        track.setStartLongitude(Double.parseDouble(autoTrackStartLongitudeField.getText()));
        track.setStartAltitude(Double.parseDouble(autoTrackStartAltitudeField.getText()));
        track.setEndLatitude(Double.parseDouble(autoTrackEndLatitudeField.getText()));
        track.setEndLongitude(Double.parseDouble(autoTrackEndLongitudeField.getText()));
        track.setEndAltitude(Double.parseDouble(autoTrackEndAltitudeField.getText()));
        track.setChangeInLatitude(Double.parseDouble(latitudeChangeField.getText()));
        track.setChangeInLongitude(Double.parseDouble(longitudeChangeField.getText()));
        track.setChangeInAltitude(Double.parseDouble(altitudeChangeField.getText()));

        return track;

    }
    private void updateTable(Track track){
        tracksList = enemyTable.getItems();
        tracksList.add(track);
        enemyTable.setItems(tracksList);
    }
    private void breakWhileLoop(Track track){
        if(track.getLatitude() == track.getEndLatitude()){
            latitudeTracker = true;
        }
        if(track.getLongitude() == track.getEndLongitude()){
            longitudeTracker = true;
        }
        if(track.getAltitude() == track.getEndAltitude()){
            altitudeTracker = true;
        }
    }
}