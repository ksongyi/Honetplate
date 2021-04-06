package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import store.model.vo.MyReview;
import store.model.vo.Review;
import static common.MvcUtils.*;
@WebServlet("/member/mypageCustomer/review")
public class MemberMyPageReviewServlet extends HttpServlet{
	private MemberService memberService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int cpage = 1;
		int numPerPage = 3;
		String memberId = request.getParameter("memberId");

		try {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		} catch (NumberFormatException e) {}
		
		List<Review> reviewList = memberService.selectMyReview(cpage, numPerPage, memberId);
		int totalContent = memberService.getMyReviewTotalContent(memberId);
		String path = request.getContextPath() + "/member/mypageCustomer/review?memberId=" + memberId;
		
		String pageBar = getMyReviewPageBar(totalContent, cpage, numPerPage, path);
		
		session.setAttribute("showMenu", "myReview");
		request.setAttribute("myReviewList", reviewList);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/member/mypageCustomer").forward(request, response);
		
	}
}	
