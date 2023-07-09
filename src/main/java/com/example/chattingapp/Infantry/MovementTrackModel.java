package com.example.chattingapp.Infantry;

import javafx.collections.ObservableList;

import java.io.Serializable;

public class MovementTrackModel implements Serializable {
    public TrackModel trackModel;
    private double startLatitude;
    private double startLongitude;
    private double startAltitude;
    private double endLatitude;
    private double endLongitude;
    private double endAltitude;
    private double changeInLatitude;
    private double changeInLongitude;
    private double changeInAltitude;
    private double TrackFrequency;
    private double timeFrame;
    private boolean isReachEndLatitude;
    private boolean isReachEndLongitude;
    private boolean isReachEndAltitude;
    public MovementTrackModel(){
    }
    public TrackModel getTrackModel() {
        return trackModel;
    }

    public void setTrackModel(TrackModel trackModel) {
        this.trackModel = trackModel;
    }

    public double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(double startLongitude) {
        this.startLongitude = startLongitude;
    }

    public double getStartAltitude() {
        return startAltitude;
    }

    public void setStartAltitude(double startAltitude) {
        this.startAltitude = startAltitude;
    }

    public double getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(double endLatitude) {
        this.endLatitude = endLatitude;
    }

    public double getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(double endLongitude) {
        this.endLongitude = endLongitude;
    }

    public double getEndAltitude() {
        return endAltitude;
    }

    public void setEndAltitude(double endAltitude) {
        this.endAltitude = endAltitude;
    }

    public double getChangeInLatitude() {
        return changeInLatitude;
    }

    public void setChangeInLatitude(double changeInLatitude) {
        this.changeInLatitude = changeInLatitude;
    }

    public double getChangeInLongitude() {
        return changeInLongitude;
    }

    public void setChangeInLongitude(double changeInLongitude) {
        this.changeInLongitude = changeInLongitude;
    }

    public double getChangeInAltitude() {
        return changeInAltitude;
    }

    public void setChangeInAltitude(double changeInAltitude) {
        this.changeInAltitude = changeInAltitude;
    }

    public double getTrackFrequency() {
        return TrackFrequency;
    }

    public void setTrackFrequency(double trackFrequency) {
        TrackFrequency = trackFrequency;
    }

    public double getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(double timeFrame) {
        this.timeFrame = timeFrame;
    }

    public boolean isReachEndLatitude() {
        return isReachEndLatitude;
    }

    public void setReachEndLatitude(boolean reachEndLatitude) {
        isReachEndLatitude = reachEndLatitude;
    }

    public boolean isReachEndLongitude() {
        return isReachEndLongitude;
    }

    public void setReachEndLongitude(boolean reachEndLongitude) {
        isReachEndLongitude = reachEndLongitude;
    }

    public boolean isReachEndAltitude() {
        return isReachEndAltitude;
    }

    public void setReachEndAltitude(boolean reachEndAltitude) {
        isReachEndAltitude = reachEndAltitude;
    }

    public void calculateLLAAfterMovement (MovementTrackModel track, int firstRound){
        if(firstRound == 0){
            track.getTrackModel().setLatitude(this.countLLA(track.getStartLatitude(),track.getEndLatitude(),track.getChangeInLatitude(),track.isReachEndLatitude(),"latitude"));
            track.getTrackModel().setLongitude(this.countLLA(track.getStartLongitude(), track.getEndLongitude(), track.getChangeInLongitude(),track.isReachEndLongitude(), "longitude"));
            track.getTrackModel().setAltitude(this.countLLA(track.getStartAltitude(), track.getEndAltitude(), track.getChangeInAltitude(),track.isReachEndAltitude(), "altitude"));
        }else{
            track.getTrackModel().setLatitude(this.countLLA(track.getTrackModel().getLatitude(),track.getEndLatitude(),track.getChangeInLatitude(),track.isReachEndLatitude(),"latitude"));
            track.getTrackModel().setLongitude(this.countLLA(track.getTrackModel().getLongitude(), track.getEndLongitude(), track.getChangeInLongitude(),track.isReachEndLongitude(),"longitude"));
            track.getTrackModel().setAltitude(this.countLLA(track.getTrackModel().getAltitude(), track.getEndAltitude(), track.getChangeInAltitude(),track.isReachEndAltitude(),"altitude"));
        }
    }
    private Double countLLA(Double geographicCoordinates, Double endLLA,Double changeInLLA, boolean breakLoop, String key){
        if(!breakLoop) {
            if (geographicCoordinates < endLLA) {
                geographicCoordinates = geographicCoordinates + changeInLLA;
                if (geographicCoordinates >= endLLA) {
                    if (key == "latitude"){
                        this.setReachEndLatitude(true);
                    }
                    if (key == "longitude"){
                        this.setReachEndLongitude(true);
                    }
                    if (key == "altitude"){
                        this.setReachEndAltitude(true);
                    }
                }
            }
            else if(geographicCoordinates > endLLA) {
                geographicCoordinates = geographicCoordinates - changeInLLA;
                if (geographicCoordinates <= endLLA){
                    if (key == "latitude"){
                        this.setReachEndLatitude(true);
                    }
                    if (key == "longitude"){
                        this.setReachEndLongitude(true);
                    }
                    if (key == "altitude"){
                        this.setReachEndAltitude(true);
                    }
                }
            }
            else if(geographicCoordinates.equals(endLLA)){
                if (key == "latitude"){
                    this.setReachEndLatitude(true);
                }
                if (key == "longitude"){
                    this.setReachEndLongitude(true);
                }
                if (key == "altitude"){
                    this.setReachEndAltitude(true);
                }
            }
        }
        return geographicCoordinates;
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
    public void getChangeInLLA(MovementTrackModel track, Double trackTime) {
        track.setChangeInLatitude(Math.abs(track.getStartLatitude() - track.getEndLatitude()) / trackTime);
        track.setChangeInLongitude(Math.abs(track.getStartLongitude() - track.getEndLongitude()) / trackTime);
        track.setChangeInAltitude(Math.abs(track.getStartAltitude() - track.getEndAltitude()) / trackTime);
    }
    @Override
    public String toString() {
        return "MovementTrackModel{" +
                "trackModel=" + this.trackModel.toString() +
                ", startLatitude=" + this.startLatitude +
                ", startLongitude=" + this.startLongitude +
                ", startAltitude=" + this.startAltitude +
                ", endLatitude=" + this.endLatitude +
                ", endLongitude=" + this.endLongitude +
                ", endAltitude=" + this.endAltitude +
                ", changeInLatitude=" + this.changeInLatitude +
                ", changeInLongitude=" + this.changeInLongitude +
                ", changeInAltitude=" + this.changeInAltitude +
                ", TrackFrequency=" + this.TrackFrequency +
                ", timeFrame=" + this.timeFrame +
                ", isReachEndLatitude=" + this.isReachEndLatitude +
                ", isReachEndLongitude=" + this.isReachEndLongitude +
                ", isReachEndAltitude=" + this.isReachEndAltitude +
                '}';
    }
}
