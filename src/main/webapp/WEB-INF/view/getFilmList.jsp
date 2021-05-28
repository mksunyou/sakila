<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>getFilmList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 <!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#btn').click(function(){
			 console.log("btn click!");
			 $('#filmForm').submit();
		});
	});
</script>
</head>
<body>
<div class="container">
    <h1>getFilmList</h1>
    <div>
    	<form id="filmForm" action="${pageContext.request.contextPath}/admin/getFilmList" method="get">
    		<label for="category"> Category :</label> 
			<select name="categoryName">
			<option value="">카테고리선택</option>
    			<c:forEach var="c" items="${categoryNameList}">
    				<c:if test="${c.name == categoryName}"> 
    					<option value="${c.name}" selected="selected">${c.name}</option>
    				</c:if>
    				<c:if test="${c.name != categoryName}"> 
    					<option value="${c.name}">${c.name}</option>
    				</c:if>
    			</c:forEach>
    		</select>
    		
    		 <label for="price"> Price :</label> 
    		 <select name="price">
    			<option value="0">가격 선택</option>
    			<c:if test="${price ==0.99}">
    				<option value="0.99" selected="selected">0.99</option>
    			</c:if>
    			<c:if test="${price !=0.99}">
    				<option value="0.99">0.99</option>
    			</c:if>
    			<c:if test="${price ==2.99}">
    				<option value="2.99" selected="selected">2.99</option>
    			</c:if>
    			<c:if test="${price !=2.99}">
    				<option value="2.99">2.99</option>
    			</c:if>
    			<c:if test="${price ==4.99}">
    				<option value="4.99" selected="selected">4.99</option>
    			</c:if>
    			<c:if test="${price !=4.99}">
    				<option value="4.99">4.99</option>
    			</c:if>    			
    		</select>
    		
    		<label for="rating"> Rating :</label> 
			<select name="rating">
			<option value="">등급 선택</option>
    			<c:if test="${rating == 'G'}">
    				<option value="G" selected="selected">G</option>
    			</c:if>
    			<c:if test="${rating != 'G'}">
    				<option value="G">G</option>
    			</c:if>
    			<c:if test="${rating == 'PG'}">
    				<option value="PG" selected="selected">PG</option>
    			</c:if>
    			<c:if test="${rating != 'PG'}">
    				<option value="PG">PG</option>
    			</c:if>
    			<c:if test="${rating == 'PG-13'}">
    				<option value="PG-13" selected="selected">PG-13</option>
    			</c:if>
    			<c:if test="${rating != 'PG-13'}">
    				<option value="PG-13">PG-13</option>
    			</c:if>
    			<c:if test="${rating == 'R'}">
    				<option value="R" selected="selected">R</option>
    			</c:if>
    			<c:if test="${rating != 'R'}">
    				<option value="R">R</option>
    			</c:if>
    			<c:if test="${rating == 'NC-17'}">
    				<option value="NC-17" selected="selected">NC-17</option>
    			</c:if>
    			<c:if test="${rating != 'NC-17'}">
    				<option value="NC-17">NC-17</option>
    			</c:if>
    		</select>    		
    		
	        <label for="title">검색어(title) :</label> 
	        <input name="title" type="text">
	        
	        <label for="actors">검색어(actor) :</label> 
	        <input name="actors" type="text">
	        
        	<button id="btn" type="button">검색</button>
    </form>
    </div>
    
    <a href="${pageContext.request.contextPath}/admin/getFilmList">전체보기</a>
     <a href="${pageContext.request.contextPath}/admin/addFilm">영화 추가</a>
    <table class="table table-striped">
        <thead>
			<tr>
				<th>FID</th>
				<th>title</th>
				<th>category</th>
				<th>price</th>
				<th>length</th>
				<th>rating</th>
			</tr>
		</thead>
        <tbody>
            <c:forEach var="f" items="${filmList}">
                <tr>
                	<td>${f.FID}</td>
                    <td><a href="${pageContext.request.contextPath}/admin/getFilmOne?filmId=${f.FID}">${f.title}</a></td>
                    <td>${f.category}</td>
					<td>${f.price}</td>
					<td>${f.length}</td>
					<td>${f.rating}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <!-- 검색어 입력창 -->
    
    
    
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage-1}&categoryName=${categoryName}&price=${price}&rating=${rating}&title=${title}&actors=${actors}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage+1}&categoryName=${categoryName}&price=${price}&rating=${rating}&title=${title}&actors=${actors}">다음</a></li>
        </c:if>
    </ul> 
 
</div>
</body>
</html>