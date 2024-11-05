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
import jdbc.connection.ConnectionProvider;
import kihonkankyousettei.model.Hatsurei;

/**
 * HatsureiDao는 발령 정보 관련 데이터베이스 접근을 담당하는 DAO 클래스입니다.
 * HatsureiDaoは発令情報関連のデータベースアクセスを担当するDAOクラスです。
 */
public class HatsureiDao {

	private static HatsureiDao instance = new HatsureiDao();

	/**
	 * HatsureiDao의 인스턴스를 반환합니다.
	 * HatsureiDaoのインスタンスを返します。
	 *
	 * @return HatsureiDao 인스턴스 / HatsureiDaoインスタンス
	 */
	public static HatsureiDao getInstance() {
		return instance;
	}

	/**
	 * 발령 정보를 삽입하는 메서드입니다.
	 * 発令情報を挿入するメソッドです。
	 *
	 * @param conn     데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param hatsurei 삽입할 발령 정보 객체 / 挿入する発令情報オブジェクト
	 * @return 영향을 받은 행의 수 / 影響を受けた行数
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int insertHatsurei(Connection conn, Hatsurei hatsurei) throws SQLException {
	    String sql = "INSERT INTO Hatsurei (hatsurei_id, shain_id, hatsureiKubun, hatsureiNichi, busho, yakushoku, shokuseki, bikou) "
	            + "VALUES (Hatsurei_sequence.nextval, ?, ?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	        ps.setInt(1, hatsurei.getShain_id());
	        ps.setString(2, hatsurei.getHatsureiKubun());
	        ps.setDate(3, new java.sql.Date(hatsurei.getHatsureiNichi().getTime()));
	        ps.setString(4, hatsurei.getBusho());
	        ps.setString(5, hatsurei.getYakushoku());
	        ps.setString(6, hatsurei.getShokuseki());
	        ps.setString(7, hatsurei.getBikou());
	        int affectedRows = ps.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("발령 추가 실패: no rows affected。/ 発令追加に失敗しました：影響を受けた行がありません。");
	        }
	        return affectedRows;
	    }
	}

	/**
	 * 발령 정보를 업데이트하는 메서드입니다.
	 * 発令情報を更新するメソッドです。
	 *
	 * @param conn       데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param hatsurei   업데이트할 발령 정보 객체 / 更新する発令情報オブジェクト
	 * @param hatsureiId 업데이트할 발령 정보의 ID / 更新する発令情報のID
	 * @return 업데이트된 행의 수를 반환 / 更新された行数を返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int updateHatsurei(Connection conn, Hatsurei hatsurei, int hatsureiId) throws SQLException {
		String sql = "UPDATE Hatsurei SET shain_id = ?, hatsureiKubun = ?, hatsureiNichi = ?, busho = ?, yakushoku = ?, "
				+ "shokuseki = ?, bikou = ? WHERE hatsurei_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, hatsurei.getShain_id());
			ps.setString(2, hatsurei.getHatsureiKubun());
			ps.setDate(3, new java.sql.Date(hatsurei.getHatsureiNichi().getTime()));
			ps.setString(4, hatsurei.getBusho());
			ps.setString(5, hatsurei.getYakushoku());
			ps.setString(6, hatsurei.getShokuseki());
			ps.setString(7, hatsurei.getBikou());
			ps.setInt(8, hatsureiId);
			return ps.executeUpdate();
		}
	}

	/**
	 * 특정 ID로 발령 정보를 조회하는 메서드입니다.
	 * 特定のIDで発令情報を照会するメソッドです。
	 *
	 * @param conn       데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param hatsureiId 조회할 발령 정보의 ID / 照会する発令情報のID
	 * @return 조회된 발령 정보 객체, 존재하지 않으면 null 반환 / 照会された発令情報オブジェクト、存在しない場合はnullを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public Hatsurei getHatsureiById(Connection conn, int hatsureiId) throws SQLException {
		String sql = "SELECT h.*, s.namae_kana FROM Hatsurei h "
				+ "JOIN Shain s ON h.shain_id = s.shain_id WHERE h.hatsurei_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, hatsureiId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Hatsurei(rs.getInt("hatsurei_id"), rs.getInt("shain_id"), rs.getString("hatsureiKubun"),
						rs.getDate("hatsureiNichi"), rs.getString("busho"), rs.getString("yakushoku"),
						rs.getString("shokuseki"), rs.getString("bikou"));
			}
		}
		return null;
	}

	/**
	 * 모든 발령 정보를 조회하는 메서드입니다.
	 * すべての発令情報を照会するメソッドです。
	 *
	 * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @return 조회된 발령 정보 목록을 반환 / 照会された発令情報リストを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public List<Hatsurei> selectAllHatsurei(Connection conn) throws SQLException {
		String sql = "SELECT h.*, s.namae_kana FROM Hatsurei h JOIN Shain s ON h.shain_id = s.shain_id";
		List<Hatsurei> hatsureiList = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Hatsurei hatsurei = new Hatsurei(rs.getInt("hatsurei_id"), rs.getInt("shain_id"),
						rs.getString("hatsureiKubun"), rs.getDate("hatsureiNichi"), rs.getString("busho"),
						rs.getString("yakushoku"), rs.getString("shokuseki"), rs.getString("bikou"));
				hatsureiList.add(hatsurei);
			}
		}
		return hatsureiList;
	}
}
