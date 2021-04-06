<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Arrays"%>
<%@page import="store.controller.storeviewUpdateServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="store.model.vo.Store"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/storeviewUpdate.css" />
    
<script src="<%=request.getContextPath()%>/js/newTpl.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newTpl.css" />
<%@ include file="/WEB-INF/view/common/header.jsp" %>

<title>가게 정보 수정</title>
<%
	Store store = (Store)request.getAttribute("store");
	List<String> hashtagList = (List<String>)request.getAttribute("hashtagList");
	List<String> dayList = new ArrayList<>(Arrays.asList(store.getClosing_day().split(",")));
%>
<script>
$(function(){
	
	<%if(hashtagList != null && hashtagList.size() >= 3){%>
		$("option[value='<%=hashtagList.get(0)%>']").prop("selected",true);
		$("option[value='<%=hashtagList.get(1)%>']").prop("selected",true);
		$("option[value='<%=hashtagList.get(2)%>']").prop("selected",true);
	<%}%>
	
	$("#input-tel").change(function(){
		var $inputTel = $("#input-tel");
		
		if(/^[0-9|-]{7,12}$/.test($inputTel.val()) == false){
			$inputTel.next().addClass("text-show");
			$inputTel.select();
		
		}else{
			$inputTel.next().removeClass("text-show");
		}
	})
	
	$("#input-address").change(function(){
		var $inputAddress = $("#input-address");
		console.log($inputAddress.val());
		
		if(/^.{2,50}$/.test($inputAddress.val()) == false){
			$inputAddress.next().addClass("text-show");
			$inputAddress.select();
			
		}else{
			$inputAddress.next().removeClass("text-show");
		}
	});
	
	$(".day").click(function(e){
		$("#alwaysOpen").prop("checked",false);
	});
		 	
	$("#alwaysOpen").click(function(e){
		$(".day").prop("checked",false);
	});
	
	$("#input-time").change(function(){
		var $inputTime = $("#input-time");
		var openTime = $("#")
	});
	
	$(".update-submit").click(function(){
		
		var $inputTel = $("#input-tel");
		var $inputAddress = $("#input-address");
		var $inputMenu = $("#input-menu");
		var $breakOpen = $("#break-open");
		var $breakClose = $("#break-close");
		
		if(/^[0-9|-]{7,12}$/.test($inputTel.val()) == false){
			alert("유효한 전화번호를 입력해주세요.");
			$inputTel.select();
			return false;
			
		}else if(/^.{2,50}$/.test($inputAddress.val()) == false){
			alert("유효한 주소를 입력해주세요.");
			$inputAddress.select();
			return false;
		
		}else if($(".form-check-input:checked").length  == 7){
			alert("휴일은 7일 미만이어야 합니다.");
			return false;
			
		}else if(/^.{1,20}$/.test($inputMenu.val()) == false){
			alert("대표 메뉴를 입력해주세요.");
			$inputMenu.select();
			return false;
			
		}else if($breakOpen.val() != ""){
			
			if($breakClose.val() == ""){
				alert("브레이크 타임 종료 시간을 입력하세요.");
				return false;
			}else if($breakOpen.val() > $breakClose.val()){
				alert("브레이크 타임 시작시간이 브레이크 타임 종료시간보다 늦을 수 없습니다.");
				return false;
			}

		}else if($breakClose.val() != ""){
			if($breakOpen.val() == ""){
				alert("브레이크 타임 시작 시간을 입력하세요.");
				return false;		
			}
		}
	})
});

</script>
</head>
<body>
	
    <div class="store-update-container">
        <form method="post" action="<%=request.getContextPath()%>/store/storeUpdate">
            <h2>가게 정보 수정</h2>
            <hr>
            <ul>
            	<input type="hidden" value="<%=store.getBoard_no()%>" name="boardNo"/>
            	<input type="hidden" value="<%=store.getMember_id()%>" name="memberId"/>
            	
            	<li>
                    <label for="">가게이름</label>
                    <input type="text" class="form-control" value="<%=store.getStore_name()%>" readonly="readonly">
            	</li>
            	
                <li>
                    <label for="tel">전화번호</label>
                    <input type="tel" class="form-control" id="input-tel" name="tel" value="<%=store.getStore_phone()%>">
                    <small class="form-text text-muted">유효한 전화번호가 아닙니다.</small>
                </li>

                <li>
                    <label for="address">주소</label>
                    <input type="text" class="form-control" id="input-address" name="address" value="<%=store.getLocation()%>">
                    <small class="form-text text-muted">유효한 주소가 아닙니다.</small>
                </li>

                <li>
                    <label for="exampleInputPassword1" style="display:block;">휴일</label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input day" type="checkbox" name="inlineRadioOptions" id="inlineRadio1" value="월" <%=dayList.contains("월") ? "checked" : "" %>>
                        <label class="form-check-label" for="inlineRadio1">월</label> 
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input day" type="checkbox" name="inlineRadioOptions" id="inlineRadio2" value="화" <%=dayList.contains("화") ? "checked" : "" %>>
                        <label class="form-check-label" for="inlineRadio2">화</label>
                     </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input day" type="checkbox" name="inlineRadioOptions" id="inlineRadio3" value="수" <%=dayList.contains("수") ? "checked" : "" %>>
                        <label class="form-check-label" for="inlineRadio3">수</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input day" type="checkbox" name="inlineRadioOptions" id="inlineRadio3" value="목" <%=dayList.contains("목") ? "checked" : "" %>>
                        <label class="form-check-label" for="inlineRadio3">목</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input day" type="checkbox" name="inlineRadioOptions" id="inlineRadio3" value="금" <%=dayList.contains("금") ? "checked" : "" %>>
                        <label class="form-check-label" for="inlineRadio3">금</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input day" type="checkbox" name="inlineRadioOptions" id="inlineRadio3" value="토" <%=dayList.contains("토") ? "checked" : "" %>>
                        <label class="form-check-label" for="inlineRadio3">토</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input day" type="checkbox" name="inlineRadioOptions" id="inlineRadio3" value="일" <%=dayList.contains("일") ? "checked" : "" %>>
                        <label class="form-check-label" for="inlineRadio3">일</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" name="inlineRadioOptions" id="alwaysOpen" value="연중무휴" <%=dayList.contains("연중무휴") ? "checked" : "" %>>
                        <label class="form-check-label" for="inlineRadio3">연중무휴</label>
                    </div>
                </li>

                <li>
                    <div class="form-group">
                        <label for="open-time">오픈시간</label>
                        <input type="time" class="form-control" id="open-time" name="open-time" value="<%=store.getOpen_hours()%>">
                    </div>
                </li>
                
                <li>
                    <div class="form-group">
                        <label for="close-time">클로즈시간</label>
                        <input type="time" class="form-control" id="close-time" name="close-time" value="<%=store.getClose_hours()%>">                      
                    </div>
                </li>
                
                <li>
                	<div class="form-group">
                        <label for="open-time">브레이크 타임 시작시간</label>
                        <input type="time" class="form-control" id="break-open" name="break-open" value="<%=store.getBreaktime_open() != null ? store.getBreaktime_open() : ""%>">
                        <small class="form-text text-muted"></small>
                    </div>
                </li>
                
                <li>
                    <div class="form-group">
                        <label for="close-time">브레이크 타임 종료시간</label>
                        <input type="time" class="form-control" id="break-close" name="break-close" value="<%=store.getBreaktime_close() != null ? store.getBreaktime_close() : ""%>">
                    </div>
                </li>

                <li>
                    <div class="form-group">
                        <label for="menu">대표메뉴</label>
                        <input type="text" class="form-control" id="input-menu" name="menu" value="<%=store.getMenu()%>" >                  
                    </div>
                </li>

                <li>
                    <label for="exampleInputPassword1">주차</label>
                    <select name="parking" class="custom-select">
                            <option value="Y" <%=store.getParking().equals("Y") ? "selected" : ""%>>Y(가능)</option>
                            <option value="N" <%=store.getParking().equals("N") ? "selected" : ""%>>N(불가능)</option>
                    </select>
                </li>

                <li>
                    <div class="form-row">
                      <div class="col-md-4 mb-3">
                          <label for="hashtag1">해시태그1</label>
                          <select class="custom-select" name="hashtag1">
                            <option value="강남맛집">강남맛집</option>
                            <option value="광진구맛집">광진구맛집</option>
                            <option value="마포맛집">마포맛집</option>
                            <option value="서초맛집">서초맛집</option>
                            <option value="용산맛집">용산맛집</option>
                            <option value="이탈리안">이탈리안</option>
                            <option value="일식">일식</option>
                            <option value="중식">중식</option>
                            <option value="태국">태국</option>
                            <option value="프랑스">프랑스</option>
                            <option value="한정식">한정식</option>
                            <option value="할랄푸드">할랄푸드</option>
                            <option value="한식">한식</option>
                          </select>
                      </div>
                      <div class="col-md-4 mb-3">
                          <label for="hashtag2" >해시태그2</label>
                          <select class="custom-select" name="hashtag2">
                            <option value="고기맛집">고기맛집</option>
                            <option value="국물음식">국물음식</option>
                            <option value="깔끔한">깔끔한</option>
                            <option value="냉면">냉면</option>
                            <option value="떡볶이">떡볶이</option>
                            <option value="분식">분식</option>
                            <option value="빵집">빵집</option>
                            <option value="수제버거">수제버거</option>
                            <option value="술안주">술안주</option>
                            <option value="초밥">초밥</option>
                            <option value="파스타">파스타</option>
                            <option value="한우">한우</option>
                            <option value="해산물">해산물</option>
                          </select>                
                      </div>
                      <div class="col-md-4 mb-3">
                          <label for="hashtag3">해시태그3</label>
                          <select class="custom-select" name="hashtag3">
                            <option value="tv맛집">tv맛집</option>
                            <option value="가성비맛집">가성비맛집</option>
                            <option value="가족모임">가족모임</option>
                            <option value="격식있는">격식있는</option>
                            <option value="술집">술집</option>
                            <option value="핫플">핫플</option>
                            <option value="혼밥">혼밥</option>
                            <option value="혼술">혼술</option>
                            <option value="회식장소">회식장소</option>
                            <option value="주차">주차</option>
                            <option value="캐주얼한">캐주얼한</option>
                            <option value="저녁식사">저녁식사</option>
                            <option value="조용한">조용한</option>
                            <option value="존맛탱">존맛탱</option>
                            <option value="인기카페">인기카페</option>
                            <option value="유기농식당">유기농식당</option>
                            <option value="아이동반">아이동반</option>
                            <option value="애견동반">애견동반</option>
                            <option value="예쁜카페">예쁜카페</option>
                            <option value="다이어트식">다이어트식</option>
                            <option value="단체모임">단체모임</option>
                            <option value="데이트코스">데이트코스</option>
                            <option value="디저트맛집">디저트맛집</option>
                          </select>
                        </div>
                    </div>
                </li>
            </ul>
        
             
              <div class="submit-cotainer">
                  <button type="submit" style="background-color: #FFA726; border: none;" class="btn btn-warning update-submit">수정하기</button>
              </div>

          </form>
    </div>
    
<%@ include file="/WEB-INF/view/common/footer.jsp" %>