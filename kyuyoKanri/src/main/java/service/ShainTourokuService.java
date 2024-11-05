package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import kihonkankyousettei.dao.FuyouKazokuDao;
import kihonkankyousettei.dao.GakurekiDao;
import kihonkankyousettei.dao.HeiekiDao;
import kihonkankyousettei.dao.KeirekiDao;
import kihonkankyousettei.dao.ShainKihonkyuuDao;
import kihonkankyousettei.dao.YondaihokenJouhouDao;
import kihonkankyousettei.model.FuyouKazoku;
import kihonkankyousettei.model.Gakureki;
import kihonkankyousettei.model.Heieki;
import kihonkankyousettei.model.Keireki;
import kihonkankyousettei.model.ShainKihonkyuu;
import kihonkankyousettei.model.YondaihokenJouhou;

public class ShainTourokuService {

	private ShainKihonkyuuDao shainKihonkyuuDao = ShainKihonkyuuDao.getInstance();
	private YondaihokenJouhouDao yondaihokenJouhouDao = YondaihokenJouhouDao.getInstance();
	private FuyouKazokuDao fuyouKazokuDao = FuyouKazokuDao.getInstance();
	private GakurekiDao gakurekiDao = GakurekiDao.getInstance();
	private KeirekiDao keirekiDao = KeirekiDao.getInstance();
	private HeiekiDao heiekiDao = HeiekiDao.getInstance();

	/**
	 * ShainKihonkyuu 등록 메서드입니다. ShainKihonkyuuを登録するメソッドです。
	 */
	public void registerShainKihonkyuu(ShainKihonkyuu shainKihonkyuu) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			shainKihonkyuuDao.insertShainKihonkyuu(conn, shainKihonkyuu);
		}
	}

	/**
	 * YondaihokenJouhou 등록 메서드입니다. 余大保険情報を登録するメソッドです。
	 */
	public void registerYondaihokenJouhou(YondaihokenJouhou yondaihokenJouhou) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			yondaihokenJouhouDao.insertYondaihokenJouhou(conn, yondaihokenJouhou);
		}
	}

	/**
	 * FuyouKazoku 등록 메서드입니다. 扶養家族を登録するメソッドです。
	 */
	public void registerFuyouKazoku(FuyouKazoku fuyouKazoku) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			fuyouKazokuDao.insertFuyouKazoku(conn, fuyouKazoku);
		}
	}

	/**
	 * Gakureki 등록 메서드입니다. 学歴を登録するメソッドです。
	 */
	public void registerGakureki(Gakureki gakureki) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			gakurekiDao.insertGakureki(conn, gakureki);
		}
	}

	/**
	 * Keireki 등록 메서드입니다. 経歴を登録するメソッドです。
	 */
	public void registerKeireki(Keireki keireki) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			keirekiDao.insertKeireki(conn, keireki);
		}
	}

	/**
	 * Heieki 등록 메서드입니다. 兵役を登録するメソッドです。
	 */
	public void registerHeieki(Heieki heieki) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			heiekiDao.insertHeieki(conn, heieki);
		}
	}

	/**
	 * 특정 사원의 ShainKihonkyuu 조회 메서드입니다. 特定の社員の基本給情報を取得するメソッドです。
	 */
	public ShainKihonkyuu getShainKihonkyuuById(int kihonKyuuId) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return shainKihonkyuuDao.getShainKihonkyuuById(conn, kihonKyuuId);
		}
	}

	/**
	 * 특정 사원의 YondaihokenJouhou 조회 메서드입니다. 特定の社員の余大保険情報を取得するメソッドです。
	 */
	public YondaihokenJouhou getYondaihokenJouhouById(int shainId, String hokenKubun) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return yondaihokenJouhouDao.getYondaihokenJouhouById(conn, shainId, hokenKubun);
		}
	}

	/**
	 * 특정 사원의 FuyouKazoku 조회 메서드입니다. 特定の社員の扶養家族情報を取得するメソッドです。
	 */
	public FuyouKazoku getFuyouKazokuById(int kazokuId) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return fuyouKazokuDao.getFuyouKazokuById(conn, kazokuId);
		}
	}

	/**
	 * 특정 사원의 Gakureki 조회 메서드입니다. 特定の社員の学歴情報を取得するメソッドです。
	 */
	public Gakureki getGakurekiById(int gakurekiId) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return gakurekiDao.getGakurekiById(conn, gakurekiId);
		}
	}

	/**
	 * 특정 사원의 Keireki 조회 메서드입니다. 特定の社員の経歴情報を取得するメソッドです。
	 */
	public Keireki getKeirekiById(int keirekiId) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return keirekiDao.getKeirekiById(conn, keirekiId);
		}
	}

	/**
	 * 특정 사원의 Heieki 조회 메서드입니다. 特定の社員の兵役情報を取得するメソッドです。
	 */
	public Heieki getHeiekiById(int heiekiId) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return heiekiDao.getHeiekiById(conn, heiekiId);
		}
	}

	/**
	 * 모든 ShainKihonkyuu 조회 메서드입니다. すべての社員基本給情報を取得するメソッドです。
	 */
	public ArrayList<ShainKihonkyuu> getAllShainKihonkyuu() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return shainKihonkyuuDao.selectAllShainKihonkyuu(conn);
		}
	}

	/**
	 * 모든 YondaihokenJouhou 조회 메서드입니다. すべての余大保険情報を取得するメソッドです。
	 */
	public ArrayList<YondaihokenJouhou> getAllYondaihokenJouhou() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return yondaihokenJouhouDao.selectAllYondaihokenJouhou(conn);
		}
	}

	/**
	 * 모든 FuyouKazoku 조회 메서드입니다. すべての扶養家族情報を取得するメソッドです。
	 */
	public ArrayList<FuyouKazoku> getAllFuyouKazoku() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return fuyouKazokuDao.selectAllFuyouKazoku(conn);
		}
	}

	/**
	 * 모든 Gakureki 조회 메서드입니다. すべての学歴情報を取得するメソッドです。
	 */
	public ArrayList<Gakureki> getAllGakureki() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return gakurekiDao.selectAllGakurekiWithJoin(conn);
		}
	}

	/**
	 * 모든 Keireki 조회 메서드입니다. すべての経歴情報を取得するメソッドです。
	 */
	public ArrayList<Keireki> getAllKeireki() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return keirekiDao.selectAllKeireki(conn);
		}
	}

	/**
	 * 모든 Heieki 조회 메서드입니다. すべての兵役情報を取得するメソッドです。
	 */
	public ArrayList<Heieki> getAllHeieki() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return heiekiDao.selectAllHeieki(conn);
		}
	}

	/**
	 * ShainKihonkyuu 업데이트 메서드입니다. ShainKihonkyuuを更新するメソッドです。
	 */
	public void updateShainKihonkyuu(ShainKihonkyuu shainKihonkyuu) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			shainKihonkyuuDao.updateShainKihonkyuu(conn, shainKihonkyuu);
		}
	}

	/**
	 * YondaihokenJouhou 업데이트 메서드입니다. 余大保険情報を更新するメソッドです。
	 */
	public void updateYondaihokenJouhou(YondaihokenJouhou yondaihokenJouhou) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			yondaihokenJouhouDao.updateYondaihokenJouhou(conn, yondaihokenJouhou);
		}
	}

	/**
	 * FuyouKazoku 업데이트 메서드입니다. 扶養家族情報を更新するメソッドです。
	 */
	public void updateFuyouKazoku(FuyouKazoku fuyouKazoku) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			fuyouKazokuDao.updateFuyouKazoku(conn, fuyouKazoku);
		}
	}

	/**
	 * Gakureki 업데이트 메서드입니다. 学歴情報を更新するメソッドです。
	 */
	public void updateGakureki(Gakureki gakureki) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			gakurekiDao.updateGakureki(conn, gakureki);
		}
	}

	/**
	 * Keireki 업데이트 메서드입니다. 経歴情報を更新するメソッドです。
	 */
	public void updateKeireki(Keireki keireki) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			keirekiDao.updateKeireki(conn, keireki);
		}
	}

	/**
	 * Heieki 업데이트 메서드입니다. 兵役情報を更新するメソッドです。
	 */
	public void updateHeieki(Heieki heieki) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			heiekiDao.updateHeieki(conn, heieki);
		}
	}
}
