package reservation.model.service;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import reservation.model.dao.ReservationDAO;
import reservation.model.vo.Reservation;
import static common.JDBCTemplate.*;
public class ReservationService {
	ReservationDAO reservationDAO = new ReservationDAO();
	
// =============================== 강유정 ===============================================
	
	public List<Reservation> selectReservationList() {
		Connection conn = getConnection();
		List<Reservation> reservationList = reservationDAO.selectReservationList(conn);
		
		close(conn);
		return reservationList;
	}

	public int insertReservation(Reservation reservation) {
		Connection conn = getConnection();
		int result = reservationDAO.insertReservation(conn,reservation);
		updateReservationNum(reservation.getBoardNo());
		
		commitOrRollBack(result, conn);
		return result;
	}
	
	public void updateReservationNum(int storeNo) {
		Connection conn = getConnection();
		int result = reservationDAO.updateReservationNum(conn, storeNo);

		commitOrRollBack(result, conn);
	}

	public List<Reservation> selectReservationListOwner(String memberId) {
		Connection conn = getConnection();
		List<Reservation> reservationListOwner = reservationDAO.selectReservationListOwner(conn,memberId);
		
		close(conn);
		return reservationListOwner;
	}

	public List<Reservation> selectReservationListCustomer(String memberId) {
		Connection conn = getConnection();
		List<Reservation> reservationListCustomer = reservationDAO.selectReservationListCustomer(conn,memberId);
		
		close(conn);
		return reservationListCustomer;
	}

	public Reservation selectReservationListDetailsOwner(int reservationNo) {
		Connection conn = getConnection();
		Reservation reservation = reservationDAO.selectReservationListDetailsOwner(conn,reservationNo);
		
		close(conn);
		return reservation;
	}

	public int updateReservationStatus(int boardNo) {
		Connection conn = getConnection();
		int result = reservationDAO.updateReservationStatus(conn, boardNo);
		
		commitOrRollBack(result, conn);
		return result;
	}
	
	public List<String> selectReservationFileName(int boardNo) {
		Connection conn = getConnection();
		List<String>  fileNameList = reservationDAO.selectReservationFileName(conn, boardNo);
		
		close(conn);
		return fileNameList;
	}
	
	
	
// =============================== 김자경 ===============================================	
	
	public int updateReservationStatusRefusal(int boardNo) {
		Connection conn = getConnection();
		int result = reservationDAO.updateReservationStatusRefusal(conn, boardNo);
		
		commitOrRollBack(result, conn);
		return result;
	}
	
	public int updateDepositYn(int reservationNo) {
		Connection conn = getConnection();
		int result = reservationDAO.updateDepositYn(conn, reservationNo);
		
		commitOrRollBack(result, conn);
		return result;
	}

	public int updateReservationStatusVisited(int reservationNo) {
		Connection conn = getConnection();
		int result = reservationDAO.updateReservationStatusVisited(conn, reservationNo);
		commitOrRollBack(result, conn);
		return result;
	}

	public int updateReservationStatusNoshow(int reservationNo) {
		Connection conn = getConnection();
		int result = reservationDAO.updateReservationStatusNoshow(conn, reservationNo);
		commitOrRollBack(result, conn);
		return result;
	}

	public int updateReservationNoshowNum(String memberId) {
		Connection conn = getConnection();
		int result = reservationDAO.updateReservationNoshowNum(conn, memberId);
		commitOrRollBack(result, conn);
		return result;
	}

	


	
}
