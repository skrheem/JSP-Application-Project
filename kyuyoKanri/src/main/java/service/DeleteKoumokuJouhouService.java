package service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import keisan.dao.KoujoKoumokuDao;
import keisan.dao.KyuuyoKoumokuDao;

// 임세규 林世圭
// 급여항목과 공제항목을 삭제하는 클래스
// 給与項目と控除項目を削除するクラス
public class DeleteKoumokuJouhouService {
    
    // 급여항목을 삭제하는 메서드
    // 給与項目を削除するメソッド
    public int deleteKyuuyoKoumoku(Integer kyuuyokoumoku_id) {
        KyuuyoKoumokuDao kyDao = KyuuyoKoumokuDao.getInstance();
        int rValue = 0;
        try {
            Connection conn = ConnectionProvider.getConnection();
            rValue = kyDao.deleteKyuuyoKoumoku(conn, kyuuyokoumoku_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }    
        return rValue;
    }

    // 공제항목을 삭제하는 메서드
    // 控除項目を削除するメソッド
    public int deleteKoujoKoumoku(Integer koujokoumoku_id) {
        KoujoKoumokuDao kjDao = KoujoKoumokuDao.getInstance();
        int rValue = 0;
        try {
            Connection conn = ConnectionProvider.getConnection();
            rValue = kjDao.deleteKoujoKoumoku(conn, koujokoumoku_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rValue;
    }
}
