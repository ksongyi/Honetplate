package reservation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import reservation.model.service.ReservationService;
import reservation.model.vo.Reservation;

@WebServlet("/reservation/listCustomer")
public class ReservationListViewCustomerServlet extends HttpServlet{
	private ReservationService reservationService = new ReservationService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		List<Reservation> reservationListCustomer = reservationService.selectReservationListCustomer(memberId);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(reservationListCustomer,response.getWriter());
	}
}
