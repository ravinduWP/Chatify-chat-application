package ChatEngine;

import GUI.test1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    private int count=0;
    public void startServer(){

        while(!serverSocket.isClosed()){
            try {
                Socket socket = serverSocket.accept();
                test1.jTextArea1.append("\nNew Client Connected..");
                ++count;
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();

            } catch (IOException e) {
                System.out.println("Exception :"+e.getMessage());
            }

        }
    }

    public void closeServerSocket(){
        if (serverSocket != null){
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
