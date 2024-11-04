package service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import dao.KoujoKoumokuDao;
import dao.KyuuyoKoumokuDao;
import jdbc.connection.ConnectionProvider;

public class InsertKoumokuJouhouService {
	public int insertKyuuyoKoumoku(String kyuuyokoumoku_mei, String kazeikubun, BigDecimal hikazeigendogaku, String bikou, String keisanhouhou, String zenshadani, String kintairenkei, String ikkatsushiharai, BigDecimal ikkatsushiharaigaku, String shiyouumu) {
		KyuuyoKoumokuDao kyDao = KyuuyoKoumokuDao.getInstance();
		int rValue = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			rValue = kyDao.insertKyuuyoKoumoku(conn, kyuuyokoumoku_mei, kazeikubun, hikazeigendogaku, bikou, keisanhouhou, zenshadani, kintairenkei, ikkatsushiharai, ikkatsushiharaigaku, shiyouumu);
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return rValue;
	}
	
	public int insertKoujoKoumoku(String koujokoumoku_mei, double koujoritsu, String keisanhouhou, String zenshadani, char shiyouumu, String koujokoumokukubun, String kihonkoumoku, String bikou) {
		KoujoKoumokuDao kjDao = KoujoKoumokuDao.getInstance();
		int rValue = 0;
		try {
			Connection conn = ConnectionProvider.getConnection();
			rValue = kjDao.insertKoujoKoumoku(conn, koujokoumoku_mei, koujoritsu, keisanhouhou, zenshadani, shiyouumu, koujokoumokukubun, kihonkoumoku, bikou);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return rValue;
	}
}
