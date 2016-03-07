$(document).ready(function() {
	var cod_evento = null;
	var row=null;
	
	//Buscar Eventos
	$("#buscarEvento").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaEvento').serialize();
		
		$.get("listEventos",formData, function(data, status) {
			
			//alert("Data: " + data + "\nStatus: " + status);
		    
			oTable.fnClearTable();
            
            $.each(data, function(key, value) {
            	  	
                oTable.fnAddData([value.cod_evento,value.nombre,value.descripcion,value.fecha_creacion,
                                  value.estado==0 ? "Activo" : "Inactivo",
                                  '<a>' +
                                  '<span id="editEvento" class="icon-pencil"></span> ' +
                                  '</a>',
                                  '<a>' + 
                                  '<span  class="icon-close"></span></a>'
                                  ]);
            });
            oTable.fnDraw();
		});
	});
	
	$("#btnClosePEvento,#btnCerrarPEvento,#btnAgregarEvento").click(function(e) {
		$('.form-group').removeClass('has-error');
		var validator = $( "#form_evento" ).validate();
		validator.resetForm();
		$('#form_evento').trigger("reset");
		$('[name=estado]').select2("val", "");
		$('#modalAgregarEvento').modal('hide');
	});
	
	//Agregar/Modificar Eventos
	$("#btnAgregarPEvento").click(function(e) {
		e.preventDefault();
		var form = $('#form_evento');
		var oTable = $('#tableNormal').dataTable();
		
		if(form.valid()== true){
			
			var formData = $('#form_evento').serialize();
			
			$('[name=cod_evento]', '#form_evento').val() == "" ? 
				$.post("agregarEvento",formData, function(data, status){
					oTable.fnAddData( [
	                                   data.objeto.cod_evento,
	                                   data.objeto.nombre,
	                                   data.objeto.descripcion,
	                                   data.objeto.fecha_creacion,
	                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
	                        		   '<a>' +
	                                   '<span id="editEvento" class="icon-pencil"></span> ' +
	                                   '</a>',
	                                   '<a>' + 
	                                   '<span  class="icon-close"></span></a>']
	                                 );
					toastr.success(data.mensaje, 'Mensaje');
				})
				:$.post("actualizarEvento",formData, function(data, status){
					oTable.fnUpdate( [
	                                   data.objeto.cod_evento,
	                                   data.objeto.nombre,
	                                   data.objeto.descripcion,
	                                   data.objeto.fecha_creacion,
	                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
	                        		   '<a>' +
	                                   '<span id="editEvento" class="icon-pencil"></span> ' +
	                                   '</a>',
	                                   '<a>' + 
	                                   '<span  class="icon-close"></span></a>'],row
	                                 );
					toastr.success(data.mensaje, 'Mensaje');
			    });	
			$('#modalAgregarEvento').modal('hide');
		}

	});
	
	$('#tableNormal').on( 'click', '#editEvento', function (e) {
        e.preventDefault();
     
        cod_evento = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $.get("getEvento",{ cod_evento : cod_evento }, function(data, status) {
			$.each(data, function(key, value){
        		if(value != null){    			
        			key =='estado' ? 
        						$('[name=estado]','#form_evento').select2("val", value) : 
        							$('[name='+key+']', '#form_evento').val(value);
        		}
        	});
        	$('#modalAgregarEvento').modal('show');
		});
	});
	
	$('#tableNormal').on( 'click', 'span.icon-close', function (e) {
        e.preventDefault();
     
        cod_evento = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $('#deleteModalEvento').modal('show');
	});
	
	//Eliminar Evento
	$("#btnEliminarEvento").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		oTable.fnDeleteRow(row);
		
		var formData = $('#form_evento').serialize();
		 $.post("eliminarEvento",{ cod_evento : cod_evento }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalEvento').modal('hide');
	});
	
});