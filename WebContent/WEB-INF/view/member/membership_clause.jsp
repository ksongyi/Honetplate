<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>memebership clause</title>
    <link rel="stylesheet" href="./css/memebership_clause.css">
    <link rel="stylesheet" href="./css/slick.css" />
    <link rel="stylesheet" href="./css/template.css" />
    <link rel="stylesheet" href="./css/custom.css" />
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <!-- JS, Popper.js, and jQuery -->
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    
    <script type="text/javascript" src="./js/memebership_clause.js"></script>

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;900&display=swap" rel="stylesheet">

</head>
<body>
    <form action="" id="joinForm">
        <ul class="join_box">
            <li class="checkBox check01">
                <ul class="clearfix">
                    <li>
                        <h4>허니플레이트</h4>
                        <h5>서비스약관에 동의해주세요.</h5>
                    </li>
                </ul>
            </li>
            <li class="checkBox check02">
                <ul class="clearfix">
                    <li>모두 동의합니다.</li>
                    <li class="checkBtn">
                        <input type="checkbox" name="chk" value="selecetall" onclick="selectAll(this)"> 
                    </li>
                </ul>
                <textarea name="" id=""> 전체동의는 필수 및 선택정보에 대한 동의도 포함되어 있으며, 개인적으로도 동의를 선택하실 수 있습니다. 선택항목에 대한 동의를 거부하시는 경우에도 서비스는 이용이 가능합니다.
                </textarea>
            </li>
            <hr>

            <li class="checkBox check03">
                <ul class="clearfix">
                    <li>만 14세 이상입니다.</li>
                    <li class="checkBtn">
                        <input type="checkbox" name="chk">
                    </li>
                </ul>
            </li>
            <li class="checkBox check03">
                <ul class="clearfix">
                    <li>허니플레이트계정 약관</li>
                    <li class="checkBtn">
                        <input type="checkbox" name="chk">
                    </li>
                </ul>
            </li>
            <li class="checkBox check03">
                <ul class="clearfix">
                    <li>허니플레이트 서비스약관</li>
                    <li class="checkBtn">
                        <input type="checkbox" name="chk">
                    </li>
                </ul>
            </li>
            <br>

            <li class="checkBox check03">
                <ul class="clearfix">
                    <li>[선택] 광고메일 수신</li>
                    <li class="checkBtn">
                        <input type="checkbox" name="chk">
                    </li>
                </ul>
            </li>
            <li class="checkBox check03">
                <ul class="clearfix">
                    <li>[필수]개인정보 수집 및 이용 동의</li>
                    <li class="checkBtn">
                        <input type="checkbox" name="chk">
                    </li>
                </ul>
            </li>
            <hr>

            
            <li class="checkBox check03">
                <ul class="clearfix">
                    <li>일반 가입자 입니다</li>
                    <li class="checkBtn">
                        <input type="radio" name="membertype">
                    </li>
                </ul>
            </li>
            <li class="checkBox check03">
                <ul class="clearfix">
                    <li>사업자 가입자 입니다</li>
                    <li class="checkBtn">
                        <input type="radio" name="membertype">
                    </li>
                </ul>
            </li>

        <ul class="footBtwrap clearfix">
            <li><button class="agreeb1">동의</button></li>
        </ul>
        
    </form>
</body>
</html>