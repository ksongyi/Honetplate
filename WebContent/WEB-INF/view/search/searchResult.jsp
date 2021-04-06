<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="member.model.service.MemberService"%>
<%@page import="member.model.vo.Member"%>
<%@page import="search.model.vo.ViewList"%>
<%@page import="search.model.vo.NewList"%>
<%@page import="search.model.vo.RatingList"%>
<%@page import="search.model.vo.SearchTable"%>
<%@page import="search.model.vo.ReviewList"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	List<SearchTable> list = (List<SearchTable>)request.getAttribute("searchTableList");
	List<NewList> newList = (List<NewList>)request.getAttribute("newList");
	List<ReviewList> reviewList = (List<ReviewList>)request.getAttribute("reviewList");
	List<RatingList> ratingList = (List<RatingList>)request.getAttribute("ratingList");
	List<ViewList> viewList = (List<ViewList>)request.getAttribute("viewList");

%>	
<%@ include file="/WEB-INF/view/common/header.jsp" %>
<%-- 마이페이지, 식당정보 페이지 등 랜딩페이지를 제외한 나머지 페이지에 쓰이는 템플릿입니다. --%>
<!DOCTYPE html  PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>searchResult</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<script src="<%= request.getContextPath() %>/js/newTpl.js"></script>

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/b36a7fe661.js" crossorigin="anonymous"></script>

<link rel="manifest" href="site.webmanifest">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/images/favicon/favicon.ico">

<!-- CSS here -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/searchResult.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newTpl.css?123"/>

 <script>
 <%if(msg != null) {%>
	alert("<%=msg%>"); 
<%}%>

 $(function(){
     $(".nav-link").click(function(e){
         $(this).tab('show');
 })
 $( ".searchTerm" ).focus(function() {
     document.querySelector(".tab-container").style.visibility ="visible";
 });

 $( ".searchTerm" ).blur(function() {
   $( ".tab-container" ).mouseleave(function() {
      document.querySelector(".tab-container").style.visibility ="hidden";
   });
 });
});
 </script>   
		 
		 
</head>

    
	<section>


		<div class="searchbar">
		   <!--검색바-->
		       <div class="wrap">
		           <form class="search"  action="<%= request.getContextPath() %>/SearchResult">
		               <input  type="text" class="searchTerm" id="searchTermcss" name="searchWord"
		               		placeholder="지역, 식당, 음식, 해시태그로 검색해보세요!" 
		               		value ="<%= request.getParameter("searchWord")  != null ? request.getParameter("searchWord")  : "" %>" >
		               <button type="submit" class="searchButton">
		                   <i class="fa fa-search"></i>
		               </button>
		        <!--검색팝업창-->
		                     <!--data-toggle="tab"이 탭으로 보여주게 만드는 코드-->
			           <div class="tab-container" >
			            <div class="row">
			              <div class="col">
			                <!-- <p>Tab</p> -->
			                  <ul class="nav nav-tabs">
			                    <li class="nav-item">
				                      <a class="nav-link active" data-toggle="tab" href="#recommendstore">추천검색어</a>
				                    </li>
				                    <li class="nav-item">
				                      <a class="nav-link " data-toggle="tab" href="#beststore">인기가게</a>
				                    </li>
				                    <li class="nav-item">
				                      <a class="nav-link" data-toggle="tab" href="#newstore">새로생긴가게</a>
				                    </li>
				                    <li class="nav-item">
				                      <a class="nav-link" data-toggle="tab" href="#reviewstore">리뷰많은가게</a>
				                    </li>
				                    <li class="nav-item">
				                      <a class="nav-link" data-toggle="tab" href="#ratingstore">별점높은가게</a>
				                    </li>
			                  </ul>
			            </div>
			                <div class="tab-content">
			                	<div class="tab-pane fade  show active" id="recommendstore">
			                      <a href="<%= request.getContextPath() %>/SearchResult?searchWord=예쁜카페"> 예쁜카페 </a><br><hr>
			                       <a href="<%= request.getContextPath() %>/SearchResult?searchWord=데이트코스"> 데이트코스 </a><br><hr>
			                       <a href="<%= request.getContextPath() %>/SearchResult?searchWord=디저트맛집"> 디저트맛집</a><br><hr>
			                    </div>
			                    <div class="tab-pane fade" id="beststore">
			                        <% for(int i = 0; i < 3; i++){ %>
				                       <a href="<%= request.getContextPath() %>/store/storedetail?board_no=<%= viewList.get(i).getBoardNo() %>"> <%= viewList.get(i).getStoreName() %> </a><br><hr>
								    <%} %>
			                    </div>
			                    <div class="tab-pane fade" id="newstore">
			                    	<% for(int i = 0; i < 3; i++){ %>
			                      		<a href="<%= request.getContextPath() %>/store/storedetail?board_no=<%= newList.get(i).getBoardNo() %>"> <%= newList.get(i).getStoreName() %> </a><br><hr>
			                   		<%} %>
			                    </div>
			                    <div class="tab-pane fade" id="reviewstore">
			                    	<% for(int i = 0; i < 3; i++){ %>
			                      		<a href="<%= request.getContextPath() %>/store/storedetail?board_no=<%= reviewList.get(i).getBoardNo() %>"> <%= reviewList.get(i).getStoreName() %> </a><br><hr>
			                   		<%} %>
			                    </div>
			                    <div class="tab-pane fade" id="ratingstore">
			                    	<% for(int i = 0; i < 3; i++){ %>  
			                       		<a href="<%= request.getContextPath() %>/store/storedetail?board_no=<%= ratingList.get(i).getBoardNo() %>"> <%= ratingList.get(i).getStoreName() %> </a><br><hr>
			                    	<%} %>
			                    </div>
				            </div>
			              </div>
			          </div>      
		           </form>
		       </div>
		 </div>  
		     
		         

		    
		    
		    
		<!--검색결과 멘트-->    
		    
		<h2 id="key-word">
			<% if(request.getParameter("searchWord") != null){%>  
				'<%= request.getParameter("searchWord") %>' 검색 결과
			<% }else %> 검색 결과가 없습니다.
			
				
		</h2>
		  
		<br>
		
		
		
		  
		  
		<!--검색목록-->
		<div class="result-container">
		
		  <div class="row">
		  <% if(list != null){ %>
		  	<% for(SearchTable s : list){ %>
		    <div class="col-4">
		      <div style="width:70%" class="card" style="width: 18rem;">
		      	<a class="card-text" href="<%= request.getContextPath() %>/store/storedetail?board_no=<%= s.getBoardNo() %>">
		        <img  src="<%=request.getContextPath()%>/upload/store/<%= s.getReviewOriginalFileName1() %>" 
		        		class="card-img-top" alt="음식사진" >
		        <div  class="card-body">
		          <p class="card-text">
		          		<%= s.getStoreName() %> <br>
		    			대표메뉴 : <%= s.getMenu() %> <br>
		    		    <%= s.getHashtagKeyword1() != null ? "#" + s.getHashtagKeyword1() : "<br>" %> <%=  s.getHashtagKeyword2() != null ?  "#" +  s.getHashtagKeyword2() : "" %> <%=  s.getHashtagKeyword3() != null ?  "#" +  s.getHashtagKeyword3() : "" %>         
		          </p>
		        </div>
		      	</a>
		      </div>
		    </div>
		 	<%} %>
		  <%} %>  
		  </div>
			
		
		  
		</div>
		
		<br>
		<br>
		
		<!-- 페이지바 -->
		<% if(request.getAttribute("pageBar") != null){%>
			<div id="pageBar">
				<%= request.getAttribute("pageBar") %>
			</div>
		<% } %>

		<br>
		<br>
	</section>
	
	
<%@ include file="/WEB-INF/view/common/footer.jsp" %>