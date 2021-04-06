package store.model.dao;

import static common.JDBCTemplate.close;

import java.awt.print.Book;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.dao.MemberDAO;
import store.model.vo.Bookmark;
import store.model.vo.MyBookmark;

public class BookmarkDAO {
private Properties prop = new Properties();
	
	public BookmarkDAO() {
		String fileName = "/sql/bookmark/bookmark-query.properties";
		String path = BookmarkDAO.class.getResource(fileName).getPath();
		
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertBookmark(Connection conn, Bookmark bookmark) {
		String sql = prop.getProperty("insertBookmark");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookmark.getBoardNo());
			pstmt.setString(2, bookmark.getMemberId());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}

	public int deleteBookmark(Connection conn, String id, int storeNo) {
		String sql = prop.getProperty("deleteBookmark");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storeNo);
			pstmt.setString(2, id);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteBookmark(Connection conn, int bookmarkNo) {
		String sql = prop.getProperty("deleteBookmarkNo");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookmarkNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	

	public boolean isBookmark(Connection conn, String memberId, int storeNo) {
		String sql = prop.getProperty("isBookmark");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean result = false;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storeNo);
			pstmt.setString(2, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1) > 0 ? true : false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int getTotalContent(Connection conn, String memberId) {
		String sql = prop.getProperty("getTotalContent");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		List<Bookmark> bookmarkList = new ArrayList<Bookmark>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
				
			}
			while (rset.next()) {
				Bookmark bookmark = new Bookmark();
				bookmark.setBookmakrkNo(rset.getInt("bmark_no"));
				bookmark.setBoardNo(rset.getInt("board_no"));
				bookmark.setMemberId(rset.getString("member_id"));
				
				bookmarkList.add(bookmark);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}

	public List<Bookmark> selectBookmarkList(Connection conn, int cpage, int numPerPage, String memberId) {
		String sql = prop.getProperty("selectBookmarkList");
		sql = sql.replace("#", memberId);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Bookmark> bookmarkList = new ArrayList<Bookmark>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ((cpage - 1) * numPerPage) + 1);
			pstmt.setInt(2, numPerPage * cpage);
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				MyBookmark mybookmark = new MyBookmark();
				mybookmark.setBookmakrkNo(rset.getInt("bmark_no"));
				mybookmark.setBoardNo(rset.getInt("board_no"));
				mybookmark.setMemberId(rset.getString("member_id"));
				mybookmark.setFileName(rset.getString("filename"));
				mybookmark.setStoreName(rset.getString("store_name"));
				mybookmark.setHashtag1(rset.getString("hashtag_keyword1"));
				mybookmark.setHashtag2(rset.getString("hashtag_keyword2"));
				mybookmark.setHashtag3(rset.getString("hashtag_keyword3"));
				
				bookmarkList.add(mybookmark);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return bookmarkList;
	}


	
}
