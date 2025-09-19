<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>와이파이 가져오기 결과</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 80px; }
        h1 { font-size: 22px; margin-bottom: 20px; }
        a { color: #6a5acd; text-decoration: none; margin: 0 8px; }
    </style>
</head>
<body>
<h1><c:out value="${saved}" />개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
<div>
    <a href="<c:url value='/'/>">홈으로 가기</a>
    |
    <a href="<c:url value='/history'/>">위치 히스토리 목록</a>
</div>
</body>
</html>
