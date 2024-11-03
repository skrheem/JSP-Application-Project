package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import model.KyuuyoKoumoku;
import util.ObjectFormatter;

public class KyuuyoKoumokuDao {
	
	private static KyuuyoKoumokuDao kDao = new KyuuyoKoumokuDao();
	
	public static KyuuyoKoumokuDao getInstance() {
		return kDao;
	}
	
	public ArrayList<KyuuyoKoumoku> getKyuuyoKoumokuJouhou(Connection conn) {
		ArrayList<KyuuyoKoumoku> kList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from kyuuyokoumoku koujokoumoku where shiyouumu = 'Y' ORDER BY kyuuyokoumoku_id";
		
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				kList.add(new KyuuyoKoumoku(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBigDecimal(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBigDecimal(10), rs.getString(11)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return kList;
	}
	
	public ArrayList<KyuuyoKoumoku> getKyuuyoKoumokuKingaku(Connection conn, Integer shain_id, String kyuuyoNengappi) {
		ArrayList<KyuuyoKoumoku> kList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * "
				+ "FROM ( "
				+ "    SELECT sk.kyuuyokoumoku_id, sk.KYUUYOKOUMOKU_KINGAKU "
				+ "    FROM shainkyuuyokiroku sk "
				+ "    WHERE sk.shain_id = ? "
				+ "      AND sk.KYUUYOKOUMOKU_NENGAPPI = TO_DATE(?, 'YYYY-MM-DD') "
				+ "      AND sk.KYUUYOKOUMOKU_ID != 1 "
				+ "    UNION ALL "
				+ "    SELECT kk.kyuuyokoumoku_id, NVL(kk.hikazeigendogaku, 0) AS hikazeigendogaku "
				+ "    FROM kyuuyokoumoku kk "
				+ "    WHERE kk.shiyouumu = 'Y' "
				+ "      AND kk.kyuuyokoumoku_mei != '基本給' "
				+ "      AND NOT EXISTS ( "
				+ "          SELECT 1  "
				+ "          FROM shainkyuuyokiroku sk "
				+ "          WHERE sk.shain_id = ? "
				+ "            AND sk.KYUUYOKOUMOKU_NENGAPPI = TO_DATE(?, 'YYYY-MM-DD') "
				+ "      ) "
				+ ") result";
		try {
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, shain_id);
			ps.setString(2, kyuuyoNengappi);
			ps.setInt(3, shain_id);
			ps.setString(4, kyuuyoNengappi);
			
			rs = ps.executeQuery();
			while(rs.next())
				kList.add(new KyuuyoKoumoku(rs.getInt(1), rs.getBigDecimal(2)));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return kList;
	}
}
