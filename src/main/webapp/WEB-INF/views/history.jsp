<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>위치 히스토리 목록</title>
    <style>
        body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif; }
        .container { max-width: 1100px; margin: 24px auto; }
        h1 { font-size: 26px; margin-bottom: 16px; }
        a { color:#6a5acd; text-decoration:none; margin-right:8px; }
        table { width:100%; border-collapse:collapse; margin-top:14px; }
        th, td { border:1px solid #eaeaea; padding:10px; text-align:left; }
        th { background:#14a34a; color:#fff; }
        .center { text-align:center; }
        .btn-del { padding:4px 8px; border:1px solid #ccc; background:#fff; cursor:pointer; }
    </style>
    <script>
        function del(id) {
            if (confirm('정말 삭제하시겠습니까?')) {
                location.href = '<c:url value="/history/delete"/>?id=' + id;
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h1>위치 히스토리 목록</h1>

    <div>
        <a href="<c:url value='/'/>">홈</a> |
        <a href="<c:url value='/import/wifi'/>">Open API 와이파이 정보 가져오기</a>
    </div>

    <table>
        <thead>
        <tr>
            <th class="center" style="width:80px;">ID</th>
            <th>X좌표 (경도)</th>
            <th>Y좌표 (위도)</th>
            <th style="width:220px;">조회일자</th>
            <th class="center" style="width:90px;">비고</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="h" items="${items}">
            <tr>
                <td class="center"><c:out value="${h.id}"/></td>
                <td><c:out value="${h.x}"/></td>
                <td><c:out value="${h.y}"/></td>
                <td><c:out value="${h.queryDttm}"/></td>
                <td class="center">
                    <button class="btn-del" onclick="del(${h.id})">삭제</button>
                </td>
            </tr>
        </c:forEach>

        <c:if test="${empty items}">
            <tr><td class="center" colspan="5">저장된 조회 이력이 없습니다.</td></tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>