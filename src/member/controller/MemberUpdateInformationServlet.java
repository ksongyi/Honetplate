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

@WebServlet("/member/updateInformation")
public class MemberUpdateInformationServlet extends HttpServlet{
	private MemberService memberService = new MemberService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String memberId = request.getParameter("memberId");
		String email = request.getParameter("update-email");
		String phone = request.getParameter("update-phone");
		String role = request.getParameter("role");
		System.out.println(memberId);
		
		String path = role.equals(MemberService.MEMBER_ROLE_CUSTOMER) ? "/member/mypageCustomer" : "/member/mypageOwner";
		int result = memberService.updateMemberInformation(memberId, email, phone);
		
		if(result > 0) {
			Member member = memberService.selectMemberOne(memberId);
			session.setAttribute("memberLoggedIn", member);
		}
		System.out.println("@MemberUpdateInformationServlet" + result);
		response.sendRedirect(request.getContextPath() + path);
	}

}
