<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>getInventoryList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#btn').click(function(){
			 console.log("btn click!");
			 $('#inventoryForm').submit();
		});
	});
</script>
</head>
<body>
<div class="container">
    <h1>getInventoryList</h1>
     <div>    	
    <!-- 검색어 입력창 -->
    	<form id="inventoryForm" action="${pageContext.request.contextPath}/admin/getInventoryList" method="get">	
    		
	        <label for="title">검색어(name) :</label> 
	        <input name="title" type="text">
	        
        	<button id="btn" type="button">검색</button>
    </form>
    </div>
    <a href="${pageContext.request.contextPath}/admin/addInventory">재고 추가</a>
    <a href="${pageContext.request.contextPath}/admin/removeInventory">재고 삭제</a>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>filmId</th>
                <th>title</th>
                <th>총재고량</th>
                <th>매장1 재고</th>
                <th>매장2 재고</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="${inventoryList}">
                <tr>
                	<td>${i.filmId}</td>
                	<td>${i.title}</td>
                	<td>
	                	<c:if test="${i.total==null}">
	                		${i.total}=0;
	                	</c:if>
	                	<c:if test="${i.total!=null}">
	                		${i.total}
	                	</c:if>
	                </td>
                	<td>
	                	<c:if test="${i.store1==null}">
	                		0
	                	</c:if>
	                	<c:if test="${i.store1!=null}">
	                		${i.store1}
	                	</c:if>
	                </td>
	                <td>
	                	<c:if test="${i.store2==null}">
	                		0
	                	</c:if>
	                	<c:if test="${i.store2!=null}">
	                		${i.store2}
	                	</c:if>
	                </td>
                	
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getInventoryList?currentPage=${currentPage-1}&title=${title}&storeId=${storeId}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getInventoryList?currentPage=${currentPage+1}&title=${title}&storeId=${storeId}">다음</a></li>
        </c:if>
    </ul> 

</div>
</body>
</html>