package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ShainTekiyouKoujoKoumoku;

public class ShainTekiyouKoujoKoumokuDao {
	
	
	// 김현서 金賢徐
	//미니팝업 (사원이미지, 이름, 주소, 갑근세_명)
	public List<ShainTekiyouKoujoKoumoku> ShainListMiniPopupOne(Connection conn, Integer shain_id, String koujoKoumokuKubun) throws SQLException {
	    String query = "SELECT s.shain_image_keiro, s.namae_kana, s.nyuusha_nengappi, s.juusho ,k.koujoKoumoku_mei " +
	                   "FROM ShainTekiyouKoujoKoumoku st " +
	                   "JOIN KoujoKoumoku k ON st.koujoKoumoku_id = k.koujoKoumoku_id " +
	                   "JOIN Shain s ON st.shain_id = s.shain_id " +
	                   "WHERE st.shain_id = ? AND k.koujoKoumokuKubun = ?";

	    List<ShainTekiyouKoujoKoumoku> resultList = new ArrayList<>();

	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setInt(1, shain_id);  // 첫 번째 ?에 shain_id 값 설정
	        pstmt.setString(2, koujoKoumokuKubun);  // 두 번째 ?에 koujoKoumokuKubun 값 설정

	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                ShainTekiyouKoujoKoumoku record = new ShainTekiyouKoujoKoumoku();
	                record.setShainImageKeiro(rs.getString("shain_image_keiro"));
	                record.setNamaeKana(rs.getString("namae_kana"));
	                record.setNyuushaNengappi(rs.getDate("nyuusha_nengappi"));
	                record.setJuusho(rs.getString("juusho"));
	                record.setKoujoKoumokuMei(rs.getString("koujoKoumoku_mei"));

	                resultList.add(record);  // 결과를 리스트에 추가
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }

	    return resultList;  // 결과 리스트 반환
	}
	
	
	
	// 김현서 金賢徐
	// 미니팝업의 두번째 정보 / 사대보험(국민연금, 건강보험, 고용보험)의 id가 있는지 확인
	// id값이 있을 시 "공제"를 반환, null값일시 "비공제" 반환
	public String ShainListMiniPopupTwo(Connection conn, Integer shain_id, String koujoKoumoku_id) throws SQLException {
	    String query = 
	    			"SELECT koujokoumoku_id" +
	    			"FROM ShainTekiyouKoujoKoumoku" +
	    			"WHERE shain_id = ? AND koujokoumoku_id = ? ";
	    
	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setInt(1, shain_id); 
	        pstmt.setString(2, koujoKoumoku_id); 
	    
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                // 값이 있으면 "控除" 반환
	                return "控除";
	            } else {
	                // 값이 없으면 "非控除" 반환
	                return "非控除";
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    	}        
	    }

}
