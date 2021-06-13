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
	console.log("document ready!");
	$('#btn').click(function(){
		console.log("btn click!");
		//폼 유효성검사
		$('#modifyForm').submit();
	});
 });
</script>
<title>modifyFilm</title>
</head>
<body>
   <div class="container">
      <h1>modifyFilm</h1>
      <form id="modifyForm" method="post" action="${pageContext.request.contextPath}/admin/modifyFilm">
         <table class="table table-hover">
          	<tr>
               <td>filmId</td>
               <td>
                  <input type="text" name="film.filmId" id="film.filmId" value="${filmMap.filmId}" readonly="readonly" class="form-control"><!-- FilmForm.film.title로 들어감. -->
               </td>
            </tr>
            <tr>
               <td>title</td>
               <td>
                  <input type="text" name="film.title" value="${filmMap.title}" id="title" class="form-control"><!-- FilmForm.film.title로 들어감. -->
               </td>
            </tr>
            <tr>
               <td>category</td>
               <td>
                  <select name="category.categoryId" id ="categoryId" class="form-control">
                     <c:forEach var="c" items="${categoryList}">
                        <c:if test="${c.name == filmMap.category}">
                     		<option value="${c.categoryId}" selected="selected">${c.name}</option>
                     	</c:if>
                     	<c:if test="${c.name != filmMap.category}">
                     		<option value="${c.categoryId}">${c.name}</option>
                     	</c:if>
                     </c:forEach>
                  </select>
               </td>
            </tr>
            <tr>
               <td>description</td>
               <td>
                  <textarea rows="5" cols="100" name="film.description" id="description" class="form-control">${filmMap.description}</textarea>
               </td>
            </tr>
            <tr>
               <td>releaseYear</td>
               <td>
                  <input type="text" name="film.releaseYear" value="${filmMap.releaseYear}" id="releaseYear" class="form-control">
               </td>
            </tr>
            <tr>
               <td>language</td>
               <td>
                  <select name="film.languageId" id ="language" class="form-control">
                     <c:forEach var="lang" items="${languageList}">
                     	<c:if test="${lang.languageId==filmMap.languageId}">
                        	<option value="${lang.languageId}" selected="selected">${lang.name}</option>
                        </c:if>
                        <c:if test="${lang.languageId!=filmMap.languageId}">
                        	<option value="${lang.languageId}">${lang.name}</option>
                        </c:if>
                     </c:forEach>
                  </select>
               </td>
            </tr>
            <tr>
               <td>originalLanguage</td>
               <td>
                  <select name="film.originalLanguageId" id ="originalLanguage" class="form-control">
                     <c:forEach var="lang" items="${languageList}">
                        <option value="${lang.languageId}">${lang.name}</option>
                     </c:forEach>
                  </select>
               </td>
            </tr>
            <tr>
               <td>rentalDuration</td>
               <td>
                  <input type="text" name="film.rentalDuration" id="rentalDuration" value="${filmMap.rentalDuration}" class="form-control">               
               </td>
            </tr>
            <tr>
               <td>rentalRate</td>
               <td>
                  <input type="text" name="film.rentalRate" id="rentalRate" value="${filmMap.rentalRate}" class="form-control">
               </td>
            </tr>
            <tr>
               <td>length</td>
               <td>
                  <input type="text" name="film.length" id="length" value="${filmMap.length}" class="form-control">
               </td>
            </tr>
            <tr>
               <td>replacementCost</td>
               <td>
                  <input type="text" name="film.replacementCost" id="replacementCost" value="${filmMap.replacementCost}" value="19.99" class="form-control">
               </td>
            </tr>
            <tr>
               <td>rating</td>
               <td>
                 	 <select name="film.rating" id ="rating" class="form-control">
	                    <option value="G" ${filmMap.rating == 'G'? 'selected="selected"': ''}>G</option>
	                    <option value="PG" ${filmMap.rating == 'PG'? 'selected="selected"': ''}>PG</option>
	                    <option value="PG-13" ${filmMap.rating == 'PG-13'? 'selected="selected"': ''}>PG-13</option>
	                    <option value="R" ${filmMap.rating == 'R'? 'selected="selected"': ''}>R</option>
	                    <option value="NC-17" ${filmMap.rating == 'NC-17'? 'selected="selected"': ''}>NC-17</option>
	                 </select>
               </td>
            </tr>
            <tr>
               <td>specialFeatures</td>
               <td>
                  <input type="checkbox" name="specialFeatures" value="Trailers" ${filmMap.specialFeatures.contains('Trailers') ? 'checked="checked"':''}>Trailers&nbsp;
                  <input type="checkbox" name="specialFeatures" value="Commentaries" ${filmMap.specialFeatures.contains('Commentaries') ? 'checked="checked"':''}>Commentaries&nbsp;
                  <input type="checkbox" name="specialFeatures" value="Deleted Scenes" ${filmMap.specialFeatures.contains('Deleted Scenes') ? 'checked="checked"':''}>Deleted Scenes&nbsp;
                  <input type="checkbox" name="specialFeatures" value="Behind the Scenes" ${filmMap.specialFeatures.contains('Behind the Scenes') ? 'checked="checked"':''}>Behind the Scenes
               </td>
            </tr>
         </table>
         
         <button id="btn" class="btn btn-secondary">등록</button>
      </form>
      
   </div>
</body>
</html>