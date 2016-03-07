$(document).ready(function() {
	
	var cod_menu = null;
	var row=null;
	
	var opcion="menu";
	
	inicializarPopUp(1);
	 
	//Buscar Menus
	$("#buscarMenu").click(function(e) {	
		e.preventDefault();
		
		var formData = $('#busquedaMenu').serialize();
		var oTable = $('#tableNormal').dataTable();
		
		$('#pageBody').prepend(block);

		$.get("listarMenus",formData, function(data, status) {
			
			block.remove();
			
			oTable.fnClearTable();
            
            $.each(data.objeto, function(key, value) {
            	  	
                oTable.fnAddData([
							      value.cod_menu,
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
           
		});
		
		 oTable.fnDraw();
	});
	
	//Bonotes Modal y Agregar Principal
	$("#btnClosePMenu,#btnCerrarPMenu,#btnAgregarMenu").click(function(e) {
		$('.form-group').removeClass('has-error');
		
		validatorPadre = $("#form_menu_padre").validate();	
		validatorHijo = $("#form_menu_hijo").validate();
		
		validatorPadre.resetForm();
		validatorHijo.resetForm();
		
		$('#form_menu_padre').trigger("reset");
		$('#form_menu_hijo').trigger("reset");
		
		$('[name=estado]').select2("val", "");
		
		$('#modalAgregarMenu').modal('hide');
		
		$("#form_menu_padre [name='nombre']").parent().removeClass('has-error');
		$("#form_menu_hijo [name='nombre']").parent().removeClass('has-error');
		
		inicializarPopUp(1);

	});
	//Agregar Principal
	$("#btnAgregarMenu").click(function(e) {
		cargarSistema();
	});
	
	//Combo Menu en opcion SubMenu
	$("#btnAgregarMenu").click(function(e) {
		var menuPadres="";

		cargarMenu();
	});
	
	//Agregar/Modificar Menus
	$("#btnAgregarPMenu").click(function(e) {
		e.preventDefault();
		var form = "";
		var oTable = $('#tableNormal').dataTable();
		
		if(opcion=="menu")
		{
			form = $('#form_menu_padre');
			if(form.valid()== true){
				var formData = $('#form_menu_padre').serialize();
				
				$('[name=cod_menu]', '#form_menu_padre').val() == "" ? 
					$.post("saveMenuPadre",formData, function(data, status){
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
					:$.post("updateMenuPadre",formData, function(data, status){
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
			validator = $("#form_menu_padre").validate();
			$('#form_menu_padre').trigger("reset");
		}
		else
		{
			form = $('#form_menu_hijo');
			if(form.valid()== true){
				var formData = $('#form_menu_hijo').serialize();
				
				$('[name=cod_menu]', '#form_menu_hijo').val() == "" ? 
					$.post("saveMenuHijo",formData, function(data, status){
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
					:$.post("updateMenuHijo",formData, function(data, status){
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
			validator = $("#form_menu_padre").validate();
			$('#form_menu_padre').trigger("reset");
		}

	});
	
	//Editar
	$('#tableNormal').on( 'click', '#editMenu', function (e) {
        e.preventDefault();
        
        $('#pageBody').prepend(block);
        
        var oTable = $('#tableNormal').dataTable();
        
        row = $(this).parents('tr')[0];
        
        cod_menu = $(this).parents('tr:first').find('td:first').text();
        
        var cod_sistema = oTable.fnGetData(row)[1];
    	
        cargarMenu(cod_menu);	
	    
        $.get("buscaMenu",{ cod_menu : cod_menu }, function(data, status) {
        	
        	block.remove();
        	
			$.each(data.objeto, function(key, value){
        		if(value != null){    		
        			if(data.objeto.orden == 0){
        				key =='estado' ? 
        						$('[name=estado]','#form_menu_padre').select2("val", value) : 
        							$('[name='+key+']', '#form_menu_padre').val(value);	
        				
        				inicializarPopUp(1);
        			}else
        			{
        				key =='estado' ? 
        						$('[name=estado]','#form_menu_hijo').select2("val", value) : 
        							$('[name='+key+']', '#form_menu_hijo').val(value);	
        				if(key =='grupo')
        				{
        					$('[name=grupo]','#form_menu_hijo').select2("val", value);
        				}
        				inicializarPopUp(2);
        			}
        		}
        	});
			
			cargarSistema(cod_sistema);
		  
        	$('#modalAgregarMenu').modal('show');
		});
	});
	
	//Eliminar Perfil
	$('#tableNormal').on( 'click', 'span.icon-close', function (e) {
        e.preventDefault();
     
        cod_menu = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        
        $('#deleteModalMenu').modal('show');
	});
	
	//Eliminar Perfil en Modal
	$("#btnEliminarMenu").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		
		oTable.fnDeleteRow(row);
		
		$.post("deleteMenu",{ cod_menu : cod_menu }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalMenu').modal('hide');
	});
	
	//Cambiar Diseño del modal para Padres e hijos
	$("#btnMenu").click(function(e) {
		
		opcion="menu";
		
		$("#form_menu_padre").show();
		$("#form_menu_hijo").hide();
		
		$("#btnMenu").removeClass();
		$("#btnSubMenu").removeClass();
		
		$('#btnSubMenu').addClass('btn blue');
		$('#btnMenu').addClass('btn red');		
	});
	$("#btnSubMenu").click(function(e) {
		
		opcion="submenu";
		
		$("#form_menu_hijo").show();
		$("#form_menu_padre").hide();
		
		$("#btnMenu").removeClass();
		$("#btnSubMenu").removeClass();
		
		$('#btnSubMenu').addClass('btn red');
		$('#btnMenu').addClass('btn blue');
	});

});


var inicializarPopUp = function (tipo) {
	// 1 --> Menu // 2 -->SubMenu
	if(tipo==1)
	{
		$("#form_menu_padre").show();
		$("#form_menu_hijo").hide();
		
		$("#btnMenu").removeClass();
		$("#btnSubMenu").removeClass();
		
		$('#btnMenu').addClass('btn red');
		$('#btnSubMenu').addClass('btn blue');
				
	}
	else
	{
	
		$("#form_menu_padre").hide();
		$("#form_menu_hijo").show();
		
		$("#btnMenu").removeClass();
		$("#btnSubMenu").removeClass();
		
		$('#btnMenu').addClass('btn blue');
		$('#btnSubMenu').addClass('btn red');
	}
}
