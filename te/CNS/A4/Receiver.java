package A4;

import java.io.*;
import java.net.*;

public class Receiver {
    public static void main(String[] args) throws IOException {
        try (DatagramSocket socket = new DatagramSocket(8080)) {
            int expectedSeqNum = 0;

            while (true) {
                // Receive packet
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                int seqNum = Integer.parseInt(receivedMessage.split(" ")[1]);

                // Check if the received packet is the expected one
                if (seqNum == expectedSeqNum) {
                    System.out.println("Received: " + receivedMessage);
                    expectedSeqNum++;
                } else {
                    System.out.println("Received out of order packet, expecting: " + expectedSeqNum);
                }

                // Send ACK
                String ackMessage = "ACK " + (expectedSeqNum - 1);
                byte[] ackBuffer = ackMessage.getBytes();
                DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length, packet.getAddress(), packet.getPort());
                socket.send(ackPacket);
                System.out.println("Sent: " + ackMessage);
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
