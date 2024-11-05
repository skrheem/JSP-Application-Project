package keisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.JdbcUtil;
import keisan.model.KintaiKoumoku;

//임세규 林世圭
//급여항목을 삽입, 수정할 때 근태항목 연계 드롭다운 메뉴에 출력할 정보들을 가져오는 클래스
//給与項目を挿入・修正する際に勤怠項目と連携するドロップダウンメニューに表示する情報を取得するクラス
public class KintaiKoumokuDao {
	private static KintaiKoumokuDao instance = new KintaiKoumokuDao();
	public static KintaiKoumokuDao getInstance() {
		return instance;
	}
	public ArrayList<KintaiKoumoku> getKintaiMei(Connection conn) {
		ArrayList<KintaiKoumoku> kList = new ArrayList<>();
		String query = "select kintai_mei from kintaikoumoku";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				kList.add(new KintaiKoumoku(rs.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
		return kList;
	}
}
