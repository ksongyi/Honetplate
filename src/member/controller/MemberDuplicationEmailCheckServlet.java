package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.model.service.MemberService;

@WebServlet("/member/duplicationEmail")
public class MemberDuplicationEmailCheckServlet extends HttpServlet{
	private MemberService memberService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		boolean isDuplicate = memberService.isMemberEmailDuplicate(email);
		String result = isDuplicate ? "true" : "false";
		
		new Gson().toJson(result,response.getWriter());
	}
}
