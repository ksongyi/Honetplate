package member.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import member.model.dao.MemberDAO;
import member.model.vo.Member;
import store.model.vo.Review;
public class MemberService {
	public static final String MEMBER_ROLE_BUSINESS = "R";
	public static final String MEMBER_ROLE_CUSTOMER = "P";
	private MemberDAO memberDAO = new MemberDAO();
	
//	 ============================= 강유정 ======================================
	
	public int selectNoshowFreq(String memberId) {
		Connection conn = getConnection();
		int result =  memberDAO.selectNoshowFreq(conn, memberId);
		
		close(conn);
		return result;
	}

	public boolean isMemberIdDuplicate(String memberId) {
		Connection conn = getConnection();
		boolean result = memberDAO.isMemberIdDuplicate(conn, memberId);
		
		close(conn);
		return result;
	}

	public boolean isMemberEmailDuplicate(String email) {
		Connection conn = getConnection();
		boolean result = memberDAO.isMemberEmailDuplicate(conn, email);
		
		close(conn);
		return result;
	}

	public int insertMemberCustomer(Member member) {
		Connection conn = getConnection();
		int result = memberDAO.insertMemberCustomer(conn, member);
		
		commitOrRollBack(result, conn);
		return result;
	}

	public Member selectMemberOne(String memberId) {
		Connection conn = getConnection();
		Member member = memberDAO.selectMembmerOne(conn, memberId);
		
		close(conn);
		return member;
	}

	public int updateMemberPassword(String memberId, String password) {
		Connection conn = getConnection();
		int result = memberDAO.updateMemberPassword(conn, memberId, password);
		
		commitOrRollBack(result, conn);
		return result;
	}
	

	public int insertMemberOwner(Member member) {
		Connection conn = getConnection();
		int result = memberDAO.insertMemberOwner(conn, member);
		
		commitOrRollBack(result, conn);
		return result;
	}

	public Member loginMember(String role, String memberId, String password) {
		Connection conn = getConnection();
		Member member = memberDAO.loginMember(conn,role,memberId,password);
		
		close(conn);
		return member;
	}

	public int updateMemberInformation(String memberId, String email, String phone) {
		Connection conn = getConnection();
		int result = memberDAO.updateMemberInformation(conn, memberId, email, phone);
		
		commitOrRollBack(result, conn);
		return result;
	}

	public List<Review> selectMyReview(int cpage, int numPerPage, String memberId) {
		Connection conn = getConnection();
		List<Review> reviewList = memberDAO.selectMyReview(conn, cpage, numPerPage, memberId);
		
		close(conn);
		return reviewList;
	}

	public int getMyReviewTotalContent(String memberId) {
		Connection conn = getConnection();
		int result = memberDAO.getMyReviewTotalContent(conn, memberId);
		
		close(conn);
		return result;
	}

	public int deleteMyReview(int reviewNo) {
		Connection conn = getConnection();
		int result = memberDAO.deleteMyReview(conn, reviewNo);
		
		commitOrRollBack(result, conn);
		return result;
	}
	
	public int getMemberBoardNo(String memberId) {
		Connection conn = getConnection();
		int result = memberDAO.getMemberBoardNo(conn, memberId);
		
		close(conn);
		return result;
	}
	
//	 ============================= 이수연 ======================================
	
	public Member getUserId(Member member) {
		Connection conn = getConnection();
		Member result = memberDAO.selectUserId(conn, member);
		
		close(conn);
		return result;
	}
	
	
	public int updateQuitYn(String userId) {
		Connection conn = getConnection();
		int result = memberDAO.updateQuitYn(conn, userId);
		
		commitOrRollBack(result, conn);
		return result;
	}
	
//	 ============================= 김자경 ======================================
	
	public int updateMemberNoshowFreq(String memberId) {
		Connection conn = getConnection();
		int result = memberDAO.updateMemberNoshowFreq(conn, memberId);
		
		commitOrRollBack(result, conn);
		return result;
	}

}
