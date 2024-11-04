package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.KoujoKoumokuDao;
import dao.KyuuyoKoumokuDao;
import jdbc.connection.ConnectionProvider;

public class DeleteKoumokuJouhouService {
	public int deleteKyuuyoKoumoku(Integer kyuuyokoumoku_id) {
		KyuuyoKoumokuDao kyDao = KyuuyoKoumokuDao.getInstance();
		int rValue = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			rValue = kyDao.deleteKyuuyoKoumoku(conn, kyuuyokoumoku_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rValue;
	}

	public int deleteKoujoKoumoku(Integer koujokoumoku_id) {
		KoujoKoumokuDao kjDao = KoujoKoumokuDao.getInstance();
		int rValue = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			rValue = kjDao.deleteKoujoKoumoku(conn, koujokoumoku_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rValue;
	}
}
