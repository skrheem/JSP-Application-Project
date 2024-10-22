package dao;
// 김찬호 金燦鎬
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Tantousha;

// * TantoushaDao 클래스는 담당자 정보를 관리하는 DAO(Data Access Object)로,
// * 담당자 정보를 데이터베이스에서 조회, 업데이트 하는 기능을 제공합니다.

public class TantoushaDao {

    private Connection connection;

    // * 생성자: 데이터베이스 연결 객체를 받아 DAO 인스턴스를 초기화합니다. 
    // * @param connection 데이터베이스와의 연결 객체
    public TantoushaDao(Connection connection) {
        this.connection = connection;
    }

    // * 특정 담당자 ID로 담당자 정보를 조회합니다. 
    // * @param tantoushaId 조회할 담당자의 ID
    // * @return 담당자 정보가 담긴 Tantousha 객체, 없으면 null 반환
    public Tantousha getTantoushaById(int tantoushaId) {
        Tantousha tantousha = null;
        String query = "SELECT * FROM Tantousha WHERE tantousha_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, tantoushaId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // ResultSet에서 데이터를 가져와 Tantousha 객체에 설정
                tantousha = new Tantousha(tantoushaId, query, query, query, query, tantoushaId, tantoushaId, tantoushaId);
                tantousha.setTantousha_id(rs.getInt("tantousha_id"));
                tantousha.setNamae_kana(rs.getString("namae_kana"));
                tantousha.setDenwa_tantousha(rs.getString("denwa_tantousha"));
                tantousha.setDenwa_keitai(rs.getString("denwa_keitai"));
                tantousha.setMeeru(rs.getString("meeru"));
                tantousha.setKaisha_id(rs.getInt("kaisha_id"));
                tantousha.setBusho_id((Integer) rs.getObject("busho_id")); // null 처리
                tantousha.setYakushoku_id((Integer) rs.getObject("yakushoku_id")); // null 처리
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tantousha;
    }

    // * 모든 담당자 정보를 데이터베이스에서 조회하여 리스트로 반환합니다.
    // * @return 모든 담당자 정보가 담긴 Tantousha 객체 리스트
    public List<Tantousha> getAllTantousha() {
        List<Tantousha> tantoushaList = new ArrayList<>();
        String query = "SELECT * FROM Tantousha";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            // 결과 집합에서 모든 담당자 정보를 반복 처리하여 리스트에 추가
            while (rs.next()) {
                Tantousha tantousha = new Tantousha(null, query, query, query, query, null, null, null);
                tantousha.setTantousha_id(rs.getInt("tantousha_id"));
                tantousha.setNamae_kana(rs.getString("namae_kana"));
                tantousha.setDenwa_tantousha(rs.getString("denwa_tantousha"));
                tantousha.setDenwa_keitai(rs.getString("denwa_keitai"));
                tantousha.setMeeru(rs.getString("meeru"));
                tantousha.setKaisha_id(rs.getInt("kaisha_id"));
                tantousha.setBusho_id((Integer) rs.getObject("busho_id")); // null 처리
                tantousha.setYakushoku_id((Integer) rs.getObject("yakushoku_id")); // null 처리

                tantoushaList.add(tantousha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tantoushaList;
    }

    // * 주어진 Tantousha 객체를 기반으로 담당자 정보를 업데이트합니다.
    // * @param tantousha 업데이트할 담당자 정보가 담긴 Tantousha 객체
    public void updateTantousha(Tantousha tantousha) {
        String query = "UPDATE Tantousha SET namae_kana = ?, denwa_tantousha = ?, denwa_keitai = ?, meeru = ?, "
                + "kaisha_id = ?, busho_id = ?, yakushoku_id = ? WHERE tantousha_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, tantousha.getNamae_kana());
            ps.setString(2, tantousha.getDenwa_tantousha());
            ps.setString(3, tantousha.getDenwa_keitai());
            ps.setString(4, tantousha.getMeeru());
            ps.setInt(5, tantousha.getKaisha_id());

            // busho_id와 yakushoku_id의 null 처리
            if (tantousha.getBusho_id() != null) {
                ps.setInt(6, tantousha.getBusho_id());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            if (tantousha.getYakushoku_id() != null) {
                ps.setInt(7, tantousha.getYakushoku_id());
            } else {
                ps.setNull(7, java.sql.Types.INTEGER);
            }

            ps.setInt(8, tantousha.getTantousha_id());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
