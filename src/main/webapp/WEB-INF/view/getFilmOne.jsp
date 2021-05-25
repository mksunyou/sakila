<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>getFilmOne</title>
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
    <h1>getFilmOne</h1>
     <table class="table">
         <tbody>
             <tr>
                <td>film_id:</td>
                <td>${filmMap.filmId}</td>
               </tr>
            <tr>
                   <td>title:</td>
                   <td>${filmMap.title}</td>
            </tr>
            <tr>
                   <td>description:</td>
                   <td>${filmMap.description}</td>
            </tr>
             <tr>
                   <td>actors:</td>
                   <td>${filmMap.actors}</td>
            </tr>
            <tr>
                   <td>releaseYear :</td>
                   <td>${filmMap.releaseYear}</td>
            </tr>
            <tr>
                   <td>language:</td>
                   <td>${filmMap.name}</td>
             </tr>
            <tr>
                   <td>rentalDuration:</td>
                   <td>${filmMap.rentalDuration}</td>
            </tr>
            <tr>
                   <td>rentalRate :</td>
                   <td>${filmMap.rentalRate}</td>
            </tr>
            <tr>
                   <td>length:</td>
                   <td>${filmMap.length}</td>
            </tr>
            <tr>
                   <td>replacementCost:</td>
                   <td>${filmMap.replacementCost}</td>
            </tr>
            <tr>
                   <td>rating:</td>
                   <td>${filmMap.rating}</td>
            </tr>
            <tr>
                   <td>specialFeatures:</td>
                   <td>${filmMap.specialFeatures}</td>
            </tr>
            <tr>
                   <td>lastUpdate:</td>
                   <td>${filmMap.lastUpdate}</td>
            </tr>
			<tr>
					<td>store1Stock</td>
    				<td>${store1Stock}</td>
            </tr>
            <tr>
					<td>store2Stock</td>
    				<td>${store2Stock}</td>
            </tr>
        </tbody>
    </table>
       <div>
          <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/">수정</a>
          <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/">삭제</a>
          <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getFilmList">영화 목록</a>   
       </div>
</div>
</body>
</html>