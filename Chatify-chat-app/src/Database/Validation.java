/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

/**
 *
 * @author user
 */
public class Validation {
    
    public boolean isValidEmail(String email) {
        String regexPattern = "^(.+)@(\\S+)$";
       if (email.matches(regexPattern)) {
           return true;
       }else{
           return false;
       }
    }


private boolean authenticateUser(String email, String password) {
    // Placeholder implementation for user authentication
    // You need to implement the actual authentication logic, such as checking against a database or user repository
    // Here's a basic implementation that checks if the email and password match a predefined value
    String validEmail = "example@example.com";
    String validPassword = "password123";
    
    return email.equals(validEmail) && password.equals(validPassword);
    }

    
}
