package store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import store.model.service.BookmarkService;
import store.model.vo.Bookmark;

@WebServlet("/store/bookmark/add")
public class StoreBookmarkAddServlet extends HttpServlet{
	private BookmarkService bookmarkService = new BookmarkService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("memberId");
		int storeNo = Integer.parseInt(request.getParameter("store_no"));

		Bookmark bookmark = new Bookmark(0,storeNo,id);
		int result = bookmarkService.insertBookmark(bookmark);

		new Gson().toJson(result,response.getWriter());
	}
}
