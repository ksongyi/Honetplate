package member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.vo.Member;
import reservation.model.dao.ReservationDAO;
import reservation.model.vo.Reservation;
import store.model.vo.MyReview;
import store.model.vo.Review;

import static common.JDBCTemplate.*;
public class MemberDAO {
	private Properties prop = new Properties();
	
	public MemberDAO() {
		String fileName = "/sql/member/member-query.properties";
		String path = MemberDAO.class.getResource(fileName).getPath();
		
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
// ======================================== 강유정 =========================================
	public int selectNoshowFreq(Connection conn, String memberId) {
		String sql = prop.getProperty("selectNoshowFreq");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int noshowFreq = -1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) noshowFreq = rset.getInt("noshow_freq");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return noshowFreq;
	}

	public boolean isMemberIdDuplicate(Connection conn, String memberId) {
		String sql = prop.getProperty("isMemberIdDuplicate");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean result = false;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			result = rset.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public boolean isMemberEmailDuplicate(Connection conn, String email) {
		String sql = prop.getProperty("isMemberEmailDuplicate");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean result = false;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rset = pstmt.executeQuery();
			result = rset.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int insertMemberCustomer(Connection conn, Member member) {
		String sql = prop.getProperty("insertMemberCustomer");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberRole());
			pstmt.setDate(5, member.getBirthDay());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setObject(8, null); // 사업자 번호 NULL처리
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertMemberOwner(Connection conn, Member member) {
		String sql = prop.getProperty("insertMemberOwner");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberRole());
			pstmt.setDate(5, member.getBirthDay());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getCorporateNo()); 
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member selectMembmerOne(Connection conn, String memberId) {
		String sql = prop.getProperty("selectMemberOne");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = setMember(rset);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}
	

	public int updateMemberPassword(Connection conn, String memberId, String password) {
		String sql = prop.getProperty("updateMemberPassword");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, memberId);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateMemberInformation(Connection conn,String memberId, String email, String phone) {
		String sql = prop.getProperty("updateMemberInformation");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, phone);
			pstmt.setString(3, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member loginMember(Connection conn, String role, String memberId, String password) {
		String sql = prop.getProperty("loginMember");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, role);
			pstmt.setString(2, memberId);
			pstmt.setString(3, password);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = setMember(rset);			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

	private Member setMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		
		member.setMemberId(rset.getString("member_id"));
		member.setMemberPassword(rset.getString("member_password"));
		member.setMemberName(rset.getString("member_name"));
		member.setMemberRole(rset.getString("member_role"));
		member.setBirthDay(rset.getDate("birthday"));
		member.setEmail(rset.getString("email"));
		member.setPhone(rset.getString("phone"));
		member.setEnrollDate(rset.getDate("enrolldate"));
		member.setQuitYn(rset.getString("quit_yn"));
		member.setNoshowFreq(rset.getInt("noshow_freq"));		
		member.setCorporateNo(rset.getString("corporate_no"));
		
		return member;
	}

	public List<Review> selectMyReview(Connection conn, int cpage, int numPerPage, String memberId) {
		String sql = prop.getProperty("selectMyReview");
		sql = sql.replace("#", memberId);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> reviewList = new ArrayList<Review>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ((cpage - 1) * numPerPage) + 1);
			pstmt.setInt(2, numPerPage * cpage);
			pstmt.setString(3, memberId);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				MyReview review = new MyReview();
				review.setReview_no(rset.getInt("review_no"));
				review.setReview_content(rset.getString("review_content"));
				review.setReview_date(rset.getDate("review_date"));
				review.setRating(rset.getInt("rating"));
				review.setBoard_no(rset.getInt("board_no"));
				review.setReview_del_yn(rset.getString("review_del_yn"));
				review.setStoreName(rset.getString("store_name"));

				reviewList.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return reviewList;
	}

	public int getMyReviewTotalContent(Connection conn, String memberId) {
		String sql = prop.getProperty("getMyReviewTotalContent");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);

			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}

	public int deleteMyReview(Connection conn, int reviewNo) {
		String sql = prop.getProperty("deleteMyReview");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	public int getMemberBoardNo(Connection conn, String memberId) {
		String sql = prop.getProperty("getMemberBoardNo");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	

	
// ======================================== 이수연 =========================================
	
	public Member selectUserId(Connection conn, Member member) {
		String sql = prop.getProperty("selectMemberId");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member result = new Member();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getEmail());

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result.setMemberId(rset.getString("member_id"));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int updateQuitYn(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateQuitYn");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
// ======================================== 김자경 =========================================
	public int updateMemberNoshowFreq(Connection conn, String memberId) {
		int result = 0;
		int noShowFreq = selectNoshowFreq(conn, memberId);
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNoshowFreq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ++noShowFreq);
			pstmt.setString(2, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
