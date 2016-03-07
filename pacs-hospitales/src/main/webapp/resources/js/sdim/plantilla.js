$(document).ready(function() {
	var cod_plantilla = null;
	var row=null;
	
	//Buscar Plantillas
	$("#buscarPlantilla").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaPlantilla').serialize();
		
		$.get("listPlantillas",formData, function(data, status) {
			
			//alert("Data: " + data + "\nStatus: " + status);
		    
			oTable.fnClearTable();
            
            $.each(data, function(key, value) {
            	  	
                oTable.fnAddData([value.cod_plantilla,value.nombre,value.descripcion,value.fecha_creacion,
                                  value.estado==0 ? "Activo" : "Inactivo",
                                  '<a>' +
                                  '<span id="editPlantilla" class="icon-pencil"></span> ' +
                                  '</a>',
                                  '<a>' + 
                                  '<span  class="icon-close"></span></a>'
                                  ]);
            });
            oTable.fnDraw();
		});
	});
	
	$("#btnClosePPlantilla,#btnCerrarPPlantilla,#btnAgregarPlantilla").click(function(e) {
		$('.form-group').removeClass('has-error');
		var validator = $( "#form_plantilla" ).validate();
		validator.resetForm();
		tinymce.get('plantilla').setContent(''); 
		$('#form_plantilla').trigger("reset");
		$('[name=estado]').select2("val", "");
		$('#modalAgregarPlantilla').modal('hide');
	});
	
	//Agregar/Modificar Plantillas
	$("#btnAgregarPPlantilla").click(function(e) {
		e.preventDefault();
		var form = $('#form_plantilla');
		var oTable = $('#tableNormal').dataTable();
		
		if(form.valid()== true){
			
			var formData = $('#form_plantilla').serializeArray();
			var template = tinyMCE.get('plantilla').getContent();
			
			formData.push({name: "template", value: template }); 
			
			
			
			$('[name=cod_plantilla]', '#form_plantilla').val() == "" ? 
				$.post("agregarPlantilla",formData, function(data, status){
					oTable.fnAddData( [
	                                   data.objeto.cod_plantilla,
	                                   data.objeto.nombre,
	                                   data.objeto.descripcion,
	                                   data.objeto.fecha_creacion,
	                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
	                        		   '<a>' +
	                                   '<span id="editPlantilla" class="icon-pencil"></span> ' +
	                                   '</a>',
	                                   '<a>' + 
	                                   '<span  class="icon-close"></span></a>']
	                                 );
					toastr.success(data.mensaje, 'Mensaje');
				})
				:$.post("actualizarPlantilla",formData, function(data, status){
					oTable.fnUpdate( [
	                                   data.objeto.cod_plantilla,
	                                   data.objeto.nombre,
	                                   data.objeto.descripcion,
	                                   data.objeto.fecha_creacion,
	                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
	                        		   '<a>' +
	                                   '<span id="editPlantilla" class="icon-pencil"></span> ' +
	                                   '</a>',
	                                   '<a>' + 
	                                   '<span  class="icon-close"></span></a>'],row
	                                 );
					toastr.success(data.mensaje, 'Mensaje');
			    });	
			$('#modalAgregarPlantilla').modal('hide');
		}

	});
	
	$('#tableNormal').on( 'click', '#editPlantilla', function (e) {
        e.preventDefault();
     
        cod_plantilla = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $.get("getPlantilla",{ cod_plantilla : cod_plantilla }, function(data, status) {
           
			$.each(data, function(key, value){
        		if(value != null){    			
        			key =='estado' ? 
        						$('[name=estado]','#form_plantilla').select2("val", value) : 
        							$('[name='+key+']', '#form_plantilla').val(value);
        			tinyMCE.get('plantilla').setContent(data.descripcion);
        		}
        	});
        	$('#modalAgregarPlantilla').modal('show');
		});
	});
	
	$('#tableNormal').on( 'click', 'span.icon-close', function (e) {
        e.preventDefault();
     
        cod_plantilla = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $('#deleteModalPlantilla').modal('show');
	});
	
	//Eliminar Plantilla
	$("#btnEliminarPlantilla").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		oTable.fnDeleteRow(row);
		
		var formData = $('#form_plantilla').serialize();
		 $.post("eliminarPlantilla",{ cod_plantilla : cod_plantilla }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalPlantilla').modal('hide');
	});
	
});