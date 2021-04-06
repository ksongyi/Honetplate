package reservation.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sun.xml.internal.ws.Closeable;

import member.model.dao.MemberDAO;
import reservation.model.vo.MyReservation;
import reservation.model.vo.Reservation;
import static common.JDBCTemplate.*;
public class ReservationDAO {
	private Properties prop = new Properties();
	private MemberDAO memberDao = new MemberDAO();

	public ReservationDAO() {
		String fileName = "/sql/reservation/reservation-query.properties";
		String path = ReservationDAO.class.getResource(fileName).getPath();
		
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//===================================== 강유정 =========================================
	
	public List<Reservation> selectReservationList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReservationList");
		List<Reservation> reservationList = new ArrayList<Reservation>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				reservationList.add(setReservation(rset));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return reservationList;
	}

	public int insertReservation(Connection conn, Reservation reservation) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertReservation");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservation.getMemberId());
			pstmt.setInt(2, reservation.getBoardNo());
			pstmt.setInt(3, reservation.getAdult());
			pstmt.setInt(4, reservation.getChild());
			pstmt.setTimestamp(5, reservation.getRsvDate());
			pstmt.setString(6, reservation.getReservationPhone());
			pstmt.setInt(7, reservation.getNoShowFreq());
			pstmt.setString(8, reservation.getDepositYn());
			pstmt.setObject(9, reservation.getReservationStatus());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Reservation> selectReservationListOwner(Connection conn, String memberId) {
		String sql = prop.getProperty("selectReservationListOwner");
		sql = sql.replace("#", memberId);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reservation> reservationListOwner = new ArrayList<Reservation>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				reservationListOwner.add(setReservation(rset));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return reservationListOwner;
	}
	
	public List<Reservation> selectReservationListCustomer(Connection conn, String memberId) {
		String sql = prop.getProperty("selectReservationListCustomer");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reservation> reservationListCustomer = new ArrayList<Reservation>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				MyReservation rsv = new MyReservation(); 

				rsv.setReservationNo(rset.getInt("reservation_no"));
				rsv.setMemberId(rset.getString("member_id"));
				rsv.setBoardNo(rset.getInt("board_no"));
				rsv.setAdult(rset.getInt("adult"));
				rsv.setChild(rset.getInt("child"));
				rsv.setRsvDate(rset.getTimestamp("rsv_date"));
				rsv.setNoShowYn(rset.getString("noshow_yn"));
				rsv.setReservationPhone(rset.getString("reservation_phone"));
				rsv.setDepositYn(rset.getString("deposit_yn"));
				rsv.setReservationStatus(rset.getString("reservation_status"));
				rsv.setNoShowFreq(rset.getInt("no_show"));
				rsv.setStoreName(rset.getString("store_name")); 
				
				reservationListCustomer.add(rsv);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return reservationListCustomer;
	}
	
	public Reservation selectReservationListDetailsOwner(Connection conn, int reservationNo) {
		String sql = prop.getProperty("selectReservationListDetailsOwner");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Reservation reservation = new Reservation();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservationNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				reservation = setReservation(rset);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return reservation;
	}
	
	public int updateReservationStatus(Connection conn, int boardNo) {
		String sql = prop.getProperty("updateReservationStatus");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateReservationNum(Connection conn, int storeNo) {
		String sql = prop.getProperty("updateReservationNum");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<String> selectReservationFileName(Connection conn, int boardNo) {
		String sql = prop.getProperty("selectReservationFileName");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<String> fileNameList = new ArrayList<String>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				fileNameList.add(rset.getString("reviewrenamedfilename1"));
				fileNameList.add(rset.getString("reviewrenamedfilename2"));
				fileNameList.add(rset.getString("reviewrenamedfilename3"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return fileNameList;
	}
	
	
	private Reservation setReservation(ResultSet rset) throws SQLException {
		Reservation rsv = new Reservation(); 

		rsv.setReservationNo(rset.getInt("reservation_no"));
		rsv.setMemberId(rset.getString("member_id"));
		rsv.setBoardNo(rset.getInt("board_no"));
		rsv.setAdult(rset.getInt("adult"));
		rsv.setChild(rset.getInt("child"));
		rsv.setRsvDate(rset.getTimestamp("rsv_date"));
		rsv.setNoShowYn(rset.getString("noshow_yn"));
		rsv.setReservationPhone(rset.getString("reservation_phone"));
		rsv.setDepositYn(rset.getString("deposit_yn"));
		rsv.setReservationStatus(rset.getString("reservation_status"));
		rsv.setNoShowFreq(rset.getInt("no_show"));
	
		return rsv;
	}
	
	
	
	
//===================================== 김자경 =========================================
	
	public int updateReservationStatusRefusal(Connection conn, int boardNo) {
		String sql = prop.getProperty("updateReservationStatusRefusal");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateDepositYn(Connection conn, int reservationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateDepositYn");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservationNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReservationStatusVisited(Connection conn, int reservationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateVisited");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservationNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReservationStatusNoshow(Connection conn, int reservationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNoshow");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservationNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReservationNoshowNum(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNoshowNum");
		int noShowFreq = memberDao.selectNoshowFreq(conn, memberId);
		//update reservation set no_show = ? where member_id = ?
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noShowFreq);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
}
