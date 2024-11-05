package keisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jdbc.JdbcUtil;

// 임세규 林世圭
// 사원의 공제항목 기록을 삽입하는 클래스
// 社員の控除項目記録を挿入するクラス
public class ShainKoujoKirokuDao {
    
    private static ShainKoujoKirokuDao instance = new ShainKoujoKirokuDao();
    
    public static ShainKoujoKirokuDao getInstance() {
        return instance;
    }
    
    // 공제항목id, 공제적용일, 급여차수를 조건으로 선택한 사원에게 같은 내용의 공제기록 데이터가 있다면 업데이트를 하고 존재하지 않는다면 삽입하는 메서드
    // 控除項目ID、控除適用日、給与次数を条件に、選択した社員に同じ内容の控除記録データがあれば更新し、存在しない場合は挿入するメソッド
    public int insertShainKoujoKiroku(Connection conn, Integer shain_id, Integer koujokoumoku_id, int koujokoumoku_kingaku, String koujokoumoku_nengappi, String kyuuyo_jisuu) {
        PreparedStatement ps = null;
        String query = "MERGE INTO shainkoujokiroku target "
                + "USING (SELECT ? AS shain_id, "
                + "              ? AS koujokoumoku_id, "
                + "              ? AS koujokoumoku_kingaku, "
                + "              TO_DATE(?, 'YYYY-MM-DD') AS koujokoumoku_nengappi, "
                + "              ? AS kyuuyo_jisuu "
                + "       FROM dual) source "
                + "ON (target.shain_id = source.shain_id "
                + "    AND target.koujokoumoku_id = source.koujokoumoku_id "
                + "    AND target.koujokoumoku_nengappi = source.koujokoumoku_nengappi "
                + "    AND target.kyuuyo_jisuu = source.kyuuyo_jisuu) "
                + "WHEN MATCHED THEN "
                + "    UPDATE SET target.koujokoumoku_kingaku = source.koujokoumoku_kingaku "
                + "WHEN NOT MATCHED THEN "
                + "    INSERT (shain_id, koujokoumoku_id, koujokoumoku_kingaku, koujokoumoku_nengappi, kyuuyo_jisuu) "
                + "    VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)";
        int rValue = 0;
        
        try {
            ps = conn.prepareStatement(query);

            ps.setInt(1, shain_id);
            ps.setInt(2, koujokoumoku_id);
            ps.setInt(3, koujokoumoku_kingaku);
            ps.setString(4, koujokoumoku_nengappi);
            ps.setString(5, kyuuyo_jisuu);
            ps.setInt(6, shain_id);
            ps.setInt(7, koujokoumoku_id);
            ps.setInt(8, koujokoumoku_kingaku);
            ps.setString(9, koujokoumoku_nengappi);
            ps.setString(10, kyuuyo_jisuu);
            
            rValue = ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
        }
        return rValue;
    }
}
