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

import kihonkankyousettei.model.Hoshounin;

/**
 * HoshouninDao는 보증인 정보 관련 데이터베이스 접근을 담당하는 DAO 클래스입니다.
 * HoshouninDaoは保証人情報関連のデータベースアクセスを担当するDAOクラスです。
 */
public class HoshouninDao {

	private static HoshouninDao instance = new HoshouninDao();

	/**
	 * HoshouninDao의 인스턴스를 반환합니다. HoshouninDaoのインスタンスを返します。
	 *
	 * @return HoshouninDao 인스턴스 / HoshouninDaoインスタンス
	 */
	public static HoshouninDao getInstance() {
		return instance;
	}

	/**
	 * 보증인 정보를 삽입하는 메서드입니다. 保証人情報を挿入するメソッドです。
	 *
	 * @param conn      데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param hoshounin 삽입할 보증인 정보 객체 / 挿入する保証人情報オブジェクト
	 * @return 영향을 받은 행의 수 / 影響を受けた行数
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int insertHoshounin(Connection conn, Hoshounin hoshounin) throws SQLException {
		String sql = "INSERT INTO Hoshounin (Hoshounin_id, shain_id, hoshou_kingaku, kaishi_nengappi, shuuryou_nengappi, Hoshounin_mei, kankei, Hoshounin_jumin_bangou, Hoshounin_denwa) "
				+ "VALUES (Hoshounin_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Hoshounin_id" })) {
			ps.setInt(1, hoshounin.getShain_id());
			ps.setBigDecimal(2, hoshounin.getHoshou_kingaku());
			ps.setDate(3, new java.sql.Date(hoshounin.getKaishi_nengappi().getTime()));
			ps.setDate(4, new java.sql.Date(hoshounin.getShuuryou_nengappi().getTime()));
			ps.setString(5, hoshounin.getHoshounin_mei());
			ps.setString(6, hoshounin.getKankei());
			ps.setString(7, hoshounin.getHoshounin_jumin_bangou());
			ps.setString(8, hoshounin.getHoshounin_denwa());

			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("보증인 추가 실패: no rows affected。/ 保証人追加に失敗しました：影響を受けた行がありません。");
			}

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					hoshounin.setHoshounin_id(generatedKeys.getInt(1));
				} else {
					throw new SQLException("보증인 추가 실패: ID를 얻을 수 없습니다。/ 保証人追加に失敗しました：IDを取得できませんでした。");
				}
			}
			return affectedRows;
		}
	}

	/**
	 * 보증인 정보를 업데이트하는 메서드입니다. 保証人情報を更新するメソッドです。
	 *
	 * @param conn        데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param hoshounin   업데이트할 보증인 정보 객체 / 更新する保証人情報オブジェクト
	 * @param hoshouninId 업데이트할 보증인 ID / 更新する保証人ID
	 * @return 업데이트된 행의 수를 반환 / 更新された行数を返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int updateHoshounin(Connection conn, Hoshounin hoshounin, int hoshouninId) throws SQLException {
		String sql = "UPDATE Hoshounin SET shain_id = ?, hoshou_kingaku = ?, kaishi_nengappi = ?, shuuryou_nengappi = ?, Hoshounin_mei = ?, kankei = ?, Hoshounin_jumin_bangou = ?, Hoshounin_denwa = ? "
				+ "WHERE Hoshounin_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, hoshounin.getShain_id());
			ps.setBigDecimal(2, hoshounin.getHoshou_kingaku());
			ps.setDate(3, new java.sql.Date(hoshounin.getKaishi_nengappi().getTime()));
			ps.setDate(4, new java.sql.Date(hoshounin.getShuuryou_nengappi().getTime()));
			ps.setString(5, hoshounin.getHoshounin_mei());
			ps.setString(6, hoshounin.getKankei());
			ps.setString(7, hoshounin.getHoshounin_jumin_bangou());
			ps.setString(8, hoshounin.getHoshounin_denwa());
			ps.setInt(9, hoshouninId);
			return ps.executeUpdate();
		}
	}

	/**
	 * 특정 ID로 보증인 정보를 조회하는 메서드입니다. 特定のIDで保証人情報を照会するメソッドです。
	 *
	 * @param conn        데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param hoshouninId 조회할 보증인의 ID / 照会する保証人のID
	 * @return 조회된 보증인 정보 객체, 존재하지 않으면 null 반환 / 照会された保証人情報オブジェクト、存在しない場合はnullを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public Hoshounin getHoshouninById(Connection conn, int hoshouninId) throws SQLException {
		String sql = "SELECT h.*, s.namae_kana FROM Hoshounin h "
				+ "JOIN Shain s ON h.shain_id = s.shain_id WHERE h.Hoshounin_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, hoshouninId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Hoshounin(rs.getInt("Hoshounin_id"), rs.getInt("shain_id"),
						rs.getBigDecimal("hoshou_kingaku"), rs.getDate("kaishi_nengappi"),
						rs.getDate("shuuryou_nengappi"), rs.getString("Hoshounin_mei"), rs.getString("kankei"),
						rs.getString("Hoshounin_jumin_bangou"), rs.getString("Hoshounin_denwa"));
			}
		}
		return null;
	}

	/**
	 * 모든 보증인 정보를 조회하는 메서드입니다. すべての保証人情報を照会するメソッドです。
	 *
	 * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @return 조회된 보증인 정보 목록을 반환 / 照会された保証人情報リストを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public List<Hoshounin> selectAllHoshounin(Connection conn) throws SQLException {
		String sql = "SELECT h.*, s.namae_kana FROM Hoshounin h JOIN Shain s ON h.shain_id = s.shain_id";
		List<Hoshounin> hoshouninList = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Hoshounin hoshounin = new Hoshounin(rs.getInt("Hoshounin_id"), rs.getInt("shain_id"),
						rs.getBigDecimal("hoshou_kingaku"), rs.getDate("kaishi_nengappi"),
						rs.getDate("shuuryou_nengappi"), rs.getString("Hoshounin_mei"), rs.getString("kankei"),
						rs.getString("Hoshounin_jumin_bangou"), rs.getString("Hoshounin_denwa"));
				hoshouninList.add(hoshounin);
			}
		}
		return hoshouninList;
	}
}
