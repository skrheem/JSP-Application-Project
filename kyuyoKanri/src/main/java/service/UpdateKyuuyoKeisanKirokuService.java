package service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import keisan.dao.ShainKyuuyoKeisanKirokuDao;

// 임세규 林世圭
// 사원의 급여계산기록을 수정하는 클래스
// 社員の給与計算記録を修正するクラス
public class UpdateKyuuyoKeisanKirokuService {
    
    // 선택한 사원의 급여계산기록을 수정하는 메서드
    // 選択した社員の給与計算記録を修正するメソッド
    public int updateKyuuyoKeisan(Integer shain_id, String kyuuyoGatsu, String kyuuyoJisuu, int kyuuyoSougaku, int koujoSougaku, int jissaiKyuuyo) {
        
        ShainKyuuyoKeisanKirokuDao sDao = ShainKyuuyoKeisanKirokuDao.getInstance();
        
        int rValue = 0;
        
        try {
            Connection conn = ConnectionProvider.getConnection();
            rValue = sDao.updateKyuuyoKeisanKiroku(conn, shain_id, kyuuyoGatsu, kyuuyoJisuu, kyuuyoSougaku, koujoSougaku, jissaiKyuuyo);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return rValue;
    }
}
