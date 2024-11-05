package keisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.JdbcUtil;
import keisan.model.ShainKyuuyoKeisanKiroku;

//임세규 林世圭
//사원의 급여항목 기록을 삽입하는 클래스
public class ShainKyuuyoKeisanKirokuDao {
	
	private static ShainKyuuyoKeisanKirokuDao instance = new ShainKyuuyoKeisanKirokuDao();

	public static ShainKyuuyoKeisanKirokuDao getInstance() {
		return instance;
	}

	// 급여입력/관리 페이지에서 설정한 "귀속연월, 급여차수"에 해당하는 각 사원별 급여계산기록 리스트를 반환한다.
	// 給与入力・管理ページで設定した”帰属年月、給与次数”と同じデータを持っている社員別計算記録レコード達をArrayList状で返す。
	public ArrayList<ShainKyuuyoKeisanKiroku> getKyuuyoKeisanListByDateAndType(Connection conn, String kyuuyo_gatsu,
			String kyuuyo_jisuu, int koukinzeiType) {
		ArrayList<ShainKyuuyoKeisanKiroku> sList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
		if (koukinzeiType == 0) {
			query = "SELECT s.shain_id, " + "       s.kubun, " + "       s.namae_kana, " + "       b.busho_mei, "
					+ "       sk.shikyuuSougaku, " + "       sk.koujoSougaku, " + "       sk.jissai_kyuuyo, "
					+ "       TO_CHAR(sk.KYUUYOSANTEIKAISHI, 'YYYY-MM-DD') AS KYUUYOSANTEIKAISHI, "
					+ "       TO_CHAR(sk.KYUUYOSANTEISHUURYOU, 'YYYY-MM-DD') AS KYUUYOSANTEISHUURYOU, "
					+ "       TO_CHAR(sk.KYUUYO_SHIKYUUBI, 'YYYY-MM-DD') AS KYUUYO_SHIKYUUBI, "
					+ "       z.koukinzei_kubun , sk.KYUUYO_GATSU " 
					+ "FROM Shain s "
					+ "JOIN ShainKyuuyoKeisanKiroku sk ON s.shain_id = sk.shain_id "
					+ "JOIN busho b ON s.busho_id = b.busho_id "
					+ "JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id "
					+ "JOIN koukinzei z ON s.shain_id = z.shain_id "
					+ "WHERE sk.kyuuyo_gatsu = TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'MM')" + "  AND sk.kyuuyo_jisuu = ? "
					+ "  AND z.koukinzei_kubun = '勤労所得者'";
		} else if (koukinzeiType == 1) {
			query = "SELECT s.shain_id, " + "       s.kubun, " + "       s.namae_kana, " + "       b.busho_mei, "
					+ "       sk.shikyuuSougaku, " + "       sk.koujoSougaku, " + "       sk.jissai_kyuuyo, "
					+ "       TO_CHAR(sk.KYUUYOSANTEIKAISHI, 'YYYY-MM-DD') AS KYUUYOSANTEIKAISHI, "
					+ "       TO_CHAR(sk.KYUUYOSANTEISHUURYOU, 'YYYY-MM-DD') AS KYUUYOSANTEISHUURYOU, "
					+ "       TO_CHAR(sk.KYUUYO_SHIKYUUBI, 'YYYY-MM-DD') AS KYUUYO_SHIKYUUBI, "
					+ "       z.koukinzei_kubun , sk.KYUUYO_GATSU " 
					+ "FROM Shain s "
					+ "JOIN ShainKyuuyoKeisanKiroku sk ON s.shain_id = sk.shain_id "
					+ "JOIN busho b ON s.busho_id = b.busho_id "
					+ "JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id "
					+ "JOIN koukinzei z ON s.shain_id = z.shain_id "
					+ "WHERE sk.kyuuyo_gatsu = TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'MM')" + "  AND sk.kyuuyo_jisuu = ? "
					+ "  AND z.koukinzei_kubun != '勤労所得者' AND z.koukinzei_kubun != '日雇い'";
		} else if (koukinzeiType == 2)
			query = "SELECT s.shain_id, " + "       s.kubun, " + "       s.namae_kana, " + "       b.busho_mei, "
					+ "       sk.shikyuuSougaku, " + "       sk.koujoSougaku, " + "       sk.jissai_kyuuyo, "
					+ "       TO_CHAR(sk.KYUUYOSANTEIKAISHI, 'YYYY-MM-DD') AS KYUUYOSANTEIKAISHI, "
					+ "       TO_CHAR(sk.KYUUYOSANTEISHUURYOU, 'YYYY-MM-DD') AS KYUUYOSANTEISHUURYOU, "
					+ "       TO_CHAR(sk.KYUUYO_SHIKYUUBI, 'YYYY-MM-DD') AS KYUUYO_SHIKYUUBI, "
					+ "       z.koukinzei_kubun , sk.KYUUYO_GATSU " 
					+ "FROM Shain s "
					+ "JOIN ShainKyuuyoKeisanKiroku sk ON s.shain_id = sk.shain_id "
					+ "JOIN busho b ON s.busho_id = b.busho_id "
					+ "JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id "
					+ "JOIN koukinzei z ON s.shain_id = z.shain_id "
					+ "WHERE sk.kyuuyo_gatsu = TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'MM')" + "  AND sk.kyuuyo_jisuu = ? "
					+ "  AND z.koukinzei_kubun = '日雇い'";
		try {
			ps = conn.prepareStatement(query);

			ps.setString(1, kyuuyo_gatsu);
			ps.setString(2, kyuuyo_jisuu);

			rs = ps.executeQuery();
			while (rs.next()) {
				sList.add(new ShainKyuuyoKeisanKiroku(rs.getInt("shain_id"), rs.getString("kubun"),
						rs.getString("namae_kana"), rs.getString("busho_mei"), rs.getInt("shikyuuSougaku"),
						rs.getInt("koujoSougaku"), rs.getInt("jissai_kyuuyo"), rs.getString("KYUUYOSANTEIKAISHI"),
						rs.getString("KYUUYOSANTEISHUURYOU"), rs.getString("KYUUYO_SHIKYUUBI"),
						rs.getString("koukinzei_kubun"), rs.getDate("KYUUYO_GATSU")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return sList;
	}
	
	// 신규 추가 버튼을 눌렀을 때 갑근세 타입을 넘겨 갑근세 타입에 해당하는 사원리스트를 반환
	// 新規追加ボタンを押したとき、甲欽税タイプを渡して、該当する甲欽税タイプの社員リストを返す
	public ArrayList<ShainKyuuyoKeisanKiroku> getKyuuyoKeisanListByType(Connection conn, String kyuuyo_gatsu,
			String kyuuyo_jisuu, int koukinzeiType) {
		ArrayList<ShainKyuuyoKeisanKiroku> sList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
		if (koukinzeiType == 0) {
			query = "SELECT s.shain_id, " + "       s.kubun, " + "       s.namae_kana, " + "       b.busho_mei, "
					+ "       sk.shikyuuSougaku, " + "       sk.koujoSougaku, " + "       sk.jissai_kyuuyo, "
					+ "       TO_CHAR(sk.KYUUYOSANTEIKAISHI, 'YYYY-MM-DD') AS KYUUYOSANTEIKAISHI, "
					+ "       TO_CHAR(sk.KYUUYOSANTEISHUURYOU, 'YYYY-MM-DD') AS KYUUYOSANTEISHUURYOU, "
					+ "       TO_CHAR(sk.KYUUYO_SHIKYUUBI, 'YYYY-MM-DD') AS KYUUYO_SHIKYUUBI, "
					+ "       z.koukinzei_kubun " + "FROM Shain s "
					+ "JOIN ShainKyuuyoKeisanKiroku sk ON s.shain_id = sk.shain_id "
					+ "JOIN busho b ON s.busho_id = b.busho_id "
					+ "JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id "
					+ "JOIN koukinzei z ON s.shain_id = z.shain_id "
					+ "WHERE sk.kyuuyo_gatsu = TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'MM')" + "  AND sk.kyuuyo_jisuu = ? "
					+ "  AND z.koukinzei_kubun = '勤労所得者'";
		} else if (koukinzeiType == 1) {
			query = "SELECT s.shain_id, " + "       s.kubun, " + "       s.namae_kana, " + "       b.busho_mei, "
					+ "       sk.shikyuuSougaku, " + "       sk.koujoSougaku, " + "       sk.jissai_kyuuyo, "
					+ "       TO_CHAR(sk.KYUUYOSANTEIKAISHI, 'YYYY-MM-DD') AS KYUUYOSANTEIKAISHI, "
					+ "       TO_CHAR(sk.KYUUYOSANTEISHUURYOU, 'YYYY-MM-DD') AS KYUUYOSANTEISHUURYOU, "
					+ "       TO_CHAR(sk.KYUUYO_SHIKYUUBI, 'YYYY-MM-DD') AS KYUUYO_SHIKYUUBI, "
					+ "       z.koukinzei_kubun " + "FROM Shain s "
					+ "JOIN ShainKyuuyoKeisanKiroku sk ON s.shain_id = sk.shain_id "
					+ "JOIN busho b ON s.busho_id = b.busho_id "
					+ "JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id "
					+ "JOIN koukinzei z ON s.shain_id = z.shain_id "
					+ "WHERE sk.kyuuyo_gatsu = TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'MM')" + "  AND sk.kyuuyo_jisuu = ? "
					+ "  AND z.koukinzei_kubun != '勤労所得者' AND z.koukinzei_kubun != '日雇い'";
		} else if (koukinzeiType == 2)
			query = "SELECT s.shain_id, " + "       s.kubun, " + "       s.namae_kana, " + "       b.busho_mei, "
					+ "       sk.shikyuuSougaku, " + "       sk.koujoSougaku, " + "       sk.jissai_kyuuyo, "
					+ "       TO_CHAR(sk.KYUUYOSANTEIKAISHI, 'YYYY-MM-DD') AS KYUUYOSANTEIKAISHI, "
					+ "       TO_CHAR(sk.KYUUYOSANTEISHUURYOU, 'YYYY-MM-DD') AS KYUUYOSANTEISHUURYOU, "
					+ "       TO_CHAR(sk.KYUUYO_SHIKYUUBI, 'YYYY-MM-DD') AS KYUUYO_SHIKYUUBI, "
					+ "       z.koukinzei_kubun " + "FROM Shain s "
					+ "JOIN ShainKyuuyoKeisanKiroku sk ON s.shain_id = sk.shain_id "
					+ "JOIN busho b ON s.busho_id = b.busho_id "
					+ "JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id "
					+ "JOIN koukinzei z ON s.shain_id = z.shain_id "
					+ "WHERE sk.kyuuyo_gatsu = TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'MM')" + "  AND sk.kyuuyo_jisuu = ? "
					+ "  AND z.koukinzei_kubun = '日雇い'";
		try {
			ps = conn.prepareStatement(query);

			ps.setString(1, kyuuyo_gatsu);
			ps.setString(2, kyuuyo_jisuu);

			rs = ps.executeQuery();
			while (rs.next()) {
				sList.add(new ShainKyuuyoKeisanKiroku(rs.getInt("shain_id"), rs.getString("kubun"),
						rs.getString("namae_kana"), rs.getString("busho_mei"), rs.getInt("shikyuuSougaku"),
						rs.getInt("koujoSougaku"), rs.getInt("jissai_kyuuyo"), rs.getString("KYUUYOSANTEIKAISHI"),
						rs.getString("KYUUYOSANTEISHUURYOU"), rs.getString("KYUUYO_SHIKYUUBI"),
						rs.getString("koukinzei_kubun"), rs.getDate("KYUUYO_GATSU")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return sList;
	}
	// 급여산정일과 급여지급일을 수정하는 메서드
	// 給与算定日と給与支給日を修正するメソッド
	public int updateSanteiShikyuuBi(Connection conn, String santei_kaishi, String santei_shuuryou, String shikyuu_bi, String EXsantei_kaishi, String EXsantei_shuuryou, String EXshikyuu_bi, String koukinzei_kubun) {
		PreparedStatement ps = null;
		String query = "";
		int rValue = 0;
		if(koukinzei_kubun.equals("0")) {
			query = "UPDATE ShainKyuuyoKeisanKiroku "
					+ "SET "
					+ "KYUUYOSANTEIKAISHI = TO_DATE(?, 'YYYY-MM-DD'), "
					+ "KYUUYOSANTEISHUURYOU = TO_DATE(?, 'YYYY-MM-DD'), "
					+ "KYUUYO_SHIKYUUBI = TO_DATE(?, 'YYYY-MM-DD') "
					+ "WHERE (KYUUYOSANTEIKAISHI = TO_DATE(?, 'YYYY-MM-DD') "
					+ "AND KYUUYOSANTEISHUURYOU = TO_DATE(?, 'YYYY-MM-DD') "
					+ "AND KYUUYO_SHIKYUUBI = TO_DATE(?, 'YYYY-MM-DD')) "
					+ "AND koukinzei = '勤労所得者'";
	    } else if(koukinzei_kubun.equals("1")) {
	    	query = "UPDATE ShainKyuuyoKeisanKiroku "
					+ "SET "
					+ "KYUUYOSANTEIKAISHI = TO_DATE(?, 'YYYY-MM-DD'), "
					+ "KYUUYOSANTEISHUURYOU = TO_DATE(?, 'YYYY-MM-DD'), "
					+ "KYUUYO_SHIKYUUBI = TO_DATE(?, 'YYYY-MM-DD') "
					+ "WHERE (KYUUYOSANTEIKAISHI = TO_DATE(?, 'YYYY-MM-DD') "
					+ "AND KYUUYOSANTEISHUURYOU = TO_DATE(?, 'YYYY-MM-DD') "
					+ "AND KYUUYO_SHIKYUUBI = TO_DATE(?, 'YYYY-MM-DD')) "
					+ "AND koukinzei　!= '勤労所得者' AND koukinzei　!= '日雇い'";
	    } else if(koukinzei_kubun.equals("2")) {
	    	query = "UPDATE ShainKyuuyoKeisanKiroku "
					+ "SET "
					+ "KYUUYOSANTEIKAISHI = TO_DATE(?, 'YYYY-MM-DD'), "
					+ "KYUUYOSANTEISHUURYOU = TO_DATE(?, 'YYYY-MM-DD'), "
					+ "KYUUYO_SHIKYUUBI = TO_DATE(?, 'YYYY-MM-DD') "
					+ "WHERE (KYUUYOSANTEIKAISHI = TO_DATE(?, 'YYYY-MM-DD') "
					+ "AND KYUUYOSANTEISHUURYOU = TO_DATE(?, 'YYYY-MM-DD') "
					+ "AND KYUUYO_SHIKYUUBI = TO_DATE(?, 'YYYY-MM-DD')) "
					+ "AND koukinzei = '日雇い'";
	    }
		 
		try {
			ps = conn.prepareStatement(query);

			ps.setString(1, santei_kaishi);
			ps.setString(2, santei_shuuryou);
			ps.setString(3, shikyuu_bi);
			ps.setString(4, EXsantei_kaishi);
			ps.setString(5, EXsantei_shuuryou);
			ps.setString(6, EXshikyuu_bi);
			
			rValue = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
		return rValue;
	}
	
	// 사원의 급여계산기록을 삽입하는 메서드
	// 社員の給与計算記録を挿入するメソッド
	public int insertShainKeisanKiroku(Connection conn, String kyuuyoGatsu, String kyuuyoJisuu, String shikyuu_bi, Integer shain_id, String santeiKaishi, String santeiShuuryou, int kihonkyuu, String koukinzei) {
		PreparedStatement ps = null;
		int rValue = 0;
		String query = "insert into shainkyuuyokeisankiroku (shain_id, kyuuyo_gatsu, kyuuyo_jisuu, kyuuyo_shikyuubi, kyuuyosanteikaishi, kyuuyosanteishuuryou, kihonkyuu, koukinzei) "
				+ "VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), ?, ?)";
		try {
			ps = conn.prepareStatement(query);

			ps.setInt(1, shain_id);
			ps.setString(2, kyuuyoGatsu);
			ps.setString(3, kyuuyoJisuu);
			ps.setString(4, shikyuu_bi);
			ps.setString(5, santeiKaishi);
			ps.setString(6, santeiShuuryou);
			ps.setInt(7, kihonkyuu);
			ps.setString(8, koukinzei);
			
			rValue = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
		return rValue;
	}
	
	// 급여계산기록을 삽입하기 전, 중복되는 데이터의 유무를 반환하는 메서드
	// 給与計算記録を挿入する前に、重複するデータの有無を返すメソッド
	public boolean isEmptyShainKeisanKiroku(Connection conn, String kyuuyoGatsu, Integer shain_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select 'T' from shainkyuuyokeisankiroku where kyuuyo_gatsu = TO_DATE(?, 'YYYY-MM-DD') AND shain_id = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, kyuuyoGatsu);
			ps.setInt(2, shain_id);
			rs = ps.executeQuery();
			if (rs.next()) {
	            return false;  // 중복 있음 重複あり
	        } else {
	            return true;  // 중복 없음 重複なし
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
		
		return false;
	}
	
	// 사원의 급여계산기록을 삭제하는 메서드
	// 社員の給与計算記録を削除するメソッド
	public int deleteShainKyuuyoKeisanKiroku(Connection conn, Integer shain_id, String kyuuyoGatsu, String kyuuyoJisuu) {
		PreparedStatement ps = null;
		int deleteRecord = 0;
		String query = "delete from shainkyuuyokeisankiroku where SHAIN_ID = ? AND KYUUYO_GATSU = TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'MM') AND kyuuyo_jisuu = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, shain_id);
			ps.setString(2, kyuuyoGatsu);
			ps.setString(3, kyuuyoJisuu);
			
			deleteRecord = ps.executeUpdate();
            return deleteRecord;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
		return deleteRecord;
	}
	
	// 귀속연월, 급여차수에 해당하는 모든 급여계산기록을 삭제하는 메서드
	// 帰属年月、給与次数に該当するすべての給与計算記録を削除するメソッド
	public int deleteAllShainKyuuyoKeisanKiroku(Connection conn, String kyuuyoGatsu, String kyuuyoJisuu) {
		PreparedStatement ps = null;
		int deleteRecord = 0;
		String query = "delete from shainkyuuyokeisankiroku where KYUUYO_GATSU = TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'MM') AND kyuuyo_jisuu = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, kyuuyoGatsu);
			ps.setString(2, kyuuyoJisuu);
			
			deleteRecord = ps.executeUpdate();
            return deleteRecord;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
		return deleteRecord;
	}
	
	// 급여계산기록을 수정하는 메서드
	// 給与計算記録を修正するメソッド
	public int updateKyuuyoKeisanKiroku(Connection conn, Integer shain_id, String kyuuyoGatsu, String kyuuyoJisuu, int kyuuyoSougaku, int koujoSougaku, int jissaiKyuuyo) {
		PreparedStatement ps = null;
		String query = "UPDATE shainkyuuyokeisankiroku "
				+ "SET "
				+ "shikyuusougaku = ?, "
				+ "koujosougaku = ?, "
				+ "jissai_kyuuyo = ? "
				+ "where shain_id = ? "
				+ "AND kyuuyo_gatsu = TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND kyuuyo_jisuu = ?";
		int rValue = 0;
		
		 
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, kyuuyoSougaku);
			ps.setInt(2, koujoSougaku);
			ps.setInt(3, jissaiKyuuyo);
			ps.setInt(4, shain_id);
			ps.setString(5, kyuuyoGatsu);
			ps.setString(6, kyuuyoJisuu);
			
			rValue = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
		return rValue;
	}
	
	// 지난 급여기록의 목록을 가져오는 메서드
	// 過去の給与記録のリストを取得するメソッド
	public ArrayList<ShainKyuuyoKeisanKiroku> pastKyuuyoKiroku(Connection conn) {
		ArrayList<ShainKyuuyoKeisanKiroku> sList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT kyuuyo_gatsu, kyuuyo_jisuu "
				+ "FROM ( "
				+ "    SELECT kyuuyo_gatsu, kyuuyo_jisuu "
				+ "    FROM shainkyuuyokeisankiroku "
				+ "    GROUP BY kyuuyo_gatsu, kyuuyo_jisuu "
				+ "    ORDER BY kyuuyo_gatsu DESC "
				+ ") "
				+ "WHERE ROWNUM <= 13";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				sList.add(new ShainKyuuyoKeisanKiroku(rs.getDate(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
		return sList;
	}
}