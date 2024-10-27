package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.ShainKyuuyoKeisanKirokuDao;
import jdbc.connection.ConnectionProvider;
import model.ShainKyuuyoKeisanKiroku;

// 임세규 林世圭 급여입력・관리 페이지 / 給与入力・管理ページ
// "자동계산" 버튼을 눌렀을 때 공제금액을 자동으로 계산하는 클래스
// ”自動計算“ボタンを押した際に控除額を計算する。
public class kyuuyoKanriShainListService {
	// 현재 날짜
    LocalDate today = LocalDate.now();
    
    // 현재 달의 1일
    LocalDate firstDayOfMonth = today.withDayOfMonth(1);
    
    // 현재 달의 마지막 날 (말일)
    YearMonth yearMonth = YearMonth.from(today);
    LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
    
    // 포맷팅
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedFirstDay = firstDayOfMonth.format(formatter);
    String formattedLastDay = lastDayOfMonth.format(formatter);
	
	public ArrayList<ShainKyuuyoKeisanKiroku> getShainList() {
		ShainKyuuyoKeisanKirokuDao sd = ShainKyuuyoKeisanKirokuDao.getInstance();
		ArrayList<ShainKyuuyoKeisanKiroku> sList = new ArrayList<>();
		try {
			Connection conn = ConnectionProvider.getConnection();
			sList = sd.getKyuuyoKeisanList(conn, formattedFirstDay, formattedLastDay, "1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sList;
	}
}
