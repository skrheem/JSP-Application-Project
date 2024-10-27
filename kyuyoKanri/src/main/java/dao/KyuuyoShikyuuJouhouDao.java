package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//임세규 林世圭 급여입력・관리 페이지 / 給与入力・管理ページ
public class KyuuyoShikyuuJouhouDao {
	public static void main(String args[]) {
		try {
			Connection conn = ConnectionProvider.getConnection();
			System.out.println(KyuuyoShikyuuJouhouDao.getInstance().getKyuuyoSanteiKaishi(conn));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static KyuuyoShikyuuJouhouDao kDao = new KyuuyoShikyuuJouhouDao();
	
	public static KyuuyoShikyuuJouhouDao getInstance() {
		return kDao;
	}
	
	// 임세규 林世圭
	// 급여입력관리 페이지에서 급여지급일에 출력할 데이터
	// 給与入力管理ページで給与支給日に出力するデータ
	public String getKyuuyoShikyuuBi(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT kyuuyoShikyuu_bi FROM KyuuyoShikyuuJouhou";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			 if (rs.next()) {
				return rs.getString(1).split(" ")[0];
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return null;
	}
	
	public String getKyuuyoSanteiKaishi(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT kyuuyoSanteiKaishi FROM KyuuyoShikyuuJouhou";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			 if (rs.next()) {
				return rs.getString(1).split(" ")[0];
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return null;
	}
	
	public String getKyuuyoSanteiShuuryou(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT kyuuyoSanteiShuuryou FROM KyuuyoShikyuuJouhou";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			 if (rs.next()) {
				return rs.getString(1).split(" ")[0];
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return null;
	}
}
