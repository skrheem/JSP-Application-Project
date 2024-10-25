package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import jdbc.*;
import model.Shain;
import dao.ShainDao;

public class getShainList {
	
	
	
	//사원 리스트를 불러오는 서비스
	private ShainDao shainDao = new ShainDao();
    private int size = 10;  // 페이지당 표시할 사원 수

    public ShainPage getShainPage(int pageNum) {
        try (Connection conn = DBCPInit.getConnection()) {
            int total = shainDao.getShainCountAll(conn);  // 총 사원 수
            List<Shain> content = shainDao.getShainListByPage(conn, (pageNum - 1) * size, size);  // 페이지별 사원 리스트 가져오기
            return new ShainPage(total, pageNum, size, content);  // 페이지 정보 생성 후 반환
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
