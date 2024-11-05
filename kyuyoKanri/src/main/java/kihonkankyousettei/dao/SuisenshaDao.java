package kihonkankyousettei.dao;
//김찬호 金燦鎬
//기본환경설정 사원등록
//基本環境設定 社員登録
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kihonkankyousettei.model.Suisensha;

/**
 * SuisenshaDao는 담당자 정보에 대한 데이터 접근 객체입니다. SuisenshaDaoは担当者情報に関するデータアクセスオブジェクトです。
 */
public class SuisenshaDao {

	private static SuisenshaDao instance = new SuisenshaDao();

	/**
	 * SuisenshaDao의 인스턴스를 반환합니다. SuisenshaDaoのインスタンスを返します。
	 */
	public static SuisenshaDao getInstance() {
		return instance;
	}

	/**
	 * 담당자 정보를 데이터베이스에 입력하는 메서드입니다. 担当者情報をデータベースに入力するメソッドです。
	 */
	public void insertSuisensha(Connection conn, Suisensha suisensha) throws SQLException {
		String query = "INSERT INTO Suisensha (suisensha_id, shain_id, suisensha_mei, kankei, kaisha_mei, yakushoku_id, suisensha_denwa) "
				+ "VALUES (Suisensha_sequence.nextval, ?, ?, ?, ?, ?, ?)";
		String[] generatedColumns = { "suisensha_id" };

		try (PreparedStatement ps = conn.prepareStatement(query, generatedColumns)) {
			ps.setInt(1, suisensha.getShain_id());
			ps.setString(2, suisensha.getSuisensha_mei());
			ps.setString(3, suisensha.getKankei());
			ps.setString(4, suisensha.getKaisha_mei());
			ps.setString(5, suisensha.getYakushoku_id());
			ps.setString(6, suisensha.getSuisensha_denwa());
			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					suisensha.setSuisensha_id(rs.getInt(1));
				}
			}
		}
	}

	/**
	 * 담당자 정보를 업데이트하는 메서드입니다. 担当者情報を更新するメソッドです。
	 */
	public void updateSuisensha(Connection conn, Suisensha suisensha, int suisenshaId) throws SQLException {
		String query = "UPDATE Suisensha SET shain_id = ?, suisensha_mei = ?, kankei = ?, kaisha_mei = ?, yakushoku_id = ?, suisensha_denwa = ? "
				+ "WHERE suisensha_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, suisensha.getShain_id());
			ps.setString(2, suisensha.getSuisensha_mei());
			ps.setString(3, suisensha.getKankei());
			ps.setString(4, suisensha.getKaisha_mei());
			ps.setString(5, suisensha.getYakushoku_id());
			ps.setString(6, suisensha.getSuisensha_denwa());
			ps.setInt(7, suisenshaId);
			ps.executeUpdate();
		}
	}

	/**
	 * 특정 ID로 담당자 정보를 조회하는 메서드입니다. 特定IDで担当者情報を取得するメソッドです。
	 */
	public Suisensha getSuisenshaById(Connection conn, int suisenshaId) throws SQLException {
		String query = "SELECT su.*, sh.namae_kana FROM Suisensha su "
				+ "JOIN Shain sh ON su.shain_id = sh.shain_id WHERE su.suisensha_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, suisenshaId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Suisensha(rs.getInt("suisensha_id"), rs.getInt("shain_id"), rs.getString("suisensha_mei"),
						rs.getString("kankei"), rs.getString("kaisha_mei"), rs.getString("yakushoku_id"),
						rs.getString("suisensha_denwa"));
			}
		}
		return null;
	}

	/**
	 * 모든 담당자 정보를 조회하는 메서드입니다. すべての担当者情報を取得するメソッドです。
	 */
	public List<Suisensha> selectAllSuisensha(Connection conn) throws SQLException {
		String query = "SELECT su.*, sh.namae_kana FROM Suisensha su JOIN Shain sh ON su.shain_id = sh.shain_id";
		List<Suisensha> suisenshaList = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				suisenshaList.add(new Suisensha(rs.getInt("suisensha_id"), rs.getInt("shain_id"),
						rs.getString("suisensha_mei"), rs.getString("kankei"), rs.getString("kaisha_mei"),
						rs.getString("yakushoku_id"), rs.getString("suisensha_denwa")));
			}
		}
		return suisenshaList;
	}
}
