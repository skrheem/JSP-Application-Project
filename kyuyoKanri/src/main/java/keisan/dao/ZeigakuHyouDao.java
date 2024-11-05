package keisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.JdbcUtil;

// 임세규 林世圭
// 사원의 소득세를 계산하여 반환하는 클래스
// 社員の所得税を計算して返すクラス
public class ZeigakuHyouDao {

	private static ZeigakuHyouDao instance = new ZeigakuHyouDao();

	public static ZeigakuHyouDao getInstance() {
		return instance;
	}

	// 사원의 기본급과 그 사원의 부양가족 수에 따라 소득세를 계산하여 반환하는 메서드
	// 社員の基本給とその社員の扶養家族数に基づいて所得税を計算して返すメソッド
	public int keisanZeigaku(Connection conn, Integer shain_id, int kihonkyuu, int countHatachi) {
		int zeigaku = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT shotoku_zei " + "FROM zeiGakuHyou "
				+ "WHERE ijyou <= ((SELECT kihonkyuu FROM SHAINKIHONKYUU WHERE shain_id = ?) / 1000) "
				+ "AND miman >= ((SELECT kihonkyuu FROM SHAINKIHONKYUU WHERE shain_id = ?) / 1000) "
				+ "AND kazokusuu = ( " + "CASE "
				+ "WHEN (SELECT count(*) FROM fuyoukazoku WHERE shain_id = ? AND DOUKYOUMU = 'Y') = 0 " + "THEN 1 "
				+ "ELSE (SELECT count(*) FROM fuyoukazoku WHERE shain_id = ? AND DOUKYOUMU = 'Y') " + "END)";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, shain_id);
			ps.setInt(2, shain_id);
			ps.setInt(3, shain_id);
			ps.setInt(4, shain_id);
			rs = ps.executeQuery();
			if (rs.next())
				zeigaku = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}

		// 부양가족 수에 따른 소득세 조정
		// 扶養家族数に応じた所得税の調整
		if (countHatachi == 1)
			return Math.max(0, zeigaku - 12500);
		else if (countHatachi == 2)
			return Math.max(0, zeigaku - 29160);
		else if (countHatachi >= 3)
			return Math.max(0, zeigaku - (29160 + (25000 * (countHatachi - 2))));

		return zeigaku;
	}
}
