package dao;
//김찬호 金燦鎬
//급여항목 설정
//給与項目設定
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kihonkankyousettei.model.KoujoKoumoku;

/**
 * KoujoKoumokuDao는 공제 항목에 대한 데이터베이스 작업을 수행하는 클래스입니다.
 * KoujoKoumokuDaoは控除項目に関するデータベース操作を行うクラスです。
 */
public class KoujoKoumokuDao {

	private static KoujoKoumokuDao instance = new KoujoKoumokuDao();

	/**
	 * KoujoKoumokuDao 인스턴스를 반환합니다. KoujoKoumokuDaoのインスタンスを返します。
	 */
	public static KoujoKoumokuDao getInstance() {
		return instance;
	}

	/**
	 * 새로운 공제 항목을 데이터베이스에 추가합니다. 新しい控除項目をデータベースに追加します。
	 *
	 * @param conn         데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param koujoKoumoku 추가할 공제 항목 객체 / 追加する控除項目オブジェクト
	 * @return 영향을 받은 행 수를 반환 / 影響を受けた行数を返します
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public int insertKoujoKoumoku(Connection conn, KoujoKoumoku koujoKoumoku) throws SQLException {
		String query = "INSERT INTO koujoKoumoku (koujoKoumoku_id, koujoKoumoku_mei, koujoRitsu, keisanHouhou, shiyouUmu) "
				+ "VALUES (koujoKoumoku_sequence.nextval, ?, ?, ?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, koujoKoumoku.getKoujoKoumoku_mei());
			ps.setBigDecimal(2, koujoKoumoku.getKoujoRitsu());
			ps.setString(3, koujoKoumoku.getKeisanHouhou());
			ps.setString(4, String.valueOf(koujoKoumoku.getShiyouUmu()));
			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("공제 항목 추가 실패: no rows affected. / 控除項目の追加に失敗しました：影響を受けた行がありません。");
			}
			return affectedRows;
		}
	}

	/**
	 * 특정 공제 항목 정보를 업데이트합니다. 特定の控除項目情報を更新します。
	 *
	 * @param conn            데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param koujoKoumoku    업데이트할 공제 항목 객체 / 更新する控除項目オブジェクト
	 * @param koujoKoumoku_id 업데이트할 공제 항목의 ID / 更新する控除項目のID
	 * @return 업데이트된 행 수를 반환 / 更新された行数を返します
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public int updateKoujoKoumoku(Connection conn, KoujoKoumoku koujoKoumoku, int koujoKoumoku_id) throws SQLException {
		String query = "UPDATE koujoKoumoku SET koujoKoumoku_mei = ?, koujoRitsu = ?, keisanHouhou = ?, shiyouUmu = ? "
				+ "WHERE koujoKoumoku_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, koujoKoumoku.getKoujoKoumoku_mei());
			ps.setBigDecimal(2, koujoKoumoku.getKoujoRitsu());
			ps.setString(3, koujoKoumoku.getKeisanHouhou());
			ps.setString(4, String.valueOf(koujoKoumoku.getShiyouUmu()));
			ps.setInt(5, koujoKoumoku_id);
			return ps.executeUpdate();
		}
	}

	/**
	 * 특정 ID로 공제 항목 정보를 조회합니다. 特定のIDで控除項目情報を照会します。
	 *
	 * @param conn            데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param koujoKoumoku_id 조회할 공제 항목의 ID / 照会する控除項目のID
	 * @return 조회된 공제 항목 객체, 없을 경우 null / 照会された控除項目オブジェクト、存在しない場合はnull
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public KoujoKoumoku getKoujoKoumokuById(Connection conn, int koujoKoumoku_id) throws SQLException {
		String query = "SELECT * FROM koujoKoumoku WHERE koujoKoumoku_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, koujoKoumoku_id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					KoujoKoumoku koujoKoumoku = new KoujoKoumoku();
					koujoKoumoku.setKoujoKoumoku_id(rs.getInt("koujoKoumoku_id"));
					koujoKoumoku.setKoujoKoumoku_mei(rs.getString("koujoKoumoku_mei"));
					koujoKoumoku.setKoujoRitsu(rs.getBigDecimal("koujoRitsu"));
					koujoKoumoku.setKeisanHouhou(rs.getString("keisanHouhou"));
					koujoKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));
					return koujoKoumoku;
				}
			}
		}
		return null;
	}

	/**
	 * 모든 공제 항목을 조회합니다. すべての控除項目を照会します。
	 *
	 * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @return 공제 항목 목록을 포함하는 ArrayList / 控除項目リストを含むArrayList
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public ArrayList<KoujoKoumoku> selectAllKoujoKoumoku(Connection conn) throws SQLException {
		String query = "SELECT * FROM koujoKoumoku";
		ArrayList<KoujoKoumoku> koujoKoumokuList = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				KoujoKoumoku koujoKoumoku = new KoujoKoumoku();
				koujoKoumoku.setKoujoKoumoku_id(rs.getInt("koujoKoumoku_id"));
				koujoKoumoku.setKoujoKoumoku_mei(rs.getString("koujoKoumoku_mei"));
				koujoKoumoku.setKoujoRitsu(rs.getBigDecimal("koujoRitsu"));
				koujoKoumoku.setKeisanHouhou(rs.getString("keisanHouhou"));
				koujoKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));

				koujoKoumokuList.add(koujoKoumoku);
			}
		}
		return koujoKoumokuList;
	}
}
