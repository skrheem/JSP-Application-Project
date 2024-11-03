package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class KoukinzeiDao {
	private static KoukinzeiDao kDao = new KoukinzeiDao();

	public static KoukinzeiDao getInstance() {
		return kDao;
	}

	public String getKoukinzei(Connection conn, Integer shain_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String koukinzei = "";
		String query = "select koukinzei_kubun from koukinzei where shain_id = ?";
		try {
			ps = conn.prepareStatement(query);

			ps.setInt(1, shain_id);

			rs = ps.executeQuery();
			if (rs.next()) {
				koukinzei = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return koukinzei;
	}
}
