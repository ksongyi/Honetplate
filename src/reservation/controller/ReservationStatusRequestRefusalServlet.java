package reservation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.model.service.ReservationService;

@WebServlet("/reservation/request/statusRefusal")
public class ReservationStatusRequestRefusalServlet extends HttpServlet{
	private ReservationService reservationService = new ReservationService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//예약 거절
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		int boardNo = Integer.parseInt(request.getParameter("resv-board-no"));
		
		int result = reservationService.updateReservationStatusRefusal(boardNo);
		String msg = result > 0 ? "예약이 거절되었습니다." : "예약 거절을 실패했습니다. 고객센터로 문의 바랍니다.";
		
		session.setAttribute("msg", msg);
		session.setAttribute("showMenu", "reservationTable");
		
		response.sendRedirect(request.getContextPath() + "/member/mypageOwner");
	}
}
