<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	
	<style>
		.desc {
		}
	</style>
	
</head>
<body>
<h1>
	안녕 Hello ${name}!!!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<ul>
	<c:forEach var="film" items="${filmList}">
		<li><span>${film.title }</span> - <span class="desc">${film.description }</span></li>
	</c:forEach>
</ul>
</body>
</html>
