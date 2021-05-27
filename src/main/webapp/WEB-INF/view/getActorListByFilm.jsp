<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>영화(filmId: ${filmId}) 출연배우(actor) 수정</h1>
	<div>
		<form action="${pageContext.request.contextPath}/admin/modifyFilmActor" method="post">
			<input type="hidden" name="filmId" value="${filmId}">
			<c:forEach var="m" items="${actorList}">
					<c:if test="${m.filmId == null}">
						<input type="checkbox" name="actorId" value="${m.actorId}">
					</c:if>
					<c:if test="${m.filmId != null}">
						<input type="checkbox" name="actorId" value="${m.actorId}" checked="checked" >
					</c:if>
					<span style="color:red">${m.name.substring(0,1)}</span>
					${m.name.substring(1)}&nbsp;
			</c:forEach>
			<div>
				<button type="submit" id="btn">출연배우 수정</button>
			</div>
		</form>
	</div>
</body>
</html>