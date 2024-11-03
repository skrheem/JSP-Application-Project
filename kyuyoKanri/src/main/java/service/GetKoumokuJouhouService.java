package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.FuyouKazokuDao;
import dao.KoujoKoumokuDao;
import dao.KyuuyoKoumokuDao;
import dao.ShainKihonkyuuDao;
import dao.ShainTekiyouKoujoKoumokuDao;
import dao.ZeigakuHyouDao;
import jdbc.connection.ConnectionProvider;
import model.KoujoKoumoku;
import model.KyuuyoKoumoku;
import model.ShainTekiyouKoujoKoumoku;

public class GetKoumokuJouhouService {
	
	// 기본항목인 공제항목의 정보를 가져오는 메서드
	public ArrayList<KoujoKoumoku> getKihonKoujoKoumokuJouhou() {
		ArrayList<KoujoKoumoku> kkList = new ArrayList<>();
		KoujoKoumokuDao kDao = KoujoKoumokuDao.getInstance();
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			kkList = kDao.getKihonKoujoKoumokuJouhou(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kkList;
	}
	// 사용자가 정의한 공제항목의 정보를 가져오는 메서드
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
	// 급여항목의 정보를 가져오는 메서드
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
	// 사원의 기본급 정보를 가져오는 메서드
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
	// 사원별로 적용되는 공제항목의 정보를 가져오는 메서드
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
	// 사원의 소득세를 계산하는 메서드
	public int keisanShotokuZei(Integer shain_id) {
		ZeigakuHyouDao zDao = ZeigakuHyouDao.getInstance();
		FuyouKazokuDao fDao = FuyouKazokuDao.getInstance();
		ShainKihonkyuuDao sDao = ShainKihonkyuuDao.getInstance();
		int shotokuZei = 0;
		try(Connection conn = ConnectionProvider.getConnection()) {
			shotokuZei = zDao.keisanZeigaku(conn, shain_id, sDao.getKihonkyuu(conn, shain_id), fDao.countHatachiKazoku(conn, shain_id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shotokuZei;
	}
	
	// 기본급을 제외한 급여항목의 id값과 비과세한도액(전체과세라면 0을 반환)을 가져오는 메서드
	public ArrayList<KyuuyoKoumoku> getKyuuyokoumokuKingakuList(Integer shain_id, String kyuuyoNengappi) {
		KyuuyoKoumokuDao kDao = KyuuyoKoumokuDao.getInstance();
		ArrayList<KyuuyoKoumoku> kList = new ArrayList<>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			kList = kDao.getKyuuyoKoumokuKingaku(conn, shain_id, kyuuyoNengappi);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return kList;
	}
}
