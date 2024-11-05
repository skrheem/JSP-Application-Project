package service;
//김찬호 金燦鎬
//휴가근태설정
//休暇勤怠設定
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import kihonkankyousettei.dao.KintaiKoumokuDao;
import kihonkankyousettei.dao.KyuukaKoumokuDao;
import kihonkankyousettei.model.KintaiKoumoku;
import kihonkankyousettei.model.KyuukaKoumoku;

public class KyuukaKintaiKoumokuService {

    private KyuukaKoumokuDao kyuukaKoumokuDao = KyuukaKoumokuDao.getInstance();
    private KintaiKoumokuDao kintaiKoumokuDao = KintaiKoumokuDao.getInstance();

    /**
     * KyuukaKoumoku를 등록하는 메서드입니다.
     * KyuukaKoumokuを登録するメソッドです。
     */
    public boolean registerKyuukaKoumoku(KyuukaKoumoku kyuukaKoumoku) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return kyuukaKoumokuDao.insertKyuukaKoumoku(conn, kyuukaKoumoku) > 0;
        }
    }

    /**
     * 특정 ID로 KyuukaKoumoku를 조회하는 메서드입니다.
     * 特定のIDでKyuukaKoumokuを取得するメソッドです。
     */
    public KyuukaKoumoku getKyuukaKoumokuById(int kyuukaKoumokuId) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return kyuukaKoumokuDao.getKyuukaKoumokuById(conn, kyuukaKoumokuId);
        }
    }

    /**
     * 모든 KyuukaKoumoku를 조회하는 메서드입니다.
     * すべてのKyuukaKoumokuを取得するメソッドです。
     */
    public ArrayList<KyuukaKoumoku> getAllKyuukaKoumoku() throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return kyuukaKoumokuDao.getKyuukaKoumokuList(conn);
        }
    }

    /**
     * KyuukaKoumoku를 업데이트하는 메서드입니다.
     * KyuukaKoumokuを更新するメソッドです。
     */
    public boolean updateKyuukaKoumoku(KyuukaKoumoku kyuukaKoumoku, int kyuukaKoumokuId) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return kyuukaKoumokuDao.updateKyuukaKoumoku(conn, kyuukaKoumoku, kyuukaKoumokuId) > 0;
        }
    }

    /**
     * KintaiKoumoku를 등록하는 메서드입니다.
     * KintaiKoumokuを登録するメソッドです。
     */
    public boolean registerKintaiKoumoku(KintaiKoumoku kintaiKoumoku) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return kintaiKoumokuDao.insertKintaiKoumoku(conn, kintaiKoumoku) > 0;
        }
    }

    /**
     * 특정 ID로 KintaiKoumoku를 조회하는 메서드입니다.
     * 特定のIDでKintaiKoumokuを取得するメソッドです。
     */
    public KintaiKoumoku getKintaiKoumokuById(int kintaiKoumokuId) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return kintaiKoumokuDao.getKintaiKoumokuById(conn, kintaiKoumokuId);
        }
    }

    /**
     * 모든 KintaiKoumoku를 조회하는 메서드입니다.
     * すべてのKintaiKoumokuを取得するメソッドです。
     */
    public ArrayList<KintaiKoumoku> getAllKintaiKoumoku() throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return kintaiKoumokuDao.getKintaiKoumokuList(conn);
        }
    }

    /**
     * KintaiKoumoku를 업데이트하는 메서드입니다.
     * KintaiKoumokuを更新するメソッドです。
     */
    public boolean updateKintaiKoumoku(KintaiKoumoku kintaiKoumoku) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return kintaiKoumokuDao.updateKintaiKoumoku(conn, kintaiKoumoku) > 0;
        }
    }
    
    /**
     * 사원별 휴가일수를 저장하는 메서드입니다.
     * 社員ごとの休日数を保存するメソッドです。
     */
    public void insertKyuukaNissuuShain(String shainId, int kyuukaKoumokuId, double vacationDays) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            kyuukaKoumokuDao.insertKyuukaNissuuShain(Integer.parseInt(shainId), kyuukaKoumokuId, vacationDays);
        }
    }
}
