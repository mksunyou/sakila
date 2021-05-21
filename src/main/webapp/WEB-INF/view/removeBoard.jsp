<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeBoard</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	console.log('btn ready!');
	$('#btn').click(function(){
		console.log('btn click!'); // 웹브라우저 콘솔에서 확인 가능
		if($('#boardPw').val() == '') {
			alert('boardPw 입력!');
		} else {
			$('#removeForm').submit();
		}
		
	});
});
</script>
</head>
<body>
	<h1>removeBoard</h1>
	<form id="removeForm" action="${pageContext.request.contextPath}/admin/removeBoard" method="post">
		<input type="hidden" name="boardId" value="${boardId}">
		<div>
			<label>boardPw: </label>
			<input type="password" id="boardPw" name="boardPw"> 	
		</div>
		<button type="button" id="btn">삭제</button>
	</form>
</body>
</html>