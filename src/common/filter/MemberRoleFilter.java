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

import member.model.service.MemberService;
import member.model.vo.Member;

@WebFilter({ "/member/mypageOwner",
			"/store/storeUpdate",
			"/reservation/listDetailsOwner",
			"/reservation/listOwner",
			"/reservation/request/noshow",
			"/reservation/request/statusRefusal",
			"/reservation/request/status",
			"/reservation/request/visited"
		})
public class MemberRoleFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

			HttpServletRequest httpReq = (HttpServletRequest)request;
			HttpSession session = httpReq.getSession();
			Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
				
			if(memberLoggedIn != null && !memberLoggedIn.getMemberRole().equals(MemberService.MEMBER_ROLE_BUSINESS)){
					HttpServletResponse httpResp = (HttpServletResponse)response;
					session.setAttribute("msg", "관리자만 이용할 수 있는 페이지 입니다.");
					httpResp.sendRedirect(httpReq.getContextPath() + "/landing.jsp");
					return;
			}
				
			chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
