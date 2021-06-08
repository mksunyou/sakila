<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>CustomerOne</title>
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
      console.log("document ready!");
      $('#btn').click(function(){
         console.log("btn click!");
         if ($('#username').val().length < 3){
            alert('username는 3자이상 이어야 합니다');
            $('#username').focus();
         } else if ($('#commentContent').val().length < 10) {
             alert('commentContent는 10자이상 이어야 합니다');
             $('#commentContent').focus();
         } else {
             $('#commentForm').submit();
         }
      });
   });
</script>
 
</head>
<body>
<div class="container">
    <h1>CustomerOne</h1>
     <div>
          <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/modifyCustomer?customerId=${customerMap.customerId}">수정</a>
          <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/removeBoard?customerId=${customerMap.customerId}">삭제</a>
          <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getCustomerList">고객목록</a>
        
     </div>
     <table class="table">
         <tbody>
             <tr>
                <td>customerId :</td>
                <td>${customerMap.customerId}</td>
            </tr>
            <tr>
                   <td>name :</td>
                   <td>${customerMap.name}</td>
            </tr>
            <tr>
                   <td>storeId :</td>
                   <td>${customerMap.storeId}</td>
            </tr>
            <tr>
                   <td>email :</td>
                   <td>${customerMap.email}</td>
            </tr>
            <tr>
                   <td>createDate :</td>
                   <td>${customerMap.createDate}</td>
            </tr>
            <tr>
                   <td>active :</td>
                   <td>${customerMap.active}</td>
            </tr>
            
            <tr>
                   <td>address :</td>
                   <td>${customerMap.address}</td>
            </tr>
            <tr>
                   <td>zipcode :</td>
                   <td>${customerMap.zipcode}</td>
            </tr>
            <tr>
                   <td>phone :</td>
                   <td>${customerMap.phone}</td>
            </tr>
            <tr>
                   <td>city :</td>
                   <td>${customerMap.city}</td>
            </tr>
            <tr>
                   <td>country :</td>
                   <td>${customerMap.country}</td>
            </tr>
            <tr>
                   <td>total payment :</td>
                   <td>${paymentMap.totalPayment}</td>
            </tr>
           	
        </tbody>
    </table>
    <div>
      <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/addRental?customerId=${customerMap.customerId}">대출</a>
	  <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/addReturn?customerId=${customerMap.customerId}">반납</a>
    </div>
    
    <table class="table">
    	<thead>
    		<tr>
    			<th>customerId</th>
    			<th>inventoryId</th>
    			<th>storeId</th>
    			<th>rentalId</th>
    			<th>rentalDate</th>
    			<th>returnDate</th>
    			<th>title</th>
    			<th>amount</th>
    			<th>status</th>
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach var="r" items="${rentalList}">
	    		<tr>
	    			<td>${r.customerId }</td>
	    			<td>${r.inventoryId }</td>
	    			<td>${r.storeId }</td>
	    			<td>${r.rentalId }</td>
	    			<td>${r.rentalDate }</td>
	    			<td>${r.returnDate }</td>
	    			<td>${r.title }</td>
	    			<td>${r.amount }</td>
	    			<td>${r.status }</td>
	    		</tr>
    		</c:forEach>
    		<tr>
    			<td>
    		</tr>
    	</tbody>
    </table>   
</div>
</body>
</html>