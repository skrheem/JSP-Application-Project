package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ShainKyuuyoKeisanKirokuDao;
import jdbc.connection.ConnectionProvider;

public class UpdateKyuuyoKeisanService {
	
	public int updateSanteiShikyuuBi(String santei_kaishi, String santei_shuuryou, String shikyuu_bi, String exSantei_kaishi, String exSantei_shuuryou, String exShikyuu_bi, String koukinzei_kubun) {
		ShainKyuuyoKeisanKirokuDao skDao = ShainKyuuyoKeisanKirokuDao.getInstance();
		System.out.println("====================================================");
		System.out.println("업데이트 서비스 접속");
		System.out.println("업데이트 할 값 : ");
		System.out.println(santei_kaishi);
		System.out.println(santei_shuuryou);
		System.out.println(shikyuu_bi);
		System.out.println(exSantei_kaishi);
		System.out.println(exSantei_shuuryou);
		System.out.println(exShikyuu_bi);
		int rValue = 0;
		try {
			Connection conn = ConnectionProvider.getConnection(); 
			rValue = skDao.updateSanteiShikyuuBi(conn, santei_kaishi, santei_shuuryou, shikyuu_bi, exSantei_kaishi, exSantei_shuuryou, exShikyuu_bi, koukinzei_kubun);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rValue;
	}
		
}
