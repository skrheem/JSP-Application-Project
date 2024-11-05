package kihonkankyousettei.dao;

// 김찬호 金燦鎬
//기본환경설정 사용자정보
//基本環境設定 使用者情報
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kihonkankyousettei.model.Tantousha;

public class TantoushaDao {

	private static TantoushaDao instance = new TantoushaDao();

	/**
	 * TantoushaDao의 인스턴스를 반환합니다. TantoushaDaoのインスタンスを返します。
	 */
	public static TantoushaDao getInstance() {
		return instance;
	}

	/**
	 * 담당자 정보를 데이터베이스에 입력하는 메서드입니다. 担当者情報をデータベースに入力するメソッドです。
	 */
	public int insertTantousha(Connection conn, Tantousha tantousha) {
		String query = "INSERT INTO Tantousha (tantousha_id, namae_kana, denwa_tantousha, denwa_keitai, meeru, "
				+ "kaisha_id, busho_id, yakushoku_id) " + "VALUES (Tantousha_sequence.nextval, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = null;
		ResultSet rs = null;
		int affectedRows = 0;

		try {
			ps = conn.prepareStatement(query, new String[] { "tantousha_id" });
			ps.setString(1, tantousha.getNamae_kana());
			ps.setString(2, tantousha.getDenwa_tantousha());
			ps.setString(3, tantousha.getDenwa_keitai());
			ps.setString(4, tantousha.getMeeru());

			// 회사 ID가 null일 경우 처리
			if (tantousha.getKaisha_id() != null) {
				ps.setInt(5, tantousha.getKaisha_id());
			} else {
				ps.setNull(5, java.sql.Types.INTEGER);
			}

			// 부서 ID가 null일 경우 처리
			if (tantousha.getBusho_id() != null) {
				ps.setInt(6, tantousha.getBusho_id());
			} else {
				ps.setNull(6, java.sql.Types.INTEGER);
			}

			// 직책 ID가 null일 경우 처리
			if (tantousha.getYakushoku_id() != null) {
				ps.setInt(7, tantousha.getYakushoku_id());
			} else {
				ps.setNull(7, java.sql.Types.INTEGER);
			}

			affectedRows = ps.executeUpdate();

			// 생성된 키 가져오기
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				tantousha.setTantousha_id(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}

		return affectedRows;
	}

	/**
	 * 특정 담당자 ID로 담당자 정보를 조회하는 메서드입니다. 特定の担当者IDで担当者情報を取得するメソッドです。
	 */
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

	/**
	 * 주어진 Tantousha 객체를 기반으로 담당자 정보를 업데이트하는 메서드입니다.
	 * 与えられたTantoushaオブジェクトに基づいて担当者情報を更新するメソッドです。
	 */
	public int updateTantousha(Connection conn, Tantousha tantousha, Integer tantoushaId) {
		String query = "UPDATE Tantousha SET namae_kana = ?, denwa_tantousha = ?, denwa_keitai = ?, meeru = ?, "
				+ "kaisha_id = ?, busho_id = ?, yakushoku_id = ? WHERE tantousha_id = ?";

		PreparedStatement ps = null;
		int affectedRows = 0;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, tantousha.getNamae_kana());
			ps.setString(2, tantousha.getDenwa_tantousha());
			ps.setString(3, tantousha.getDenwa_keitai());
			ps.setString(4, tantousha.getMeeru());
			ps.setInt(5, tantousha.getKaisha_id());

			// 부서 ID가 null일 경우 처리
			if (tantousha.getBusho_id() != null) {
				ps.setInt(6, tantousha.getBusho_id());
			} else {
				ps.setNull(6, java.sql.Types.INTEGER);
			}

			// 직책 ID가 null일 경우 처리
			if (tantousha.getYakushoku_id() != null) {
				ps.setInt(7, tantousha.getYakushoku_id());
			} else {
				ps.setNull(7, java.sql.Types.INTEGER);
			}

			ps.setInt(8, tantoushaId);
			affectedRows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}

		return affectedRows;
	}
}
