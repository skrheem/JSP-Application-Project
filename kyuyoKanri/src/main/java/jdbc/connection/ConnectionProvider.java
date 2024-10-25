package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	public static Connection getConnection() throws SQLException {
		// 만들어둔 데이터베이스 커넥션 풀 board와 연결 후 Connection을 반환
		//return DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "system";
        String password = "1234";
        return DriverManager.getConnection(url, user, password);
	}
}
