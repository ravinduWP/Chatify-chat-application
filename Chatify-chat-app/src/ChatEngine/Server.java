package ChatEngine;

import GUI.Serverstat;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final int port;
    private final List<ClientHandler> clients;
    private ServerSocket serverSocket;
    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
    }

    public void start() {
        try {
            // Create a server socket
            this.serverSocket = new ServerSocket(port);
            
//            System.out.println("Server started on port " + port);
            Serverstat.stat.append("Server started on port " + port);

            while (true) {
                // Accept client connections
                Socket clientSocket = serverSocket.accept();
//                System.out.println("Client connected: " + clientSocket.getInetAddress());
                Serverstat.stat.append("\nClient connected: " + clientSocket.getInetAddress());                // Create a new client handler for each client
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);

                // Add the client handler to the list of clients
                clients.add(clientHandler);

                // Start a new thread to handle the client
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            // Skip sending the message to the sender
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
        String disconnectMessage = "\nClient disconnected: " + client.getClientSocket().getInetAddress();
        broadcast(disconnectMessage, null);
        Serverstat.stat.append(disconnectMessage);
        // Display a message when a client disconnects
        
//        System.out.println(disconnectMessage);

    }
    public void stop() throws IOException {
        
            // Close the server socket
//            System.out.println(this.serverSocket==null);
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            // Terminate all client connections
            for (ClientHandler client : clients) {
                client.stop();
            }
        
    
    }


}
