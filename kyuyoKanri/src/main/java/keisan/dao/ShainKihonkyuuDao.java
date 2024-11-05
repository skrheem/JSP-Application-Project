package keisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.JdbcUtil;

// 임세규 林世圭
// 사원 리스트에서 사원을 선택했을 때, 그 사원의 기본급 정보를 가져오는 클래스
// 社員リストから社員を選択した時、その社員の基本給情報を取得するクラス
public class ShainKihonkyuuDao {
    
    private static ShainKihonkyuuDao instance = new ShainKihonkyuuDao();
    
    public static ShainKihonkyuuDao getInstance() {
        return instance;
    }
    
    // 선택한 사원의 기본급 정보를 가져오는 메서드
    // 選択した社員の基本給情報を取得するメソッド
    public int getKihonkyuu(Connection conn, Integer shain_id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int kihonkyuu = 0;
        String query = "select kihonkyuu from shainkihonkyuu where shain_id = ?";
        try {
            ps = conn.prepareStatement(query);

            ps.setInt(1, shain_id);

            rs = ps.executeQuery();
            if(rs.next()) {
                kihonkyuu = rs.getInt(1);
                return kihonkyuu;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }
        return kihonkyuu;
    }
}
