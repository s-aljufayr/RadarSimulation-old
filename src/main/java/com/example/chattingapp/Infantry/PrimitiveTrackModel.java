package com.example.chattingapp.Infantry;

public class PrimitiveTrackModel {
//    TrackModel track;
    private int id;
    private String time;
    private double latitude;
    private double longitude;
    private double altitude;
    private String militarySymbol;
    private int radarId;
    private double speed;
    private double startLatitude;
    private double startLongitude;
    private double startAltitude;
    private double endLatitude;
    private double endLongitude;
    private double endAltitude;
    public PrimitiveTrackModel(TrackModel track) {
        this.id = track.getId();
        this.time = track.getTime();
        this.latitude = track.getLatitude();
        this.longitude = track.getLongitude();
        this.altitude = track.getAltitude();
        this.militarySymbol = track.getMilitarySymbol();
        this.radarId = track.getRadarId();
        this.speed = track.getSpeed();
        this.startLatitude = track.getStartLatitude();
        this.startLongitude = track.getStartLongitude();
        this.startAltitude = track.getStartAltitude();
        this.endLatitude = track.getEndLatitude();
        this.endLongitude = track.getEndLongitude();
        this.endAltitude = track.getEndAltitude();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public String getMilitarySymbol() {
        return militarySymbol;
    }

    public void setMilitarySymbol(String militarySymbol) {
        this.militarySymbol = militarySymbol;
    }

    public int getRadarId() {
        return radarId;
    }

    public void setRadarId(int radarId) {
        this.radarId = radarId;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
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
}
