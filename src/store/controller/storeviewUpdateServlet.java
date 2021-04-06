package store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import store.model.service.StoreService;
import store.model.vo.Store;

@WebServlet("/store/storeUpdate")
public class storeviewUpdateServlet extends HttpServlet{
	private StoreService storeService = new StoreService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		Store store = storeService.selectOne(boardNo);
		List<String> hashtagList =  storeService.selectStoreHashtag(boardNo);
		
		request.setAttribute("store",store);
		request.setAttribute("hashtagList", hashtagList);
		request.getRequestDispatcher("/WEB-INF/view/store/storeviewUpdate.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String memberId = request.getParameter("memberId");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String[] dayArr = request.getParameterValues("inlineRadioOptions");
		String days = "";
		
		if(dayArr != null) days = String.join(",", dayArr);
		
		String openTime = request.getParameter("open-time");
		String closeTime = request.getParameter("close-time");
		String breakOpen = request.getParameter("break-open");
		String breakClose = request.getParameter("break-close");
		String menu = request.getParameter("menu");
		String parking = request.getParameter("parking");
		
		List<String> hashTag = new ArrayList<String>();
		hashTag.add(request.getParameter("hashtag1"));
		hashTag.add(request.getParameter("hashtag2"));
		hashTag.add(request.getParameter("hashtag3"));
		
		Store store = new Store(boardNo, memberId, null, tel, address, days, openTime, closeTime, breakOpen, breakClose, menu, parking, 0, 0, null);
		
		int result = storeService.updateStoreInfo(store,hashTag);
		
		response.sendRedirect(request.getContextPath() + "/store/storedetail?board_no=" + boardNo);
	}
}
