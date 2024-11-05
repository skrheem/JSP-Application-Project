package keisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.JdbcUtil;
import keisan.model.KoujoKoumoku;

// 임세규 林世圭
// 공제항목을 삽입, 수정, 삭제하는 클래스
// 控除項目を挿入・修正・削除するクラス
public class KoujoKoumokuDao {
    
    private static KoujoKoumokuDao instance = new KoujoKoumokuDao();
    
    public static KoujoKoumokuDao getInstance() {
        return instance;
    }
    
    // 공제항목 중 기본 항목의 정보를 가져오는 메서드(국민연금, 건강보험, 장기요양보험, 고용보험)
    // 控除項目の基本項目の情報を取得するメソッド（国民年金、健康保険、長期介護保険、雇用保険）
    public ArrayList<KoujoKoumoku> getKihonKoujoKoumokuJouhou(Connection conn) {
        ArrayList<KoujoKoumoku> kkList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select koujokoumoku_id, koujokoumoku_mei, koujoritsu, keisanhouhou, shiyouumu, zenshadani, koujokoumokukubun, KihonKoumoku, bikou from koujokoumoku where shiyouumu = 'Y' AND KihonKoumoku IS NOT NULL ORDER BY koujokoumoku_id";
        
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()) {
                kkList.add(new KoujoKoumoku(rs.getInt(1), rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5).charAt(0), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }
        return kkList;
    }
    
    // 사용자가 정의한 공제항목의 정보를 가져오는 메서드(기본항목 외의 공제항목)
    // ユーザーが定義した控除項目の情報を取得するメソッド（基本項目以外の控除項目）
    public ArrayList<KoujoKoumoku> getKoujoKoumokuJouhou(Connection conn) {
        System.out.println("이 SQL문");
        ArrayList<KoujoKoumoku> kList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select koujokoumoku_id, koujokoumoku_mei, koujoritsu, keisanhouhou, shiyouumu, zenshadani, koujokoumokukubun, KihonKoumoku, bikou from koujokoumoku where shiyouumu = 'Y' AND kihonkoumoku IS NULL ORDER BY koujokoumoku_id";
        
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()) {
                kList.add(new KoujoKoumoku(rs.getInt(1), rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5).charAt(0), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }
        return kList;
    }
    
    // 공제항목을 추가하는 메서드
    // 控除項目を追加するメソッド
    public int insertKoujoKoumoku(Connection conn, String koujokoumoku_mei, double koujoritsu, String keisanhouhou, String zenshadani, char shiyouumu, String koujokoumokukubun, String kihonkoumoku, String bikou) {
        PreparedStatement ps = null;
        int rValue = 0;
        String query = "INSERT INTO koujokoumoku (koujokoumoku_id, koujokoumoku_mei, koujoritsu, keisanhouhou, zenshadani, shiyouumu, koujokoumokukubun, kihonkoumoku, bikou) "
                + "VALUES (koujokoumoku_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, koujokoumoku_mei);
            ps.setDouble(2, koujoritsu);
            ps.setString(3, keisanhouhou);
            ps.setString(4, zenshadani);
            ps.setString(5, String.valueOf(shiyouumu));
            ps.setString(6, koujokoumokukubun);
            ps.setString(7, kihonkoumoku);
            ps.setString(8, bikou);
            
            rValue = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
        }
        return rValue;
    }
    
    // 공제항목을 수정하는 클래스
    // 控除項目を修正するクラス
    public int updateKoujoKoumoku(Connection conn, Integer koujokoumoku_id, String koujokoumoku_mei, String keisanhouhou, String zenshadani, String bikou) {
        PreparedStatement ps = null;
        int rValue = 0;
        String query = "UPDATE koujokoumoku "
                + "SET koujokoumoku_mei = ?, keisanhouhou = ?, zenshadani = ?, bikou = ? "
                + "WHERE koujokoumoku_id = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, koujokoumoku_mei);
            ps.setString(2, keisanhouhou);
            ps.setString(3, zenshadani);
            ps.setString(4, bikou);
            ps.setInt(5, koujokoumoku_id);
            
            rValue = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
        }
        return rValue;
    }
    
    // 공제항목을 삭제하는 클래스
    // 控除項目を削除するクラス
    public int deleteKoujoKoumoku(Connection conn, Integer koujokoumoku_id) {
        PreparedStatement ps = null;
        int rValue = 0;
        String query = "DELETE FROM koujokoumoku "
                + "WHERE koujokoumoku_id = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, koujokoumoku_id);
            
            rValue = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
        }
        return rValue;
    }
}
