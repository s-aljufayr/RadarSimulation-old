package com.example.chattingapp.Infantry;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TrackControlController {
    ObservableList<TrackModel> tracksList;
    UDPSender udpSender = new UDPSender();
    UDPReceiver udpReceiver = new UDPReceiver();
    RadarModel radar = new RadarModel();
    double maxTrackTime = 0;
    private int minute;
    private int hour;
    private int second;
    static boolean isReachEndLatitude;
    static boolean isReachEndLongitude;
    static boolean isReachEndAltitude;
    static boolean isTrackExists;
    String trackTime;
    TrackModel track = new TrackModel();
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
    private TextField trackTimeField;
    @FXML
    private TableView<TrackModel> trackTable;
    @FXML
    TableColumn<TrackModel, Double> speedColumn;
    @FXML
    private TableColumn<TrackModel, String> militarySymbolColumn;
    @FXML
    private TableColumn<TrackModel, Double> pV1Column;
    @FXML
    private TableColumn<TrackModel, Double> pV2Column;
    @FXML
    private TableColumn<TrackModel, Integer> radarIdColumn;
    @FXML
    private TableColumn<TrackModel, Double> rcsColumn;
    @FXML
    private TableColumn<TrackModel, Double> timeColumn;
    @FXML
    private TableColumn<TrackModel, Integer> typeColumn;
    @FXML
    private TableColumn<TrackModel, Double> v1Column;
    @FXML
    private TableColumn<TrackModel, Double> v2Column;
    @FXML
    private TableColumn<TrackModel, Double> enemyLongitudeColumn, endLongitudeColumn, changeInLongitudeColumn;
    @FXML
    private TableColumn<TrackModel, Integer> enemyIdColumn;
    @FXML
    private TableColumn<TrackModel, Double> startAltitudeColumn, startLatitudeColumn, startLongitudeColumn;
    @FXML
    private TableColumn<TrackModel, Double> enemyLatitudeColumn, endLatitudeColumn, changeInLatitudeColumn;
    @FXML
    private TableColumn<TrackModel, Double> enemyAltitudeColumn, endAltitudeColumn, changeInAltitudeColumn;
    public TrackControlController() throws UnknownHostException {
    }
    @FXML
    void deleteTrackButton(ActionEvent event) {
        int selectedEnemy = trackTable.getSelectionModel().getSelectedIndex();
        trackTable.getItems().remove(selectedEnemy);
    }
    @FXML
    void resetSimulationButton(ActionEvent event) {
        trackTable.getItems().clear();
    }
    @FXML
    void newTrackButton(ActionEvent event) {
        TrackModel track = this.getTrackFromUi();
        this.updateTable(track);
    }
    @FXML
    void startSimulationButton(ActionEvent event) throws IOException {
//        TrackModel track = new TrackModel();
        List<TrackModel> tracksListAfterMovement = new ArrayList<>();
        // this counter to now the #round on the loop
        int firstRound = 0;
        track.setReachEndLatitude(false);
        track.setReachEndLongitude(false);
        track.setReachEndAltitude(false);
        isReachEndLatitude = false;
        isReachEndLongitude = false;
        isReachEndAltitude = false;
        //!(track.isReachEndLatitude() && track.isReachEndLongitude() && track.isReachEndAltitude())
        while (firstRound <= maxTrackTime) {

            tracksList = trackTable.getItems();

            for(int rowIndex = 0; rowIndex < tracksList.size(); rowIndex++){

                track = tracksList.get(rowIndex);

                this.getTrackFromTable(track);
                System.out.println(("Row Index : " + rowIndex));
                System.out.println("loop Counter: "+firstRound);
                
                // Perform loop operations here
                this.getChangeInLLA(track,track.getTimeFrame());
//                this.checkAllArrivedToDestination(track);
                this.calculateLLAAfterMovement(track, firstRound);
                // get track time
                track.setTime(this.getLocalTime());
                // Check the track id on the list or not, will update if  exists, will add new if not
                isTrackExists = false;
                tracksListAfterMovement = this.checkTrackIdExists(tracksListAfterMovement,track);
                if(!isTrackExists){
                    tracksListAfterMovement.add(track);
                }
            }
            // Delay for TrackFrequency second
            try {
                Thread.sleep((long) (track.getTrackFrequency()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!udpReceiver.isOnline()){
//                udpReceiver.getSocket().close();
                udpSender.getSocket().close();
                break;
            }
            firstRound++;
            if(!tracksListAfterMovement.isEmpty()){
                udpSender.sendData(tracksListAfterMovement);
                System.out.println(tracksListAfterMovement);
            }
        }
    }
    @FXML
    public void initialize() {
        enemyIdColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Integer>("id"));
        speedColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("speed"));

        enemyLatitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("latitude"));
        enemyLongitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("longitude"));
        enemyAltitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("altitude"));

        startLatitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("startLatitude"));
        startLongitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("startLongitude"));
        startAltitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("startAltitude"));

        endLatitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("endLatitude"));
        endLongitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("endLongitude"));
        endAltitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("endAltitude"));

        changeInLatitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("changeInLatitude"));
        changeInLongitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("changeInLongitude"));
        changeInAltitudeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("changeInAltitude"));

        militarySymbolColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, String>("militarySymbol"));
        pV1Column.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("p_v1"));
        pV2Column.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("p_v2"));
        radarIdColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Integer>("radarId"));
        rcsColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("rcs"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("timeFrame"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<TrackModel, Integer>("type"));
        v1Column.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("v1"));
        v2Column.setCellValueFactory(new PropertyValueFactory<TrackModel, Double>("v2"));
    }
    private Double countLLA(Double geographicCoordinates, Double endLLA,Double changeInLLA, boolean breakLoop, String key){
        if(!breakLoop) {
            if (geographicCoordinates < endLLA) {
                geographicCoordinates += changeInLLA;
                if (geographicCoordinates >= endLLA) {
                    if (key == "latitude"){
                        this.isReachEndLatitude = true;
                        this.track.setReachEndLatitude(true);
                    }
                    if (key == "longitude"){
                        this.isReachEndLongitude = true;
                        this.track.setReachEndLongitude(true);
                    }
                    if (key == "altitude"){
                        this.isReachEndAltitude = true;
                        this.track.setReachEndAltitude(true);
                    }
                }
            }
            else if(geographicCoordinates > endLLA) {
                geographicCoordinates -= changeInLLA;
                if (geographicCoordinates <= endLLA){
                    if (key == "latitude"){
                        this.isReachEndLatitude = true;
                        this.track.setReachEndLatitude(true);
                    }
                    if (key == "longitude"){
                        this.isReachEndLongitude = true;
                        this.track.setReachEndLongitude(true);
                    }
                    if (key == "altitude"){
                        this.isReachEndAltitude = true;
                        this.track.setReachEndAltitude(true);
                    }
                }
            }
            else if(geographicCoordinates.equals(endLLA)){
                if (key == "latitude"){
                    this.isReachEndLatitude = true;
                    this.track.setReachEndLatitude(true);
                }
                if (key == "longitude"){
                    this.isReachEndLongitude = true;
                    this.track.setReachEndLongitude(true);
                }
                if (key == "altitude"){
                    this.isReachEndAltitude = true;
                    this.track.setReachEndAltitude(true);
                }
            }
        }
        return geographicCoordinates;
    }
    private void getTrackFromTable(TrackModel track){
        track.setId(Integer.parseInt(String.valueOf(enemyIdColumn.getCellData(track))));
        track.setSpeed(Double.parseDouble(String.valueOf(speedColumn.getCellData(track))));
        track.setStartLatitude(Double.parseDouble(String.valueOf(startLatitudeColumn.getCellData(track))));
        track.setStartLongitude(Double.parseDouble(String.valueOf(startLongitudeColumn.getCellData(track))));
        track.setStartAltitude(Double.parseDouble(String.valueOf(startAltitudeColumn.getCellData(track))));
        track.setEndLatitude(Double.parseDouble(String.valueOf(endLatitudeColumn.getCellData(track))));
        track.setEndLongitude(Double.parseDouble(String.valueOf(endLongitudeColumn.getCellData(track))));
        track.setEndAltitude(Double.parseDouble(String.valueOf(endAltitudeColumn.getCellData(track))));
        track.setTrackFrequency(Long.parseLong(trackFrequencyField.getText()));
        track.setTimeFrame(Double.parseDouble(String.valueOf(timeColumn.getCellData(track))));
    }
    private TrackModel getTrackFromUi(){
        TrackModel track = new TrackModel();
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
        track.setTimeFrame(Double.parseDouble(trackTimeField.getText()));
        track.setRadarId(radar.getId());
        return track;
    }
    private void updateTable(TrackModel track){
        tracksList = trackTable.getItems();
        tracksList.add(track);
        trackTable.setItems(tracksList);
    }
    public boolean hasReachedEndLatitude(TrackModel track) {
        return track.getLatitude() == track.getEndLatitude()
                || Math.abs(track.getLatitude() - track.getEndLatitude()) == Math.abs(track.getChangeInLatitude());
    }
    public boolean hasReachedEndLongitude(TrackModel track) {
        return track.getLongitude() == track.getEndLongitude()
                || Math.abs(track.getLongitude() - track.getEndLongitude()) == Math.abs(track.getChangeInLongitude());
    }
    public boolean hasReachedEndAltitude(TrackModel track) {
        return track.getAltitude() == track.getEndAltitude()
                || Math.abs(track.getAltitude() - track.getEndAltitude()) == Math.abs(track.getChangeInAltitude());
    }
    private void checkAllArrivedToDestination(TrackModel track) {
        this.isReachEndLatitude = hasReachedEndLatitude(track);
        this.isReachEndLongitude = hasReachedEndLongitude(track);
        this.isReachEndAltitude = hasReachedEndAltitude(track);
    }
    private void calculateLLAAfterMovement(TrackModel track, int firstRound) {
        if(firstRound == 0){
            track.setLatitude(this.countLLA(track.getStartLatitude(),track.getEndLatitude(),track.getChangeInLatitude(),track.isReachEndLatitude(),"latitude"));
            track.setLongitude(this.countLLA(track.getStartLongitude(), track.getEndLongitude(), track.getChangeInLongitude(),track.isReachEndLongitude(), "longitude"));
            track.setAltitude(this.countLLA(track.getStartAltitude(), track.getEndAltitude(), track.getChangeInAltitude(),track.isReachEndAltitude(), "altitude"));
            this.maxTrackTime = this.maxTrackTime(tracksList);
        }else{
            track.setLatitude(this.countLLA(track.getLatitude(),track.getEndLatitude(),track.getChangeInLatitude(),track.isReachEndLatitude(),"latitude"));
            track.setLongitude(this.countLLA(track.getLongitude(), track.getEndLongitude(), track.getChangeInLongitude(),track.isReachEndLongitude(),"longitude"));
            track.setAltitude(this.countLLA(track.getAltitude(), track.getEndAltitude(), track.getChangeInAltitude(),track.isReachEndAltitude(),"altitude"));
        }
    }
    private List<TrackModel> checkTrackIdExists(List<TrackModel> trackingList, TrackModel track) {
        for(int listIndex = 0; listIndex < trackingList.size(); listIndex++){
            TrackModel ExistingTrack = trackingList.get(listIndex);
            if(ExistingTrack.getId() == track.getId()){
                trackingList.remove(listIndex);
                trackingList.add(track);
                this.isTrackExists = true;
                break;
            }
        }
        return trackingList;
    }
    private void getChangeInLLA(TrackModel track, Double trackTime) {
        track.setChangeInLatitude(Math.abs(track.getStartLatitude() - track.getEndLatitude()) / trackTime);
        track.setChangeInLongitude(Math.abs(track.getStartLongitude() - track.getEndLongitude()) / trackTime);
        track.setChangeInAltitude(Math.abs(track.getStartAltitude() - track.getEndAltitude()) / trackTime);
    }
    private double maxTrackTime(ObservableList<TrackModel> trackList) {
        double maxTrackTime = 0;
        for (int rowIndex = 0; rowIndex < trackList.size(); rowIndex++){
            double trackTime = trackList.get(rowIndex).getTimeFrame();
            if(maxTrackTime < trackTime)
                maxTrackTime = trackTime;
        }
        return maxTrackTime;
    }
    private String getLocalTime(){
        Calendar cal = Calendar.getInstance();
        second = cal.get(Calendar.SECOND);
        minute = cal.get(Calendar.MINUTE);
        hour = cal.get(Calendar.HOUR);
        String localTime = hour + ":" + (minute) + ":" + second;
        return localTime;
    }
}