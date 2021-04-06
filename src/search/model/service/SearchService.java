package search.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import search.model.vo.RatingList;
import search.model.dao.SearchDao;
import search.model.vo.HashtagRankList;
import search.model.vo.NewList;
import search.model.vo.ReservationRankList;
import search.model.vo.ReviewList;
import search.model.vo.SearchTable;
import search.model.vo.ViewList;

public class SearchService {

	private SearchDao searchDao = new SearchDao();

	//조회결과 한페이지에 9개씩 나오게 
	public List<SearchTable> searchAll(String searchWord1, String searchWord2, String searchWord3, String searchWord4, String searchWord5, String searchWord6, int cpage,  int numPerPage ) {
		Connection conn = getConnection();
		List<SearchTable> searchTableList = searchDao.searchAll(conn, searchWord1, searchWord2, searchWord3, searchWord4, searchWord5, searchWord6, cpage, numPerPage);
		close(conn);
		return searchTableList;
	}
	
	//총 페이지 구하기
	public int selectTotalResult(String searchWord1, String searchWord2, String searchWord3, String searchWord4, String searchWord5, String searchWord6) {
		Connection conn = getConnection();
		int totalContents = searchDao.selectTotalResult(conn, searchWord1, searchWord2, searchWord3, searchWord4, searchWord5, searchWord6);
		close(conn);
		return totalContents;
	}

	//모두 조회 예약횟수별
	public List<ReservationRankList> searchAll() {
		Connection conn = getConnection();
		List<ReservationRankList> reservationRankList = searchDao.searchAll(conn);
		close(conn);
		return reservationRankList;
	}
	
	//해시태그별 조회수 1위
	public List<HashtagRankList> hashtagAll() {
		Connection conn = getConnection();
		List<HashtagRankList> hashtagRankList = searchDao.hashtagAll(conn);
		close(conn);
		return hashtagRankList;
	}
	
	//조회수순 다 조회
	public List<ViewList> viewAll() {
		Connection conn = getConnection();
		List<ViewList> viewList = searchDao.viewAll(conn);
		close(conn);
		return viewList;
	}

	//나중에 등록한 가게순 전부 
	public List<NewList> newAll() {
		Connection conn = getConnection();
		List<NewList> newList = searchDao.newAll(conn);
		close(conn);
		return newList;
	}
	
	//리뷰많은순
	public List<ReviewList> reviewAll() {
		Connection conn = getConnection();
		List<ReviewList> reviewList = searchDao.reviewAll(conn);
		close(conn);
		return reviewList;
	}

	//별점높은순
	public List<RatingList> ratingAll() {
		Connection conn = getConnection();
		List<RatingList> ratingList = searchDao.ratingAll(conn);
		close(conn);
		return ratingList;
	}
	
}
