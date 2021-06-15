<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.9/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.9/js/select2.min.js"></script>
<!-- jquery를 사용하기위한 CDN주소 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	// #inputInventory 추가
		$('#addInventory').click(function(){
    	console.log('#addRentalBtn click!');
    	$('#inputInventory').append('<div>${inventoryId} ${title} ${cost}</div>')
    });
	
		// #inputInventory 추가
		$('#delInventory').click(function(){
    	console.log('#delRentalBtn click!');
    	$('#inputInventory').children().last().remove();
    });
});
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	<h1>addRental</h1>
	<table class="table table-hover">
		<tr>
			<td>
				customerId
			</td>
			<td>
				<input type="text" name="customerId" value="${filmMap.customerId}" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td>
				inventory검색
			</td>
			<td>
				 <input list="film" name="filmId">
					<datalist id="film">
						<c:forEach items="${filmList}" var="f">
							<option value="${f.inventoryId}">${f.title}</option>
						</c:forEach>
					</datalist>
			   	<button id="addInventory" type="button">영화 추가</button>
			   	<button id="delInventory" type="button">영화 삭제</button>
			   	<div id="inputInventory"></div>
			</td>
		</tr>
		
		<tr>
			<td>
				payment
			</td>
			<td>
				<input type="text" name="inventoryId" value="inventoryId"> 영화제목 + 가격 + 여러개 선택 가능				
			</td>
		</tr>
	</table>
	<button id="btn">대출</button>
	</div>
</body>
</html>