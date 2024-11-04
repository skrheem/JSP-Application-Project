package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ShainKyuuyoKeisanKirokuDao;
import jdbc.connection.ConnectionProvider;
import model.ShainKyuuyoKeisanKiroku;

public class pastKyuuyoKeisanKirokuService {
	public ArrayList<ShainKyuuyoKeisanKiroku> getPastKeisanKiroku() {
		ArrayList<ShainKyuuyoKeisanKiroku> pList = new ArrayList<>();
		ShainKyuuyoKeisanKirokuDao skDao = ShainKyuuyoKeisanKirokuDao.getInstance();
		try {
			Connection conn = ConnectionProvider.getConnection();
			pList = skDao.pastKyuuyoKiroku(conn);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return pList;
	}
}
