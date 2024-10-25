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

public class ShainDao {

	public static void main(String[] args) {
	    try(Connection conn = ConnectionProvider.getConnection()) {
	    
	        System.out.println("!!!!" + conn);
	        
	        ArrayList<Shain> ShainList = getInstance().getShainSentakuList(conn);
	        
	        try {
	        
	            System.out.println(ObjectFormatter.formatList(ShainList));
	            
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	private static ShainDao shainDao = new ShainDao();
	
	public static ShainDao getInstance() {
		return shainDao;
	}
	
	// 임세규 林世圭
	// 급여입력/관리 페이지의 "신규추가" 버튼을 눌렀을 때 출력할 사원 리스트
	// 給与入力・管理ページの”新規追加“ボタンを押した時ポップアップ画面に出力する社員のリスト
	public ArrayList<Shain> getShainSentakuList(Connection conn) {
		ArrayList shainList = new ArrayList<Shain>();	
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT s.kubun, s.shain_id, s.namae_kana, b.busho_mei, y.yakushoku_mei, s.jyoutai "
				+ "FROM Shain s "
				+ "JOIN Busho b ON s.busho_id = b.busho_id "
				+ "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id "
				+ "WHERE s.kubun != \'日雇い\'";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				shainList.add(new Shain(rs.getString("kubun"), rs.getBigDecimal("shain_id").intValue(), rs.getString("namae_kana"), rs.getString("busho_mei"), rs.getString("yakushoku_mei"), rs.getString("jyoutai")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return shainList;
	}
}
