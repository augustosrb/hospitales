<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
	
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 4.1.0
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>Metronic | Page Layouts - Blank Page</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/assets/global/plugins/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/assets/global/plugins/uniform/css/uniform.default.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"/>" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE DATATABLES STYLES -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/global/plugins/select2/select2.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>"/>
<!-- END PAGE DATATABLES STYLES -->
<!-- BEGIN PAGE TOASTR STYLES -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/global/plugins/bootstrap-toastr/toastr.min.css"/>"/>
<!-- END PAGE TOASTR STYLES -->
<!-- BEGIN DATEPICKERS STYLES -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/global/plugins/clockface/css/clockface.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>"/>
<!-- END DATEPICKERS STYLES -->
<!-- BEGIN PAGE MULTISELECT STYLES -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/global/plugins/jquery-multi-select/css/multi-select.css"/>"/>
<!-- END PAGE MULTISELECT STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="<c:url value="/resources/assets/global/css/components-md.css"/>" id="style_components" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/assets/global/css/plugins-md.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/assets/admin/layout4/css/layout.css"/>" rel="stylesheet" type="text/css"/>
<link id="style_color" href="<c:url value="/resources/assets/admin/layout4/css/themes/light.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/assets/admin/layout4/css/custom.css"/>" rel="stylesheet" type="text/css"/>

<style>
.block-loading{
position:fixed;
width:100%;
height:100%;
top:0;
left:0;
background:#fff url(../resources/extras/preloader.gif) no-repeat center center;
opacity:0.70;
z-index:99999;}
</style>

<!-- END THEME STYLES -->
</head>
<body class="page-md page-header-fixed page-sidebar-closed-hide-logo">

		<!-- BEGIN HEADER -->
		<div class="page-header md-shadow-z-1-i navbar navbar-fixed-top">
			<tiles:insertAttribute name="header" />
		</div>
		<!-- END HEADER -->
		<div class="clearfix"></div>
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN SIDEBAR -->
			<tiles:insertAttribute name="menu" />
			<!-- END SIDEBAR -->
	
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="page-content">
					<!-- BEGIN PAGE CONTENT-->
					<div class="row">
						<div id ="pageBody" class="col-md-12">
							<tiles:insertAttribute name="body" />
							
						</div>
					</div>
					<!-- END PAGE CONTENT-->
				</div>
			</div>
			<!-- END CONTENT -->
		</div>
		<!-- END CONTAINER -->
		<!-- BEGIN FOOTER -->
		<div class="page-footer">
			<tiles:insertAttribute name="footer" />
		</div> 
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="<c:url value="/resources/assets/global/plugins/respond.min.js"/>"></script>
<script src="<c:url value="/resources/assets/global/plugins/excanvas.min.js"/>" ></script> 
<![endif]-->



<script src="<c:url value="/resources/assets/global/plugins/jquery.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/assets/global/plugins/jquery-migrate.min.js"/>" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="<c:url value="/resources/assets/global/plugins/jquery-ui/jquery-ui.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/assets/global/plugins/bootstrap/js/bootstrap.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/assets/global/plugins/jquery.blockui.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/assets/global/plugins/jquery.cokie.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/assets/global/plugins/uniform/jquery.uniform.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"/>" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE DATATABLES PLUGINS -->
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/jquery-validation/js/jquery.validate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/jquery-validation/js/additional-methods.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/select2/select2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"/>"></script>
<!-- END PAGE DATATABLES PLUGINS -->
<!-- BEGIN DATEPICKERS PLUGINS -->
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/clockface/js/clockface.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/bootstrap-daterangepicker/moment.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"/>"></script>
<!-- END DATEPICKERS PLUGINS -->
<!-- BEGIN PAGE TOASTR SCRIPTS -->
<script src="<c:url value="/resources/assets/global/plugins/bootstrap-toastr/toastr.min.js"/>"></script>
<!-- END PAGE TOASTR SCRIPTS -->
<!-- BEGIN PAGE MULTISELECT PLUGINS -->
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/select2/select2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js"/>"></script>
<!-- END PAGE MULTISELECT PLUGINS -->

<script src="<c:url value="/resources/assets/admin/pages/scripts/ui-toastr.js"/>"></script>
<script src="<c:url value="/resources/assets/global/scripts/metronic.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/assets/admin/layout4/scripts/layout.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/assets/admin/layout4/scripts/demo.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/assets/admin/pages/scripts/components-pickers.js"/>"></script>
<script src="<c:url value="/resources/assets/admin/pages/scripts/components-dropdowns.js"/>"></script>

<!--  <script src="<c:url value="/resources/extras/tooltip.css"/>" type="text/javascript"></script>-->

<script src="<c:url value="/resources/extras/jquery.quicksearch.js"/>"></script>
<script src="<c:url value="/resources/extras/multiSelect.js"/>"></script>

<script src="<c:url value="/resources/extras/datatable.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/extras/validators.js"/>" type="text/javascript"></script>

<script src="<c:url value="/resources/js/util.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/extras/validator-es.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/sistema.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/rol.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/perfil.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/menu.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/usuario.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/listaEstudios.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/listaTrabajo.js"/>" type="text/javascript"></script>

<script>
      jQuery(document).ready(function() {    
	      Metronic.init(); // init metronic core components
		  Layout.init(); // init current layout
		  Demo.init(); // init demo features
		  TableAplicacion.init();
		  FormValidation.init();
		  ComponentsPickers.init();
		  ComponentsDropdowns.init();
		  multiSelect.init();
      });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>