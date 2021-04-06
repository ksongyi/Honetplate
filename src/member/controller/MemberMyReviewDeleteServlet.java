package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/member/mypageCustomer/review/delete")
public class MemberMyReviewDeleteServlet extends HttpServlet{
	private MemberService memberService = new MemberService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int reviewNo = Integer.parseInt(request.getParameter("review-no"));
		
		int result = memberService.deleteMyReview(reviewNo);
		String msg = result > 0 ? "리뷰가 삭제되었습니다." : "리뷰 삭제를 실패했습니다.";
		Member member = (Member)session.getAttribute("memberLoggedIn");
		String memberId = member.getMemberId();
		
		session.setAttribute("msg",msg);
		session.setAttribute("showMenu", "myReview");
		response.sendRedirect(request.getContextPath() + "/member/mypageCustomer/review?memberId=" + memberId);
	}
}
