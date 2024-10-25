package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;

import dao.ShainDao;
import model.Shain;
import jdbc.connection.ConnectionProvider;

public class ShainService {

    private ShainDao shainDao = new ShainDao();

    
    // 사원 리스트를 가져오는 서비스 메서드
    public ArrayList<Shain> getShainList() throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return shainDao.getAllShains(conn);
        }
    } 
    
    // 사원 상태(재직/퇴직)별 카운트
    public int[] getShainStatusCount() throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return shainDao.getShainJyoutaiCNT(conn);
        }
    }
    
    
    // 사원 정렬 서비스 메서드
    public ArrayList<Shain> getSortedShainList(String sortCriteria1, String sortDirection1, 
                                          String sortCriteria2, String sortDirection2, 
                                          String sortCriteria3, String sortDirection3) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return shainDao.getShainListBySortCriteria(conn, sortCriteria1, sortDirection1, 
                                                       sortCriteria2, sortDirection2, 
                                                       sortCriteria3, sortDirection3);
        }
    }
    
    
    // 페이지별 사원 리스트 가져오는 서비스 메서드
    public ArrayList<Shain> getShainListByPage(int pageNumber, int itemsPerPage) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return shainDao.getShainListNumber(conn, pageNumber, itemsPerPage);
        }
    }
    
    
}
