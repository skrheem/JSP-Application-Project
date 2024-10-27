package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import model.Shain;
import model.ShainKyuuyoKeisanKiroku;
import util.ObjectFormatter;

public class ShainKyuuyoKeisanKirokuDao {
	public static void main(String[] args) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			ShainKyuuyoKeisanKirokuDao skd = ShainKyuuyoKeisanKirokuDao.getInstance();

			ArrayList<ShainKyuuyoKeisanKiroku> ShainList = skd.getKyuuyoKeisanList(conn, "2024-01-01", "2024-12-31",
					"1");

			try {

				System.out.println(ObjectFormatter.formatList(ShainList));

			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static ShainKyuuyoKeisanKirokuDao shainKyuuyoKeisanKirokuDao = new ShainKyuuyoKeisanKirokuDao();

	public static ShainKyuuyoKeisanKirokuDao getInstance() {
		return shainKyuuyoKeisanKirokuDao;
	}

	// 임세규 林世圭 급여입력・관리 페이지 / 給与入力・管理ページ
	// 급여입력/관리 페이지에서 설정한 "귀속연월, 급여차수"에 해당하는 각 사원별 급여계산기록 리스트를 반환한다.
	// 給与入力・管理ページで設定した”帰属年月、給与次数”と同じデータを持っている社員別計算記録レコード達をArrayList状で返す。
	public ArrayList<ShainKyuuyoKeisanKiroku> getKyuuyoKeisanList(Connection conn, String kyuuyo_gatsu_kaishi,
			String kyuuyo_gatsu_shuuryou, String kyuuyo_jisuu) {
		ArrayList<ShainKyuuyoKeisanKiroku> sList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT s.shain_id, s.kubun, s.namae_kana, b.busho_mei, sk.shikyuuSougaku, sk.koujoSougaku, sk.jissai_kyuuyo "
				+ "FROM Shain s " + "JOIN ShainKyuuyoKeisanKiroku sk ON s.shain_id = sk.shain_id "
				+ "JOIN busho b ON s.busho_id = b.busho_id " + "JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id "
				+ "WHERE (sk.kyuuyo_gatsu >= TO_DATE(?, 'YYYY-MM-DD') AND sk.kyuuyo_gatsu <= TO_DATE(?, 'YYYY-MM-DD')) "
				+ "AND sk.kyuuyo_jisuu = ? AND s.kubun != \'日雇い\'";
		try {
			ps = conn.prepareStatement(query);

			ps.setString(1, kyuuyo_gatsu_kaishi);
			ps.setString(2, kyuuyo_gatsu_shuuryou);
			ps.setString(3, kyuuyo_jisuu);

			rs = ps.executeQuery();
			while (rs.next()) {
				sList.add(new ShainKyuuyoKeisanKiroku(rs.getInt("shain_id"), rs.getString("kubun"),
						rs.getString("namae_kana"), rs.getString("busho_mei"), rs.getInt("shikyuuSougaku"),
						rs.getInt("koujoSougaku"), rs.getInt("jissai_kyuuyo")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return sList;
	}
}