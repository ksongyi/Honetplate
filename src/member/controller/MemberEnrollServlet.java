package member.controller;

import java.io.IOException;

import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import static common.MvcUtils.*;
@WebServlet("/member/enroll")
public class MemberEnrollServlet extends HttpServlet {
	private MemberService memberService = new MemberService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/memberEnroll.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String msg = "";
		int result = 0;

		String userId = request.getParameter("userId");
		String password = getEncryptedPassword(request.getParameter("password"));
		String userName = request.getParameter("userName");
		String birthDay = request.getParameter("birthday");
		String userEmail = request.getParameter("userEmail");
		String userTel = request.getParameter("userTel");
		String userRole = request.getParameter("memberRole");

		Date birthDate = null;
		if (birthDay != null && !"".equals(birthDay))
			birthDate = Date.valueOf(birthDay);

		Member member = new Member(userId, password, userName, userRole, birthDate, userEmail, userTel, null, "", 0,
				null);
		System.out.println(member);

		if (userRole.equals(MemberService.MEMBER_ROLE_CUSTOMER)) {
			result = memberService.insertMemberCustomer(member);

		} else {
			String userNumber = request.getParameter("userNumber");
			member.setCorporateNo(userNumber); 
			result =  memberService.insertMemberOwner(member);
		}

		msg = result > 0 ? "가입이 완료되었습니다." : "가입을 실패했습니다. 고객센터로 문의 바랍니다.";
		session.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/landing.jsp");
	}
}
