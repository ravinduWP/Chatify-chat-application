package ChatEngine;

import GUI.client_chat;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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