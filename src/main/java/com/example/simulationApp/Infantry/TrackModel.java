package com.example.simulationApp.Infantry;

public class TrackModel {
    public int id;
    private String time;
    private double latitude;
    private double longitude;
    private double altitude;
    private String militarySymbol;
    private int radarId;
    private double v1;
    private double v2;
    private double p_v1;
    private double p_v2;
    private double rcs;
    private int type;
    private double speed;

    public TrackModel() {
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

    public double getV1() {
        return v1;
    }

    public void setV1(double v1) {
        this.v1 = v1;
    }

    public double getV2() {
        return v2;
    }

    public void setV2(double v2) {
        this.v2 = v2;
    }

    public double getP_v1() {
        return p_v1;
    }

    public void setP_v1(double p_v1) {
        this.p_v1 = p_v1;
    }

    public double getP_v2() {
        return p_v2;
    }

    public void setP_v2(double p_v2) {
        this.p_v2 = p_v2;
    }

    public double getRcs() {
        return rcs;
    }

    public void setRcs(double rcs) {
        this.rcs = rcs;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "TrackModel{" +
                "id=" + this.id +
                ", time='" + this.time + '\'' +
                ", latitude=" + this.latitude +
                ", longitude=" + this.longitude +
                ", altitude=" + this.altitude +
                ", militarySymbol='" + this.militarySymbol + '\'' +
                ", radarId=" + this.radarId +
                ", v1=" + this.v1 +
                ", v2=" + this.v2 +
                ", p_v1=" + this.p_v1 +
                ", p_v2=" + this.p_v2 +
                ", rcs=" + this.rcs +
                ", type=" + this.type +
            ", speed=" + this.speed +
                '}';
    }
}
