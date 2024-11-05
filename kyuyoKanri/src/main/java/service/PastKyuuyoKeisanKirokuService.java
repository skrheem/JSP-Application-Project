package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import keisan.dao.ShainKyuuyoKeisanKirokuDao;
import keisan.model.ShainKyuuyoKeisanKiroku;

// 임세규 林世圭
// 지난급여기록 리스트를 불러오는 클래스
// 過去の給与記録リストを取得するクラス
public class PastKyuuyoKeisanKirokuService {
    
    // 지난급여기록 리스트를 불러오는 메서드
    // 過去の給与記録リストを取得するメソッド
    public ArrayList<ShainKyuuyoKeisanKiroku> getPastKeisanKiroku() {
        ArrayList<ShainKyuuyoKeisanKiroku> pList = new ArrayList<>();
        ShainKyuuyoKeisanKirokuDao skDao = ShainKyuuyoKeisanKirokuDao.getInstance();
        
        try {
            Connection conn = ConnectionProvider.getConnection();
            pList = skDao.pastKyuuyoKiroku(conn);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return pList;
    }
}
