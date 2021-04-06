<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.List"%>
<%@page import="store.model.vo.Store"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/reservationRequest.css">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.kr.min.js" integrity="sha512-2XuQYSojalNmRQyWxr1Dr+KWZ7Gn6JgWMZvPxIYwGFRVA1r8RPWHRWybIu8zp/G2EtTTAXh56aMpk99pkrrpNA==" crossorigin="anonymous"></script>

<script src="https://kit.fontawesome.com/d37b4c8496.js" crossorigin="anonymous"></script>
<script src="<%= request.getContextPath() %>/js/reservationRequest.js"></script>


<link rel="stylesheet" href="<%= request.getContextPath() %>/css/newTpl.css">

<%@ include file="/WEB-INF/view/common/header.jsp" %>

<%
	Store store = (Store)request.getAttribute("store");
	List<String> fileNameList = (List<String>)request.getAttribute("fileNameList");
	System.out.println(fileNameList.size());
%>
<script type="text/javascript">
$(function(){
	console.log(getDaysDisabled());
	$('#datepicker').datepicker({
        format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
        startDate: '0d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
        endDate: '+50d',	//달력에서 선택 할 수 있는 가장 느린 날짜. 이후로 선택 불가 ( d : 일 m : 달 y : 년 w : 주)
        autoclose : false,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
        calendarWeeks : false, //캘린더 옆에 몇 주차인지 보여주는 옵션 기본값 false 보여주려면 true
        clearBtn : false, //날짜 선택한 값 초기화 해주는 버튼 보여주는 옵션 기본값 false 보여주려면 true
        datesDisabled : ['2019-06-24','2019-06-26'],//선택 불가능한 일 설정 하는 배열 위에 있는 format 과 형식이 같아야함.
        daysOfWeekDisabled : getDaysDisabled(),	//선택 불가능한 요일 설정 0 : 일요일 ~ 6 : 토요일
        disableTouchKeyboard : false,	//모바일에서 플러그인 작동 여부 기본값 false 가 작동 true가 작동 안함.
        immediateUpdates: false,	//사용자가 보는 화면으로 바로바로 날짜를 변경할지 여부 기본값 :false 
        multidate : false, //여러 날짜 선택할 수 있게 하는 옵션 기본값 :false 
        multidateSeparator :",", //여러 날짜를 선택했을 때 사이에 나타나는 글짜 2019-05-01,2019-06-01
        todayHighlight : true ,	//오늘 날짜에 하이라이팅 기능 기본값 :false 
        toggleActive : true,	//이미 선택된 날짜 선택하면 기본값 : false인경우 그대로 유지 true인 경우 날짜 삭제
        weekStart : 0 ,//달력 시작 요일 선택하는 것 기본값은 0인 일요일 
        language : "kr",	//달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
        inline: true,
        sideBySide: true,
        changeYear: false,
        minViewMode: 0,
        maxViewMode:0,
    }).on("changeDate", function(e) {
        var day = getDateStr(e.date,e.date.getDay());
        $(".input-day").val(day);
    });
	
	$(".timepicker").change(function(e){
		$this = $(this);
		
		if($this.val() <= "<%=store.getOpen_hours()%>" || $this.val() >= "<%=store.getClose_hours()%>"){
			alert("오픈시간보다 더 빠른시간이거나 클로즈 시간보다 더 늦은 시간일 수 없습니다. 다시 선택해주세요.");
			$this.val("");
			$this.focus();
			return;
			
		}else if($this.val() >= "<%=store.getBreaktime_open()%>" && $this.val() <= "<%=store.getBreaktime_close()%>"){
			alert("브레이크 타임에 예약하실 수 없습니다.");
			$this.val("");
			$this.focus();
			return;
		}
	})
	
	
	function getDaysDisabled(){
		var close = "<%=store.getClosing_day()%>";

		if(close == '연중무휴'){
			return false;
			
		}else{
			var dayArr = ['일','월','화','수','목','금','토'];
			var closeArr = close.split(",");
			var result = [];
			
			for(var i = 0; i < closeArr.length; i++){
				
				if(dayArr.indexOf(closeArr[i]) != -1){
						result.push(dayArr.indexOf(closeArr[i]));
				}
			}
			
			return result;
		}
	}
})

</script>
<title>예약 신청</title>
</head>
<body>

    <div class="container">
        
            <p class="store-name">
            <a href=""><i class="fas fa-home"></i></a>   <%=store.getStore_name()%>  예약하기
            </p>
            <hr class="store-hr">

            <div class="reservation">

                <div class="reservation-info">
                    <div id="carouselExampleSlidesOnly" class="carousel slide store-img" data-ride="carousel">
                        <div class="carousel-inner">
                          <%if(fileNameList != null && fileNameList.size() == 3) {%>
                           	<div class="carousel-item active">
                            	<img class="d-block w-100" src="<%=request.getContextPath()%>/upload/store/<%=fileNameList.get(0)%>" alt="First slide">
                          	</div>
                          	<div class="carousel-item">
                            	<img class="d-block w-100" src="<%=request.getContextPath()%>/upload/store/<%=fileNameList.get(1)%>" alt="Second slide">
                          	</div>
                          	<div class="carousel-item">
                            	<img class="d-block w-100" src="<%=request.getContextPath()%>/upload/store/<%=fileNameList.get(2)%>" alt="Third slide">
                          	</div>
                          <%}else{%>
	                          <div class="carousel-item active">
	                            <img class="d-block w-100" src="https://images.unsplash.com/photo-1467003909585-2f8a72700288?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80" alt="First slide">
	                          </div>
	                          <div class="carousel-item">
	                            <img class="d-block w-100" src="https://images.unsplash.com/photo-1590166774851-bc49b23a18fe?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=635&q=80" alt="Second slide">
	                          </div>
	                          <div class="carousel-item">
	                            <img class="d-block w-100" src="https://images.unsplash.com/photo-1544962059-b04e6d47e9a0?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80" alt="Third slide">
	                          </div>
                          <%} %>
                          </div>
                      </div>
 
                        <div class="info-container" >
                            <p class="info-text">
                            	<br>
                               	<%=store.getStore_name()%> <br/>
                                                           대표 메뉴 : <%=store.getMenu()%><br/>
                                OPEN : <%=store.getOpen_hours()%><br/>
                                CLOSE : <%=store.getClose_hours()%><br/>
                                BREAKTIME : <%=store.getBreaktime_open() == null ? "없음" : store.getBreaktime_open() + "~" + store.getBreaktime_close()%><br/>
                                                           휴일 : <%=store.getClosing_day() %><br/>
                                <br />
                                <br />
                                                           장소 : <%=store.getLocation() %><br/>
								주차 : <%=store.getParking().equals("Y") ? "주차 가능" : "주차 불가능"%><br>                                                          
                                                           문의전화 : <%=store.getStore_phone()%>                           
                            </p>
                        </div>

                        <div class="notice-container" >
                            <p class="notice">
                                <i class="far fa-question-circle"></i> 알립니다<br>
                            </p>

                            <p class="notice-text"> 
                                *가게 사정에 따라 예약이 거절 될 수 있습니다. <br>
                                *당일 예약은 취소가 불가능합니다.<br>
                                *환불은 고객센터로 문의 바랍니다.<br>
                            </p>
                        </div>
                </div>

                <div class="reservation-container">
                    <form  id="reservation-form" method="post" action="<%=request.getContextPath() %>/reservation/request">                      	                 
                      	<input type="hidden" value="<%=store.getBoard_no()%>" name="boardNo" />                 

                        <div class="datepicker" id="datepicker" data-date="02/27/2021"></div>
                         
                        <div class="input-reservation-info">
                            <ul>
                                <li class="text-day">
                                   <label for="input-day">날짜</label>
                                   <input name="input-day" class="input-day" type="text" placeholder="달력에서 날짜를 선택해주세요." readonly>
                                </li>

                                <li>
                                    <hr>
                                </li>
                                
                                <li class="text-time">
                                    <label for="timepicker">시간</label>         
                                    <input name="time" type="time" class="timepicker" required>
                                </li>
                                
                                <li>
                                    <hr>
                                </li>
                                
                                <li>
                                    <label for="child-num">아이인원</label>
                                    <input name="child-num" class="child-num" type="number" min="0" max="15" value="0" required>
                                </li>

                                <li>
                                    <label for="adult-num">어른인원</label>
                                    <input name="adult-num" class="adult-num" type="number" min="1" max="15" value="0" required>
                                </li>

                                <li>
                                    <hr>
                                </li>

                                <li class="text-sum">
                                    <p>합계</p>
                                    <p id="sum">0원</p>
                                    <input type="hidden" id="child-sum">
                                    <input type="hidden" id="adult-sum">
                                </li>
                            </ul>
                        </div>

                        <div class="customer-info">
                            <ul>
                                <li>
                                    <p>예약자 정보</p>
                                </li>

                                <li>
                                    <hr>
                                </li>

                                <li>
                                    <label for="memberId">예약자 아이디</label>
                                    <input name="memberId" type="text" class="memberId_" value="<%=member.getMemberId()%>" readonly>
                                </li>

                                <li>
                                    <label for="customer-phone">연락처</label>
                                    <input name="customer-phone" type="tel" placeholder="-을 빼고 입력해주세요." class="customer-phone" required>
                            </ul>
                        </div>

                        <div class="consent-container">
                            <ul>
                                <li>
                                    <label for="inputState">개인정보제공동의</label>
                                </li>

                                <li>
                                    <hr>
                                </li>


                                <li>
                                    <select id="inputState" class="form-control consent">
                                        <option selected>선택해주세요.</option>
                                        <option>동의합니다.</option>
                                        <option>동의하지않습니다.</option>
                                    </select>
                                </li>

                            </ul>
                        </div>
                        <input type="submit" value="예약하기" id="submit-btn"/>
                    </form>
                </div>
        </div> 
    </div>
<%@ include file="/WEB-INF/view/common/footer.jsp" %>