package payment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.model.service.PaymentService;
import payment.model.vo.Payment;
import reservation.model.service.ReservationService;


@WebServlet("/payment/payDeposit")
public class PaymentDataInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaymentService paymentService = new PaymentService();
	private ReservationService reservationService = new ReservationService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/memberEnroll.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int resvNo = Integer.parseInt(request.getParameter("resv-no"));
		int boardNo = Integer.parseInt(request.getParameter("resv-board-no"));
		String pg = request.getParameter("pg");
		String payMethod = request.getParameter("payMethod");
		String merchantUid = request.getParameter("merchantUid");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String buyerEmail = request.getParameter("buyerEmail");
		String buyerName = request.getParameter("buyerName");
		String buyerTel = request.getParameter("buyerTel");
		String memberId = request.getParameter("resv-memberId");
		
		/*
		 * System.out.println("resvNo@payServlet = " + resvNo);
		 * System.out.println("boardNo@payServlet = " + boardNo);
		 * System.out.println("pg@payServlet = " + pg);
		 * System.out.println("payMethod@payServlet = " + payMethod);
		 * System.out.println("merchantUid@payServlet = " + merchantUid);
		 * System.out.println("merchantName@payServlet = " + merchantName);
		 * System.out.println("amount@payServlet = " + amount);
		 * System.out.println("buyerEmail@payServlet = " + buyerEmail);
		 * System.out.println("buyerName@payServlet = " + buyerName);
		 * System.out.println("buyerTel@payServlet = " + buyerTel);
		 * System.out.println("memberId@payServlet = " + memberId);
		 */
		
		Payment py = new Payment(merchantUid, memberId, boardNo, buyerName, buyerTel, buyerEmail, amount, payMethod, null, pg, null, resvNo);
		
		int insertResult = paymentService.insertPayment(py);
		int updateResult = reservationService.updateDepositYn(py.getReservationNo());
		
		String insertMsg = insertResult > 0 ? "결제정보 저장 성공" : "결제정보 저장 실패";
		String updateMsg = updateResult > 0 ? "결제상태 변경 성공" : "결제상태 변경 성공";
		request.getSession().setAttribute("insertMsg", insertMsg);
		request.getSession().setAttribute("updateMsg", updateMsg);
		request.getSession().setAttribute("py", py);
		response.sendRedirect(request.getContextPath() + "/member/mypageCustomer");
	}

}
