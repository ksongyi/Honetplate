<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

<!-- FireBase -->
<!-- The core Firebase JS SDK is always required and must be listed first -->
<script src="https://www.gstatic.com/firebasejs/8.2.9/firebase-app.js"></script>

<!-- TODO: Add SDKs for Firebase products that you want to use
     https://firebase.google.com/docs/web/setup#available-libraries -->
<script src="https://www.gstatic.com/firebasejs/8.2.9/firebase-analytics.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.6.0/firebase-auth.js"></script>

<title>허니플레이트 | 이메일 인증</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/checkEmailPopup.css" />
</head>

<body>
	<div class="popup-container">
        <div class="logo">
            <img src="<%=request.getContextPath()%>/images/logo/hplogo.png" alt="logo image(honey jar)">
        </div>
        <h2 id="result"></h2>
        <p id="result-text"></p>
    </div>
</body>
<script>
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
			    			
			    			
var $frm = $(opener.document.enrollForm);
var email = $frm.find("#userEmail").val();
var password = $frm.find("#password").val();

var user = null;

firebase.auth().signInWithEmailAndPassword(email, password)
	  	.then((user) => {
	    	// Signed in
	    	user = firebase.auth().currentUser;
	    	});

firebase.auth().onAuthStateChanged(function(user) {

	if(user){
	  if (user.emailVerified) {
		$("#result").text("인증되었습니다.");
		$("#result-text").html("이메일 인증이 완료되었습니다. <br>창을 닫으셔도 됩니다.");
		$frm.find("#emailValid").val(1);
		
	  } else {
		  $("#result").text("인증되지않았습니다.");
		  $("#result-text").html("이메일 인증이 완료되지않았습니다. <br>메일함을 확인해주세요.");
	  }
	}
}); 

</script>
</html>