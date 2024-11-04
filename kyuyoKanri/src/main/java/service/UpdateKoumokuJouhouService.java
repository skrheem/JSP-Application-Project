package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.KoujoKoumokuDao;
import dao.KyuuyoKoumokuDao;
import jdbc.connection.ConnectionProvider;

public class UpdateKoumokuJouhouService {
	public int updateKyuuyoKoumoku(Integer kyuuyokoumoku_id, String kyuuyokoumoku_mei, String keisanhouhou, String zenshadani, String bikou) {
		KyuuyoKoumokuDao kyDao = KyuuyoKoumokuDao.getInstance();
		int rValue = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			rValue = kyDao.updateKyuuyoKoumoku(conn, kyuuyokoumoku_id, kyuuyokoumoku_mei, keisanhouhou, zenshadani, bikou);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rValue;
	}

	public int updateKoujoKoumoku(Integer koujokoumoku_id, String koujokoumoku_mei, String keisanhouhou, String zenshadani, String bikou) {
		KoujoKoumokuDao kjDao = KoujoKoumokuDao.getInstance();
		int rValue = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			rValue = kjDao.updateKoujoKoumoku(conn, koujokoumoku_id, koujokoumoku_mei, keisanhouhou, zenshadani, bikou);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rValue;
	}
}
