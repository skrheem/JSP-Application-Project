package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import model.ShainTekiyouKoujoKoumoku;
import util.ObjectFormatter;

public class ShainTekiyouKoujoKoumokuDao {

	private static ShainTekiyouKoujoKoumokuDao tekiyouKoujoKoumokuDao = new ShainTekiyouKoujoKoumokuDao();

	public static ShainTekiyouKoujoKoumokuDao getInstance() {
		return tekiyouKoujoKoumokuDao;
	}

	public ArrayList<ShainTekiyouKoujoKoumoku> getKoujoJouhou(Connection conn, Integer shain_id) {
		ArrayList<ShainTekiyouKoujoKoumoku> stjList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT kj.koujoKoumoku_mei, kj.koujoRitsu, kj.keisanHouhou "
				+ "FROM ShainTekiyouKoujoKoumoku stj " 
				+ "JOIN Shain s ON stj.shain_id = s.shain_id "
				+ "JOIN KoujoKoumoku kj ON stj.koujoKoumoku_id = kj.koujoKoumoku_id " 
				+ "WHERE stj.shain_id = ?";

		try {
			ps = conn.prepareStatement(query);

			ps.setInt(1, shain_id);

			rs = ps.executeQuery();
			while (rs.next()) {
				stjList.add(new ShainTekiyouKoujoKoumoku(rs.getString(1), rs.getDouble(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return stjList;
	}
	public ArrayList<ShainTekiyouKoujoKoumoku> getShainTekiyouKoujoKingaku(Connection conn, Integer shain_id) {
		ArrayList<ShainTekiyouKoujoKoumoku> stkList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select kj.koujokoumoku_id, kj.koujoritsu, kj.koujokoumoku_mei, sk.kihonkyuu "
				+ "from Shain s "
				+ "JOIN shainkihonkyuu sk ON s.shain_id = sk.shain_id "
				+ "JOIN shaintekiyoukoujokoumoku skj ON s.shain_id = skj.shain_id "
				+ "JOIN koujokoumoku kj ON skj.koujokoumoku_id = kj.koujokoumoku_id "
				+ "WHERE s.shain_id = ? "
				+ "ORDER BY koujokoumoku_id";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, shain_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				stkList.add(new ShainTekiyouKoujoKoumoku(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getBigDecimal(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return stkList;
	}
}
