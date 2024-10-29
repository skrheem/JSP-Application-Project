package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ShainKoujoKiroku;

public class ShainKoujoKirokuDao {
private static ShainKoujoKirokuDao skjDao = new ShainKoujoKirokuDao();
	
	public static ShainKoujoKirokuDao getInstance() {
		return skjDao;
	}
	
	public ArrayList<ShainKoujoKiroku> getShainKoujoKiroku(Connection conn, Integer shain_id) {
		ArrayList<ShainKoujoKiroku> skjList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select sk.*, kj.koujokoumoku_mei, kj.keisanHouhou "
					+ "from shainkoujokiroku sk "
					+ "JOIN koujokoumoku kj ON sk.koujokoumoku_id = kj.koujokoumoku_id "
					+ "where shain_id = ? AND shiyouumu = 'Y' ORDER BY kj.koujokoumoku_id";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, shain_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				skjList.add(new ShainKoujoKiroku(rs.getInt(1), rs.getInt(2), rs.getBigDecimal(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
		return skjList;
	}
}
