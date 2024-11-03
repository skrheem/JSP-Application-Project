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
		String query = "MERGE INTO shainkoujokiroku target "
				+ "USING (SELECT ? AS shain_id, "
				+ "              ? AS koujokoumoku_id, "
				+ "              ? AS koujokoumoku_kingaku, "
				+ "              TO_DATE(?, 'YYYY-MM-DD') AS koujokoumoku_nengappi, "
				+ "              ? AS kyuuyo_jisuu "
				+ "       FROM dual) source "
				+ "ON (target.shain_id = source.shain_id "
				+ "    AND target.koujokoumoku_id = source.koujokoumoku_id "
				+ "    AND target.koujokoumoku_nengappi = source.koujokoumoku_nengappi "
				+ "    AND target.kyuuyo_jisuu = source.kyuuyo_jisuu) "
				+ "WHEN MATCHED THEN "
				+ "    UPDATE SET target.koujokoumoku_kingaku = source.koujokoumoku_kingaku "
				+ "WHEN NOT MATCHED THEN "
				+ "    INSERT (shain_id, koujokoumoku_id, koujokoumoku_kingaku, koujokoumoku_nengappi, kyuuyo_jisuu) "
				+ "    VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)";
		int rValue = 0;
		
		 
		try {
			ps = conn.prepareStatement(query);

			ps.setInt(1, shain_id);
			ps.setInt(2, koujokoumoku_id);
			ps.setInt(3, koujokoumoku_kingaku);
			ps.setString(4, koujokoumoku_nengappi);
			ps.setString(5, kyuuyo_jisuu);
			ps.setInt(6, shain_id);
			ps.setInt(7, koujokoumoku_id);
			ps.setInt(8, koujokoumoku_kingaku);
			ps.setString(9, koujokoumoku_nengappi);
			ps.setString(10, kyuuyo_jisuu);
			
			rValue = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
		return rValue;
	}
}
