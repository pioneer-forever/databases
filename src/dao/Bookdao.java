package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Book;
import model.Student;
import util.DbUtil;
import util.StrUtil;

public class Bookdao {

	public int add1(Connection con,Book book) throws SQLException {
	        // 检查馆藏资源是否为正数
	        if (book.getSource() <= 0) {
	            throw new IllegalArgumentException("馆藏资源必须为正数");
	        }
	        
	        // 插入图书记录
	        String sql = "INSERT INTO book (id, name, author, source, category) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, book.getId());
	        pstmt.setString(2, book.getName());
	        pstmt.setString(3, book.getAuthor());
	        pstmt.setInt(4, book.getSource());
	        pstmt.setString(5, book.getCategory());
	        return pstmt.executeUpdate();
	    } 

	
	public int add(Connection con, Book book) throws Exception {
		String sql = "insert into book values(null,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book.getName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setInt(3, book.getSource());
		pstmt.setString(4, book.getCategory());
		return pstmt.executeUpdate();
	}

	public ResultSet list(Connection con, Book book) throws Exception {
		StringBuffer sb = new StringBuffer("select * from book b, area bt where b.Id=bt.id");
		if (StrUtil.isNotEmpty(book.getName())) {
			sb.append(" and b.bookName like '%" + book.getName() + "%'");
		}
		if (StrUtil.isNotEmpty(book.getAuthor())) {
			sb.append(" and b.author like '%" + book.getAuthor() + "%'");
		}
		if (book.getCategory() != null && !book.getCategory().equals("-1")) {
			sb.append(" and b.category='" + book.getCategory() + "'");
		}

		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public ResultSet list1(Connection con, Book book) throws Exception {
		StringBuffer sb = new StringBuffer("select * from book b, area bt where b.Id=bt.id");
		if (StrUtil.isNotEmpty(book.getName())) {
			sb.append(" and b.bookName like '%" + book.getName() + "%'");
		}
		if (StrUtil.isNotEmpty(book.getAuthor())) {
			sb.append(" and b.author like '%" + book.getAuthor() + "%'");
		}
		if (book.getCategory() != null && !book.getCategory().equals("-1")) {
			sb.append(" and b.category='" + book.getCategory() + "'");
		}

		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public int delete(Connection con, String id) throws Exception {
		String sql = "delete from book where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}

	public int update(Connection con, Book book) throws Exception {
		String sql = "update book set name=?, author=?, source=?, category=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book.getName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setInt(3, book.getSource());
		pstmt.setString(4, book.getCategory());
		pstmt.setInt(5, book.getId());
		return pstmt.executeUpdate();
	}

	public int update1(Connection con, Book book, int studentId) throws Exception {
		// 更新book表
		String sql = "UPDATE book SET source=? WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, book.getSource());
		pstmt.setInt(2, book.getId());
		int updateNum = pstmt.executeUpdate();

		if (updateNum == 1) {
			// 插入到record表
			String sql1 = "INSERT INTO record (stuid, bookid) VALUES (?, ?)";
			PreparedStatement pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, studentId);
			pstmt1.setInt(2, book.getId());
			pstmt1.executeUpdate();

			// 更新student表的borrow字段
			String sql2 = "UPDATE student SET borrow = borrow + 1 WHERE id = ? AND borrow < 5";
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, studentId);
			int borrowUpdateNum = pstmt2.executeUpdate();

			return borrowUpdateNum == 1 ? 1 : 0;
		} else {
			return 0;
		}
	}

	public int update2(Connection con, Book book) throws Exception {
		String sql = "update book set source=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, book.getSource());
		pstmt.setInt(2, book.getId());
		return pstmt.executeUpdate();
		
	}
	
	  public boolean existBookByBookTypeId(Connection con, String bookTypeId)
	  throws Exception { String sql = "select * from t_book where bookTypeId=?";
	  PreparedStatement pstmt = con.prepareStatement(sql); pstmt.setString(1,
	  bookTypeId); ResultSet rs = pstmt.executeQuery(); return rs.next(); }
	
}










