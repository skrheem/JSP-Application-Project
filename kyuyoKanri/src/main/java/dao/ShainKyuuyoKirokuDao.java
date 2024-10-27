package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		}
			
		return skkList;
	}
}
