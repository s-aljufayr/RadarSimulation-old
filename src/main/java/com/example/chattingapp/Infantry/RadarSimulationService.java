package com.example.chattingapp.Infantry;

import java.io.IOException;

public class RadarSimulationService {
    RadarSimulationController radarSimulationController = new RadarSimulationController();
    UDPReceiver udpReceiver = new UDPReceiver();
    UDPSender udpSender = new UDPSender();

    public RadarSimulationService() throws IOException {
    }

    public void getChangeInLLA(MovementTrackModel track, int trackTime) {
        track.setChangeInLatitude(Math.abs(track.getStartLatitude() - track.getEndLatitude()) / trackTime);
        track.setChangeInLongitude(Math.abs(track.getStartLongitude() - track.getEndLongitude()) / trackTime);
        track.setChangeInAltitude(Math.abs(track.getStartAltitude() - track.getEndAltitude()) / trackTime);
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

    public double distanceBetweenTwoPoints(double lat1, double lat2, double lon1, double lon2)
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
}
