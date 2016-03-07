$(document).ready(function() {
	var cod_perfil = null;
	var row=null;
	
	//Buscar Perfils
	$("#buscarPerfil").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaPerfil').serialize();
		
		$.get("listPerfils",formData, function(data, status) {
			
			//alert("Data: " + data + "\nStatus: " + status);
		    
			oTable.fnClearTable();
            
            $.each(data, function(key, value) {
            	  	
                oTable.fnAddData([value.cod_perfil,
                                  value.sistema.cod_sistema,
                                  value.sistema.nombre,
                                  value.nombre,value.descripcion,value.fecha_creacion,
                                  value.estado==0 ? "Activo" : "Inactivo",
                                  '<a>' +
                                  '<span id="editPerfil" class="icon-pencil"></span> ' +
                                  '</a>',
                                  '<a>' + 
                                  '<span  class="icon-close"></span></a>',
                                  '<a>' + 
                                  '<span  class="icon-layers"></span></a>'
                                  ]);
            });
            oTable.fnDraw();
		});
	});
	
	$("#btnClosePPerfil,#btnCerrarPPerfil").click(function(e) {
		$('.form-group').removeClass('has-error');
		var validator = $( "#form_perfil" ).validate();
		validator.resetForm();
		$('#form_perfil').trigger("reset");
		$('[name=estado]').select2("val", "");
		$('#modalAgregarPerfil').modal('hide');
	});

	$("#btnAgregarPerfil").click(function(e) {
		cargarSistema();
	});
	
	
	$("#btnClosePAsocMenu,#btnCerrarPAsocMenu,#btnAgregarPAsocMenu").click(function(e) {
		$('#modalAsociarMenu').modal('hide');
	});
	
	//Agregar/Modificar Perfils
	$("#btnAgregarPPerfil").click(function(e) {
		e.preventDefault();
		var form = $('#form_perfil');
		var oTable = $('#tableNormal').dataTable();
		
		if(form.valid()== true){
			
			var formData = $('#form_perfil').serialize();
			
			$('[name=cod_perfil]', '#form_perfil').val() == "" ? 
				$.post("agregarPerfil",formData, function(data, status){
					oTable.fnAddData( [
	                                   data.objeto.cod_perfil,
	                                   data.objeto.sistema.cod_sistema,
	                                   data.objeto.sistema.nombre,
	                                   data.objeto.nombre,
	                                   data.objeto.descripcion,
	                                   data.objeto.fecha_creacion,
	                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
	                        		   '<a>' +
	                                   '<span id="editPerfil" class="icon-pencil"></span> ' +
	                                   '</a>',
	                                   '<a>' + 
	                                   '<span  class="icon-close"></span></a>',
	                                   '<a>' + 
	                                   '<span  class="icon-layers"></span></a>']
	                                 );
					toastr.success(data.mensaje, 'Mensaje');
				})
				:$.post("actualizarPerfil",formData, function(data, status){
					oTable.fnUpdate( [
	                                   data.objeto.cod_perfil,
	                                   data.objeto.sistema.cod_sistema,
	                                   data.objeto.sistema.nombre,
	                                   data.objeto.nombre,
	                                   data.objeto.descripcion,
	                                   data.objeto.fecha_creacion,
	                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
	                        		   '<a>' +
	                                   '<span id="editPerfil" class="icon-pencil"></span> ' +
	                                   '</a>',
	                                   '<a>' + 
	                                   '<span  class="icon-close"></span></a>',
	                                   '<a>' + 
	                                   '<span  class="icon-layers"></span></a>'],row
	                                 );
					toastr.success(data.mensaje, 'Mensaje');
			    });	
			$('#modalAgregarPerfil').modal('hide');
		}

	});
	
	$('#tableNormal').on( 'click', '#editPerfil', function (e) {
        e.preventDefault();
        var oTable = $('#tableNormal').dataTable();
        cod_perfil = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        var cod_sistema = oTable.fnGetData(row)[1];
        $.get("getPerfil",{ cod_perfil : cod_perfil }, function(data, status) {
           
			$.each(data, function(key, value){
        		if(value != null){    			
        			key =='estado' ? 
        						$('[name=estado]','#form_perfil').select2("val", value) : 
        							$('[name='+key+']', '#form_perfil').val(value);
        		}
        	});
			cargarSistema(cod_sistema);
        	$('#modalAgregarPerfil').modal('show');
		});
	});
	
	$('#tableNormal').on( 'click', 'span.icon-close', function (e) {
        e.preventDefault();
     
        cod_perfil = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $('#deleteModalPerfil').modal('show');
	});
	
	//Eliminar Perfil
	$("#btnEliminarPerfil").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		oTable.fnDeleteRow(row);
		
		var formData = $('#form_perfil').serialize();
		 $.post("eliminarPerfil",{ cod_perfil : cod_perfil }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalPerfil').modal('hide');
	});
	
	//Multiselect Menus
	$('#tableNormal').on( 'click', 'span.icon-layers', function (e) {
		e.preventDefault(); 
		    var $select = $('#selectMenus');
	        cod_perfil = $(this).parents('tr:first').find('td:first').text();
			
	        $.get("getMenuPerfil", { cod_perfil : cod_perfil },function(data, status) {
	        	if(data.objeto!=null)
	        	{	
	        		 //limpiar seleccionados
	    	        $("#selectMenus option:selected").removeAttr("selected");
	    	        //limpiar multiselect y refresh control
	    	        $('#selectMenus').empty();
	    	        $('#selectMenus').multiSelect('refresh');
	    	        
		        		$.each(data.objeto, function(key, value) {
		        			if (value.orden == 0) {
			        		    //agregar grupo
		        				var group = $('<optgroup value = "' + value.cod_menu
		    							+ '" label="' + value.nombre + '" />');
		    					group.appendTo($select);
		        			}
		        			else
		        			{
		        				//agregar hijo al grupo
		        				$('#selectMenus').multiSelect('addOption', { value: value.cod_menu, text: value.nombre, index: 0, nested : value.menuPadreNom });	
		        			}
		            	});
		        		
		    			$.each(data.objeto, function(key, value) {
		    				value.menuAsoc != null ? 
		    						$("#selectMenus option[value='" + value.cod_menu + "']").attr("selected", 1)
		    						: "";
		    			});
	        		
	        	    $('#selectMenus').multiSelect('refresh');
	    	        $('#modalAsociarMenu').modal('show');
	        	}else
	        	{
	        		toastr.info(data.mensaje, 'Mensaje');
	        	}
	        	
			});
	});
	
	$("#btnAgregarPAsocMenu").click(function(e) {
		var menus = $('select#selectMenus').val() == null ? 0 : $('select#selectMenus').val() ;
		$.ajax({
			url : 'registrarAsocMenu',
			data : "menusInt=" + menus + "&cod_perfil=" + cod_perfil,
			type : "POST",
			success : function(responce) {
				toastr.success(responce.mensaje, 'Mensaje');
			}
		});	
	});
	
});