package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

import static common.MvcUtils.*;

@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet{
	private MemberService memberService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String role = request.getParameter("connector");
		String memberId = request.getParameter("userId");
		String password = getEncryptedPassword(request.getParameter("password"));
		String saveId = request.getParameter("saveId");
		
		Member member = memberService.loginMember(role,memberId,password);
		
		if(member != null) {
			session.setAttribute("memberLoggedIn", member);
			Cookie c = new Cookie("cookieId", memberId);
			
			if(saveId != null) {
				c.setPath(request.getContextPath());
				c.setMaxAge(7 * 24 * 60 * 60);
				
			}else {
				c.setMaxAge(0);
			} 

			response.addCookie(c);
			response.sendRedirect(request.getContextPath() + "/landing.jsp"); 

		}else{
			session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");	
			response.sendRedirect(request.getContextPath() + "/member/login");
		}

	}
}
