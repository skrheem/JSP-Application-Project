package dao;
//김찬호 金燦鎬
//휴가근태설정
//休暇勤怠設定
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kihonkankyousettei.model.KintaiKoumoku;

/**
 * KintaiKoumokuDao는 근태 항목에 대한 데이터베이스 작업을 담당하는 클래스입니다.
 * KintaiKoumokuDaoは勤怠項目に関するデータベース操作を担当するクラスです。
 */
public class KintaiKoumokuDao {

	private static KintaiKoumokuDao instance = new KintaiKoumokuDao();

	/**
	 * KintaiKoumokuDao의 인스턴스를 반환합니다. KintaiKoumokuDaoのインスタンスを返します。
	 */
	public static KintaiKoumokuDao getInstance() {
		return instance;
	}

	/**
	 * 새로운 근태 항목을 데이터베이스에 추가하는 메서드입니다. 新しい勤怠項目をデータベースに追加するメソッドです。
	 *
	 * @param conn   데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param kintai 추가할 근태 항목 객체 / 追加する勤怠項目オブジェクト
	 * @return 영향을 받은 행 수를 반환 / 影響を受けた行数を返します
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public int insertKintaiKoumoku(Connection conn, KintaiKoumoku kintai) throws SQLException {
		String query = "INSERT INTO KintaiKoumoku (kintai_id, kintai_mei, tani, kyuukaKoumoku_id, group_id, roudouJikanRenkei, shiyouUmu) "
				+ "VALUES (KintaiKoumoku_sequence.nextVal, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, kintai.getKintai_mei());
			ps.setString(2, kintai.getTani());
			ps.setObject(3, kintai.getKyuukaKoumoku_id());
			ps.setObject(4, kintai.getGroup_id());
			ps.setString(5, kintai.getRoudouJikanRenkei());
			ps.setString(6, String.valueOf(kintai.getShiyouUmu()));
			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("근태 항목 추가 실패: no rows affected. / 勤怠項目の追加に失敗しました：影響を受けた行がありません。");
			}
			return affectedRows;
		}
	}

	/**
	 * 기존 근태 항목의 정보를 수정하는 메서드입니다. 既存の勤怠項目の情報を更新するメソッドです。
	 *
	 * @param conn          데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param kintaiKoumoku 업데이트할 근태 항목 객체 / 更新する勤怠項目オブジェクト
	 * @return 업데이트된 행의 수를 반환 / 更新された行数を返します
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public int updateKintaiKoumoku(Connection conn, KintaiKoumoku kintaiKoumoku) throws SQLException {
		if (kintaiKoumoku.getKintai_id() == null) {
			System.out.println("Kintai_id가 null입니다. 업데이트를 수행할 수 없습니다. / Kintai_idがnullです。更新を行うことができません。");
			return 0;
		}

		String query = "UPDATE KintaiKoumoku SET kintai_mei = ?, tani = ?, kyuukaKoumoku_id = ?, group_id = ?, roudouJikanRenkei = ?, shiyouUmu = ? "
				+ "WHERE kintai_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, kintaiKoumoku.getKintai_mei());
			ps.setString(2, kintaiKoumoku.getTani());
			ps.setObject(3, kintaiKoumoku.getKyuukaKoumoku_id());
			ps.setObject(4, kintaiKoumoku.getGroup_id());
			ps.setString(5, kintaiKoumoku.getRoudouJikanRenkei());
			ps.setString(6, String.valueOf(kintaiKoumoku.getShiyouUmu()));
			ps.setInt(7, kintaiKoumoku.getKintai_id());
			return ps.executeUpdate();
		}
	}

	/**
	 * 특정 ID를 가진 근태 항목 정보를 조회하는 메서드입니다. 特定のIDを持つ勤怠項目情報を照会するメソッドです。
	 *
	 * @param conn      데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param kintai_id 조회할 근태 항목의 ID / 照会する勤怠項目のID
	 * @return 조회된 근태 항목 객체, 없을 경우 null / 照会された勤怠項目オブジェクト、存在しない場合はnull
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public KintaiKoumoku getKintaiKoumokuById(Connection conn, Integer kintai_id) throws SQLException {
		KintaiKoumoku kintaiKoumoku = new KintaiKoumoku();
		String query = "SELECT * FROM KintaiKoumoku WHERE kintai_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, kintai_id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					kintaiKoumoku.setKintai_id(rs.getInt("kintai_id"));
					kintaiKoumoku.setKintai_mei(rs.getString("kintai_mei"));
					kintaiKoumoku.setTani(rs.getString("tani"));
					kintaiKoumoku.setKyuukaKoumoku_id(rs.getInt("kyuukaKoumoku_id"));
					kintaiKoumoku.setGroup_id(rs.getInt("group_id"));
					kintaiKoumoku.setRoudouJikanRenkei(rs.getString("roudouJikanRenkei"));
					kintaiKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));
				}
			}
		}
		return kintaiKoumoku;
	}

	/**
	 * 모든 근태 항목 목록을 조회하는 메서드입니다. すべての勤怠項目のリストを照会するメソッドです。
	 *
	 * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @return 근태 항목 목록을 포함하는 ArrayList / 勤怠項目のリストを含むArrayList
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public ArrayList<KintaiKoumoku> getKintaiKoumokuList(Connection conn) throws SQLException {
		ArrayList<KintaiKoumoku> kintaiList = new ArrayList<>();
		String query = "SELECT * FROM KintaiKoumoku";

		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				KintaiKoumoku kintaiKoumoku = new KintaiKoumoku();
				kintaiKoumoku.setKintai_id(rs.getInt("kintai_id"));
				kintaiKoumoku.setKintai_mei(rs.getString("kintai_mei"));
				kintaiKoumoku.setTani(rs.getString("tani"));
				kintaiKoumoku.setKyuukaKoumoku_id(rs.getInt("kyuukaKoumoku_id"));
				kintaiKoumoku.setGroup_id(rs.getInt("group_id"));
				kintaiKoumoku.setRoudouJikanRenkei(rs.getString("roudouJikanRenkei"));
				kintaiKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));
				kintaiList.add(kintaiKoumoku);
			}
		}
		return kintaiList;
	}
}
