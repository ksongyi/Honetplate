<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%
String userId = (String) request.getAttribute("userId");
%>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>find Id</title>

  <!-- JS, jQuery, Bootstrap -->
  <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
  <script src="https://kit.fontawesome.com/b36a7fe661.js" crossorigin="anonymous"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/newTpl.js"></script>
	
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;900&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css"/>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/newTpl.css"/>
  <%@ include file="/WEB-INF/view/common/header.jsp" %>
	  
</head>

<body>
	
    
	<!-- section -->
	<section>
		<form action="/Honeyplate/member/memberFindId" class="all" name="findId" method="post" style="text-align:center;">
		<input type="hidden" name="msg" value="<%= msg %>" />
		<input type="hidden" name="userId" value="<%= userId %>" />
	    <fieldset>
	      <div class="title">
	        <h1 >아이디찾기</h1>
	      </div>
	      <div class="box">
	        <br>
	        <br>
	       	<input type="text" name="userName" id="userName" placeholder="&nbsp;&nbsp;이름">
	        <br>
	        <br>
        	<input type="email" name="userEmail" id="userEmail" placeholder="&nbsp;&nbsp;이메일" required>
	        <br>
	        <br>  
		        <div class="bt">
		          <button type="submit" class="btn btn-warning">아이디 찾기</button>
		        </div>
	      	</div>
	    </fieldset>
      	<div class="find">
      		<p><a href="<%=request.getContextPath()%>/member/login">로그인</a>&nbsp;&nbsp;</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/member/enroll">회원가입</a> </p>     
    	</div>
  	  </form>
	</section>  

<%@ include file="/WEB-INF/view/common/footer.jsp" %>
	
	<script>
  	var msg = document.findId.msg.value;
  	var userId = document.findId.userId.value;
  	
  	if(msg != '' && msg != null && msg != 'null'){
  		alert(msg);
  	}else if(userId != '' && userId != null && userId != 'null'){
  		alert('고객님의 ID는 '+userId+' 입니다.');
  	}
  </script>
  
</html>