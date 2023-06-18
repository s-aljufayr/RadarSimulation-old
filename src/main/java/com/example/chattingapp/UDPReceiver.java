package com.example.chattingapp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import javafx.application.Platform;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class UDPReceiver {
    private int receiverPort = 9002;
    private boolean isReceivingMessages = true;
    static boolean[] online;
    DatagramSocket socket = new DatagramSocket(receiverPort);
    public UDPReceiver() throws SocketException {
    }
    public boolean[] startReceiving() throws IOException {
        AtomicBoolean isReceivingMessages = new AtomicBoolean(true);
        DatagramSocket socket = new DatagramSocket(receiverPort);
        byte[] buffer = new byte[1024];

        DatagramPacket packet = new DatagramPacket(buffer, 1024);

        new Thread(() -> {
            try {
                while (isReceivingMessages.get()) {
                    socket.receive(packet);
                    String jsonMessage = new String(packet.getData(), 0, packet.getLength());
                    // Process the received JSON message
                    String key = "online";

                    // Find the index of the key in the JSON string
                    int keyIndex = jsonMessage.indexOf("\"" + key + "\"");

                    // If the key exists, extract the boolean value
                    if (keyIndex != -1) {
                        // Find the index of the value
                        int valueStartIndex = jsonMessage.indexOf(":", keyIndex);
                        int valueEndIndex = jsonMessage.indexOf(",", keyIndex);
                        if (valueEndIndex == -1) {
                            valueEndIndex = jsonMessage.indexOf("}", keyIndex);
                        }

                        // Extract the value substring
                        String valueString = jsonMessage.substring(valueStartIndex + 1, valueEndIndex).trim();

                        // Parse the value into a boolean
                        boolean booleanValue = Boolean.parseBoolean(valueString);
                        if(!booleanValue)
                            break;
                        online[0] = booleanValue;
                    }
                    packet.setLength(1024);
                }
                socket.close();
            } catch (Exception e) {
                System.out.println("Error receiving UDP message: " + e.getMessage());
            }
        }).start();
        return online;
    }

}
