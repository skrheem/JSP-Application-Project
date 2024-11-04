package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import model.KyuuyoKoumoku;
import util.ObjectFormatter;

public class KyuuyoKoumokuDao {
	
	private static KyuuyoKoumokuDao kDao = new KyuuyoKoumokuDao();
	
	public static KyuuyoKoumokuDao getInstance() {
		return kDao;
	}
	
	public ArrayList<KyuuyoKoumoku> getKyuuyoKoumokuJouhou(Connection conn) {
		ArrayList<KyuuyoKoumoku> kList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from kyuuyokoumoku koujokoumoku where shiyouumu = 'Y' ORDER BY kyuuyokoumoku_id";
		
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				kList.add(new KyuuyoKoumoku(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBigDecimal(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBigDecimal(10), rs.getString(11)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		return kList;
	}
	
	public ArrayList<KyuuyoKoumoku> getKyuuyoKoumokuKingaku(Connection conn, Integer shain_id, String kyuuyoNengappi) {
		ArrayList<KyuuyoKoumoku> kList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * "
				+ "FROM ( "
				+ "    SELECT sk.kyuuyokoumoku_id, sk.KYUUYOKOUMOKU_KINGAKU "
				+ "    FROM shainkyuuyokiroku sk "
				+ "    WHERE sk.shain_id = ? "
				+ "      AND sk.KYUUYOKOUMOKU_NENGAPPI = TO_DATE(?, 'YYYY-MM-DD') "
				+ "      AND sk.KYUUYOKOUMOKU_ID != 1 "
				+ "    UNION ALL "
				+ "    SELECT kk.kyuuyokoumoku_id, NVL(kk.hikazeigendogaku, 0) AS hikazeigendogaku "
				+ "    FROM kyuuyokoumoku kk "
				+ "    WHERE kk.shiyouumu = 'Y' "
				+ "      AND kk.kyuuyokoumoku_mei != '基本給' "
				+ "      AND NOT EXISTS ( "
				+ "          SELECT 1  "
				+ "          FROM shainkyuuyokiroku sk "
				+ "          WHERE sk.shain_id = ? "
				+ "            AND sk.KYUUYOKOUMOKU_NENGAPPI = TO_DATE(?, 'YYYY-MM-DD') "
				+ "      ) "
				+ ") result";
		try {
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, shain_id);
			ps.setString(2, kyuuyoNengappi);
			ps.setInt(3, shain_id);
			ps.setString(4, kyuuyoNengappi);
			
			rs = ps.executeQuery();
			while(rs.next())
				kList.add(new KyuuyoKoumoku(rs.getInt(1), rs.getBigDecimal(2)));
			try {
				System.out.println(ObjectFormatter.formatList(kList));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
		}
		
		return kList;
	}
	
	public int insertKyuuyoKoumoku(Connection conn, String kyuuyokoumoku_mei, String kazeikubun, BigDecimal hikazeigendogaku, String bikou, String keisanhouhou, String zenshadani, String kintairenkei, String ikkatsushiharai, BigDecimal ikkatsushiharaigaku, String shiyouumu) {
	    PreparedStatement ps = null;
	    int rValue = 0;
	    String query = "INSERT INTO kyuuyokoumoku (kyuuyokoumoku_id, kyuuyokoumoku_mei, kazeikubun, hikazeigendogaku, bikou, keisanhouhou, zenshadani, kintairenkei, ikkatsushiharai, ikkatsushiharaigaku, shiyouumu) "
	            + "VALUES (kyuuyoKoumoku_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	        ps = conn.prepareStatement(query);
	        ps.setString(1, kyuuyokoumoku_mei);
	        ps.setString(2, kazeikubun);
	        ps.setBigDecimal(3, hikazeigendogaku);
	        ps.setString(4, bikou);
	        ps.setString(5, keisanhouhou);
	        ps.setString(6, zenshadani);
	        ps.setString(7, kintairenkei);
	        ps.setString(8, ikkatsushiharai);
	        ps.setBigDecimal(9, ikkatsushiharaigaku);
	        ps.setString(10, shiyouumu);

	        rValue = ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        JdbcUtil.close(ps);
	    }
	    return rValue;
	}
	
	public int updateKyuuyoKoumoku(Connection conn, Integer kyuuyokoumoku_id, String kyuuyokoumoku_mei, String keisanhouhou, String zenshadani, String bikou) {
	    PreparedStatement ps = null;
	    int rValue = 0;
	    String query = "UPDATE kyuuyokoumoku "
	            + "SET kyuuyokoumoku_mei = ?, keisanhouhou = ?, zenshadani = ?, bikou = ? "
	            + "WHERE kyuuyokoumoku_id = ?";

	    try {
	        ps = conn.prepareStatement(query);
	        ps.setString(1, kyuuyokoumoku_mei);
	        ps.setString(2, keisanhouhou);
	        ps.setString(3, zenshadani);
	        ps.setString(4, bikou);
	        ps.setInt(5, kyuuyokoumoku_id);

	        rValue = ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        JdbcUtil.close(ps);
	    }
	    return rValue;
	}

	public int deleteKyuuyoKoumoku(Connection conn, Integer kyuuyokoumoku_id) {
	    PreparedStatement ps = null;
	    int rValue = 0;
	    String query = "DELETE FROM kyuuyokoumoku WHERE kyuuyokoumoku_id = ?";

	    try {
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, kyuuyokoumoku_id);

	        rValue = ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        JdbcUtil.close(ps);
	    }
	    return rValue;
	}
}
