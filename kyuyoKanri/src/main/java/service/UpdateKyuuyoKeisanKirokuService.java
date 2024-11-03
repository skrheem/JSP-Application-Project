package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ShainKyuuyoKeisanKirokuDao;
import jdbc.connection.ConnectionProvider;

public class UpdateKyuuyoKeisanKirokuService {

	public int updateKyuuyoKeisan(Integer shain_id, String kyuuyoGatsu, String kyuuyoJisuu, int kyuuyoSougaku, int koujoSougaku, int jissaiKyuuyo) {
		
		ShainKyuuyoKeisanKirokuDao sDao = ShainKyuuyoKeisanKirokuDao.getInstance();
		
		int rValue = 0;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			rValue = sDao.updateKyuuyoKeisanKiroku(conn, shain_id, kyuuyoGatsu, kyuuyoJisuu, kyuuyoSougaku, koujoSougaku, jissaiKyuuyo);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rValue;
	}
	
}
