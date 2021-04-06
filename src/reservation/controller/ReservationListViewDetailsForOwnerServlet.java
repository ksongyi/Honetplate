package reservation.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import reservation.model.service.ReservationService;
import reservation.model.vo.Reservation;

@WebServlet("/reservation/listDetailsOwner")
public class ReservationListViewDetailsForOwnerServlet extends HttpServlet{
	private ReservationService reservationService = new ReservationService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			int reservationNo = Integer.parseInt(request.getParameter("reservationNo"));
			Reservation resv = reservationService.selectReservationListDetailsOwner(reservationNo);

			session.setAttribute("showMenu", "reservationList");
			request.setAttribute("reservation", resv);
			request.getRequestDispatcher("/WEB-INF/view/member/myPageOwner.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
			


	}
}
