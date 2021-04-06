<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.concurrent.ExecutionException"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="member.model.service.MemberService"%>
<%@page import="member.model.vo.Member"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="reservation.model.vo.Reservation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>사업자 마이페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/d37b4c8496.js" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/b36a7fe661.js" crossorigin="anonymous"></script>
<script src="<%= request.getContextPath() %>/js/myPageOwner.js"></script>

<!-- CSS here -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/myPageOwner.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/newTpl.css">

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
	if(showMenu != null) session.removeAttribute("showMenu");

	String noShowFreq = (String)request.getAttribute("noShowFreq");
	Reservation resv = 	(Reservation)request.getAttribute("reservation");
%>
<script>

$(function(){
	
var resvPageNum = 1; //페이지바 현재 페이지
var numPerPage = 10; //몇 개의 리스트를 출력할지
var pageStart = 1; //페이지바의 시작값
var date;


 $("#show-reservation").click(function(){
	 $(".showbox").hide();
	 $(".reservation-table").show();
	 
	 $.ajax({
			type:"get",
			url:"<%=request.getContextPath()%>/reservation/listOwner",
			data:{pageNum:resvPageNum, memberId:"<%=member.getMemberId()%>"},
			dataType:"json",
		
			success:function(data){
				$("#reservation-table-body").html("");
				
				var flag = false;
				var start = (resvPageNum -1) * numPerPage;
				var maxNum = (resvPageNum  * 10) - 1;
				
				for(var i = 0; i < data.length; i++){
					if(!flag){
						
						console.log('start' + start);
						i += start;
						console.log('i' + i);
						flag = true;
					}
			
					if(i > maxNum){
						console.log(i)
						console.log(maxNum);
						//페이지 10개씩 출력 그 이상은 다음페이지로
						break;
					}
					
					var order = data[i];
					var date = new Date(order.rsvDate);
					var strDate = getDateStr(date,date.getDay());
					var status = order.reservationStatus;
					var deposit = order.depositYn;
					var noShowYn = order.noShowYn;
					var text = "";
					
					
					if((status == null || status == 'N' || deposit == "N" ) 
						&& compareDate(date)){
						//예약이 승인되지않았고, 결제도 하지 않은 상태에서 예약일이 지난날일때 예약 취소 출력
						text ="예약 취소";
						
					}else if(status == null){
						text ="수락 대기중";
							
					}else if(status == 'N'){
						text ="예약 거절";
						
					}else if(deposit == "N"){
						text ="결제 대기중";
							
					}else if(deposit == "Y"){
						text ="결제 완료";
							
						if (noShowYn == "Y") {
								text ="노쇼";

						}else if(noShowYn == "N"){
								text ="방문 완료";

						}						
					}

					var html = "<tr>";				
					html += "<td>" + text + "</td>";
					html += "<td><a  style='color: #333333;'"
					html += "href="
					html +="<%=request.getContextPath()%>/reservation/listDetailsOwner?reservationNo=" + order.reservationNo;
					html += ">" + order.memberId + "</a></td>"; 
					html += "<td>" + strDate + "</td>";
					html += "<td>" + order.noShowFreq + '회' + "</td>";
					html += "</tr>";

					$("#reservation-table-body").append(html);			
				}
				
				//페이지바 만드는곳 
				var pageBarSize = 3;
				var pageEnd = pageStart + pageBarSize - 1;
				var pageNo = pageStart;
				var totalPage = Math.ceil(data.length / numPerPage);
				
				$(".pagination").html("");
				if(data.length >= 10){

					var nav = "<li class='page-item'>" 
					nav += "<button class='page-link' aria-label='Previous'>"
					nav += "<span aria-hidden='true'>&laquo;</span>"
					nav += " <span class='sr-only'>Previous</span></button></li>"
					$(".pagination").append(nav);
					
					nav = '';
					
					while(pageNo <= pageEnd && pageNo <= totalPage) {
						nav += "<li class='page-item'><button class='page-link' style='color: #FF9800'>" +  pageNo  +"</button></li>"
						pageNo++;
					}
					
					if(pageNo <= totalPage){
						nav += "<li class='page-item'>";
						nav += "<button class='page-link' href='#' aria-label='Next'>";
						nav += "<span aria-hidden='true'>&raquo;</span>";
						nav += "<span class='sr-only'>Next</span></button></li>"
						
					}									
					
					$(".page-item").after(nav);
				}
			},
			error:function(xhr,status,error){
				alert("조회를 실패했습니다.");
			}
		
		}); //end of ajax
		
 });//end of $("#show-reservation").click


 //페이지바 클릭시 새로운 리스트 출력됨.
 $(".pagination").click(function(e){
	 var $btn = $(e.target);
	 var page = $btn.text();
	 
	 if(page.indexOf('«') != -1){
		 
 		 if(resvPageNum == pageStart && resvPageNum != 1)
			 pageStart = pageStart - 3;
		 
		resvPageNum = resvPageNum != 1 ? --resvPageNum : resvPageNum;
		 
	 }else if(page.indexOf('»') != -1){
		 
		 if(resvPageNum % 3 == 0)
			 pageStart += 3;
		 
		++resvPageNum;
	 }else{
		 resvPageNum = $btn.text();
	 }

	 $("#show-reservation").click();
 });//end of $(".pagination").click
 
 
 //수락 버튼
 $("#approval-btn").click(function(){
	 
	 <%if(resv != null){%>
		if("<%=resv.getReservationStatus()%>" == 'Y'){
			alert("이미 수락하신 예약입니다.");
			return;
		
		}else if("<%=resv.getReservationStatus()%>" == 'N'){
			alert("이미 거절하신 예약입니다.");
			return;
		
		}else if("<%=resv.getDepositYn()%>" == 'Y'){
			alert("이미 결제된 예약입니다.");
			return;
		}
		 
		$("#resv-form").submit();
	 <%}%>
 });
 
 //거절 버튼
 $("#refusal-btn").click(function(){
	 
	 <%if(resv != null){%>
		if("<%=resv.getReservationStatus()%>" == 'Y'){
			alert("이미 수락하신 예약입니다.");
			return;
		
		}else if("<%=resv.getReservationStatus()%>" == 'N'){
			alert("이미 거절하신 예약입니다.");
			return;
		
		}else if("<%=resv.getDepositYn()%>" == 'Y'){
			alert("이미 결제된 예약입니다.");
			return;
		}
		
		
		$("#resv-form").attr("action", "<%=request.getContextPath()%>/reservation/request/statusRefusal")	
					   .submit();
	 <%}%>
 });
 
//방문완료 버튼
 $("#visited-btn").click(function(){
	 
	 <%if(resv != null){%>
		if("<%=resv.getNoShowYn()%>" == 'Y'){
			alert("이미 노쇼 처리하신 예약입니다.");
			return;
		
		}else if("<%=resv.getNoShowYn()%>" == 'N'){
			alert("이미 방문완료 처리하신 예약입니다.");
			return;
		
		}else if("<%=resv.getReservationStatus()%>" == 'N'){
			alert("이미 거절하신 예약입니다.");
			return;
		}
		
		$("#resv-form").attr("action", "<%=request.getContextPath()%>/reservation/request/visited")	
					   .submit();
	 <%}%>
 });
 
 //노쇼 버튼
 $("#noshow-btn").click(function(){
	 
	 <%if(resv != null){%>
		if("<%=resv.getNoShowYn()%>" == 'Y'){
			alert("이미 노쇼 처리하신 예약입니다.");
			return;
		
		}else if("<%=resv.getNoShowYn()%>" == 'N'){
			alert("이미 방문완료 처리하신 예약입니다.");
			return;
		
		}else if("<%=resv.getReservationStatus()%>" == 'N'){
			alert("이미 거절하신 예약입니다.");
			return;
		}
		
		$("#resv-form").attr("action", "<%=request.getContextPath()%>/reservation/request/noshow")	
		   .submit();
	 <%}%>
 });
 
 //예약 상세보기 닫기버튼
 $("#reservation-close").click(function(){
	 $("#show-reservation").trigger('click');
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
		
	if($text.length != 0 ){
		alert($text.text());
		return false;
	}
		
	$("#password-update-form").submit();
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

$("#show-store").click(function(){
	var boardNo = 0;
	$.ajax({
		type:"get",
		url:"<%=request.getContextPath()%>/member/boardNo",
		dataType:"text",
	
		success:function(data){
			console.log(data);
		
			boardNo = data;
			
			if(boardNo == 0){
				alert("내 식당 게시판이 없습니다. 고객센터로 문의 바랍니다.");
				return;
			}else{
				location.href="<%=request.getContextPath()%>/store/storedetail?board_no=" + boardNo;
			}

		},
		error:function(xhr,status,error){
			alert("조회를 실패했습니다.");
		}
	});
});

<%----------------------------------- 로그아웃 -----------------------------------------------%>
$("#logout-btn").click(function(){
	
	location.href="<%=request.getContextPath()%>/member/logout";
})


//session에서 받아온 값 기준으로 어떤 div를 보여줄지 정하는 if문 메소드 실행이 안돼서 밑으로 내림..
if('<%=showMenu%>' == 'reservationList'){
	$(".showbox").hide();
	$(".reservation-list").show();
		 
}else if('<%=showMenu%>' == 'reservationTable'){	
	$("#show-reservation").trigger('click');
}

});

//date값 받아서 string값으로 리턴하는 메소드
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

//현재날짜와 예약날짜 비교하는 메서드
function compareDate(date){
	var now = new Date();
	var a = now.getTime();
	var b = date.getTime();
	
	return a > b;
}

function getEncryptedPassword(password){
	var en = CryptoJS.SHA512(password);
	var encode = en.toString(CryptoJS.enc.Base64)
	
	return encode;
}

function updateEmailFireBase(updateEmail){
	
	var email = "<%=member.getEmail()%>";
	var password = "<%=member.getMemberPassword()%>"
	
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

</script>
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
                    <button id="show-reservation">내 가게 예약요청</button>
                </li>
                <li>
                    <hr>
                </li>
                <li>             
	                <button id="show-store">내 식당</button>
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
        <div class="reservation-table showbox">
            <table class="table">
                <thead >
                  <tr>
                    <th scope="col">수락여부</th>
                    <th scope="col">예약자</th>
                    <th scope="col">시간</th>
                    <th scope="col">노쇼</th>
                  </tr>
                </thead>
                <tbody id="reservation-table-body">
					
                </tbody>
              </table>
       		<div id="table-nav-container">
	           <nav aria-label="reservation-table showbox Page navigation example" class="">
	                <ul class="pagination">
	                  <li class="page-item page-first">
	
	                  </li>
	                </ul>
	        	</nav>       		
       		</div>
        </div>
    
          
 
        <div class="reservation-list showbox"style="display:none">
            <button id="reservation-close">
                <i class="far fa-times-circle"></i>
            </button>
            
            <ul>
            	<%if(resv != null) {%>

            	<li>
            		<form action="<%=request.getContextPath()%>/reservation/request/status" method="post" id="resv-form">
	                    <input type="hidden" name="resv-board-no" value="<%=resv.getReservationNo()%>" />
            		</form>
                    
                    <i class="fas fa-portrait"></i>
                    <p>예약자 아이디 : </p>
                    <p><%=resv.getMemberId() %></p>
                </li>
                <li>
                    <i class="fas fa-exclamation-circle"></i>
                    <p>노쇼 : </p>
                    <p><%=resv.getNoShowFreq() %>회</p>
                </li>
                <li>
                    <i class="fas fa-calendar-day"></i>
                    <p>예약 날짜 : </p>
                    <p><%=resv.timestampToString()%></p>
                </li>
                <li>
                    <i class="fas fa-phone-square-alt"></i>
                    <p>예약자 전화번호 : </p>
                    <p><%=resv.getReservationPhone()%></p>
                </li>
                <li>
                    <i class="fas fa-child"></i>
                    <p>어린이 인원 : </p>
                    <p><%=resv.getChild()%>명</p>
                </li>
                <li>
                    <i class="fas fa-user"></i>
                    <p>어른 인원 : </p>
                    <p><%=resv.getAdult()%>명</p>
                </li>
                <li>
                
                <% 
                    int sum = (resv.getChild() + resv.getAdult()) * 5000;
                	DecimalFormat df = new DecimalFormat("#,###");
                 %>
                    <i class="fas fa-won-sign"></i>
                    <p>결제 금액 : </p>
                    <p><%=df.format(sum) %></p>
                </li>
                <%} %>
                
            </ul>
            <div class="pageBar">          
				<div class="btn-group" role="group" aria-label="Basic example" style="margin-top: 20px;">
					<button type="button" class="btn btn-secondary" id="approval-btn">승인</button>
					<button type="button" class="btn btn-secondary" id=refusal-btn>거절</button>
					<button type="button" class="btn btn-secondary" id="visited-btn">방문완료</button>
					<button type="button" class="btn btn-secondary" id="noshow-btn">노쇼</button>
				</div>
            </div>
        </div>

        <div class="withdrawal showbox">
            <form>
                <h3>회원 탈퇴</h3>
                <div class="form-group">
                  <input type="password" class="form-control" id="formGroupExampleInput" placeholder="현재 비밀번호를 입력하세요." required>
                  <p>*현재 비밀번호가 일치하지 않습니다.</p>
                </div>
            </form>

            <input type="submit" value="탈퇴하기" id="password-update-btn"/>
        </div>
    </div> 

<%@ include file="/WEB-INF/view/common/footer.jsp" %>