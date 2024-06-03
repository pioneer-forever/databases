package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Student;

public class studao {

    public Student login(Connection con, Student user) throws Exception {
        Student result = null;
        String sql = "select * from student where id=? and password=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, user.getId());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            result = new Student();
            result.setId(rs.getInt("id"));
            result.setUsername(rs.getString("name"));
            result.setPassword(rs.getString("password"));
        }
        rs.close();
        pstmt.close();
        return result;
    }

    public Student loginA(Connection con, Student user) throws Exception {
        Student result = null;
        String sql = "select * from auser where id=? and password=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, user.getId());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            result = new Student();
            result.setId(rs.getInt("id"));
            result.setUsername(rs.getString("username"));
            result.setPassword(rs.getString("password"));
        }
        rs.close();
        pstmt.close();
        return result;
    }

    public int register(Connection con, Student user) throws Exception {
        String sql = "select * from user where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, user.getId());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            rs.close();
            pstmt.close();
            return 0; // 用户名已存在
        }
        rs.close();
        pstmt.close();

        sql = "insert into user(id, password) values(?, ?)";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, user.getId());
        pstmt.setString(2, user.getPassword());
        pstmt.execute();
        pstmt.close();
        return 1;
    }

    public int add(Connection con, Student user) throws Exception {
        String sql = "select * from auser where username=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            rs.close();
            pstmt.close();
            return 0; // 用户名已存在
        }
        rs.close();
        pstmt.close();

        sql = "insert into auser(username, password) values(?, ?)";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.execute();
        pstmt.close();
        return 1;
    }

    public int delete(Connection con, Student user) throws Exception {
        String sql = "select * from auser where username=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            rs.close();
            pstmt.close();
            return 0; // 用户名不存在
        }
        rs.close();
        pstmt.close();

        sql = "delete from auser where username=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        pstmt.execute();
        pstmt.close();
        return 1;
    }
}
