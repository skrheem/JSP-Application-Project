package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ShainKoujoKirokuDao;
import dao.ShainKyuuyoKeisanKirokuDao;
import dao.ShainKyuuyoKirokuDao;
import jdbc.connection.ConnectionProvider;
import model.ShainKoujoKiroku;
import model.ShainKyuuyoKeisanKiroku;
import model.ShainKyuuyoKiroku;

// 임세규 林世圭
// 급여입력・관리 페이지 / 給与入力・管理ページ
// 귀속연월, 차수에 해당하는 급여계산기록의 리스트를 가져옴
// 帰属年月、給与次数に該当する給与計算記録のリストを取得する
public class getKyuuyoKeisanKirokuListService {
	
	
	private ShainKyuuyoKeisanKirokuDao sk = ShainKyuuyoKeisanKirokuDao.getInstance();
	
	private ShainKyuuyoKirokuDao skk = ShainKyuuyoKirokuDao.getInstance();
	
	private ShainKoujoKirokuDao skj = ShainKoujoKirokuDao.getInstance();
	
	public ArrayList<ShainKyuuyoKiroku> getShainKyuuyoKiroku(Integer shain_id) {
		ArrayList<ShainKyuuyoKiroku> skkList = new ArrayList<>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			skkList = skk.getShainKyuuyoKiroku(conn, shain_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return skkList;
	}
	
	public ArrayList<ShainKoujoKiroku> getShainKoujoKiroku(Integer shain_id) {
		ArrayList<ShainKoujoKiroku> skjList = new ArrayList<>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			skjList = skj.getShainKoujoKiroku(conn, shain_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return skjList;
	}
	
	public ArrayList<ShainKyuuyoKeisanKiroku> getKiroku(String kyuuyo_gatsu, String kyuuyo_jisuu) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			ArrayList<ShainKyuuyoKeisanKiroku> skList = sk.getKyuuyoKeisanList(conn, kyuuyo_gatsu, kyuuyo_jisuu);
			
			return skList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
