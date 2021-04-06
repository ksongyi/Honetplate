package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import member.model.service.MemberService;
import member.model.vo.Member;
import reservation.model.vo.Reservation;

@WebServlet("/member/boardNo")
public class MemberGetBoardNoServlet extends HttpServlet{
	private MemberService memberService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("memberLoggedIn");
		String memberId = member.getMemberId();
		int boardNo = memberService.getMemberBoardNo(memberId);


		new Gson().toJson(boardNo,response.getWriter());
	}

}
