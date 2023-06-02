package ChatEngine;

import GUI.client_chat;
<<<<<<< Updated upstream

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
=======
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
>>>>>>> Stashed changes

public class Client {
    private final String host;
    private final int port;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    SimpleDateFormat df;

<<<<<<< Updated upstream
    public Client(Socket socket, String username){
        try {
            this.socket = socket;
            this.username = username;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void sendMessage(){
        try{
//            System.out.println(username+" :"+message);

            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            Scanner scanner =new Scanner(System.in);
            while(socket.isConnected()){
                String message =scanner.nextLine();
                bufferedWriter.write(username+": "+message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                if (message.equalsIgnoreCase("bye")) {
                    System.exit(0);
                    break;
                }

            }
        } catch (IOException e) {
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                String msgfromchat;

                while(socket.isConnected()){

                    try{
                           msgfromchat = bufferedReader.readLine();
//                           client_chat.chat_display.append(msgfromchat+"\n");
                        System.out.println(msgfromchat);

                    } catch (IOException e) {
                        closeEverything(socket,bufferedReader,bufferedWriter);
                    }
                }
            }
        }).start();
    }



    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try{
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if (socket != null){
                socket.close();
=======
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
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
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String message = userInputReader.readLine();
                if (message.equalsIgnoreCase("bye")) {
                    disconnect();
                    break;
                }
                sendMessage(message);
>>>>>>> Stashed changes
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

<<<<<<< Updated upstream
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name to join the Chat");
        String username = scanner.nextLine();
        Socket socket = new Socket("192.168.8.177",55555);
        Client client = new Client(socket,username);
        client.listenForMessage();
       client.sendMessage();
    }

}
=======
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
                client_chat.chat_display.append("\nReceived message:"+ message);
                
            }
        }
    } catch (SocketException e) {
        // SocketException occurs when the socket is closed
        client_chat.chat_display.append("\nClient disconnected: " + socket.getInetAddress());
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    public void sendMessage(String message) {
        writer.println(message);
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

//    public static void Start() {
//        String host = "localhost";  // Replace with the server host
//        int port = 12345;  // Replace with the server port
//        Client client = new Client(host, port);
//        client.connect();
//    }
}

>>>>>>> Stashed changes
