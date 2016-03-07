$(document).ready(function() {
	var cod_rol = null;
	var row=null;
	
	//Buscar Rols
	$("#buscarRol").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaRol').serialize();
		
		$.get("listRols",formData, function(data, status) {
			
			//alert("Data: " + data + "\nStatus: " + status);
		    
			oTable.fnClearTable();
            
            $.each(data, function(key, value) {
            	  	
                oTable.fnAddData([value.cod_rol,
                                  value.sistema.cod_sistema,
                                  value.sistema.nombre,
                                  value.nombre,value.fecha_creacion,
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
	
	$("#btnClosePRol,#btnCerrarPRol").click(function(e) {
		$('.form-group').removeClass('has-error');
		var validator = $( "#form_rol" ).validate();
		validator.resetForm();
		$('#form_rol').trigger("reset");
		$('[name=estado]').select2("val", "");
		$('#modalAgregarRol').modal('hide');
	});
	
	$("#btnAgregarRol").click(function(e) {
		cargarSistema();
	});
	
	//Agregar/Modificar Rols
	$("#btnAgregarPRol").click(function(e) {
		e.preventDefault();
		var form = $('#form_rol');
		var oTable = $('#tableNormal').dataTable();
		
		if(form.valid()== true){
			
			var formData = $('#form_rol').serialize();
			
			$('[name=cod_rol]', '#form_rol').val() == "" ? 
				$.post("agregarRol",formData, function(data, status){
					oTable.fnAddData( [
	                                   data.objeto.cod_rol,
	                                   data.objeto.sistema.cod_sistema,
	                                   data.objeto.sistema.nombre,
	                                   data.objeto.nombre,
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
				:$.post("actualizarRol",formData, function(data, status){
					oTable.fnUpdate( [
	                                   data.objeto.cod_rol,
	                                   date.objeto.sistema.cod_sistema,
	                                   date.objeto.sistema.nombre,
	                                   data.objeto.nombre,
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

	});
	
	$('#tableNormal').on( 'click', '#editRol', function (e) {
        e.preventDefault();
        var oTable = $('#tableNormal').dataTable();
        cod_rol = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        
        var cod_sistema = oTable.fnGetData(row)[1]; 
        $.get("getRol",{ cod_rol : cod_rol }, function(data, status) {
           
			$.each(data, function(key, value){
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
	
	$('#tableNormal').on( 'click', 'span.icon-close', function (e) {
        e.preventDefault();
     
        cod_rol = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $('#deleteModalRol').modal('show');
	});
	
	//Eliminar Rol
	$("#btnEliminarRol").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		oTable.fnDeleteRow(row);
		
		var formData = $('#form_rol').serialize();
		 $.post("eliminarRol",{ cod_rol : cod_rol }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalRol').modal('hide');
	});
	
});