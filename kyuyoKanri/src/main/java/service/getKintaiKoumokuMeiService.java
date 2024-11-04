package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.KintaiKoumokuDao;
import jdbc.connection.ConnectionProvider;
import model.KintaiKoumoku;

public class getKintaiKoumokuMeiService {
	public ArrayList<KintaiKoumoku> getKintaiMeiList() {
		KintaiKoumokuDao kDao = KintaiKoumokuDao.getInstance();
		ArrayList<KintaiKoumoku> kList = new ArrayList<>();
		try {
			Connection conn = ConnectionProvider.getConnection();
			kList = kDao.getKintaiMei(conn);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return kList;
	}
}
