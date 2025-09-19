<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <style>
        body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif; }
        .container { max-width: 1100px; margin: 32px auto; }
        h1 { font-size: 28px; margin-bottom: 16px; }
        .nav { margin-bottom: 16px; }
        .nav a { color: #6a5acd; margin-right: 10px; text-decoration: none; }
        .form-row { margin: 12px 0; }
        input[type=text] { width: 140px; padding: 6px 8px; }
        button { padding: 6px 10px; cursor: pointer; }
        table { width: 100%; border-collapse: collapse; margin-top: 16px; }
        th, td { border: 1px solid #eaeaea; padding: 10px; text-align: left; }
        th { background: #14a34a; color: #fff; font-weight: 600; }
        .placeholder { text-align: center; color: #666; padding: 24px 0; }
        .badge { font-size:12px; color:#fff; background:#0ea5e9; padding:2px 6px; border-radius:4px; margin-left:6px;}
    </style>
</head>
<body>
<div class="container">
    <h1>와이파이 정보 구하기</h1>

    <div class="nav">
        <a href="<c:url value='/'/>">홈</a> |
        <a href="<c:url value='/history'/>">위치 히스토리 목록</a> |
        <a href="<c:url value='/import/wifi'/>">Open API 와이파이 정보 가져오기</a>
    </div>

    <form action="<c:url value='/nearby'/>" method="get">
        LAT: <input type="text" name="lat" />
        , LNT: <input type="text" name="lnt" />
        <button type="button" onclick="getMyLocation()">내 위치 가져오기</button>
        <button type="submit">근처 WIFI 정보 보기</button>
    </form>

    <table>
        <thead>
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="placeholder" colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
        </tr>
        </tbody>
    </table>
</div>

<script>
    function getMyLocation() {
        if (!navigator.geolocation) {
            alert('이 브라우저에서는 위치 정보를 지원하지 않습니다.');
            return;
        }
        navigator.geolocation.getCurrentPosition(function(pos) {
            document.querySelector('input[name="lat"]').value = pos.coords.latitude.toFixed(6);
            document.querySelector('input[name="lnt"]').value = pos.coords.longitude.toFixed(6);
        }, function(err) {
            alert('위치 정보를 가져오지 못했습니다: ' + err.message);
        });
    }
</script>
</body>
</html>