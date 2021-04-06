$(function() {

    $('#datepicker').datepicker({
        format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
        startDate: '0d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
        endDate: '+50d',	//달력에서 선택 할 수 있는 가장 느린 날짜. 이후로 선택 불가 ( d : 일 m : 달 y : 년 w : 주)
        autoclose : false,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
        calendarWeeks : false, //캘린더 옆에 몇 주차인지 보여주는 옵션 기본값 false 보여주려면 true
        clearBtn : false, //날짜 선택한 값 초기화 해주는 버튼 보여주는 옵션 기본값 false 보여주려면 true
        datesDisabled : ['2019-06-24','2019-06-26'],//선택 불가능한 일 설정 하는 배열 위에 있는 format 과 형식이 같아야함.
        daysOfWeekDisabled : false,	//선택 불가능한 요일 설정 0 : 일요일 ~ 6 : 토요일
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

    $(".child-num").change(function(){
        var $sum = $("#sum");
        var $sumInt = $("#child-sum");
        var num = $(".child-num").val();
        
        //어른 밥값
        var adultSum = 0;
        adultSum = $("#adult-sum").val();

        //1. 어린이 밥값 계산후 어린이 밥값 자리에 넣기
        var child = num * 5000;
        $sumInt.val(child);

        //2. 어린이 밥값과 어른의 밥값 합쳐서 문자열로 출력하기
        var result = child + Number(adultSum);
        $sum.text(new Intl.NumberFormat('en-IN', { maximumSignificantDigits: 3 }).format(result) + '원');
    });

    $(".adult-num").change(function(){
        var $sum = $("#sum");
        var $sumInt = $("#adult-sum");
        var num = $(".adult-num").val();

        //어린이 밥값
        var childSum = 0;
        childSum = $("#child-sum").val();

        //1. 어른의 밥값 계산후 어른밥값 자리에 넣기
        var adult = num * 5000;
        $sumInt.val(adult);

        //2. 어른이 밥값이랑 어린이 밥값 합쳐서 문자열로 출력하기
        var result =  adult + Number(childSum);
        $sum.text(new Intl.NumberFormat('en-IN', { maximumSignificantDigits: 3 }).format(result) + '원');
    });

    $("#reservation-form").submit(function(){
	    var	$aultNum = $(".adult-num");
		var $customerPhone = $(".customer-phone");
		var $consent = $(".consent");
		var $intputDay = $(".input-day");
		var $timepicker = $(".timepicker");
		
		if($intputDay.val().length != 12){
			 alert("예약날짜를 선택해주세요.");
			 $intputDay.focus();
		     return false;
		
		}else if($timepicker.val() == ''){
			alert("예약 시간을 선택해주세요.")
			$timepicker.focus();
			return false;
			
		}else if($aultNum.val() == 0){
			alert("어른 인원이 1명 미만일 수 없습니다.");
			$aultNum.focus();
			return false;
			
		}else if(/^010[0-9]{8}$/.test($customerPhone.val()) == false){
			alert("유효한 전화번호가 아닙니다.");
			$customerPhone.select();
			return false;
			
		}else if($consent.val() != '동의합니다.'){
            alert("개인정보제공 동의를 하셔야 예약이 가능합니다.");
			$consent.focus();
            return false;
		}

        return true;
    })

});

function getDateStr(date, day){
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var date = date.getDate();
	var dayOfWeek = ['일','월','화','수','목','금','토'];

	month = month > 9 ? month : "0" + month;
	date = date > 9 ? date : "0" + date;

	return year + '.' + month  + '.' + date + ' ' + dayOfWeek[day];
}