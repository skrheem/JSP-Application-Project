package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.JdbcUtil;
import model.KyuuyoKoumoku;

public class KyuuyoKoumokuDao {
	
	private static KyuuyoKoumokuDao kDao = new KyuuyoKoumokuDao();
	
	public static KyuuyoKoumokuDao getInstance() {
		return kDao;
	}
	
	public ArrayList<KyuuyoKoumoku> getKyuuyoKoumokuJouhou(Connection conn) {
		ArrayList<KyuuyoKoumoku> kList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from kyuuyokoumoku ORDER BY kyuuyokoumoku_id";
		
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				kList.add(new KyuuyoKoumoku(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBigDecimal(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBigDecimal(10), rs.getString(11).charAt(0)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return kList;
	}
}
