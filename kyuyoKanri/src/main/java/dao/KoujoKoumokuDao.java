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
		String query = "select koujokoumoku_id, koujokoumoku_mei, koujoritsu, keisanhouhou, shiyouumu, zenshadani, koujokoumokukubun, KihonKoumoku, bikou from koujokoumoku where shiyouumu = 'Y' AND KihonKoumoku IS NOT NULL ORDER BY koujokoumoku_id";
		
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				kkList.add(new KoujoKoumoku(rs.getInt(1), rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5).charAt(0), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
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
		System.out.println("이 SQL문");
		ArrayList<KoujoKoumoku> kList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select koujokoumoku_id, koujokoumoku_mei, koujoritsu, keisanhouhou, shiyouumu, zenshadani, koujokoumokukubun, KihonKoumoku, bikou from koujokoumoku where shiyouumu = 'Y' AND kihonkoumoku IS NULL ORDER BY koujokoumoku_id";
		
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				kList.add(new KoujoKoumoku(rs.getInt(1), rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5).charAt(0), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return kList;
	}
	
	public int insertKoujoKoumoku(Connection conn, String koujokoumoku_mei, double koujoritsu, String keisanhouhou, String zenshadani, char shiyouumu, String koujokoumokukubun, String kihonkoumoku, String bikou) {
		PreparedStatement ps = null;
		int rValue = 0;
		String query = "INSERT INTO koujokoumoku (koujokoumoku_id, koujokoumoku_mei, koujoritsu, keisanhouhou, zenshadani, shiyouumu, koujokoumokukubun, kihonkoumoku, bikou) "
				+ "VALUES (koujokoumoku_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(query);
	        ps.setString(1, koujokoumoku_mei);
	        ps.setDouble(2, koujoritsu);
	        ps.setString(3, keisanhouhou);
	        ps.setString(4, zenshadani);
	        ps.setString(5, String.valueOf(shiyouumu));
	        ps.setString(6, koujokoumokukubun);
	        ps.setString(7, kihonkoumoku);
	        ps.setString(8, bikou);
	        
	        rValue = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
		return rValue;
	}
	
	public int updateKoujoKoumoku(Connection conn, Integer koujokoumoku_id, String koujokoumoku_mei, String keisanhouhou, String zenshadani, String bikou) {
		PreparedStatement ps = null;
		int rValue = 0;
		String query = "UPDATE koujokoumoku "
				+ "SET koujokoumoku_mei = ?, keisanhouhou = ?, zenshadani = ?, bikou = ? "
				+ "WHERE koujokoumoku_id = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, koujokoumoku_mei);
	        ps.setString(2, keisanhouhou);
	        ps.setString(3, zenshadani);
	        ps.setString(4, bikou);
	        ps.setInt(5, koujokoumoku_id);
	        
	        rValue = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
		return rValue;
	}
	
	public int deleteKoujoKoumoku(Connection conn, Integer koujokoumoku_id) {
		PreparedStatement ps = null;
		int rValue = 0;
		String query = "DELETE FROM koujokoumoku "
				+ "WHERE koujokoumoku_id = ?";
		try {
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, koujokoumoku_id); // ?에 koujokoumoku_id 바인딩

	        rValue = ps.executeUpdate(); // 쿼리 실행
	    } catch (SQLException e) {
	        e.printStackTrace(); // 예외 발생 시 출력
	    } finally {
	        JdbcUtil.close(ps);
	    }
		return rValue;
	}
}
