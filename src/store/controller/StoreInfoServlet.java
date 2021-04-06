package store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* import common.util.MvcUtils; --> import common.MvcUtils;로 코드 변경 및 코드 위치 변경(util 패키지 따로 안만들고 common 패키지에 둠). */
import common.MvcUtils;
import member.model.vo.Member;
import store.model.service.BookmarkService;
import store.model.service.StoreService;
import store.model.vo.Bookmark;
import store.model.vo.Review;
import store.model.vo.Store;


/**
 * Servlet implementation class StoreInfoServlet
 */
@WebServlet("/store/storedetail") //아직이거없어서 오류남500번
public class StoreInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoreService storeservice = new StoreService();
	private BookmarkService bookmarkService = new BookmarkService();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int numPerPage = 3;
		int cpage = 1; 
		try {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		} catch(NumberFormatException e) {
			
		}
		
		
		request.setCharacterEncoding("utf-8");
		//1. 사용자 입력값 처리
//		int boardNo = Integer.parseInt("29");
		int boardNo = Integer.parseInt(request.getParameter("board_no"));
		
		//2. 업무로직 : 조회
		
		//board_no로 가게 받아오기
		Store store = storeservice.selectOne(boardNo);
		//Review review = storeservice.selectreviewOne(boardNo);
		
		//각 리뷰번호에 해당하는 거 가져오기
		List<Review> list = storeservice.selectreviewOne(boardNo, cpage, numPerPage);

		int totalContents = storeservice.selectTotalReview(boardNo);
		String url = request.getRequestURI() + "?board_no=" + boardNo;
		String pageBar = MvcUtils.getPageBar(totalContents, cpage, numPerPage, url);
		
		//북마크 체크 여부 강유정
		Member member = (Member)(request.getSession().getAttribute("memberLoggedIn"));

		if(member != null) {
			boolean isBookmark = bookmarkService.isBookmark(member.getMemberId(), store.getBoard_no());	
			request.setAttribute("isBookmark", isBookmark);
		}

		
		//3. view단처리 : jsp forwarding
		request.setAttribute("store", store);
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/view/store/storeview.jsp")
			   .forward(request, response);
	
	
	}

	

}