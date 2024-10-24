package B4.chat_server;

import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_ADDRESS = "localhost"; // Change this to server's IP if running on different machines
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connected to the chat server");

            // Create a new thread for listening to incoming messages
            new Thread(() -> {
                try {
                    String incomingMessage;
                    while ((incomingMessage = serverIn.readLine()) != null) {
                        System.out.println("Server: " + incomingMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            }).start();

            // Main thread handles sending messages to the server
            String userInput;
            while ((userInput = input.readLine()) != null) {
                serverOut.println(userInput);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
