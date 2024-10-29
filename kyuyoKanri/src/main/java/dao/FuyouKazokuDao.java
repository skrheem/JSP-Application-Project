package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class FuyouKazokuDao {
	private static FuyouKazokuDao fDao = new FuyouKazokuDao();
	
	public static FuyouKazokuDao getInstance() {
		return fDao;
	}
	
	public int countHatachiKazoku(Connection conn, Integer shain_id) {
		int hatachiCount = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select COUNT(*) from fuyoukazoku "
				+ "where shain_id = ? AND HATACHIUMU = 'Y'";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, shain_id);
			rs = ps.executeQuery();
			if(rs.next())
				hatachiCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return hatachiCount;
	}
}
