package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import keisan.dao.ShainDao;
import keisan.model.Shain;

// 임세규 林世圭
// "신규추가" 버튼을 눌렀을 때 일용직을 제외한 사원 리스트를 가져옴
// 帰属年月、給与次数に該当する給与計算記録のリストを取得する
public class GetShainTsuikaListService {
	// 갑근세 타입별로 사원 리스트를 만들어 반환하는 메서드
	// 甲欽税タイプ別に社員リストを作成して返すメソッド
	public ArrayList<Shain> tsuikaShainList(String koukinzei) {
		ShainDao sd = ShainDao.getInstance();
		ArrayList<Shain> sList = new ArrayList<>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			sList = sd.getShainSentakuList(conn, koukinzei);
			return sList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sList;
	}


}
