package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.KoujoKoumokuDao;
import dao.KyuuyoKoumokuDao;
import dao.ShainKihonkyuuDao;
import dao.ShainTekiyouKoujoKoumokuDao;
import jdbc.connection.ConnectionProvider;
import model.KoujoKoumoku;
import model.KyuuyoKoumoku;
import model.ShainTekiyouKoujoKoumoku;
import util.ObjectFormatter;

public class getKoumokuJouhouService {
	
	public static void main(String a[]) {
		getKoumokuJouhouService gs = new getKoumokuJouhouService();
		try {
			System.out.println(ObjectFormatter.formatList(gs.getShainTekiyouKoujoKoumokuList(1)));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<KoujoKoumoku> getKoujoKoumokuJouhou() {
		ArrayList<KoujoKoumoku> kList = new ArrayList<>();
		KoujoKoumokuDao kDao = KoujoKoumokuDao.getInstance();
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			kList = kDao.getKoujoKoumokuJouhou(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kList;
	}
	
	public ArrayList<KyuuyoKoumoku> getKyuuyoKoumokuJouhou() {
		ArrayList<KyuuyoKoumoku> kList = new ArrayList<>();
		KyuuyoKoumokuDao kDao = KyuuyoKoumokuDao.getInstance();
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			kList = kDao.getKyuuyoKoumokuJouhou(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kList;
	}
	
	public int getShainKihonkyuu(Integer shain_id) {
		int kihonKyuu = 0;
		ShainKihonkyuuDao skDao = ShainKihonkyuuDao.getInstance();
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			kihonKyuu = skDao.getKihonkyuu(conn, shain_id);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return kihonKyuu;
	}
	
	public ArrayList<ShainTekiyouKoujoKoumoku> getShainTekiyouKoujoKoumokuList(Integer shain_id) {
		ArrayList<ShainTekiyouKoujoKoumoku> sktList = new ArrayList<>();
		ShainTekiyouKoujoKoumokuDao sDao = ShainTekiyouKoujoKoumokuDao.getInstance();
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			sktList = sDao.getShainTekiyouKoujoKingaku(conn, shain_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sktList;
	}
}
