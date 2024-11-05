package service;

import java.sql.Connection;
import java.sql.SQLException;
import jdbc.connection.ConnectionProvider;
import keisan.dao.KoukinzeiDao;
import keisan.dao.ShainKihonkyuuDao;
import keisan.dao.ShainKyuuyoKeisanKirokuDao;

// 임세규 林世圭
// 사원급여계산기록에 새로운 급여계산기록을 삽입하는 클래스
// 社員給与計算記録に新しい給与計算記録を挿入するクラス
public class KyuuyoShainTsuikaService {
    
    // 사원급여계산기록에 새로운 급여계산기록을 삽입하는 메서드
    // 社員給与計算記録に新しい給与計算記録を挿入するメソッド
    public void shainKeisanKirokuTsuika(String kyuuyoBi, String kyuuyoJisuu, String shikyuu_bi, Integer shain_id,
                                        String santeiKaishi, String santeiShuuryou) {
        ShainKyuuyoKeisanKirokuDao skkDao = ShainKyuuyoKeisanKirokuDao.getInstance();
        ShainKihonkyuuDao skDao = ShainKihonkyuuDao.getInstance();
        KoukinzeiDao kDao = KoukinzeiDao.getInstance();
        
        try {
            Connection conn = ConnectionProvider.getConnection();
            int kihonkyuu = skDao.getKihonkyuu(conn, shain_id);
            String koukinzei = kDao.getKoukinzei(conn, shain_id);
            if (skkDao.isEmptyShainKeisanKiroku(conn, kyuuyoBi, shain_id))
                skkDao.insertShainKeisanKiroku(conn, kyuuyoBi, kyuuyoJisuu, shikyuu_bi, shain_id, santeiKaishi,
                                               santeiShuuryou, kihonkyuu, koukinzei);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
