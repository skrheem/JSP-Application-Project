package kihonkankyousettei.dao;
//김찬호 金燦鎬
//기본환경설정 사원등록
//基本環境設定 社員登録
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kihonkankyousettei.model.Gakureki;

/**
 * GakurekiDao는 학력 정보 관련 데이터베이스 접근을 담당하는 DAO 클래스입니다.
 * GakurekiDaoは学歴情報関連のデータベースアクセスを担当するDAOクラスです。
 */
public class GakurekiDao {

	private static GakurekiDao instance = new GakurekiDao();

	/**
	 * GakurekiDao의 인스턴스를 반환합니다. GakurekiDaoのインスタンスを返します。
	 *
	 * @return GakurekiDao 인스턴스 / GakurekiDaoインスタンス
	 */
	public static GakurekiDao getInstance() {
		return instance;
	}

	/**
	 * 학력 정보를 삽입하는 메서드입니다. 学歴情報を挿入するメソッドです。
	 *
	 * @param conn     데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param gakureki 삽입할 학력 정보 객체 / 挿入する学歴情報オブジェクト
	 * @return 영향을 받은 행의 수 / 影響を受けた行数
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int insertGakureki(Connection conn, Gakureki gakureki) throws SQLException {
		String sql = "INSERT INTO Gakureki (gakureki_id, shain_id, gakurekiKubun, nyugakuNengatsu, sotsugyoNengatsu, gakkoNama, senmon, shuuryouJyoutai) "
				+ "VALUES (gakureki_sequence.nextval, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, gakureki.getShain_id());
			ps.setString(2, gakureki.getGakurekiKubun());
			ps.setDate(3, new java.sql.Date(gakureki.getNyugakuNengatsu().getTime()));
			ps.setDate(4, new java.sql.Date(gakureki.getSotsugyoNengatsu().getTime()));
			ps.setString(5, gakureki.getGakkoName());
			ps.setString(6, gakureki.getSenmon());
			ps.setString(7, gakureki.getShuuryouJyoutai());
			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("학력 추가 실패: no rows affected。/ 学歴追加に失敗しました：影響を受けた行がありません。");
			}
			return affectedRows;
		}
	}

	/**
	 * 학력 정보를 업데이트하는 메서드입니다. 学歴情報を更新するメソッドです。
	 *
	 * @param conn     데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param gakureki 업데이트할 학력 정보 객체 / 更新する学歴情報オブジェクト
	 * @return 업데이트된 행의 수를 반환 / 更新された行数を返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int updateGakureki(Connection conn, Gakureki gakureki) throws SQLException {
		String sql = "UPDATE Gakureki SET shain_id = ?, gakurekiKubun = ?, nyugakuNengatsu = ?, sotsugyoNengatsu = ?, "
				+ "gakkoNama = ?, senmon = ?, shuuryouJyoutai = ? WHERE gakureki_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, gakureki.getShain_id());
			ps.setString(2, gakureki.getGakurekiKubun());
			ps.setDate(3, new java.sql.Date(gakureki.getNyugakuNengatsu().getTime()));
			ps.setDate(4, new java.sql.Date(gakureki.getSotsugyoNengatsu().getTime()));
			ps.setString(5, gakureki.getGakkoName());
			ps.setString(6, gakureki.getSenmon());
			ps.setString(7, gakureki.getShuuryouJyoutai());
			ps.setInt(8, gakureki.getGakureki_id());
			return ps.executeUpdate();
		}
	}

	/**
	 * 특정 ID로 학력 정보를 조회하는 메서드입니다. 特定のIDで学歴情報を照会するメソッドです。
	 *
	 * @param conn        데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param gakureki_id 조회할 학력 정보의 ID / 照会する学歴情報のID
	 * @return 조회된 학력 정보 객체, 존재하지 않으면 null 반환 / 照会された学歴情報オブジェクト、存在しない場合はnullを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public Gakureki getGakurekiById(Connection conn, int gakureki_id) throws SQLException {
		String sql = "SELECT * FROM Gakureki WHERE gakureki_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, gakureki_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Gakureki gakureki = new Gakureki();
				gakureki.setGakureki_id(rs.getInt("gakureki_id"));
				gakureki.setShain_id(rs.getInt("shain_id"));
				gakureki.setGakurekiKubun(rs.getString("gakurekiKubun"));
				gakureki.setNyugakuNengatsu(rs.getDate("nyugakuNengatsu"));
				gakureki.setSotsugyoNengatsu(rs.getDate("sotsugyoNengatsu"));
				gakureki.setGakkoName(rs.getString("gakkoNama"));
				gakureki.setSenmon(rs.getString("senmon"));
				gakureki.setShuuryouJyoutai(rs.getString("shuuryouJyoutai"));
				return gakureki;
			}
		}
		return null;
	}

	/**
	 * 모든 학력 정보를 조회하는 메서드입니다. すべての学歴情報を照会するメソッドです。
	 *
	 * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @return 조회된 학력 정보 목록을 반환 / 照会された学歴情報リストを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public ArrayList<Gakureki> selectAllGakurekiWithJoin(Connection conn) throws SQLException {
		String sql = "SELECT g.gakureki_id, g.shain_id, g.gakurekiKubun, g.nyugakuNengatsu, g.sotsugyoNengatsu, "
				+ "g.gakkoNama, g.senmon, g.shuuryouJyoutai, s.namae_kana FROM Gakureki g "
				+ "JOIN Shain s ON g.shain_id = s.shain_id";
		ArrayList<Gakureki> gakurekiList = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Gakureki gakureki = new Gakureki();
				gakureki.setGakureki_id(rs.getInt("gakureki_id"));
				gakureki.setShain_id(rs.getInt("shain_id"));
				gakureki.setGakurekiKubun(rs.getString("gakurekiKubun"));
				gakureki.setNyugakuNengatsu(rs.getDate("nyugakuNengatsu"));
				gakureki.setSotsugyoNengatsu(rs.getDate("sotsugyoNengatsu"));
				gakureki.setGakkoName(rs.getString("gakkoNama"));
				gakureki.setSenmon(rs.getString("senmon"));
				gakureki.setShuuryouJyoutai(rs.getString("shuuryouJyoutai"));
				gakurekiList.add(gakureki);
			}
		}

		return gakurekiList;
	}
}
