$(document).ready(function() {
	
	$("#form_menuPadre").show();
	$("#form_menuHijo").hide();
	
	$("#optMenu").prop("checked", true);
	
	var cod_menu = null;
	var row=null;
	
	//Buscar Menus
	$("#buscarMenu").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaMenu').serialize();
		
		$.get("listMenus",formData, function(data, status) {
			
			//alert("Data: " + data + "\nStatus: " + status);
		    
			oTable.fnClearTable();
            
            $.each(data, function(key, value) {
            	  	
                oTable.fnAddData([value.cod_menu,
                                  value.sistema.cod_sistema,
                                  value.sistema.nombre,
                                  value.nombre,value.descripcion,value.fecha_creacion,
                                  value.estado==0 ? "Activo" : "Inactivo",
                                  '<a>' +
                                  '<span id="editMenu"  class="icon-pencil"></span> ' +
                                  '</a>',
                                  '<a>' + 
                                  '<span  class="icon-close"></span></a>'
                                  ]);
            });
            oTable.fnDraw();
		});
	});
	
	$("#btnClosePMenu,#btnCerrarPMenu,#btnAgregarMenu").click(function(e) {
		$('.form-group').removeClass('has-error');
		var optRadio = $("input[name='cabecera']:checked").val();
		var validator;
		
		$("#form_menuPadre").show();
		$("#optMenu").prop("checked", true);
		
		if(optRadio=="menu")
		{
			validator = $("#form_menuPadre").validate();
			
		}
		else
		{
			validator = $("#form_menuHijo").validate();
				
		}
		$('#form_menuHijo').trigger("reset");
		$('#form_menuPadre').trigger("reset");
		validator.resetForm();
		$('[name=estado]').select2("val", "");
		$('#modalAgregarMenu').modal('hide');
	});
	
	$("#btnAgregarMenu").click(function(e) {
		
		$("#optMenu").prop("checked", true)
		
		cargarSistema();
		cargarSistemaFormHijo();
	});
	
	//Agregar/Modificar Menus
	$("#btnAgregarPMenu").click(function(e) {
		e.preventDefault();
		var form = "";
		var oTable = $('#tableNormal').dataTable();
		
		var optRadio = $("input[name='cabecera']:checked").val();
		
		if(optRadio=="menu")
		{
			form = $('#form_menuPadre');
			if(form.valid()== true){
				var formData = $('#form_menuPadre').serialize();
				
				$('[name=cod_menu]', '#form_menuPadre').val() == "" ? 
					$.post("agregarMenuPadre",formData, function(data, status){
						oTable.fnAddData( [
		                                   data.objeto.cod_menu,
		                                   data.objeto.sistema.cod_sistema,
		                                   data.objeto.sistema.nombre,
		                                   data.objeto.nombre,
		                                   data.objeto.descripcion,
		                                   data.objeto.fecha_creacion,
		                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
		                        		   '<a>' +
		                                   '<span  id="editMenu" class="icon-pencil"></span> ' +
		                                   '</a>',
		                                   '<a>' + 
		                                   '<span  class="icon-close"></span></a>']
		                                 );
						toastr.success(data.mensaje, 'Mensaje');
					})
					:$.post("actualizarMenuPadre",formData, function(data, status){
						oTable.fnUpdate( [
		                                   data.objeto.cod_menu,
		                                   data.objeto.sistema.cod_sistema,
		                                   data.objeto.sistema.nombre,
		                                   data.objeto.nombre,
		                                   data.objeto.descripcion,
		                                   data.objeto.fecha_creacion,
		                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
		                        		   '<a>' +
		                                   '<span id="editMenu" class="icon-pencil"></span> ' +
		                                   '</a>',
		                                   '<a>' + 
		                                   '<span  class="icon-close"></span></a>'],row
		                                 );
						toastr.success(data.mensaje, 'Mensaje');
				    });	
				$('#modalAgregarMenu').modal('hide');
			}
			validator = $("#form_menuPadre").validate();
			$('#form_menuPadre').trigger("reset");
		}
		else
		{
			form = $('#form_menuHijo');
			if(form.valid()== true){
				var formData = $('#form_menuHijo').serialize();
				
				$('[name=cod_menu]', '#form_menuHijo').val() == "" ? 
					$.post("agregarMenuHijo",formData, function(data, status){
						oTable.fnAddData( [
		                                   data.objeto.cod_menu,
		                                   data.objeto.sistema.cod_sistema,
		                                   data.objeto.sistema.nombre,
		                                   data.objeto.nombre,
		                                   data.objeto.descripcion,
		                                   data.objeto.fecha_creacion,
		                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
		                        		   '<a>' +
		                                   '<span id="editMenu" class="icon-pencil"></span> ' +
		                                   '</a>',
		                                   '<a>' + 
		                                   '<span  class="icon-close"></span></a>']
		                                 );
						toastr.success(data.mensaje, 'Mensaje');
					})
					:$.post("actualizarMenuHijo",formData, function(data, status){
						oTable.fnUpdate( [
		                                   data.objeto.cod_menu,
		                                   data.objeto.sistema.cod_sistema,
		                                   data.objeto.sistema.nombre,
		                                   data.objeto.nombre,
		                                   data.objeto.descripcion,
		                                   data.objeto.fecha_creacion,
		                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
		                        		   '<a>' +
		                                   '<span  id="editMenu" class="icon-pencil"></span> ' +
		                                   '</a>',
		                                   '<a>' + 
		                                   '<span  class="icon-close"></span></a>'],row
		                                 );
						toastr.success(data.mensaje, 'Mensaje');
				    });	
				$('#modalAgregarMenu').modal('hide');
			}
			validator = $("#form_menuPadre").validate();
			$('#form_menuPadre').trigger("reset");
		}

	});
	
	$('#tableNormal').on( 'click', '#editMenu', function (e) {
        e.preventDefault();
        var oTable = $('#tableNormal').dataTable();
        cod_menu = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        
        var cod_sistema = oTable.fnGetData(row)[1];
        
		var menuPadres="";

		$("#padresList option").remove();
		$('#padresList').attr('placeholder', 'Menu').select2();
		
		menuPadres += '<option value=""></option>';
		$.get("listMenusPadres", function(data, status) {
			 $.each(data, function(key, value) { 
        	 	 menuPadres += "<option value="+value.cod_menu + ">"+value.nombre+"</option>";
        	 });
			 
        	 $("#padresList").append(menuPadres);
        	 $("#padresList").select2().select2("val", cod_menu);
		 });
        
        
        $.get("getMenu",{ cod_menu : cod_menu }, function(data, status) {
        	
			$.each(data, function(key, value){
        		if(value != null){    		
        			if(data.orden == 0){
        				key =='estado' ? 
        						$('[name=estado]','#form_menuPadre').select2("val", value) : 
        							$('[name='+key+']', '#form_menuPadre').val(value);	
        				$("#optMenu").prop("checked", true);
        			}else
        			{
        				key =='estado' ? 
        						$('[name=estado]','#form_menuHijo').select2("val", value) : 
        							$('[name='+key+']', '#form_menuHijo').val(value);	
        				if(key =='grupo')
        				{
        					$('[name=grupo]','#form_menuHijo').select2("val", value);
        				}
        				  $("#optSubMenu").prop("checked", true);
        			}
        		}
        	});
			cargarSistemaFormHijo(cod_sistema);
			cargarSistema(cod_sistema);
		  
        	$('#modalAgregarMenu').modal('show');
		});
	});
	
	$('#tableNormal').on( 'click', 'span.icon-close', function (e) {
        e.preventDefault();
     
        cod_menu = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $('#deleteModalMenu').modal('show');
	});
	
	//Eliminar Menu
	$("#btnEliminarMenu").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		oTable.fnDeleteRow(row);
		
		var formData = $('#form_menu').serialize();
		 $.post("eliminarMenu",{ cod_menu : cod_menu }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalMenu').modal('hide');
	});
	
	//Combo Menu en opcion SubMenu
	$("#btnAgregarMenu").click(function(e) {
		var menuPadres="";

		$("#padresList option").remove();
		$('#padresList').attr('placeholder', 'Menu').select2();
		
		menuPadres += '<option value=""></option>';
		$.get("listMenusPadres", function(data, status) {
			 $.each(data, function(key, value) { 
        	 	 menuPadres += "<option value="+value.cod_menu + ">"+value.nombre+"</option>";
        	 });
			 
        	 $("#padresList").append(menuPadres);
        	 $("#padresList").select2().select2("val", cod_menu);
		 });
	});
	
	//Otros
	//Cambiar Diseño del modal para Padres e hijos
	$("#optMenu").click(function(e) {
		$("#form_menuPadre").show();
		$("#form_menuHijo").hide();
	});
	$("#optSubMenu").click(function(e) {
		$("#form_menuHijo").show();
		$("#form_menuPadre").hide();
	});
	
});