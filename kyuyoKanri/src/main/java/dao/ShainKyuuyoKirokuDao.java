package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import model.ShainKyuuyoKiroku;

public class ShainKyuuyoKirokuDao {
	private static ShainKyuuyoKirokuDao skkDao = new ShainKyuuyoKirokuDao();
	
	public static ShainKyuuyoKirokuDao getInstance() {
		return skkDao;
	}
	
	public ArrayList<ShainKyuuyoKiroku> getShainKyuuyoKiroku(Connection conn, Integer shain_id) {
		ArrayList<ShainKyuuyoKiroku> skkList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select sk.*, kj.kyuuyokoumoku_mei, kj.keisanHouhou "
				+ "from shainkyuuyokiroku sk "
				+ "JOIN kyuuyokoumoku kj ON sk.kyuuyokoumoku_id = kj.kyuuyokoumoku_id "
				+ "where shain_id = ? ORDER BY kj.kyuuyoKoumoku_id";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, shain_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				skkList.add(new ShainKyuuyoKiroku(rs.getInt(1), rs.getInt(2), rs.getBigDecimal(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
			
		return skkList;
	}
	
	public int insertShainKyuuyoKiroku(Connection conn, Integer shain_id, Integer kyuuyokoumoku_id, int kyuuyokoumoku_kingaku, String kyuuyokoumoku_nengappi, String kyuuyo_jisuu) {
		PreparedStatement ps = null;
		String query = "MERGE INTO shainkyuuyoKiroku target "
				+ "USING (SELECT ? AS shain_id, "
				+ "              ? AS kyuuyokoumoku_id, "
				+ "              ? AS kyuuyokoumoku_kingaku, "
				+ "              TO_DATE(?, 'YYYY-MM-DD') AS kyuuyokoumoku_nengappi, "
				+ "              ? AS kyuuyo_jisuu "
				+ "       FROM dual) source "
				+ "ON (target.shain_id = source.shain_id "
				+ "    AND target.kyuuyokoumoku_id = source.kyuuyokoumoku_id "
				+ "    AND target.kyuuyokoumoku_nengappi = source.kyuuyokoumoku_nengappi "
				+ "    AND target.kyuuyo_jisuu = source.kyuuyo_jisuu) "
				+ "WHEN MATCHED THEN"
				+ "    UPDATE SET target.kyuuyokoumoku_kingaku = source.kyuuyokoumoku_kingaku "
				+ "WHEN NOT MATCHED THEN "
				+ "    INSERT (shain_id, kyuuyokoumoku_id, kyuuyokoumoku_kingaku, kyuuyokoumoku_nengappi, kyuuyo_jisuu) "
				+ "    VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)";
		int rValue = 0;
		
		 
		try {
			ps = conn.prepareStatement(query);

			ps.setInt(1, shain_id);
			ps.setInt(2, kyuuyokoumoku_id);
			ps.setInt(3, kyuuyokoumoku_kingaku);
			ps.setString(4, kyuuyokoumoku_nengappi);
			ps.setString(5, kyuuyo_jisuu);
			ps.setInt(6, shain_id);
			ps.setInt(7, kyuuyokoumoku_id);
			ps.setInt(8, kyuuyokoumoku_kingaku);
			ps.setString(9, kyuuyokoumoku_nengappi);
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
