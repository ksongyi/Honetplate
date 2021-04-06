package search.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import search.model.service.SearchService;
import search.model.vo.ViewList;
import search.model.vo.HashtagRankList;
import search.model.vo.NewList;
import search.model.vo.RatingList;
import search.model.vo.ReservationRankList;
import search.model.vo.ReviewList;


@WebServlet("/landing.jsp")
public class MainListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SearchService searchService = new SearchService();
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		

		//2. 업무로직 : 검색
		List<ReservationRankList> reservationRankList = searchService.searchAll();
		//검색팝업창용 조회수순
		List<ViewList> viewList = searchService.viewAll();
		//검색팝업창용 새로생긴가게순
		List<NewList> newList = searchService.newAll();
		//검색팝업창용 리뷰 많은순
		List<ReviewList> reviewList = searchService.reviewAll();
		//검색팝업창용 별점 높은순
		List<RatingList> ratingList = searchService.ratingAll();
		//해시태그별 조회수1위 + 가게사진
		List<HashtagRankList> hashtagRankList = searchService.hashtagAll();
		
		//3. view단 처리
		//예약 많은순 + 사진
		request.setAttribute("reservationRankList", reservationRankList);
		//해시태그별 조회수1위 + 가게사진
		request.setAttribute("hashtagRankList", hashtagRankList);
		//검색팝업창용 조회수순
		request.setAttribute("viewList", viewList);
		//검색팝업창용 새로생긴가게순
		request.setAttribute("newList", newList);
		//검색팝업창용 리뷰 많은순
		request.setAttribute("reviewList", reviewList);
		//검색팝업창용 별점 높은순
		request.setAttribute("ratingList", ratingList);
		request.getRequestDispatcher("/WEB-INF/view/search/main.jsp")
			   .forward(request, response);
	}
	
	


}
