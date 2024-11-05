package service;

import java.sql.Connection;
import java.sql.SQLException;
import jdbc.connection.ConnectionProvider;
import keisan.dao.ShainKyuuyoKirokuDao;

// 임세규 林世圭
// 사원의 급여항목기록을 삽입하는 클래스
// 社員の給与項目記録を挿入するクラス
public class InsertShainKyuuyoKirokuService {
    
    // 사원의 급여항목기록을 삽입하는 메서드
    // 社員の給与項目記録を挿入するメソッド
    public int insertShainKyuuyoKiroku(Integer shain_id, Integer kyuuyokoumoku_id, int kyuuyokoumoku_kingaku, String kyuuyokoumoku_nengappi, String kyuuyo_jisuu) {

        ShainKyuuyoKirokuDao sDao = ShainKyuuyoKirokuDao.getInstance();
        
        int rValue = 0;
        
        try {
            Connection conn = ConnectionProvider.getConnection();
            rValue = sDao.insertShainKyuuyoKiroku(conn, shain_id, kyuuyokoumoku_id, kyuuyokoumoku_kingaku, kyuuyokoumoku_nengappi, kyuuyo_jisuu);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return rValue;
    }
}
