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

	private static ShainKyuuyoKeisanKirokuDao shainKyuuyoKeisanKirokuDao = new ShainKyuuyoKeisanKirokuDao();

	public static ShainKyuuyoKeisanKirokuDao getInstance() {
		return shainKyuuyoKeisanKirokuDao;
	}

	// 임세규 林世圭 급여입력・관리 페이지 / 給与入力・管理ページ
	// 급여입력/관리 페이지에서 설정한 "귀속연월, 급여차수"에 해당하는 각 사원별 급여계산기록 리스트를 반환한다.
	// 給与入力・管理ページで設定した”帰属年月、給与次数”と同じデータを持っている社員別計算記録レコード達をArrayList状で返す。
	public ArrayList<ShainKyuuyoKeisanKiroku> getKyuuyoKeisanList(Connection conn, String kyuuyo_gatsu, String kyuuyo_jisuu, String koukinzei) {
		ArrayList<ShainKyuuyoKeisanKiroku> sList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
		if(koukinzei.equals("勤労所得者")||koukinzei.equals(""))
		{
			query = "SELECT s.shain_id, "
					+ "       s.kubun, "
					+ "       s.namae_kana, "
					+ "       b.busho_mei, "
					+ "       sk.shikyuuSougaku, "
					+ "       sk.koujoSougaku, "
					+ "       sk.jissai_kyuuyo, "
					+ "       sk.KYUUYOSANTEIKAISHI, "
					+ "       sk.KYUUYOSANTEISHUURYOU, "
					+ "       sk.KYUUYO_SHIKYUUBI ,"
					+ "       z.koukinzei_kubun "
					+ "FROM Shain s "
					+ "JOIN ShainKyuuyoKeisanKiroku sk ON s.shain_id = sk.shain_id "
					+ "JOIN busho b ON s.busho_id = b.busho_id "
					+ "JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id "
					+ "JOIN koukinzei z ON s.shain_id = z.shain_id "
					+ "WHERE sk.kyuuyo_gatsu >= TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'MM') "
					+ "  AND sk.kyuuyo_gatsu <= LAST_DAY(TO_DATE(?, 'YYYY-MM-DD')) "
					+ "  AND sk.kyuuyo_jisuu = ? "
					+ "  AND z.koukinzei_kubun = '勤労所得者'";
		} else if(koukinzei.equals("不勤労所得者")) {
			query = "SELECT s.shain_id, "
					+ "       s.kubun, "
					+ "       s.namae_kana, "
					+ "       b.busho_mei, "
					+ "       sk.shikyuuSougaku, "
					+ "       sk.koujoSougaku, "
					+ "       sk.jissai_kyuuyo, "
					+ "       sk.KYUUYOSANTEIKAISHI, "
					+ "       sk.KYUUYOSANTEISHUURYOU, "
					+ "       sk.KYUUYO_SHIKYUUBI ,"
					+ "       z.koukinzei_kubun "
					+ "FROM Shain s "
					+ "JOIN ShainKyuuyoKeisanKiroku sk ON s.shain_id = sk.shain_id "
					+ "JOIN busho b ON s.busho_id = b.busho_id "
					+ "JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id "
					+ "JOIN koukinzei z ON s.shain_id = z.shain_id "
					+ "WHERE sk.kyuuyo_gatsu >= TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'MM') "
					+ "  AND sk.kyuuyo_gatsu <= LAST_DAY(TO_DATE(?, 'YYYY-MM-DD')) "
					+ "  AND sk.kyuuyo_jisuu = ? "
					+ "  AND z.koukinzei_kubun != '勤労所得者' AND z.koukinzei_kubun != '日雇い'";
		} else if(koukinzei.equals("日雇い"))
			query = "SELECT s.shain_id, "
					+ "       s.kubun, "
					+ "       s.namae_kana, "
					+ "       b.busho_mei, "
					+ "       sk.shikyuuSougaku, "
					+ "       sk.koujoSougaku, "
					+ "       sk.jissai_kyuuyo, "
					+ "       sk.KYUUYOSANTEIKAISHI, "
					+ "       sk.KYUUYOSANTEISHUURYOU, "
					+ "       sk.KYUUYO_SHIKYUUBI ,"
					+ "       z.koukinzei_kubun "
					+ "FROM Shain s "
					+ "JOIN ShainKyuuyoKeisanKiroku sk ON s.shain_id = sk.shain_id "
					+ "JOIN busho b ON s.busho_id = b.busho_id "
					+ "JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id "
					+ "JOIN koukinzei z ON s.shain_id = z.shain_id "
					+ "WHERE sk.kyuuyo_gatsu >= TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'MM') "
					+ "  AND sk.kyuuyo_gatsu <= LAST_DAY(TO_DATE(?, 'YYYY-MM-DD')) "
					+ "  AND sk.kyuuyo_jisuu = ? "
					+ "  AND z.koukinzei_kubun = '日雇い'";
		try {
			ps = conn.prepareStatement(query);

			ps.setString(1, kyuuyo_gatsu);
			ps.setString(2, kyuuyo_gatsu);
			ps.setString(3, kyuuyo_jisuu);

			rs = ps.executeQuery();
			while (rs.next()) {
				sList.add(new ShainKyuuyoKeisanKiroku(rs.getInt("shain_id"), rs.getString("kubun"),
						rs.getString("namae_kana"), rs.getString("busho_mei"), rs.getInt("shikyuuSougaku"),
						rs.getInt("koujoSougaku"), rs.getInt("jissai_kyuuyo"), rs.getString("KYUUYOSANTEIKAISHI"), rs.getString("KYUUYOSANTEISHUURYOU"), rs.getString("KYUUYO_SHIKYUUBI"), rs.getString("koukinzei_kubun")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return sList;
	}
	
	
	
	public int updateSanteiShikyuuBi(Connection conn, String santei_kaishi, String santei_shuuryou, String shikyuu_bi, String EXsantei_kaishi, String EXsantei_shuuryou, String EXshikyuu_bi, String koukinzei_kubun) {
		System.out.println("업데이트 DAO 접속");
		System.out.println("업데이트 할 값 : ");
		System.out.println(santei_kaishi);
		System.out.println(santei_shuuryou);
		System.out.println(shikyuu_bi);
		System.out.println(EXsantei_kaishi);
		System.out.println(EXsantei_shuuryou);
		System.out.println(EXshikyuu_bi);
		
		PreparedStatement ps = null;
		int rValue = 0;
		String query = "UPDATE ShainKyuuyoKeisanKiroku "
				+ "SET "
				+ "KYUUYOSANTEIKAISHI = TO_DATE(?, 'YYYY-MM-DD'), "
				+ "KYUUYOSANTEISHUURYOU = TO_DATE(?, 'YYYY-MM-DD'), "
				+ "KYUUYO_SHIKYUUBI = TO_DATE(?, 'YYYY-MM-DD'), "
				+ "KYUUYO_GATSU = TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'MM') "
				+ "WHERE (KYUUYOSANTEIKAISHI = TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND KYUUYOSANTEISHUURYOU = TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND KYUUYO_SHIKYUUBI = TO_DATE(?, 'YYYY-MM-DD')) "
				+ "AND koukinzei = ?";
		try {
			ps = conn.prepareStatement(query);

			ps.setString(1, santei_kaishi);
			ps.setString(2, santei_shuuryou);
			ps.setString(3, shikyuu_bi);
			ps.setString(4, santei_kaishi);
			ps.setString(5, EXsantei_kaishi);
			ps.setString(6, EXsantei_shuuryou);
			ps.setString(7, EXshikyuu_bi);
			ps.setString(8, koukinzei_kubun);
			
			rValue = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
		return rValue;
	}
}