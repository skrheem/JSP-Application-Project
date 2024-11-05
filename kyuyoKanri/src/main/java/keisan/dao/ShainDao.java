package keisan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.JdbcUtil;
import keisan.model.Shain;

// 임세규 林世圭
// 사원과 관련된 정보를 가져오는 클래스
// 社員に関する情報を取得するクラス
public class ShainDao {
    
    private static ShainDao instance = new ShainDao();
    
    public static ShainDao getInstance() {
        return instance;
    }
    
    // 임세규 林世圭
    // 급여입력/관리 페이지의 "신규추가" 버튼을 눌렀을 때 출력할 사원 리스트, 갑근세 타입에 따라 가져오는 사원의 정보가 다르다.
    // 給与入力・管理ページの「新規追加」ボタンを押した時に出力する社員リスト、甲欽税タイプによって取得する社員情報が異なる。
    public ArrayList<Shain> getShainSentakuList(Connection conn, String koukinzei) {
        ArrayList<Shain> shainList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "";
        if(koukinzei.equals("0")) {
            query = "SELECT   s.shain_id, "
                    + "       s.kubun, "
                    + "       s.namae_kana, "
                    + "       b.busho_mei, "
                    + "       y.yakushoku_mei, "
                    + "       s.jyoutai, "
                    + "       sk.kihonkyuu, "
                    + "       k.koukinzei_kubun "
                    + "FROM Shain s "
                    + "JOIN Busho b ON s.busho_id = b.busho_id "
                    + "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id "
                    + "JOIN koukinzei k ON s.shain_id = k.shain_id "
                    + "JOIN shainkihonkyuu sk ON s.shain_id = sk.shain_id "
                    + "WHERE k.koukinzei_kubun = '勤労所得者'";
        } else if(koukinzei.equals("1")) {
            query = "SELECT   s.shain_id, "
                    + "       s.kubun, "
                    + "       s.namae_kana, "
                    + "       b.busho_mei, "
                    + "       y.yakushoku_mei, "
                    + "       s.jyoutai, "
                    + "       sk.kihonkyuu, "
                    + "       k.koukinzei_kubun "
                    + "FROM Shain s "
                    + "JOIN Busho b ON s.busho_id = b.busho_id "
                    + "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id "
                    + "JOIN koukinzei k ON s.shain_id = k.shain_id "
                    + "JOIN shainkihonkyuu sk ON s.shain_id = sk.shain_id "
                    + "WHERE k.koukinzei_kubun != '勤労所得者' AND k.koukinzei_kubun != '日雇い'";
        } else if(koukinzei.equals("2")) {
            query = "SELECT   s.shain_id, "
                    + "       s.kubun, "
                    + "       s.namae_kana, "
                    + "       b.busho_mei, "
                    + "       y.yakushoku_mei, "
                    + "       s.jyoutai, "
                    + "       sk.kihonkyuu, "
                    + "       k.koukinzei_kubun "
                    + "FROM Shain s "
                    + "JOIN Busho b ON s.busho_id = b.busho_id "
                    + "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id "
                    + "JOIN koukinzei k ON s.shain_id = k.shain_id "
                    + "JOIN shainkihonkyuu sk ON s.shain_id = sk.shain_id "
                    + "WHERE k.koukinzei_kubun = '日雇い'";
        }
         
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()) {
                shainList.add(new Shain(rs.getInt("shain_id"), rs.getString("kubun"), rs.getString("namae_kana"), rs.getString("busho_mei"), rs.getString("yakushoku_mei"), rs.getString("jyoutai"), rs.getInt("kihonkyuu"), rs.getString("koukinzei_kubun")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
            JdbcUtil.close(rs);
        }
        return shainList;
    }
}
