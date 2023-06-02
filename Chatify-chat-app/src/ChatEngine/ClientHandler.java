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

    public ClientHandler(Socket clientSocket, Server server) throws IOException {
        this.clientSocket = clientSocket;
        this.server = server;
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("bye")) {
                    break; // Disconnect if client says "bye"
                }
                server.broadcast(message, this);
            }
        } catch (IOException e) {
            System.out.println("client disconnected");
        } finally {
            try {
                clientSocket.close();
                server.removeClient(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

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
