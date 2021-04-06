package common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MvcUtils {
	
	/**
	 * totalPage 전체페이지수     올림(totalContents / numPerPage)
	 * pageBarSize 페이지바의 페이지수 
	 * pageStart ~ pageEnd 페이지바 범위
	 * pageNo 증감변수
	 * 
	 * < 1 2 3 4 5 >  이전버튼 비활성화
	 * < 6 7 8 9 10 >
	 * < 11 12 >	    다음버튼 비활성화
	 * 
	 */
	
	/* 박소연 getPageBar */
	public static String getPageBar(int totalContents, int cpage, int numPerPage, String url) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		String pageBar = "";
		
		int pageBarSize = 3;
		int totalPage = (int)Math.ceil((double)totalContents / numPerPage);

		url = url + (url.indexOf("?") > -1 ? "&" : "?") + "cpage=";
		
		// 1 2 3 : pageStart 1 ~ pageEnd 3
		// pageStart 4 ~ pageEnd 6 
		int pageStart = ((cpage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;
		
		//증감변수
		int pageNo = pageStart;
		
		//이전 영역
		if(pageNo == 1) {
			
		}
		else {
			
			pageBar += "<a href='" + url + (pageNo - 1) + "'>&lt;</a>\n";
		}
		
		//페이지 No 영역
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			//현재페이지인 경우, 링크비활성화
			if(pageNo == cpage) {
				pageBar += "<span class='cPage'>" + pageNo + "</span>\n";
			}
			else {
				pageBar += "<a href='" + url + pageNo + "'>" + pageNo + "</a>\n";
			}
			pageNo++;
		}
		//다음 영역
		if(pageNo > totalPage) {
			
		}
		else {
			pageBar += "<a href='" + url + pageNo + "'>&gt;</a>\n";
		}
		return pageBar;
	}
	
	
	/* 강송이 getPageBar */
	public static String getPageBar(int totalContents, int cpage, int numPerPage, String url, String searchWord1, String searchWord2, String searchWord3, String searchWord4, String searchWord5, String searchWord6) {
		String pageBar = "";
		
		int pageBarSize = 3;
		int totalPage = (int)Math.ceil((double)totalContents / numPerPage);

		// /mvc/admin/memberList?cpage=
		// /mvc/admin/memberFinder?searchType=memberId&searchKeyward=a&cpage=
		url = url + "?searchWord=" + searchWord1 + "&"+ "cpage=";
		
		// 1 2 3 4 5 : pageStart 1 ~ pageEnd 5 
		// 6 7 8 9 10 : pageStart 6 ~ pageEnd 10 
		int pageStart = ((cpage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;
		
		//증감변수
		int pageNo = pageStart;
		
		//이전 영역
		if(pageNo == 1) {
			
		}
		else {
			// /mvc/admin/memberList?cpage=5
			pageBar += "<a href='" + url + (pageNo - 1) + "'>&lt;</a>\n";
		}
		
		//페이지 No 영역
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			//현재페이지인 경우, 링크비활성화
			if(pageNo == cpage) {
				pageBar += "<span class='cPage'>" + pageNo + "</span>\n";
			}
			else {
				pageBar += "<a href='" + url + pageNo + "'>" + pageNo + "</a>\n";
			}
			pageNo++;
		}
		//다음 영역
		if(pageNo > totalPage) {
			
		}
		else {
			pageBar += "<a href='" + url + pageNo + "'>&gt;</a>\n";
		}
		return pageBar;
	}
	
	
	//강유정 pageBar
	public static String getMyReviewPageBar(int totalContent, int cpage, int numPerPage, String url) {
      
		String pageBar = "<li class='page-item' >";
		int pageBarSize = 3;
		int totalPage = (int)Math.ceil((double)totalContent / numPerPage);
		url = url + (url.indexOf("?") > -1 ? "&" : "?") + "cpage=";
		
		int pageStart =  ((cpage -1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;
		int pageNo = pageStart; //증감 변수

		if(pageNo != 1) {
			pageBar += "<a class='page-link' href='" + url + (pageNo - 1) + "'aria-label='Previous>\n" 
						+ "<span aria-hidden='true' style='color:#FFA726;'>&laquo;</span>\n"
						+ "<span class=\"sr-only\">Previous</span>\n"
						+ "</a>\n</li>\n";
		} 
		
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == cpage) {
				pageBar += "<li class='page-item'><a class='page-link'style='color:#FFA726;'>" + pageNo + "</a></li>\n";

			}else {
				pageBar +="<li class='page-item'><a class='page-link' style='color:#FFA726;' href='" + url + pageNo + "'>" + pageNo + "</a></li>\n";
			}
			
			pageNo++;
		}
		
		if(pageNo < totalPage) {
			
			pageBar += "<a class='page-link' href='" + url + pageNo + "'aria-label='Previous>\n" 
					+ "<span aria-hidden='true' style='color:#FFA726;'>&raquo;</span>\n"
					+ "<span class=\"sr-only\">Next</span>\n"
					+ "</a>\n</li>\n";
		} 
		
		return pageBar;
	}
	
	public static String getEncryptedPassword(String password) {
		String encryptedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");

			byte[] bytes = password.getBytes("UTF-8");
			md.update(bytes);
			byte[] encryptedBytes = md.digest();

			encryptedPassword = Base64.getEncoder().encodeToString(encryptedBytes);
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
		return encryptedPassword;
	}

}


