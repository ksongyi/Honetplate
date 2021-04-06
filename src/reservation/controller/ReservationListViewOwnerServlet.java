package reservation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.model.vo.Member;
import reservation.model.service.ReservationService;
import reservation.model.vo.Reservation;

@WebServlet("/reservation/listOwner")
public class ReservationListViewOwnerServlet extends HttpServlet{
	private ReservationService reservationService = new ReservationService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");

		List<Reservation> reservationListOwner = reservationService.selectReservationListOwner(memberId);
		new Gson().toJson(reservationListOwner,response.getWriter());
	}
}
