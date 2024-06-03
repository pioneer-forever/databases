package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Area;
import util.StrUtil;

public class Areadao {

    public int add(Connection con, Area area) throws Exception {
        String sql = "INSERT INTO area VALUES (NULL, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, area.getCategory());
        pstmt.setString(2, area.getLocation());
        return pstmt.executeUpdate();
    }

    public ResultSet list(Connection con, Area area) throws Exception {
        StringBuffer sb = new StringBuffer("SELECT * FROM area");
        if (StrUtil.isNotEmpty(area.getCategory())) {
            sb.append(" AND category LIKE '%" + area.getCategory() + "%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("AND", "WHERE"));
        return pstmt.executeQuery();
    }
    
    public List<Area> list(Connection con) throws Exception {
        List<Area> areaList = new ArrayList<>();
        String sql = "SELECT * FROM area";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Area area = new Area();
            area.setId(rs.getInt("id"));
            area.setCategory(rs.getString("category"));
            area.setLocation(rs.getString("location"));
            areaList.add(area);
        }
        return areaList;
    }

    public int delete(Connection con, String id) throws Exception {
        String sql = "DELETE FROM area WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }

    public int update(Connection con, Area area) throws Exception {
        String sql = "UPDATE area SET category=?, location=? WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, area.getCategory());
        pstmt.setString(2, area.getLocation());
        pstmt.setInt(3, area.getId());
        return pstmt.executeUpdate();
    }
}
