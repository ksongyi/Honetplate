package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import store.model.service.BookmarkService;

@WebServlet("/member/mypageCustomer/bookmark/remove")
public class MemberMyPageBookmarkRemoveServlet extends HttpServlet{
	private BookmarkService bookmarkService = new BookmarkService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int bookmarkNo = Integer.parseInt(request.getParameter("bookmarkNo"));
		int result = bookmarkService.deleteBookmark(bookmarkNo);
		Member member = (Member) session.getAttribute("memberLoggedIn");
		String memberId = member.getMemberId();
		
		System.out.println("@MemberMyPageBookmarkRemoveServlet result " + result);
		
		response.sendRedirect(request.getContextPath() + "/member/mypageCustomer/bookmark?memberId=" + memberId);
	}
}
