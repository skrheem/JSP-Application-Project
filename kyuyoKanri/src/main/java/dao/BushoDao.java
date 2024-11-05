package dao;
//김찬호 金燦鎬
//기본환경설정 담당자 정보 부서 관리 버튼
//担当者情報 部署管理
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kihonkankyousettei.model.Busho;

/**
 * BushoDao는 부서 관련 데이터베이스 접근을 담당하는 DAO 클래스입니다.
 * BushoDaoは部署関連のデータベースアクセスを担当するDAOクラスです。
 */
public class BushoDao {

	private static BushoDao instance = new BushoDao();

	/**
	 * BushoDao의 인스턴스를 반환합니다. BushoDaoのインスタンスを返します。
	 *
	 * @return BushoDao 인스턴스 / BushoDaoインスタンス
	 */
	public static BushoDao getInstance() {
		return instance;
	}

	/**
	 * 부서를 삽입하는 메서드입니다. 部署を挿入するメソッドです。
	 *
	 * @param conn  데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param busho 삽입할 부서 객체 / 挿入する部署オブジェクト
	 * @return 새로 생성된 busho_id를 반환 / 新しく生成されたbusho_idを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int insertBusho(Connection conn, Busho busho) throws SQLException {
		String insertSql = "INSERT INTO Busho (busho_id, busho_mei) VALUES (Busho_sequence.nextval, ?)";
		String selectSql = "SELECT Busho_sequence.currval FROM dual"; // 마지막으로 생성된 busho_id를 가져오는 쿼리 /
																		// 最後に生成されたbusho_idを取得するクエリ

		try (PreparedStatement insertStmt = conn.prepareStatement(insertSql);
				PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

			// INSERT 수행 / INSERTを実行
			insertStmt.setString(1, busho.getBusho_mei());
			int affectedRows = insertStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("부서 추가 실패: no rows affected。/ 部署の追加に失敗しました：影響を受けた行がありません。");
			}

			// 마지막으로 생성된 busho_id 가져오기 / 最後に生成されたbusho_idを取得
			try (ResultSet rs = selectStmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1); // 새로 생성된 busho_id 반환 / 新しく生成されたbusho_idを返します
				} else {
					throw new SQLException("부서 추가 실패: busho_id를 가져오지 못했습니다。/ 部署の追加に失敗しました：busho_idを取得できませんでした。");
				}
			}
		}
	}

	/**
	 * 부서를 업데이트하는 메서드입니다. 部署を更新するメソッドです。
	 *
	 * @param conn  데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param busho 업데이트할 부서 객체 / 更新する部署オブジェクト
	 * @return 업데이트된 행의 수를 반환 / 更新された行数を返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public int updateBusho(Connection conn, Busho busho) throws SQLException {
		String sql = "UPDATE Busho SET busho_mei = ? WHERE busho_id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, busho.getBusho_mei());
			stmt.setInt(2, busho.getBusho_id());
			return stmt.executeUpdate();
		}
	}

	/**
	 * 특정 ID로 부서를 조회하는 메서드입니다. 特定のIDで部署を照会するメソッドです。
	 *
	 * @param conn     데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @param busho_id 조회할 부서의 ID / 照会する部署のID
	 * @return 조회된 부서 객체, 존재하지 않으면 null 반환 / 照会された部署オブジェクト、存在しない場合はnullを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public Busho selectBushoById(Connection conn, Integer busho_id) throws SQLException {
		String sql = "SELECT busho_id, busho_mei FROM Busho WHERE busho_id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, busho_id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Busho(rs.getInt("busho_id"), rs.getString("busho_mei"));
				}
			}
		}
		return null;
	}

	/**
	 * 모든 부서 목록을 조회하는 메서드입니다. すべての部署リストを照会するメソッドです。
	 *
	 * @param conn 데이터베이스 연결 객체 / データベース接続オブジェクト
	 * @return 조회된 부서 목록을 반환 / 照会された部署リストを返します
	 * @throws SQLException SQL 처리 중 오류 발생 시 / SQL処理中にエラーが発生した場合
	 */
	public static ArrayList<Busho> selectAllBusho(Connection conn) throws SQLException {
		ArrayList<Busho> bushoList = new ArrayList<>();
		String sql = "SELECT busho_id, busho_mei FROM Busho";
		try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				bushoList.add(new Busho(rs.getInt("busho_id"), rs.getString("busho_mei")));
			}
		}
		return bushoList;
	}
}
