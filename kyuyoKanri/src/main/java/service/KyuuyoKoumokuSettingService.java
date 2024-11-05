package service;

//김찬호 金燦鎬
//급여항목 설정
//給与項目設定
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import kihonkankyousettei.dao.KoujoKoumokuDao;
import kihonkankyousettei.dao.KyuuyoKoumokuDao;
import kihonkankyousettei.model.KoujoKoumoku;
import kihonkankyousettei.model.KyuuyoKoumoku;

public class KyuuyoKoumokuSettingService {

	private KyuuyoKoumokuDao kyuuyoKoumokuDao = KyuuyoKoumokuDao.getInstance();
	private KoujoKoumokuDao koujoKoumokuDao = KoujoKoumokuDao.getInstance();

	/**
	 * KyuuyoKoumoku를 등록하는 메서드입니다. KyuuyoKoumokuを登録するメソッドです。
	 */
	public boolean registerKyuuyoKoumoku(KyuuyoKoumoku kyuuyoKoumoku) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			System.out.println("KyuuyoKoumoku 등록 중: " + kyuuyoKoumoku);
			int result = kyuuyoKoumokuDao.insertKyuuyoKoumoku(conn, kyuuyoKoumoku);
			return result > 0;
		} catch (SQLException e) {
			System.err.println("KyuuyoKoumoku 등록 중 오류 발생");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 특정 ID로 KyuuyoKoumoku를 조회하는 메서드입니다. 特定のIDでKyuuyoKoumokuを取得するメソッドです。
	 */
	public KyuuyoKoumoku getKyuuyoKoumokuById(int kyuuyoKoumokuId) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return kyuuyoKoumokuDao.getKyuuyoKoumokuById(conn, kyuuyoKoumokuId);
		} catch (SQLException e) {
			System.err.println("ID로 KyuuyoKoumoku 조회 중 오류 발생: " + kyuuyoKoumokuId);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 모든 KyuuyoKoumoku를 조회하는 메서드입니다. すべてのKyuuyoKoumokuを取得するメソッドです。
	 */
	public ArrayList<KyuuyoKoumoku> getAllKyuuyoKoumoku() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return kyuuyoKoumokuDao.selectAllKyuuyoKoumoku(conn);
		} catch (SQLException e) {
			System.err.println("모든 KyuuyoKoumoku 조회 중 오류 발생");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * KyuuyoKoumoku를 업데이트하는 메서드입니다. KyuuyoKoumokuを更新するメソッドです。
	 */
	public boolean updateKyuuyoKoumoku(KyuuyoKoumoku kyuuyoKoumoku, int kyuuyoKoumokuId) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int result = kyuuyoKoumokuDao.updateKyuuyoKoumoku(conn, kyuuyoKoumoku, kyuuyoKoumokuId);
			return result > 0;
		} catch (SQLException e) {
			System.err.println("ID로 KyuuyoKoumoku 업데이트 중 오류 발생: " + kyuuyoKoumokuId);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * KoujoKoumoku를 등록하는 메서드입니다. KoujoKoumokuを登録するメソッドです。
	 */
	public boolean registerKoujoKoumoku(KoujoKoumoku koujoKoumoku) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			System.out.println("KoujoKoumoku 등록 중: " + koujoKoumoku);
			int result = koujoKoumokuDao.insertKoujoKoumoku(conn, koujoKoumoku);
			return result > 0;
		} catch (SQLException e) {
			System.err.println("KoujoKoumoku 등록 중 오류 발생");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 특정 ID로 KoujoKoumoku를 조회하는 메서드입니다. 特定のIDでKoujoKoumokuを取得するメソッドです。
	 */
	public KoujoKoumoku getKoujoKoumokuById(int koujoKoumokuId) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return koujoKoumokuDao.getKoujoKoumokuById(conn, koujoKoumokuId);
		} catch (SQLException e) {
			System.err.println("ID로 KoujoKoumoku 조회 중 오류 발생: " + koujoKoumokuId);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 모든 KoujoKoumoku를 조회하는 메서드입니다. すべてのKoujoKoumokuを取得するメソッドです。
	 */
	public ArrayList<KoujoKoumoku> getAllKoujoKoumoku() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return koujoKoumokuDao.selectAllKoujoKoumoku(conn);
		} catch (SQLException e) {
			System.err.println("모든 KoujoKoumoku 조회 중 오류 발생");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * KoujoKoumoku를 업데이트하는 메서드입니다. KoujoKoumokuを更新するメソッドです。
	 */
	public boolean updateKoujoKoumoku(KoujoKoumoku koujoKoumoku, int koujoKoumokuId) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int result = koujoKoumokuDao.updateKoujoKoumoku(conn, koujoKoumoku, koujoKoumokuId);
			return result > 0;
		} catch (SQLException e) {
			System.err.println("ID로 KoujoKoumoku 업데이트 중 오류 발생: " + koujoKoumokuId);
			e.printStackTrace();
			return false;
		}
	}
}
