$(document).ready(function() {
	var cod_rol = null;
	var row=null;
	
	//Buscar Roles
	$("#buscarRol").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaRol').serialize();
		
		$('#pageBody').prepend(block);
		$.get("listarRoles",formData, function(data, status) {
			
			//alert("Data: " + data.objeto + "\nStatus: " + status);

			block.remove();
			
			oTable.fnClearTable();
            
            $.each(data.objeto, function(key, value) {
            	//alert("Value: " + value );
            	
                oTable.fnAddData([value.cod_rol,value.sistema.cod_sistema,value.nombre,value.descripcion,value.fecha_creacion,
                                  value.estado==0 ? "Activo" : "Inactivo",
                                  '<a>' +
                                  '<span id="editRol" class="icon-pencil"></span> ' +
                                  '</a>',
                                  '<a>' + 
                                  '<span  class="icon-close"></span></a>'
                                  ]);
            });
            
            oTable.fnDraw();
		});
	});
	
	//Bonotes Modal y Agregar Principal
	$("#btnClosePRol,#btnCerrarPRol,#btnAgregarRol").click(function(e) {
		$('.form-group').removeClass('has-error');
		var validator = $( "#form_rol" ).validate();
		validator.resetForm();
		$('#form_rol').trigger("reset");
		$('[name=estado]').select2("val", "");
		$('#modalAgregarRol').modal('hide');
		$("#form_rol [name='nombre']").parent().removeClass('has-error');
	});
	//Agregar Principal
	$("#btnAgregarRol").click(function(e) {
		cargarSistema();
	});
	
	//Editar Rol
	$('#tableNormal').on( 'click', '#editRol', function (e) {
        e.preventDefault();
     
        cod_rol = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        
        var oTable = $('#tableNormal').dataTable();
        var cod_sistema = oTable.fnGetData(row)[1];
        $.get("buscaRol",{ cod_rol : cod_rol }, function(data, status) {
           
			$.each(data.objeto, function(key, value){
        		if(value != null){    			
        			key =='estado' ? 
        						$('[name=estado]','#form_rol').select2("val", value) : 
        							$('[name='+key+']', '#form_rol').val(value);
        		}
        	});
			cargarSistema(cod_sistema);
        	$('#modalAgregarRol').modal('show');
		});
	});
	
	//Agregar Rol
	$("#form_rol").submit(function(e) {
		e.preventDefault();
		
		var form = $('#form_rol');
		var oTable = $('#tableNormal').dataTable();
		
		var formData = form.serialize();
		
		$.get("isRolNombreUnique",formData, function(data, status) {
			if(data.objeto==true)
			{
				toastr.info(data.mensaje, 'Mensaje');
				$("#form_rol [name='nombre']").parent().addClass('has-error');
			}
			else
			{
				if(form.valid() == true){
					$('[name=cod_rol]', '#form_rol').val() == "" ? 
						$.post("saveRol",formData, function(data, status){
							oTable.fnAddData( [
							                   data.objeto.sistema.cod_sistema,
			                                   data.objeto.cod_rol,
			                                   data.objeto.nombre,
			                                   data.objeto.descripcion,
			                                   data.objeto.fecha_creacion,
			                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
			                        		   '<a>' +
			                                   '<span id="editRol" class="icon-pencil"></span> ' +
			                                   '</a>',
			                                   '<a>' + 
			                                   '<span  class="icon-close"></span></a>']
			                                 );
							toastr.success(data.mensaje, 'Mensaje');
						})
						:$.post("updateRol",formData, function(data, status){
							oTable.fnUpdate( [
							                   data.objeto.sistema.cod_sistema,			                                   
			                                   data.objeto.cod_rol,
			                                   data.objeto.nombre,
			                                   data.objeto.descripcion,
			                                   data.objeto.fecha_creacion,
			                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
			                        		   '<a>' +
			                                   '<span id="editRol" class="icon-pencil"></span> ' +
			                                   '</a>',
			                                   '<a>' + 
			                                   '<span  class="icon-close"></span></a>'],row
			                                 );
							toastr.success(data.mensaje, 'Mensaje');
					    });	
					$('#modalAgregarRol').modal('hide');
				}	
			}
		});
	});
	
	//Eliminar Rol
	$('#tableNormal').on( 'click', 'span.icon-close', function (e) {
        e.preventDefault();
     
        cod_rol = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $('#deleteModalRol').modal('show');
	});
	
	//Eliminar Rol en Modal
	$("#btnEliminarRol").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		oTable.fnDeleteRow(row);
		
		var formData = $('#form_rol').serialize();
		 $.post("deleteRol",{ cod_rol : cod_rol }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalRol').modal('hide');
	});

});