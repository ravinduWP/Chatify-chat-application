package Database;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private String Email;
    private String username;
    private String nickname;
    private String password;
    private String propic;
    
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPropic() {
        return propic;
    }

    public void setPropic(String propic) {
        this.propic = propic;
    }

    public void registerUser() throws SQLException, ClassNotFoundException {
        ConnectDB db = new ConnectDB();
        Statement st = db.getConn().createStatement();
        String query = "INSERT INTO chatify_db VALUES('"+Email+"','"+username+"','"+nickname+"','"+password+"','"+propic+"')";
        int row = st.executeUpdate(query);
        if (row>1){
            JOptionPane.showMessageDialog(null,"User Register Successfully");
        }
        else {
            JOptionPane.showMessageDialog(null,"User Registeration failed, Check Email and try Again");
        }
    }
    
    public boolean loginUser(String email, String password) throws ClassNotFoundException, SQLException {
        Boolean flag = false;
        ConnectDB db = new ConnectDB();
        Statement st = db.getConn().createStatement();
        String query = "SELECT * FROM user WHERE email='"+email+"' AND password='"+password+"'";
    // Validate the email and password
    if (new Validation().isValidEmail(email)&& !password.matches("")) {
        ResultSet rs = st.executeQuery(query);
        if (rs.next()){
            JOptionPane.showMessageDialog(null,"success!");
            flag=true;
        }else {
            JOptionPane.showMessageDialog(null,"check email and Password");
            flag=false;
        }

        
    } else {
        JOptionPane.showMessageDialog(null,"Fill all the fields");
        flag=false;

        // Display error message or take appropriate action
        }
        return flag;
    }
}
