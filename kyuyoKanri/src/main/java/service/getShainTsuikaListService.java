package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ShainDao;
import jdbc.connection.ConnectionProvider;
import model.Shain;

// 임세규 林世圭 급여입력・관리 페이지 / 給与入力・管理ページ
// "신규추가" 버튼을 눌렀을 때 일용직을 제외한 사원 리스트를 가져옴
// 帰属年月、給与次数に該当する給与計算記録のリストを取得する
public class getShainTsuikaListService {
	
	private ShainDao sd = ShainDao.getInstance();
	
	public ArrayList<Shain> tsuikaShainList() {
		ArrayList<Shain> sList = new ArrayList<>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			sList = sd.getShainSentakuList(conn);
			return sList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sList;
	}


}
