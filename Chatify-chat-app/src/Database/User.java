package Database;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private String Email;
    private String usname;
    private String nickname;
    private String pword;
    private String propic;

    private ConnectDB db;
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsname() {
        return usname;
    }

    public void setUsname(String usname) {
        this.usname = usname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    public String getPropic() {
        return propic;
    }

    public void setPropic(String propic) {
        this.propic = propic;
    }

    public void registerUser(String email,String Username,String Nickname,String pwd) throws SQLException, ClassNotFoundException {
         db = new ConnectDB();
        Statement st = db.getConn().createStatement();
        String query = "INSERT INTO user VALUES('"+email+"','"+Username+"','"+Nickname+"','"+pwd+"')";
        int row = st.executeUpdate(query);
        if (row>0){
            JOptionPane.showMessageDialog(null,"User Register Successfully");
        }
        else {
            JOptionPane.showMessageDialog(null,"User Registeration failed, Check Email and try Again");
        }
    }

    public boolean loginUser(String Email,String pw) throws SQLException, ClassNotFoundException {
        boolean flag = true;
        db = new ConnectDB();
        Statement st = db.getConn().createStatement();
        String qry = "SELECT username,nickname FROM user WHERE email='"+Email+"' AND password='"+pw+"'";
        ResultSet rs = st.executeQuery(qry);

        if (rs.next()){
            setUsname(rs.getString(1));
            setNickname(rs.getString(2));
            setEmail(Email);
            flag = true;
        }else{
            flag = false;
        }
        return flag;
    }
    public boolean checkEmail(String email) throws SQLException, ClassNotFoundException {
        boolean flag=false;
        db= new ConnectDB();
        Statement st = db.getConn().createStatement();
        String query = "SELECT email FROM user WHERE email="+"'email'";
        ResultSet rs = st.executeQuery(query);
        int row = rs.getRow();
        if (row==1){
            flag= true;
        }
        return flag;
    }

    public void userUpdate(String name,String nick,String pwd) throws SQLException, ClassNotFoundException {
        db= new ConnectDB();
        Statement st = db.getConn().createStatement();
        String qry = "UPDATE user SET username='"+name+"',nickname='"+nick+"',password='"+pwd+"' WHERE email='"+getEmail()+"'";
        int rows = st.executeUpdate(qry);
        if(rows>0){
            JOptionPane.showMessageDialog(null,"User Updated Successfully..!");
        }else{
            JOptionPane.showMessageDialog(null,"User Updated failed..!");
        }
    }

    public ResultSet userDetails() throws SQLException, ClassNotFoundException {

        db = new ConnectDB();
        Statement st = db.getConn().createStatement();
        String query = "SELECT username,nickname,password FROM user WHERE email='"+getEmail()+"'";
        ResultSet rs = st.executeQuery(query);

        return rs;
    }


}
