package Database;

import javax.swing.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    

    public void registerUser(String Email,String username,String nickname,String password) throws SQLException, ClassNotFoundException {
        ConnectDB db = new ConnectDB();
        Statement st = db.getConn().createStatement();
        String query = "INSERT INTO user VALUES('" + Email + "','" + username + "','" + nickname + "','" + password + "')";
        int row = st.executeUpdate(query);
        if (row > 0) {
            JOptionPane.showMessageDialog(null, "User Register Successfully");
        } else {
            JOptionPane.showMessageDialog(null, "User Registeration failed, Check Email and try Again");
        }
    }

    public ResultSet loginUser(String email, String password)  {



            try {

                ConnectDB db = new ConnectDB();
                Statement st = db.getConn().createStatement();
                String query = "SELECT * FROM user WHERE email='" + email + "' AND password='" + password + "'";
                ResultSet rs = st.executeQuery(query);
                return rs;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);

        }


    }
    
    public ResultSet allUsers(){
        ResultSet rs;
        try {
            ConnectDB db = new ConnectDB();
            Statement st = db.getConn().createStatement();
            String query = "SELECT * FROM user";
            rs = st.executeQuery(query);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rs;
    }
    public void deleteUser(String mail){
        try {
            ConnectDB db = new ConnectDB();
            Statement st = db.getConn().createStatement();
            String query = "DELETE FROM user WHERE email='"+mail+"'";
            int rows= st.executeUpdate(query);
            if (rows>0){
                JOptionPane.showMessageDialog(null,"User Deleted...!!");
            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public ResultSet allchats(){
        ResultSet rs;
        try {
            ConnectDB db = new ConnectDB();
            Statement st = db.getConn().createStatement();
            String query = "SELECT * FROM chat";
            rs = st.executeQuery(query);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rs;
    }
    
    public void createchat(String cname){
    
       
        try {
            ConnectDB db = new ConnectDB();
            Statement st = db.getConn().createStatement();
            String query = "INSERT INTO chat (name) VALUES ('"+cname+"')";
            int row = st.executeUpdate(query);
            if (row>0){
                JOptionPane.showMessageDialog(null,"Chat created successfully");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

}
