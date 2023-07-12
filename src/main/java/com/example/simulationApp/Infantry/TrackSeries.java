package com.example.simulationApp.Infantry;

public class TrackSeries {
    int id;
    double latitude[];
    double longitude[];
    double altitude[];

    public TrackSeries(int id, double[] latitude, double[] longitude, double[] altitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }
}
