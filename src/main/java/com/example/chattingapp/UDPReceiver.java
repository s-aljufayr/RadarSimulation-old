package com.example.chattingapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPReceiver {
    static boolean online = true;
    boolean isReceivingMessages = true;
    Integer receiverPort = 9002;
    private DatagramSocket socket;
    public UDPReceiver() throws UnknownHostException {
    }
    public void startReceiving() throws IOException {
        setReceivingMessages(true);
        setOnline(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    socket = null;
                    try {
                        socket = new DatagramSocket(receiverPort);
                    } catch (SocketException e) {
                        throw new RuntimeException(e);
                    }

                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, 1024);

                    while (isReceivingMessages) {
                        try {
                            socket.receive(packet);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        String json = new String(packet.getData(), 0, packet.getLength());
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode rootNode;
                        try {
                            rootNode = mapper.readTree(json);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                        setOnline(rootNode.get("online").asBoolean());
                        if(!online){
                            setReceivingMessages(false);
                            socket.close();
                            break;
                        }
                        packet.setLength(1024); // Reset the packet length for the next receive
                    }
                    System.out.println(online);
                    socket.close();// Close the socket when done receiving
                    break;
                }
            }
        }).start();
    }

    public static boolean isOnline() {
        return online;
    }

    public static void setOnline(boolean online) {
        UDPReceiver.online = online;
    }

    public boolean isReceivingMessages() {
        return isReceivingMessages;
    }

    public void setReceivingMessages(boolean receivingMessages) {
        isReceivingMessages = receivingMessages;
    }

    public Integer getReceiverPort() {
        return receiverPort;
    }

    public void setReceiverPort(Integer receiverPort) {
        this.receiverPort = receiverPort;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }
}
