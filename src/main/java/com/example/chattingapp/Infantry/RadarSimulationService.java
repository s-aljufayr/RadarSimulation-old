//package com.example.chattingapp.Infantry;
//
//import javafx.application.Platform;
//
//import java.io.IOException;
//import java.util.Calendar;
//import java.util.List;
//
//public class RadarSimulationService {
//    RadarSimulationController radarSimulationController = new RadarSimulationController();
//    UDPReceiver udpReceiver = new UDPReceiver();
//    UDPSender udpSender = new UDPSender();
//
//    public RadarSimulationService() throws IOException {
//    }
//
//    public void getChangeInLLA(TrackModel track, int trackTime) {
//        track.setChangeInLatitude(Math.abs(track.getStartLatitude() - track.getEndLatitude()) / trackTime);
//        track.setChangeInLongitude(Math.abs(track.getStartLongitude() - track.getEndLongitude()) / trackTime);
//        track.setChangeInAltitude(Math.abs(track.getStartAltitude() - track.getEndAltitude()) / trackTime);
//    }
//    public String getLocalTime(){
//        Calendar cal = Calendar.getInstance();
//        radarSimulationController.second = cal.get(Calendar.SECOND);
//        radarSimulationController.minute = cal.get(Calendar.MINUTE);
//        radarSimulationController.hour = cal.get(Calendar.HOUR);
//        String localTime = radarSimulationController.hour + ":" + (radarSimulationController.minute) + ":" + radarSimulationController.second;
//        return localTime;
//    }
//    public Double countLLA(Double geographicCoordinates, Double endLLA,Double changeInLLA, boolean breakLoop, String key){
//        if(!breakLoop) {
//            if (geographicCoordinates < endLLA) {
//                geographicCoordinates += changeInLLA;
//                if (geographicCoordinates >= endLLA) {
//                    if (key == "latitude"){
//                        radarSimulationController.isReachEndLatitude = true;
//                        radarSimulationController.track.setReachEndLatitude(true);
//                    }
//                    if (key == "longitude"){
//                        radarSimulationController.isReachEndLongitude = true;
//                        radarSimulationController.track.setReachEndLongitude(true);
//                    }
//                    if (key == "altitude"){
//                        radarSimulationController.isReachEndAltitude = true;
//                        radarSimulationController.track.setReachEndAltitude(true);
//                    }
//                }
//            }
//            else if(geographicCoordinates > endLLA) {
//                geographicCoordinates -= changeInLLA;
//                if (geographicCoordinates <= endLLA){
//                    if (key == "latitude"){
//                        radarSimulationController.isReachEndLatitude = true;
//                        radarSimulationController.track.setReachEndLatitude(true);
//                    }
//                    if (key == "longitude"){
//                        radarSimulationController.isReachEndLongitude = true;
//                        radarSimulationController.track.setReachEndLongitude(true);
//                    }
//                    if (key == "altitude"){
//                        radarSimulationController.isReachEndAltitude = true;
//                        radarSimulationController.track.setReachEndAltitude(true);
//                    }
//                }
//            }
//            else if(geographicCoordinates.equals(endLLA)){
//                if (key == "latitude"){
//                    radarSimulationController.isReachEndLatitude = true;
//                    radarSimulationController.track.setReachEndLatitude(true);
//                }
//                if (key == "longitude"){
//                    radarSimulationController.isReachEndLongitude = true;
//                    radarSimulationController.track.setReachEndLongitude(true);
//                }
//                if (key == "altitude"){
//                    radarSimulationController.isReachEndAltitude = true;
//                    radarSimulationController.track.setReachEndAltitude(true);
//                }
//            }
//        }
//        return geographicCoordinates;
//    }
//    public boolean hasReachedEndLatitude(TrackModel track) {
//        return track.getLatitude() == track.getEndLatitude()
//                || Math.abs(track.getLatitude() - track.getEndLatitude()) == Math.abs(track.getChangeInLatitude());
//    }
//    public boolean hasReachedEndLongitude(TrackModel track) {
//        return track.getLongitude() == track.getEndLongitude()
//                || Math.abs(track.getLongitude() - track.getEndLongitude()) == Math.abs(track.getChangeInLongitude());
//    }
//    public boolean hasReachedEndAltitude(TrackModel track) {
//        return track.getAltitude() == track.getEndAltitude()
//                || Math.abs(track.getAltitude() - track.getEndAltitude()) == Math.abs(track.getChangeInAltitude());
//    }
//    public void checkAllArivedToDestination(TrackModel track) {
//
//        radarSimulationController.isReachEndLatitude = hasReachedEndLatitude(track);
//        radarSimulationController.isReachEndLongitude = hasReachedEndLongitude(track);
//        radarSimulationController.isReachEndAltitude = hasReachedEndAltitude(track);
//
//    }
//    public List<TrackModel> checkTrackIdExists(List<TrackModel> trackingList, TrackModel track) {
//        for(int i = 0; i < trackingList.size(); i++){
//            TrackModel ExistingTrack = trackingList.get(i);
//            if(ExistingTrack.getId() == track.getId()){
//                trackingList.remove(i);
//                trackingList.add(track);
//                radarSimulationController.isTrackExists = true;
//                break;
//            }
//        }
//        return trackingList;
//    }
//    public void startSending(RadarModel radar) {
//        radar.setStatus(true);
//
//        Thread sendStatus = new Thread(() -> {
//            while (radar.isStatus()) {
//                // to send the radar information
//                try {
//                    udpSender.sendData(radar);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                // set Radar Pane On UI
//                Platform.runLater(() -> {
//                    radarSimulationController.setRadarPane(radar);
//                });
//                if(!udpReceiver.isOnline())
//                    radar.setStatus(false);
//                if(!radar.isStatus())
//                    break;
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        sendStatus.start();
//    }
//    //    public void setRadarProperties(RadarModel radar) {
////        radar.setId(Integer.parseInt(radarSimulationController.deviceIdField.getText()));
////        radar.setLatitude(Double.parseDouble(radarSimulationController.deviceLatitudeField.getText()));
////        radar.setLongitude(Double.parseDouble(radarSimulationController.deviceLongitudeField.getText()));
////        radar.setAltitude(Double.parseDouble(radarSimulationController.deviceAltitudeField.getText()));
////        radar.setStatus(true);
////    }
////    public void setRadarPane(RadarModel radar) {
////        // print the radar information on radar labels
////        if(!radar.isStatus()){
////            radarSimulationController.deviceIdLable.setText("Unknown");
////            radarSimulationController.deviceLatitudeLable.setText("Unknown");
////            radarSimulationController.deviceLongitudeLable.setText("Unknown");
////            radarSimulationController.deviceAltitudeLable.setText("Unknown");
////            radarSimulationController.deviceStatusLabel.setText("Offline");
////        }else{
////            radarSimulationController.deviceIdLable.setText(String.valueOf(radar.getId()));
////            radarSimulationController.deviceLatitudeLable.setText(String.valueOf(radar.getLatitude()));
////            radarSimulationController.deviceLongitudeLable.setText(String.valueOf(radar.getLongitude()));
////            radarSimulationController.deviceAltitudeLable.setText(String.valueOf(radar.getAltitude()));
////            radarSimulationController.deviceStatusLabel.setText("Online");
////        }
////    }
////  public void getTrackFromTable(TrackModel track){
////    track.setId(Integer.parseInt(String.valueOf(radarSimulationController.enemyIdColumn.getCellData(track))));
////    track.setSpeed(Double.parseDouble(String.valueOf(radarSimulationController.speedColumn.getCellData(track))));
////    track.setStartLatitude(Double.parseDouble(String.valueOf(radarSimulationController.startLatitudeColumn.getCellData(track))));
////    track.setStartLongitude(Double.parseDouble(String.valueOf(radarSimulationController.startLongitudeColumn.getCellData(track))));
////    track.setStartAltitude(Double.parseDouble(String.valueOf(radarSimulationController.startAltitudeColumn.getCellData(track))));
////    track.setEndLatitude(Double.parseDouble(String.valueOf(radarSimulationController.endLatitudeColumn.getCellData(track))));
////    track.setEndLongitude(Double.parseDouble(String.valueOf(radarSimulationController.endLongitudeColumn.getCellData(track))));
////    track.setEndAltitude(Double.parseDouble(String.valueOf(radarSimulationController.endAltitudeColumn.getCellData(track))));
////    track.setChangeInLatitude(Double.parseDouble(String.valueOf(radarSimulationController.changeInLatitudeColumn.getCellData(track))));
////    track.setChangeInLongitude(Double.parseDouble(String.valueOf(radarSimulationController.changeInLongitudeColumn.getCellData(track))));
////    track.setChangeInAltitude(Double.parseDouble(String.valueOf(radarSimulationController.changeInAltitudeColumn.getCellData(track))));
////    track.setTrackFrequency(Long.parseLong(radarSimulationController.trackFrequencyField.getText()));
////}
////    public void updateTable(TrackModel track){
////        radarSimulationController.tracksList = radarSimulationController.trackTable.getItems();
////        radarSimulationController.tracksList.add(track);
////        radarSimulationController.trackTable.setItems(radarSimulationController.tracksList);
////    }
//}
