package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.connection.ConnectionProvider;
import keisan.dao.FuyouKazokuDao;
import keisan.dao.KoujoKoumokuDao;
import keisan.dao.KyuuyoKoumokuDao;
import keisan.dao.ShainKihonkyuuDao;
import keisan.dao.ShainTekiyouKoujoKoumokuDao;
import keisan.dao.ZeigakuHyouDao;
import keisan.model.KoujoKoumoku;
import keisan.model.KyuuyoKoumoku;
import keisan.model.ShainTekiyouKoujoKoumoku;

// 임세규 林世圭
// 급여항목, 공제항목을 가져오는 클래스
// 給与項目、控除項目を取得するクラス
public class GetKoumokuJouhouService {
    
    // 기본항목인 공제항목의 정보를 가져오는 메서드
    // 基本項目の控除項目の情報を取得するメソッド
    public ArrayList<KoujoKoumoku> getKihonKoujoKoumokuJouhou() {
        ArrayList<KoujoKoumoku> kkList = new ArrayList<>();
        KoujoKoumokuDao kDao = KoujoKoumokuDao.getInstance();
        
        try(Connection conn = ConnectionProvider.getConnection()) {
            kkList = kDao.getKihonKoujoKoumokuJouhou(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kkList;
    }

    // 사용자가 정의한 공제항목의 정보를 가져오는 메서드
    // ユーザーが定義した控除項目の情報を取得するメソッド
    public ArrayList<KoujoKoumoku> getKoujoKoumokuJouhou() {
        ArrayList<KoujoKoumoku> kList = new ArrayList<>();
        KoujoKoumokuDao kDao = KoujoKoumokuDao.getInstance();
        
        try(Connection conn = ConnectionProvider.getConnection()) {
            kList = kDao.getKoujoKoumokuJouhou(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kList;
    }

    // 급여항목의 정보를 가져오는 메서드
    // 給与項目の情報を取得するメソッド
    public ArrayList<KyuuyoKoumoku> getKyuuyoKoumokuJouhou() {
        ArrayList<KyuuyoKoumoku> kList = new ArrayList<>();
        KyuuyoKoumokuDao kDao = KyuuyoKoumokuDao.getInstance();
        
        try(Connection conn = ConnectionProvider.getConnection()) {
            kList = kDao.getKyuuyoKoumokuJouhou(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kList;
    }

    // 사원의 기본급 정보를 가져오는 메서드
    // 社員の基本給情報を取得するメソッド
    public int getShainKihonkyuu(Integer shain_id) {
        int kihonKyuu = 0;
        ShainKihonkyuuDao skDao = ShainKihonkyuuDao.getInstance();
        
        try(Connection conn = ConnectionProvider.getConnection()) {
            kihonKyuu = skDao.getKihonkyuu(conn, shain_id);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return kihonKyuu;
    }

    // 사원별로 적용되는 공제항목의 정보를 가져오는 메서드
    // 社員別に適用される控除項目の情報を取得するメソッド
    public ArrayList<ShainTekiyouKoujoKoumoku> getShainTekiyouKoujoKoumokuList(Integer shain_id) {
        ArrayList<ShainTekiyouKoujoKoumoku> sktList = new ArrayList<>();
        ShainTekiyouKoujoKoumokuDao sDao = ShainTekiyouKoujoKoumokuDao.getInstance();
        
        try(Connection conn = ConnectionProvider.getConnection()) {
            sktList = sDao.getShainTekiyouKoujoKingaku(conn, shain_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sktList;
    }

    // 사원의 소득세를 계산하는 메서드
    // 社員の所得税を計算するメソッド
    public int keisanShotokuZei(Integer shain_id) {
        ZeigakuHyouDao zDao = ZeigakuHyouDao.getInstance();
        FuyouKazokuDao fDao = FuyouKazokuDao.getInstance();
        ShainKihonkyuuDao sDao = ShainKihonkyuuDao.getInstance();
        int shotokuZei = 0;
        try(Connection conn = ConnectionProvider.getConnection()) {
            shotokuZei = zDao.keisanZeigaku(conn, shain_id, sDao.getKihonkyuu(conn, shain_id), fDao.countHatachiKazoku(conn, shain_id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shotokuZei;
    }
    
    // 기본급을 제외한 급여항목의 id값과 비과세한도액(전체課税なら0을返す)을 가져오는 메서드
    // 基本給を除く給与項目のIDと非課税限度額（全課税なら0を返す）を取得するメソッド
    public ArrayList<KyuuyoKoumoku> getKyuuyokoumokuKingakuList(Integer shain_id, String kyuuyoNengappi) {
        KyuuyoKoumokuDao kDao = KyuuyoKoumokuDao.getInstance();
        ArrayList<KyuuyoKoumoku> kList = new ArrayList<>();
        try(Connection conn = ConnectionProvider.getConnection()) {
            kList = kDao.getKyuuyoKoumokuKingaku(conn, shain_id, kyuuyoNengappi);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return kList;
    }
}
