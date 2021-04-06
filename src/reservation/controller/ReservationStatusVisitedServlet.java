package reservation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.model.service.ReservationService;

/**
 * Servlet implementation class ReservationStatusVisitedServlet
 */
@WebServlet("/reservation/request/visited")
public class ReservationStatusVisitedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationService reservationService = new ReservationService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		int reservationNo = Integer.parseInt(request.getParameter("resv-board-no"));
		
		int result = reservationService.updateReservationStatusVisited(reservationNo);
		String msg = result > 0 ? "방문완료 처리되었습니다." : "방문완료 처리를 실패했습니다. 고객센터로 문의 바랍니다.";
		
		session.setAttribute("msg", msg);
		session.setAttribute("showMenu", "reservationTable");
		
		response.sendRedirect(request.getContextPath() + "/myPageOwner.jsp");
	}

}
