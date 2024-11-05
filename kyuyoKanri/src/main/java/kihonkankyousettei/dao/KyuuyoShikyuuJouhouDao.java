package kihonkankyousettei.dao;
//김찬호 金燦鎬
//기본환경설정 사용자정보
//基本環境設定 使用者情報
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kihonkankyousettei.model.KyuuyoShikyuuJouhou;

/**
 * KyuuyoShikyuuJouhouDao는 급여 지급 정보를 관리하는 데이터 접근 객체입니다.
 * KyuuyoShikyuuJouhouDaoは給与支給情報を管理するデータアクセスオブジェクトです。
 */
public class KyuuyoShikyuuJouhouDao {

    private static KyuuyoShikyuuJouhouDao instance = new KyuuyoShikyuuJouhouDao();

    /**
     * KyuuyoShikyuuJouhouDao의 인스턴스를 반환합니다.
     * KyuuyoShikyuuJouhouDaoのインスタンスを返します。
     */
    public static KyuuyoShikyuuJouhouDao getInstance() {
        return instance;
    }

    /**
     * 지급 정보를 데이터베이스에 입력하는 메서드입니다.
     * 支給情報をデータベースに入力するメソッドです。
     */
    public void insertShikyuuJouhou(Connection conn, KyuuyoShikyuuJouhou shikyuuJouhou) throws SQLException {
        String query = "INSERT INTO KyuuyoShikyuuJouhou (kaisha_id, kyuuyoSanteiKaishi, kyuuyoSanteiShuuryou, KyuuyoShikyuu_bi, "
                + "KinyuuKikan, Kouza_bangou, YokinShaMeigi) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, shikyuuJouhou.getKaisha_id());

            if (shikyuuJouhou.getKyuuyoSanteiKaishi() != null) {
                ps.setDate(2, new java.sql.Date(shikyuuJouhou.getKyuuyoSanteiKaishi().getTime()));
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }

            if (shikyuuJouhou.getKyuuyoSanteiShuuryou() != null) {
                ps.setDate(3, new java.sql.Date(shikyuuJouhou.getKyuuyoSanteiShuuryou().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            if (shikyuuJouhou.getKyuuyoShikyuu_bi() != null) {
                ps.setDate(4, new java.sql.Date(shikyuuJouhou.getKyuuyoShikyuu_bi().getTime()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            ps.setString(5, shikyuuJouhou.getKinyuuKikan());
            ps.setString(6, shikyuuJouhou.getKouza_bangou());
            ps.setString(7, shikyuuJouhou.getYokinShaMeigi());

            ps.executeUpdate();
        }
    }

    /**
     * 특정 회사 ID로 지급 정보를 조회하는 메서드입니다.
     * 特定の会社IDで支給情報を照会するメソッドです。
     */
    public KyuuyoShikyuuJouhou getShikyuuJouhouByKaishaId(Connection conn, Integer kaishaId) throws SQLException {
        KyuuyoShikyuuJouhou shikyuuJouhou = null;
        String query = "SELECT * FROM KyuuyoShikyuuJouhou WHERE kaisha_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, kaishaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    shikyuuJouhou = new KyuuyoShikyuuJouhou();
                    shikyuuJouhou.setKaisha_id(rs.getInt("kaisha_id"));
                    shikyuuJouhou.setKyuuyoSanteiKaishi(rs.getDate("kyuuyoSanteiKaishi"));
                    shikyuuJouhou.setKyuuyoSanteiShuuryou(rs.getDate("KyuuyoSanteiShuuryou"));
                    shikyuuJouhou.setKyuuyoShikyuu_bi(rs.getDate("KyuuyoShikyuu_bi"));
                    shikyuuJouhou.setKinyuuKikan(rs.getString("KinyuuKikan"));
                    shikyuuJouhou.setKouza_bangou(rs.getString("Kouza_bangou"));
                    shikyuuJouhou.setYokinShaMeigi(rs.getString("YokinShaMeigi"));
                }
            }
        }
        return shikyuuJouhou;
    }

    /**
     * 지급 정보를 업데이트하는 메서드입니다.
     * 支給情報を更新するメソッドです。
     */
    public void updateShikyuuJouhou(Connection conn, KyuuyoShikyuuJouhou shikyuuJouhou) throws SQLException {
        String query = "UPDATE KyuuyoShikyuuJouhou SET KyuuyoSanteiKaishi = ?, KyuuyoSanteiShuuryou = ?, KyuuyoShikyuu_bi = ?, "
                + "KinyuuKikan = ?, Kouza_bangou = ?, YokinShaMeigi = ? WHERE kaisha_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            if (shikyuuJouhou.getKyuuyoSanteiKaishi() != null) {
                ps.setDate(1, new java.sql.Date(shikyuuJouhou.getKyuuyoSanteiKaishi().getTime()));
            } else {
                ps.setNull(1, java.sql.Types.DATE);
            }

            if (shikyuuJouhou.getKyuuyoSanteiShuuryou() != null) {
                ps.setDate(2, new java.sql.Date(shikyuuJouhou.getKyuuyoSanteiShuuryou().getTime()));
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }

            if (shikyuuJouhou.getKyuuyoShikyuu_bi() != null) {
                ps.setDate(3, new java.sql.Date(shikyuuJouhou.getKyuuyoShikyuu_bi().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            ps.setString(4, shikyuuJouhou.getKinyuuKikan());
            ps.setString(5, shikyuuJouhou.getKouza_bangou());
            ps.setString(6, shikyuuJouhou.getYokinShaMeigi());
            ps.setInt(7, shikyuuJouhou.getKaisha_id());

            ps.executeUpdate();
        }
    }
}