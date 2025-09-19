<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>근처 WIFI 정보</title>
    <style>
        body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif; }
        .container { max-width: 1100px; margin: 24px auto; }
        a { color:#6a5acd; text-decoration:none; margin-right:8px; }
        table { width:100%; border-collapse:collapse; margin-top:14px; }
        th, td { border:1px solid #eaeaea; padding:8px; text-align:left; }
        th { background:#14a34a; color:#fff; }
        .right { text-align:right; }
    </style>
</head>
<body>
<div class="container">
    <h1>근처 WIFI 정보</h1>

    <div>
        <a href="<c:url value='/'/>">홈</a> |
        <a href="<c:url value='/history'/>">위치 히스토리 목록</a> |
        <a href="<c:url value='/import/wifi'/>">Open API 와이파이 정보 가져오기</a>
    </div>

    <div style="margin-top:10px;">
        LAT: <strong><c:out value="${lat}"/></strong>,
        LNT: <strong><c:out value="${lnt}"/></strong>
    </div>

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
        <c:forEach var="w" items="${items}">
            <tr>
                <td class="right">
                    <fmt:formatNumber value="${w.distanceKm}" pattern="0.000"/>
                </td>
                <td><c:out value="${w.mgrNo}"/></td>
                <td><c:out value="${w.wrdofc}"/></td>
                <td><c:out value="${w.mainNm}"/></td>
                <td><c:out value="${w.adres1}"/></td>
                <td><c:out value="${w.adres2}"/></td>
                <td><c:out value="${w.instlFloor}"/></td>
                <td><c:out value="${w.instlTy}"/></td>
                <td><c:out value="${w.instlMby}"/></td>
                <td><c:out value="${w.svcSe}"/></td>
                <td><c:out value="${w.cmcwr}"/></td>
                <td><c:out value="${w.cnstcYear}"/></td>
                <td><c:out value="${w.inoutDoor}"/></td>
                <td><c:out value="${w.remars3}"/></td>
                <td><c:out value="${w.lnt}"/></td>
                <td><c:out value="${w.lat}"/></td>
                <td><c:out value="${w.workDttm}"/></td>
            </tr>
        </c:forEach>

        <c:if test="${empty items}">
            <tr><td colspan="17" style="text-align:center;color:#666;">조회 결과가 없습니다.</td></tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>