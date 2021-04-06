package store.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.model.service.StoreService;

/**
 * Servlet implementation class ReviewDeletServlet
 */
@WebServlet("/review/reviewDelete")
public class ReviewDeletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoreService storeService = new StoreService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reviewNo = Integer.parseInt(request.getParameter("review_no"));
		String rName1 = request.getParameter("rName1");
		String rName2 = request.getParameter("rName2");
		String rName3 = request.getParameter("rName3");
		System.out.println("rName1@ReviewDeleteServlet = [" + rName1 + "]");
		System.out.println("rName2@ReviewDeleteServlet = [" + rName2 + "]");
		System.out.println("rName3@ReviewDeleteServlet = [" + rName3 + "]");
		
		int result = storeService.deleteReview(reviewNo);
		String msg = result > 0 ? "게시물 삭제 성공!" : "게시물 삭제 실패!";
		
		//첨부파일이 있는 경우, 삭제처리
		if(result > 0 && !"".equals(rName1)) {
			String saveDirectory = getServletContext().getRealPath("/upload/store");
			File delFile = new File(saveDirectory, rName1);
			boolean bool = delFile.delete();
			msg += bool ? "(첨부파일삭제성공)" : "(첨부파일삭제실패)"; 
		}
		
		//3.사용자메세지 및 redirect처리
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/store/storedetaile");
	}

}
