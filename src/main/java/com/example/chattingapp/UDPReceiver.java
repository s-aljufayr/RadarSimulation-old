package com.example.chattingapp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceiver {
    private static int receiverPort = 9002;
    private boolean isReceivingMessages = true;

    public UDPReceiver() {
    }
    private void startReceiving() throws SocketException {
        DatagramSocket socket = new DatagramSocket(receiverPort);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer,1024);

    }
}
