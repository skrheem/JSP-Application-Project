package keisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.JdbcUtil;

// 임세규 林世圭
// 회사의 급여지급정보를 가져오는 클래스
// 会社の給与支給情報を取得するクラス
public class KyuuyoShikyuuJouhouDao {
    
    private static KyuuyoShikyuuJouhouDao instance = new KyuuyoShikyuuJouhouDao();
    
    public static KyuuyoShikyuuJouhouDao getInstance() {
        return instance;
    }
    
    // 급여입력관리 페이지에서 급여지급일에 출력할 데이터를 가져오는 메서드
    // 給与入力管理ページで給与支給日に出力するデータを取得するメソッド
    public String getKyuuyoShikyuuBi(Connection conn) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT kyuuyoShikyuu_bi FROM KyuuyoShikyuuJouhou";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1).split(" ")[0];
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }
        return null;
    }
    
    // 급여입력관리 페이지에서 급여산정일 개시에 출력할 데이터를 가져오는 메서드
    // 給与入力管理ページで給与算定開始日に出力するデータを取得するメソッド
    public String getKyuuyoSanteiKaishi(Connection conn) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT kyuuyoSanteiKaishi FROM KyuuyoShikyuuJouhou";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1).split(" ")[0];
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }
        return null;
    }
    
    // 급여입력관리 페이지에서 급여산정일 종료에 출력할 데이터를 가져오는 메서드
    // 給与入力管理ページで給与算定終了日に出力するデータを取得するメソッド
    public String getKyuuyoSanteiShuuryou(Connection conn) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT kyuuyoSanteiShuuryou FROM KyuuyoShikyuuJouhou";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1).split(" ")[0];
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }
        return null;
    }
}
