package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;

@WebFilter({ "/member/mypageCustomer/bookmark", 
			"/member/mypageCustomer/bookmark/remove", 
			"/member/mypageCustomer/bookmark",
			"/member/logout",
			"/member/mypageCustomer",
			"/member/mypageCustomer/review",
			"/member/mypageCustomer/review/delete",
			"/member/mypageOwner",
			"/member/updateInformation",
			"/member/updatePassword",
			"/store/writereview",
			"/store/bookmark/add",
			"/store/bookmark/remove",
			"/store/storeUpdate",
			"/reservation/listCustomer",
			"/reservation/listDetailsOwner",
			"/reservation/listOwner",
			"/reservation/request",
			"/reservation/request/noshow",
			"/reservation/request/statusRefusal",
			"/reservation/request/status",
			"/reservation/request/visited",
			"/payment/payDeposit"	
			})
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpSession session = httpReq.getSession();
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");

		if(memberLoggedIn == null) {
			HttpServletResponse httpResp = (HttpServletResponse)response;
			session.setAttribute("msg", "로그인 후 이용하실 수 있습니다.");
			httpResp.sendRedirect(httpReq.getContextPath() + "/landing.jsp");
			return;
		}

		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
