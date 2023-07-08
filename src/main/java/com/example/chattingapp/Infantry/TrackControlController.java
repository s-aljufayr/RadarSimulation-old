package com.example.chattingapp.Infantry;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
    ObservableList<CalcTrackModel> tracksList;
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
    CalcTrackModel calcTrackModel = new CalcTrackModel();
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
    private TableView<CalcTrackModel> trackTable;
    @FXML
    private TableColumn<CalcTrackModel, Double> speedColumn;
    @FXML
    private TableColumn<CalcTrackModel, String> militarySymbolColumn;
    @FXML
    private TableColumn<CalcTrackModel, Double> pV1Column;
    @FXML
    private TableColumn<CalcTrackModel, Double> pV2Column;
    @FXML
    private TableColumn<CalcTrackModel, Integer> radarIdColumn;
    @FXML
    private TableColumn<CalcTrackModel, Double> rcsColumn;
    @FXML
    private TableColumn<CalcTrackModel, Integer> typeColumn;
    @FXML
    private TableColumn<CalcTrackModel, Double> v1Column;
    @FXML
    private TableColumn<CalcTrackModel, Double> v2Column;
    @FXML
    private TableColumn<CalcTrackModel, Double> enemyLongitudeColumn;
    @FXML
    private TableColumn<CalcTrackModel, Integer> enemyIdColumn;
    @FXML
    private TableColumn<CalcTrackModel, Double> enemyLatitudeColumn, enemyAltitudeColumn;

    //////////////////////////////////////////////
    @FXML
    private TableColumn<CalcTrackModel, Double> timeColumn;
    @FXML
    private TableColumn<CalcTrackModel, Double> endLongitudeColumn, changeInLongitudeColumn;
    @FXML
    private TableColumn<CalcTrackModel, Double> startAltitudeColumn, startLatitudeColumn, startLongitudeColumn;
    @FXML
    private TableColumn<CalcTrackModel, Double> endLatitudeColumn, changeInLatitudeColumn;
    @FXML
    private TableColumn<CalcTrackModel, Double>  endAltitudeColumn, changeInAltitudeColumn;
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
        CalcTrackModel track = this.getTrackFromUi();
        this.updateTable(track);
    }
    @FXML
    void startSimulationButton(ActionEvent event) throws IOException {
//        CalcTrackModel track = new CalcTrackModel();
        List<CalcTrackModel> tracksListAfterMovement = new ArrayList<>();
        List<TrackModel> trackModelListAfterMovement = new ArrayList<>();
        // this counter to now the #round on the loop
        int firstRound = 0;
        calcTrackModel.setReachEndLatitude(false);
        calcTrackModel.setReachEndLongitude(false);
        calcTrackModel.setReachEndAltitude(false);
        isReachEndLatitude = false;
        isReachEndLongitude = false;
        isReachEndAltitude = false;
        //!(track.isReachEndLatitude() && track.isReachEndLongitude() && track.isReachEndAltitude())
        while (firstRound <= maxTrackTime) {

            tracksList = trackTable.getItems();

            for(int rowIndex = 0; rowIndex < tracksList.size(); rowIndex++){

                calcTrackModel = (CalcTrackModel) tracksList.get(rowIndex);

                this.getTrackFromTable(calcTrackModel);
                System.out.println(("Row Index : " + rowIndex));
                System.out.println("loop Counter: "+firstRound);
                
                // Perform loop operations here
                this.getChangeInLLA(calcTrackModel,calcTrackModel.getTimeFrame());
//                this.checkAllArrivedToDestination(track);
                this.calculateLLAAfterMovement(calcTrackModel, firstRound);
                // get track time
                calcTrackModel.getTrackModel().setTime(this.getLocalTime());
                // Check the track id on the list or not, will update if  exists, will add new if not
                isTrackExists = false;
//                tracksListAfterMovement = this.checkTrackIdExists(tracksListAfterMovement,calcTrackModel);
                trackModelListAfterMovement = this.checkTrackIdExists(trackModelListAfterMovement,(TrackModel) calcTrackModel.trackModel);
                if(!isTrackExists){
                    trackModelListAfterMovement.add(calcTrackModel.trackModel);
//                    tracksListAfterMovement.add(calcTrackModel);
                }
            }
            // Delay for TrackFrequency second
            try {
                Thread.sleep((long) (1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!udpReceiver.isOnline()){
//                udpReceiver.getSocket().close();
                udpSender.getSocket().close();
                break;
            }
            firstRound++;
            if(!trackModelListAfterMovement.isEmpty()){
                udpSender.sendData(trackModelListAfterMovement);
                System.out.println(trackModelListAfterMovement);
//                udpSender.sendData(tracksListAfterMovement);
//                System.out.println(tracksListAfterMovement);
            }
        }
    }
    @FXML
    public void initialize() {
//        trackTable.setItems(FXCollections.observableArrayList());
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
        timeColumn.setCellValueFactory(new PropertyValueFactory<CalcTrackModel, Double>("timeFrame"));

        startLatitudeColumn.setCellValueFactory(new PropertyValueFactory<CalcTrackModel, Double>("startLatitude"));
        startLongitudeColumn.setCellValueFactory(new PropertyValueFactory<CalcTrackModel, Double>("startLongitude"));
        startAltitudeColumn.setCellValueFactory(new PropertyValueFactory<CalcTrackModel, Double>("startAltitude"));

        endLatitudeColumn.setCellValueFactory(new PropertyValueFactory<CalcTrackModel, Double>("endLatitude"));
        endLongitudeColumn.setCellValueFactory(new PropertyValueFactory<CalcTrackModel, Double>("endLongitude"));
        endAltitudeColumn.setCellValueFactory(new PropertyValueFactory<CalcTrackModel, Double>("endAltitude"));

        changeInLatitudeColumn.setCellValueFactory(new PropertyValueFactory<CalcTrackModel, Double>("changeInLatitude"));
        changeInLongitudeColumn.setCellValueFactory(new PropertyValueFactory<CalcTrackModel, Double>("changeInLongitude"));
        changeInAltitudeColumn.setCellValueFactory(new PropertyValueFactory<CalcTrackModel, Double>("changeInAltitude"));

    }
    private Double countLLA(Double geographicCoordinates, Double endLLA,Double changeInLLA, boolean breakLoop, String key){
        if(!breakLoop) {
            if (geographicCoordinates < endLLA) {
                geographicCoordinates += changeInLLA;
                if (geographicCoordinates >= endLLA) {
                    if (key == "latitude"){
                        this.isReachEndLatitude = true;
                        this.calcTrackModel.setReachEndLatitude(true);
                    }
                    if (key == "longitude"){
                        this.isReachEndLongitude = true;
                        this.calcTrackModel.setReachEndLongitude(true);
                    }
                    if (key == "altitude"){
                        this.isReachEndAltitude = true;
                        this.calcTrackModel.setReachEndAltitude(true);
                    }
                }
            }
            else if(geographicCoordinates > endLLA) {
                geographicCoordinates -= changeInLLA;
                if (geographicCoordinates <= endLLA){
                    if (key == "latitude"){
                        this.isReachEndLatitude = true;
                        this.calcTrackModel.setReachEndLatitude(true);
                    }
                    if (key == "longitude"){
                        this.isReachEndLongitude = true;
                        this.calcTrackModel.setReachEndLongitude(true);
                    }
                    if (key == "altitude"){
                        this.isReachEndAltitude = true;
                        this.calcTrackModel.setReachEndAltitude(true);
                    }
                }
            }
            else if(geographicCoordinates.equals(endLLA)){
                if (key == "latitude"){
                    this.isReachEndLatitude = true;
                    this.calcTrackModel.setReachEndLatitude(true);
                }
                if (key == "longitude"){
                    this.isReachEndLongitude = true;
                    this.calcTrackModel.setReachEndLongitude(true);
                }
                if (key == "altitude"){
                    this.isReachEndAltitude = true;
                    this.calcTrackModel.setReachEndAltitude(true);
                }
            }
        }
        return geographicCoordinates;
    }
    private void getTrackFromTable(CalcTrackModel track){
        track.getTrackModel().setId(Integer.parseInt(String.valueOf(enemyIdColumn.getCellData(track))));
        track.getTrackModel().setSpeed(Double.parseDouble(String.valueOf(speedColumn.getCellData(track))));
        track.setStartLatitude(Double.parseDouble(String.valueOf(startLatitudeColumn.getCellData(track))));
        track.setStartLongitude(Double.parseDouble(String.valueOf(startLongitudeColumn.getCellData(track))));
        track.setStartAltitude(Double.parseDouble(String.valueOf(startAltitudeColumn.getCellData(track))));
        track.setEndLatitude(Double.parseDouble(String.valueOf(endLatitudeColumn.getCellData(track))));
        track.setEndLongitude(Double.parseDouble(String.valueOf(endLongitudeColumn.getCellData(track))));
        track.setEndAltitude(Double.parseDouble(String.valueOf(endAltitudeColumn.getCellData(track))));
//        track.setTrackFrequency(Long.parseLong(trackFrequencyField.getText()));
        track.setTimeFrame(Double.parseDouble(String.valueOf(timeColumn.getCellData(track))));
    }
    private CalcTrackModel getTrackFromUi(){
        CalcTrackModel calcTrackModel = new CalcTrackModel();
        TrackModel track = new TrackModel();

        track.setId(Integer.parseInt(autoTrackIdField.getText()));
        track.setSpeed(Double.parseDouble(autoTrackSpeedField.getText()));
        track.setTime(this.getLocalTime());
        track.setLatitude(Double.parseDouble(autoTrackStartLatitudeField.getText()));
        track.setLongitude(Double.parseDouble(autoTrackStartLongitudeField.getText()));
        track.setAltitude(Double.parseDouble(autoTrackStartAltitudeField.getText()));
        track.setRadarId(radar.getId());

        calcTrackModel.setTrackModel(track);
        calcTrackModel.setStartLatitude(Double.parseDouble(autoTrackStartLatitudeField.getText()));
        calcTrackModel.setStartLongitude(Double.parseDouble(autoTrackStartLongitudeField.getText()));
        calcTrackModel.setStartAltitude(Double.parseDouble(autoTrackStartAltitudeField.getText()));
        calcTrackModel.setEndLatitude(Double.parseDouble(autoTrackEndLatitudeField.getText()));
        calcTrackModel.setEndLongitude(Double.parseDouble(autoTrackEndLongitudeField.getText()));
        calcTrackModel.setEndAltitude(Double.parseDouble(autoTrackEndAltitudeField.getText()));
//        calcTrackModel.setChangeInLatitude(Double.parseDouble(latitudeChangeField.getText()));
//        calcTrackModel.setChangeInLongitude(Double.parseDouble(longitudeChangeField.getText()));
//        calcTrackModel.setChangeInAltitude(Double.parseDouble(altitudeChangeField.getText()));
        calcTrackModel.setTimeFrame(Double.parseDouble(trackTimeField.getText()));

        return calcTrackModel;
    }
    private void updateTable(CalcTrackModel track){
        tracksList = trackTable.getItems();
        tracksList.add(track);
        trackTable.setItems(tracksList);
    }
    public boolean hasReachedEndLatitude(CalcTrackModel track) {
        return track.getTrackModel().getLatitude() == track.getEndLatitude()
                || Math.abs(track.getTrackModel().getLatitude() - track.getEndLatitude()) == Math.abs(track.getChangeInLatitude());
    }
    public boolean hasReachedEndLongitude(CalcTrackModel track) {
        return track.getTrackModel().getLongitude() == track.getEndLongitude()
                || Math.abs(track.getTrackModel().getLongitude() - track.getEndLongitude()) == Math.abs(track.getChangeInLongitude());
    }
    public boolean hasReachedEndAltitude(CalcTrackModel track) {
        return track.getTrackModel().getAltitude() == track.getEndAltitude()
                || Math.abs(track.getTrackModel().getAltitude() - track.getEndAltitude()) == Math.abs(track.getChangeInAltitude());
    }
    private void checkAllArrivedToDestination(CalcTrackModel track) {
        this.isReachEndLatitude = hasReachedEndLatitude(track);
        this.isReachEndLongitude = hasReachedEndLongitude(track);
        this.isReachEndAltitude = hasReachedEndAltitude(track);
    }
    private void calculateLLAAfterMovement(CalcTrackModel track, int firstRound) {
        if(firstRound == 0){
            track.getTrackModel().setLatitude(this.countLLA(track.getStartLatitude(),track.getEndLatitude(),track.getChangeInLatitude(),track.isReachEndLatitude(),"latitude"));
            track.getTrackModel().setLongitude(this.countLLA(track.getStartLongitude(), track.getEndLongitude(), track.getChangeInLongitude(),track.isReachEndLongitude(), "longitude"));
            track.getTrackModel().setAltitude(this.countLLA(track.getStartAltitude(), track.getEndAltitude(), track.getChangeInAltitude(),track.isReachEndAltitude(), "altitude"));
            this.maxTrackTime = this.maxTrackTime(tracksList);
        }else{
            track.getTrackModel().setLatitude(this.countLLA(track.getTrackModel().getLatitude(),track.getEndLatitude(),track.getChangeInLatitude(),track.isReachEndLatitude(),"latitude"));
            track.getTrackModel().setLongitude(this.countLLA(track.getTrackModel().getLongitude(), track.getEndLongitude(), track.getChangeInLongitude(),track.isReachEndLongitude(),"longitude"));
            track.getTrackModel().setAltitude(this.countLLA(track.getTrackModel().getAltitude(), track.getEndAltitude(), track.getChangeInAltitude(),track.isReachEndAltitude(),"altitude"));
        }
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
    private void getChangeInLLA(CalcTrackModel track, Double trackTime) {
        track.setChangeInLatitude(Math.abs(track.getStartLatitude() - track.getEndLatitude()) / trackTime);
        track.setChangeInLongitude(Math.abs(track.getStartLongitude() - track.getEndLongitude()) / trackTime);
        track.setChangeInAltitude(Math.abs(track.getStartAltitude() - track.getEndAltitude()) / trackTime);
    }
    private double maxTrackTime(ObservableList<CalcTrackModel> trackList) {
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