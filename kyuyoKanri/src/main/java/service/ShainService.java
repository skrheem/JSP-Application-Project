package service;
//김찬호 金燦鎬
import java.sql.SQLException;
import java.util.ArrayList;

import kihonkankyousettei.dao.ShainDao;
import kihonkankyousettei.model.Shain;

public class ShainService {
	private ShainDao shainDao = ShainDao.getInstance();

	/**
	 * 모든 사원 목록을 가져오는 메서드입니다.
	 * すべての社員のリストを取得するメソッドです。
	 */
	public ArrayList<Shain> getShainList() throws SQLException {
		ArrayList<Shain> shainList = shainDao.getAllShain();
		System.out.println("Service에서 가져온 Shain List: " + shainList); // 데이터 확인
		return shainList;
	}

	/**
	 * 휴가일수를 추가하는 메서드입니다.
	 * 休暇日数を追加するメソッドです。
	 */
	public void addKyuukaNissuu(int shainId, double vacationDays) throws SQLException {
		shainDao.insertKyuukaNissuu(shainId, vacationDays);
	}
}
