<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="member.model.service.MemberService"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newTpl.css"/>
<link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath() %>/images/favicon/favicon.ico">
<script src="<%= request.getContextPath() %>/js/newTpl.js"></script>
<%
	String msg = (String)session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");//1회 사용후 폐기
	
	Member member = (Member)session.getAttribute("memberLoggedIn");
%>
<script>
<%if(msg != null) {%>
	alert("<%=msg%>"); 
<%}%>

</script>
<title>허니플레이트 | Honeyplate</title>
</head>
<body>
	<!--? Preloader Start -->
    <div id="preloader-active">
        <div class="preloader d-flex align-items-center justify-content-center">
            <div class="preloader-inner position-relative">
                <div class="preloader-circle"></div>
                <div class="preloader-img pere-text">
                    <img src="<%=request.getContextPath()%>/images/logo/apitherapy.png" alt="preloader-image-honeycomb-and-bee">
                </div>
            </div>
        </div>
    </div>
    
	 <header>
        <div class="logo" >
            <a href="<%=request.getContextPath()%>/landing.jsp"><img src="<%= request.getContextPath() %>/images/logo/hplogo.png" alt="logo image(honey jar)"><p style="display: inline-block;">Honey Plate</p></a>
        </div>
        <nav id="navigation">
        	<%if(member != null) {%>
        		<a href="<%=request.getContextPath()%>/member/logout">Log out</a>
        		
        		<%if(member.getMemberRole().equals(MemberService.MEMBER_ROLE_CUSTOMER)) {%>
	        		<a href="<%=request.getContextPath()%>/member/mypageCustomer?memberId=<%=member.getMemberId() %>"  class="signup">My page</a>
        		
        		<%}else{%>
        			<a href="<%=request.getContextPath()%>/member/mypageOwner?memberId=<%=member.getMemberId()%>" class="signup">My page</a>
        		<%} %>
        	<%}else {%>
	            <a href="<%=request.getContextPath()%>/member/login">Sign in</a>        	
	            <a href="<%=request.getContextPath()%>/member/enroll" class="signup">Sign up</a>
        	<%} %>
        </nav>
    </header>