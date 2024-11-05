package service;

import java.sql.Connection;
import java.sql.SQLException;
import jdbc.connection.ConnectionProvider;
import keisan.dao.ShainKyuuyoKeisanKirokuDao;

// 임세규 林世圭
// 급여계산기록을 삭제하는 클래스
// 給与計算記録を削除するクラス
public class DeleteKyuuyoKeisanService {
    
    // 선택한 사원 한명의 급여계산기록을 삭제하는 메서드
    // 選択した社員の給与計算記録を削除するメソッド
    public int deleteKyuuyoKeisanKiroku(Integer shain_id, String kyuuyoGatsu, String kyuuyoJisuu) {
        ShainKyuuyoKeisanKirokuDao sDao = ShainKyuuyoKeisanKirokuDao.getInstance();    
        int deleteCount = 0;        
        try {
            Connection conn = ConnectionProvider.getConnection();
            deleteCount = sDao.deleteShainKyuuyoKeisanKiroku(conn, shain_id, kyuuyoGatsu, kyuuyoJisuu);
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return deleteCount;
    }
    
    // 선택한 귀속연월, 차수에 있는 모든 급여계산기록을 삭제하는 메서드
    // 選択した帰属年月、次数にあるすべての給与計算記録を削除するメソッド
    public int deleteAllKyuuyoKeisanKiroku(String kyuuyoGatsu, String kyuuyoJisuu) {
        ShainKyuuyoKeisanKirokuDao sDao = ShainKyuuyoKeisanKirokuDao.getInstance();
        int deleteCount = 0;        
        try {
            Connection conn = ConnectionProvider.getConnection();
            deleteCount = sDao.deleteAllShainKyuuyoKeisanKiroku(conn, kyuuyoGatsu, kyuuyoJisuu);
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return deleteCount;
    }
}
