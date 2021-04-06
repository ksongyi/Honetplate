package store.model.service;

import store.model.dao.BookmarkDAO;
import store.model.vo.Bookmark;
import static common.JDBCTemplate.*;

import java.awt.print.Book;
import java.sql.Connection;
import java.util.List;
public class BookmarkService {
	private BookmarkDAO bookmarkDAO = new BookmarkDAO();

	public int insertBookmark(Bookmark bookmark) {
		Connection conn = getConnection();
		int result = bookmarkDAO.insertBookmark(conn,bookmark);
		
		commitOrRollBack(result, conn);
		return result;
	}

	public int deleteBookmark(String id, int storeNo) {
		Connection conn = getConnection();
		int result = bookmarkDAO.deleteBookmark(conn, id, storeNo);
		
		commitOrRollBack(result, conn);
		return result;
	}
	
	public int deleteBookmark(int bookmarkNo) {
		Connection conn = getConnection();
		int result = bookmarkDAO.deleteBookmark(conn, bookmarkNo);
		
		commitOrRollBack(result, conn);
		return result;
	}

	public boolean isBookmark(String memberId, int storeNo) {
		Connection conn = getConnection();
		boolean result = bookmarkDAO.isBookmark(conn, memberId, storeNo);
		
		close(conn);
		return result;
	}

	public int getTotalContent(String memberId) {
		Connection conn = getConnection();
		int result = bookmarkDAO.getTotalContent(conn, memberId);
		
		close(conn);
		return result;
	}

	public List<Bookmark> selectBookmarkList(int cpage, int numPerPage, String memberId) {
		Connection conn = getConnection();
		List<Bookmark> bookmarkList = bookmarkDAO.selectBookmarkList(conn, cpage, numPerPage, memberId);
		
		close(conn);
		return bookmarkList;
	}

	
	
	
}
