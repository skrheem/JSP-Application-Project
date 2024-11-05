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
import kihonkankyousettei.model.FuyouKazoku;

/**
 * FuyouKazokuDao는 가족 구성원 관련 데이터베이스 접근을 담당하는 DAO 클래스입니다.
 * FuyouKazokuDaoは扶養家族関連のデータベースアクセスを担当するDAOクラスです。
 */
public class FuyouKazokuDao {

	private static FuyouKazokuDao instance = new FuyouKazokuDao();

	/**
	 * FuyouKazokuDao의 인스턴스를 반환합니다.
	 * FuyouKazokuDaoのインスタンスを返します。
	 *
	 * @return FuyouKazokuDao 인스턴스 / FuyouKazokuDaoインスタンス
	 */
	public static FuyouKazokuDao getInstance() {
		return instance;
	}

	/**
	 * 가족 구성원을 삽입하는 메서드입니다.
	 * 家族構成員を挿入するメソッドです。
	 *
	 * @param conn       데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param fuyouKazoku 삽입할 가족 구성원 객체 / 挿入する家族構成員オブジェクト
	 * @return 영향을 받은 행의 수 / 影響を受けた行数
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int insertFuyouKazoku(Connection conn, FuyouKazoku fuyouKazoku) throws SQLException {
	    String sql = "INSERT INTO FuyouKazoku (kazoku_id, shain_id, kankei, namae, kubun, jumin_bangou, shougaiUmu, injinKoseiUmu, "
	                 + "kenkouHokenUmu, doukyoUmu, gakkunZeiUmu, hatachiUmu) "
	                 + "VALUES (fuyouKazoku_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	        ps.setInt(1, fuyouKazoku.getShain_id());
	        ps.setString(2, fuyouKazoku.getKankei());
	        ps.setString(3, fuyouKazoku.getNamae());
	        ps.setString(4, fuyouKazoku.getKubun());
	        ps.setString(5, fuyouKazoku.getJumin_bangou());
	        ps.setString(6, String.valueOf(fuyouKazoku.getShougaiUmu()));
	        ps.setString(7, String.valueOf(fuyouKazoku.getInjinKoseiUmu()));
	        ps.setString(8, String.valueOf(fuyouKazoku.getKenkouHokenUmu()));
	        ps.setString(9, String.valueOf(fuyouKazoku.getDoukyoUmu()));
	        ps.setString(10, String.valueOf(fuyouKazoku.getGakkunZeiUmu()));
	        ps.setString(11, String.valueOf(fuyouKazoku.getHatachiUmu()));
	        int affectedRows = ps.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("가족 추가 실패: no rows affected。/ 家族の追加に失敗しました：影響を受けた行がありません。");
	        }
	        return affectedRows;
	    }
	}

	/**
	 * 가족 구성원을 업데이트하는 메서드입니다.
	 * 家族構成員を更新するメソッドです。
	 *
	 * @param conn       데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param fuyouKazoku 업데이트할 가족 구성원 객체 / 更新する家族構成員オブジェクト
	 * @return 업데이트된 행의 수를 반환 / 更新された行数を返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int updateFuyouKazoku(Connection conn, FuyouKazoku fuyouKazoku) throws SQLException {
	    String sql = "UPDATE FuyouKazoku SET shain_id = ?, kankei = ?, namae = ?, kubun = ?, jumin_bangou = ?, "
	               + "shougaiUmu = ?, injinKoseiUmu = ?, kenkouHokenUmu = ?, doukyoUmu = ?, gakkunZeiUmu = ?, hatachiUmu = ? "
	               + "WHERE kazoku_id = ?";
	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, fuyouKazoku.getShain_id());
	        ps.setString(2, fuyouKazoku.getKankei());
	        ps.setString(3, fuyouKazoku.getNamae());
	        ps.setString(4, fuyouKazoku.getKubun());
	        ps.setString(5, fuyouKazoku.getJumin_bangou());
	        ps.setString(6, String.valueOf(fuyouKazoku.getShougaiUmu()));
	        ps.setString(7, String.valueOf(fuyouKazoku.getInjinKoseiUmu()));
	        ps.setString(8, String.valueOf(fuyouKazoku.getKenkouHokenUmu()));
	        ps.setString(9, String.valueOf(fuyouKazoku.getDoukyoUmu()));
	        ps.setString(10, String.valueOf(fuyouKazoku.getGakkunZeiUmu()));
	        ps.setString(11, String.valueOf(fuyouKazoku.getHatachiUmu()));
	        ps.setInt(12, fuyouKazoku.getKazoku_id());
	        return ps.executeUpdate();
	    }
	}

	/**
	 * 특정 ID로 가족 구성원을 조회하는 메서드입니다.
	 * 特定のIDで家族構成員を照会するメソッドです。
	 *
	 * @param conn      데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param kazoku_id 조회할 가족 구성원의 ID / 照会する家族構成員のID
	 * @return 조회된 가족 구성원 객체, 존재하지 않으면 null 반환 / 照会された家族構成員オブジェクト、存在しない場合はnullを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public FuyouKazoku getFuyouKazokuById(Connection conn, int kazoku_id) throws SQLException {
		String query = "SELECT fk.*, s.namae_kana FROM FuyouKazoku fk "
				+ "JOIN Shain s ON fk.shain_id = s.shain_id WHERE fk.kazoku_id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, kazoku_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				FuyouKazoku fuyouKazoku = new FuyouKazoku();
				fuyouKazoku.setKazoku_id(rs.getInt("kazoku_id"));
				fuyouKazoku.setShain_id(rs.getInt("shain_id"));
				fuyouKazoku.setKankei(rs.getString("kankei"));
				fuyouKazoku.setNamae(rs.getString("namae"));
				fuyouKazoku.setKubun(rs.getString("kubun"));
				fuyouKazoku.setJumin_bangou(rs.getString("jumin_bangou"));
				fuyouKazoku.setShougaiUmu(rs.getString("shougaiUmu").charAt(0));
				fuyouKazoku.setInjinKoseiUmu(rs.getString("injinKoseiUmu").charAt(0));
				fuyouKazoku.setKenkouHokenUmu(rs.getString("kenkouHokenUmu").charAt(0));
				fuyouKazoku.setDoukyoUmu(rs.getString("doukyoUmu").charAt(0));
				fuyouKazoku.setGakkunZeiUmu(rs.getString("gakkunZeiUmu").charAt(0));
				fuyouKazoku.setHatachiUmu(rs.getString("hatachiUmu").charAt(0));
				return fuyouKazoku;
			}
		}
		return null;
	}

	/**
	 * 모든 가족 구성원을 조회하는 메서드입니다.
	 * すべての家族構成員を照会するメソッドです。
	 *
	 * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @return 조회된 가족 구성원 목록을 반환 / 照会された家族構成員リストを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public ArrayList<FuyouKazoku> selectAllFuyouKazoku(Connection conn) throws SQLException {
		String query = "SELECT fk.*, s.namae_kana FROM FuyouKazoku fk JOIN Shain s ON fk.shain_id = s.shain_id";
		ArrayList<FuyouKazoku> fuyouKazokuList = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				FuyouKazoku fuyouKazoku = new FuyouKazoku();
				fuyouKazoku.setKazoku_id(rs.getInt("kazoku_id"));
				fuyouKazoku.setShain_id(rs.getInt("shain_id"));
				fuyouKazoku.setKankei(rs.getString("kankei"));
				fuyouKazoku.setNamae(rs.getString("namae"));
				fuyouKazoku.setKubun(rs.getString("kubun"));
				fuyouKazoku.setJumin_bangou(rs.getString("jumin_bangou"));
				fuyouKazoku.setShougaiUmu(rs.getString("shougaiUmu").charAt(0));
				fuyouKazoku.setInjinKoseiUmu(rs.getString("injinKoseiUmu").charAt(0));
				fuyouKazoku.setKenkouHokenUmu(rs.getString("kenkouHokenUmu").charAt(0));
				fuyouKazoku.setDoukyoUmu(rs.getString("doukyoUmu").charAt(0));
				fuyouKazoku.setGakkunZeiUmu(rs.getString("gakkunZeiUmu").charAt(0));
				fuyouKazoku.setHatachiUmu(rs.getString("hatachiUmu").charAt(0));
				fuyouKazokuList.add(fuyouKazoku);
			}
		}
		return fuyouKazokuList;
	}
}
