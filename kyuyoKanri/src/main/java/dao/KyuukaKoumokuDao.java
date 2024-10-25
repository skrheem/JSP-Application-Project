package dao;

//김찬호 金燦鎬
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jdbc.JdbcUtil;
import model.KyuukaKoumoku;

public class KyuukaKoumokuDao {

	private Connection connection;

	public void insertKyuukaKoumoku(Connection conn, String kyuukaShurui, Date tekiyouKaishi, Date tekiyouShuuryou,
			char shiyouUmu) {
		String query = "INSERT INTO KyuukaKoumoku (kyuukaKoumoku_id, kyuukaShurui, tekiyouKaishi, tekiyouShuuryou, shiyouUmu) "
				+ "VALUES (KyuukaKoumoku_sequence.nextVal, ?, ?, ?, ?)";

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, kyuukaShurui);

			if (tekiyouKaishi != null) {
				ps.setDate(2, new java.sql.Date(tekiyouKaishi.getTime()));
			} else {
				ps.setNull(2, java.sql.Types.DATE);
			}

			if (tekiyouShuuryou != null) {
				ps.setDate(3, new java.sql.Date(tekiyouShuuryou.getTime()));
			} else {
				ps.setNull(3, java.sql.Types.DATE);
			}

			ps.setString(4, String.valueOf(shiyouUmu));
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
	}

	// 특정 ID로 KyuukaKoumoku 데이터를 조회하는 메서드
	public KyuukaKoumoku getKyuukaKoumokuById(Integer kyuukaKoumokuId) {
		KyuukaKoumoku kyuukaKoumoku = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM KyuukaKoumoku WHERE kyuukaKoumoku_id = ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setObject(1, kyuukaKoumokuId);
			rs = ps.executeQuery();

			if (rs.next()) {
				kyuukaKoumoku.setKyuukaKoumoku_id(rs.getInt("kyuukaKoumoku_id"));
				kyuukaKoumoku.setKyuukaShurui(rs.getString("kyuukaShurui"));
				kyuukaKoumoku.setTekiyouKaishi(rs.getDate("tekiyouKaishi"));
				kyuukaKoumoku.setTekiyouShuuryou(rs.getDate("tekiyouShuuryou"));
				kyuukaKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}

		return kyuukaKoumoku;
	}

	// KyuukaKoumoku 데이터를 업데이트하는 메서드
	public void updateKyuukaKoumoku(KyuukaKoumoku kyuukaKoumoku) {
		String query = "UPDATE KyuukaKoumoku SET kyuukaShurui = ?, tekiyouKaishi = ?, tekiyouShuuryou = ?, shiyouUmu = ? "
				+ "WHERE kyuukaKoumoku_id = ?";

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, kyuukaKoumoku.getKyuukaShurui());

			if (kyuukaKoumoku.getTekiyouKaishi() != null) {
				ps.setDate(2, new java.sql.Date(kyuukaKoumoku.getTekiyouKaishi().getTime()));
			} else {
				ps.setNull(2, java.sql.Types.DATE);
			}

			if (kyuukaKoumoku.getTekiyouShuuryou() != null) {
				ps.setDate(3, new java.sql.Date(kyuukaKoumoku.getTekiyouShuuryou().getTime()));
			} else {
				ps.setNull(3, java.sql.Types.DATE);
			}

			ps.setString(4, String.valueOf(kyuukaKoumoku.getShiyouUmu()));

			ps.setObject(5, kyuukaKoumoku.getKyuukaKoumoku_id());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
	}

	// 사용자가 만든 모든 KyuukaKoumoku 데이터를 조회하여 리스트로 반환하는 메서드
	public ArrayList<KyuukaKoumoku> getKyuukaKoumokuListByUser(int userId) {
		ArrayList<KyuukaKoumoku> kyuukaList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM KyuukaKoumoku WHERE user_id = ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				KyuukaKoumoku kyuukaKoumoku = new KyuukaKoumoku();
				kyuukaKoumoku.setKyuukaKoumoku_id(rs.getInt("kyuukaKoumoku_id"));
				kyuukaKoumoku.setKyuukaShurui(rs.getString("kyuukaShurui"));
				kyuukaKoumoku.setTekiyouKaishi(rs.getDate("tekiyouKaishi"));
				kyuukaKoumoku.setTekiyouShuuryou(rs.getDate("tekiyouShuuryou"));
				kyuukaKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));

				kyuukaList.add(kyuukaKoumoku);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}

		return kyuukaList;
	}
}
