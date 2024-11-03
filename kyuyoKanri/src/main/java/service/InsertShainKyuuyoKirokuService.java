package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ShainKyuuyoKirokuDao;
import jdbc.connection.ConnectionProvider;

public class InsertShainKyuuyoKirokuService {
	public int insertShainKyuuyoKiroku(Integer shain_id, Integer kyuuyokoumoku_id, int kyuuyokoumoku_kingaku, String kyuuyokoumoku_nengappi, String kyuuyo_jisuu) {
		
		ShainKyuuyoKirokuDao sDao = ShainKyuuyoKirokuDao.getInstance();
		
		int rValue = 0;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			rValue = sDao.insertShainKyuuyoKiroku(conn, shain_id, kyuuyokoumoku_id, kyuuyokoumoku_kingaku, kyuuyokoumoku_nengappi, kyuuyo_jisuu);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rValue;
	}
}
