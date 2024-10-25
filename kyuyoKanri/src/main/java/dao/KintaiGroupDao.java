package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import model.KintaiGroup;

public class KintaiGroupDao {
    private Connection connection;

    public KintaiGroupDao(Connection connection) {
        this.connection = connection;
    }

    // 그룹 추가
    public void insertKintaiGroup(KintaiGroup group) {
        String query = "INSERT INTO KintaiGroup (group_id, group_name) VALUES (?, ?)";
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, group.getGroup_id());
            ps.setString(2, group.getGroup_name());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
        }
    }

    // 그룹 정보 수정
    public void updateKintaiGroup(KintaiGroup group) {
        String query = "UPDATE KintaiGroup SET group_name = ? WHERE group_id = ?";
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, group.getGroup_name());
            ps.setInt(2, group.getGroup_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
        }
    }

    // 그룹 삭제
    public void deleteKintaiGroup(int group_id) {
        String query = "DELETE FROM KintaiGroup WHERE group_id = ?";
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, group_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps);
        }
    }

    // 특정 그룹 조회
    public KintaiGroup getKintaiGroupById(int group_id) {
        KintaiGroup group = null;
        String query = "SELECT * FROM KintaiGroup WHERE group_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, group_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                group = new KintaiGroup();
                group.setGroup_id(rs.getInt("group_id"));
                group.setGroup_name(rs.getString("group_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(ps);
        }

        return group;
    }

    // 모든 그룹 목록 조회
    public ArrayList<KintaiGroup> getAllKintaiGroups() {
    	ArrayList<KintaiGroup> groupList = new ArrayList<>();
        String query = "SELECT * FROM KintaiGroup";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                KintaiGroup group = new KintaiGroup();
                group.setGroup_id(rs.getInt("group_id"));
                group.setGroup_name(rs.getString("group_name"));
                groupList.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(ps);
        }

        return groupList;
    }
}
