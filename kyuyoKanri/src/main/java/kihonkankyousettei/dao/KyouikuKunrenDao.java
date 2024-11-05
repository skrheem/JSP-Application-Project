package kihonkankyousettei.dao;
//김찬호 金燦鎬
//기본환경설정 사원등록
//基本環境設定 社員登録
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kihonkankyousettei.model.KyouikuKunren;

/**
 * KyouikuKunrenDao는 교육 훈련 정보를 관리하는 데이터 접근 객체입니다.
 * KyouikuKunrenDaoは教育訓練情報を管理するデータアクセスオブジェクトです。
 */
public class KyouikuKunrenDao {

	private static KyouikuKunrenDao instance = new KyouikuKunrenDao();

	/**
	 * KyouikuKunrenDao의 인스턴스를 반환합니다. KyouikuKunrenDaoのインスタンスを返します。
	 */
	public static KyouikuKunrenDao getInstance() {
		return instance;
	}

	/**
	 * 새로운 교육 훈련 정보를 데이터베이스에 추가합니다. 新しい教育訓練情報をデータベースに追加します。
	 *
	 * @param conn   데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param kunren 추가할 교육 훈련 객체 / 追加する教育訓練オブジェクト
	 * @return 영향을 받은 행 수를 반환 / 影響を受けた行数を返します
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public int insertKyouikuKunren(Connection conn, KyouikuKunren kunren) throws SQLException {
		String query = "INSERT INTO KyouikuKunren (kunren_id, shain_id, kunren_shubetsu, kyouiku_mei, kaishi_bi, shuuryou_bi, kikan, kyouiku_hiyou, henkin_hiyou) "
				+ "VALUES (KyouikuKunren_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, kunren.getShain_id());
			ps.setString(2, kunren.getKunren_shubetsu());
			ps.setString(3, kunren.getKyouiku_mei());
			ps.setDate(4, kunren.getKaishi_bi() != null ? new java.sql.Date(kunren.getKaishi_bi().getTime()) : null);
			ps.setDate(5,
					kunren.getShuuryou_bi() != null ? new java.sql.Date(kunren.getShuuryou_bi().getTime()) : null);
			ps.setString(6, kunren.getKikan());
			ps.setBigDecimal(7, kunren.getKyouiku_hiyou());
			ps.setBigDecimal(8, kunren.getHenkin_hiyou());
			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("교육 훈련 추가 실패: no rows affected. / 教育訓練追加失敗: 影響を受けた行がありません。");
			}
			return affectedRows;
		}
	}

	/**
	 * 특정 교육 훈련 정보를 업데이트합니다. 特定の教育訓練情報を更新します。
	 *
	 * @param conn     데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param kunren   업데이트할 교육 훈련 객체 / 更新する教育訓練オブジェクト
	 * @param kunrenId 업데이트할 교육 훈련 ID / 更新する教育訓練ID
	 * @return 업데이트된 행 수를 반환 / 更新された行数を返します
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public int updateKyouikuKunren(Connection conn, KyouikuKunren kunren, int kunrenId) throws SQLException {
		String query = "UPDATE KyouikuKunren SET shain_id = ?, kunren_shubetsu = ?, kyouiku_mei = ?, kaishi_bi = ?, shuuryou_bi = ?, "
				+ "kikan = ?, kyouiku_hiyou = ?, henkin_hiyou = ? WHERE kunren_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, kunren.getShain_id());
			ps.setString(2, kunren.getKunren_shubetsu());
			ps.setString(3, kunren.getKyouiku_mei());
			ps.setDate(4, kunren.getKaishi_bi() != null ? new java.sql.Date(kunren.getKaishi_bi().getTime()) : null);
			ps.setDate(5,
					kunren.getShuuryou_bi() != null ? new java.sql.Date(kunren.getShuuryou_bi().getTime()) : null);
			ps.setString(6, kunren.getKikan());
			ps.setBigDecimal(7, kunren.getKyouiku_hiyou());
			ps.setBigDecimal(8, kunren.getHenkin_hiyou());
			ps.setInt(9, kunrenId);
			return ps.executeUpdate();
		}
	}

	/**
	 * 특정 ID로 교육 훈련 정보를 조회합니다. 特定のIDで教育訓練情報を照会します。
	 *
	 * @param conn     데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param kunrenId 조회할 교육 훈련의 ID / 照会する教育訓練のID
	 * @return 조회된 교육 훈련 객체, 없을 경우 null / 照会された教育訓練オブジェクト、存在しない場合はnull
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public KyouikuKunren getKyouikuKunrenById(Connection conn, int kunrenId) throws SQLException {
		String query = "SELECT kk.*, s.namae_kana FROM KyouikuKunren kk "
				+ "JOIN Shain s ON kk.shain_id = s.shain_id WHERE kk.kunren_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, kunrenId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new KyouikuKunren(rs.getInt("kunren_id"), rs.getInt("shain_id"),
							rs.getString("kunren_shubetsu"), rs.getString("kyouiku_mei"), rs.getDate("kaishi_bi"),
							rs.getDate("shuuryou_bi"), rs.getString("kikan"), rs.getBigDecimal("kyouiku_hiyou"),
							rs.getBigDecimal("henkin_hiyou"));
				}
			}
		}
		return null;
	}

	/**
	 * 모든 교육 훈련 정보를 조회합니다. すべての教育訓練情報を照会します。
	 *
	 * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @return 교육 훈련 목록을 포함하는 ArrayList / 教育訓練リストを含むArrayList
	 * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
	 */
	public ArrayList<KyouikuKunren> selectAllKyouikuKunren(Connection conn) throws SQLException {
		String query = "SELECT kk.*, s.namae_kana FROM KyouikuKunren kk JOIN Shain s ON kk.shain_id = s.shain_id";
		ArrayList<KyouikuKunren> kunrenList = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				KyouikuKunren kunren = new KyouikuKunren(rs.getInt("kunren_id"), rs.getInt("shain_id"),
						rs.getString("kunren_shubetsu"), rs.getString("kyouiku_mei"), rs.getDate("kaishi_bi"),
						rs.getDate("shuuryou_bi"), rs.getString("kikan"), rs.getBigDecimal("kyouiku_hiyou"),
						rs.getBigDecimal("henkin_hiyou"));
				kunrenList.add(kunren);
			}
		}
		return kunrenList;
	}
}
