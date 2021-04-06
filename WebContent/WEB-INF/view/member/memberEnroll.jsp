<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberEnroll.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newTpl.css"/>

<!-- FireBase -->
<!-- The core Firebase JS SDK is always required and must be listed first -->
<script src="https://www.gstatic.com/firebasejs/8.2.9/firebase-app.js"></script>

<!-- TODO: Add SDKs for Firebase products that you want to use
     https://firebase.google.com/docs/web/setup#available-libraries -->
<script src="https://www.gstatic.com/firebasejs/8.2.9/firebase-analytics.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.6.0/firebase-auth.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js" integrity="sha512-nOQuvD9nKirvxDdvQ9OMqe2dgapbPB7vYAMrzJihw5m+aNcf0dX53m6YxM4LgA9u8e9eg9QX+/+mPu8kCNpV2A==" crossorigin="anonymous"></script>
<title>회원가입</title>

<%@ include file="/WEB-INF/view/common/header.jsp" %>

<script>
	var duplicationCkId = true;
	var duplicationCkEmail = true;
	
$(function(){
	
	//form check
	$("#submit-btn").click(function(){
		
	    var $userId = $("#userId");
		var $password = $("#password");
		var $passwordck = $("#passwordck");
		var $userName = $("#userName");
		var $userEmail = $("#userEmail");
		var $userTel = $("#userTel");
		
        if(/^[a-zA-Z0-9]{4,}$/.test($userId.val()) == false){
            alert("아이디는 특수 문자를 제외한 4자리 이상의 영문자와 숫자의 조합이어야 합니다.");
            $userId.select();
            return false;
			
		}else if(duplicationCkId){
			alert("중복된 아이디는 사용할 수 없습니다.");
			$userId.select();
			return false;
		
		}else if(/^[a-zA-Z0-9!@#$$%^&*()]{6,}/.test($password.val()) == false){
        	alert("비밀번호는 최소 6자리입니다.");
        	$password.select();
            return false;
		
		}else if($password.val() !=  $passwordck.val()){
            alert("패스워드가 일치하지 않습니다.");
            $passwordck.select();
            return false;
		
		}else if(/^[가-힣]{2,}$/.test($userName.val()) == false){
        	alert("이름은 한글 2글자 이상이어야 합니다.");
        	$userName.select();
        	return false;
		
		}else if(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i.test($userEmail.val()) == false){
			alert("유효한 이메일이 아닙니다.");
			$userEmail.select();
        	return false;
        	
		}else if(duplicationCkEmail){
			alert("중복된 이메일은 사용하실 수 없습니다.");
			$userEmail.select();
			return false;
			
		}else if(/^010[0-9]{8}$/.test($userTel.val()) == false){
			alert("유효한 전화번호가 아닙니다.");
			$userTel.select();
        	return false;

		}else if($("#emailValid").val() == 0){
			alert("이메일 인증이 완료되지 않았습니다.");
			return false;
		
		}else if($("#memberRole").val() == "R" && (/^[A-Za-z0-9+]{10}$/.test($("#userNumber").val()) == false)){
			alert("사업자 번호가 올바르지 않습니다.");
			return false;
		}
        
        $("#enrollForm").submit();
});	//end of form check

$("#userId").change(function(){
	var $alertId = $(".alert-id");
	var id = $("#userId").val();
	 $.ajax({
			type:"get",
			url:"<%=request.getContextPath()%>/member/duplicationId",
			dataType:"text",
			data:{memberId:id},
			success:function(data){

				if(data == '"true"'){
					duplicationCkId = true;
					$alertId.text("중복된 아이디입니다.");
					$alertId.css("display","block");
				
				}else{
					duplicationCkId = false;
					$alertId.css("display","none");
				}
				
			},
			error:function(xhr,status,error){
				alert("아이디 중복 검사를 실패했습니다. 고객센터로 문의 바랍니다.");
			}
		
		}); //end of ajax
});//end of ajax ("#userId").change

$("#password").change(function(){
	var $alertPassword = $(".alert-password");
	var $password = $("#password");
	
	if(/^[a-zA-Z0-9!@#$$%^&*()]{6,}/.test($password.val()) == false){
		$alertPassword.css("display","block");
    	$password.select();
    	
	}else{
		$alertPassword.css("display","none");		
	}
        
});//end of $("#password").change

$("#password").select(function(){
	var email = $("#userEmail").val();
	var password = $("#password").val();
	
	if(emailSend){
		if(window.confirm("이미 인증 이메일이 발송되었습니다. 비밀번호를 변경하시면 다시 이메일 인증을 받으셔야 합니다. 다시 입력하시겠습니까?"))
			userDelete(email, password);
		else
			return;		
	}
});//end of $("#password").select

$("#passwordck").change(function(){
	var $passwordck = $("#passwordck");
	var $alertPasswordck = $(".alert-passwordck");
	
	if($passwordck.val() != $("#password").val()){
		$alertPasswordck.css("display","block");
		$passwordck.select();
		
	}else{
		$alertPasswordck.css("display","none");
	}
})

$("#userName").change(function(){
	var $alertName = $(".alert-name");
	var $userName = $("#userName");
	
	if(/^[가-힣]{2,}$/.test($userName.val()) == false){
		$alertName.css("display","block");
    	$userName.select();
	
	}else{
		$alertName.css("display","none");
	}
});//end of $("#userName").change

$("#birthday").change(function(){
	var $birthDay =$("#birthday");
	var brithDate = new Date($birthDay.val());
	var now = new Date();
	
    var a = brithDate.getTime(); 
    var b = now.getTime();
	var year = (((1000 * 60 * 60 * 24) * 30)) * 12;
	var diff = (b - a) / year;

    if(a > b){
    	alert("생일이 오늘보다 빠릅니다. 다시 설정해주세요.");
    	$birthDay.val("");
    	
    }else if(diff > 130){
    	alert("잘못된 생일입니다.");
    	$birthDay.val("");
    }
    
})//end of $("#birthday").change

$("#userEmail").change(function(){
	var email = $("#userEmail").val();
	var $alertEmail = $(".alert-email");
	
	//중복 검사 
	$.ajax({
			type:"get",
			url:"<%=request.getContextPath()%>/member/duplicationEmail",
			dataType:"text",
			data:{email:email},
			success:function(data){

				if(data == '"true"'){
					duplicationCkEmail = true;
					$alertEmail.css("display","block");
				
				}else{
					duplicationCkEmail = false;
					$alertEmail.css("display","none");
				}
				
			},
			error:function(xhr,status,error){
				alert("이메일 중복 검사를 실패했습니다. 고객센터로 문의 바랍니다.");
			}	
		}); //end of ajax	
		
})//end of $("#userEmail").change

$("#userEmail").select(function(){
	var email = $("#userEmail").val();
	var pwd =  $("#password").val();
	var password = getEncryptedPassword(pwd);
	
	if(emailSend){
		if(window.confirm("이미 인증 이메일이 발송되었습니다. 이메일을 변경하시면 다시 이메일 인증을 받으셔야 합니다. 다시 입력하시겠습니까?"))
			userDelete(email, password);
		else
			return;		
	}
});//end of $("#password").select

$("#userTel").change(function(){
	var $userTel = $("#userTel");
	var $alertPhone = $(".alert-phone");
	
	if(/^010[0-9]{8}$/.test($userTel.val()) == false){
		$alertPhone.css("display","block");
		$userTel.select();
		
	}else{
		$alertPhone.css("display","none");
	}
});//end of $("#userTel").change

$("#radio-roll-r").click(function(){
	$(".userNumber").css("display","inline-block");
	$("#memberRole").val("R");
});

$("#radio-roll-p").click(function(){
	$(".userNumber").css("display","none");
	$("#memberRole").val("P");
	$(".alert-number").css("display","none")
});

$("#userNumber").change(function(){
	var $userNumber = $("#userNumber");
	var $alertNumber = $(".alert-number");
	
	if((/^[A-Za-z0-9+]{10}$/.test($userNumber.val()) == false)){
		$alertNumber.css("display","block");
	
	}else{
		$alertNumber.css("display","none");
	}
		
});

});//end of jQuery

/* -------------- javascript -------------- */
var emailSend = false;
var user = null;

	 // Your web app's Firebase configuration
	 // For Firebase JS SDK v7.20.0 and later, measurementId is optional
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
	  
var actionCodeSettings = {
			   	 			// URL you want to redirect back to. The domain (www.example.com) for this
			    			// URL must be in the authorized domains list in the Firebase Console.
			    			url: 'http://localhost:9090/Honeyplate/emailCertification.jsp',
			    			// This must be true.
			    			handleCodeInApp: true,
		 				 	};
		 				 	
		 				 	
function sendEmail(){
	//이메일 인증전 비밀번호 유효성 검사 필요 
	var email = document.getElementById("userEmail").value;
	var pwd = document.getElementById("password").value;
	var password = getEncryptedPassword(pwd);
	
	if(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i.test(email) == false){
		alert("유효한 이메일이 아닙니다.");
        return;
        
	}else if(/^[a-zA-Z0-9!@#$$%^&*()]{6,}/.test(password) == false){
    	alert("비밀번호는 최소 6자리입니다.");
        return;
        
	}else if(duplicationCkEmail){
		alert("중복된 이메일 주소입니다.");
        return;
        
	}else{
		
		firebase.auth()
	    .createUserWithEmailAndPassword(email, password).then(function(){
	    	firebase.auth().signInWithEmailAndPassword(email, password)
	  	  	.then((user) => {
	  	    	// Signed in
	  	    	user = firebase.auth().currentUser;
	  	    	user.sendEmailVerification().then(function(){
		  	    	alert("메일이 전송되었습니다.");
		  	    	emailSend = true;
		  	    	console.log(user);
	  	    	});
	  	    	
	  	  	})
	  	  	.catch((error) => {
	  	    	var errorCode = error.code;
	  	    	var errorMessage = error.message;
	  	   		alert(errorCode+"  "+errorMessage);
	  	  	});
	    })
	    .catch((error) => {
	      // Handle Errors here.
	      var errorCode = error.code;
	      var errorMessage = error.message;

	      if(errorMessage == 'The email address is already in use by another account.'){
	    	  if(window.confirm("이미 인증 이메일을 보낸 기록이 있습니다. 인증 이메일을 초기화할까요?")){
	    		  userDelete(email, password);
	    	  }
	    	  
	      }
	    });   
		
	}
	
}

function userDelete(email, password){
	
	firebase.auth().signInWithEmailAndPassword(email, password).catch(function(error) {
		var errorCode = error.code;
		var errorMessage = error.message;	
		console.log(errorCode +"error");
		
	}).then((user) => {
	   		
    		user = firebase.auth().currentUser;
    		user.delete().then(function() {
    			
    		document.getElementById("emailValid").value = 0;
    		emailSend = false;
	    	alert("인증 이메일이 초기화되었습니다. 다시 이메일 인증을 시도해주세요.");
	    	
	  	}).catch(function(error) {
		  	alert("인증 이메일이 초기화를 실패했습니다. 다른 이메일로 인증을 시도해주세요.");
		  	
	  	});
    });
}

function checkEmail(){
	if(!emailSend){
		  alert("먼저 이메일 인증을 해주세요.");
		  return;
		  
	}else{
		var title = "checkEmailPopup";
		var spec = "left=500px, top=300px, width=380px, height=320px";
		open("<%= request.getContextPath()%>/member/checkEmail", title, spec);
	}

}

function getEncryptedPassword(password){
	var en = CryptoJS.SHA512(password);
	var encode = en.toString(CryptoJS.enc.Base64)
	
	return encode;
}


</script>
</head>    
    <!-- section -->
	<section>
		<form id="enrollForm" name="enrollForm" action="<%=request.getContextPath()%>/member/enroll" method="post">
		    <input type="hidden" id="emailValid" value="0" />
		    <input type="hidden" name="memberRole" id="memberRole" value="P" />
		    	<br>
			    <br>
			    <br>
			    <h1 class="tit">허니플레이트</h1>
			 <div class="box">
      				<p>가입 유형을 선택하세요.</p>
      				<div class="radio-container">     		
							<label for="radio-roll">사업자 회원</label>
			            	<input type="radio" id="radio-roll-r" name="radio-roll" value="R">

      						<label for="radio-roll">일반 회원</label>
    			        	<input type="radio" id="radio-roll-p" name="radio-roll" value="P" checked>		      						
      				</div>
			    <div class="mem" >
			    	<ul>
			    		<li>
			    			<sup>*</sup>
				      		<input type="text" name="userId" id="userId" placeholder="아이디" required>
				    		<p class="alert-p alert-id">특수 문자를 제외한 4자리 이상의 영문자와 숫자</p>				   
			    		</li>
			    		
			    		<li>
			    			<sup>*</sup>
				      		<input type="password" name="password" id="password" placeholder="비밀번호" required>
				    		<p class="alert-p alert-password">최소 6자리 이상의 비밀번호를 입력하세요.</p>
			    		</li>
			    		
			    		<li>
			    			<sup>*</sup>
				     		<input type="password" name="passwordck" id="passwordck" placeholder="비밀번호 확인" required>
				    		<p class="alert-p alert-passwordck">비밀번호가 일치하지 않습니다.</p>
			    		</li>
			    		
			    		<li>
			    			<sup>*</sup>
				      		<input type="text" name="userName" id="userName" placeholder="이름" required>
				    		<p class="alert-p alert-name">이름은 한글 2글자 이상이어야 합니다.</p>
			    		</li>
			    		
			    		<li>
				    		<sup> </sup>
				      		<input type="date" name="birthday" id="birthday" placeholder="생년월일"> 
			    		</li>
			    		
			    		<li>
			    			<sup>*</sup>
				      		<input type="email" name="userEmail" id="userEmail" placeholder="이메일" required>
				    		<p class="alert-p alert-email">중복된 이메일 입니다.</p>				    	
			    		</li>
			    		<li>
			    			<button type="button" class="btn" onclick="sendEmail();">
			    				이메일 인증
			    			</button>
				         	
				         	<button type="button" class="btn checkEmail" onclick="checkEmail();">
				         		이메일 인증 확인
				         	</button>
			    		</li>
			    		<li>
							<sup>*</sup>
				      		<input type="tel" name="userTel" id="userTel" placeholder="-을 빼고 휴대폰 번호를 입력하세요." required>
							<p class="alert-p alert-phone">유효한 전화번호가 아닙니다. </p>
						</li>
		
						<li>
							<sup class="userNumber">*</sup>
							<input type="text" name="userNumber" id="userNumber" class="userNumber" placeholder="10자리 사업자 번호를 입력하세요." required>
				    		<p class="alert-p alert-number">10자리 사업자 번호를 입력하세요.</p>				    	
						</li>
						
						<div class="butt">
				         
				        	<button type="button" id="submit-btn" class="btn" >가입하기</button>
				         	
				      	</div>
				         	<p class="notice-p">* 표시는 필수 항목입니다.</p>	
			    	</ul>
			    </div>
		   </div>  
		   
  		</form>
	</section>

<%@ include file="/WEB-INF/view/common/footer.jsp" %>