package dao;

//김찬호 金燦鎬
//기본환경설정 사용자정보
//基本環境設定 使用者情報
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import kihonkankyousettei.model.KaishaJouhou;

/**
 * KaishaJouhouDao는 회사 정보 관련 데이터베이스 접근을 담당하는 DAO 클래스입니다.
 * KaishaJouhouDaoは会社情報関連のデータベースアクセスを担当するDAOクラスです。
 */
public class KaishaJouhouDao {

	private static KaishaJouhouDao instance = new KaishaJouhouDao();

	/**
	 * KaishaJouhouDao의 인스턴스를 반환합니다.
	 * KaishaJouhouDaoのインスタンスを返します。
	 *
	 * @return KaishaJouhouDao 인스턴스 / KaishaJouhouDaoインスタンス
	 */
	public static KaishaJouhouDao getInstance() {
		return instance;
	}

	/**
	 * 회사 정보를 데이터베이스에 입력하는 메서드입니다.
	 * 会社情報をデータベースに挿入するメソッドです。
	 *
	 * @param conn  데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param kaisha 삽입할 회사 정보 객체 / 挿入する会社情報オブジェクト
	 * @return 영향을 받은 행의 수 / 影響を受けた行数
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int insertKaisha(Connection conn, KaishaJouhou kaisha) throws SQLException {
		String sql = "INSERT INTO KaishaJouhou (kaisha_id, kaisha_mei, jigyousha_touroku_bango, setsuribi, jigyousho_jyuusho, "
				+ "yuubin_bango, daihyousha_yakushoku, daihyousha_mei, houjin_touroku_bango, homepage, denwa_kaisha, "
				+ "fakkusu_bango, gyoutai, shumoku, kaisha_image_keiro, inkan_image_keiro) "
				+ "VALUES (KaishaJouhou_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, kaisha.getKaisha_mei());
			ps.setString(2, kaisha.getJigyousha_touroku_bango());
			ps.setDate(3, kaisha.getSetsuribi() != null ? new java.sql.Date(kaisha.getSetsuribi().getTime()) : null);
			ps.setString(4, kaisha.getJigyousho_jyuusho());
			ps.setString(5, kaisha.getYuubin_bango());
			ps.setString(6, kaisha.getDaihyousha_yakushoku());
			ps.setString(7, kaisha.getDaihyousha_mei());
			ps.setString(8, kaisha.getHoujin_touroku_bango());
			ps.setString(9, kaisha.getHomepage());
			ps.setString(10, kaisha.getDenwa_kaisha());
			ps.setString(11, kaisha.getFakkusu_bango());
			ps.setString(12, kaisha.getGyoutai());
			ps.setString(13, kaisha.getShumoku());
			ps.setString(14, kaisha.getKaisha_image_keiro());
			ps.setString(15, kaisha.getInkan_image_keiro());
			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("회사 정보 추가 실패: no rows affected。/ 会社情報の追加に失敗しました：影響を受けた行がありません。");
			}
			return affectedRows;
		}
	}

	/**
	 * 특정 회사 ID로 회사 정보를 조회하는 메서드입니다.
	 * 特定の会社IDで会社情報を照会するメソッドです。
	 *
	 * @param conn      데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param kaishaId  조회할 회사의 ID / 照会する会社のID
	 * @return 조회된 회사 정보 객체, 존재하지 않으면 null 반환 / 照会された会社情報オブジェクト、存在しない場合はnullを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public KaishaJouhou getKaishaById(Connection conn, Integer kaishaId) throws SQLException {
		KaishaJouhou kaisha = new KaishaJouhou();
		String sql = "SELECT * FROM KaishaJouhou WHERE Kaisha_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, kaishaId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					kaisha.setKaisha_id(rs.getInt("kaisha_id"));
					kaisha.setKaisha_mei(rs.getString("kaisha_mei"));
					kaisha.setJigyousha_touroku_bango(rs.getString("jigyousha_touroku_bango"));
					kaisha.setSetsuribi(rs.getDate("setsuribi"));
					kaisha.setJigyousho_jyuusho(rs.getString("jigyousho_jyuusho"));
					kaisha.setYuubin_bango(rs.getString("yuubin_bango"));
					kaisha.setDaihyousha_yakushoku(rs.getString("daihyousha_yakushoku"));
					kaisha.setDaihyousha_mei(rs.getString("daihyousha_mei"));
					kaisha.setHoujin_touroku_bango(rs.getString("houjin_touroku_bango"));
					kaisha.setHomepage(rs.getString("homepage"));
					kaisha.setDenwa_kaisha(rs.getString("denwa_kaisha"));
					kaisha.setFakkusu_bango(rs.getString("fakkusu_bango"));
					kaisha.setGyoutai(rs.getString("gyoutai"));
					kaisha.setShumoku(rs.getString("shumoku"));
					kaisha.setKaisha_image_keiro(rs.getString("kaisha_image_keiro"));
					kaisha.setInkan_image_keiro(rs.getString("inkan_image_keiro"));
				}
			}
		}
		return kaisha;
	}

	/**
	 * 주어진 회사 정보를 데이터베이스에서 업데이트하는 메서드입니다.
	 * 指定された会社情報をデータベースで更新するメソッドです。
	 *
	 * @param conn      데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param kaisha    업데이트할 회사 정보 객체 / 更新する会社情報オブジェクト
	 * @param kaishaId  업데이트할 회사 ID / 更新する会社ID
	 * @return 업데이트된 행의 수를 반환 / 更新された行数を返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int updateKaisha(Connection conn, KaishaJouhou kaisha, Integer kaishaId) throws SQLException {
		String sql = "UPDATE KaishaJouhou SET kaisha_mei = ?, jigyousha_touroku_bango = ?, setsuribi = ?, "
				+ "jigyousho_jyuusho = ?, yuubin_bango = ?, daihyousha_yakushoku = ?, daihyousha_mei = ?, "
				+ "houjin_touroku_bango = ?, homepage = ?, denwa_kaisha = ?, fakkusu_bango = ?, gyoutai = ?, "
				+ "shumoku = ?, kaisha_image_keiro = ?, inkan_image_keiro = ? WHERE kaisha_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, kaisha.getKaisha_mei());
			ps.setString(2, kaisha.getJigyousha_touroku_bango());
			ps.setDate(3, kaisha.getSetsuribi() != null ? new java.sql.Date(kaisha.getSetsuribi().getTime()) : null);
			ps.setString(4, kaisha.getJigyousho_jyuusho());
			ps.setString(5, kaisha.getYuubin_bango());
			ps.setString(6, kaisha.getDaihyousha_yakushoku());
			ps.setString(7, kaisha.getDaihyousha_mei());
			ps.setString(8, kaisha.getHoujin_touroku_bango());
			ps.setString(9, kaisha.getHomepage());
			ps.setString(10, kaisha.getDenwa_kaisha());
			ps.setString(11, kaisha.getFakkusu_bango());
			ps.setString(12, kaisha.getGyoutai());
			ps.setString(13, kaisha.getShumoku());
			ps.setString(14, kaisha.getKaisha_image_keiro());
			ps.setString(15, kaisha.getInkan_image_keiro());
			ps.setInt(16, kaishaId);
			return ps.executeUpdate();
		}
	}
}
