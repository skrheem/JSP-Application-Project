package service;

import java.sql.Connection;
import java.sql.SQLException;
import jdbc.connection.ConnectionProvider;
import keisan.dao.ShainKoujoKirokuDao;

// 임세규 林世圭
// 사원의 공제항목기록을 추가하는 클래스
// 社員の控除項目記録を追加するクラス
public class InsertShainKoujoKirokuService {
    
    // 사원의 공제항목기록을 추가하는 메서드
    // 社員の控除項目記録を追加するメソッド
    public int insertShainKoujoKiroku(Integer shain_id, Integer koujokoumoku_id, int koujokoumoku_kingaku, String koujokoumoku_nengappi, String kyuuyo_jisuu) {
        
        ShainKoujoKirokuDao sDao = ShainKoujoKirokuDao.getInstance();
        
        int rValue = 0;
        try {
            Connection conn = ConnectionProvider.getConnection();
            rValue = sDao.insertShainKoujoKiroku(conn, shain_id, koujokoumoku_id, koujokoumoku_kingaku, koujokoumoku_nengappi, kyuuyo_jisuu);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }     
        return rValue;
    }

}
