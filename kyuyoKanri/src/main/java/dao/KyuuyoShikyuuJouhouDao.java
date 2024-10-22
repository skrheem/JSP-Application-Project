package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import model.KyuuyoShikyuuJouhou;

public class KyuuyoShikyuuJouhouDao {

	private Connection connection;

	// * 생성자: 데이터베이스 연결 객체를 받아 DAO 인스턴스를 초기화합니다.
	// * @param connection 데이터베이스와의 연결 객체
	public KyuuyoShikyuuJouhouDao(Connection connection) {
		this.connection = connection;
	}

	public KyuuyoShikyuuJouhou getShikyuuJouhouByKaishaId(int kaishaId) {
		KyuuyoShikyuuJouhou shikyuuJouhou = null;

		String query = "SELECT * FROM KyuuyoShikyuuJouhou WHERE Kaisha_Id = ?";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, kaishaId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				shikyuuJouhou = new KyuuyoShikyuuJouhou(kaishaId, null, null, null, query, query, query);
				shikyuuJouhou.setKaisha_id(rs.getInt("Kaisha_Id"));
				shikyuuJouhou.setkyuuyoSanteiKaishi(rs.getDate("kyuuyoSanteiKaishi"));
				shikyuuJouhou.setKyuuyoSanteiShuuryou(rs.getDate("KyuuyoSanteiShuuryou"));
				shikyuuJouhou.setKyuuyoShikyuu_bi(rs.getDate("KyuuyoShikyuu_bi"));
				shikyuuJouhou.setKinyuuKikan(rs.getString("KinyuuKikan"));
				shikyuuJouhou.setKouza_bangou(rs.getString("Kouza_bangou"));
				shikyuuJouhou.setYokinShaMeigi(rs.getString("YokinShaMeigi"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return shikyuuJouhou;
	}

	public void updateKyuuyoShikyuuJouhou(KyuuyoShikyuuJouhou shikyuuJouhou) {
		String query = "UPDATE KyuuyoShikyuuJouhou SET KyuuyoSanteiKaishi = ?, KyuuyoSanteiShuuryou = ?, KyuuyoShikyuu_bi = ?, KinyuuKikan = ?, Kouza_bangou = ?, YokinShaMeigi = ?, "
				+ "kaisha_id = ? ";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 형식 지정

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			if (shikyuuJouhou.getkyuuyoSanteiKaishi() != null) {
				ps.setString(1, dateFormat.format(shikyuuJouhou.getkyuuyoSanteiKaishi()));
			} else {
				ps.setNull(1, java.sql.Types.VARCHAR);
			}

			if (shikyuuJouhou.getKyuuyoSanteiShuuryou() != null) {
				ps.setString(2, dateFormat.format(shikyuuJouhou.getKyuuyoSanteiShuuryou()));
			} else {
				ps.setNull(2, java.sql.Types.VARCHAR);
			}

			if (shikyuuJouhou.getKyuuyoShikyuu_bi() != null) {
				ps.setString(3, dateFormat.format(shikyuuJouhou.getKyuuyoShikyuu_bi()));
			} else {
				ps.setNull(3, java.sql.Types.VARCHAR);
			}
			ps.setString(4, shikyuuJouhou.getKinyuuKikan());
			ps.setString(5, shikyuuJouhou.getKouza_bangou());
			ps.setString(6, shikyuuJouhou.getYokinShaMeigi());
			ps.setInt(7, shikyuuJouhou.getKaisha_id());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
