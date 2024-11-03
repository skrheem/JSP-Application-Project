package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class ShainKoujoKirokuDao {
	
	private static ShainKoujoKirokuDao sDao = new ShainKoujoKirokuDao();
	
	public static ShainKoujoKirokuDao getInstance() {
		return sDao;
	}
	
	public int insertShainKoujoKiroku(Connection conn, Integer shain_id, Integer koujokoumoku_id, int koujokoumoku_kingaku, String koujokoumoku_nengappi, String kyuuyo_jisuu) {
		PreparedStatement ps = null;
		String query = "INSERT INTO shainkoujokiroku "
				+ "(shain_id, koujokoumoku_id, koujokoumoku_kingaku, koujokoumoku_nengappi, kyuuyo_jisuu) "
				+ "VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)";
		int rValue = 0;
		
		 
		try {
			ps = conn.prepareStatement(query);

			ps.setInt(1, shain_id);
			ps.setInt(2, koujokoumoku_id);
			ps.setInt(3, koujokoumoku_kingaku);
			ps.setString(4, koujokoumoku_nengappi);
			ps.setString(5, kyuuyo_jisuu);
			
			rValue = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
		return rValue;
	}
}
