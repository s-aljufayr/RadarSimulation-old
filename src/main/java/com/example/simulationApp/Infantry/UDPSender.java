package com.example.simulationApp.Infantry;

import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.net.*;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


public class UDPSender {
    private static Integer port;
    // Define the destination IP address and port
    // Change to the receiver's IP address
    private static String ip_Address;
    private InetAddress ipAddress = InetAddress.getByName(ip_Address);
    private DatagramSocket socket;

    public UDPSender() throws UnknownHostException {
    }

    public void sendData(RadarModel object) throws IOException {

        // Convert the track to json
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);

        // Convert the json to bytes
        byte[] jsonData = json.getBytes();

        // Create UDP socket
        socket = new DatagramSocket();

        // Create a UDP packet with the data, IP address, and port
        DatagramPacket packet = new DatagramPacket(jsonData, jsonData.length, ipAddress, port);

        // Send the packet
//        socket.send(packet); //COMMented
        // close the socket
        socket.close();

        // Convert the object to bytes
        // You should on the resiever convert the bytes to the same object
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//        objectOutputStream.writeObject(object);
//        byte[] data = outputStream.toByteArray();
    }
    public void sendData(List<TrackModel> object) throws IOException {


        // Convert the track to json
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);

        // Convert the json to bytes
        byte[] jsonData = json.getBytes();

        // Create UDP socket
        socket = new DatagramSocket();

        // Create a UDP packet with the data, IP address, and port
        DatagramPacket packet = new DatagramPacket(jsonData, jsonData.length, ipAddress, port);

        System.out.println("Packet Size :" + packet.getLength() );
        // Send the packet
        socket.send(packet);
        // close the socket
        socket.close();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIp_Address() {
        return ip_Address;
    }

    public void setIp_Address(String ip_Address) {
        this.ip_Address = ip_Address;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }
}
