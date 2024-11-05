package dao;
//김찬호 金燦鎬
//기본환경설정 사원등록
//基本環境設定 社員登録
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kihonkankyousettei.model.ShainKihonkyuu;

/**
 * ShainKihonkyuuDao는 사원 기본급 정보를 관리하는 데이터 접근 객체입니다.
 * ShainKihonkyuuDaoは社員基本給情報を管理するデータアクセスオブジェクトです。
 */
public class ShainKihonkyuuDao {

	private static ShainKihonkyuuDao instance = new ShainKihonkyuuDao();

	/**
	 * ShainKihonkyuuDao의 인스턴스를 반환합니다.
	 * ShainKihonkyuuDaoのインスタンスを返します。
	 */
	public static ShainKihonkyuuDao getInstance() {
		return instance;
	}

	/**
	 * 사원 기본급 정보를 데이터베이스에 입력하는 메서드입니다.
	 * 社員基本給情報をデータベースに入力するメソッドです。
	 */
	public void insertShainKihonkyuu(Connection conn, ShainKihonkyuu shainKihonkyuu) throws SQLException {
	    String query = "INSERT INTO ShainKihonkyuu (kihonKyuu_id, shain_id, kihonKyuu, kokuminNenkinTekiyouGaku, "
	                 + "kenkouHokenTekiyouGaku, koyouHokenTekiyouGaku, kouza_bangou, yokinshaMeigi, kinyuuKikan) "
	                 + "VALUES (shainkihonKyuu_sequence.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement ps = conn.prepareStatement(query, new String[] { "kihonKyuu_id" })) {
	        ps.setInt(1, shainKihonkyuu.getShain_id());
	        ps.setInt(2, shainKihonkyuu.getKihonKyuu());
	        ps.setInt(3, shainKihonkyuu.getKokuminNenkinTekiyouGaku());
	        ps.setInt(4, shainKihonkyuu.getKenkouHokenTekiyouGaku());
	        ps.setInt(5, shainKihonkyuu.getKoyouHokenTekiyouGaku());
	        ps.setString(6, shainKihonkyuu.getKouza_bangou());
	        ps.setString(7, shainKihonkyuu.getYokinshaMeigi());
	        ps.setString(8, shainKihonkyuu.getKinyuuKikan());
	        ps.executeUpdate();

	        try (ResultSet rs = ps.getGeneratedKeys()) {
	            if (rs.next()) {
	                shainKihonkyuu.setKihonKyuu_id(rs.getInt(1));
	            }
	        }
	        conn.commit(); // 명시적 커밋

	    } catch (SQLException e) {
	        conn.rollback(); // 예외 발생 시 롤백
	        throw e;
	    }
	}

	/**
	 * 사원 기본급 정보를 업데이트하는 메서드입니다.
	 * 社員基本給情報を更新するメソッドです。
	 */
	public void updateShainKihonkyuu(Connection conn, ShainKihonkyuu shainKihonkyuu) throws SQLException {
		String query = "UPDATE ShainKihonkyuu SET shain_id = ?, kihonKyuu = ?, kokuminNenkinTekiyouGaku = ?, "
		             + "kenkouHokenTekiyouGaku = ?, koyouHokenTekiyouGaku = ?, kouza_bangou = ?, yokinshaMeigi = ?, kinyuuKikan = ? "
		             + "WHERE kihonKyuu_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, shainKihonkyuu.getShain_id());
			ps.setInt(2, shainKihonkyuu.getKihonKyuu());
			ps.setInt(3, shainKihonkyuu.getKokuminNenkinTekiyouGaku());
			ps.setInt(4, shainKihonkyuu.getKenkouHokenTekiyouGaku());
			ps.setInt(5, shainKihonkyuu.getKoyouHokenTekiyouGaku());
			ps.setString(6, shainKihonkyuu.getKouza_bangou());
			ps.setString(7, shainKihonkyuu.getYokinshaMeigi());
			ps.setString(8, shainKihonkyuu.getKinyuuKikan());
			ps.setInt(9, shainKihonkyuu.getKihonKyuu_id());
			ps.executeUpdate();
			conn.commit(); // 명시적 커밋
		} catch (SQLException e) {
			conn.rollback(); // 예외 발생 시 롤백
			throw e;
		}
	}

	/**
	 * 특정 ID로 사원 기본급 정보를 조회하는 메서드입니다.
	 * 特定IDで社員基本給情報を取得するメソッドです。
	 */
	public ShainKihonkyuu getShainKihonkyuuById(Connection conn, int kihonKyuu_id) throws SQLException {
		String query = "SELECT sk.*, s.namae_kana FROM ShainKihonkyuu sk JOIN Shain s ON sk.shain_id = s.shain_id WHERE sk.kihonKyuu_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, kihonKyuu_id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new ShainKihonkyuu(rs.getInt("kihonKyuu_id"), rs.getInt("shain_id"), rs.getInt("kihonKyuu"),
							rs.getInt("kokuminNenkinTekiyouGaku"), rs.getInt("kenkouHokenTekiyouGaku"), rs.getInt("koyouHokenTekiyouGaku"),
							rs.getString("kouza_bangou"), rs.getString("yokinshaMeigi"), rs.getString("kinyuuKikan"));
				}
			}
		}
		return null;
	}

	/**
	 * 모든 사원 기본급 정보를 조회하는 메서드입니다.
	 * すべての社員基本給情報を取得するメソッドです。
	 */
	public ArrayList<ShainKihonkyuu> selectAllShainKihonkyuu(Connection conn) throws SQLException {
		String query = "SELECT sk.*, s.namae_kana FROM ShainKihonkyuu sk JOIN Shain s ON sk.shain_id = s.shain_id";
		ArrayList<ShainKihonkyuu> shainKihonkyuuList = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				ShainKihonkyuu shainKihonkyuu = new ShainKihonkyuu(rs.getInt("kihonKyuu_id"), rs.getInt("shain_id"),
						rs.getInt("kihonKyuu"), rs.getInt("kokuminNenkinTekiyouGaku"), rs.getInt("kenkouHokenTekiyouGaku"),
						rs.getInt("koyouHokenTekiyouGaku"), rs.getString("kouza_bangou"), rs.getString("yokinshaMeigi"),
						rs.getString("kinyuuKikan"));
				shainKihonkyuuList.add(shainKihonkyuu);
			}
		}
		return shainKihonkyuuList;
	}
}