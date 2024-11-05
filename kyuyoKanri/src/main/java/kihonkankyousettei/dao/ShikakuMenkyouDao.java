package kihonkankyousettei.dao;

//김찬호 金燦鎬
//기본환경설정 사원등록
//基本環境設定 社員登録
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kihonkankyousettei.model.ShikakuMenkyou;

/**
 * ShikakuMenkyouDao는 자격증 정보를 관리하는 데이터 접근 객체입니다.
 * ShikakuMenkyouDaoは資格情報を管理するデータアクセスオブジェクトです。
 */
public class ShikakuMenkyouDao {

	private static ShikakuMenkyouDao instance = new ShikakuMenkyouDao();

	/**
	 * ShikakuMenkyouDao의 인스턴스를 반환합니다. ShikakuMenkyouDaoのインスタンスを返します。
	 */
	public static ShikakuMenkyouDao getInstance() {
		return instance;
	}

	/**
	 * 자격증 정보를 데이터베이스에 입력하는 메서드입니다. 資格情報をデータベースに入力するメソッドです。
	 */
	public void insertShikakuMenkyou(Connection conn, ShikakuMenkyou shikaku) throws SQLException {
		String query = "INSERT INTO ShikakuMenkyou (shikaku_id, shain_id, shikaku_mei, shutoku_bi, hakkou_kikan, shoumei_bangou, bikou) "
				+ "VALUES (shikakuMenkyou_sequence.nextval, ?, ?, ?, ?, ?, ?)";
		String[] generatedColumns = { "shikaku_id" };

		try (PreparedStatement ps = conn.prepareStatement(query, generatedColumns)) {
			ps.setInt(1, shikaku.getShain_id());
			ps.setString(2, shikaku.getShikaku_mei());
			ps.setDate(3, new java.sql.Date(shikaku.getShutoku_bi().getTime()));
			ps.setString(4, shikaku.getHakkou_kikan());
			ps.setString(5, shikaku.getShoumei_bangou());
			ps.setString(6, shikaku.getBikou());
			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					shikaku.setShikaku_id(rs.getInt(1));
				}
			}
			conn.commit(); // 명시적 커밋
		} catch (SQLException e) {
			conn.rollback(); // 예외 발생 시 롤백
			throw e;
		}
	}

	/**
	 * 자격증 정보를 업데이트하는 메서드입니다. 資格情報を更新するメソッドです。
	 */
	public void updateShikakuMenkyou(Connection conn, ShikakuMenkyou shikaku) throws SQLException {
		String query = "UPDATE ShikakuMenkyou SET shain_id = ?, shikaku_mei = ?, shutoku_bi = ?, "
				+ "hakkou_kikan = ?, shoumei_bangou = ?, bikou = ? WHERE shikaku_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, shikaku.getShain_id());
			ps.setString(2, shikaku.getShikaku_mei());
			ps.setDate(3, new java.sql.Date(shikaku.getShutoku_bi().getTime()));
			ps.setString(4, shikaku.getHakkou_kikan());
			ps.setString(5, shikaku.getShoumei_bangou());
			ps.setString(6, shikaku.getBikou());
			ps.setInt(7, shikaku.getShikaku_id());
			ps.executeUpdate();
			conn.commit(); // 명시적 커밋
		} catch (SQLException e) {
			conn.rollback(); // 예외 발생 시 롤백
			throw e;
		}
	}

	/**
	 * 특정 ID로 자격증 정보를 조회하는 메서드입니다. 特定IDで資格情報を取得するメソッドです。
	 */
	public ShikakuMenkyou getShikakuMenkyouById(Connection conn, int shikaku_id) throws SQLException {
		String query = "SELECT sm.*, s.namae_kana FROM ShikakuMenkyou sm "
				+ "JOIN Shain s ON sm.shain_id = s.shain_id WHERE sm.shikaku_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, shikaku_id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new ShikakuMenkyou(rs.getInt("shikaku_id"), rs.getInt("shain_id"),
							rs.getString("shikaku_mei"), rs.getDate("shutoku_bi"), rs.getString("hakkou_kikan"),
							rs.getString("shoumei_bangou"), rs.getString("bikou"));
				}
			}
		}
		return null;
	}

	/**
	 * 모든 자격증 정보를 조회하는 메서드입니다. すべての資格情報を取得するメソッドです。
	 */
	public ArrayList<ShikakuMenkyou> selectAllShikakuMenkyou(Connection conn) throws SQLException {
		String query = "SELECT sm.*, s.namae_kana FROM ShikakuMenkyou sm " + "JOIN Shain s ON sm.shain_id = s.shain_id";
		ArrayList<ShikakuMenkyou> shikakuList = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				shikakuList.add(new ShikakuMenkyou(rs.getInt("shikaku_id"), rs.getInt("shain_id"),
						rs.getString("shikaku_mei"), rs.getDate("shutoku_bi"), rs.getString("hakkou_kikan"),
						rs.getString("shoumei_bangou"), rs.getString("bikou")));
			}
		}
		return shikakuList;
	}
}
