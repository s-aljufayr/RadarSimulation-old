package com.example.chattingapp;

public class RadarModel {
    private int id;
    private String name;
    private int nameID;
    private double latitude;
    private double longitude;
    private double altitude;
    private int type;
    private String calibrationPoint;
    private double azimuth;
    private double elevation;
    private double range;
    private double angleOfView;
    private double protectedAreaRadius;
    private String militarySymbol;
    private static boolean status = false;
    public RadarModel(){}
    public RadarModel(int id, String name, int nameID, double latitude, double longitude, double altitude, int type,
                      String calibrationPoint, double azimuth, double elevation, double range, double angleOfView,
                      double protectedAreaRadius, String militarySymbol)
    {
        this.id = id;
        this.name = name;
        this.nameID = nameID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.type = type;
        this.calibrationPoint = calibrationPoint;
        this.azimuth = azimuth;
        this.elevation = elevation;
        this.range = range;
        this.angleOfView = angleOfView;
        this.protectedAreaRadius = protectedAreaRadius;
        this.militarySymbol = militarySymbol;
    }
    public RadarModel(int id, double latitude, double longitude, double altitude)
    {
        this.id = id;
        this.name = name;
        this.nameID = nameID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.type = type;
        this.calibrationPoint = calibrationPoint;
        this.azimuth = azimuth;
        this.elevation = elevation;
        this.range = range;
        this.angleOfView = angleOfView;
        this.protectedAreaRadius = protectedAreaRadius;
        this.militarySymbol = militarySymbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNameID() {
        return nameID;
    }

    public void setNameID(int nameID) {
        this.nameID = nameID;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCalibrationPoint() {
        return calibrationPoint;
    }

    public void setCalibrationPoint(String calibrationPoint) {
        this.calibrationPoint = calibrationPoint;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getAngleOfView() {
        return angleOfView;
    }

    public void setAngleOfView(double angleOfView) {
        this.angleOfView = angleOfView;
    }

    public double getProtectedAreaRadius() {
        return protectedAreaRadius;
    }

    public void setProtectedAreaRadius(double protectedAreaRadius) {
        this.protectedAreaRadius = protectedAreaRadius;
    }

    public String getMilitarySymbol() {
        return militarySymbol;
    }

    public void setMilitarySymbol(String militarySymbol) {
        this.militarySymbol = militarySymbol;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RadarModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameID=" + nameID +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", type=" + type +
                ", calibrationPoint='" + calibrationPoint + '\'' +
                ", azimuth=" + azimuth +
                ", elevation=" + elevation +
                ", range=" + range +
                ", angleOfView=" + angleOfView +
                ", protectedAreaRadius=" + protectedAreaRadius +
                ", militarySymbol='" + militarySymbol + '\'' +
                '}';
    }
}
