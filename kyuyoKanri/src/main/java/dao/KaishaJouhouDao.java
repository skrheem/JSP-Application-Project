
package dao;
// 김찬호 金燦鎬
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import model.KaishaJouhou;
import model.Shain;
import util.ObjectFormatter;

// * KaishaJouhouDao는 회사 정보를 관리하는 DAO(Data Access Object)로,
// * 회사 정보를 데이터베이스에서 조회, 수정 하는 기능을 제공합니다.

public class KaishaJouhouDao {

	public static void main(String args[]) {
		try {
	        Connection conn = ConnectionProvider.getConnection(); // 커넥션 풀에서 연결을 가져옴
	        //ArrayList<ShainKyuuyoKeisanKiroku> ShainList = getInstance().getKyuuyoKeisanList(conn, "2024-01-01", "2024-12-31", "1"); // 메서드 호출
	        KaishaJouhouDao k = new KaishaJouhouDao(conn);
	        List<KaishaJouhou> ShainList = k.getAllKaisha();
	        try {
	            System.out.println(ObjectFormatter.formatList(ShainList)); // 결과 출력
	        } catch (IllegalAccessException e) {
	            e.printStackTrace(); // 리플렉션 관련 예외 처리
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // 예외 발생 시 출력
	    }
	}
	
	private Connection connection = ConnectionProvider.getConnection();

	// * 생성자: 데이터베이스 연결 객체를 받아 DAO 인스턴스를 초기화합니다.
	// * @param connection 데이터베이스와의 연결 객체

	
	private static KaishaJouhouDao kd = new KaishaJouhouDao();
	
	public static KaishaJouhouDao getInstance() {
		return kd;
	}
//
	// * 특정 회사 ID로 회사 정보를 조회합니다.
	// * @param kaishaId 조회할 회사의 ID
	// * @return 회사 정보가 담긴 KaishaJouhou 객체, 없으면 null 반환
	public KaishaJouhou getKaishaById(Connection conn ,int kaishaId) {
		KaishaJouhou kaisha = null;
		String query = "SELECT * FROM KaishaJouhou WHERE kaisha_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, kaishaId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				kaisha = new KaishaJouhou(kaishaId, query, query, null, query, query, query, query, query, query, query,
						query, query, query, query, query);
				// ResultSet에서 데이터를 가져와 KaishaJouhou 객체에 설정
				kaisha.setKaisha_id(rs.getInt("kaisha_id"));
				kaisha.setKaisha_mei(rs.getString("kaisha_mei"));
				kaisha.setJigyousha_touroku_bango(rs.getString("jigyousha_touroku_bango"));
				kaisha.setSetsuribi(rs.getDate("setsuribi"));
				kaisha.setJigyousho_jyuusho(rs.getString("jigyousho_jyuusho"));
				kaisha.setYuubin_bango(rs.getString("yuubin_bango"));
				kaisha.setDaihyousha_yakushoku(rs.getString("daihyousha_yakushoku"));
				kaisha.setDaihyousha_mei(rs.getString("daihyousha_mei"));
				kaisha.setHoujinTourokuBango(rs.getString("houjin_touroku_bango"));
				kaisha.setHomepage(rs.getString("homepage"));
				kaisha.setDenwa_kaisha(rs.getString("denwa_kaisha"));
				kaisha.setFakkusu_bango(rs.getString("fakkusu_bango"));
				kaisha.setGyoutai(rs.getString("gyoutai"));
				kaisha.setShumoku(rs.getString("shumoku"));
				kaisha.setKaisha_image_keiro(rs.getString("kaisha_image_keiro"));
				kaisha.setInkan_image_keiro(rs.getString("inkan_image_keiro"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kaisha;
	}

	// * 모든 회사 정보를 데이터베이스에서 조회하여 리스트로 반환합니다.
	// * @return 모든 회사 정보가 담긴 KaishaJouhou 객체 리스트
	public List<KaishaJouhou> getAllKaisha() {
		List<KaishaJouhou> kaishaList = new ArrayList<>();
		String query = "SELECT * FROM KaishaJouhou";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();

			// 결과 집합(ResultSet)에서 모든 회사 정보를 반복 처리하여 리스트에 추가
			while (rs.next()) {
				KaishaJouhou kaisha = new KaishaJouhou(null, query, query, null, query, query, query, query, query,
						query, query, query, query, query, query, query);
				kaisha.setKaisha_id(rs.getInt("kaisha_id"));
				kaisha.setKaisha_mei(rs.getString("kaisha_mei"));
				kaisha.setJigyousha_touroku_bango(rs.getString("jigyousha_touroku_bango"));
				kaisha.setSetsuribi(rs.getDate("setsuribi"));
				kaisha.setJigyousho_jyuusho(rs.getString("jigyousho_jyuusho"));
				kaisha.setYuubin_bango(rs.getString("yuubin_bango"));
				kaisha.setDaihyousha_yakushoku(rs.getString("daihyousha_yakushoku"));
				kaisha.setDaihyousha_mei(rs.getString("daihyousha_mei"));
				kaisha.setHoujinTourokuBango(rs.getString("houjin_touroku_bango"));
				kaisha.setHomepage(rs.getString("homepage"));
				kaisha.setDenwa_kaisha(rs.getString("denwa_kaisha"));
				kaisha.setFakkusu_bango(rs.getString("fakkusu_bango"));
				kaisha.setGyoutai(rs.getString("gyoutai"));
				kaisha.setShumoku(rs.getString("shumoku"));
				kaisha.setKaisha_image_keiro(rs.getString("kaisha_image_keiro"));
				kaisha.setInkan_image_keiro(rs.getString("inkan_image_keiro"));

				kaishaList.add(kaisha);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kaishaList;
	}


	// * 주어진 회사 정보를 데이터베이스에서 업데이트합니다.
	// * @param kaisha 업데이트할 회사 정보가 담긴 KaishaJouhou 객체
	public void updateKaisha(KaishaJouhou kaisha) {
		String query = "UPDATE KaishaJouhou SET kaisha_mei = ?, jigyousha_touroku_bango = ?, setsuribi = ?, "
				+ "jigyousho_jyuusho = ?, yuubin_bango = ?, daihyousha_yakushoku = ?, daihyousha_mei = ?, "
				+ "houjin_touroku_bango = ?, homepage = ?, denwa_kaisha = ?, fakkusu_bango = ?, gyoutai = ?, "
				+ "shumoku = ?, kaisha_image_keiro = ?, inkan_image_keiro = ? WHERE kaisha_id = ?";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, kaisha.getKaisha_mei());
			ps.setString(2, kaisha.getJigyousha_touroku_bango());

			if (kaisha.getSetsuribi() != null) {
				ps.setDate(3, new java.sql.Date(kaisha.getSetsuribi().getTime()));
			} else {
				ps.setNull(3, java.sql.Types.DATE);
			}

			ps.setString(4, kaisha.getJigyousho_jyuusho());
			ps.setString(5, kaisha.getYuubin_bango());
			ps.setString(6, kaisha.getDaihyousha_yakushoku());
			ps.setString(7, kaisha.getDaihyousha_mei());
			ps.setString(8, (String) kaisha.getHoujinTourokuBango());
			ps.setString(9, kaisha.getHomepage());
			ps.setString(10, kaisha.getDenwa_kaisha());
			ps.setString(11, kaisha.getFakkusu_bango());
			ps.setString(12, kaisha.getGyoutai());
			ps.setString(13, kaisha.getShumoku());
			ps.setString(14, kaisha.getKaisha_image_keiro());
			ps.setString(15, kaisha.getInkan_image_keiro());
			ps.setInt(16, kaisha.getKaisha_id());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
