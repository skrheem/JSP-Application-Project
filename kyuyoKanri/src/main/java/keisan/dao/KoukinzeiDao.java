package keisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.JdbcUtil;

// 임세규 林世圭
// 사원의 갑근세 타입을 가져오는 클래스
// 社員の甲欽税タイプを取得するクラス
public class KoukinzeiDao {
	private static KoukinzeiDao instance = new KoukinzeiDao();

	public static KoukinzeiDao getInstance() {
		return instance;
	}
	
	// 갑근세 테이블에 접근하여 사원의 갑근세 타입을 반환하는 메서드
	// 甲欽税テーブルにアクセスして社員の甲欽税タイプを返すメソッド
	public String getKoukinzei(Connection conn, Integer shain_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String koukinzei = "";
		String query = "select koukinzei_kubun from koukinzei where shain_id = ?";
		try {
			ps = conn.prepareStatement(query);

			ps.setInt(1, shain_id);

			rs = ps.executeQuery();
			if (rs.next()) {
				koukinzei = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return koukinzei;
	}
}
