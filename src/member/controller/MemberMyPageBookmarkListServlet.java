package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import store.model.service.BookmarkService;
import store.model.vo.Bookmark;
import static common.MvcUtils.*;

@WebServlet("/member/mypageCustomer/bookmark")
public class MemberMyPageBookmarkListServlet extends HttpServlet{
	private BookmarkService bookmarkService = new BookmarkService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberId = request.getParameter("memberId");
	
		int cpage = 1;
		int numPerPage = 3;
		String path = request.getContextPath() + "/member/mypageCustomer/bookmark?memberId=" + memberId;

		try {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		} catch (NumberFormatException e) {}
		
		List<Bookmark> bookmarkList = bookmarkService.selectBookmarkList(cpage, numPerPage, memberId);
		int totalContent = bookmarkService.getTotalContent(memberId);
		String bookmarkPageBar = getMyReviewPageBar(totalContent, cpage, numPerPage, path);
		
		session.setAttribute("showMenu", "myBookmark");
		request.setAttribute("myBookmarkList", bookmarkList);
		request.setAttribute("bookmarkPageBar", bookmarkPageBar);
		request.getRequestDispatcher("/member/mypageCustomer").forward(request, response);
	}


}
