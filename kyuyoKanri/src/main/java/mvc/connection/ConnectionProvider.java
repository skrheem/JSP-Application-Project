package mvc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String JDBC_USER = "system";
	private static final String JDBC_PASSWORD = "1234";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new SQLException("Oracle JDBC 드라이버를 찾을 수 없습니다.", e);
		}

		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
}
