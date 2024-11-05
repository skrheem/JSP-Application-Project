package keisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.JdbcUtil;

//임세규 林世圭
//소득세 계산을 위해 사원의 부양가족 수를 반환하는 클래스
//所得税の計算のために社員の扶養家族数を返すクラス
public class FuyouKazokuDao {
	private static FuyouKazokuDao instance = new FuyouKazokuDao();
	
	public static FuyouKazokuDao getInstance() {
		return instance;
	}
	
	public int countHatachiKazoku(Connection conn, Integer shain_id) {
		int hatachiCount = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select COUNT(*) from fuyoukazoku "
				+ "where shain_id = ? AND HATACHIUMU = 'Y'";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, shain_id);
			rs = ps.executeQuery();
			if(rs.next())
				hatachiCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return hatachiCount;
	}
}
