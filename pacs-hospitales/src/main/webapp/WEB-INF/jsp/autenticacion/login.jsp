<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- BEGIN LOGIN FORM -->
<form class="login-form" action="/pacs-hospitales/j_spring_security_check"
	method="post">
	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		<font color="red"> Usuario o contraseña incorrectos.
		</font>
	</c:if>
	<h3 class="form-title">Ingresar</h3>
	<div class="form-group">
		<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
		<label class="control-label visible-ie8 visible-ie9">Usuario</label>
		<div class="input-icon">
			<i class="fa fa-user"></i> <input
				class="form-control placeholder-no-fix" type="text"
				autocomplete="off" placeholder="Correo" name="j_username" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label visible-ie8 visible-ie9">Password</label>
		<div class="input-icon">
			<i class="fa fa-lock"></i> <input
				class="form-control placeholder-no-fix" type="password"
				autocomplete="off" placeholder="Password" name="j_password" />
		</div>
	</div>
	<div class="form-actions">
		<button type="submit" class="btn green pull-right">
			Login <i class="m-icon-swapright m-icon-white"></i>
		</button>
	</div>
</form>
<!-- END LOGIN FORM -->