package ChatEngine;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

class ClientHandler implements Runnable {

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
    }

    @Override
    public void run() {
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
            } catch (IOException e) {
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
        }
    }



    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("Server: "+username+" has left the chat!");
    }

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





