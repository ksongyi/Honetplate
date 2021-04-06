package reservation.controller;

/* 아래 코드 The Import ... never used 떠서 제거함
 * import sun.java2d.pipe.SpanShapeRenderer.Simple;
 * import java.text.SimpleDateFormat;
 */
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import reservation.model.service.ReservationService;
import reservation.model.vo.Reservation;
import store.model.service.StoreService;
import store.model.vo.Store;

@WebServlet("/reservation/request")
public class ReservationRequestServlet extends HttpServlet{
	private ReservationService reservationService = new ReservationService();
	private MemberService memberServcie = new MemberService();
	private StoreService storeService = new StoreService();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("store_no"));
		Store store = storeService.selectOne(boardNo);
		List<String> fileName = reservationService.selectReservationFileName(boardNo); 
		
		request.setAttribute("fileNameList", fileName);
		request.setAttribute("store", store);
		request.getRequestDispatcher("/WEB-INF/view/reservation/reservationRequest.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		try {
			
			String day = request.getParameter("input-day");
			String time = request.getParameter("time");
			int child = Integer.parseInt(request.getParameter("child-num"));
			int adult = Integer.parseInt(request.getParameter("adult-num"));
			String memberId = request.getParameter("memberId");
			String phone = request.getParameter("customer-phone");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int noShow = memberServcie.selectNoshowFreq(memberId);
			
			day = day.substring(0,4) + "-" + day.substring(5,7) + "-" + day.substring(8,10) + " " + time + ":00.0";
			Timestamp rsvDate = Timestamp.valueOf(day);
			
			Reservation reservation = new Reservation(0, memberId, boardNo, adult, child, rsvDate, null, phone, "N", null, noShow);
			int result = reservationService.insertReservation(reservation);
			String msg = result > 0 ? "예약되었습니다." : "예약을 실패했습니다. 고객센터로 문의 바랍니다.";
			
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/store/storedetail?board_no=" + boardNo);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
}
