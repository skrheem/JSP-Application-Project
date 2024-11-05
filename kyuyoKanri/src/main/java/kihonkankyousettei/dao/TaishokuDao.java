package kihonkankyousettei.dao;
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

import kihonkankyousettei.model.Taishoku;

/**
 * TaishokuDao는 퇴직 정보에 대한 데이터 접근 객체입니다. TaishokuDaoは退職情報に関するデータアクセスオブジェクトです。
 */
public class TaishokuDao {

	private static TaishokuDao instance = new TaishokuDao();

	/**
	 * TaishokuDao의 인스턴스를 반환합니다. TaishokuDaoのインスタンスを返します。
	 */
	public static TaishokuDao getInstance() {
		return instance;
	}

	/**
	 * 퇴직 정보를 데이터베이스에 입력하는 메서드입니다. 退職情報をデータベースに入力するメソッドです。
	 */
	public void insertTaishoku(Connection conn, Taishoku taishoku) throws SQLException {
		String query = "INSERT INTO Taishoku (taishoku_id, shain_id, taishokuKubun, taishoku_bi, riyuu, taishoku_kin, taishokugoRenrakusaki, taishokuKinMeisaisho) "
				+ "VALUES (Taishoku_sequence.nextval, ?, ?, ?, ?, ?, ?, ?)";
		String[] generatedColumns = { "taishoku_id" };

		try (PreparedStatement ps = conn.prepareStatement(query, generatedColumns)) {
			ps.setInt(1, taishoku.getShain_id());
			ps.setString(2, taishoku.getTaishokuKubun());
			ps.setDate(3, new java.sql.Date(taishoku.getTaishoku_bi().getTime()));
			ps.setString(4, taishoku.getRiyuu());
			ps.setBigDecimal(5, taishoku.getTaishoku_kin());
			ps.setString(6, taishoku.getTaishokugoRenrakusaki());
			ps.setString(7, taishoku.getTaishokuKinMeisaisho());
			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					taishoku.setTaishoku_id(rs.getInt(1));
				}
			}
		}
	}

	/**
	 * 퇴직 정보를 업데이트하는 메서드입니다. 退職情報を更新するメソッドです。
	 */
	public void updateTaishoku(Connection conn, Taishoku taishoku, int taishokuId) throws SQLException {
		String query = "UPDATE Taishoku SET shain_id = ?, taishokuKubun = ?, taishoku_bi = ?, riyuu = ?, taishoku_kin = ?, taishokugoRenrakusaki = ?, taishokuKinMeisaisho = ? "
				+ "WHERE taishoku_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, taishoku.getShain_id());
			ps.setString(2, taishoku.getTaishokuKubun());
			ps.setDate(3, new java.sql.Date(taishoku.getTaishoku_bi().getTime()));
			ps.setString(4, taishoku.getRiyuu());
			ps.setBigDecimal(5, taishoku.getTaishoku_kin());
			ps.setString(6, taishoku.getTaishokugoRenrakusaki());
			ps.setString(7, taishoku.getTaishokuKinMeisaisho());
			ps.setInt(8, taishokuId);
			ps.executeUpdate();
		}
	}

	/**
	 * 특정 ID로 퇴직 정보를 조회하는 메서드입니다. 特定IDで退職情報を取得するメソッドです。
	 */
	public Taishoku getTaishokuById(Connection conn, int taishokuId) throws SQLException {
		String query = "SELECT t.*, s.namae_kana FROM Taishoku t JOIN Shain s ON t.shain_id = s.shain_id WHERE t.taishoku_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, taishokuId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Taishoku(rs.getInt("taishoku_id"), rs.getInt("shain_id"), rs.getString("taishokuKubun"),
						new Date(rs.getDate("taishoku_bi").getTime()), rs.getString("riyuu"),
						rs.getBigDecimal("taishoku_kin"), rs.getString("taishokugoRenrakusaki"),
						rs.getString("taishokuKinMeisaisho"));
			}
		}
		return null;
	}

	/**
	 * 모든 퇴직 정보를 조회하는 메서드입니다. すべての退職情報を取得するメソッドです。
	 */
	public List<kihonkankyousettei.model.Taishoku> selectAllTaishoku(Connection conn) throws SQLException {
		String query = "SELECT t.*, s.namae_kana FROM Taishoku t JOIN Shain s ON t.shain_id = s.shain_id";
		List<kihonkankyousettei.model.Taishoku> taishokuList = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				taishokuList.add(new Taishoku(rs.getInt("taishoku_id"), rs.getInt("shain_id"),
						rs.getString("taishokuKubun"), new Date(rs.getDate("taishoku_bi").getTime()),
						rs.getString("riyuu"), rs.getBigDecimal("taishoku_kin"), rs.getString("taishokugoRenrakusaki"),
						rs.getString("taishokuKinMeisaisho")));
			}
		}
		return taishokuList;
	}
}
