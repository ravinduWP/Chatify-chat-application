package ChatEngine;

import java.io.*;
import java.net.Socket;
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
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            Scanner scanner = new Scanner(System.in);
            while(socket.isConnected()){
                String msgtosend =  scanner.nextLine();
                bufferedWriter.write(username+": "+msgtosend);
                bufferedWriter.newLine();
                bufferedWriter.flush();


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
        System.out.println("Enter ur name to join the chat: ");
        String username =scanner.nextLine();
        Socket socket = new Socket("192.168.8.177",55555);
        Client client = new Client(socket,username);
        client.listenForMessage();
        client.sendMessage();
    }
}
