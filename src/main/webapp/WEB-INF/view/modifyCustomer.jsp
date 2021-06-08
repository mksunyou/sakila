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
<!-- jquery를 사용하기위한 CDN주소 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	console.log('ready!');
	
	$('#countryId').change(function cityList(){
		console.log('city 목록');
		$.ajax({
			type:'get',
			url:'/getCityList',
			data:{countryId : $('#countryId').val()},
			success: function(jsonData) {
				$('#cityId').empty();
				$(jsonData).each(function(index, item) {
					$('#cityId').append(
						'<option value="'+item.cityId+'">'+item.city+'</option>'
					);
				});
				$('#target').empty();
				$('#target').append('<input type="text" name="city" id="city" placeholder="city">');
				$('#target').append('<button id="cityBtn" type="button">도시추가</button>');
				
				
				$('#cityBtn').click(function(){
					console.log('도시추가');
					$.ajax({
						type: 'get',
						url: '/addCity',
						data: {city : $('#city').val(), countryId : $('#countryId').val()},
						success: function(jsonData){
							console.log('도시추가 성공');
							
							cityList();
						}
					});
				});
				
			}		
		}); 	
	});
	
	
	$('#phoneCheckBtn').click(function() {
		console.log('click');
		
		$.ajax({
			type: 'get',
			url: '/getPhoneByCustomer',
			data: {phone: $('#phone').val()},
			success: function(jsonData){
				console.log(jsonData);
				
				if(jsonData == ''){
					console.log('수정 gogo');
					$('#phoneTarget').empty();
					$('#phoneTarget').append('중복 검사 성공!');
					
				} else {
					console.log('휴대폰 있음....');
					$('#phoneTarget').empty();
					$('#phoneTarget').append('중복된 휴대폰 번호가 있어요');
				}
			}
		});
	});
	
	$('#btn').click(function(){
		console.log('click!');
		
		//유효성 검사 만들기
		$('#modifyCustomerAction').submit();
	});
	
	
	
});
</script>
<title>modifyCustomer</title>
</head>
<body>
   <div class="container">
      <h1>modifyCustomer</h1>
      <form id="modifyCustomerAction" method="post" action="${pageContext.request.contextPath}/admin/modifyCustomer">
         <table class="table table-hover">
             <tr>
             	<th>CustomerId</th>
             	<td>
             		<input type="text" id="customer.customerId" name="customer.customerId" value="${customerMap.customerId}" readonly="readonly">
             	</td>
             </tr>
             <tr>
    	        <th>등록 지점</th>
	            <td>
	               <select name="customer.storeId" id="storeId">
	                  <option value="1">1호점</option>
	                  <option value="2">2호점</option>
	               </select>
	            </td>
	         </tr>
	         <tr>
	            <th>name</th>
	            <td>
	               <div>first_name : <input type="text" id="firstName" name="customer.firstName" placeholder="firstName"></div>
	               <div>last_name : <input type="text" id="lastName" name="customer.lastName" placeholder="lastName"></div>
	            </td>
	         </tr>
	         <tr>
	            <th>email</th>
	            <td><input type="email" id="email" name="customer.email" value="${customerMap.email}" placeholder="email"></td>
	            
	         </tr>
	         <tr>
					<th>address</th>
					<td>
						<div>
								<select name="countryId" id="countryId">
								<c:forEach items="${countryList}" var="c">
									<option value="${c.countryId}">${c.country}</option>
								</c:forEach>	
							</select>
						</div>
						<div>
							<select name="address.cityId" id="cityId"></select>
							
								<span id="target"></span>
							
							<div>
								<input id="address" name="address.address" type="text" placeholder="address">
							</div>
						</div>
					</td>
				</tr>
	         <tr>
	            <th>address2</th>
	            <td><input type="text" id="address2" placeholder="${customerMap.address2}" name="address.address2"></td>
	         </tr>
	         
	         <tr>
	            <th>district</th>
	            <td><input id="district" type="text" name="address.district"></td>
	         </tr>
	         <tr>
	            <th>postal_code</th>
	            <td><input id="postalCode" type="text" placeholder="${customerMap.zipcode}" name="address.postalCode"></td>
	         </tr>
	         
	         <tr>
	            <th>phone</th>
	            <td>
	               <input id="phone" type="text" placeholder="${customerMap.phone}" name="address.phone">
	               <button type="button" id="phoneCheckBtn">중복검사</button>
	               <span id="phoneTarget">중복 검사를 해주세요.</span>
	            </td>
	         </tr>
			
         </table>
         
         <button id="btn" class="btn btn-default">등록</button>
      </form>
      
   </div>
</body>
</html>