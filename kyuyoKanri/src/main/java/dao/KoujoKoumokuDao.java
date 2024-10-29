package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import model.KoujoKoumoku;
import util.ObjectFormatter;

public class KoujoKoumokuDao {
	
	private static KoujoKoumokuDao kDao = new KoujoKoumokuDao();
	
	public static KoujoKoumokuDao getInstance() {
		return kDao;
	}
	
	// 공제항목 중 기본 항목의 정보를 가져오는 메서드(국민연금, 건강보험, 장기요양보험, 고용보험)
	// 基本項目の属性を持つ控除項目の情報を取得する。
	public ArrayList<KoujoKoumoku> getKihonKoujoKoumokuJouhou(Connection conn) {
		ArrayList<KoujoKoumoku> kkList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select koujokoumoku_id, koujokoumoku_mei, koujoritsu, keisanhouhou, shiyouumu, zenshadani, koujokoumokukubun, KihonKoumoku from koujokoumoku where shiyouumu = 'Y' AND KihonKoumoku IS NOT NULL ORDER BY koujokoumoku_id";
		
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				kkList.add(new KoujoKoumoku(rs.getInt(1), rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5).charAt(0), rs.getString(6), rs.getString(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return kkList;
	}
	
	// 사용자가 정의한 공제항목의 정보를 가져오는 메서드(기본항목 외의 공제항목)
	// 基本項目の属性を持ってない控除項目の情報を取得する。
	public ArrayList<KoujoKoumoku> getKoujoKoumokuJouhou(Connection conn) {
		ArrayList<KoujoKoumoku> kList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select koujokoumoku_id, koujokoumoku_mei, koujoritsu, keisanhouhou, shiyouumu, zenshadani, koujokoumokukubun, KihonKoumoku from koujokoumoku where shiyouumu = 'Y' AND kihonkoumoku IS NULL ORDER BY koujokoumoku_id";
		
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				kList.add(new KoujoKoumoku(rs.getInt(1), rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5).charAt(0), rs.getString(6), rs.getString(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return kList;
	}
	
}
