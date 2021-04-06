<%@page import="member.model.service.MemberService"%>
<%@page import="member.model.vo.Member"%>
<%@page import="search.model.vo.ReservationRankList"%>
<%@page import="search.model.vo.HashtagRankList"%>
<%@page import="search.model.vo.ViewList"%>
<%@page import="search.model.vo.NewList"%>
<%@page import="search.model.vo.ReviewList"%>
<%@page import="search.model.vo.RatingList"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<ReservationRankList> rrllist = (List<ReservationRankList>)request.getAttribute("reservationRankList");
	List<ViewList> viewList = (List<ViewList>)request.getAttribute("viewList");
	List<NewList> newList = (List<NewList>)request.getAttribute("newList");
	List<RatingList> ratingList = (List<RatingList>)request.getAttribute("ratingList");
	List<ReviewList> reviewList = (List<ReviewList>)request.getAttribute("reviewList");
	List<HashtagRankList> hashtagRankList = (List<HashtagRankList>)request.getAttribute("hashtagRankList");
	
	String msg = (String)session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");//1회 사용후 폐기
	Member member = (Member)session.getAttribute("memberLoggedIn");
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>허니플레이트 | Honeyplate</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/b36a7fe661.js" crossorigin="anonymous"></script>
    



<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/newTpl.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/template_common.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/main.css?123">
<link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath() %>/images/favicon/favicon.ico">


<script src="js/newTpl.js"></script>


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

<body>
	<!-- preloader 무한로딩시 아래처럼 주석처리하고, preloader 확인하고자 할 시, 주석 해제 요망 -->
	<div id="preloader-active">
		<div
			class="preloader d-flex align-items-center justify-content-center">
			<div class="preloader-inner position-relative">
				<div class="preloader-circle"></div>
				<div class="preloader-img pere-text">
					<img src="<%=request.getContextPath()%>/images/logo/apitherapy.png" alt="">
				</div>
			</div>
		</div>
	</div>

	<header class="landing-header">
		<a href="<%= request.getContextPath() %>/landing.jsp"><img src="<%=request.getContextPath()%>/images/logo/logo2.png" class="landing-logo"></a>
		<div class="account-div">
			<% if (member != null) { %>
			<a href="<%=request.getContextPath()%>/member/logout" class="ld-logout">Log Out</a>
				<% if (member.getMemberRole().equals(MemberService.MEMBER_ROLE_BUSINESS)) { %>
				<a href="<%=request.getContextPath()%>/member/mypageOwner?memberId=<%=member.getMemberId()%>" class="ld-mypage">My Page</a>
				<%} else if (member.getMemberRole().equals(MemberService.MEMBER_ROLE_CUSTOMER)) { %>
				<a href="<%=request.getContextPath()%>/member/mypageCustomer?memberId=<%=member.getMemberId() %>" class="ld-mypage">My Page</a>
				<%}%>
			<%} else { %>
			<a href="<%=request.getContextPath()%>/member/login" class="ld-signin">Sign In</a>        	
            <a href="<%=request.getContextPath()%>/member/enroll" class="ld-signup">Sign Up</a>
			<%}%>
		</div>
	</header>


<section>
	<div class="searchbar">
	  <!--검색바-->
	    <div class="wrap">
	      <form class="search" action="<%= request.getContextPath() %>/SearchResult">
	         <input type="text" class="searchTerm" id="searchTermcss" name="searchWord"
	                placeholder="지역, 식당, 음식, 해시태그로 검색해보세요!" 
	                onclick="searchpop()">
	         <button type="submit" class="searchButton" >
	           <i class="fa fa-search">
	           </i>
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
	
         
 
    
    
	  
	 <!--첫번째 베스트 목록-->
	 <div id="carouselExampleControls6" class="carousel slide" data-ride="carousel">
	    <div class="carousel-inner">
	      <div class="carousel-item active">
	        <div class="row">
	           <% if(hashtagRankList != null){ %>
  				<% for(int i = 0; i < 3; i++){ %>
		          <div class="col-4">
		  			<a class="hashtagList" href="<%= request.getContextPath() %>/SearchResult?searchWord=<%= hashtagRankList.get(i).getHashtagKeyword() %>">
		  				#<%= hashtagRankList.get(i).getHashtagKeyword() %></a>
		            <img src="<%=request.getContextPath()%>/upload/store/<%= hashtagRankList.get(i).getReviewOriginalFileName1() %>" alt="식당사진" class="img-thumbnail">
		          </div>        
	          	<%} %>
 			  <%} %>  
	        </div>
	        <div class="row">
	          <% if(hashtagRankList != null){ %>
  				<% for(int i = 3; i < 6; i++){ %>
		          <div class="col-4">
		  			<a class="hashtagList" href="<%= request.getContextPath() %>/SearchResult?searchWord=<%= hashtagRankList.get(i).getHashtagKeyword() %>">
		  				#<%= hashtagRankList.get(i).getHashtagKeyword() %></a>
		            <img src="<%=request.getContextPath()%>/upload/store/<%= hashtagRankList.get(i).getReviewOriginalFileName1() %>" alt="식당사진" class="img-thumbnail">
		          </div>        
	          	<%} %>
 			  <%} %>  
	        </div>
	      </div>
	    
	      <div class="carousel-item">
	        <div class="row">
	          <% if(hashtagRankList != null){ %>
  				<% for(int i = 6; i < 9; i++){ %>
		          <div class="col-4">
		  			<a class="hashtagList" href="<%= request.getContextPath() %>/SearchResult?searchWord=<%= hashtagRankList.get(i).getHashtagKeyword() %>">
		  				#<%= hashtagRankList.get(i).getHashtagKeyword() %></a>
		            <img src="<%=request.getContextPath()%>/upload/store/<%= hashtagRankList.get(i).getReviewOriginalFileName1() %>" alt="식당사진" class="img-thumbnail">
		          </div>        
	          	<%} %>
 			  <%} %>  
	        </div>
	        <div class="row">
	          <% if(hashtagRankList != null){ %>
  				<% for(int i = 9; i < 12; i++){ %>
		          <div class="col-4">
		  			<a class="hashtagList" href="<%= request.getContextPath() %>/SearchResult?searchWord=<%= hashtagRankList.get(i).getHashtagKeyword() %>">
		  				#<%= hashtagRankList.get(i).getHashtagKeyword() %></a>
		            <img src="<%=request.getContextPath()%>/upload/store/<%= hashtagRankList.get(i).getReviewOriginalFileName1() %>" alt="식당사진" class="img-thumbnail">
		          </div>        
	          	<%} %>
 			  <%} %>  
	        </div>
	      </div>
	    
	    </div>
	        
	    <a class="carousel-control-prev" href="#carouselExampleControls6" role="button" data-slide="prev">
	      <span class="carousel-control-prev-icon6" aria-hidden="true"></span>
	      <span class="sr-only">Previous</span>
	    </a>
	    <a class="carousel-control-next" href="#carouselExampleControls6" role="button" data-slide="next">
	      <span class="carousel-control-next-icon" aria-hidden="true"></span>
	      <span class="sr-only">Next</span>
	    </a>
	  </div>
	 
	 
	 
	 
	 
	  <br>
	  
	  <h3 class="listtitle">예약베스트</h3>
	   <!--두번째 베스트 목록-->
	  <div id="carouselExampleControls3" class="carousel slide" data-ride="carousel">
	    <div class="carousel-inner">
	      <div class="carousel-item active">
	       <div class="row">
		       <%if(rrllist != null){ %>
		       	<%for(int i = 0; i < 3; i++){ %>
				  <div class="col-4">
		          	<a class="introduce" href="<%= request.getContextPath() %>/store/storedetail?board_no=<%= rrllist.get(i).getBoardNo() %>">
		          		<%= rrllist.get(i).getStoreName() %> <br>
		          		예약 총 <%= rrllist.get(i).getReservationNum() %>회
		          	</a>
		            <img  src="<%=request.getContextPath()%>/upload/store/<%= rrllist.get(i).getReviewOriginalFileName1() %>" alt="식당사진" class="img-thumbnail">
		          </div>
		         <%} %>
		        <%} %>         
	        </div>
	      </div>
	    
	      <div class="carousel-item">
	        <div class="row">
             <%if(rrllist != null){ %>
		       	<%for(int i = 3; i < 6; i++){ %>
				  <div class="col-4">
<!-- 수정됨 시작 -->
		          	<a class="introduce" href="<%= request.getContextPath() %>/store/storedetail?board_no=<%= rrllist.get(i).getBoardNo() %>">
		          		<%= rrllist.get(i).getStoreName() %> <br>
		          		예약 총 <%= rrllist.get(i).getReservationNum() %>회
		          	</a>
<!-- 수정됨 끝 -->
		            <img  src="<%=request.getContextPath()%>/upload/store/<%= rrllist.get(i).getReviewOriginalFileName1() %>" alt="식당사진" class="img-thumbnail">
		          </div>
		        <%} %>
		      <%} %>
	        </div>
	      </div>
	 
	    </div>
	        
	    <a class="carousel-control-prev" href="#carouselExampleControls3" role="button" data-slide="prev">
	      <span class="carousel-control-prev-icon3" aria-hidden="true"></span>
	      <span class="sr-only">Previous</span>
	    </a>
	    <a class="carousel-control-next" href="#carouselExampleControls3" role="button" data-slide="next">
	      <span class="carousel-control-next-icon" aria-hidden="true"></span>
	      <span class="sr-only">Next</span>
	    </a>
	  </div>  
	  <br>
	  <br>
	  
	   
	   
	   
	   
	   
	   
	    
</section>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>
