package member.controller;

import static common.MvcUtils.getEncryptedPassword;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/member/memberDeleteOwner")
public class MemberDeleteOwnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		request.setAttribute("msg", session.getAttribute("msg"));
		session.removeAttribute("msg");
		
		String memberId = "shift";
		Member member = memberService.selectMemberOne(memberId);
		
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/view/member/myPageOwner.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("memberLoggedIn");
		String password = request.getParameter("memberPassword");
		String msg = "";
		
		Member member = memberService.selectMemberOne(loginMember.getMemberId());
		if(member != null && getEncryptedPassword(password).equals(member.getMemberPassword())) {
	
			int result = memberService.updateQuitYn(member.getMemberId());

			if(result>0) {
				msg = "성공적으로 회원정보를 삭제했습니다.";
	
				session.invalidate();
				session = request.getSession();
			}else {
				msg = "회원정보삭제에 실패했습니다.";
			}
		}else {
			
		}
		
		session.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/member/login");
	}

}
