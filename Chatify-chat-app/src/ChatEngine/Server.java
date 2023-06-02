package ChatEngine;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
import GUI.test1;

=======
import GUI.Serverstat;
>>>>>>> Stashed changes
=======
import GUI.Serverstat;
>>>>>>> Stashed changes
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

=======
    private final int port;
    private final List<ClientHandler> clients;
>>>>>>> Stashed changes
=======
    private final int port;
    private final List<ClientHandler> clients;
>>>>>>> Stashed changes
    private ServerSocket serverSocket;
    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
    }
<<<<<<< Updated upstream
<<<<<<< Updated upstream

    public void startServer(){

        while(!serverSocket.isClosed()){
            try {
                Socket socket = serverSocket.accept();
                test1.jTextArea1.append("\nNew Client Connected..");
                ClientHandler clientHandler = new ClientHandler(socket);
=======

    public void start() {
        try {
            // Create a server socket
             serverSocket = new ServerSocket(port);
            Serverstat.stat.append("Server started on port " + port);
//            System.out.println("Server started on port " + port);
=======

    public void start() {
        try {
            // Create a server socket
            this.serverSocket = new ServerSocket(port);
            
            System.out.println("Server started on port " + port);
>>>>>>> Stashed changes

            while (true) {
                // Accept client connections
                Socket clientSocket = serverSocket.accept();
<<<<<<< Updated upstream
                Serverstat.stat.append("\nClient connected: " + clientSocket.getInetAddress());
//                System.out.println("Client connected: " + clientSocket.getInetAddress());
=======
                System.out.println("Client connected: " + clientSocket.getInetAddress());
>>>>>>> Stashed changes

                // Create a new client handler for each client
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);

                // Add the client handler to the list of clients
                clients.add(clientHandler);

                // Start a new thread to handle the client
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
         
        String disconnectMessage = "\nClient disconnected: " + client.getClientSocket().getInetAddress();
=======
        String disconnectMessage = "Client disconnected: " + client.getClientSocket().getInetAddress();
>>>>>>> Stashed changes
        broadcast(disconnectMessage, null);
        Serverstat.stat.append(disconnectMessage);
        // Display a message when a client disconnects
//        System.out.println(disconnectMessage);

    }
<<<<<<< Updated upstream
    public void stop() {
        try {
            // Close the server socket
=======
    public void stop() throws IOException {
        
            // Close the server socket
//            System.out.println(this.serverSocket==null);
>>>>>>> Stashed changes
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            // Terminate all client connections
            for (ClientHandler client : clients) {
                client.stop();
            }
<<<<<<< Updated upstream
        } catch (IOException e) {
            e.printStackTrace();
        }
=======
        
    
>>>>>>> Stashed changes
    }


}
