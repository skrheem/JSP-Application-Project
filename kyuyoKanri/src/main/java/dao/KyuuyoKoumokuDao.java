package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;

import model.KyuuyoKoumoku;

public class KyuuyoKoumokuDao {

	private static KyuuyoKoumokuDao instance;
	private Connection conn;

	private KyuuyoKoumokuDao(Connection conn) {
		this.conn = conn;
	}

	public static KyuuyoKoumokuDao getInstance(Connection conn) {
		if (instance == null) {
			instance = new KyuuyoKoumokuDao(conn);
		}
		return instance;
	}

	// INSERT
	public void insertKyuuyoKoumoku(KyuuyoKoumoku kyuuyoKoumoku) throws SQLException {
		String query = "INSERT INTO kyuuyoKoumoku (kyuuyoKoumoku_id, kyuuyoKoumoku_mei, kazeiKubun, hikazeiGendogaku, "
				+ "bikou, keisanHouhou, zenshaDan_i, kintaiRenkei, ikkatsuShiharai, ikkatsuShiharaiGaku, shiyouUmu) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, kyuuyoKoumoku.getKyuuyoKoumoku_id());
			ps.setString(2, kyuuyoKoumoku.getKyuuyoKoumoku_mei());
			ps.setString(3, kyuuyoKoumoku.getKazeiKubun());
			ps.setBigDecimal(4, kyuuyoKoumoku.getHikazeiGendogaku());
			ps.setString(5, kyuuyoKoumoku.getBikou());
			ps.setString(6, kyuuyoKoumoku.getKeisanHouhou());
			ps.setString(7, kyuuyoKoumoku.getZenshaDani());
			ps.setString(8, kyuuyoKoumoku.getKintaiRenkei());
			ps.setString(9, kyuuyoKoumoku.getIkkatsuShiharai());
			ps.setBigDecimal(10, kyuuyoKoumoku.getIkkatsuShiharaiGaku());  
			ps.setString(11, String.valueOf(kyuuyoKoumoku.getShiyouUmu()));
			ps.executeUpdate();
		}
	}

	// UPDATE
	public void updateKyuuyoKoumoku(KyuuyoKoumoku kyuuyoKoumoku) throws SQLException {
		String query = "UPDATE kyuuyoKoumoku SET kyuuyoKoumoku_mei = ?, kazeiKubun = ?, hikazeiGendogaku = ?, "
				+ "bikou = ?, keisanHouhou = ?, zenshaDan_i = ?, kintaiRenkei = ?, "
				+ "ikkatsuShiharai = ?, ikkatsuShiharaiGaku = ?, shiyouUmu = ? WHERE kyuuyoKoumoku_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, kyuuyoKoumoku.getKyuuyoKoumoku_mei());
			ps.setString(2, kyuuyoKoumoku.getKazeiKubun());
			ps.setBigDecimal(3, kyuuyoKoumoku.getHikazeiGendogaku());
			ps.setString(4, kyuuyoKoumoku.getBikou());
			ps.setString(5, kyuuyoKoumoku.getKeisanHouhou());
			ps.setString(6, kyuuyoKoumoku.getZenshaDani());
			ps.setString(7, kyuuyoKoumoku.getKintaiRenkei());
			ps.setString(8, kyuuyoKoumoku.getIkkatsuShiharai());
			ps.setBigDecimal(9, kyuuyoKoumoku.getIkkatsuShiharaiGaku());
			ps.setString(10, String.valueOf(kyuuyoKoumoku.getShiyouUmu()));
			ps.setInt(11, kyuuyoKoumoku.getKyuuyoKoumoku_id());
			ps.executeUpdate();
		}
	}

	// DELETE
	public void deleteKyuuyoKoumoku(int kyuuyoKoumoku_id) throws SQLException {
		String query = "DELETE FROM kyuuyoKoumoku WHERE kyuuyoKoumoku_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, kyuuyoKoumoku_id);
			ps.executeUpdate();
		}
	}

	// SELECT
	public ArrayList<KyuuyoKoumoku> selectAllKyuuyoKoumoku() throws SQLException {
		String query = "SELECT * FROM kyuuyoKoumoku";
		ArrayList<KyuuyoKoumoku> kyuuyoKoumokuList = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				KyuuyoKoumoku kyuuyoKoumoku = new KyuuyoKoumoku();
				kyuuyoKoumoku.setKyuuyoKoumoku_id(rs.getInt("kyuuyoKoumoku_id"));
				kyuuyoKoumoku.setKyuuyoKoumoku_mei(rs.getString("kyuuyoKoumoku_mei"));
				kyuuyoKoumoku.setKazeiKubun(rs.getString("kazeiKubun"));
				kyuuyoKoumoku.setHikazeiGendogaku(rs.getBigDecimal("hikazeiGendogaku"));
				kyuuyoKoumoku.setBikou(rs.getString("bikou"));
				kyuuyoKoumoku.setKeisanHouhou(rs.getString("keisanHouhou"));
				kyuuyoKoumoku.setZenshaDani(rs.getString("zenshaDani"));
				kyuuyoKoumoku.setKintaiRenkei(rs.getString("kintaiRenkei"));
				kyuuyoKoumoku.setIkkatsuShiharai(rs.getString("ikkatsuShiharai"));
				kyuuyoKoumoku.setIkkatsuShiharaiGaku(rs.getBigDecimal("ikkatsuShiharaiGaku"));
				kyuuyoKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));

				kyuuyoKoumokuList.add(kyuuyoKoumoku);
			}
		}

		return kyuuyoKoumokuList;
	}
}
