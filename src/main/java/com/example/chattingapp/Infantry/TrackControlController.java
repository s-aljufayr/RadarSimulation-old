package com.example.chattingapp.Infantry;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
    ObservableList<MovementTrackModel> tracksList;
    UDPSender udpSender = new UDPSender();
    UDPReceiver udpReceiver = new UDPReceiver();
    RadarModel radar = new RadarModel();
    MovementTrackModel movementTrackModel = new MovementTrackModel();
    double maxTrackTime = 0;
    private int minute;
    private int hour;
    private int second;
    static boolean isTrackExists;
    String trackTime;
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
    private TextField trackTimeField;
    @FXML
    private TableView<MovementTrackModel> trackTable;
    @FXML
    private TableColumn<MovementTrackModel, Double> speedColumn;
    @FXML
    private TableColumn<MovementTrackModel, String> militarySymbolColumn;
    @FXML
    private TableColumn<MovementTrackModel, Double> pV1Column;
    @FXML
    private TableColumn<MovementTrackModel, Double> pV2Column;
    @FXML
    private TableColumn<MovementTrackModel, Integer> radarIdColumn;
    @FXML
    private TableColumn<MovementTrackModel, Double> rcsColumn;
    @FXML
    private TableColumn<MovementTrackModel, Integer> typeColumn;
    @FXML
    private TableColumn<MovementTrackModel, Double> v1Column;
    @FXML
    private TableColumn<MovementTrackModel, Double> v2Column;
    @FXML
    private TableColumn<MovementTrackModel, Double> enemyLongitudeColumn;
    @FXML
    private TableColumn<MovementTrackModel, Integer> enemyIdColumn;
    @FXML
    private TableColumn<MovementTrackModel, Double> enemyLatitudeColumn, enemyAltitudeColumn;

    //////////////////////////////////////////////
    @FXML
    private TableColumn<MovementTrackModel, Double> timeColumn;
    @FXML
    private TableColumn<MovementTrackModel, Double> endLongitudeColumn, changeInLongitudeColumn;
    @FXML
    private TableColumn<MovementTrackModel, Double> startAltitudeColumn, startLatitudeColumn, startLongitudeColumn;
    @FXML
    private TableColumn<MovementTrackModel, Double> endLatitudeColumn, changeInLatitudeColumn;
    @FXML
    private TableColumn<MovementTrackModel, Double>  endAltitudeColumn, changeInAltitudeColumn;
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
        MovementTrackModel track = this.getTrackFromUi();
        this.updateTable(track);
    }
    @FXML
    void startSimulationButton(ActionEvent event) throws IOException {

        List<TrackModel> trackModelListAfterMovement = new ArrayList<>();
        // this counter to now the #round on the loop
        int timer = 0;
        resetFlagOfReachedDistination();
        while (timer <= maxTrackTime) {

            tracksList = trackTable.getItems();
            for(int rowIndex = 0; rowIndex < tracksList.size(); rowIndex++){
                movementTrackModel = (MovementTrackModel) tracksList.get(rowIndex);
                this.getTrackFromTable(movementTrackModel);
                System.out.println(("Row Index : " + rowIndex));
                System.out.println("loop timer Counter: "+timer);
                movementTrackModel.getChangeInLLA(movementTrackModel, movementTrackModel.getTimeFrame());
                if(timer == 0 && rowIndex == 0)
                    maxTrackTime = this.maxTrackTime(tracksList);
                movementTrackModel.calculateLLAAfterMovement(movementTrackModel, timer);
                movementTrackModel.getTrackModel().setTime(this.getLocalTime());
                isTrackExists = false;
                trackModelListAfterMovement = this.checkTrackIdExists(trackModelListAfterMovement,(TrackModel) movementTrackModel.trackModel);
                if(!isTrackExists){
                    trackModelListAfterMovement.add(movementTrackModel.trackModel);
                }
            }
            try {
                Thread.sleep((long) (1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!udpReceiver.isOnline()){
                udpSender.getSocket().close();
                break;
            }
            timer++;
            if(!trackModelListAfterMovement.isEmpty()){
                udpSender.sendData(trackModelListAfterMovement);
                System.out.println(trackModelListAfterMovement);
            }
        }
    }
    @FXML
    public void initialize() {
        enemyIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTrackModel().getId()).asObject());
        speedColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTrackModel().getSpeed()).asObject());
        enemyLatitudeColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTrackModel().getLatitude()).asObject());
        enemyLongitudeColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTrackModel().getLongitude()).asObject());
        enemyAltitudeColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTrackModel().getAltitude()).asObject());
        militarySymbolColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTrackModel().getMilitarySymbol()));
        v1Column.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTrackModel().getV1()).asObject());
        v2Column.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTrackModel().getV2()).asObject());
        pV1Column.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTrackModel().getP_v1()).asObject());
        pV2Column.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTrackModel().getP_v2()).asObject());
        rcsColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTrackModel().getRcs()).asObject());
        radarIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTrackModel().getRadarId()).asObject());
        typeColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTrackModel().getType()).asObject());
        timeColumn.setCellValueFactory(new PropertyValueFactory<MovementTrackModel, Double>("timeFrame"));

        startLatitudeColumn.setCellValueFactory(new PropertyValueFactory<MovementTrackModel, Double>("startLatitude"));
        startLongitudeColumn.setCellValueFactory(new PropertyValueFactory<MovementTrackModel, Double>("startLongitude"));
        startAltitudeColumn.setCellValueFactory(new PropertyValueFactory<MovementTrackModel, Double>("startAltitude"));

        endLatitudeColumn.setCellValueFactory(new PropertyValueFactory<MovementTrackModel, Double>("endLatitude"));
        endLongitudeColumn.setCellValueFactory(new PropertyValueFactory<MovementTrackModel, Double>("endLongitude"));
        endAltitudeColumn.setCellValueFactory(new PropertyValueFactory<MovementTrackModel, Double>("endAltitude"));

        changeInLatitudeColumn.setCellValueFactory(new PropertyValueFactory<MovementTrackModel, Double>("changeInLatitude"));
        changeInLongitudeColumn.setCellValueFactory(new PropertyValueFactory<MovementTrackModel, Double>("changeInLongitude"));
        changeInAltitudeColumn.setCellValueFactory(new PropertyValueFactory<MovementTrackModel, Double>("changeInAltitude"));

    }
    private void resetFlagOfReachedDistination(){
         this.movementTrackModel.setReachEndLatitude(false);
         this.movementTrackModel.setReachEndLongitude(false);
         this.movementTrackModel.setReachEndAltitude(false);
    }
    private void getTrackFromTable(MovementTrackModel track){
        track.getTrackModel().setId(Integer.parseInt(String.valueOf(enemyIdColumn.getCellData(track))));
        track.getTrackModel().setSpeed(Double.parseDouble(String.valueOf(speedColumn.getCellData(track))));
        track.setStartLatitude(Double.parseDouble(String.valueOf(startLatitudeColumn.getCellData(track))));
        track.setStartLongitude(Double.parseDouble(String.valueOf(startLongitudeColumn.getCellData(track))));
        track.setStartAltitude(Double.parseDouble(String.valueOf(startAltitudeColumn.getCellData(track))));
        track.setEndLatitude(Double.parseDouble(String.valueOf(endLatitudeColumn.getCellData(track))));
        track.setEndLongitude(Double.parseDouble(String.valueOf(endLongitudeColumn.getCellData(track))));
        track.setEndAltitude(Double.parseDouble(String.valueOf(endAltitudeColumn.getCellData(track))));
        track.setTimeFrame(Double.parseDouble(String.valueOf(timeColumn.getCellData(track))));
    }    private MovementTrackModel getTrackFromUi(){
        MovementTrackModel movementTrackModel = new MovementTrackModel();
        TrackModel track = new TrackModel();
        track.setId(Integer.parseInt(autoTrackIdField.getText()));
        track.setSpeed(Double.parseDouble(autoTrackSpeedField.getText()));
        track.setTime(this.getLocalTime());
        track.setLatitude(Double.parseDouble(autoTrackStartLatitudeField.getText()));
        track.setLongitude(Double.parseDouble(autoTrackStartLongitudeField.getText()));
        track.setAltitude(Double.parseDouble(autoTrackStartAltitudeField.getText()));
        track.setRadarId(radar.getId());
        movementTrackModel.setTrackModel(track);
        movementTrackModel.setStartLatitude(Double.parseDouble(autoTrackStartLatitudeField.getText()));
        movementTrackModel.setStartLongitude(Double.parseDouble(autoTrackStartLongitudeField.getText()));
        movementTrackModel.setStartAltitude(Double.parseDouble(autoTrackStartAltitudeField.getText()));
        movementTrackModel.setEndLatitude(Double.parseDouble(autoTrackEndLatitudeField.getText()));
        movementTrackModel.setEndLongitude(Double.parseDouble(autoTrackEndLongitudeField.getText()));
        movementTrackModel.setEndAltitude(Double.parseDouble(autoTrackEndAltitudeField.getText()));
        movementTrackModel.setTimeFrame(Double.parseDouble(trackTimeField.getText()));
        return movementTrackModel;
    }
    private void updateTable(MovementTrackModel track){
        tracksList = trackTable.getItems();
        tracksList.add(track);
        trackTable.setItems(tracksList);
    }
    private String getLocalTime(){
        Calendar cal = Calendar.getInstance();
        second = cal.get(Calendar.SECOND);
        minute = cal.get(Calendar.MINUTE);
        hour = cal.get(Calendar.HOUR);
        String localTime = hour + ":" + (minute) + ":" + second;
        return localTime;
    }
    private List<TrackModel> checkTrackIdExists(List<TrackModel> trackingList, TrackModel track) {
        int trackId = track.getId();
        for(int listIndex = 0; listIndex < trackingList.size(); listIndex++){
            Integer existingTrack = trackingList.get(listIndex).getId();
            if(existingTrack.equals(trackId)){
                trackingList.remove(listIndex);
                trackingList.add(track);
                this.isTrackExists = true;
                break;
            }
        }
        return trackingList;
    }
    public double maxTrackTime(ObservableList<MovementTrackModel> trackList) {
        double maxTrackTime = 0;
        for (int rowIndex = 0; rowIndex < trackList.size(); rowIndex++){
            double trackTime = trackList.get(rowIndex).getTimeFrame();
            if(maxTrackTime < trackTime)
                maxTrackTime = trackTime;
        }
        return maxTrackTime;
    }
    private Double countLLA(Double geographicCoordinates, Double endLLA,Double changeInLLA, boolean breakLoop, String key){
        if(!breakLoop) {
            if (geographicCoordinates < endLLA) {
                geographicCoordinates += changeInLLA;
                if (geographicCoordinates >= endLLA) {
                    if (key == "latitude"){
                        this.movementTrackModel.setReachEndLatitude(true);
                    }
                    if (key == "longitude"){
                        this.movementTrackModel.setReachEndLongitude(true);
                    }
                    if (key == "altitude"){
                        this.movementTrackModel.setReachEndAltitude(true);
                    }
                }
            }
            else if(geographicCoordinates > endLLA) {
                geographicCoordinates -= changeInLLA;
                if (geographicCoordinates <= endLLA){
                    if (key == "latitude"){
                        this.movementTrackModel.setReachEndLatitude(true);
                    }
                    if (key == "longitude"){
                        this.movementTrackModel.setReachEndLongitude(true);
                    }
                    if (key == "altitude"){
                        this.movementTrackModel.setReachEndAltitude(true);
                    }
                }
            }
            else if(geographicCoordinates.equals(endLLA)){
                if (key == "latitude"){
                    this.movementTrackModel.setReachEndLatitude(true);
                }
                if (key == "longitude"){
                    this.movementTrackModel.setReachEndLongitude(true);
                }
                if (key == "altitude"){
                    this.movementTrackModel.setReachEndAltitude(true);
                }
            }
        }
        return geographicCoordinates;
    }
    private void calculateLLAAfterMovement(MovementTrackModel track, int firstRound) {

    }
    private void getChangeInLLA(MovementTrackModel track, Double trackTime) {
        track.setChangeInLatitude(Math.abs(track.getStartLatitude() - track.getEndLatitude()) / trackTime);
        track.setChangeInLongitude(Math.abs(track.getStartLongitude() - track.getEndLongitude()) / trackTime);
        track.setChangeInAltitude(Math.abs(track.getStartAltitude() - track.getEndAltitude()) / trackTime);
    }
}