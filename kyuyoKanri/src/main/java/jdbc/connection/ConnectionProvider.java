package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    public static Connection getConnection() throws SQLException {
        // DBCP 없이 직접 JDBC 연결을 생성
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "system";
        String password = "1234";
        return DriverManager.getConnection(url, user, password);
    }
}