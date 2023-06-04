package ChatEngine;

import Database.messageServer;
import GUI.client_chat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    private final String host;
    private final int port;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String nickname;
    SimpleDateFormat df;
    
    messageServer msr = new messageServer();
        public Client(String host, int port,String name) {
        this.host = host;
        this.port = port;
        this.nickname = name;
    }


    public void connect() {
        try {
            socket = new Socket(host, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            // Start a new thread to handle incoming messages from the server
            Thread messageThread = new Thread(this::handleMessages);
            messageThread.start();
            
            df = new SimpleDateFormat(" hh:mmaa");
                        String date = df.format(new Date());
            client_chat.chat_display.append("Chat started : "+date);
//            System.out.println("Connected to the server.");
//            System.out.println("Type 'bye' to disconnect.");

            // Read user input and send messages to the server
//            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
//            while (true) {
//                String message = userInputReader.readLine();
//                if (message.equalsIgnoreCase("bye")) {
//                    disconnect();
//                    break;
//                }
//                sendMessage(message);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleMessages() {
        try {
        String message;
        while ((message = reader.readLine()) != null) {
            if (message.equalsIgnoreCase("bye")) {
                // Disconnect the client
                disconnect();
                
                break;
            } else {
                // Handle the received message
                client_chat.chat_display.append("\n"+ message);
                msr.saveMessageToFile(message);
                
            }
        }
    } catch (SocketException e) {
        // SocketException occurs when the socket is closed
        client_chat.chat_display.append("\nClient disconnected: ");
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    public void sendMessage(String message) {
        
        writer.println(nickname+":"+message);
    }

    public void disconnect() {
        try {
            socket.close();
            System.out.println("Disconnected from the server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

}

