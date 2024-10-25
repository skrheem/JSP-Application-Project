package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ShainDao;
import jdbc.connection.ConnectionProvider;
import model.Shain;
import util.ObjectFormatter;

public class ShainListService {
	private ShainDao shainDao = ShainDao.getInstance();
	
	public ArrayList<Shain> getShainList() {
		try(Connection conn = ConnectionProvider.getConnection()) {
			ArrayList<Shain> sList = shainDao.getShainSentakuList(conn);
			return sList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String args[]) {
		ShainListService shainListService = new ShainListService();
		try {
			System.out.println(ObjectFormatter.formatList(shainListService.getShainList()));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
