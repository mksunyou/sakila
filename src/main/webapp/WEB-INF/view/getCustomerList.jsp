<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>getCustomerList</title>
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
			 $('#customerForm').submit();
		});
	});
</script>
</head>
<body>
<div class="container">
    <h1>getCustomerList</h1>
    <div>    	
    <!-- 검색어 입력창 -->
    	<form id="customerForm" action="${pageContext.request.contextPath}/admin/getCustomerList" method="get">	
	        <label for="SID"> SID :</label> 
    		 <select name="SID">
    			<option value="0">매장 선택</option>
    			<c:if test="${SID == 1}">
    				<option value="1" selected="selected">1</option>
    			</c:if>
    			<c:if test="${SID != 1}">
    				<option value="1">1</option>
    			</c:if>
    			<c:if test="${SID == 2}">
    				<option value="2" selected="selected">2</option>
    			</c:if>
    			<c:if test="${SID != 2}">
    				<option value="2">2</option>
    			</c:if>
    		</select>
	        <label for="name">검색어(name) :</label> 
	        <input name="name" type="text">
	        
	        <label for="phone">검색어(phone) :</label> 
	        <input name="phone" type="text">
	        
        	<button id="btn" type="button">검색</button>
    </form>
    </div>
    
    <a href="${pageContext.request.contextPath}/admin/getCustomerList">전체보기</a>
     <a href="${pageContext.request.contextPath}/admin/addCustomer">고객 추가</a>
    <table class="table table-striped">
        <thead>
			<tr>
				<th>ID</th>
				<th>name</th>
				<th>phone</th>
				<th>SID</th>
			</tr>
		</thead>
        <tbody>
            <c:forEach var="c" items="${customerList}">
                <tr>
                	<td>${c.ID}</td>
                    <td><a href="${pageContext.request.contextPath}/admin/getFilmOne?filmId=${c.ID}">${c.name}</a></td>
                    <td>${c.phone}</td>
					<td>${c.SID}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    
    
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage-1}&name=${name}&phone=${phone}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage+1}&name=${name}&phone=${phone}">다음</a></li>
        </c:if>
    </ul> 
 
</div>
</body>
</html>