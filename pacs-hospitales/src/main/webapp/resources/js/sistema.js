$(document).ready(function() {
	var cod_sistema = null;
	var row=null;
	
	//Buscar Sistemas
	$("#buscarSistema").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaSistema').serialize();
		
		$.get("listSistemas",formData, function(data, status) {
			
			//alert("Data: " + data + "\nStatus: " + status);
		    
			oTable.fnClearTable();
            
            $.each(data, function(key, value) {
            	  	
                oTable.fnAddData([value.cod_sistema,value.nombre,value.descripcion,value.fecha_creacion,
                                  value.estado==0 ? "Activo" : "Inactivo",
                                  '<a>' +
                                  '<span id="editSistema" class="icon-pencil"></span> ' +
                                  '</a>',
                                  '<a>' + 
                                  '<span  class="icon-close"></span></a>'
                                  ]);
            });
            oTable.fnDraw();
		});
	});
	
	$("#btnClosePSistema,#btnCerrarPSistema,#btnAgregarSistema").click(function(e) {
		$('.form-group').removeClass('has-error');
		var validator = $( "#form_sistema" ).validate();
		validator.resetForm();
		$('#form_sistema').trigger("reset");
		$('[name=estado]').select2("val", "");
		$('#modalAgregarSistema').modal('hide');
	});
	
	//Agregar/Modificar Sistemas
	$("#btnAgregarPSistema").click(function(e) {
		e.preventDefault();
		var form = $('#form_sistema');
		var oTable = $('#tableNormal').dataTable();
		
		if(form.valid()== true){
			
			var formData = $('#form_sistema').serialize();
			
			$('[name=cod_sistema]', '#form_sistema').val() == "" ? 
				$.post("agregarSistema",formData, function(data, status){
					oTable.fnAddData( [
	                                   data.objeto.cod_sistema,
	                                   data.objeto.nombre,
	                                   data.objeto.descripcion,
	                                   data.objeto.fecha_creacion,
	                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
	                        		   '<a>' +
	                                   '<span id="editSistema" class="icon-pencil"></span> ' +
	                                   '</a>',
	                                   '<a>' + 
	                                   '<span  class="icon-close"></span></a>']
	                                 );
					toastr.success(data.mensaje, 'Mensaje');
				})
				:$.post("actualizarSistema",formData, function(data, status){
					oTable.fnUpdate( [
	                                   data.objeto.cod_sistema,
	                                   data.objeto.nombre,
	                                   data.objeto.descripcion,
	                                   data.objeto.fecha_creacion,
	                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
	                        		   '<a>' +
	                                   '<span id="editSistema" class="icon-pencil"></span> ' +
	                                   '</a>',
	                                   '<a>' + 
	                                   '<span  class="icon-close"></span></a>'],row
	                                 );
					toastr.success(data.mensaje, 'Mensaje');
			    });	
			$('#modalAgregarSistema').modal('hide');
		}

	});
	
	$('#tableNormal').on( 'click', '#editSistema', function (e) {
        e.preventDefault();
     
        cod_sistema = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $.get("getSistema",{ cod_sistema : cod_sistema }, function(data, status) {
           
			$.each(data, function(key, value){
        		if(value != null){    			
        			key =='estado' ? 
        						$('[name=estado]','#form_sistema').select2("val", value) : 
        							$('[name='+key+']', '#form_sistema').val(value);
        		}
        	});
        	$('#modalAgregarSistema').modal('show');
		});
	});
	
	$('#tableNormal').on( 'click', 'span.icon-close', function (e) {
        e.preventDefault();
     
        cod_sistema = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $('#deleteModalSistema').modal('show');
	});
	
	//Eliminar Sistema
	$("#btnEliminarSistema").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		oTable.fnDeleteRow(row);
		
		var formData = $('#form_sistema').serialize();
		 $.post("eliminarSistema",{ cod_sistema : cod_sistema }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalSistema').modal('hide');
	});
	
});