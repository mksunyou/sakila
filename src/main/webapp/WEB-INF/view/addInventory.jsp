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
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$('#btn').click(function(){
		console.log('click!');
		
		//유효성 검사 만들기
		$('#addInventoryAction').submit();
	});
	
	
	
});
</script>
<title>addInventory</title>
</head>
<body>
   <div class="container">
      <h1>addInventory</h1>
      <form id="addInventoryAction" method="post" action="${pageContext.request.contextPath}/admin/addInventory">
         <table class="table table-hover">
             <tr>
	           <th>영화 선택</th>
	            <td>
	              <input list="film" name="filmId">
					<datalist id="film">
						<c:forEach items="${filmList}" var="f">
							<option value="${f.filmId}">${f.title}</option>
						</c:forEach>
					</datalist>
	            </td>
	         </tr>
	         <tr>
	            <th>등록 지점</th>
	            <td>
	               <select name="storeId" id="storeId">
	                  <option value="1">1호점</option>
	                  <option value="2">2호점</option>
	               </select>
	            </td>
	         </tr>
         </table>
         
         <button id="btn" class="btn btn-default">등록</button>
      </form>
      
   </div>
</body>
</html>