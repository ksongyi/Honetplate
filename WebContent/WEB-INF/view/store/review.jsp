<?xml version="1.0" encoding="UTF-8" ?>

<%@page import="member.model.vo.Member"%>
<%@page import="store.model.vo.Review"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- JS, Popper.js, and jQuery -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/b36a7fe661.js" crossorigin="anonymous"></script>
<script src="js/newTpl.js"></script>
    
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<!--review css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/review.css" />

    <title>review</title>

<script>
$(document).ready(function(){

$("input[type='radio']").click(function(){
var sim = $("input[type='radio']:checked").val();
alert(sim);
if (sim<3) { $('.myratings').css('color','red'); $(".myratings").text(sim); }else{ $('.myratings').css('color','green'); $(".myratings").text(sim); } }); });


function boardView(){
	history.go(-1);
}


<%--$(function(){
	$("[name=review_content]").focus(function(){
			
			
			<% if(memberLoggedIn == null){%>
				loginAlert();
			<% } %>
		});

	});
	function loginAlert(){
		alert("로그인후 리뷰 작성할 수 있습니다.");
		$("#userId").focus();
	}--%>

</script>
</head>
<body>

	
    <div class="wrapper">
        <div class ="reivew_title">
            <p>이 가게에 대한 솔직한 리뷰를 써주세요</p>
        </div>
    </div>    
    
    <br />
    <div class="wrapper2">   
        <div class="row">
            <div class="star-rating">
                <div class="stars">
					<form 
						action="<%=request.getContextPath() %>/store/writereview"
						method="post"
						enctype="multipart/form-data"> <%--enctype="multipart/form-data" --%> 
					<%-- 	<input type="hidden" value="<%=request.getAttribute("board_no")%>" name="board_no" /> --%>
						<%Member m = (Member)session.getAttribute("memberLoggedIn"); %>
						<input type="hidden" value="<%=request.getAttribute("board_no")%>" name="board_no" />
						<input type="hidden" value="<%=m.getMemberId()%>" name="memberId" />

						<div name="Rating">
					    <input class="star" id="star-5" type="radio" name="star" value="5"/>
					    <label class="star star-5" for="star-5"></label>
					    <input class="star star-4" id="star-4" type="radio" name="star" value="4"/>
					    <label class="star star-4" for="star-4"></label>
					    <input class="star star-3" id="star-3" type="radio" name="star" value="3"/>
					    <label class="star star-3" for="star-3"></label>
					    <input class="star star-2" id="star-2" type="radio" name="star" value="2"/>
					    <label class="star star-2" for="star-2"></label>
					    <input class="star star-1" id="star-1" type="radio" name="star" value="1"/>
					    <label class="star star-1" for="star-1"></label>
						</div>
					        <div class="review-group">
					           <textarea class="review-control" rows="3" placeholder="맛있으셨나요?가게에 대한 솔직한 리뷰를 써주세요." name="review_content"></textarea>
					            
					            <input type="file" class="review-file form-control-file multiple" style="margin-left: 52px; margin-top: 10px;" name="file1"/>
					            <input type="file" class="review-file form-control-file multiple" style="margin-left: 52px; margin-top: 10px;" name="file2"/>
					             <input type="file" class="review-file form-control-file multiple" style="margin-left: 52px; margin-top: 10px;" name="file3"/>
					                 
					         
					            <button type="button" class="rebtn-close btn-warning" style="margin-left: 52px; margin-top: 10px;" onclick="boardView();">작성취소</button>
							<!--<button type="button" class="rebtn-done btn-warning">작성완료</button> -->
					            <button type="submit" class="rebtn-done btn-warning">작성완료</button>
					            
							</div>
						
					</form>
                </div>
            </div>
        </div>
    </div>
    

</body>
</html>