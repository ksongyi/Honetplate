<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/login.css"/>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<!-- JS, Popper.js, and jQuery -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;900&display=swap" rel="stylesheet">
</head>
<script type="text/javascript">
<%
	Cookie[] cookies = request.getCookies();
	String cookieId = null;
	
	if(cookies != null){
		for(Cookie c : cookies){
			
			if("cookieId".equals(c.getName())){
				cookieId = c.getValue();
				break;
			}
		}	
	}
%>
$(function(){
	
	$("#login-btn").click(function(){
		console.log();
		if($("#userId").val().length < 1){
			alert("아이디를 입력하세요.");
			return false;
			
		}else if($("#password").val().length < 1){
			alert("비밀번호를 입력하세요.");
			return false;
		}
		
		$(".login-form").submit();
	})
	
});

</script>

<%@ include file="/WEB-INF/view/common/header.jsp" %>
	<div class="login-container">
		<form action="<%=request.getContextPath()%>/member/login" class="login-form" method="post">
    		<fieldset>
      			<div class="lo">
        			<h1 >로그인</h1>
      			</div>
      			<br>

      			<div class="login">
        		<div class="ck">
          		<br>
          			<input type="radio" name="connector" value="P" checked="checked">
          			<label for="individual" class="ind">개인로그인</label>
          			<input type="radio" name="connector" value="R">
          			<label for="business">사업자로그인</label>
        		</div>
        		<br>

        		<input type="text" class="user-input" name="userId" id="userId" placeholder="&nbsp;&nbsp;아이디" value="<%= cookieId != null ? cookieId : ""%>">
        		<br>
        		<br>
        		<input type="password" class="user-input" name="password" id="password" placeholder="&nbsp;&nbsp;비밀번호">
        		<br>
        		<br>
          
        		<div class="bt">
          			<button type="button" id="login-btn" class="btn btn-warning">로그인</button>
        		</div>
        		
        		<input type="checkbox" name="saveId" value="saveId" class="saveId" checked="checked">
          		<label for="saveId">아이디 저장</label>
      		</div>

    		</fieldset>
            
    		<div class="find">
      			<p><a href="<%=request.getContextPath()%>/member/memberFindId">아이디찾기</a>&nbsp;&nbsp;|&nbsp;&nbsp;|&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/member/enroll">회원가입</a> </p>     
    		</div>
  		</form>
	</div>
<%@ include file="/WEB-INF/view/common/footer.jsp" %>
