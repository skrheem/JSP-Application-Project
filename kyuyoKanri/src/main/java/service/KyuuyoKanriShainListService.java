package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import jdbc.connection.ConnectionProvider;
import keisan.dao.ShainKyuuyoKeisanKirokuDao;
import keisan.model.ShainKyuuyoKeisanKiroku;

// 임세규 林世圭
// "자동계산" 버튼을 눌렀을 때 공제금액을 자동으로 계산하는 클래스
// ”自動計算“ボタンを押した際に控除額を計算するクラス
public class KyuuyoKanriShainListService {
    
    // 현재 날짜
    // 現在の日付
    LocalDate today = LocalDate.now();

    // 현재 달의 1일
    // 今月の1日
    LocalDate firstDayOfMonth = today.withDayOfMonth(1);

    // 현재 달의 마지막 날 (말일)
    // 今月の最終日 (末日)
    YearMonth yearMonth = YearMonth.from(today);
    LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

    // 날짜 포맷팅
    // 日付フォーマット
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedFirstDay = firstDayOfMonth.format(formatter);

    // kanri.do 페이지가 로드될 때 현재 시간을 기준으로 귀속연월을 설정하여 사원급여계산기록 리스트를 가져온다.
    // kanri.doページがロードされる際に、現在の時間に基づいて帰属年月を設定し、社員給与計算記録リストを取得する。
    public ArrayList<ShainKyuuyoKeisanKiroku> getShainList(String kyuuyoNendo, String kyuuyoGatsu, String kyuuyoJisuu, int koukinzeiType) {
        ShainKyuuyoKeisanKirokuDao sd = ShainKyuuyoKeisanKirokuDao.getInstance();
        ArrayList<ShainKyuuyoKeisanKiroku> sList = new ArrayList<>();
        if (kyuuyoGatsu.length() == 1)
            kyuuyoGatsu = "0" + kyuuyoGatsu;
        String kG = kyuuyoNendo + kyuuyoGatsu + "-01";
        try {
            Connection conn = ConnectionProvider.getConnection();
            sList = sd.getKyuuyoKeisanListByDateAndType(conn, kG, kyuuyoJisuu, koukinzeiType);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sList;
    }

    // 귀속연월 차수 지정해서 사원급여계산기록 리스트를 가져온다.
    // 帰属年月と次数を指定して社員給与計算記録リストを取得する。
    public ArrayList<ShainKyuuyoKeisanKiroku> getPostShainList(String year, String month, String jisuu, int koukinzeiType) {
        String kyuuyoGatsu = year + "-" + month + "-01";
        ShainKyuuyoKeisanKirokuDao sd = ShainKyuuyoKeisanKirokuDao.getInstance();
        ArrayList<ShainKyuuyoKeisanKiroku> postShainList = new ArrayList<>();

        try {
            Connection conn = ConnectionProvider.getConnection();
            postShainList = sd.getKyuuyoKeisanListByDateAndType(conn, kyuuyoGatsu, jisuu, koukinzeiType);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return postShainList;
    }
}
