package dao;
//김찬호 金燦鎬
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import model.Tantousha;

public class TantoushaDao {

	public static void main(String args[]) {
		TantoushaDao kj = TantoushaDao.getInstance();
		try {
			Connection conn = ConnectionProvider.getConnection();

			Tantousha k = new Tantousha("김동현", "기획 전략팀", "과장", " 010-1588-1588", "010-1588-2390", "kim_2016@payzon.co.kr");
			kj.insertTantousha(conn, k);
			kj.updateTantousha(conn, k, 1);
			System.out.println(kj.getTantoushaById(conn, 1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static TantoushaDao instance = new TantoushaDao();

	public static TantoushaDao getInstance() {
		return instance;
	}

    // 특정 담당자 ID로 담당자 정보를 조회하는 메서드
    public Tantousha getTantoushaById(Connection conn, int tantoushaId) {
        Tantousha tantousha = new Tantousha();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Tantousha WHERE tantousha_id = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, tantoushaId);
            rs = ps.executeQuery();

            if (rs.next()) {
                tantousha.setTantousha_id(rs.getInt("tantousha_id"));
                tantousha.setNamae_kana(rs.getString("namae_kana"));
                tantousha.setDenwa_tantousha(rs.getString("denwa_tantousha"));
                tantousha.setDenwa_keitai(rs.getString("denwa_keitai"));
                tantousha.setMeeru(rs.getString("meeru"));
                tantousha.setKaisha_id(rs.getInt("kaisha_id"));
                tantousha.setBusho_id((Integer) rs.getObject("busho_id"));
                tantousha.setYakushoku_id((Integer) rs.getObject("yakushoku_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(ps);
        }

        return tantousha;
    }

    // 담당자 정보를 데이터베이스에 입력하는 메서드
    public void insertTantousha(Connection conn, Tantousha tantousha) {
        String query = "INSERT INTO Tantousha (namae_kana, denwa_tantousha, denwa_keitai, meeru, "
                + "kaisha_id, busho_id, yakushoku_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, tantousha.getNamae_kana());
            ps.setString(2, tantousha.getDenwa_tantousha());
            ps.setString(3, tantousha.getDenwa_keitai());
            ps.setString(4, tantousha.getMeeru());
            ps.setInt(5, tantousha.getKaisha_id());

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

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
        }
    }

    // 주어진 Tantousha 객체를 기반으로 담당자 정보를 업데이트하는 메서드
    public void updateTantousha(Connection conn, Tantousha tantousha, Integer tantoushaId) {
        String query = "UPDATE Tantousha SET namae_kana = ?, denwa_tantousha = ?, denwa_keitai = ?, meeru = ?, "
                + "kaisha_id = ?, busho_id = ?, yakushoku_id = ? WHERE tantousha_id = ?";

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, tantousha.getNamae_kana());
            ps.setString(2, tantousha.getDenwa_tantousha());
            ps.setString(3, tantousha.getDenwa_keitai());
            ps.setString(4, tantousha.getMeeru());
            ps.setInt(5, tantousha.getKaisha_id());

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
        } finally {
            JdbcUtil.close(ps);
        }
    }
}
