package service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import keisan.dao.KoujoKoumokuDao;
import keisan.dao.KyuuyoKoumokuDao;

// 임세규 林世圭
// 급여항목, 공제항목을 삽입하는 클래스
// 給与項目、控除項目を挿入するクラス
public class InsertKoumokuJouhouService {
    
    // 급여항목을 삽입하는 메서드
    // 給与項目を挿入するメソッド
    public int insertKyuuyoKoumoku(String kyuuyokoumoku_mei, String kazeikubun, BigDecimal hikazeigendogaku, String bikou, String keisanhouhou, String zenshadani, String kintairenkei, String ikkatsushiharai, BigDecimal ikkatsushiharaigaku, String shiyouumu) {
        KyuuyoKoumokuDao kyDao = KyuuyoKoumokuDao.getInstance();
        int rValue = 0;
        try {
            Connection conn = ConnectionProvider.getConnection();
            rValue = kyDao.insertKyuuyoKoumoku(conn, kyuuyokoumoku_mei, kazeikubun, hikazeigendogaku, bikou, keisanhouhou, zenshadani, kintairenkei, ikkatsushiharai, ikkatsushiharaigaku, shiyouumu);
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return rValue;
    }
    
    // 공제항목을 삽입하는 클래스
    // 控除項目を挿入するメソッド
    public int insertKoujoKoumoku(String koujokoumoku_mei, double koujoritsu, String keisanhouhou, String zenshadani, char shiyouumu, String koujokoumokukubun, String kihonkoumoku, String bikou) {
        KoujoKoumokuDao kjDao = KoujoKoumokuDao.getInstance();
        int rValue = 0;
        try {
            Connection conn = ConnectionProvider.getConnection();
            rValue = kjDao.insertKoujoKoumoku(conn, koujokoumoku_mei, koujoritsu, keisanhouhou, zenshadani, shiyouumu, koujokoumokukubun, kihonkoumoku, bikou);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return rValue;
    }
}
