package A4;

import java.io.*;
import java.net.*;

public class Sender {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress receiverAddress = InetAddress.getByName("localhost");
        int windowSize = 4;
        int base = 0;
        int nextSeqNum = 0;
        int totalPackets = 10;

        while (base < totalPackets) {
            // Send packets up to the window size
            while (nextSeqNum < base + windowSize && nextSeqNum < totalPackets) {
                String message = "Packet " + nextSeqNum;
                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverAddress, 8000);
                socket.send(packet);
                System.out.println("Sent: " + message);
                nextSeqNum++;
            }

            // Receive ACKs
            socket.setSoTimeout(2000); // 2 seconds timeout for ACK
            try {
                byte[] ackBuffer = new byte[1024];
                DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length);
                socket.receive(ackPacket);
                String ackMessage = new String(ackPacket.getData(), 0, ackPacket.getLength());
                int ackNum = Integer.parseInt(ackMessage.split(" ")[1]);
                System.out.println("Received ACK for packet: " + ackNum);
                base = ackNum + 1; // Move the window forward
            } catch (SocketTimeoutException e) {
                System.out.println("Timeout occurred, resending from packet " + base);
                nextSeqNum = base; // Resend all unacknowledged packets
            }
        }
        socket.close();
    }
}
