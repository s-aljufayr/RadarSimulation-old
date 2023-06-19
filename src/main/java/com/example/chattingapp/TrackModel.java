package com.example.chattingapp;

import java.io.Serializable;

public class TrackModel implements Serializable {
    private int id;
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

    public TrackModel(){
    }
    public TrackModel(int id, double latitude, double longitude, double altitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public TrackModel(int id, String time, double latitude, double longitude, double altitude, double speed, String militarySymbol,
                      int radarId, double v1, double v2, double p_v1, double p_v2, double rcs, int type)
    {
        this.id = id;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.speed = speed;
        this.militarySymbol = militarySymbol;
        this.radarId = radarId;
        this.v1 = v1;
        this.v2 = v2;
        this.p_v1 = p_v1;
        this.p_v2 = p_v2;
        this.rcs = rcs;
        this.type = type;
    }
    public static double calculateDistance(double lat1, double lon1, double alt1, double lat2, double lon2, double alt2) {
        double earthRadius = 6371000; // Average radius of the Earth in meters

        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Calculate the differences in latitude, longitude, and altitude
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;
        double dAlt = alt2 - alt1;

        // Apply the Haversine formula to calculate the great-circle distance between the points
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate the total distance in three-dimensional space
        return Math.sqrt(Math.pow(earthRadius * c, 2) + Math.pow(dAlt, 2));
    }

    public double distanceBetweenTowPoints(double lat1, double lat2, double lon1, double lon2)
    {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        // return the distance in km
        return(c * r);
    }
    static double calculateSpeed(double dist, double time)
    {
               return dist / time;
    }

    // Function to calculate distance travelled
    static double calculateDistance(double speed, double time)
    {
        return speed * time;
    }

    // Function to calculate time taken
    static double calculateTime(double dist, double speed)
    {
        return dist / speed;
    }
    public double getTrackFrequency() {
        return TrackFrequency;
    }

    public void setTrackFrequency(double trackFrequency) {
        TrackFrequency = trackFrequency;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
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

    @Override
    public String toString() {
        return "TrackModel{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", militarySymbol='" + militarySymbol + '\'' +
                ", radarId=" + radarId +
                ", v1=" + v1 +
                ", v2=" + v2 +
                ", p_v1=" + p_v1 +
                ", p_v2=" + p_v2 +
                ", rcs=" + rcs +
                ", type=" + type +
                ", speed=" + speed +
                '}';
    }
}
