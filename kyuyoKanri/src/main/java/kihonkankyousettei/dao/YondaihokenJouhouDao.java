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

import kihonkankyousettei.model.YondaihokenJouhou;

public class YondaihokenJouhouDao {

	private static YondaihokenJouhouDao instance = new YondaihokenJouhouDao();

	/**
	 * YondaihokenJouhouDao의 인스턴스를 반환합니다. YondaihokenJouhouDaoのインスタンスを返します。
	 */
	public static YondaihokenJouhouDao getInstance() {
		return instance;
	}

	/**
	 * YondaihokenJouhou 데이터를 데이터베이스에 삽입하는 메서드입니다.
	 * YondaihokenJouhouデータをデータベースに挿入するメソッドです。
	 */
	public void insertYondaihokenJouhou(Connection conn, YondaihokenJouhou yondaihokenJouhou) throws SQLException {
		String insertQuery = "INSERT INTO YondaihokenJouhou (id, shain_id, hokenKubun, kigouBangou, shutoku_bi, soshitsu_bi) "
				+ "VALUES (YondaihokenJouhou_sequence.nextval, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
			ps.setInt(1, yondaihokenJouhou.getShain_id());
			ps.setString(2, yondaihokenJouhou.getHokenKubun());
			ps.setString(3, yondaihokenJouhou.getKigouBangou());
			ps.setDate(4, new java.sql.Date(yondaihokenJouhou.getShutoku_bi().getTime()));
			ps.setDate(5, new java.sql.Date(yondaihokenJouhou.getSoshitsu_bi().getTime()));
			ps.executeUpdate();
			System.out.println("데이터가 성공적으로 삽입되었습니다.");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("중복된 데이터가 이미 존재하여 삽입하지 않았습니다.");
			} else {
				throw e;
			}
		}
	}

	/**
	 * YondaihokenJouhou 데이터를 업데이트하는 메서드입니다. YondaihokenJouhouデータを更新するメソッドです。
	 */
	public void updateYondaihokenJouhou(Connection conn, YondaihokenJouhou yondaihokenJouhou) throws SQLException {
		String query = "UPDATE YondaihokenJouhou SET kigouBangou = ?, shutoku_bi = ?, soshitsu_bi = ? "
				+ "WHERE shain_id = ? AND hokenKubun = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, yondaihokenJouhou.getKigouBangou());
			ps.setDate(2, new java.sql.Date(yondaihokenJouhou.getShutoku_bi().getTime()));
			ps.setDate(3, new java.sql.Date(yondaihokenJouhou.getSoshitsu_bi().getTime()));
			ps.setInt(4, yondaihokenJouhou.getShain_id());
			ps.setString(5, yondaihokenJouhou.getHokenKubun());
			ps.executeUpdate();
		}
	}

	/**
	 * 특정 사원 ID와 보험 종류로 YondaihokenJouhou 데이터를 조회하는 메서드입니다.
	 * 特定の社員IDと保険種類でYondaihokenJouhouデータを取得するメソッドです。
	 */
	public YondaihokenJouhou getYondaihokenJouhouById(Connection conn, int shain_id, String hokenKubun)
			throws SQLException {
		String query = "SELECT y.*, s.namae_kana FROM YondaihokenJouhou y "
				+ "JOIN Shain s ON y.shain_id = s.shain_id WHERE y.shain_id = ? AND y.hokenKubun = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, shain_id);
			ps.setString(2, hokenKubun);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				YondaihokenJouhou yondaihokenJouhou = new YondaihokenJouhou();
				yondaihokenJouhou.setShain_id(rs.getInt("shain_id"));
				yondaihokenJouhou.setHokenKubun(rs.getString("hokenKubun"));
				yondaihokenJouhou.setKigouBangou(rs.getString("kigouBangou"));
				yondaihokenJouhou.setShutoku_bi(new Date(rs.getDate("shutoku_bi").getTime()));
				yondaihokenJouhou.setSoshitsu_bi(new Date(rs.getDate("soshitsu_bi").getTime()));
				return yondaihokenJouhou;
			}
		}
		return null;
	}

	/**
	 * 모든 YondaihokenJouhou 데이터를 조회하는 메서드입니다. すべてのYondaihokenJouhouデータを取得するメソッドです。
	 */
	public ArrayList<YondaihokenJouhou> selectAllYondaihokenJouhou(Connection conn) throws SQLException {
		String query = "SELECT y.*, s.namae_kana FROM YondaihokenJouhou y JOIN Shain s ON y.shain_id = s.shain_id";
		ArrayList<YondaihokenJouhou> yondaihokenJouhouList = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				YondaihokenJouhou yondaihokenJouhou = new YondaihokenJouhou();
				yondaihokenJouhou.setShain_id(rs.getInt("shain_id"));
				yondaihokenJouhou.setHokenKubun(rs.getString("hokenKubun"));
				yondaihokenJouhou.setKigouBangou(rs.getString("kigouBangou"));
				yondaihokenJouhou.setShutoku_bi(new Date(rs.getDate("shutoku_bi").getTime()));
				yondaihokenJouhou.setSoshitsu_bi(new Date(rs.getDate("soshitsu_bi").getTime()));
				yondaihokenJouhouList.add(yondaihokenJouhou);
			}
		}
		return yondaihokenJouhouList;
	}
}
