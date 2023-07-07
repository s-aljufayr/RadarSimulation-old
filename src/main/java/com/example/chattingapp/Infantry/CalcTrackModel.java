package com.example.chattingapp.Infantry;

import java.io.Serializable;

public class CalcTrackModel implements Serializable {
//    private int id;
//    private String time;
//    private double latitude;
//    private double longitude;
//    private double altitude;
//    private String militarySymbol;
//    private int radarId;
//    private double v1;
//    private double v2;
//    private double p_v1;
//    private double p_v2;
//    private double rcs;
//    private int type;
//    private double speed;
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
    public CalcTrackModel(){
    }

    public CalcTrackModel(TrackModel trackModel, double startLatitude, double startLongitude, double startAltitude, double endLatitude, double endLongitude, double endAltitude, double changeInLatitude, double changeInLongitude, double changeInAltitude, double trackFrequency, double timeFrame, boolean isReachEndLatitude, boolean isReachEndLongitude, boolean isReachEndAltitude) {
        this.trackModel = trackModel;

//        this.trackModel.setId(trackModel.getId());
//        this.trackModel.setLatitude(trackModel.getLatitude());
//        this.trackModel.setLongitude(trackModel.getLongitude());
//        this.trackModel.setAltitude(trackModel.getAltitude());
//        this.trackModel.setRadarId(trackModel.getRadarId());
//        this.trackModel.setSpeed(trackModel.getSpeed());
//        this.trackModel.setMilitarySymbol(trackModel.getMilitarySymbol());
//        this.trackModel.setRcs(trackModel.getRcs());
//        this.trackModel.setP_v1(trackModel.getP_v1());
//        this.trackModel.setP_v2(trackModel.getP_v2());
//        this.trackModel.setV1(trackModel.getV1());
//        this.trackModel.setV2(trackModel.getV2());
//        this.trackModel.setTime(trackModel.getTime());
//        this.trackModel.setType(trackModel.getType());

        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.startAltitude = startAltitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.endAltitude = endAltitude;
        this.changeInLatitude = changeInLatitude;
        this.changeInLongitude = changeInLongitude;
        this.changeInAltitude = changeInAltitude;
        this.TrackFrequency = trackFrequency;
        this.timeFrame = timeFrame;
        this.isReachEndLatitude = isReachEndLatitude;
        this.isReachEndLongitude = isReachEndLongitude;
        this.isReachEndAltitude = isReachEndAltitude;
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

    @Override
    public String toString() {
        return "CalcTrackModel{" +
                "trackModel=" + trackModel.toString() +
                ", startLatitude=" + startLatitude +
                ", startLongitude=" + startLongitude +
                ", startAltitude=" + startAltitude +
                ", endLatitude=" + endLatitude +
                ", endLongitude=" + endLongitude +
                ", endAltitude=" + endAltitude +
                ", changeInLatitude=" + changeInLatitude +
                ", changeInLongitude=" + changeInLongitude +
                ", changeInAltitude=" + changeInAltitude +
                ", TrackFrequency=" + TrackFrequency +
                ", timeFrame=" + timeFrame +
                ", isReachEndLatitude=" + isReachEndLatitude +
                ", isReachEndLongitude=" + isReachEndLongitude +
                ", isReachEndAltitude=" + isReachEndAltitude +
                '}';
    }
}
