package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import model.KintaiKoumoku;

public class KintaiKoumokuDao {
	private Connection connection;

	public void insertKintaiKoumoku(KintaiKoumoku kintai) {
		String query = "INSERT INTO KintaiKoumoku (kintai_id, kintai_mei, tani, kyuukaKoumoku_id, "
				+ "group_id, roudouJikanRenkei, shiyouUmu) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(query);
			ps.setObject(1, kintai.getKintai_id());
			ps.setString(2, kintai.getKintai_mei());
			ps.setString(3, kintai.getTani());
			ps.setObject(4, kintai.getKyuukaKoumoku_id());
			ps.setObject(5, kintai.getGroup_id());
			ps.setString(6, kintai.getRoudouJikanRenkei());
			ps.setString(7, String.valueOf(kintai.getShiyouUmu()));
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
	}

	public KintaiKoumoku getKintaiKoumokuById(Integer kintai_id) {
		KintaiKoumoku kintaiKoumoku = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM KintaiKoumoku WHERE kintai_id = ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setObject(1, kintai_id);
			rs = ps.executeQuery();

			if (rs.next()) {
				kintaiKoumoku.setKintai_id(rs.getInt("kintai_id"));
				kintaiKoumoku.setKintai_mei(rs.getString("Kintai_mei"));
				kintaiKoumoku.setTani(rs.getString("Tani"));
				kintaiKoumoku.setKyuukaKoumoku_id(rs.getInt("KyuukaKoumoku_id"));
				kintaiKoumoku.setGroup_id(rs.getInt("Group_id"));
				kintaiKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}

		return kintaiKoumoku;
	}

	public void updateKintaiKoumoku(KintaiKoumoku kintaiKoumoku) {
		String query = "UPDATE KintaiKoumoku SET kintai_mei = ?, tani = ?, kyuukaKoumoku_id = ?, group_id = ?, roudouJikanRenkei = ?, shiyouUmu = ? "
				+ "WHERE kintai_id = ?";

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, kintaiKoumoku.getKintai_mei());
			ps.setString(2, kintaiKoumoku.getTani());
			ps.setObject(3, kintaiKoumoku.getKyuukaKoumoku_id());
			ps.setObject(4, kintaiKoumoku.getGroup_id());
			ps.setString(5, kintaiKoumoku.getRoudouJikanRenkei());
			ps.setString(6, String.valueOf(kintaiKoumoku.getShiyouUmu()));
			ps.setObject(7, kintaiKoumoku.getKintai_id());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
	}

	public ArrayList<KintaiKoumoku> getKintaiKoumokuListByUser(int userId) {
		ArrayList<KintaiKoumoku> kintaiList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM KintaiKoumoku WHERE user_id = ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				KintaiKoumoku kintaiKoumoku = new KintaiKoumoku();
				kintaiKoumoku.setKintai_id(rs.getInt("kintai_id"));
				kintaiKoumoku.setKintai_mei(rs.getString("kintai_mei"));
				kintaiKoumoku.setTani(rs.getString("tani"));
				kintaiKoumoku.setKyuukaKoumoku_id(rs.getInt("kyuukaKoumoku_id"));
				kintaiKoumoku.setGroup_id(rs.getInt("group_id"));
				kintaiKoumoku.setRoudouJikanRenkei(rs.getString("roudouJikanRenkei"));
				kintaiKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));

				kintaiList.add(kintaiKoumoku);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}

		return kintaiList;
	}

}
