package kihonkankyousettei.dao;
//김찬호 金燦鎬
//기본환경설정 사용자정보 담당자 정보 직위
//基本環境設定 使用者情報担当者情報 職位
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import kihonkankyousettei.model.Yakushoku;

public class YakushokuDao {

    private static YakushokuDao instance = new YakushokuDao();

    /**
     * YakushokuDao의 인스턴스를 반환합니다.
     * YakushokuDaoのインスタンスを返します。
     */
    public static YakushokuDao getInstance() {
        return instance;
    }

    /**
     * 직책을 데이터베이스에 추가하는 메서드입니다.
     * 職責をデータベースに追加するメソッドです。
     */
    public int insertYakushoku(Connection conn, Yakushoku yakushoku) throws SQLException {
        String insertSql = "INSERT INTO Yakushoku (yakushoku_id, yakushoku_mei) VALUES (Yakushoku_sequence.nextval, ?)";
        String selectSql = "SELECT Yakushoku_sequence.currval FROM dual"; // 마지막으로 생성된 yakushoku_id를 가져오는 쿼리

        try (PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            // INSERT 수행
            insertStmt.setString(1, yakushoku.getYakushoku_mei());
            int affectedRows = insertStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("직책 추가 실패: no rows affected.");
            }

            // 마지막으로 생성된 yakushoku_id 가져오기
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1); // 새로 생성된 yakushoku_id 반환
                } else {
                    throw new SQLException("직책 추가 실패: yakushoku_id를 가져오지 못했습니다.");
                }
            }
        }
    }

    /**
     * 직책 정보를 업데이트하는 메서드입니다.
     * 職責情報を更新するメソッドです。
     */
    public int updateYakushoku(Connection conn, Yakushoku yakushoku) throws SQLException {
        String sql = "UPDATE Yakushoku SET yakushoku_mei = ? WHERE yakushoku_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, yakushoku.getYakushoku_mei());
            stmt.setInt(2, yakushoku.getYakushoku_id());
            return stmt.executeUpdate();
        }
    }

    /**
     * 특정 ID로 직책 정보를 조회하는 메서드입니다.
     * 特定のIDで職責情報を取得するメソッドです。
     */
    public Yakushoku selectYakushokuById(Connection conn, Integer yakushoku_id) throws SQLException {
        String sql = "SELECT yakushoku_id, yakushoku_mei FROM Yakushoku WHERE yakushoku_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, yakushoku_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Yakushoku(rs.getInt("yakushoku_id"), rs.getString("yakushoku_mei"));
                }
            }
        }
        return null;
    }

    /**
     * 모든 직책 정보를 조회하는 메서드입니다.
     * すべての職責情報を取得するメソッドです。
     */
    public ArrayList<Yakushoku> selectAllYakushoku(Connection conn) throws SQLException {
        ArrayList<Yakushoku> yakushokuList = new ArrayList<>();
        String sql = "SELECT yakushoku_id, yakushoku_mei FROM Yakushoku";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                yakushokuList.add(new Yakushoku(rs.getInt("yakushoku_id"), rs.getString("yakushoku_mei")));
            }
        }
        return yakushokuList;
    }
}
