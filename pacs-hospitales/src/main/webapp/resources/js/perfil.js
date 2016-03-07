$(document).ready(function() {
	var cod_perfil = null;
	var row=null;
	
	//Buscar Perfiles
	$("#buscarPerfil").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaPerfil').serialize();
		
		$('#pageBody').prepend(block);
		$.get("listarPerfiles",formData, function(data, status) {
			
			//alert("Data: " + data.objeto + "\nStatus: " + status);

			block.remove();
			
			oTable.fnClearTable();
            
            $.each(data.objeto, function(key, value) {
            	//alert("Value: " + value );
            	
                oTable.fnAddData([value.cod_perfil,
                                  value.sistema.nombre,
                                  value.sistema.cod_sistema,value.nombre,value.descripcion,value.fecha_creacion,
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
	
	//Bonotes Modal y Agregar Principal
	$("#btnClosePPerfil,#btnCerrarPPerfil,#btnAgregarPerfil").click(function(e) {
		$('.form-group').removeClass('has-error');
		var validator = $( "#form_perfil" ).validate();
		validator.resetForm();
		$('#form_perfil').trigger("reset");
		$('[name=estado]').select2("val", "");
		$('#modalAgregarPerfil').modal('hide');
		$("#form_perfil [name='nombre']").parent().removeClass('has-error');
	});
	
	$("#btnClosePAsocMenu,#btnCerrarPAsocMenu,#btnAgregarPAsocMenu").click(function(e) {
		$('#modalAsociarMenu').modal('hide');
	});
	
	//Agregar Principal
	$("#btnAgregarPerfil").click(function(e) {
		cargarSistema();
	});
	
	
	
	//Editar Perfil
	$('#tableNormal').on( 'click', '#editPerfil', function (e) {
        e.preventDefault();
     
        cod_perfil = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        
        var oTable = $('#tableNormal').dataTable();
        var cod_sistema = oTable.fnGetData(row)[2];
        $.get("buscaPerfil",{ cod_perfil : cod_perfil }, function(data, status) {
           
			$.each(data.objeto, function(key, value){
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
	
	//Agregar Perfil
	$("#form_perfil").submit(function(e) {
		e.preventDefault();
		
		var form = $('#form_perfil');
		var oTable = $('#tableNormal').dataTable();
		
		var formData = form.serialize();
		
		$.get("isPerfilNombreUnique",formData, function(data, status) {
			if(data.objeto==true)
			{
				toastr.info(data.mensaje, 'Mensaje');
				$("#form_perfil [name='nombre']").parent().addClass('has-error');
			}
			else
			{
				if(form.valid() == true){
					$('[name=cod_perfil]', '#form_perfil').val() == "" ? 
						$.post("savePerfil",formData, function(data, status){
							oTable.fnAddData( [
											   data.objeto.cod_perfil,
											   data.objeto.sistema.nombre,
							                   data.objeto.sistema.cod_sistema,
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
						:$.post("updatePerfil",formData, function(data, status){
							oTable.fnUpdate( [
							                   data.objeto.cod_perfil,
											   data.objeto.sistema.nombre,
							                   data.objeto.sistema.cod_sistema,                                   
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
			}
		});
	});
	
	//Eliminar Perfil
	$('#tableNormal').on( 'click', 'span.icon-close', function (e) {
        e.preventDefault();
     
        cod_perfil = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $('#deleteModalPerfil').modal('show');
	});
	
	//Eliminar Perfil en Modal
	$("#btnEliminarPerfil").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		oTable.fnDeleteRow(row);
		
		var formData = $('#form_perfil').serialize();
		 $.post("deletePerfil",{ cod_perfil : cod_perfil }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalPerfil').modal('hide');
	});
	
	//Multiselect Menus
	$('#tableNormal').on( 'click', 'span.icon-layers', function (e) {
		e.preventDefault(); 
		
			var oTable = $('#tableNormal').dataTable();
		
			row = $(this).parents('tr')[0];
			
		    var $select = $('#selectMenus');
	        cod_perfil = $(this).parents('tr:first').find('td:first').text();
			
	        
	        var perfil = oTable.fnGetData(row)[3]; 
			var sistema = oTable.fnGetData(row)[1]; 
			
	        $("#lblPerfil").text(perfil);
			$("#lblSistema").text(sistema);
			
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