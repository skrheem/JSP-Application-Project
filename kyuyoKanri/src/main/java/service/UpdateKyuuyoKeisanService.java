package service;

import java.sql.Connection;
import java.sql.SQLException;
import jdbc.connection.ConnectionProvider;
import keisan.dao.ShainKyuuyoKeisanKirokuDao;

//임세규 林世圭
// 선택한 귀속연월, 급여차수에 해당하는 급여계산기록들의 급여산정일과 급여지급일을 수정하는 클래스
// 選択した帰属年月、給与次数に該当する給与計算記録の給与算定日と給与支給日を修正するクラス
public class UpdateKyuuyoKeisanService {
    
    // 선택한 귀속연월, 급여차수에 해당하는 급여계산기록들의 급여산정일과 급여지급일을 수정하는 메서드
    // 選択した帰属年月、給与次数に該当する給与計算記録の給与算定日と給与支給日を修正するメソッド
    public int updateSanteiShikyuuBi(String santei_kaishi, String santei_shuuryou, String shikyuu_bi, String exSantei_kaishi, String exSantei_shuuryou, String exShikyuu_bi, String koukinzei_kubun) {
        ShainKyuuyoKeisanKirokuDao skDao = ShainKyuuyoKeisanKirokuDao.getInstance();

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
