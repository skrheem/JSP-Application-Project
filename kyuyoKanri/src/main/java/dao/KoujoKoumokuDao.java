package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;

import model.KoujoKoumoku;

public class KoujoKoumokuDao {

	private static KoujoKoumokuDao instance;
	private Connection conn;

	private KoujoKoumokuDao(Connection conn) {
		this.conn = conn;
	}

	public static KoujoKoumokuDao getInstance(Connection conn) {
		if (instance == null) {
			instance = new KoujoKoumokuDao(conn);
		}
		return instance;
	}

	// INSERT
	public void insertKoujoKoumoku(KoujoKoumoku koujoKoumoku) throws SQLException {
		String query = "INSERT INTO koujoKoumoku (koujoKoumoku_id, koujoKoumoku_mei, koujoRitsu, keisanHouhou, shiyouUmu) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, koujoKoumoku.getKoujoKoumoku_id());
			ps.setString(2, koujoKoumoku.getKoujoKoumoku_mei());
			ps.setBigDecimal(3, koujoKoumoku.getKoujoRitsu());
			ps.setString(4, koujoKoumoku.getKeisanHouhou());
			ps.setString(5, String.valueOf(koujoKoumoku.getShiyouUmu()));
			ps.executeUpdate();
		}
	}

	// UPDATE
	public void updateKoujoKoumoku(KoujoKoumoku koujoKoumoku) throws SQLException {
		String query = "UPDATE koujoKoumoku SET koujoKoumoku_mei = ?, koujoRitsu = ?, keisanHouhou = ?, shiyouUmu = ? "
				+ "WHERE koujoKoumoku_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, koujoKoumoku.getKoujoKoumoku_mei());
			ps.setBigDecimal(2, koujoKoumoku.getKoujoRitsu());
			ps.setString(3, koujoKoumoku.getKeisanHouhou());
			ps.setString(4, String.valueOf(koujoKoumoku.getShiyouUmu()));
			ps.setInt(5, koujoKoumoku.getKoujoKoumoku_id());
			ps.executeUpdate();
		}
	}

	// DELETE
	public void deleteKoujoKoumoku(int koujoKoumoku_id) throws SQLException {
		String query = "DELETE FROM koujoKoumoku WHERE koujoKoumoku_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, koujoKoumoku_id);
			ps.executeUpdate();
		}
	}

	// SELECT
	public ArrayList<KoujoKoumoku> selectAllKoujoKoumoku() throws SQLException {
		String query = "SELECT * FROM koujoKoumoku";
		ArrayList<KoujoKoumoku> koujoKoumokuList = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				KoujoKoumoku koujoKoumoku = new KoujoKoumoku();
				koujoKoumoku.setKoujoKoumoku_id(rs.getInt("koujoKoumoku_id"));
				koujoKoumoku.setKoujoKoumoku_mei(rs.getString("koujoKoumoku_mei"));
				koujoKoumoku.setKoujoRitsu(rs.getBigDecimal("koujoRitsu"));
				koujoKoumoku.setKeisanHouhou(rs.getString("keisanHouhou"));
				koujoKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));

				koujoKoumokuList.add(koujoKoumoku);
			}
		}

		return koujoKoumokuList;
	}
}
