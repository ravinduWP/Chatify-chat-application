/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class messageServer {
    
    public void saveMessageToFile(String message) {
        try {
            FileWriter writer = new FileWriter("chat.txt", true);
            writer.write(message);
            writer.write(System.lineSeparator());
            writer.close();
//            System.out.println("Message saved successfully!");
        } catch (IOException e) {
//            System.out.println("An error occurred while saving the message: " + e.getMessage());
        }
    
    }
//    public static void main(String[] args) {
//        messageServer msr = new messageServer();
//        msr.saveMessageToFile("this is atest msg");
//    }
    
  
}
