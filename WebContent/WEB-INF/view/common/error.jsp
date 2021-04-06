<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>허니플레이트 | 404 ERROR</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/error.css" />
</head>
<body>
    <div class="error-container">
        <div class="logo" >
            <img src="<%=request.getContextPath()%>/images/logo/hplogo.png" alt="logo image(honey jar)">
            <p class="logo-text" style="font-size: 32px;">Honey Plate</p>
        </div>
        <div class="error-text">
            <h1>요청하신 페이지를 찾을 수 없습니다.</h1>
            <p> 찾으시려는 페이지는 주소를 <b>잘못</b> 입력하였거나<br>
                페이지 주소의 <b>변경</b> 또는 <b>변경</b> 등의 이유로 페이지를 찾을 수 없습니다.
            </p>
            <hr>
            <h3>다른 방법을 안내해 드립니다!</h3>
            <ol>
                <li>URL을 다시 한 번 확인 해 주세요.</li>
                <li><a href="<%=request.getContextPath()%>/landing.jsp" class="error-link">메인 페이지</a>로 다시 돌아가주세요.</li>
                <li>어디서 문제가 발생했는지 문의 
                    <a href="mailto:example@gmail.com" target="_top" class="error-link">메일</a>
                    을 남겨주세요.
            </ol>
            <p>이용에 불편을 드려 죄송합니다.<br>
               - HoneyPlate 드림
            </p>
        </div>
    </div>
</body>
</html>