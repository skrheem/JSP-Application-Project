package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.JdbcUtil;
import model.KoujoKoumoku;

public class KoujoKoumokuDao {
	
	private static KoujoKoumokuDao kDao = new KoujoKoumokuDao();
	
	public static KoujoKoumokuDao getInstance() {
		return kDao;
	}
	
	public ArrayList<KoujoKoumoku> getKoujoKoumokuJouhou(Connection conn) {
		ArrayList<KoujoKoumoku> kList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from koujokoumoku ORDER BY koujokoumoku_id";
		
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				kList.add(new KoujoKoumoku(rs.getInt(1), rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5).charAt(0), rs.getString(6), rs.getString(7)));
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
