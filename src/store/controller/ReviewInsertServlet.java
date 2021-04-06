package store.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.MvcFileRenamePolicy;
import store.model.service.StoreService;
import store.model.vo.Review;


/**
 * Servlet implementation class ReviewWriteServlet
 */
@WebServlet("/store/writereview")
public class ReviewInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoreService storeService = new StoreService();
	private String boardNo = "";
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("store_no"));
		
		request.setAttribute("board_no", boardNo);
		request.getRequestDispatcher("/WEB-INF/view/store/review.jsp")
		   .forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = getServletContext().getRealPath("/upload/store");// / -> Web Root Directory
		System.out.println("saveDirectory@BoardEnrollServlet = " + saveDirectory);
		
		int maxPostSize = 10 * 1024 * 1024;
		//ReviewImg reviewimg = new ReviewImg();
		String encoding = "utf-8";
		
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		
		MultipartRequest multipartReq = 
				new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
//		int seq = storeService.selectNextReviewSeq();
//
//		reviewimg.setReview_no(seq);
//		reviewimg.setReview_original_filename1(multipartReq.getOriginalFileName("REVIEW_ORIGINAL_FILENAME1"));
//		reviewimg.setReview_original_filename2(multipartReq.getOriginalFileName("REVIEW_ORIGINAL_FILENAME2"));
//		reviewimg.setReview_original_filename3(multipartReq.getOriginalFileName("REVIEW_ORIGINAL_FILENAME3"));
//		reviewimg.setReview_renamed_filename1(multipartReq.getFilesystemName("REVIEW_ORIGINAL_FILENAME1"));
//		reviewimg.setReview_renamed_filename2(multipartReq.getFilesystemName("REVIEW_ORIGINAL_FILENAME2"));
//		reviewimg.setReview_renamed_filename3(multipartReq.getFilesystemName("REVIEW_ORIGINAL_FILENAME3"));
		
		//		ReviewImg reviewimg = new ReviewImg(0, Review_original_filename1,Review_original_filename2,Review_original_filename3,
//				Review_renamed_filename1,Review_renamed_filename2,Review_renamed_filename3);
		//		Enumeration fileNames = multipartReq.getFileNames();
//		while(fileNames.hasMoreElements()) {
//			String parameter = (String) fileNames.nextElement();
//			String fileName = multipartReq.getOriginalFileName(parameter);
//			String fileRealName = multipartReq.getFilesystemName(parameter);
//		}
		
		request.setCharacterEncoding("UTF-8");
		
		
		String reviewContent = multipartReq.getParameter("review_content");
		int boardNo = Integer.parseInt(multipartReq.getParameter("board_no"));
		int rating =Integer.parseInt(multipartReq.getParameter("star"));
		System.out.println("reviewRatuion@ReviewinsertServlet"+rating);

		
		String memberId = multipartReq.getParameter("memberId");
		String reviewOriginalFileName1 = multipartReq.getOriginalFileName("file1");
		String reviewOriginalFileName2 = multipartReq.getOriginalFileName("file2");
		String reviewOriginalFileName3 = multipartReq.getOriginalFileName("file3");
		String reviewRenamedFileName1 = multipartReq.getFilesystemName("file1");
		String reviewRenamedFileName2 = multipartReq.getFilesystemName("file2");
		String reviewRenamedFileName3 = multipartReq.getFilesystemName("file3");
		
		Review review = 
				new Review(0, reviewContent, null,rating,boardNo,memberId,null,reviewOriginalFileName1,
						reviewOriginalFileName2,reviewOriginalFileName3,reviewRenamedFileName1,reviewRenamedFileName2,reviewRenamedFileName3);
		System.out.println("board-before@ReviewInsertServlet = " + review);
		int result = storeService.insertReview(review);
		System.out.println("reviewr@ReviewInsertServlet = " + review);
		
		
		//int img_result = storeService.selectimg(review_no);
		
		String msg = result > 0 ? "리뷰를 확인하세요." : "다시 한번 확인하세요.";
				
		//3.사용자 피드백(msg) 및 redirect처리 
		//DML이후 반드시 요청url을 변경할 것
		request.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/store/storedetail?board_no=" + boardNo);
		
	}

}
