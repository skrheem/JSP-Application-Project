package dao;
//김찬호 金燦鎬
//기본환경설정 사원등록
//基本環境設定 社員登録
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.connection.ConnectionProvider;
import kihonkankyousettei.model.Gogaku;

/**
 * GogakuNouryokuDao는 어학 능력 관련 데이터베이스 접근을 담당하는 DAO 클래스입니다.
 * GogakuNouryokuDaoは語学能力関連のデータベースアクセスを担当するDAOクラスです。
 */
public class GogakuNouryokuDao {

	private static GogakuNouryokuDao instance = new GogakuNouryokuDao();

	/**
	 * GogakuNouryokuDao의 인스턴스를 반환합니다.
	 * GogakuNouryokuDaoのインスタンスを返します。
	 *
	 * @return GogakuNouryokuDao 인스턴스 / GogakuNouryokuDaoインスタンス
	 */
	public static GogakuNouryokuDao getInstance() {
		return instance;
	}

	/**
	 * 어학 능력을 삽입하는 메서드입니다.
	 * 語学能力を挿入するメソッドです。
	 *
	 * @param conn  데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param gogaku 삽입할 어학 능력 객체 / 挿入する語学能力オブジェクト
	 * @return 영향을 받은 행의 수 / 影響を受けた行数
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int insertGogakuNouryoku(Connection conn, Gogaku gogaku) throws SQLException {
		String sql = "INSERT INTO GogakuNouryoku (gogaku_id, shain_id, gengo, shiken_mei, tensuu, shutoku_bi, dokkaiNouryoku, sakubunNouryoku, kaiwaNouryoku) "
				+ "VALUES (GogakuNouryoku_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, gogaku.getShain_id());
			ps.setString(2, gogaku.getGengo());
			ps.setString(3, gogaku.getShiken_mei());
			ps.setInt(4, gogaku.getTensuu());
			ps.setDate(5, new java.sql.Date(gogaku.getShutoku_bi().getTime()));
			ps.setString(6, gogaku.getDokkaiNouryoku());
			ps.setString(7, gogaku.getSakubunNouryoku());
			ps.setString(8, gogaku.getKaiwaNouryoku());
			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("어학 능력 추가 실패: no rows affected。/ 語学能力の追加に失敗しました：影響を受けた行がありません。");
			}
			return affectedRows;
		}
	}

	/**
	 * 어학 능력을 업데이트하는 메서드입니다.
	 * 語学能力を更新するメソッドです。
	 *
	 * @param conn     데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param gogaku   업데이트할 어학 능력 객체 / 更新する語学能力オブジェクト
	 * @param gogakuId 업데이트할 어학 능력의 ID / 更新する語学能力のID
	 * @return 업데이트된 행의 수를 반환 / 更新された行数を返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int updateGogakuNouryoku(Connection conn, Gogaku gogaku, int gogakuId) throws SQLException {
		String sql = "UPDATE GogakuNouryoku SET shain_id = ?, gengo = ?, shiken_mei = ?, tensuu = ?, shutoku_bi = ?, "
				+ "dokkaiNouryoku = ?, sakubunNouryoku = ?, kaiwaNouryoku = ? WHERE gogaku_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, gogaku.getShain_id());
			ps.setString(2, gogaku.getGengo());
			ps.setString(3, gogaku.getShiken_mei());
			ps.setInt(4, gogaku.getTensuu());
			ps.setDate(5, new java.sql.Date(gogaku.getShutoku_bi().getTime()));
			ps.setString(6, gogaku.getDokkaiNouryoku());
			ps.setString(7, gogaku.getSakubunNouryoku());
			ps.setString(8, gogaku.getKaiwaNouryoku());
			ps.setInt(9, gogakuId);
			return ps.executeUpdate();
		}
	}

	/**
	 * 특정 ID로 어학 능력을 조회하는 메서드입니다.
	 * 特定のIDで語学能力を照会するメソッドです。
	 *
	 * @param conn     데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param gogakuId 조회할 어학 능력의 ID / 照会する語学能力のID
	 * @return 조회된 어학 능력 객체, 존재하지 않으면 null 반환 / 照会された語学能力オブジェクト、存在しない場合はnullを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public Gogaku getGogakuNouryokuById(Connection conn, int gogakuId) throws SQLException {
		String sql = "SELECT gn.*, s.namae_kana FROM GogakuNouryoku gn "
				+ "JOIN Shain s ON gn.shain_id = s.shain_id WHERE gn.gogaku_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, gogakuId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Gogaku(rs.getInt("gogaku_id"), rs.getInt("shain_id"), rs.getString("gengo"),
						rs.getString("shiken_mei"), rs.getInt("tensuu"), rs.getDate("shutoku_bi"),
						rs.getString("dokkaiNouryoku"), rs.getString("sakubunNouryoku"), rs.getString("kaiwaNouryoku"));
			}
		}
		return null;
	}

	/**
	 * 모든 어학 능력을 조회하는 메서드입니다.
	 * すべての語学能力を照会するメソッドです。
	 *
	 * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @return 조회된 어학 능력 목록을 반환 / 照会された語学能力リストを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public ArrayList<Gogaku> selectAllGogakuNouryoku(Connection conn) throws SQLException {
		String sql = "SELECT gn.*, s.namae_kana FROM GogakuNouryoku gn JOIN Shain s ON gn.shain_id = s.shain_id";
		ArrayList<Gogaku> gogakuList = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Gogaku gogaku = new Gogaku(rs.getInt("gogaku_id"), rs.getInt("shain_id"), rs.getString("gengo"),
						rs.getString("shiken_mei"), rs.getInt("tensuu"), rs.getDate("shutoku_bi"),
						rs.getString("dokkaiNouryoku"), rs.getString("sakubunNouryoku"), rs.getString("kaiwaNouryoku"));
				gogakuList.add(gogaku);
			}
		}
		return gogakuList;
	}
}
