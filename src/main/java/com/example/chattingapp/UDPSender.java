package com.example.chattingapp;

import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.net.*;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;


public class UDPSender {

    private int port;
    // Define the destination IP address and port
    // Change to the receiver's IP address
    private String ip_Address;
    private InetAddress ipAddress = InetAddress.getByName(ip_Address);

    public UDPSender() throws UnknownHostException {
    }

    public UDPSender(int port, String ip_Address) throws UnknownHostException {
        this.port = port;
        this.ip_Address = ip_Address;
    }

    public void sendData(TrackModel object) throws IOException {


        // Convert the track to json
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);

        // Convert the json to bytes
        byte[] jsonData = json.getBytes();

        // Create UDP socket
        DatagramSocket socket = new DatagramSocket();

        // Create a UDP packet with the data, IP address, and port
        DatagramPacket packet = new DatagramPacket(jsonData, jsonData.length, ipAddress, port);

        // Send the packet
        socket.send(packet);
        // close the socket
        socket.close();

    }

    public void sendData(RadarModel object) throws IOException {

        // Define the port
//        int port = 9001;
        // Define the destination IP address and port
        // Change to the receiver's IP address
//        InetAddress ipAddress = InetAddress.getByName("localhost");

        // Convert the track to json
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);

        // Convert the json to bytes
        byte[] jsonData = json.getBytes();

        // Create UDP socket
        DatagramSocket socket = new DatagramSocket();

        // Create a UDP packet with the data, IP address, and port
        DatagramPacket packet = new DatagramPacket(jsonData, jsonData.length, ipAddress, port);

        // Send the packet
        socket.send(packet);
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
        DatagramSocket socket = new DatagramSocket();

        // Create a UDP packet with the data, IP address, and port
        DatagramPacket packet = new DatagramPacket(jsonData, jsonData.length, ipAddress, port);

    System.out.println("Packet Size :" + packet.getLength() );
        // Send the packet
        socket.send(packet);
        // close the socket
        socket.close();

    }

    public void sendData(ObservableList<TrackModel> object) throws IOException {

        // Define the port
//        int port = 9001;
        // Define the destination IP address and port
        // Change to the receiver's IP address
//        InetAddress ipAddress = InetAddress.getByName("localhost");

        // Convert the track to json
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);

        // Convert the json to bytes
        byte[] jsonData = json.getBytes();

        // Create UDP socket
        DatagramSocket socket = new DatagramSocket();

        // Create a UDP packet with the data, IP address, and port
        DatagramPacket packet = new DatagramPacket(jsonData, jsonData.length, ipAddress, port);

        // Send the packet
        socket.send(packet);
        // close the socket
        socket.close();

        // Convert the object to bytes
        // You should on the resiever convert the bytes to the same object
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//        objectOutputStream.writeObject(object);
//        byte[] data = outputStream.toByteArray();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp_Address() {
        return ip_Address;
    }

    public void setIp_Address(String ip_Address) {
        this.ip_Address = ip_Address;
    }
}
