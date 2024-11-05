package dao;
//김찬호 金燦鎬
//급여항목 설정
//給与項目設定
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kihonkankyousettei.model.KyuuyoKoumoku;

/**
 * KyuuyoKoumokuDao는 급여 항목 정보를 관리하는 데이터 접근 객체입니다.
 * KyuuyoKoumokuDaoは給与項目情報を管理するデータアクセスオブジェクトです。
 */
public class KyuuyoKoumokuDao {

	private static KyuuyoKoumokuDao instance = new KyuuyoKoumokuDao();

	/**
	 * KyuuyoKoumokuDao의 인스턴스를 반환합니다. KyuuyoKoumokuDaoのインスタンスを返します。
	 */
	public static KyuuyoKoumokuDao getInstance() {
		return instance;
	}

	/**
	 * 급여 항목을 데이터베이스에 추가합니다. 給与項目をデータベースに追加します。
	 */
	public int insertKyuuyoKoumoku(Connection conn, KyuuyoKoumoku kyuuyoKoumoku) throws SQLException {
		String query = "INSERT INTO kyuuyoKoumoku (kyuuyoKoumoku_id, kyuuyoKoumoku_mei, kazeiKubun, hikazeiGendogaku, "
				+ "bikou, keisanHouhou, zenshaDani, kintaiRenkei, ikkatsuShiharai, ikkatsuShiharaiGaku, shiyouUmu) "
				+ "VALUES (KyuuyoKoumoku_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, kyuuyoKoumoku.getKyuuyoKoumoku_mei());
			ps.setString(2, kyuuyoKoumoku.getKazeiKubun());
			ps.setBigDecimal(3, kyuuyoKoumoku.getHikazeiGendogaku());
			ps.setString(4, kyuuyoKoumoku.getBikou());
			ps.setString(5, kyuuyoKoumoku.getKeisanHouhou());
			ps.setString(6, kyuuyoKoumoku.getZenshaDani());
			ps.setString(7, kyuuyoKoumoku.getKintaiRenkei());
			ps.setString(8, kyuuyoKoumoku.getIkkatsuShiharai());
			ps.setBigDecimal(9, kyuuyoKoumoku.getIkkatsuShiharaiGaku());
			ps.setString(10, String.valueOf(kyuuyoKoumoku.getShiyouUmu()));

			int affectedRows = ps.executeUpdate();
			conn.commit(); // 명시적으로 커밋 추가 / 明示的にコミットを追加
			return affectedRows;
		} catch (SQLException e) {
			conn.rollback(); // 오류 발생 시 롤백 / エラー発生時にロールバック
			throw e;
		}
	}

	/**
	 * 특정 급여 항목을 업데이트합니다. 特定の給与項目を更新します。
	 */
	public int updateKyuuyoKoumoku(Connection conn, KyuuyoKoumoku kyuuyoKoumoku, int kyuuyoKoumoku_id)
			throws SQLException {
		String query = "UPDATE kyuuyoKoumoku SET kyuuyoKoumoku_mei = ?, kazeiKubun = ?, hikazeiGendogaku = ?, "
				+ "bikou = ?, keisanHouhou = ?, zenshaDani = ?, kintaiRenkei = ?, "
				+ "ikkatsuShiharai = ?, ikkatsuShiharaiGaku = ?, shiyouUmu = ? WHERE kyuuyoKoumoku_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, kyuuyoKoumoku.getKyuuyoKoumoku_mei());
			ps.setString(2, kyuuyoKoumoku.getKazeiKubun());
			ps.setBigDecimal(3, kyuuyoKoumoku.getHikazeiGendogaku());
			ps.setString(4, kyuuyoKoumoku.getBikou());
			ps.setString(5, kyuuyoKoumoku.getKeisanHouhou());
			ps.setString(6, kyuuyoKoumoku.getZenshaDani());
			ps.setString(7, kyuuyoKoumoku.getKintaiRenkei());
			ps.setString(8, kyuuyoKoumoku.getIkkatsuShiharai());
			ps.setBigDecimal(9, kyuuyoKoumoku.getIkkatsuShiharaiGaku());
			ps.setString(10, String.valueOf(kyuuyoKoumoku.getShiyouUmu()));
			ps.setInt(11, kyuuyoKoumoku_id);

			return ps.executeUpdate();
		}
	}

	/**
	 * 특정 ID로 급여 항목을 조회합니다. 特定のIDで給与項目を照会します。
	 */
	public KyuuyoKoumoku getKyuuyoKoumokuById(Connection conn, int kyuuyoKoumoku_id) throws SQLException {
		String query = "SELECT * FROM kyuuyoKoumoku WHERE kyuuyoKoumoku_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, kyuuyoKoumoku_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				KyuuyoKoumoku kyuuyoKoumoku = new KyuuyoKoumoku();
				kyuuyoKoumoku.setKyuuyoKoumoku_id(rs.getInt("kyuuyoKoumoku_id"));
				kyuuyoKoumoku.setKyuuyoKoumoku_mei(rs.getString("kyuuyoKoumoku_mei"));
				kyuuyoKoumoku.setKazeiKubun(rs.getString("kazeiKubun"));
				kyuuyoKoumoku.setHikazeiGendogaku(rs.getBigDecimal("hikazeiGendogaku"));
				kyuuyoKoumoku.setBikou(rs.getString("bikou"));
				kyuuyoKoumoku.setKeisanHouhou(rs.getString("keisanHouhou"));
				kyuuyoKoumoku.setZenshaDani(rs.getString("zenshaDani"));
				kyuuyoKoumoku.setKintaiRenkei(rs.getString("kintaiRenkei"));
				kyuuyoKoumoku.setIkkatsuShiharai(rs.getString("ikkatsuShiharai"));
				kyuuyoKoumoku.setIkkatsuShiharaiGaku(rs.getBigDecimal("ikkatsuShiharaiGaku"));
				kyuuyoKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));
				return kyuuyoKoumoku;
			}
		}
		return null;
	}

	/**
	 * 특정 ID의 급여 항목을 삭제합니다. 特定のIDの給与項目を削除します。
	 */
	public void deleteKyuuyoKoumoku(Connection conn, int kyuuyoKoumoku_id) throws SQLException {
		String query = "DELETE FROM kyuuyoKoumoku WHERE kyuuyoKoumoku_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, kyuuyoKoumoku_id);
			ps.executeUpdate();
		}
	}

	/**
	 * 모든 급여 항목 목록을 조회합니다. すべての給与項目リストを照会します。
	 */
	public ArrayList<KyuuyoKoumoku> selectAllKyuuyoKoumoku(Connection conn) throws SQLException {
		String query = "SELECT * FROM kyuuyoKoumoku";
		ArrayList<KyuuyoKoumoku> kyuuyoKoumokuList = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				KyuuyoKoumoku kyuuyoKoumoku = new KyuuyoKoumoku();
				kyuuyoKoumoku.setKyuuyoKoumoku_id(rs.getInt("kyuuyoKoumoku_id"));
				kyuuyoKoumoku.setKyuuyoKoumoku_mei(rs.getString("kyuuyoKoumoku_mei"));
				kyuuyoKoumoku.setKazeiKubun(rs.getString("kazeiKubun"));
				kyuuyoKoumoku.setHikazeiGendogaku(rs.getBigDecimal("hikazeiGendogaku"));
				kyuuyoKoumoku.setBikou(rs.getString("bikou"));
				kyuuyoKoumoku.setKeisanHouhou(rs.getString("keisanHouhou"));
				kyuuyoKoumoku.setZenshaDani(rs.getString("zenshaDani"));
				kyuuyoKoumoku.setKintaiRenkei(rs.getString("kintaiRenkei"));
				kyuuyoKoumoku.setIkkatsuShiharai(rs.getString("ikkatsuShiharai"));
				kyuuyoKoumoku.setIkkatsuShiharaiGaku(rs.getBigDecimal("ikkatsuShiharaiGaku"));
				kyuuyoKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));

				kyuuyoKoumokuList.add(kyuuyoKoumoku);
			}
		}
		return kyuuyoKoumokuList;
	}
}
