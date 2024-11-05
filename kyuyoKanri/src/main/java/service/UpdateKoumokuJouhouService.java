package service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import keisan.dao.KoujoKoumokuDao;
import keisan.dao.KyuuyoKoumokuDao;

// 임세규 林世圭
// 급여항목, 공제항목 정보를 수정하는 클래스
// 給与項目、控除項目の情報を修正するクラス
public class UpdateKoumokuJouhouService {
    
    // 급여항목을 수정하는 메서드
    // 給与項目を修正するメソッド
    public int updateKyuuyoKoumoku(Integer kyuuyokoumoku_id, String kyuuyokoumoku_mei, String keisanhouhou, String zenshadani, String bikou) {
        KyuuyoKoumokuDao kyDao = KyuuyoKoumokuDao.getInstance();
        int rValue = 0;
        
        try {
            Connection conn = ConnectionProvider.getConnection();
            rValue = kyDao.updateKyuuyoKoumoku(conn, kyuuyokoumoku_id, kyuuyokoumoku_mei, keisanhouhou, zenshadani, bikou);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rValue;
    }

    // 공제항목을 수정하는 메서드
    // 控除項目を修正するメソッド
    public int updateKoujoKoumoku(Integer koujokoumoku_id, String koujokoumoku_mei, String keisanhouhou, String zenshadani, String bikou) {
        KoujoKoumokuDao kjDao = KoujoKoumokuDao.getInstance();
        int rValue = 0;
        
        try {
            Connection conn = ConnectionProvider.getConnection();
            rValue = kjDao.updateKoujoKoumoku(conn, koujokoumoku_id, koujokoumoku_mei, keisanhouhou, zenshadani, bikou);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rValue;
    }
}
