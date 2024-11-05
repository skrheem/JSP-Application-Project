package kihonkankyousettei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kihonkankyousettei.model.ShainKyuukaNissuu;

/**
 * ShainKyuukaNissuuDao는 사원별 휴가일수에 대한 데이터베이스 작업을 담당하는 클래스입니다.
 * ShainKyuukaNissuuDaoは社員別休暇日数に関するデータベース操作を担当するクラスです。
 */
public class ShainKyuukaNissuuDao {

	private static ShainKyuukaNissuuDao instance = new ShainKyuukaNissuuDao();

	/**
	 * ShainKyuukaNissuuDao의 인스턴스를 반환합니다. ShainKyuukaNissuuDaoのインスタンスを返します。
	 */
	public static ShainKyuukaNissuuDao getInstance() {
		return instance;
	}

	/**
	 * 사원별 휴가일수를 데이터베이스에 추가하는 메서드입니다. 社員別休暇日数をデータベースに追加するメソッドです。
	 *
	 * @param conn              데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param shainKyuukaNissuu 추가할 사원별 휴가일수 객체 / 追加する社員別休暇日数オブジェクト
	 * @return 영향을 받은 행 수를 반환 / 影響を受けた行数を返します
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public int insertShainKyuukaNissuu(Connection conn, ShainKyuukaNissuu shainKyuukaNissuu) throws SQLException {
		String query = "INSERT INTO ShainKyuukaNissuu (shain_id, kyuukaKoumoku_id, kyuukaNissuu) VALUES (?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, shainKyuukaNissuu.getShain_id());
			ps.setInt(2, shainKyuukaNissuu.getKyuukaKoumoku_id());
			ps.setDouble(3, shainKyuukaNissuu.getKyuukaNissuu());
			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("사원별 휴가일수 추가 실패: no rows affected. / 社員別休暇日数の追加に失敗しました：影響を受けた行がありません。");
			}
			return affectedRows;
		}
	}

	/**
	 * 사원 ID를 기반으로 사원별 휴가일수를 조회하는 메서드입니다. 社員IDを基に社員別休暇日数を照会するメソッドです。
	 *
	 * @param conn    데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param shainId 조회할 사원의 ID / 照会する社員のID
	 * @return 조회된 사원별 휴가일수 목록 / 照会された社員別休暇日数のリスト
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public ArrayList<ShainKyuukaNissuu> getShainKyuukaNissuuByShainId(Connection conn, int shainId)
			throws SQLException {
		String query = "SELECT * FROM ShainKyuukaNissuu WHERE shain_id = ?";
		ArrayList<ShainKyuukaNissuu> kyuukaList = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, shainId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ShainKyuukaNissuu kyuuka = new ShainKyuukaNissuu(rs.getInt("shain_id"),
							rs.getInt("kyuukaKoumoku_id"), rs.getInt("kyuukaNissuu"));
					kyuukaList.add(kyuuka);
				}
			}
		}
		return kyuukaList;
	}

	/**
	 * 사원별 휴가일수를 수정하는 메서드입니다. 社員別休暇日数を更新するメソッドです。
	 *
	 * @param conn              데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param shainKyuukaNissuu 업데이트할 사원별 휴가일수 객체 / 更新する社員別休暇日数オブジェクト
	 * @return 업데이트된 행의 수를 반환 / 更新された行数を返します
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public int updateShainKyuukaNissuu(Connection conn, ShainKyuukaNissuu shainKyuukaNissuu) throws SQLException {
		String query = "UPDATE ShainKyuukaNissuu SET kyuukaNissuu = ? WHERE shain_id = ? AND kyuukaKoumoku_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setDouble(1, shainKyuukaNissuu.getKyuukaNissuu());
			ps.setInt(2, shainKyuukaNissuu.getShain_id());
			ps.setInt(3, shainKyuukaNissuu.getKyuukaKoumoku_id());
			return ps.executeUpdate();
		}
	}

	/**
	 * 사원별 휴가일수를 삭제하는 메서드입니다. 社員別休暇日数を削除するメソッドです。
	 *
	 * @param conn            데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param shainId         삭제할 사원의 ID / 削除する社員のID
	 * @param kyuukaKoumokuId 삭제할 휴가 항목 ID / 削除する休暇項目のID
	 * @return 삭제된 행의 수를 반환 / 削除された行数を返します
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public int deleteShainKyuukaNissuu(Connection conn, int shainId, int kyuukaKoumokuId) throws SQLException {
		String query = "DELETE FROM ShainKyuukaNissuu WHERE shain_id = ? AND kyuukaKoumoku_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, shainId);
			ps.setInt(2, kyuukaKoumokuId);
			return ps.executeUpdate();
		}
	}
}
