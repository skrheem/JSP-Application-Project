package dao;
//김찬호 金燦鎬
//기본환경설정 사원등록
//基本環境設定 社員登録
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kihonkankyousettei.model.Shoubatsu;

/**
 * ShoubatsuDao는 퇴직 정보에 대한 데이터 접근 객체입니다. ShoubatsuDaoは退職情報に関するデータアクセスオブジェクトです。
 */
public class ShoubatsuDao {

	private static ShoubatsuDao instance = new ShoubatsuDao();

	/**
	 * ShoubatsuDao의 인스턴스를 반환합니다. ShoubatsuDaoのインスタンスを返します。
	 */
	public static ShoubatsuDao getInstance() {
		return instance;
	}

	/**
	 * 퇴직 정보를 데이터베이스에 입력하는 메서드입니다. 退職情報をデータベースに入力するメソッドです。
	 */
	public void insertShoubatsu(Connection conn, Shoubatsu shoubatsu) throws SQLException {
		String query = "INSERT INTO Shoubatsu (shouBatsu_id, shain_id, shouBatsuKubun, shouBatsuMei, shouBatsu_bi, shouBatsuSha, shouBatsuNaiyou, bikou) "
				+ "VALUES (Shoubatsu_sequence.nextval, ?, ?, ?, ?, ?, ?, ?)";
		String[] generatedColumns = { "shouBatsu_id" };

		try (PreparedStatement ps = conn.prepareStatement(query, generatedColumns)) {
			ps.setInt(1, shoubatsu.getShain_id());
			ps.setString(2, shoubatsu.getShouBatsuKubun());
			ps.setString(3, shoubatsu.getShouBatsuMei());
			ps.setDate(4, new java.sql.Date(shoubatsu.getShouBatsu_bi().getTime()));
			ps.setString(5, shoubatsu.getShouBatsuSha());
			ps.setString(6, shoubatsu.getShouBatsuNaiyou());
			ps.setString(7, shoubatsu.getBikou());
			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					shoubatsu.setShouBatsu_id(rs.getInt(1));
				}
			}
			conn.commit(); // 명시적 커밋
		} catch (SQLException e) {
			conn.rollback(); // 예외 발생 시 롤백
			throw e;
		}
	}

	/**
	 * 퇴직 정보를 업데이트하는 메서드입니다. 退職情報を更新するメソッドです。
	 */
	public void updateShoubatsu(Connection conn, Shoubatsu shoubatsu, int shouBatsuId) throws SQLException {
		String query = "UPDATE Shoubatsu SET shain_id = ?, shouBatsuKubun = ?, shouBatsuMei = ?, shouBatsu_bi = ?, shouBatsuSha = ?, "
				+ "shouBatsuNaiyou = ?, bikou = ? WHERE shouBatsu_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, shoubatsu.getShain_id());
			ps.setString(2, shoubatsu.getShouBatsuKubun());
			ps.setString(3, shoubatsu.getShouBatsuMei());
			ps.setDate(4, new java.sql.Date(shoubatsu.getShouBatsu_bi().getTime()));
			ps.setString(5, shoubatsu.getShouBatsuSha());
			ps.setString(6, shoubatsu.getShouBatsuNaiyou());
			ps.setString(7, shoubatsu.getBikou());
			ps.setInt(8, shouBatsuId);
			ps.executeUpdate();
			conn.commit(); // 명시적 커밋
		} catch (SQLException e) {
			conn.rollback(); // 예외 발생 시 롤백
			throw e;
		}
	}

	/**
	 * 특정 ID로 퇴직 정보를 조회하는 메서드입니다. 特定IDで退職情報を取得するメソッドです。
	 */
	public Shoubatsu getShoubatsuById(Connection conn, int shouBatsuId) throws SQLException {
		String query = "SELECT sb.*, s.namae_kana FROM Shoubatsu sb JOIN Shain s ON sb.shain_id = s.shain_id WHERE sb.shouBatsu_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, shouBatsuId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Shoubatsu(rs.getInt("shouBatsu_id"), rs.getInt("shain_id"), rs.getString("shouBatsuKubun"),
						rs.getString("shouBatsuMei"), new Date(rs.getDate("shouBatsu_bi").getTime()),
						rs.getString("shouBatsuSha"), rs.getString("shouBatsuNaiyou"), rs.getString("bikou"));
			}
		}
		return null;
	}

	/**
	 * 모든 퇴직 정보를 조회하는 메서드입니다. すべての退職情報を取得するメソッドです。
	 */
	public List<Shoubatsu> selectAllShoubatsu(Connection conn) throws SQLException {
		String query = "SELECT sb.*, s.namae_kana FROM Shoubatsu sb JOIN Shain s ON sb.shain_id = s.shain_id";
		List<Shoubatsu> shoubatsuList = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				shoubatsuList.add(
						new Shoubatsu(rs.getInt("shouBatsu_id"), rs.getInt("shain_id"), rs.getString("shouBatsuKubun"),
								rs.getString("shouBatsuMei"), new Date(rs.getDate("shouBatsu_bi").getTime()),
								rs.getString("shouBatsuSha"), rs.getString("shouBatsuNaiyou"), rs.getString("bikou")));
			}
		}
		return shoubatsuList;
	}
}
