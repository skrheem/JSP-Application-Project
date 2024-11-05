package dao;
//김찬호 金燦鎬
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import kihonkankyousettei.model.Shain;

/**
 * ShainDao는 사원 정보를 관리하는 데이터 접근 객체입니다. ShainDaoは社員情報を管理するデータアクセスオブジェクトです。
 */
public class ShainDao {

	private static ShainDao instance = new ShainDao();

	/**
	 * ShainDao의 인스턴스를 반환합니다. ShainDaoのインスタンスを返します。
	 */
	public static ShainDao getInstance() {
		return instance;
	}

	/**
	 * 모든 사원 목록을 DB에서 가져오는 메서드입니다. すべての社員のリストをDBから取得するメソッドです。
	 */
	public ArrayList<Shain> getAllShain() throws SQLException {
		ArrayList<Shain> shainList = new ArrayList<>();
		String query = "SELECT shain_id, namae_kana, busho_id, yakushoku_id, nyuusha_nengappi FROM Shain";

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Shain shain = new Shain();
				shain.setShain_id(rs.getInt("shain_id"));
				shain.setNamae_kana(rs.getString("namae_kana"));
				shain.setBusho_id(rs.getInt("busho_id"));
				shain.setYakushoku_id(rs.getInt("yakushoku_id"));
				shain.setNyuusha_nengappi(rs.getDate("nyuusha_nengappi"));
				shainList.add(shain);
			}
			System.out.println("DAO에서 가져온 Shain List: " + shainList); // 데이터 확인
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("사원 목록을 가져오는 중 오류가 발생했습니다.", e);
		}
		return shainList;
	}

	/**
	 * 사원 목록을 상태로 필터링하여 가져오는 메서드입니다. ステータスでフィルタリングして社員リストを取得するメソッドです。
	 */
	public ArrayList<Shain> getShainList(Connection conn, String selectedJyoutai) throws SQLException {
		ArrayList<Shain> shainList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder(
				"SELECT s.kubun, s.shain_id, s.namae_kana, b.busho_mei, y.yakushoku_mei, s.nyuusha_nengappi FROM Shain s "
						+ "JOIN Busho b ON s.busho_id = b.busho_id "
						+ "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id "
						+ "WHERE (? IS NULL OR s.jyoutai = ?)");

		if (selectedJyoutai == null) {
			selectedJyoutai = "在職"; // 초기값으로 재직 상태
		}

		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, selectedJyoutai);
			ps.setString(2, selectedJyoutai);

			rs = ps.executeQuery();

			while (rs.next()) {
				String kubun = rs.getString("kubun");
				Integer shain_id = rs.getInt("shain_id");
				String namae_kana = rs.getString("namae_kana");
				String busho_mei = rs.getString("busho_mei");
				String yakushoku_mei = rs.getString("yakushoku_mei");
				java.sql.Date nyuusha_nengappi = rs.getDate("nyuusha_nengappi");

				Shain shain = new Shain(kubun, shain_id, namae_kana, busho_mei, yakushoku_mei, nyuusha_nengappi);
				shainList.add(shain);
			}
		} catch (SQLException e) {
			throw new SQLException("사원 목록을 가져오는 중 오류가 발생했습니다.", e);
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return shainList;
	}

	/**
	 * 휴가일수를 INSERT하는 메서드입니다. 休暇日数をINSERTするメソッドです。
	 */
	public void insertKyuukaNissuu(int shainId, double vacationDays) throws SQLException {
		String insertQuery = "INSERT INTO ShainKyuukaNissuu (shain_id, kyuukaNissuu) VALUES (?, ?)";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement ps = conn.prepareStatement(insertQuery)) {
			ps.setInt(1, shainId);
			ps.setDouble(2, vacationDays);
			ps.executeUpdate();
		}
	}
}
