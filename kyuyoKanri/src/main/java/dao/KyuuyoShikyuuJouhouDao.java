package dao;
//김찬호 金燦鎬
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import model.KyuuyoShikyuuJouhou;

public class KyuuyoShikyuuJouhouDao {

    private static KyuuyoShikyuuJouhouDao instance;
    private Connection connection;

    private KyuuyoShikyuuJouhouDao(Connection connection) {
        this.connection = connection;
    }

    public static KyuuyoShikyuuJouhouDao getInstance(Connection connection) {
        if (instance == null) {
            instance = new KyuuyoShikyuuJouhouDao(connection);
        }
        return instance;
    }

    // 특정 회사 ID로 지급 정보를 조회하는 메서드
    public KyuuyoShikyuuJouhou getShikyuuJouhouByKaishaId(Integer kaishaId) {
        KyuuyoShikyuuJouhou shikyuuJouhou = null;
        String query = "SELECT * FROM KyuuyoShikyuuJouhou WHERE kaisha_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, kaishaId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                shikyuuJouhou = new KyuuyoShikyuuJouhou();
                shikyuuJouhou.setKaisha_id(rs.getInt("kaisha_id"));
                shikyuuJouhou.setkyuuyoSanteiKaishi(rs.getDate("kyuuyoSanteiKaishi"));
                shikyuuJouhou.setKyuuyoSanteiShuuryou(rs.getDate("KyuuyoSanteiShuuryou"));
                shikyuuJouhou.setKyuuyoShikyuu_bi(rs.getDate("KyuuyoShikyuu_bi"));
                shikyuuJouhou.setKinyuuKikan(rs.getString("KinyuuKikan"));
                shikyuuJouhou.setKouza_bangou(rs.getString("Kouza_bangou"));
                shikyuuJouhou.setYokinShaMeigi(rs.getString("YokinShaMeigi"));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shikyuuJouhou;
    }

    // 지급 정보를 업데이트하는 메서드
    public void updateKyuuyoShikyuuJouhou(KyuuyoShikyuuJouhou shikyuuJouhou) {
        String query = "UPDATE KyuuyoShikyuuJouhou SET KyuuyoSanteiKaishi = ?, KyuuyoSanteiShuuryou = ?, KyuuyoShikyuu_bi = ?, "
                + "KinyuuKikan = ?, Kouza_bangou = ?, YokinShaMeigi = ? WHERE kaisha_id = ?";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 형식 지정

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            if (shikyuuJouhou.getkyuuyoSanteiKaishi() != null) {
                ps.setString(1, dateFormat.format(shikyuuJouhou.getkyuuyoSanteiKaishi()));
            } else {
                ps.setNull(1, java.sql.Types.VARCHAR);
            }

            if (shikyuuJouhou.getKyuuyoSanteiShuuryou() != null) {
                ps.setString(2, dateFormat.format(shikyuuJouhou.getKyuuyoSanteiShuuryou()));
            } else {
                ps.setNull(2, java.sql.Types.VARCHAR);
            }

            if (shikyuuJouhou.getKyuuyoShikyuu_bi() != null) {
                ps.setString(3, dateFormat.format(shikyuuJouhou.getKyuuyoShikyuu_bi()));
            } else {
                ps.setNull(3, java.sql.Types.VARCHAR);
            }

            ps.setString(4, shikyuuJouhou.getKinyuuKikan());
            ps.setString(5, shikyuuJouhou.getKouza_bangou());
            ps.setString(6, shikyuuJouhou.getYokinShaMeigi());
            ps.setInt(7, shikyuuJouhou.getKaisha_id());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
