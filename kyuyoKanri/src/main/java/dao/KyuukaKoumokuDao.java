package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import oracle.sql.DATE;

import model.KyuukaKoumoku;

public class KyuukaKoumokuDao {

	private Connection connection;

	// * 생성자: 데이터베이스 연결 객체를 받아 DAO 인스턴스를 초기화합니다.
	// * @param connection 데이터베이스와의 연결 객체
	public KyuukaKoumokuDao(Connection connection) {
		this.connection = connection;
	}

	public KyuukaKoumoku getKyuukaKoumokuByid(String kyuukaShurui, Date tekiyouKaishi, Date tekiyouShuuryou, char shiyouUmu) {
		KyuukaKoumoku kyuukaKoumoku = null;

		String query = "INSERT INTO KyuukaKoumoku (kyuukaKoumoku_id, kyuukaShurui, tekiyouKaishi, tekiyouShuuryou, shiyouUmu) "
				+ "VALUES (KyuukaKoumoku_sequence.nextVal, ?, ?, ?, ?)";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, kyuukaShurui);
			if (tekiyouKaishi != null) {
			    java.sql.Date sqlTekiyouKaishi = new java.sql.Date(tekiyouKaishi.getTime());
			    ps.setDate(2, sqlTekiyouKaishi);
			} else {
			    ps.setNull(2, java.sql.Types.DATE);
			}
			if (tekiyouKaishi != null) {
			    java.sql.Date sqlTekiyouShuuryou = new java.sql.Date(tekiyouShuuryou.getTime());
			    ps.setDate(3, sqlTekiyouShuuryou);
			} else {
			    ps.setNull(3, java.sql.Types.DATE);
			}
			String shiyou = shiyouUmu + "";
			ps.setString(4, shiyou);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				kyuukaKoumoku = new kyuukaKoumoku(KyuukaKoumoku_id, query, null, null, query);
				kyuukaKoumoku.setKyuukaKoumoku_id(rs.getInt("kyuukaKoumoku_id"));
				kyuukaKoumoku.setKyuukaShurui(rs.getString("kyuukaShurui"));
				kyuukaKoumoku.setTekiyouKaishi(rs.getDate("tekiyouKaishi"));
				kyuukaKoumoku.setTekiyouShuuryou(rs.getDate("tekiyouShuuryou"));
				kyuukaKoumoku.setShiyouUmu(rs.getChar("shiyouUmu"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kyuukaKoumoku;
	}
	
	public void updateKyuukaKoumoku(KyuukaKoumoku kyuukaKoumoku) {
        String query = "UPDATE Tantousha SET namae_kana = ?, denwa_tantousha = ?, denwa_keitai = ?, meeru = ?, "
                + "kaisha_id = ?, busho_id = ?, yakushoku_id = ? WHERE tantousha_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, kyuukaKoumoku.getNamae_kana());
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
