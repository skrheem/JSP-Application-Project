package kihonkankyousettei.dao;
//김찬호 金燦鎬
//휴가근태설정
//休暇勤怠設定
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.connection.ConnectionProvider;
import kihonkankyousettei.model.KyuukaKoumoku;

/**
 * KyuukaKoumokuDao는 휴가 항목 정보를 관리하는 데이터 접근 객체입니다.
 * KyuukaKoumokuDaoは休暇項目情報を管理するデータアクセスオブジェクトです。
 */
public class KyuukaKoumokuDao {

    private static KyuukaKoumokuDao instance = new KyuukaKoumokuDao();

    /**
     * KyuukaKoumokuDao의 인스턴스를 반환합니다.
     * KyuukaKoumokuDaoのインスタンスを返します。
     */
    public static KyuukaKoumokuDao getInstance() {
        return instance;
    }

    /**
     * 새로운 휴가 항목을 데이터베이스에 추가합니다.
     * 新しい休暇項目をデータベースに追加します。
     *
     * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
     * @param kyuukaKoumoku 추가할 휴가 항목 객체 / 追加する休暇項目オブジェクト
     * @return 영향을 받은 행 수를 반환 / 影響を受けた行数を返します
     * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
     */
    public int insertKyuukaKoumoku(Connection conn, KyuukaKoumoku kyuukaKoumoku) throws SQLException {
        String query = "INSERT INTO KyuukaKoumoku (kyuukaKoumoku_id, kyuukaShurui, tekiyouKaishi, tekiyouShuuryou, shiyouUmu) "
                + "VALUES (KyuukaKoumoku_sequence.nextVal, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, kyuukaKoumoku.getKyuukaShurui());
            ps.setDate(2, kyuukaKoumoku.getTekiyouKaishi() != null ? new java.sql.Date(kyuukaKoumoku.getTekiyouKaishi().getTime()) : null);
            ps.setDate(3, kyuukaKoumoku.getTekiyouShuuryou() != null ? new java.sql.Date(kyuukaKoumoku.getTekiyouShuuryou().getTime()) : null);
            ps.setString(4, String.valueOf(kyuukaKoumoku.getShiyouUmu()));

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("휴가 항목 추가 실패: no rows affected. / 休暇項目追加失敗: 影響を受けた行がありません。");
            }
            return affectedRows;
        }
    }

    /**
     * 특정 ID로 휴가 항목을 조회합니다.
     * 特定のIDで休暇項目を照会します。
     *
     * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
     * @param kyuukaKoumokuId 조회할 휴가 항목 ID / 照会する休暇項目ID
     * @return 조회된 휴가 항목 객체, 없을 경우 null / 照会された休暇項目オブジェクト、存在しない場合はnull
     * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
     */
    public KyuukaKoumoku getKyuukaKoumokuById(Connection conn, int kyuukaKoumokuId) throws SQLException {
        KyuukaKoumoku kyuukaKoumoku = null;
        String query = "SELECT * FROM KyuukaKoumoku WHERE kyuukaKoumoku_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, kyuukaKoumokuId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                kyuukaKoumoku = new KyuukaKoumoku();
                kyuukaKoumoku.setKyuukaKoumoku_id(rs.getInt("kyuukaKoumoku_id"));
                kyuukaKoumoku.setKyuukaShurui(rs.getString("kyuukaShurui"));
                kyuukaKoumoku.setTekiyouKaishi(rs.getDate("tekiyouKaishi"));
                kyuukaKoumoku.setTekiyouShuuryou(rs.getDate("tekiyouShuuryou"));
                kyuukaKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));
            }
        }
        return kyuukaKoumoku;
    }

    /**
     * 모든 휴가 항목을 조회합니다.
     * すべての休暇項目を照会します。
     *
     * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
     * @return 휴가 항목 목록을 포함하는 ArrayList / 休暇項目リストを含むArrayList
     * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
     */
    public ArrayList<KyuukaKoumoku> getKyuukaKoumokuList(Connection conn) throws SQLException {
        ArrayList<KyuukaKoumoku> kyuukaList = new ArrayList<>();
        String query = "SELECT * FROM KyuukaKoumoku";

        try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                KyuukaKoumoku kyuukaKoumoku = new KyuukaKoumoku();
                kyuukaKoumoku.setKyuukaKoumoku_id(rs.getInt("kyuukaKoumoku_id"));
                kyuukaKoumoku.setKyuukaShurui(rs.getString("kyuukaShurui"));
                kyuukaKoumoku.setTekiyouKaishi(rs.getDate("tekiyouKaishi"));
                kyuukaKoumoku.setTekiyouShuuryou(rs.getDate("tekiyouShuuryou"));
                kyuukaKoumoku.setShiyouUmu(rs.getString("shiyouUmu").charAt(0));

                kyuukaList.add(kyuukaKoumoku);
            }
        }
        return kyuukaList;
    }

    /**
     * 특정 ID의 휴가 항목 정보를 업데이트합니다.
     * 特定のIDの休暇項目情報を更新します。
     *
     * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
     * @param kyuukaKoumoku 업데이트할 휴가 항목 객체 / 更新する休暇項目オブジェクト
     * @param kyuukaKoumokuId 업데이트할 휴가 항목 ID / 更新する休暇項目ID
     * @return 업데이트된 행 수를 반환 / 更新された行数を返します
     * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
     */
    public int updateKyuukaKoumoku(Connection conn, KyuukaKoumoku kyuukaKoumoku, int kyuukaKoumokuId) throws SQLException {
        String query = "UPDATE KyuukaKoumoku SET kyuukaShurui = ?, tekiyouKaishi = ?, tekiyouShuuryou = ?, shiyouUmu = ? "
                + "WHERE kyuukaKoumoku_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, kyuukaKoumoku.getKyuukaShurui());
            ps.setDate(2, kyuukaKoumoku.getTekiyouKaishi() != null ? new java.sql.Date(kyuukaKoumoku.getTekiyouKaishi().getTime()) : null);
            ps.setDate(3, kyuukaKoumoku.getTekiyouShuuryou() != null ? new java.sql.Date(kyuukaKoumoku.getTekiyouShuuryou().getTime()) : null);
            ps.setString(4, String.valueOf(kyuukaKoumoku.getShiyouUmu()));
            ps.setInt(5, kyuukaKoumokuId);

            return ps.executeUpdate();
        }
    }

    /**
     * 사원별 휴가 일수를 데이터베이스에 저장합니다.
     * 社員別の休暇日数をデータベースに保存します。
     *
     * @param shainId 사원 ID / 社員ID
     * @param kyuukaKoumokuId 휴가 항목 ID / 休暇項目ID
     * @param vacationDays 휴가 일수 / 休暇日数
     * @throws SQLException SQL 예외 발생 시 처리 / SQL例外発生時に処理
     */
    public void insertKyuukaNissuuShain(int shainId, int kyuukaKoumokuId, double vacationDays) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM ShainKyuukaNissuu WHERE shain_id = ? AND kyuukaKoumoku_id = ?";
        String insertQuery = "INSERT INTO ShainKyuukaNissuu (shain_id, kyuukaKoumoku_id, kyuukaNissuu) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
             PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {

            checkStatement.setInt(1, shainId);
            checkStatement.setInt(2, kyuukaKoumokuId);
            ResultSet rs = checkStatement.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                throw new SQLException("해당 사원과 휴가 항목의 조합이 이미 존재합니다. / 指定した社員と休暇項目の組み合わせが既に存在します。");
            }

            insertStatement.setInt(1, shainId);
            insertStatement.setInt(2, kyuukaKoumokuId);
            insertStatement.setDouble(3, vacationDays);
            int affectedRows = insertStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("사원별 휴가일수 추가 실패: no rows affected. / 社員別の休暇日数追加失敗: 影響を受けた行がありません。");
            }
        }
    }
}
