package dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import model.Shain;

public class ShainDao {

	// 총 사원수 카운트
	public int getShainCountAll(Connection conn) {
		String query = "SELECT COUNT(*) FROM Shain";
		try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1); // 첫 번째 컬럼의 값을 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 사원 상태별 카운트 (재직, 퇴직)
	public int[] getShainJyoutaiCNT(Connection conn) throws SQLException {
		String query = "SELECT " + "COUNT(CASE WHEN jyoutai = '在職' THEN 1 END) AS zaishoku_count, "
				+ "COUNT(CASE WHEN jyoutai = '退職' THEN 1 END) AS taishoku_count " + "FROM Shain";

		try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				int zaishokuCount = rs.getInt("zaishoku_count");
				int taishokuCount = rs.getInt("taishoku_count");

				return new int[] { zaishokuCount, taishokuCount };
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		// 결과가 없을 경우 0을 반환
		return new int[] { 0, 0 };
	}

	// 사원 구분별 카운트 (정규직, 계약직, 임시직, 파견직, 위촉직, 일용직)
	public int[] getShainKubunCNT(Connection conn) throws SQLException {
		String query = "SELECT " + "COUNT(CASE WHEN jyoutai = '正規職' THEN 1 END) AS seiki_count, "
				+ "COUNT(CASE WHEN jyoutai = '契約職' THEN 1 END) AS keiyaku_count, "
				+ "COUNT(CASE WHEN jyoutai = '臨時職' THEN 1 END) AS ranji_count, "
				+ "COUNT(CASE WHEN jyoutai = '派遣職' THEN 1 END) AS haken_count, "
				+ "COUNT(CASE WHEN jyoutai = '委嘱職' THEN 1 END) AS ishoku_count, "
				+ "COUNT(CASE WHEN jyoutai = '日雇い職' THEN 1 END) AS hiyatoi_count " + "FROM Shain";

		try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				int seikiCount = rs.getInt("seiki_count");
				int keiyakuCount = rs.getInt("keiyaku_count");
				int ranjiCount = rs.getInt("ranji_count");
				int hakenCount = rs.getInt("haken_count");
				int ishokuCount = rs.getInt("ishoku_count");
				int hiyatoiCount = rs.getInt("hiyatoi_count");

				return new int[] { seikiCount, keiyakuCount, ranjiCount, hakenCount, ishokuCount, hiyatoiCount };
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		// 결과가 없을 경우 0을 반환
		return new int[] { 0, 0 };
	}

	// ------------------<리스트에 표시되는 사원 정보>---------------------

	// 사원 리스트
	public ArrayList<Shain> getAllShains(Connection conn) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT s.kubun, s.nyuusha_nengappi, s.shain_id, s.namae_kana, s.namae_eigo, b.busho_mei, "
					+ "y.yakushoku_mei, s.jumin_bangou, s.kokuseki, s.juusho, s.denwa_uchi, s.denwa_keitai, "
					+ "s.meeru, s.SNS, s.taisha_nengappi, s.jyoutai, sk.kouza_bangou " + "FROM Shain s "
					+ "LEFT JOIN ShainKihonKyuu sk ON s.shain_id = sk.shain_id"
					+ "JOIN busho b ON s.busho_id = b.busho_id" + "JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id";

			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			ArrayList<Shain> shainList = new ArrayList<>(); // 사원 정보를 담을 리스트

			while (rs.next()) {
				Shain shain = new Shain(rs.getInt("shain_id"), rs.getString("namae_kana"), rs.getString("namae_eigo"),
						rs.getDate("nyuusha_nengappi"), rs.getDate("taisha_nengappi"), rs.getString("busho_mei"),
						rs.getString("yakushoku_mei"), rs.getString("kokuseki"), rs.getString("jumin_bangou"),
						rs.getString("juusho"), rs.getString("denwa_uchi"), rs.getString("denwa_keitai"),
						rs.getString("meeru"), rs.getString("SNS"), rs.getString("kubun"), rs.getString("jyoutai"),
						rs.getString("kouza_bangou"));
				shainList.add(shain); // 리스트에 추가
			}
			return shainList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	// ------------------<검색 기능>-----------------

	// 김현서 金賢徐
	// 체크박스 선택에 따른 검색 기능을 제공
	public ArrayList<Shain> searchShainInfo(Connection conn, String searchType, String searchValue)
			throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			StringBuilder query = new StringBuilder(
					"SELECT s.shain_id, s.namae_kana, s.namae_eigo, s.nyuusha_nengappi, s.taisha_nengappi, "
							+ "b.busho_mei, y.yakushoku_mei, s.jumin_bangou, s.kokuseki, s.juusho, "
							+ "s.denwa_uchi, s.denwa_keitai, s.meeru, s.SNS, s.kubun, s.jyoutai, sk.kouza_bangou "
							+ "FROM Shain s " + "JOIN Busho b ON s.busho_id = b.busho_id "
							+ "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id "
							+ "LEFT JOIN ShainKihonKyuu sk ON s.shain_id = sk.shain_id " + "WHERE 1=1" // 물음표(?)처럼 유동적으로
																										// 값이 바뀌는 쿼리문
			);

			// 검색 타입에 따라 쿼리의 WHERE 조건을 추가
			if (searchType != null && searchValue != null && !searchValue.isEmpty()) {
				switch (searchType) {
				case "shainMei":
					query.append(" AND s.namae_kana = ?");
					break;
				case "bushoMei":
					query.append(" AND b.busho_mei = ?");
					break;
				case "yakushokuMei":
					query.append(" AND y.yakushoku_mei = ?");
					break;
				case "shainId":
					query.append(" AND s.shain_id = ?");
					break;
				default:
					break;
				}
			}

			stmt = conn.prepareStatement(query.toString());

			// PreparedStatement의 ? 부분에 값을 설정
			if (searchType != null && searchValue != null && !searchValue.isEmpty()) {
				if (searchType.equals("shainId")) {
					stmt.setInt(1, Integer.parseInt(searchValue)); // shain_id는 숫자
				} else {
					stmt.setString(1, searchValue); // 그 외 검색 값은 문자열
				}
			}

			rs = stmt.executeQuery(); // 쿼리 실행

			ArrayList<Shain> shainList = new ArrayList<>(); // 결과를 담을 리스트

			// ResultSet을 돌면서 검색 결과를 리스트에 추가
			while (rs.next()) {
				Shain shain = new Shain(rs.getInt("shain_id"), rs.getString("namae_kana"), rs.getString("namae_eigo"),
						rs.getDate("nyuusha_nengappi"), rs.getDate("taisha_nengappi"), rs.getString("busho_mei"),
						rs.getString("yakushoku_mei"), rs.getString("kokuseki"), rs.getString("jumin_bangou"),
						rs.getString("juusho"), rs.getString("denwa_uchi"), rs.getString("denwa_keitai"),
						rs.getString("meeru"), rs.getString("SNS"), rs.getString("kubun"), rs.getString("jyoutai"),
						rs.getString("kouza_bangou") // String 타입으로 설정
				);
				shainList.add(shain); // 리스트에 추가
			}

			return shainList; // 검색된 결과 리스트 반환
		} finally {
			JdbcUtil.close(rs); // ResultSet 닫기
			JdbcUtil.close(stmt); // PreparedStatement 닫기
		}
	}

	// -------------------<선택박스>-------------------

	// 김현서 金賢徐
	// 상태(jyoutai)별로 리스트 표시
	// 状態(jyoutai)別にリストを表示する。
	public static ArrayList<Shain> getShainbyJyoutai(Connection conn, String jyoutai) throws SQLException {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT s.kubun, s.nyuusha_nengappi, s.shain_id, s.namae_kana, s.namae_eigo, b.busho_mei, "
	                + "y.yakushoku_mei, s.jumin_bangou, s.kokuseki, s.juusho, s.denwa_uchi, s.denwa_keitai, "
	                + "s.meeru, s.SNS, s.taisha_nengappi, s.jyoutai, sk.kouza_bangou "
	                + "FROM Shain s "
	                + "LEFT JOIN ShainKihonKyuu sk ON s.shain_id = sk.shain_id "
	                + "JOIN busho b ON s.busho_id = b.busho_id "
	                + "JOIN yakushoku y ON s.yakushoku_id = y.yakushoku_id "
	                + "WHERE s.jyoutai = ?";

	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, jyoutai);
	        rs = pstmt.executeQuery();

	        ArrayList<Shain> shainList = new ArrayList<>();
	        while (rs.next()) {
	            Shain shain = new Shain(rs.getInt("shain_id"), rs.getString("namae_kana"),
	                    rs.getString("namae_eigo"), rs.getDate("nyuusha_nengappi"), rs.getDate("taisha_nengappi"),
	                    rs.getString("busho_mei"), rs.getString("yakushoku_mei"), rs.getString("kokuseki"),
	                    rs.getString("jumin_bangou"), rs.getString("juusho"), rs.getString("denwa_uchi"),
	                    rs.getString("denwa_keitai"), rs.getString("meeru"), rs.getString("SNS"),
	                    rs.getString("kubun"), rs.getString("jyoutai"), rs.getString("kouza_bangou"));
	            shainList.add(shain);
	        }
	        return shainList;
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(pstmt);
	    }
	}
	
	
	// 김현서 金賢徐
	// 고용형태(kubun)별로 리스트 표시
	// 雇用状態(kubun)別にリストを表示する。
	public static ArrayList<Shain> getShainbyKubun(Connection conn, String kubun) throws SQLException {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT s.kubun, s.nyuusha_nengappi, s.shain_id, s.namae_kana, s.namae_eigo, b.busho_mei, "
	                + "y.yakushoku_mei, s.jumin_bangou, s.kokuseki, s.juusho, s.denwa_uchi, s.denwa_keitai, "
	                + "s.meeru, s.SNS, s.taisha_nengappi, s.jyoutai, sk.kouza_bangou "
	                + "FROM Shain s "
	                + "LEFT JOIN ShainKihonKyuu sk ON s.shain_id = sk.shain_id "
	                + "JOIN busho b ON s.busho_id = b.busho_id "
	                + "JOIN yakushoku y ON s.shain_id = y.shain_id "
	                + "WHERE s.kubun = ?";

	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, kubun);
	        rs = pstmt.executeQuery();

	        ArrayList<Shain> shainList = new ArrayList<>();
	        while (rs.next()) {
	            Shain shain = new Shain(rs.getInt("shain_id"), rs.getString("namae_kana"),
	                    rs.getString("namae_eigo"), rs.getDate("nyuusha_nengappi"), rs.getDate("taisha_nengappi"),
	                    rs.getString("busho_mei"), rs.getString("yakushoku_mei"), rs.getString("kokuseki"),
	                    rs.getString("jumin_bangou"), rs.getString("juusho"), rs.getString("denwa_uchi"),
	                    rs.getString("denwa_keitai"), rs.getString("meeru"), rs.getString("SNS"),
	                    rs.getString("kubun"), rs.getString("jyoutai"), rs.getString("kouza_bangou"));
	            shainList.add(shain);
	        }
	        return shainList;
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(pstmt);
	    }
	}
	

	// 김현서 金賢徐
	// 한 페이지에 표시되는 리스트의 수를 정할 수 있는 메서드 (10,30, 50, 100개씩 표시 가능)
	// 一ページに表示されるリストの数を指定することができるメソッドです・
	public ArrayList<Shain> getShainListNumber(Connection conn, int pageNumber, int itemsPerPage) throws SQLException {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT s.shain_id, s.namae_kana, s.namae_eigo, b.busho_mei, y.yakushoku_mei, "
	                + "s.jumin_bangou, s.kokuseki, s.juusho, s.denwa_uchi, s.denwa_keitai, s.meeru, s.SNS, "
	                + "s.jyoutai, sk.kouza_bangou "
	                + "FROM Shain s "
	                + "LEFT JOIN ShainKihonKyuu sk ON s.shain_id = sk.shain_id "
	                + "JOIN busho b ON s.busho_id = b.busho_id "
	                + "JOIN yakushoku y ON s.shain_id = y.shain_id "
	                + "ORDER BY s.shain_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	        pstmt = conn.prepareStatement(query);
	        int offset = (pageNumber - 1) * itemsPerPage;
	        pstmt.setInt(1, offset);
	        pstmt.setInt(2, itemsPerPage);
	        rs = pstmt.executeQuery();

	        ArrayList<Shain> shainList = new ArrayList<>();
	        while (rs.next()) {
	            Shain shain = new Shain(rs.getInt("shain_id"), rs.getString("namae_kana"),
	                    rs.getString("namae_eigo"), rs.getDate("nyuusha_nengappi"), rs.getDate("taisha_nengappi"),
	                    rs.getString("busho_mei"), rs.getString("yakushoku_mei"), rs.getString("kokuseki"),
	                    rs.getString("jumin_bangou"), rs.getString("juusho"), rs.getString("denwa_uchi"),
	                    rs.getString("denwa_keitai"), rs.getString("meeru"), rs.getString("SNS"),
	                    rs.getString("kubun"), rs.getString("jyoutai"), rs.getString("kouza_bangou"));
	            shainList.add(shain);
	        }
	        return shainList;
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(pstmt);
	    }
	}
	
	

	// 김현서 金現徐
	// 리스트 수를 설정해서 해당 페이지에 맞는 수만큼 사원 리스트를 가져오는 메소드
	// リストの件数を設定し、該当ページに適切な数の社員リストを表示するメソッドです。
	public ArrayList<Shain> getShainListByPage(Connection conn, int pageNumber, int itemsPerPage) throws SQLException {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT s.shain_id, s.namae_kana, s.namae_eigo, b.busho_mei, y.yakushoku_mei, "
	                + "s.jumin_bangou, s.kokuseki, s.juusho, s.denwa_uchi, s.denwa_keitai, s.meeru, s.SNS, "
	                + "s.jyoutai, sk.kouza_bangou "
	                + "FROM Shain s "
	                + "LEFT JOIN ShainKihonKyuu sk ON s.shain_id = sk.shain_id "
	                + "JOIN Busho b ON s.busho_id = b.busho_id "
	                + "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id "
	                + "ORDER BY s.shain_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	        pstmt = conn.prepareStatement(query);
	        int offset = (pageNumber - 1) * itemsPerPage;
	        pstmt.setInt(1, offset);
	        pstmt.setInt(2, itemsPerPage);
	        rs = pstmt.executeQuery();

	        ArrayList<Shain> shainList = new ArrayList<>();
	        while (rs.next()) {
	            Shain shain = new Shain(rs.getInt("shain_id"), rs.getString("namae_kana"),
	                    rs.getString("namae_eigo"), rs.getDate("nyuusha_nengappi"), rs.getDate("taisha_nengappi"),
	                    rs.getString("busho_mei"), rs.getString("yakushoku_mei"), rs.getString("kokuseki"),
	                    rs.getString("jumin_bangou"), rs.getString("juusho"), rs.getString("denwa_uchi"),
	                    rs.getString("denwa_keitai"), rs.getString("meeru"), rs.getString("SNS"),
	                    rs.getString("kubun"), rs.getString("jyoutai"), rs.getString("kouza_bangou"));
	            shainList.add(shain);
	        }
	        return shainList;
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(pstmt);
	    }
	}

	
	
	

	// 2. 사용자가 설정한 정렬 기준에 따라 리스트를 정렬하는 메서드
	public ArrayList<Shain> getShainListBySortCriteria(Connection conn, String sortCriteria1, String sortDirection1,
	        String sortCriteria2, String sortDirection2, String sortCriteria3, String sortDirection3) throws SQLException {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT s.shain_id, s.namae_kana, s.namae_eigo, b.busho_mei, y.yakushoku_mei, "
	                + "s.jumin_bangou, s.kokuseki, s.juusho, s.denwa_uchi, s.denwa_keitai, s.meeru, s.SNS, "
	                + "s.jyoutai, sk.kouza_bangou "
	                + "FROM Shain s "
	                + "LEFT JOIN ShainKihonKyuu sk ON s.shain_id = sk.shain_id "
	                + "JOIN Busho b ON s.busho_id = b.busho_id "
	                + "JOIN Yakushoku y ON s.yakushoku_id = y.yakushoku_id "
	                + "ORDER BY " + sortCriteria1 + " " + sortDirection1 + ", " + sortCriteria2 + " " + sortDirection2
	                + ", " + sortCriteria3 + " " + sortDirection3;

	        pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();

	        ArrayList<Shain> shainList = new ArrayList<>();
	        while (rs.next()) {
	            Shain shain = new Shain(rs.getInt("shain_id"), rs.getString("namae_kana"),
	                    rs.getString("namae_eigo"), rs.getDate("nyuusha_nengappi"), rs.getDate("taisha_nengappi"),
	                    rs.getString("busho_mei"), rs.getString("yakushoku_mei"), rs.getString("kokuseki"),
	                    rs.getString("jumin_bangou"), rs.getString("juusho"), rs.getString("denwa_uchi"),
	                    rs.getString("denwa_keitai"), rs.getString("meeru"), rs.getString("SNS"),
	                    rs.getString("kubun"), rs.getString("jyoutai"), rs.getString("kouza_bangou"));
	            shainList.add(shain);
	        }
	        return shainList;
	    } finally {
	        JdbcUtil.close(rs);
	        JdbcUtil.close(pstmt);
	    }

	}
}
