package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ZeigakuHyouDao {
	public static void main(String a[]) {
		ZeigakuHyouDao zDao = ZeigakuHyouDao.getInstance();
		FuyouKazokuDao fDao = FuyouKazokuDao.getInstance();
		try {
			Connection conn = ConnectionProvider.getConnection();
			System.out.println(zDao.keisanZeigaku(conn, 5, 3000000, fDao.countHatachiKazoku(conn, 5)));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private static ZeigakuHyouDao zDao = new ZeigakuHyouDao();

	public static ZeigakuHyouDao getInstance() {
		return zDao;
	}

	public int keisanZeigaku(Connection conn, Integer shain_id, int kihonkyuu,  int countHatachi) {
		int zeigaku = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT shotoku_zei " 
				+ "FROM zeiGakuHyou "
				+ "WHERE ijyou <= ((SELECT kihonkyuu FROM SHAINKIHONKYUU WHERE shain_id = ?) / 1000) "
				+ "AND miman >= ((SELECT kihonkyuu FROM SHAINKIHONKYUU WHERE shain_id = ?) / 1000) "
				+ "AND kazokusuu = ( " 
				+ "CASE "
				+ "WHEN (SELECT count(*) FROM fuyoukazoku WHERE shain_id = ? AND DOUKYOUMU = 'Y') = 0 "
				+ "THEN 1 "
				+ "ELSE (SELECT count(*) FROM fuyoukazoku WHERE shain_id = ? AND DOUKYOUMU = 'Y') "
				+ "END)";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, shain_id);
			ps.setInt(2, shain_id);
			ps.setInt(3, shain_id);
			ps.setInt(4, shain_id);
			rs = ps.executeQuery();
			if(rs.next())
				zeigaku = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		if(countHatachi == 1) 
			return (zeigaku - 12500 <= 0) ? 0 : zeigaku - 12500;
		else if(countHatachi == 2) 
			return (zeigaku - 29160 <= 0) ? 0 : zeigaku - 29160;
		else if(countHatachi >= 3)
			return (zeigaku - (29160 + (25000 * (countHatachi - 2))) <= 0) ? 0 : zeigaku - (29160 + (25000 * (countHatachi - 2)));

		return zeigaku;
	}
}
