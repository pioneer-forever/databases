package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Recorddao {

    /**
     * 查询学生的借阅记录
     */
    public ResultSet list(Connection con, int studentId) throws SQLException {
        String sql = "SELECT b.id as bookid, b.name, b.author, b.source, b.category " +
                     "FROM record r " +
                     "JOIN book b ON r.bookid = b.id " +
                     "WHERE r.stuid = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, studentId);
        return pstmt.executeQuery();
    }

    /**
     * 添加新的借阅记录
     */
    public int add(Connection con, int studentId, int bookId) throws SQLException {
        String sql = "INSERT INTO record (stuid, bookid) VALUES (?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, studentId);
        pstmt.setInt(2, bookId);
        return pstmt.executeUpdate();
    }

    /**
     * 删除借阅记录
     */
    public int delete(Connection con, int studentId, int bookId) throws SQLException {
        String sql = "DELETE FROM record WHERE stuid = ? AND bookid = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, studentId);
        pstmt.setInt(2, bookId);
        return pstmt.executeUpdate();
    }
}
