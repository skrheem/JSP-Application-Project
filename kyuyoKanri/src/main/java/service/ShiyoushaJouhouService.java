package service;
//김찬호 金燦鎬

//기본환경설정 사용자정보
//基本環境設定 使用者情報
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kihonkankyousettei.dao.BushoDao;
import kihonkankyousettei.dao.KaishaJouhouDao;
import kihonkankyousettei.dao.KyuuyoShikyuuJouhouDao;
import kihonkankyousettei.dao.TantoushaDao;
import kihonkankyousettei.dao.YakushokuDao;
import kihonkankyousettei.model.Busho;
import kihonkankyousettei.model.KaishaJouhou;
import kihonkankyousettei.model.KyuuyoShikyuuJouhou;
import kihonkankyousettei.model.Tantousha;
import kihonkankyousettei.model.Yakushoku;

public class ShiyoushaJouhouService {

	private KaishaJouhouDao kaishaJouhouDao = KaishaJouhouDao.getInstance();
	private TantoushaDao tantoushaDao = TantoushaDao.getInstance();
	private KyuuyoShikyuuJouhouDao kyuuyoShikyuuJouhouDao = KyuuyoShikyuuJouhouDao.getInstance();
	private BushoDao bushoDao = BushoDao.getInstance();
	private YakushokuDao yakushokuDao = YakushokuDao.getInstance();

	/**
	 * 회사 정보를 삽입하는 메서드입니다.
	 * 会社情報を挿入するメソッドです。
	 */
	public void insertKaisha(KaishaJouhou kaisha) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			kaishaJouhouDao.insertKaisha(conn, kaisha);
		}
	}

	/**
	 * 특정 ID로 회사 정보를 조회하는 메서드입니다.
	 * 特定のIDで会社情報を取得するメソッドです。
	 */
	public KaishaJouhou getKaishaById(int kaishaId) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return kaishaJouhouDao.getKaishaById(conn, kaishaId);
		}
	}

	/**
	 * 회사 정보를 업데이트하는 메서드입니다.
	 * 会社情報を更新するメソッドです。
	 */
	public void updateKaisha(KaishaJouhou kaisha, int kaishaId) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			kaishaJouhouDao.updateKaisha(conn, kaisha, kaishaId);
		}
	}

	/**
	 * 담당자 정보를 삽입하는 메서드입니다.
	 * 担当者情報を挿入するメソッドです。
	 */
	public void insertTantousha(Tantousha tantousha) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			tantoushaDao.insertTantousha(conn, tantousha);
		}
	}

	/**
	 * 특정 ID로 담당자 정보를 조회하는 메서드입니다.
	 * 特定のIDで担当者情報を取得するメソッドです。
	 */
	public Tantousha getTantoushaById(int tantoushaId) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return tantoushaDao.getTantoushaById(conn, tantoushaId);
		}
	}

	/**
	 * 담당자 정보를 업데이트하는 메서드입니다.
	 * 担当者情報を更新するメソッドです。
	 */
	public void updateTantousha(Tantousha tantousha, int tantoushaId) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			tantoushaDao.updateTantousha(conn, tantousha, tantoushaId);
		}
	}

	/**
	 * 부서 정보를 삽입하는 메서드입니다.
	 * 部署情報を挿入するメソッドです。
	 */
	public boolean addBusho(Busho busho) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			System.out.println("ShiyoushaJouhouService: 부서 추가 시작 - 부서명: " + busho.getBusho_mei());
			int result = bushoDao.insertBusho(conn, busho);
			System.out.println("ShiyoushaJouhouService: 부서 추가 결과 - 성공 여부: " + (result > 0));
			return result > 0;
		} catch (SQLException e) {
			System.err.println("ShiyoushaJouhouService: 부서 추가 중 오류 발생");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 부서 정보를 업데이트하는 메서드입니다.
	 * 部署情報を更新するメソッドです。
	 */
	public boolean updateBusho(Busho busho) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return bushoDao.updateBusho(conn, busho) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 급여 지급 정보를 삽입하는 메서드입니다.
	 * 給与支給情報を挿入するメソッドです。
	 */
	public void insertShikyuuJouhou(KyuuyoShikyuuJouhou shikyuuJouhou) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			kyuuyoShikyuuJouhouDao.insertShikyuuJouhou(conn, shikyuuJouhou);
		}
	}

	/**
	 * 특정 회사 ID로 급여 지급 정보를 조회하는 메서드입니다.
	 * 特定の会社IDで給与支給情報を取得するメソッドです。
	 */
	public KyuuyoShikyuuJouhou getShikyuuJouhouByKaishaId(int kaishaId) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return kyuuyoShikyuuJouhouDao.getShikyuuJouhouByKaishaId(conn, kaishaId);
		}
	}

	/**
	 * 급여 지급 정보를 업데이트하는 메서드입니다.
	 * 給与支給情報を更新するメソッドです。
	 */
	public void updateShikyuuJouhou(KyuuyoShikyuuJouhou shikyuuJouhou) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			kyuuyoShikyuuJouhouDao.updateShikyuuJouhou(conn, shikyuuJouhou);
		}
	}

	/**
	 * 모든 부서 정보를 조회하는 메서드입니다.
	 * すべての部署情報を取得するメソッドです。
	 */
	public List<Busho> getBushoList() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return bushoDao.selectAllBusho(conn); // selectAllBusho는 인스턴스 메서드로 호출
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 직책 정보를 삽입하는 메서드입니다.
	 * 職務情報を挿入するメソッドです。
	 */
	public boolean addYakushoku(Yakushoku yakushoku) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int result = yakushokuDao.insertYakushoku(conn, yakushoku);
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 특정 ID로 직책 정보를 조회하는 메서드입니다.
	 * 特定のIDで職務情報を取得するメソッドです。
	 */
	public Yakushoku getYakushokuById(int yakushokuId) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return yakushokuDao.selectYakushokuById(conn, yakushokuId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 직책 정보를 업데이트하는 메서드입니다.
	 * 職務情報を更新するメソッドです。
	 */
	public boolean updateYakushoku(Yakushoku yakushoku) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return yakushokuDao.updateYakushoku(conn, yakushoku) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 모든 직책 정보를 조회하는 메서드입니다.
	 * すべての職務情報を取得するメソッドです。
	 */
	public ArrayList<Yakushoku> getAllYakushoku() {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return yakushokuDao.selectAllYakushoku(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
