package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private Connection conn;


    public ConnectDB() throws ClassNotFoundException, SQLException {
        String path = "jdbc:mysql://localhost:3306/chatify_db";
        String user = "root";
        String pwd = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn = DriverManager.getConnection(path,user,pwd);
    }

    public Connection getConn() {
        return conn;
    }
}
