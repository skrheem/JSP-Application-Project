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

import kihonkankyousettei.model.HoshouhoHoken;

/**
 * HoshouhoHokenDao는 보장 보험 정보 관련 데이터베이스 접근을 담당하는 DAO 클래스입니다.
 * HoshouhoHokenDaoは保障保険情報関連のデータベースアクセスを担当するDAOクラスです。
 */
public class HoshouhoHokenDao {

	private static HoshouhoHokenDao instance = new HoshouhoHokenDao();

	/**
	 * HoshouhoHokenDao의 인스턴스를 반환합니다. HoshouhoHokenDaoのインスタンスを返します。
	 *
	 * @return HoshouhoHokenDao 인스턴스 / HoshouhoHokenDaoインスタンス
	 */
	public static HoshouhoHokenDao getInstance() {
		return instance;
	}

	/**
	 * 보장 보험 정보를 삽입하는 메서드입니다. 保障保険情報を挿入するメソッドです。
	 *
	 * @param conn          데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param hoshouhoHoken 삽입할 보장 보험 정보 객체 / 挿入する保障保険情報オブジェクト
	 * @return 영향을 받은 행의 수 / 影響を受けた行数
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int insertHoshouhoHoken(Connection conn, HoshouhoHoken hoshouhoHoken) throws SQLException {
		String sql = "INSERT INTO HoshouhoHoken (hoshouHoken_id, shain_id, kanyuuKikan, hoshouBangou, hoshouKingaku, kanyuu_bi, yuukouKikan, bikou) "
				+ "VALUES (HoshouhoHoken_sequence.nextval, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, hoshouhoHoken.getShain_id());
			ps.setString(2, hoshouhoHoken.getKanyuuKikan());
			ps.setString(3, hoshouhoHoken.getHoshouBangou());
			ps.setBigDecimal(4, hoshouhoHoken.getHoshouKingaku());
			ps.setDate(5,
					hoshouhoHoken.getKanyuu_bi() != null ? new java.sql.Date(hoshouhoHoken.getKanyuu_bi().getTime())
							: null);
			ps.setDate(6,
					hoshouhoHoken.getYuukouKikan() != null ? new java.sql.Date(hoshouhoHoken.getYuukouKikan().getTime())
							: null);
			ps.setString(7, hoshouhoHoken.getBikou());
			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("보장보험 추가 실패: no rows affected。/ 保障保険追加に失敗しました：影響を受けた行がありません。");
			}
			return affectedRows;
		}
	}

	/**
	 * 보장 보험 정보를 업데이트하는 메서드입니다. 保障保険情報を更新するメソッドです。
	 *
	 * @param conn          데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param hoshouhoHoken 업데이트할 보장 보험 정보 객체 / 更新する保障保険情報オブジェクト
	 * @param hoshouHokenId 업데이트할 보장 보험 ID / 更新する保障保険ID
	 * @return 업데이트된 행의 수를 반환 / 更新された行数を返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int updateHoshouhoHoken(Connection conn, HoshouhoHoken hoshouhoHoken, int hoshouHokenId)
			throws SQLException {
		String sql = "UPDATE HoshouhoHoken SET shain_id = ?, kanyuuKikan = ?, hoshouBangou = ?, hoshouKingaku = ?, kanyuu_bi = ?, yuukouKikan = ?, bikou = ? "
				+ "WHERE hoshouHoken_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, hoshouhoHoken.getShain_id());
			ps.setString(2, hoshouhoHoken.getKanyuuKikan());
			ps.setString(3, hoshouhoHoken.getHoshouBangou());
			ps.setBigDecimal(4, hoshouhoHoken.getHoshouKingaku());
			ps.setDate(5,
					hoshouhoHoken.getKanyuu_bi() != null ? new java.sql.Date(hoshouhoHoken.getKanyuu_bi().getTime())
							: null);
			ps.setDate(6,
					hoshouhoHoken.getYuukouKikan() != null ? new java.sql.Date(hoshouhoHoken.getYuukouKikan().getTime())
							: null);
			ps.setString(7, hoshouhoHoken.getBikou());
			ps.setInt(8, hoshouHokenId);
			return ps.executeUpdate();
		}
	}

	/**
	 * 특정 ID로 보장 보험 정보를 조회하는 메서드입니다. 特定のIDで保障保険情報を照会するメソッドです。
	 *
	 * @param conn          데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param hoshouHokenId 조회할 보장 보험의 ID / 照会する保障保険のID
	 * @return 조회된 보장 보험 정보 객체, 존재하지 않으면 null 반환 /
	 *         照会された保障保険情報オブジェクト、存在しない場合はnullを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public HoshouhoHoken getHoshouhoHokenById(Connection conn, int hoshouHokenId) throws SQLException {
		String sql = "SELECT h.*, s.namae_kana FROM HoshouhoHoken h "
				+ "JOIN Shain s ON h.shain_id = s.shain_id WHERE h.hoshouHoken_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, hoshouHokenId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new HoshouhoHoken(rs.getInt("hoshouHoken_id"), rs.getInt("shain_id"),
						rs.getString("kanyuuKikan"), rs.getString("hoshouBangou"), rs.getBigDecimal("hoshouKingaku"),
						rs.getDate("kanyuu_bi") != null ? new Date(rs.getDate("kanyuu_bi").getTime()) : null,
						rs.getDate("yuukouKikan") != null ? new Date(rs.getDate("yuukouKikan").getTime()) : null,
						rs.getString("bikou"));
			}
		}
		return null;
	}

	/**
	 * 모든 보장 보험 정보를 조회하는 메서드입니다. すべての保障保険情報を照会するメソッドです。
	 *
	 * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @return 조회된 보장 보험 정보 목록을 반환 / 照会された保障保険情報リストを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public List<HoshouhoHoken> selectAllHoshouhoHoken(Connection conn) throws SQLException {
		String sql = "SELECT h.*, s.namae_kana FROM HoshouhoHoken h JOIN Shain s ON h.shain_id = s.shain_id";
		List<HoshouhoHoken> hoshouhoHokenList = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				HoshouhoHoken hoshouhoHoken = new HoshouhoHoken(rs.getInt("hoshouHoken_id"), rs.getInt("shain_id"),
						rs.getString("kanyuuKikan"), rs.getString("hoshouBangou"), rs.getBigDecimal("hoshouKingaku"),
						rs.getDate("kanyuu_bi") != null ? new Date(rs.getDate("kanyuu_bi").getTime()) : null,
						rs.getDate("yuukouKikan") != null ? new Date(rs.getDate("yuukouKikan").getTime()) : null,
						rs.getString("bikou"));
				hoshouhoHokenList.add(hoshouhoHoken);
			}
		}
		return hoshouhoHokenList;
	}
}
