package store.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;
import static common.JDBCTemplate.commitOrRollBack;

import java.sql.Connection;
import java.util.List;

import store.model.dao.StoreDAO;
import store.model.vo.Review;

import store.model.vo.Store;


public class StoreService {

	private StoreDAO storedao = new StoreDAO();
	
	public Store selectOne(int boardNo) {
		Connection conn = getConnection();
		Store board = storedao.selectOne(conn, boardNo);
		close(conn);
		return board;
	}

	public List<Review> selectreviewOne(int boardNo,int cpage, int numPerPage) {
		Connection conn = getConnection();
		List<Review> list = storedao.selectreviewOne(conn, boardNo,cpage,numPerPage);
		return list;
	}

//	public int insertImgReview(ReviewImg reviewimg) {
//		Connection conn = getConnection();
//		int result = storedao.insertImgReview(conn, reviewimg);
//		if(result > 0) {
//			int review_no = storedao.selectNextReviewSeq(conn);
//			reviewimg.setReview_no(review_no);
//			commit(conn);
//		}
//		else rollback(conn);
//		
//		close(conn);
//		return result;
//	}

//	public int selectNextReviewSeq() {
//		Connection conn = getConnection();
//		int seq = storedao.selectNextReviewSeq(conn);
//		close(conn);
//		return seq;
//	}

	public int insertReview(Review review) {
		Connection conn = getConnection();
		int result = storedao.insertReview(conn, review);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}


	public int selectTotalReview(int boardNo) {
		Connection conn = getConnection();
		int totalReviewCount = storedao.selectTotalReview(conn, boardNo);
		close(conn);
		return totalReviewCount;
	}

	public int deleteReview(int reviewNo) {
		Connection conn = getConnection();
		int result = storedao.deleteReview(conn, reviewNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	
	//강유정 코드
	public List<String> selectStoreHashtag(int boardNo) {
		Connection conn = getConnection();
		List<String> hashtagList = storedao.selectStoreHashtag(conn, boardNo);
		
		close(conn);
		return hashtagList;
	}

	public int updateStoreInfo(Store store, List<String> hashTag) {
		Connection conn = getConnection();
		 int result = storedao.updateStoreInfo(conn,store);
		commitOrRollBack(result, conn);
		
		result += updateStoreHashtag(store.getBoard_no(), hashTag);
		
		return result;
	}
	
	public int updateStoreHashtag(int boardNo, List<String> hashTag) {
		Connection conn = getConnection();
		int result = storedao.updateStoreHashtag(conn, boardNo , hashTag);
		
		commitOrRollBack(result, conn);
		return result;
	}


}
