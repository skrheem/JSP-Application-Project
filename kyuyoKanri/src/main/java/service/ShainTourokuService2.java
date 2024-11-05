package service;
//김찬호 金燦鎬
//기본환경설정 사원등록
//基本環境設定 社員登録
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kihonkankyousettei.dao.GogakuNouryokuDao;
import kihonkankyousettei.dao.HatsureiDao;
import kihonkankyousettei.dao.HoshouhoHokenDao;
import kihonkankyousettei.dao.HoshouninDao;
import kihonkankyousettei.dao.KyouikuKunrenDao;
import kihonkankyousettei.dao.ShikakuMenkyouDao;
import kihonkankyousettei.dao.ShoubatsuDao;
import kihonkankyousettei.dao.SuisenshaDao;
import kihonkankyousettei.dao.TaishokuDao;
import kihonkankyousettei.model.Gogaku;
import kihonkankyousettei.model.Hatsurei;
import kihonkankyousettei.model.HoshouhoHoken;
import kihonkankyousettei.model.Hoshounin;
import kihonkankyousettei.model.KyouikuKunren;
import kihonkankyousettei.model.Shoubatsu;
import kihonkankyousettei.model.Suisensha;
import kihonkankyousettei.model.ShikakuMenkyou;
import kihonkankyousettei.model.Taishoku;

public class ShainTourokuService2 {

	private ShikakuMenkyouDao shikakuMenkyouDao = ShikakuMenkyouDao.getInstance();
	private GogakuNouryokuDao gogakuNouryokuDao = GogakuNouryokuDao.getInstance();
	private KyouikuKunrenDao kyouikuKunrenDao = KyouikuKunrenDao.getInstance();
	private ShoubatsuDao shoubatsuDao = ShoubatsuDao.getInstance();
	private HatsureiDao hatsureiDao = HatsureiDao.getInstance();
	private SuisenshaDao suisenshaDao = SuisenshaDao.getInstance();
	private HoshouhoHokenDao hoshouhoHokenDao = HoshouhoHokenDao.getInstance();
	private HoshouninDao hoshouninDao = HoshouninDao.getInstance();
	private TaishokuDao taishokuDao = TaishokuDao.getInstance();

	/**
	 * ShikakuMenkyou 등록 메서드입니다. 資格免許を登録するメソッドです。
	 */
	public void registerShikakuMenkyou(ShikakuMenkyou shikaku) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			shikakuMenkyouDao.insertShikakuMenkyou(conn, shikaku);
		}
	}

	/**
	 * GogakuNouryoku 등록 메서드입니다. 語学能力を登録するメソッドです。
	 */
	public void registerGogakuNouryoku(Gogaku gogaku) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			gogakuNouryokuDao.insertGogakuNouryoku(conn, gogaku);
		}
	}

	/**
	 * KyouikuKunren 등록 메서드입니다. 教育訓練を登録するメソッドです。
	 */
	public void registerKyouikuKunren(KyouikuKunren kunren) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			kyouikuKunrenDao.insertKyouikuKunren(conn, kunren);
		}
	}

	/**
	 * Shoubatsu 등록 메서드입니다. 処罰情報を登録するメソッドです。
	 */
	public void registerShoubatsu(Shoubatsu shoubatsu) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			shoubatsuDao.insertShoubatsu(conn, shoubatsu);
		}
	}

	/**
	 * Hatsurei 등록 메서드입니다. 発令情報を登録するメソッドです。
	 */
	public void registerHatsurei(Hatsurei hatsurei) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			hatsureiDao.insertHatsurei(conn, hatsurei);
		}
	}

	/**
	 * Suisensha 등록 메서드입니다. 推薦者情報を登録するメソッドです。
	 */
	public void registerSuisensha(Suisensha suisensha) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			suisenshaDao.insertSuisensha(conn, suisensha);
		}
	}

	/**
	 * HoshouhoHoken 등록 메서드입니다. 保証保険情報を登録するメソッドです。
	 */
	public void registerHoshouhoHoken(HoshouhoHoken hoshouhoHoken) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			hoshouhoHokenDao.insertHoshouhoHoken(conn, hoshouhoHoken);
		}
	}

	/**
	 * Hoshounin 등록 메서드입니다. 保証人情報を登録するメソッドです。
	 */
	public void registerHoshounin(Hoshounin hoshounin) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			hoshouninDao.insertHoshounin(conn, hoshounin);
		}
	}

	/**
	 * Taishoku 등록 메서드입니다. 退職情報を登録するメソッドです。
	 */
	public void registerTaishoku(Taishoku taishoku) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			taishokuDao.insertTaishoku(conn, taishoku);
		}
	}

	/**
	 * 모든 ShikakuMenkyou 조회 메서드입니다. すべての資格免許情報を取得するメソッドです。
	 */
	public List<ShikakuMenkyou> getAllShikakuMenkyou() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return shikakuMenkyouDao.selectAllShikakuMenkyou(conn);
		}
	}

	/**
	 * 모든 GogakuNouryoku 조회 메서드입니다. すべての語学能力情報を取得するメソッドです。
	 */
	public List<Gogaku> getAllGogakuNouryoku() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return gogakuNouryokuDao.selectAllGogakuNouryoku(conn);
		}
	}

	/**
	 * 모든 KyouikuKunren 조회 메서드입니다. すべての教育訓練情報を取得するメソッドです。
	 */
	public List<KyouikuKunren> getAllKyouikuKunren() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return kyouikuKunrenDao.selectAllKyouikuKunren(conn);
		}
	}

	/**
	 * 모든 Shoubatsu 조회 메서드입니다. すべての処罰情報を取得するメソッドです。
	 */
	public List<Shoubatsu> getAllShoubatsu() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return shoubatsuDao.selectAllShoubatsu(conn);
		}
	}

	/**
	 * 모든 Hatsurei 조회 메서드입니다. すべての発令情報を取得するメソッドです。
	 */
	public List<Hatsurei> getAllHatsurei() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return hatsureiDao.selectAllHatsurei(conn);
		}
	}

	/**
	 * 모든 Suisensha 조회 메서드입니다. すべての推薦者情報を取得するメソッドです。
	 */
	public List<Suisensha> getAllSuisensha() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return suisenshaDao.selectAllSuisensha(conn);
		}
	}

	/**
	 * 모든 HoshouhoHoken 조회 메서드입니다. すべての保証保険情報を取得するメソッドです。
	 */
	public List<HoshouhoHoken> getAllHoshouhoHoken() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return hoshouhoHokenDao.selectAllHoshouhoHoken(conn);
		}
	}

	/**
	 * 모든 Hoshounin 조회 메서드입니다. すべての保証人情報を取得するメソッドです。
	 */
	public List<Hoshounin> getAllHoshounin() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return hoshouninDao.selectAllHoshounin(conn);
		}
	}

	/**
	 * 모든 Taishoku 조회 메서드입니다. すべての退職情報を取得するメソッドです。
	 */
	public List<Taishoku> getAllTaishoku() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return taishokuDao.selectAllTaishoku(conn);
		}
	}
}
