package dao;

//김찬호 金燦鎬
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import model.KaishaJouhou;

public class KaishaJouhouDao {

	public static void main(String args[]) {
		KaishaJouhouDao kj = KaishaJouhouDao.getInstance();
		try {
			Connection conn = ConnectionProvider.getConnection();
			KaishaJouhou k = new KaishaJouhou("예스픔", "120-86-50680", "2000-01-03", "서울특별시 성동구 성수동1가 14-18 코오롱디지털3차 901호", "1588-2390", "대표이사" , "이응열", "110111-275401", "www.yesform.com", "010-2222-3333", "02-2117-2552", "사업서비스업", "온라인정보제공", null, null);
//			kj.insertKaisha(conn, k);
			kj.updateKaisha(conn, k, 1);
			System.out.println(kj.getKaishaById(conn, 1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static KaishaJouhouDao instance = new KaishaJouhouDao();

	public static KaishaJouhouDao getInstance() {
		return instance;
	}

	// 회사 정보를 데이터베이스에 입력하는 메서드
	public void insertKaisha(Connection conn, KaishaJouhou kaisha) {
		String query = "INSERT INTO KaishaJouhou (kaisha_id, kaisha_mei, jigyousha_touroku_bango, setsuribi, jigyousho_jyuusho, "
				+ "yuubin_bango, daihyousha_yakushoku, daihyousha_mei, houjin_touroku_bango, homepage, denwa_kaisha, "
				+ "fakkusu_bango, gyoutai, shumoku, kaisha_image_keiro, inkan_image_keiro) "
				+ "VALUES (KaishaJouhou_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(query);
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
			ps.setString(8, kaisha.getHoujin_touroku_bango());
			ps.setString(9, kaisha.getHomepage());
			ps.setString(10, kaisha.getDenwa_kaisha());
			ps.setString(11, kaisha.getFakkusu_bango());
			ps.setString(12, kaisha.getGyoutai());
			ps.setString(13, kaisha.getShumoku());
			ps.setString(14, kaisha.getKaisha_image_keiro());
			ps.setString(15, kaisha.getInkan_image_keiro());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
	}

	// 특정 회사 ID로 회사 정보를 조회하는 메서드
	public KaishaJouhou getKaishaById(Connection conn, Integer kaishaId) {
		KaishaJouhou kaisha = new KaishaJouhou();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM KaishaJouhou WHERE Kaisha_id = ?";

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, kaishaId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				kaisha.setKaisha_id(rs.getInt("kaisha_id"));
				kaisha.setKaisha_mei(rs.getString("kaisha_mei"));
				kaisha.setJigyousha_touroku_bango(rs.getString("jigyousha_touroku_bango"));
				kaisha.setSetsuribi(rs.getDate("setsuribi"));
				kaisha.setJigyousho_jyuusho(rs.getString("jigyousho_jyuusho"));
				kaisha.setYuubin_bango(rs.getString("yuubin_bango"));
				kaisha.setDaihyousha_yakushoku(rs.getString("daihyousha_yakushoku"));
				kaisha.setDaihyousha_mei(rs.getString("daihyousha_mei"));
				kaisha.setHoujin_touroku_bango(rs.getString("houjin_touroku_bango"));
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
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
		return kaisha;
		
	}

	// 주어진 회사 정보를 데이터베이스에서 업데이트하는 메서드
	public void updateKaisha(Connection conn, KaishaJouhou kaisha, Integer KaishaId) {
		String query = "UPDATE KaishaJouhou SET kaisha_mei = ?, jigyousha_touroku_bango = ?, setsuribi = ?, "
				+ "jigyousho_jyuusho = ?, yuubin_bango = ?, daihyousha_yakushoku = ?, daihyousha_mei = ?, "
				+ "houjin_touroku_bango = ?, homepage = ?, denwa_kaisha = ?, fakkusu_bango = ?, gyoutai = ?, "
				+ "shumoku = ?, kaisha_image_keiro = ?, inkan_image_keiro = ? WHERE kaisha_id = ?";

		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(query);
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
			ps.setString(8, kaisha.getHoujin_touroku_bango());
			ps.setString(9, kaisha.getHomepage());
			ps.setString(10, kaisha.getDenwa_kaisha());
			ps.setString(11, kaisha.getFakkusu_bango());
			ps.setString(12, kaisha.getGyoutai());
			ps.setString(13, kaisha.getShumoku());
			ps.setString(14, kaisha.getKaisha_image_keiro());
			ps.setString(15, kaisha.getInkan_image_keiro());
			ps.setInt(16, KaishaId);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
	}
}
