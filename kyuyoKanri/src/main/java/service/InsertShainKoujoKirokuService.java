package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ShainKoujoKirokuDao;
import jdbc.connection.ConnectionProvider;

public class InsertShainKoujoKirokuService {
	
	public int insertShainKoujoKiroku(Integer shain_id, Integer koujokoumoku_id, int koujokoumoku_kingaku, String koujokoumoku_nengappi, String kyuuyo_jisuu) {
		
		ShainKoujoKirokuDao sDao = ShainKoujoKirokuDao.getInstance();
		
		int rValue = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			rValue = sDao.insertShainKoujoKiroku(conn, shain_id, koujokoumoku_id, koujokoumoku_kingaku, koujokoumoku_nengappi, kyuuyo_jisuu);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rValue;
	}

}
