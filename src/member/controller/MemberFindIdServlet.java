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

/**
 * Servlet implementation class MemberFindIdServlet
 */
@WebServlet("/member/memberFindId")
public class MemberFindIdServlet extends HttpServlet {
	
	private MemberService memberService = new MemberService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		request.setAttribute("msg", session.getAttribute("msg"));
		request.setAttribute("userId", session.getAttribute("userId"));
		
		session.removeAttribute("msg");
		session.removeAttribute("userId");
		
		request.getRequestDispatcher("/WEB-INF/view/member/find_id.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String msg = "";
		
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		
		if(userName == null || "".equals(userName)) {
			msg = "이름을 입력해주세요.";
		}
		
		if(userEmail == null || "".equals(userEmail)) {
			msg = "이메일을 입력해주세요.";
		}
		
		if(!"".equals(userName) && !"".equals(userEmail)) {		
			Member member = new Member("", "", userName, "P", null, userEmail, "", null, "", 0, "");
			Member result = memberService.getUserId(member);
			
			if(result == null || result.getMemberId() == null || "".equals(result.getMemberId())) {
				msg = "조회된 아이디가 없습니다.";
			}else {				
				session.setAttribute("userId", result.getMemberId());
			}
		}
		
		session.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/member/memberFindId");
	}

}
