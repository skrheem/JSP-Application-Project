package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

// 임세규 林世圭 급여입력・관리 페이지 / 給与入力・管理ページ
// 사원 리스트에서 사원을 선택했을 때, 그 사원의 기본급을 가져온다.
// 社員リストから社員を選んだ際にその社員の基本給の情報を取得する。
public class ShainKihonkyuuDao {
	
	private static ShainKihonkyuuDao skDao = new ShainKihonkyuuDao();
	
	public static ShainKihonkyuuDao getInstance() {
		return skDao;
	}
	
	public int getKihonkyuu(Connection conn, Integer shain_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int kihonkyuu = 0;
		String query = "select kihonkyuu from shainkihonkyuu where shain_id = ?";
		try {
			ps = conn.prepareStatement(query);

			ps.setInt(1, shain_id);

			rs = ps.executeQuery();
			if(rs.next()) {
				kihonkyuu = rs.getInt(1);
				return kihonkyuu;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return kihonkyuu;
	}
}
