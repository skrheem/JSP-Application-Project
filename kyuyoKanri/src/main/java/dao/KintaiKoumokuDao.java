package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import model.KintaiKoumoku;

public class KintaiKoumokuDao {
	private static KintaiKoumokuDao instance = new KintaiKoumokuDao();
	public static KintaiKoumokuDao getInstance() {
		return instance;
	}
	public ArrayList<KintaiKoumoku> getKintaiMei(Connection conn) {
		ArrayList<KintaiKoumoku> kList = new ArrayList<>();
		String query = "select kintai_mei from kintaikoumoku";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				kList.add(new KintaiKoumoku(rs.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
		return kList;
	}
}
