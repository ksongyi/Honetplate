<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="store.model.vo.MyBookmark"%>
<%@page import="store.model.vo.Bookmark"%>
<%@page import="store.model.vo.MyReview"%>
<%@page import="store.model.vo.Review"%>
<%@page import="java.util.List"%>
<%@page import="member.model.service.MemberService"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/d37b4c8496.js" crossorigin="anonymous"></script>
<script src="<%= request.getContextPath() %>/js/newTpl.js"></script>
<script src="<%= request.getContextPath() %>/js/myPageCustomer.js"></script>
	
<link rel="stylesheet"href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/myPageCustomer.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/newTpl.css">
<link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath() %>/images/favicon/favicon.ico">

<!-- import -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<!-- FireBase -->
<!-- The core Firebase JS SDK is always required and must be listed first -->
<script src="https://www.gstatic.com/firebasejs/8.2.9/firebase-app.js"></script>

<!-- TODO: Add SDKs for Firebase products that you want to use
     https://firebase.google.com/docs/web/setup#available-libraries -->
<script src="https://www.gstatic.com/firebasejs/8.2.9/firebase-analytics.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.6.0/firebase-auth.js"></script>

<!-- crypto -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js" integrity="sha512-nOQuvD9nKirvxDdvQ9OMqe2dgapbPB7vYAMrzJihw5m+aNcf0dX53m6YxM4LgA9u8e9eg9QX+/+mPu8kCNpV2A==" crossorigin="anonymous"></script>

<%@ include file="/WEB-INF/view/common/header.jsp" %>

<%
	String showMenu = (String)session.getAttribute("showMenu");
	if(showMenu != null)session.removeAttribute("showMenu");

	List<Review> myReviewList = (List<Review>)request.getAttribute("myReviewList");
	String pageBar = (String)request.getAttribute("pageBar");
	
	List<Bookmark> myBookmarkList = (List<Bookmark>)request.getAttribute("myBookmarkList");
	String bookmarkPageBar = (String)request.getAttribute("bookmarkPageBar");
	
	String insertMsg = (String)request.getAttribute("insertMsg");
	String updateMsg = (String)request.getAttribute("updateMsg");
%>
	
<script>
	
$(function(){
		
var resvPageNum = 0;
var resvPageMax = 0;

var resvNo;
var resvStatus;
var depositYn;
var date;
var people;
var depositAmount;
var boardNo;

var pg;
var payMethod;
var merchantUid;
var name;
var buyerEmail;
var buyerName;
var buyerTel;
var sum;

<%------------------- 예약 내역 ----------------------%>

$("#show-reservation").click(function (){
	$.ajax({
		type:"get",
		url:"<%=request.getContextPath()%>/reservation/listCustomer",
		dataType:"json",
		data:{memberId:"<%=member.getMemberId()%>"},
		
		success:function(data){
			console.log(data);
			
			if(data != null && data.length > 0){
				
				resvPageMax = (data.length)-1;
				var reservation = data[resvPageNum];
				var dateStr = reservation.rsvDate;
				
				date = new Date(dateStr);
				resvNo = reservation.reservationNo;
				boardNo = reservation.boardNo;
				resvStatus = reservation.reservationStatus;
				depositYn = reservation.depositYn;
				people = reservation.adult + reservation.child;
				depositAmount = people * 5000;
				console.log(compareDate(date));
					
				if((reservation.reservationStatus == null || reservation.reservationStatus == 'N' || reservation.depositYn == "N" ) 
					&& compareDate(date)){
					//예약이 승인되지않았고, 결제도 하지 않은 상태에서 예약일이 지난날일때 예약 취소 출력
					$("#reservation-state").text("예약 취소");
					
				}else if(reservation.reservationStatus == null){
					$("#reservation-state").text("예약 확인중");
					
				}else if(reservation.reservationStatus == 'N'){
					$("#reservation-state").text("예약 거절");
					
				}else if(reservation.depositYn == "N"){
					$("#reservation-state").text("결제 대기중");
						
				}else if(reservation.depositYn == "Y"){
					$("#reservation-state").text("결제 완료");
				}
						
					$("#reservation-date").text(getDateStr(date, date.getDay()));
					$("#reservation-phone").text(reservation.reservationPhone);
					$("#reservation-child").text(reservation.child + '명');
					$("#reservation-adult").text(reservation.adult + '명');
					$("#reservation-storeName").text(reservation.storeName);
					$(".page-num").text("No." + reservation.reservationNo);
					
					sum = (Number(reservation.child) + Number(reservation.adult)) * 5000;
					$("#reservation-sum").text(new Intl.NumberFormat('en-IN', { maximumSignificantDigits: 3 }).format(sum) + '원');
				
				}else{
					$("#reservation-state").text("예약 내역이 없습니다.");
				}
			
				//예약내역이 한개거나 없을때 페이지 넘김 버튼 숨김
				if(data.length < 2){
					$("#resv-pre").css("display","none");
					$("#resv-next").css("display","none");
				}
				
			},
			error:function(xhr,status,error){
				alert("조회를 실패했습니다.")
			}
		
		}); //end of ajax

});//end of $("#show-reservation").click
		
$("#resv-pre").click(function(){
	if(resvPageNum != 0){
		--resvPageNum;
		$("#show-reservation").click();
	}
});//end of $("#resv-pre").click
	
$("#resv-next").click(function(){
	if(resvPageNum != resvPageMax){
		++resvPageNum;
		$("#show-reservation").click();
	}
});//end of $("#resv-next").click


/* ------------------------- iamport 결제 모듈 구현 김자경 ------------------------- */
$("#payment-btn").click(function () {
	pg = 'kakaopay';
	payMethod = 'card';
	merchantUid = resvNo + 'rsv' + new Date().getTime();
	name = 'HP_'+ '<%= member.getMemberName() %>' +'_예약금';
	buyerEmail = '<%= member.getEmail() %>';
	buyerName = '<%= member.getMemberName() %>';
	buyerTel = '<%= member.getPhone() %>';
	$("#hidden-resv-no").val(resvNo);
	$("#hidden-resv-board-no").val(boardNo);
	$("#hidden-pg").val(pg);
	$("#hidden-pay-method").val(payMethod);
	$("#hidden-merchant-uid").val(merchantUid);
	$("#hidden-merchant-name").val(name);
	$("#hidden-deposit-amount").val(sum);
	$("#hidden-buyer-email").val(buyerEmail);
	$("#hidden-buyer-name").val(buyerName);
	$("#hidden-buyer-tel").val(buyerTel);
	
	if ((compareDate(date) == false) && (resvStatus == 'Y') && (depositYn == 'N')) {
		var IMP = window.IMP;
		IMP.init("imp39728603");
		IMP.request_pay({
			pg : pg,
			pay_method : payMethod,
			merchant_uid : merchantUid,
			name : name,
			amount : sum,
			buyer_email : buyerEmail,
			buyer_name : buyerName,
			buyer_tel : buyerTel
		}, function(rsp) {
			if (rsp.success) {
				var msg = "결제가 완료되었습니다. \n";
				msg += "고유 ID : " + rsp.imp_uid + "\n";
				msg += "상점 거래ID : " + rsp.merchant_uid + "\n";
				msg += '결제 금액 : ' + rsp.paid_amount + "\n";
			    msg += '카드 승인번호 : ' + rsp.apply_num + "\n";
			    msg += '<%= insertMsg %>' + "\n";
			    msg += '<%= updateMsg %>' + "\n";
			    alert(msg);
				$("#resv-payment-form").submit();
			} else {
				var msg = "결제에 실패하였습니다.\n";
				msg += "ERROR : " + rsp.error_msg;
				alert(msg);
			}
		});
		
	} else {
		alert("결제할 수 없습니다. 예약 상태를 체크해주세요.");
	}
	
});

<%------------------------ 개인정보 수정 --------------------------%>
var validEmail = true;
var validPhone = true;

$("#update-email").change(function(){
	var $textEmail = $(".p-email");
	var $updateEmail = $("#update-email");
	
	var email = $updateEmail.val();
	
	if(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i.test(email) == false){
		
		$textEmail.text("유효한 이메일이 아닙니다.");
		$textEmail.css("display","block");
		$updateEmail.select();
		validEmail = false;
        return;
	
	}else if(email != "<%=member.getEmail()%>"){

		$.ajax({
			type:"get",
			url:"<%=request.getContextPath()%>/member/duplicationEmail",
			dataType:"text",
			data:{email:email},
			success:function(data){

				if(data == '"true"'){
					validEmail = false;
					$textEmail.text("중복된 이메일입니다.");
					$textEmail.css("display","block");
					$updateEmail.select();
					
				}else{
					validEmail = true;
					$textEmail.css("display","none");
				}
				
			},
			error:function(xhr,status,error){
				alert("이메일 중복 검사를 실패했습니다. 고객센터로 문의 바랍니다.");
			}	
		}); //end of ajax
		
	}else{
		validEmail = true;
		$textEmail.css("display","none");
	}
});

$("#update-phone").change(function(){
	var $textPhone = $(".p-phone");
	var $updatePhone = $("#update-phone");
	var phone = $updatePhone.val();
	
	if(/^010[0-9]{8}$/.test(phone) == false){
		$textPhone.css("display","block");
		$updatePhone.select();
		validPhone = false; 
			
	}else{
		$textPhone.css("display","none");
		validPhone = true; 
	}
});


$("#update-btn").click(function(){
	var $updateEmail = $("#update-email");
	var $updatePhone = $("#update-phone");
	
	if(!validPhone){
		alert("유효한 휴대폰 번호가 아닙니다.");
		$updatePhone.select();
		return;

	}else if(!validEmail){
		alert("유효한 이메일이 아닙니다.");
		$updatePhone.select();
		return;
	
	}else{
		updateEmailFireBase($updateEmail.val())

	}
});

<%-----------------password update-------------------%>

$(".password-now").change(function(){
	var $pwdText = $(".password-now-p");
	var $pwdNow = $(".password-now");
	
	var inputPwd = getEncryptedPassword($pwdNow.val());
	
	if("<%=member.getMemberPassword()%>" != inputPwd){
		$pwdText.addClass("alert-p");
		$pwdNow.select();
	
	}else{
		$pwdText.removeClass("alert-p");
	}

});//end of $(".password-now").change

$(".password-upd").change(function(){
	var $pwdText = $(".password-update-p");
	var $pwdUpdate = $(".password-upd");
	
	var inputPwd = $pwdUpdate.val();
	
	if(/^[a-zA-Z0-9!@#$$%^&*()]{6,}/.test(inputPwd) == false){
		$pwdText.addClass("alert-p");
		$pwdUpdate.select();
		
	}else{
		$pwdText.removeClass("alert-p");
	}
	
});//end of $("password-update").change

$(".password-ck").change(function(){
	var $pwdText = $(".password-ck-p");
	var $pwdCheck = $(".password-ck");
	
	var input = $pwdCheck.val();
	var pwd = $(".password-upd").val();
	
	if(pwd.length < 6){
		$pwdText.text("*변경할 비밀번호가 유효하지 않습니다. 먼저 변경할 비밀번호를 입력하세요.");
		$pwdText.addClass("alert-p");
		$(".password-upd").select();
		
	}else if(input != pwd){
		$pwdText.text("*비밀번호가 일치하지 않습니다.");
		$pwdText.addClass("alert-p");
		$pwdCheck.select();
	
	}else{
		$pwdText.removeClass("alert-p");
	}
});

$("#password-update-btn").click(function(){
	var $text = $(".alert-p");
	console.log ($text);
	
	if($text.length != 0 ){
		alert($text.text());
		return false;
	}
	
	$("#password-update-form").submit();
});


<%----------------------------------- 리뷰  -----------------------------------------------%>
$("#show-review").click(function(){
	console.log("?");
	location.href="<%=request.getContextPath()%>/member/mypageCustomer/review?memberId=<%=member.getMemberId()%>";
})

if('<%=showMenu%>' == 'myReview'){
	$(".showbox").hide();
	$(".review-list").show();
}
 
$(".review-btn").click(function(){
	if(window.confirm("정말로 삭제하시겠습니까?")){
		$(".review-del-form").submit();
		
	}else return;
})

<%----------------------------------- 북마크  -----------------------------------------------%>

$("#show-bookmark").click(function (){
	location.href="<%=request.getContextPath()%>/member/mypageCustomer/bookmark?memberId=<%=member.getMemberId()%>";
});


if('<%=showMenu%>' == 'myBookmark'){
	$(".showbox").hide();
	$(".bookmark-list").show();
}

$(".bookmark-btn").click(function(e){
	$(this).find(".bookmark-remove-form").submit();
})

<%----------------------------------- 로그아웃 -----------------------------------------------%>
$("#logout-btn").click(function(){
	location.href="<%=request.getContextPath()%>/member/logout";
})



});//end of jQuery

/* -------------------------- javascript --------------------------------- */

function getDateStr(date, index){
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var dayOfWeek = ['일','월','화','수','목','금','토'];
	var hours = date.getHours();
	var minutes = date.getMinutes()
			
	month = month > 9 ? month : "0" + month;
	day = day > 9 ? day : "0" + day;
	hours = hours > 9 ? hours : "0" + hours;
	minutes = minutes > 9 ? minutes : "0" + minutes;
			
	return year + '.' + month  + '.' + day + ' ' + dayOfWeek[index] + " " +  hours + ":" + minutes;
}
	
function compareDate(date){
	var now = new Date();
	var a = now.getTime();
	var b = date.getTime();
		
	return a > b;
}

function updateEmailFireBase(updateEmail){
	
	var email = "<%=member.getEmail()%>";
	var password = "<%=member.getMemberPassword()%>";
	
	var firebaseConfig = {
			
			apiKey: "AIzaSyBETG-eZo2GUTJ1UxO20pv79O3LIP0uX6M",
			authDomain: "honey-plate.firebaseapp.com",
			projectId: "honey-plate",
			storageBucket: "honey-plate.appspot.com",
			messagingSenderId: "726370714589",
			appId: "1:726370714589:web:7e9f51f74b6329efed6c34",
			measurementId: "G-L4L9WCXSE5"
			    		 };
	
	firebase.initializeApp(firebaseConfig);
	
	firebase.auth().signInWithEmailAndPassword(email, password)
		.then((user) => {

		user = firebase.auth().currentUser;
		
		user.updateEmail(updateEmail).then(function() {
			 console.log("firebase update Email Update successful");
				$(".update-form").submit();
			 
		}).catch(function(error) {
			console.log("@firebase update Email Update error happened");
		});

  	}).catch(function(error) {
  		console.log(error.message);
  		$(".update-form").submit();
  	});
	
	
}

function getEncryptedPassword(password){
	var en = CryptoJS.SHA512(password);
	var encode = en.toString(CryptoJS.enc.Base64)
	
	return encode;
}
</script>
<title>마이페이지</title>
</head>
<body>
    <div class="mypage-container">
        <div class="mypage-menu">
            <ul>
                <li>
                    <h2><%=member.getMemberName() %>님 안녕하세요!</h2>
                </li>
                <li>
                    <hr>
                </li>                
                <li>
                    <button id="show-update">개인정보 수정</button>
                </li>
                <li>
                    <hr>
                </li>
                <li>
                    <button href="" id="show-password">비밀번호 변경</button>
                </li>
                <li>
                    <hr>
                </li>
                <li>
                    <button href="" id="show-reservation">예약내역 확인</button>
                </li>
                <li>
                    <hr>
                </li>
                <li>
                    <button id="show-review">내 리뷰 관리</button>
                </li>
                <li>
                    <hr>
                </li>
                <li>
                    <button id="show-bookmark">즐겨찾기한 식당 목록</button>
                </li>
                <li>
                    <hr>
                </li>
                <li>
                    <button href="" id="show-withdrawal">회원탈퇴</button>
                </li>
                <li>
                    <hr>
                </li>
            </ul>
        </div>
        
        <div class="mypage-myinfo showbox">
            <table>
                <tr>
                    <th>회원구분</th>
                    <th><%=member.getMemberRole().equals(MemberService.MEMBER_ROLE_CUSTOMER) ? "일반 회원" : "사업자 회원"%></th>
                </tr>
                <tr>
                    <td>아이디</td>
                    <td><%=member.getMemberId()%></td>
                </tr>
                <tr>
                    <td>이름</td>
                    <td><%=member.getMemberName()%></td>
                </tr>
                <tr>
                    <td>생년월일</td>
                    <td><%=member.getBirthDay() != null ? member.getBirthDay() : ""%></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><%=member.getEmail()%></td>
                </tr>
                <tr>
                    <td>연락처</td>
                    <td><%=member.getPhone()%></td>
                </tr>
            </table>

            <input type="button" value="로그아웃" id="logout-btn">
        </div>

        <div class="myinfo-update showbox">
            <table>
            	<form class="update-form" action="<%=request.getContextPath()%>/member/updateInformation" method="post">
            		<input type="hidden" value="<%=member.getMemberRole()%>"  name="role"/>
            		<input type="hidden" value="<%=member.getMemberId()%>"  name="memberId"/>

            		<tr>
                    	<th>회원구분</th>
                    	<th><%=member.getMemberRole().equals(MemberService.MEMBER_ROLE_CUSTOMER) ? "일반 회원" : "사업자 회원"%></th>
                	</tr>
                	<tr>
                    	<td>아이디</td>
                    	<td><%=member.getMemberId()%></td>
                	</tr>
                	<tr>
                    	<td>이름</td>
                    	<td><%=member.getMemberName()%></td>
                	</tr>
                	<tr>
                    	<td>생년월일</td>
                    	<td><%=member.getBirthDay() != null ? member.getBirthDay() : ""%></td>
                	</tr>
                	<tr>
                    	<td>이메일</td>
                    	<td>
                    		<p class="update-p p-email">중복된 이메일입니다.</p>
                    		<input type="email"  name="update-email" value="<%=member.getEmail()%>" id="update-email">
                    	</td>
                	</tr>
                	<tr>
                    	<td>연락처</td>
                    	<td>
                    		<p class="update-p p-phone">유효한 휴대폰 번호가 아닙니다.</p>
                    		<input type="tel" name="update-phone" value="<%=member.getPhone()%>" id="update-phone">
                    	</td>
                	</tr>

                </form>
            </table>

            <input type="button" value="업데이트" id="update-btn">
        </div>

        <div class="password-update showbox">       
            <form id="password-update-form" method="post" action="<%=request.getContextPath()%>/member/updatePassword">
            	<input type="hidden" value=<%=member.getMemberId()%> name="memberId"/>
                <div class="form-group">
                  <label for="formGroupExampleInput">현재 비밀번호</label>
                  <input type="password" class="password-now form-control" id="formGroupExampleInput" placeholder="현재 비밀번호를 입력하세요." required>
                  <p class="password-now-p">*현재 비밀번호가 일치하지 않습니다.</p>
                </div>
                
                <div class="form-group">
                  <label for="formGroupExampleInput">변경할 비밀번호</label>
                  <input type="password" class="password-upd form-control" id="formGroupExampleInput2" name="password-upd" placeholder="변경할 비밀번호를 입력하세요." required>
                  <p class="password-update-p">*비밀번호는 최소 6자리입니다.</p>
                </div>
                
                <div class="form-group">
                    <label for="formGroupExampleInput">변경할 비밀번호 확인</label>
                    <input type="password" class="password-ck form-control" id="formGroupExampleInput3" placeholder="변경할 비밀번호를 한번 더 입력하세요." required>
                    <p class="password-ck-p">*비밀번호가 일치하지 않습니다.</p>
                </div>
            </form>

            <input type="submit" value="변경하기" id="password-update-btn"/>
        </div>
        
        <div class="reservation-list showbox">
            <div class="reservation-list-title"> 
                <hr>
                <h2>예약내역</h2>
                <hr>
            </div>
            
            <ul>
            	<li>
                    <i class="fas fa-file-signature"></i>
                    <p>예약 가게 : </p>
                    <p id="reservation-storeName"></p>
                </li>  
                <li>
                    <i class="fas fa-check-square"></i>
                    <p>예약 상태 : </p>
                    <p id="reservation-state"></p>
                </li>
                <li>
                    <i class="fas fa-calendar-day"></i>
                    <p>예약 날짜 : </p>
                    <p id="reservation-date"></p>
                </li>
                <li>
                    <i class="fas fa-phone-square-alt"></i>
                    <p>예약자 번호 : </p>
                    <p id="reservation-phone"></p>
                </li>
                <li>
                    <i class="fas fa-child"></i>
                    <p>어린이 인원 : </p>
                    <p id="reservation-child"></p>
                </li>

                <li>
                    <i class="fas fa-user"></i>
                    <p>어른 인원 : </p>
                    <p id="reservation-adult"></p>
                </li>
                <li>
                    <i class="fas fa-won-sign"></i>
                    <p>결제 금액 : </p>
                    <p id="reservation-sum"></p>
                </li>
            </ul>

            <div class="pageBar">
                	<button type="button" class="btm_image" id="resv-pre"><i class="fas fa-caret-left next"></i></button>
                	<form action="<%=request.getContextPath()%>/payment/payDeposit" method="post" id="resv-payment-form">
                    	<input type="hidden" name="resv-no" id="hidden-resv-no"/>
                    	<input type="hidden" name="resv-board-no" id="hidden-resv-board-no"/>
                    	<input type="hidden" name="resv-memberId" id="hidden-resv-memberId" value="<%= member.getMemberId() %>" />
                    	<input type="hidden" name="pg" id="hidden-pg"/>
                    	<input type="hidden" name="payMethod" id="hidden-pay-method"/>
                    	<input type="hidden" name="merchantUid" id="hidden-merchant-uid"/>
                    	<input type="hidden" name="merchantName" id="hidden-merchant-name"/>
                    	<input type="hidden" name="amount" id="hidden-deposit-amount"/>
                    	<input type="hidden" name="buyerEmail" id="hidden-buyer-email"/>
                    	<input type="hidden" name="buyerName" id="hidden-buyer-name"/>
                    	<input type="hidden" name="buyerTel" id="hidden-buyer-tel"/>
           			</form> 
                	<input type="submit" value="결제하기" id="payment-btn" />
                	<!--  form tag/input:hidden만들고, action값에 서블릿 주소 넣고, payment-btn.click()에서 
                	  	input:hidden에 val()이용해서 value = reservationNo 넣어서 form.submit한다. 
                	  	input:hidden값으로 위에 값 넣으면서, JSP표현식이용해 member_id도 파라미터로 넘어가도록 작성  -->
                	<button type="button" class="btm_image" id="resv-next"><i class="fas fa-caret-right next"></i></button>
            	</div>
            		<p class="payment-method-p">※ 카카오페이 결제만 가능합니다.</p>
		            <p class="page-num"></p>
        </div>

        <div class="review-list showbox">
           <h2>나의 리뷰</h2>

            <ul>
            	<%if(myReviewList != null) {
            		for(Review review : myReviewList){
            			MyReview myReview = (MyReview)review;%>
            			
            			<form method="post"  action="<%=request.getContextPath()%>/member/mypageCustomer/review/delete" class="review-del-form">
            				<input type="hidden" value="<%=myReview.getReview_no()%>" name="review-no" />
            				<input type="hidden" value="<%=member.getMemberId()%>" name="memberId" />
            			</form>
            			
            			<li>
                    		<div class="review">
                        		<div class="review-text">
                            
                            		<p class="review-date">작성일 : <%=myReview.getReview_date()%></p>
                            		<h3 id="store_name"><a href="<%=request.getContextPath()%>/store/storedetail?board_no=<%=myReview.getBoard_no()%>" style="color:black;"><%=myReview.getStoreName()%></a></h3>
                            		<p class="review-content"><%=myReview.getReview_content() %></p>
  									<div >
  										<p class="stars">
                            			<%for(int i = 0; i < myReview.getRating(); i++) {%>
	                                    	<i class="fas fa-star"></i>
                            			<%} %>
	                					<button type="button" class="btn btn-light review-btn" id="review-del-btn" >삭제</button>                  
                            			<hr> 
                            			</p>
  									</div>
  									             
                        		</div>                      		
                    		</div>
                		</li>
            		
            		<%} %>
            	<%} %>
                
            </ul>
            <nav aria-label="Page navigation example" class="review-pageBar" style="width:100%; margin:0 auto; margin-top: 10px;">
                <ul class="pagination review-page-ul">
                  <%=pageBar%>
                </ul>
              </nav>
        </div>

        <div class="bookmark-list showbox">
            <ul>
            	<%if(myBookmarkList != null) {
            		for(Bookmark bookmark : myBookmarkList){
            			MyBookmark myBookmark = (MyBookmark)bookmark;%>
      
            			<li>
                    		<div class="bookmark">
                        		<ul class="bookmark-container">
                            		<li>
                            			<%if(myBookmark.getFileName() != null) {%>
                                			<img class="bookmark-img" src="<%= request.getContextPath()%>/upload/store/<%= myBookmark.getFileName()%>" alt="가게사진">
                            			<%}else{%>
                                			<img class="bookmark-img" src="<%= request.getContextPath() %>/images/logo/hplogo.png" alt="가게사진">                            		
                            			<%}%>
                            			
                            		</li>
                            		<li class="bookmark-text">
                                		<h4 id="store-name"><a href="<%=request.getContextPath()%>/store/storedetail?board_no=<%=myBookmark.getBoardNo()%>" style="color:black;"><%=myBookmark.getStoreName()%></a></h4>
                                		<p class="store-info"><%=myBookmark.getHashtag1() + ", " + myBookmark.getHashtag2() + ", " + myBookmark.getHashtag3()%></p>
                            		</li>
                            		<li>
                                		<button class="bookmark-btn">
                                    		<i class="fas fa-star"></i>
	                            			<form action="<%=request.getContextPath()%>/member/mypageCustomer/bookmark/remove" method="post" class="bookmark-remove-form" >
	                            				<input type="hidden" value="<%=myBookmark.getBookmakrkNo()%>" name="bookmarkNo" />
	                            			</form>                                   		
                                		</button>
                            		</li>
                        		</ul>
                    		</div>
                		</li>    
            		<%} %>
            	<%} %>
            </ul>
            
            <nav aria-label="Page navigation example" class="review-pageBar" style="width:100%; margin:0 auto; margin-top: 10px;">
                <ul class="pagination review-page-ul">
                  <%=bookmarkPageBar%>
                </ul>
             </nav>
        </div>

		<div class="withdrawal showbox">
            <form action="/Honeyplate/member/memberDeleteCusomer" name="memberDelete" method="post">
                <h3>회원 탈퇴</h3>
                <div class="form-group">
                  <input type="password" class="form-control" id="formGroupExampleInput" name="memberPassword" placeholder="현재 비밀번호를 입력하세요." required>
                  <p>*현재 비밀번호가 일치하지 않습니다.</p>
                </div>
            </form>

            <input type="submit" onclick="document.memberDelete.submit();" value="탈퇴하기" id="password-update-btn"/>
        </div>
    </div>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>
