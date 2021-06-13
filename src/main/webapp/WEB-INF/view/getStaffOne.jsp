<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>getStaffOne</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 
 
</head>
<body>
<div class="container">
    <h1>getStaffOne</h1>
     <table class="table">
         <tbody>
         	<tr>
         		<td>picture</td>
         		<td>${map.picture}</td>         		
         	</tr>
             <tr>
                <td>Id:</td>
                <td>${map.staffId}</td>
               </tr>
            <tr>
                   <td>name:</td>
                   <td>${map.name}</td>
            </tr>
            <tr>
                   <td>email:</td>
                   <td>${map.email}</td>
            </tr>            
            <tr>
                   <td>phone:</td>
                   <td>${map.phone}</td>
            </tr>
            <tr>
                   <td>districe:</td>
                   <td>${map.district}</td>
            </tr>
            <tr>
                   <td>address:</td>
                   <td>${map.address}</td>
            </tr>
            <tr>
                   <td>active:</td>
                   <td>
                   		<c:if test="${map.active==true}">
                   			활동 직원
                   		</c:if>
                   		<c:if test="${map.active==false}">
                   			휴면 직원
                   		</c:if>
                   </td>
            </tr>
            <tr>
                   <td>storeId:</td>
                   <td>${map.storeId}</td>
            </tr>
        </tbody>
    </table>
       <div>
          <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/modifyStaff?staffId=${map.Id}">수정</a>
          <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/removeStaff">삭제</a>
          <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getStaffList">직원 목록</a>   
       </div>
</div>
</body>
</html>