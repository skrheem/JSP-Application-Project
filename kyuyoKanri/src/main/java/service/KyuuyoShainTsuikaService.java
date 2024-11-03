package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.KoukinzeiDao;
import dao.ShainKihonkyuuDao;
import dao.ShainKyuuyoKeisanKirokuDao;
import jdbc.connection.ConnectionProvider;

public class KyuuyoShainTsuikaService {
///kyuuyoBi, kyuuyoJisuu, shikyuu_bi, Integer.parseInt(selectedIds.getString(i)), santeiKaishi, santeiShuuryou
	
	public void shainKeisanKirokuTsuika(String kyuuyoBi, String kyuuyoJisuu, String shikyuu_bi, Integer shain_id, String santeiKaishi, String santeiShuuryou) {
		ShainKyuuyoKeisanKirokuDao skkDao = ShainKyuuyoKeisanKirokuDao.getInstance();
		ShainKihonkyuuDao skDao = ShainKihonkyuuDao.getInstance();
		KoukinzeiDao kDao = KoukinzeiDao.getInstance();
		try {
			Connection conn = ConnectionProvider.getConnection();
			int kihonkyuu = skDao.getKihonkyuu(conn, shain_id);
			String koukinzei = kDao.getKoukinzei(conn, shain_id);
			if(skkDao.isEmptyShainKeisanKiroku(conn, kyuuyoBi, shain_id))
				skkDao.insertShainKeisanKiroku(conn, kyuuyoBi, kyuuyoJisuu, shikyuu_bi, shain_id, santeiKaishi, santeiShuuryou, kihonkyuu, koukinzei);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
