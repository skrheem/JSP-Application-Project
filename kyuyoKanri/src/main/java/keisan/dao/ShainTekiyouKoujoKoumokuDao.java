package keisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.JdbcUtil;
import keisan.model.ShainTekiyouKoujoKoumoku;

// 임세규 林世圭
// 사원별로 적용되는 공제항목의 정보를 가져오는 클래스
// 社員別に適用される控除項目の情報を取得するクラス
public class ShainTekiyouKoujoKoumokuDao {

    private static ShainTekiyouKoujoKoumokuDao instance = new ShainTekiyouKoujoKoumokuDao();

    public static ShainTekiyouKoujoKoumokuDao getInstance() {
        return instance;
    }

    // 사원별로 적용되는 공제항목의 공제항목명, 공제율, 계산방법을 가져오는 메서드
    // 社員別に適用される控除項目の控除項目名、控除率、計算方法を取得するメソッド
    public ArrayList<ShainTekiyouKoujoKoumoku> getKoujoJouhou(Connection conn, Integer shain_id) {
        ArrayList<ShainTekiyouKoujoKoumoku> stjList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT kj.koujoKoumoku_mei, kj.koujoRitsu, kj.keisanHouhou "
                + "FROM ShainTekiyouKoujoKoumoku stj "
                + "JOIN Shain s ON stj.shain_id = s.shain_id "
                + "JOIN KoujoKoumoku kj ON stj.koujoKoumoku_id = kj.koujoKoumoku_id "
                + "WHERE stj.shain_id = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, shain_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                stjList.add(new ShainTekiyouKoujoKoumoku(rs.getString(1), rs.getDouble(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }
        return stjList;
    }

    // 사원에게 적용되는 기본항목인 공제항목의 공제율과 그 사원의 기본급을 가져오는 메서드
    // 社員に適用される基本項目の控除項目の控除率とその社員の基本給を取得するメソッド
    public ArrayList<ShainTekiyouKoujoKoumoku> getShainTekiyouKoujoKingaku(Connection conn, Integer shain_id) {
        ArrayList<ShainTekiyouKoujoKoumoku> stkList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select kj.koujokoumoku_id, kj.koujoritsu, kj.koujokoumoku_mei, sk.kihonkyuu, kj.kihonkoumoku, kj.keisanhouhou " 
                + "from Shain s "
                + "JOIN shainkihonkyuu sk ON s.shain_id = sk.shain_id "
                + "JOIN shaintekiyoukoujokoumoku skj ON s.shain_id = skj.shain_id "
                + "JOIN koujokoumoku kj ON skj.koujokoumoku_id = kj.koujokoumoku_id "
                + "WHERE s.shain_id = ? AND kihonkoumoku IS NOT NULL "
                + "ORDER BY koujokoumoku_id";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, shain_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                stkList.add(new ShainTekiyouKoujoKoumoku(rs.getInt(1), rs.getDouble(2), rs.getString(3),
                        rs.getBigDecimal(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }
        return stkList;
    }
}
