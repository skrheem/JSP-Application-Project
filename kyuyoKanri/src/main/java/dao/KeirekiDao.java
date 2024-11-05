package dao;
//김찬호 金燦鎬
//기본환경설정 사원등록
//基本環境設定 社員登録
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.connection.ConnectionProvider;
import kihonkankyousettei.model.Keireki;

/**
 * KeirekiDao는 경력 정보 관련 데이터베이스 접근을 담당하는 DAO 클래스입니다.
 * KeirekiDaoはキャリア情報関連のデータベースアクセスを担当するDAOクラスです。
 */
public class KeirekiDao {

    private static KeirekiDao instance = new KeirekiDao();

    /**
     * KeirekiDao의 인스턴스를 반환합니다.
     * KeirekiDaoのインスタンスを返します。
     *
     * @return KeirekiDao 인스턴스 / KeirekiDaoインスタンス
     */
    public static KeirekiDao getInstance() {
        return instance;
    }

    /**
     * 경력 정보를 데이터베이스에 삽입하는 메서드입니다.
     * キャリア情報をデータベースに挿入するメソッドです。
     *
     * @param conn    데이터베이스 연결 객체 / データベース接続オブジェクト
     * @param keireki 삽입할 경력 정보 객체 / 挿入するキャリア情報オブジェクト
     * @return 영향을 받은 행의 수 / 影響を受けた行数
     * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
     */
    public int insertKeireki(Connection conn, Keireki keireki) throws SQLException {
        String sql = "INSERT INTO Keireki (keireki_id, shain_id, kaishaNama, nyusha_bi, taisha_bi, kinmuKikan, saigoShokui, tantouShigoto, taishoRiyuuCode) "
                + "VALUES (keireki_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[] { "keireki_id" })) {
            ps.setInt(1, keireki.getShain_id());
            ps.setString(2, keireki.getKaishaNama());
            ps.setDate(3, new java.sql.Date(keireki.getNyusha_bi().getTime()));
            ps.setDate(4, new java.sql.Date(keireki.getTaisha_bi().getTime()));
            ps.setString(5, keireki.getKinmuKikan());
            ps.setString(6, keireki.getSaigoShokui());
            ps.setString(7, keireki.getTantouShigoto());
            ps.setString(8, keireki.getTaishoRiyuuCode());
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("경력 추가 실패: no rows affected. / キャリアの追加に失敗しました：影響を受けた行がありません。");
            }

            // 생성된 keireki_id 가져오기
            // 生成されたkeireki_idを取得
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    keireki.setKeireki_id(generatedKeys.getInt(1)); // keireki_id 설정 / keireki_id設定
                } else {
                    throw new SQLException("경력 추가 실패: ID를 얻지 못했습니다. / キャリアの追加に失敗しました：IDを取得できませんでした。");
                }
            }
            return affectedRows;
        }
    }

    /**
     * 주어진 경력 정보를 데이터베이스에서 업데이트하는 메서드입니다.
     * 指定されたキャリア情報をデータベースで更新するメソッドです。
     *
     * @param conn    데이터베이스 연결 객체 / データベース接続オブジェクト
     * @param keireki 업데이트할 경력 정보 객체 / 更新するキャリア情報オブジェクト
     * @return 업데이트된 행의 수를 반환 / 更新された行数を返します
     * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
     */
    public int updateKeireki(Connection conn, Keireki keireki) throws SQLException {
        String sql = "UPDATE Keireki SET shain_id = ?, kaishaNama = ?, nyusha_bi = ?, taisha_bi = ?, "
                + "kinmuKikan = ?, saigoShokui = ?, tantouShigoto = ?, taishoRiyuuCode = ? WHERE keireki_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, keireki.getShain_id());
            ps.setString(2, keireki.getKaishaNama());
            ps.setDate(3, new java.sql.Date(keireki.getNyusha_bi().getTime()));
            ps.setDate(4, new java.sql.Date(keireki.getTaisha_bi().getTime()));
            ps.setString(5, keireki.getKinmuKikan());
            ps.setString(6, keireki.getSaigoShokui());
            ps.setString(7, keireki.getTantouShigoto());
            ps.setString(8, keireki.getTaishoRiyuuCode());
            ps.setInt(9, keireki.getKeireki_id());
            return ps.executeUpdate();
        }
    }

    /**
     * 특정 경력 ID로 경력 정보를 조회하는 메서드입니다.
     * 特定のキャリアIDでキャリア情報を照会するメソッドです。
     *
     * @param conn       데이터베이스 연결 객체 / データベース接続オブジェクト
     * @param keireki_id 조회할 경력 ID / 照会するキャリアID
     * @return 조회된 경력 정보 객체, 존재하지 않으면 null 반환 / 照会されたキャリア情報オブジェクト、存在しない場合はnullを返します
     * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
     */
    public Keireki getKeirekiById(Connection conn, int keireki_id) throws SQLException {
        String sql = "SELECT k.*, s.namae_kana FROM Keireki k "
                + "JOIN Shain s ON k.shain_id = s.shain_id WHERE k.keireki_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, keireki_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Keireki keireki = new Keireki();
                keireki.setKeireki_id(rs.getInt("keireki_id"));
                keireki.setShain_id(rs.getInt("shain_id"));
                keireki.setKaishaNama(rs.getString("kaishaNama"));
                keireki.setNyusha_bi(rs.getDate("nyusha_bi"));
                keireki.setTaisha_bi(rs.getDate("taisha_bi"));
                keireki.setKinmuKikan(rs.getString("kinmuKikan"));
                keireki.setSaigoShokui(rs.getString("saigoShokui"));
                keireki.setTantouShigoto(rs.getString("tantouShigoto"));
                keireki.setTaishoRiyuuCode(rs.getString("taishoRiyuuCode"));
                return keireki;
            }
        }
        return null;
    }

    /**
     * 모든 경력 정보를 조회하는 메서드입니다.
     * すべてのキャリア情報を照会するメソッドです。
     *
     * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
     * @return 모든 경력 정보를 포함하는 ArrayList / すべてのキャリア情報を含むArrayList
     * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
     */
    public ArrayList<Keireki> selectAllKeireki(Connection conn) throws SQLException {
        String sql = "SELECT k.*, s.namae_kana FROM Keireki k JOIN Shain s ON k.shain_id = s.shain_id";
        ArrayList<Keireki> keirekiList = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Keireki keireki = new Keireki();
                keireki.setKeireki_id(rs.getInt("keireki_id"));
                keireki.setShain_id(rs.getInt("shain_id"));
                keireki.setKaishaNama(rs.getString("kaishaNama"));
                keireki.setNyusha_bi(rs.getDate("nyusha_bi"));
                keireki.setTaisha_bi(rs.getDate("taisha_bi"));
                keireki.setKinmuKikan(rs.getString("kinmuKikan"));
                keireki.setSaigoShokui(rs.getString("saigoShokui"));
                keireki.setTantouShigoto(rs.getString("tantouShigoto"));
                keireki.setTaishoRiyuuCode(rs.getString("taishoRiyuuCode"));
                keirekiList.add(keireki);
            }
        }
        return keirekiList;
    }
}
