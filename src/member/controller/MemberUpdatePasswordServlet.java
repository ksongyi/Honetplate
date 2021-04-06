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

import static common.MvcUtils.*;
@WebServlet("/member/updatePassword")
public class MemberUpdatePasswordServlet extends HttpServlet{
	private MemberService memberService = new MemberService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String memberId = request.getParameter("memberId");
		String password = getEncryptedPassword(request.getParameter("password-upd"));
		Member member = (Member)session.getAttribute("memberLoggedIn");

		int result = memberService.updateMemberPassword(memberId,password);
		String path = member.getMemberRole().equals(MemberService.MEMBER_ROLE_CUSTOMER) ? "/member/mypageCustomer" : "/member/mypageOwner";
		String msg = result > 0 ? "비밀번호가 변경되었습니다." : "비밀번호 변경을 실패했습니다. 고객센터로 문의 바랍니다.";
		
		session.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + path);
	}
}
