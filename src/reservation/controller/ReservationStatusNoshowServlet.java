package reservation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import reservation.model.service.ReservationService;

/**
 * Servlet implementation class ReservationStatusNoshowServlet
 */
@WebServlet("/reservation/request/noshow")
public class ReservationStatusNoshowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationService reservationService = new ReservationService();
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		int reservationNo = Integer.parseInt(request.getParameter("resv-board-no"));
		String memberId = request.getParameter("resv-member-id");
		
		int noShowYnresult = reservationService.updateReservationStatusNoshow(reservationNo);
		int noShowFreqResult = memberService.updateMemberNoshowFreq(memberId);
		int noshowResult = reservationService.updateReservationNoshowNum(memberId);
		
		String msg = ((noShowYnresult > 0) && (noShowFreqResult > 0)) ? "노쇼 처리되었습니다." : "노쇼 처리를 실패했습니다. 고객센터로 문의 바랍니다.";
		
		session.setAttribute("msg", msg);
		session.setAttribute("showMenu", "reservationTable");
		
		response.sendRedirect(request.getContextPath() + "/myPageOwner.jsp");
	}

}
