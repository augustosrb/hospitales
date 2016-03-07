<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="col-md-12 page-500">
	<div class=" number">403</div>
	<div class=" details">
		<h3>
			Oops! Usted no cuenta con los permisos </br> necesarios para acceder a la
			aplicación.
		</h3>
		<p>
			Para volver
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<spring:url value="/home" var="Login" htmlEscape="true" />
			</c:if>
			<c:if test="${pageContext.request.userPrincipal.name == null}">
				<spring:url value="/" var="Login" htmlEscape="true" />
			</c:if>

			<a href="${Login}"> Click Aqui </a>
		</p>




	</div>
</div>