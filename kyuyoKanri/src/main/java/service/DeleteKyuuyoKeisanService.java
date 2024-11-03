package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ShainKyuuyoKeisanKirokuDao;
import jdbc.connection.ConnectionProvider;

public class DeleteKyuuyoKeisanService {
	public int deleteKyuuyoKeisanKiroku(Integer shain_id, String kyuuyoGatsu, String kyuuyoJisuu) {
		
		ShainKyuuyoKeisanKirokuDao sDao = ShainKyuuyoKeisanKirokuDao.getInstance();
		
		int deleteCount = 0;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			deleteCount = sDao.deleteShainKyuuyoKeisanKiroku(conn, shain_id, kyuuyoGatsu, kyuuyoJisuu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return deleteCount;
	}
	
public int deleteAllKyuuyoKeisanKiroku(String kyuuyoGatsu, String kyuuyoJisuu) {
		
		ShainKyuuyoKeisanKirokuDao sDao = ShainKyuuyoKeisanKirokuDao.getInstance();
		
		int deleteCount = 0;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			deleteCount = sDao.deleteAllShainKyuuyoKeisanKiroku(conn, kyuuyoGatsu, kyuuyoJisuu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return deleteCount;
	}
}
