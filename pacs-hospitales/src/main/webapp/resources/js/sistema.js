$(document).ready(function() {
	var cod_sistema = null;
	var row=null;
	
	//Buscar Sistemas
	$("#buscarSistema").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaSistema').serialize();
		
		$('#pageBody').prepend(block);
		$.get("listarSistemas",formData, function(data, status) {
			
			//alert("Data: " + data.objeto + "\nStatus: " + status);

			block.remove();
			
			oTable.fnClearTable();
            
            $.each(data.objeto, function(key, value) {
            	//alert("Value: " + value );
            	
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
		$("#form_sistema [name='nombre']").parent().removeClass('has-error');
	});
	
	$('#tableNormal').on( 'click', '#editSistema', function (e) {
        e.preventDefault();
     
        cod_sistema = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $.get("buscaSistema",{ cod_sistema : cod_sistema }, function(data, status) {
           
			$.each(data.objeto, function(key, value){
        		if(value != null){    			
        			key =='estado' ? 
        						$('[name=estado]','#form_sistema').select2("val", value) : 
        							$('[name='+key+']', '#form_sistema').val(value);
        		}
        	});
        	$('#modalAgregarSistema').modal('show');
		});
	});
	
	$("#form_sistema").submit(function(e) {
		e.preventDefault();
		
		var form = $('#form_sistema');
		var oTable = $('#tableNormal').dataTable();
		
		var formData = form.serialize();
		

		$.get("isSistemaNombreUnique",formData, function(data, status) {
			if(data.objeto==true)
			{
				toastr.info(data.mensaje, 'Mensaje');
				$("#form_sistema [name='nombre']").parent().addClass('has-error');
			}
			else
			{
				if(form.valid() == true){
					$('[name=cod_sistema]', '#form_sistema').val() == "" ? 
						$.post("saveSistema",formData, function(data, status){
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
						:$.post("updateSistema",formData, function(data, status){
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
			}
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
		 $.post("deleteSistema",{ cod_sistema : cod_sistema }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalSistema').modal('hide');
	});

});