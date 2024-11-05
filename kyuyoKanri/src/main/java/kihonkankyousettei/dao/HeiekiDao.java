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

import kihonkankyousettei.model.Heieki;

/**
 * HeiekiDao는 병역 정보 관련 데이터베이스 접근을 담당하는 DAO 클래스입니다.
 * HeiekiDaoは兵役情報関連のデータベースアクセスを担当するDAOクラスです。
 */
public class HeiekiDao {

	private static HeiekiDao instance = new HeiekiDao();

	/**
	 * HeiekiDao의 인스턴스를 반환합니다. HeiekiDaoのインスタンスを返します。
	 *
	 * @return HeiekiDao 인스턴스 / HeiekiDaoインスタンス
	 */
	public static HeiekiDao getInstance() {
		return instance;
	}

	/**
	 * 병역 정보를 삽입하는 메서드입니다. 兵役情報を挿入するメソッドです。
	 *
	 * @param conn   데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param heieki 삽입할 병역 정보 객체 / 挿入する兵役情報オブジェクト
	 * @return 영향을 받은 행의 수 / 影響を受けた行数
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int insertHeieki(Connection conn, Heieki heieki) throws SQLException {
		String sql = "INSERT INTO Heieki (heieki_id, shain_id, heiekiKubun, gunBetsu, fukumukiKaishi, fukumukiShuuryou, Kaikyuu, heika, mirikouRiyuuCode) "
				+ "VALUES (heieki_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, heieki.getShain_id());
			ps.setString(2, heieki.getHeiekiKubun());
			ps.setString(3, heieki.getGunBetsu());
			ps.setDate(4, heieki.getFukumukiKaishi() != null ? new java.sql.Date(heieki.getFukumukiKaishi().getTime())
					: null);
			ps.setDate(5,
					heieki.getFukumukiShuuryou() != null ? new java.sql.Date(heieki.getFukumukiShuuryou().getTime())
							: null);
			ps.setString(6, heieki.getKaikyuu());
			ps.setString(7, heieki.getHeika());
			ps.setString(8, heieki.getMirikouRiyuuCode());
			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("병역 추가 실패: no rows affected。/ 兵役追加に失敗しました：影響を受けた行がありません。");
			}
			return affectedRows;
		}
	}

	/**
	 * 병역 정보를 업데이트하는 메서드입니다. 兵役情報を更新するメソッドです。
	 *
	 * @param conn   데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param heieki 업데이트할 병역 정보 객체 / 更新する兵役情報オブジェクト
	 * @return 업데이트된 행의 수를 반환 / 更新された行数を返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int updateHeieki(Connection conn, Heieki heieki) throws SQLException {
		String sql = "UPDATE Heieki SET shain_id = ?, heiekiKubun = ?, gunBetsu = ?, fukumukiKaishi = ?, "
				+ "fukumukiShuuryou = ?, Kaikyuu = ?, heika = ?, mirikouRiyuuCode = ? WHERE heieki_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, heieki.getShain_id());
			ps.setString(2, heieki.getHeiekiKubun());
			ps.setString(3, heieki.getGunBetsu());
			ps.setDate(4, heieki.getFukumukiKaishi() != null ? new java.sql.Date(heieki.getFukumukiKaishi().getTime())
					: null);
			ps.setDate(5,
					heieki.getFukumukiShuuryou() != null ? new java.sql.Date(heieki.getFukumukiShuuryou().getTime())
							: null);
			ps.setString(6, heieki.getKaikyuu());
			ps.setString(7, heieki.getHeika());
			ps.setString(8, heieki.getMirikouRiyuuCode());
			ps.setInt(9, heieki.getHeieki_id());
			return ps.executeUpdate();
		}
	}

	/**
	 * 특정 ID로 병역 정보를 조회하는 메서드입니다. 特定のIDで兵役情報を照会するメソッドです。
	 *
	 * @param conn      데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param heieki_id 조회할 병역 정보의 ID / 照会する兵役情報のID
	 * @return 조회된 병역 정보 객체, 존재하지 않으면 null 반환 / 照会された兵役情報オブジェクト、存在しない場合はnullを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public Heieki getHeiekiById(Connection conn, int heieki_id) throws SQLException {
		String sql = "SELECT h.*, s.namae_kana FROM Heieki h "
				+ "JOIN Shain s ON h.shain_id = s.shain_id WHERE h.heieki_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, heieki_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Heieki heieki = new Heieki();
				heieki.setHeieki_id(rs.getInt("heieki_id"));
				heieki.setShain_id(rs.getInt("shain_id"));
				heieki.setHeiekiKubun(rs.getString("heiekiKubun"));
				heieki.setGunBetsu(rs.getString("gunBetsu"));
				heieki.setFukumukiKaishi(
						rs.getDate("fukumukiKaishi") != null ? new Date(rs.getDate("fukumukiKaishi").getTime()) : null);
				heieki.setFukumukiShuuryou(
						rs.getDate("fukumukiShuuryou") != null ? new Date(rs.getDate("fukumukiShuuryou").getTime())
								: null);
				heieki.setKaikyuu(rs.getString("Kaikyuu"));
				heieki.setHeika(rs.getString("heika"));
				heieki.setMirikouRiyuuCode(rs.getString("mirikouRiyuuCode"));
				return heieki;
			}
		}
		return null;
	}

	/**
	 * 모든 병역 정보를 조회하는 메서드입니다. すべての兵役情報を照会するメソッドです。
	 *
	 * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @return 조회된 병역 정보 목록을 반환 / 照会された兵役情報リストを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public ArrayList<Heieki> selectAllHeieki(Connection conn) throws SQLException {
		String sql = "SELECT h.*, s.namae_kana FROM Heieki h JOIN Shain s ON h.shain_id = s.shain_id";
		ArrayList<Heieki> heiekiList = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Heieki heieki = new Heieki();
				heieki.setHeieki_id(rs.getInt("heieki_id"));
				heieki.setShain_id(rs.getInt("shain_id"));
				heieki.setHeiekiKubun(rs.getString("heiekiKubun"));
				heieki.setGunBetsu(rs.getString("gunBetsu"));
				heieki.setFukumukiKaishi(
						rs.getDate("fukumukiKaishi") != null ? new Date(rs.getDate("fukumukiKaishi").getTime()) : null);
				heieki.setFukumukiShuuryou(
						rs.getDate("fukumukiShuuryou") != null ? new Date(rs.getDate("fukumukiShuuryou").getTime())
								: null);
				heieki.setKaikyuu(rs.getString("Kaikyuu"));
				heieki.setHeika(rs.getString("heika"));
				heieki.setMirikouRiyuuCode(rs.getString("mirikouRiyuuCode"));
				heiekiList.add(heieki);
			}
		}
		return heiekiList;
	}
}
