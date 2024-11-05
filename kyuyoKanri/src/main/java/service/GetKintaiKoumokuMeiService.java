package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.connection.ConnectionProvider;
import keisan.dao.KintaiKoumokuDao;
import keisan.model.KintaiKoumoku;

// 임세규 林世圭
// 근태항목명들을 가져오는 클래스
// 勤怠項目名を取得するクラス
public class GetKintaiKoumokuMeiService {
    
    // 근태항목명들을 리스트 형태로 만들어 반환하는 메서드
    // 勤怠項目名をリスト形式で作成して返すメソッド
    public ArrayList<KintaiKoumoku> getKintaiMeiList() {
        KintaiKoumokuDao kDao = KintaiKoumokuDao.getInstance();
        ArrayList<KintaiKoumoku> kList = new ArrayList<>();
        try {
            Connection conn = ConnectionProvider.getConnection();
            kList = kDao.getKintaiMei(conn);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return kList;
    }
}
