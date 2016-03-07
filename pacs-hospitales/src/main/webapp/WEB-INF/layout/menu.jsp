<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
	<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
	<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
	<div class="page-sidebar md-shadow-z-2-i  navbar-collapse collapse">
		<!-- BEGIN SIDEBAR MENU -->
		<ul class="page-sidebar-menu " data-keep-expanded="false"
			data-auto-scroll="true" data-slide-speed="200">
			<c:forEach items="${sessionScope.menu}" var="menu">
				<li><a href="javascript:;"> <span class="title"><c:out
								value="${menu.nombre}" /> </span> <span class="arrow "></span></a>
					<ul class="sub-menu">
						<c:forEach items="${menu.listMenu}" var="menu1">
							<li><a href='<spring:url value="${menu1.ruta}" />'> <c:out
										value="${menu1.nombre}" />
							</a></li>
						</c:forEach>
					</ul></li>
			</c:forEach>
			<!-- END ANGULARJS LINK -->
		</ul>
		<!-- END SIDEBAR MENU -->
	</div>
</div>
<!-- END SIDEBAR -->