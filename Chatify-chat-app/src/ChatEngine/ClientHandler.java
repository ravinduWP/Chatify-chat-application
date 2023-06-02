package ChatEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Server server;
    private final BufferedReader in;
    private final PrintWriter out;

<<<<<<< Updated upstream
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private String username;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ClientHandler(Socket socket) {
        try {
            this.socket =socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("Server: "+username+" has enter the chat! ");
        } catch (IOException e) {
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
=======
    public ClientHandler(Socket clientSocket, Server server) throws IOException {
        this.clientSocket = clientSocket;
        this.server = server;
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
>>>>>>> Stashed changes
    }

    public void run() {
<<<<<<< Updated upstream
        String message;
        while(socket.isConnected()){
            try {
                message = bufferedReader.readLine();
                broadcastMessage(message);

            } catch (IOException e) {
                closeEverything(socket,bufferedReader,bufferedWriter);
                break;
            }
        }
    }

    private void broadcastMessage(String messagetosend) {

        for (ClientHandler clientHandler : clientHandlers){

            try{
                if (!clientHandler.username.equals(username)){
                    clientHandler.bufferedWriter.write(messagetosend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();

               }
=======
        try {
            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("bye")) {
                    break; // Disconnect if client says "bye"
                }
                server.broadcast(message, this);
            }
        } catch (IOException e) {
            System.out.println("client disconnected:");
        } finally {
            try {
                clientSocket.close();
                server.removeClient(this);
>>>>>>> Stashed changes
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

<<<<<<< Updated upstream


    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
            removeClientHandler();
            try{
                if (bufferedReader != null){
                    bufferedReader.close();
                }
                if (bufferedWriter != null){
                    bufferedWriter.close();
                }
                if (socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





=======
    public Socket getClientSocket() {
        return clientSocket;
    }
    public void stop() {
        try {
            // Close the client socket
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
>>>>>>> Stashed changes
