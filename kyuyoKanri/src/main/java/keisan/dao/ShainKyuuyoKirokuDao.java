package keisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.JdbcUtil;
import keisan.model.ShainKyuuyoKiroku;

// 임세규 林世圭
// 사원의 급여항목기록을 가져오거나 삽입하는 클래스
// 社員の給与項目記録を取得または挿入するクラス
public class ShainKyuuyoKirokuDao {
    private static ShainKyuuyoKirokuDao instance = new ShainKyuuyoKirokuDao();
    
    public static ShainKyuuyoKirokuDao getInstance() {
        return instance;
    }
    
    // 사원의 급여항목기록을 가져오는 메서드
    // 社員の給与項目記録を取得するメソッド
    public ArrayList<ShainKyuuyoKiroku> getShainKyuuyoKiroku(Connection conn, Integer shain_id) {
        ArrayList<ShainKyuuyoKiroku> skkList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select sk.*, kj.kyuuyokoumoku_mei, kj.keisanHouhou "
                + "from shainkyuuyokiroku sk "
                + "JOIN kyuuyokoumoku kj ON sk.kyuuyokoumoku_id = kj.kyuuyokoumoku_id "
                + "where shain_id = ? ORDER BY kj.kyuuyoKoumoku_id";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, shain_id);
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
                skkList.add(new ShainKyuuyoKiroku(rs.getInt(1), rs.getInt(2), rs.getBigDecimal(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(ps);
        }
            
        return skkList;
    }
    
    // 사원의 급여항목기록을 삽입하는 메서드. 급여항목id, 급여항목지급일, 급여차수를 조건으로 같은 내용의 데이터가 있다면 수정하고, 없다면 삽입하는 메서드
    // 社員の給与項目記録を挿入するメソッド。給与項目ID、給与項目支給日、給与次数を条件に同じ内容のデータがあれば修正し、なければ挿入するメソッド
    public int insertShainKyuuyoKiroku(Connection conn, Integer shain_id, Integer kyuuyokoumoku_id, int kyuuyokoumoku_kingaku, String kyuuyokoumoku_nengappi, String kyuuyo_jisuu) {
        PreparedStatement ps = null;
        String query = "MERGE INTO shainkyuuyoKiroku target "
                + "USING (SELECT ? AS shain_id, "
                + "              ? AS kyuuyokoumoku_id, "
                + "              ? AS kyuuyokoumoku_kingaku, "
                + "              TO_DATE(?, 'YYYY-MM-DD') AS kyuuyokoumoku_nengappi, "
                + "              ? AS kyuuyo_jisuu "
                + "       FROM dual) source "
                + "ON (target.shain_id = source.shain_id "
                + "    AND target.kyuuyokoumoku_id = source.kyuuyokoumoku_id "
                + "    AND target.kyuuyokoumoku_nengappi = source.kyuuyokoumoku_nengappi "
                + "    AND target.kyuuyo_jisuu = source.kyuuyo_jisuu) "
                + "WHEN MATCHED THEN"
                + "    UPDATE SET target.kyuuyokoumoku_kingaku = source.kyuuyokoumoku_kingaku "
                + "WHEN NOT MATCHED THEN "
                + "    INSERT (shain_id, kyuuyokoumoku_id, kyuuyokoumoku_kingaku, kyuuyokoumoku_nengappi, kyuuyo_jisuu) "
                + "    VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)";
        int rValue = 0;
        
        try {
            ps = conn.prepareStatement(query);

            ps.setInt(1, shain_id);
            ps.setInt(2, kyuuyokoumoku_id);
            ps.setInt(3, kyuuyokoumoku_kingaku);
            ps.setString(4, kyuuyokoumoku_nengappi);
            ps.setString(5, kyuuyo_jisuu);
            ps.setInt(6, shain_id);
            ps.setInt(7, kyuuyokoumoku_id);
            ps.setInt(8, kyuuyokoumoku_kingaku);
            ps.setString(9, kyuuyokoumoku_nengappi);
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
