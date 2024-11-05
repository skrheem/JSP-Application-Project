package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import kihonkankyousettei.dao.ShainKyuukaNissuuDao;
import kihonkankyousettei.model.ShainKyuukaNissuu;

public class ShainKyuukaNissuuService {

    private ShainKyuukaNissuuDao shainKyuukaNissuuDao = ShainKyuukaNissuuDao.getInstance();

    /**
     * 사원별 휴가일수를 등록하는 메서드입니다.
     * @param conn 데이터베이스 연결 객체
     * @param shainKyuukaNissuu 등록할 사원별 휴가일수 객체
     * @return 등록 성공 여부
     * @throws SQLException 데이터베이스 관련 예외 발생 시 처리
     */
    public boolean registerShainKyuukaNissuu(Connection conn, ShainKyuukaNissuu shainKyuukaNissuu) throws SQLException {
        return shainKyuukaNissuuDao.insertShainKyuukaNissuu(conn, shainKyuukaNissuu) > 0;
    }

    /**
     * 사원 ID를 기준으로 휴가일수를 조회하는 메서드입니다.
     * @param conn 데이터베이스 연결 객체
     * @param shainId 사원 ID
     * @return 사원별 휴가일수 목록
     * @throws SQLException 데이터베이스 관련 예외 발생 시 처리
     */
    public ArrayList<ShainKyuukaNissuu> getKyuukaNissuuByShainId(Connection conn, int shainId) throws SQLException {
        return shainKyuukaNissuuDao.getShainKyuukaNissuuByShainId(conn, shainId);
    }

    /**
     * 사원별 휴가일수를 업데이트하는 메서드입니다.
     * @param conn 데이터베이스 연결 객체
     * @param shainKyuukaNissuu 업데이트할 사원별 휴가일수 객체
     * @return 업데이트 성공 여부
     * @throws SQLException 데이터베이스 관련 예외 발생 시 처리
     */
    public boolean updateShainKyuukaNissuu(Connection conn, ShainKyuukaNissuu shainKyuukaNissuu) throws SQLException {
        return shainKyuukaNissuuDao.updateShainKyuukaNissuu(conn, shainKyuukaNissuu) > 0;
    }

    /**
     * 사원별 휴가일수를 삭제하는 메서드입니다.
     * @param conn 데이터베이스 연결 객체
     * @param shainId 사원 ID
     * @param kyuukaKoumokuId 삭제할 휴가 항목 ID
     * @return 삭제 성공 여부
     * @throws SQLException 데이터베이스 관련 예외 발생 시 처리
     */
    public boolean deleteShainKyuukaNissuu(Connection conn, int shainId, int kyuukaKoumokuId) throws SQLException {
        return shainKyuukaNissuuDao.deleteShainKyuukaNissuu(conn, shainId, kyuukaKoumokuId) > 0;
    }
}
