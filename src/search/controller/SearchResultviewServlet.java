package search.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MvcUtils;
import search.model.service.SearchService;
import search.model.vo.NewList;
import search.model.vo.RatingList;
import search.model.vo.ReviewList;
import search.model.vo.SearchTable;
import search.model.vo.ViewList;

/**
 * Servlet implementation class SearchResultviewServlet
 */
@WebServlet("/SearchResult")
public class SearchResultviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SearchService searchService = new SearchService();   

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 사용자입력값 검색결과
				int cpage = 1;
				String searchWord = request.getParameter("searchWord");
				try {
					cpage = Integer.parseInt(request.getParameter("cpage"));
 					request.getParameter("searchWord");
 					request.getParameter("searchWord");
 					request.getParameter("searchWord");
 					request.getParameter("searchWord");
 					request.getParameter("searchWord");
				} catch(NumberFormatException e) {
					//예외가 발생한 경우, cpage는 1로 유지한다.
				}
				int numPerPage = 9;
				

		

		
		//2. 업무로직 : 검색
		//검색결과
		List<SearchTable> searchTableList = searchService.searchAll(searchWord, searchWord, searchWord, searchWord, searchWord, searchWord, cpage, numPerPage);
		//검색팝업창용 조회수순
		List<ViewList> viewList = searchService.viewAll();
		//검색팝업창용 새로생긴가게순
		List<NewList> newList = searchService.newAll();
		//검색팝업창용 리뷰 많은순
		List<ReviewList> reviewList = searchService.reviewAll();
		//검색팝업창용 별점 높은순
		List<RatingList> ratingList = searchService.ratingAll();
		
		
		
		
		//페이지바 처리
		int totalContents = searchService.selectTotalResult(searchWord, searchWord, searchWord, searchWord, searchWord, searchWord);
		String url = request.getRequestURI();
		String pageBar = MvcUtils.getPageBar(totalContents, cpage, numPerPage, url, searchWord, searchWord, searchWord, searchWord, searchWord, searchWord );
		
		
		//3. view단 처리
		//검색결과
		request.setAttribute("searchTableList", searchTableList);
		//페이지바
		request.setAttribute("pageBar", pageBar);
		//검색팝업창용 조회수순
		request.setAttribute("viewList", viewList);
		//검색팝업창용 새로생긴가게순
		request.setAttribute("newList", newList);
		//검색팝업창용 리뷰 많은순
		request.setAttribute("reviewList", reviewList);
		//검색팝업창용 별점 높은순
		request.setAttribute("ratingList", ratingList);
		request.getRequestDispatcher("/WEB-INF/view/search/searchResult.jsp")
			   .forward(request, response);

	}

}
