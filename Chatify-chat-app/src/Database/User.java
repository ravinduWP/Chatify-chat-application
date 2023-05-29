package Database;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    

    public void registerUser(String Email,String username,String nickname,String password) throws SQLException, ClassNotFoundException {
        ConnectDB db = new ConnectDB();
        Statement st = db.getConn().createStatement();
        String query = "INSERT INTO chatify_db VALUES('" + Email + "','" + username + "','" + nickname + "','" + password + "')";
        int row = st.executeUpdate(query);
        if (row > 1) {
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
}
