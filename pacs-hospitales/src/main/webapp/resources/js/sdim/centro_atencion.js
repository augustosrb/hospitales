$(document).ready(function() {
	
	$("#form_CentroAtencionPadre").show();
	$("#form_CentroAtencionHijo").hide();
	
	$("#optCentroAtencion").prop("checked", true);
	
	var cod_centro_atencion = null;
	var row=null;
	
	//Buscar CentroAtencions
	$("#buscarCentroAtencion").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaCentroAtencion').serialize();
		
		$.get("listCentroAtencions",formData, function(data, status) {
			
			//alert("Data: " + data + "\nStatus: " + status);
		    
			oTable.fnClearTable();
            
            $.each(data, function(key, value) {
            	  	
                oTable.fnAddData([value.cod_centro_atencion,
                                  value.nombre,value.descripcion,value.fecha_creacion,
                                  value.estado==0 ? "Activo" : "Inactivo",
                                  '<a>' +
                                  '<span id="editCentroAtencion"  class="icon-pencil"></span> ' +
                                  '</a>',
                                  '<a>' + 
                                  '<span  class="icon-close"></span></a>'
                                  ]);
            });
            oTable.fnDraw();
		});
	});
	
	$("#btnClosePCentroAtencion,#btnCerrarPCentroAtencion,#btnAgregarCentroAtencion").click(function(e) {
		$('.form-group').removeClass('has-error');
		var optRadio = $("input[name='cabecera']:checked").val();
		var validator;
		
		$("#form_CentroAtencionPadre").show();
		$("#optCentroAtencion").prop("checked", true);
		
		if(optRadio=="centroAtencion")
		{
			validator = $("#form_CentroAtencionPadre").validate();
			
		}
		else
		{
			validator = $("#form_CentroAtencionHijo").validate();
				
		}
		$('#form_CentroAtencionHijo').trigger("reset");
		$('#form_CentroAtencionPadre').trigger("reset");
		validator.resetForm();
		$('[name=estado]').select2("val", "");
		$('#modalAgregarCentroAtencion').modal('hide');
	});
	
	$("#btnAgregarCentroAtencion").click(function(e) {
		
		$("#optCentroAtencion").prop("checked", true)
		
		cargarPlantilla();
	});
	
	//Agregar/Modificar CentroAtencions
	$("#btnAgregarPCentroAtencion").click(function(e) {
		e.preventDefault();
		var form = "";
		var oTable = $('#tableNormal').dataTable();
		
		var optRadio = $("input[name='cabecera']:checked").val();
		
		if(optRadio=="centroAtencion")
		{
			form = $('#form_CentroAtencionPadre');
			if(form.valid()== true){
				var formData = $('#form_CentroAtencionPadre').serialize();
				
				$('[name=cod_centro_atencion]', '#form_CentroAtencionPadre').val() == "" ? 
					$.post("agregarCentroAtencionPadre",formData, function(data, status){
						oTable.fnAddData( [
		                                   data.objeto.cod_centro_atencion,
		                                   data.objeto.nombre,
		                                   data.objeto.descripcion,
		                                   data.objeto.fecha_creacion,
		                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
		                        		   '<a>' +
		                                   '<span  id="editCentroAtencion" class="icon-pencil"></span> ' +
		                                   '</a>',
		                                   '<a>' + 
		                                   '<span  class="icon-close"></span></a>']
		                                 );
						toastr.success(data.mensaje, 'Mensaje');
					})
					:$.post("actualizarCentroAtencionPadre",formData, function(data, status){
						oTable.fnUpdate( [
		                                   data.objeto.cod_centro_atencion,
		                                   data.objeto.nombre,
		                                   data.objeto.descripcion,
		                                   data.objeto.fecha_creacion,
		                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
		                        		   '<a>' +
		                                   '<span id="editCentroAtencion" class="icon-pencil"></span> ' +
		                                   '</a>',
		                                   '<a>' + 
		                                   '<span  class="icon-close"></span></a>'],row
		                                 );
						toastr.success(data.mensaje, 'Mensaje');
				    });	
				$('#modalAgregarCentroAtencion').modal('hide');
			}
			validator = $("#form_CentroAtencionPadre").validate();
			$('#form_CentroAtencionPadre').trigger("reset");
		}
		else
		{
			form = $('#form_CentroAtencionHijo');
			if(form.valid()== true){
				var formData = $('#form_CentroAtencionHijo').serialize();
				
				$('[name=cod_centro_atencion]', '#form_CentroAtencionHijo').val() == "" ? 
					$.post("agregarCentroAtencionHijo",formData, function(data, status){
						oTable.fnAddData( [
		                                   data.objeto.cod_centro_atencion,
		                                   data.objeto.nombre,
		                                   data.objeto.descripcion,
		                                   data.objeto.fecha_creacion,
		                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
		                        		   '<a>' +
		                                   '<span id="editCentroAtencion" class="icon-pencil"></span> ' +
		                                   '</a>',
		                                   '<a>' + 
		                                   '<span  class="icon-close"></span></a>']
		                                 );
						toastr.success(data.mensaje, 'Mensaje');
					})
					:$.post("actualizarCentroAtencionHijo",formData, function(data, status){
						oTable.fnUpdate( [
		                                   data.objeto.cod_centro_atencion,
		                                   data.objeto.nombre,
		                                   data.objeto.descripcion,
		                                   data.objeto.fecha_creacion,
		                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
		                        		   '<a>' +
		                                   '<span  id="editCentroAtencion" class="icon-pencil"></span> ' +
		                                   '</a>',
		                                   '<a>' + 
		                                   '<span  class="icon-close"></span></a>'],row
		                                 );
						toastr.success(data.mensaje, 'Mensaje');
				    });	
				$('#modalAgregarCentroAtencion').modal('hide');
			}
			validator = $("#form_CentroAtencionPadre").validate();
			$('#form_CentroAtencionPadre').trigger("reset");
		}

	});
	
	$('#tableNormal').on( 'click', '#editCentroAtencion', function (e) {
        e.preventDefault();
        var oTable = $('#tableNormal').dataTable();
        cod_centro_atencion = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        
        var cod_sistema = oTable.fnGetData(row)[1];
        
		var centroAtencionPadres="";

		$("#padresList option").remove();
		$('#padresList').attr('placeholder', 'CentroAtencion').select2();
		
		centroAtencionPadres += '<option value=""></option>';
		$.get("listCentroAtencionsPadres", function(data, status) {
			 $.each(data, function(key, value) { 
        	 	 centroAtencionPadres += "<option value="+value.cod_centro_atencion + ">"+value.nombre+"</option>";
        	 });
			 
        	 $("#padresList").append(centroAtencionPadres);
        	 $("#padresList").select2().select2("val", cod_centro_atencion);
		 });
        
        
        $.get("getCentroAtencion",{ cod_centro_atencion : cod_centro_atencion }, function(data, status) {
        	
			$.each(data, function(key, value){
        		if(value != null){    		
        			if(data.orden == 0){
        				key =='estado' ? 
        						$('[name=estado]','#form_CentroAtencionPadre').select2("val", value) : 
        							$('[name='+key+']', '#form_CentroAtencionPadre').val(value);	
        				$("#optCentroAtencion").prop("checked", true);
        			}else
        			{
        				key =='estado' ? 
        						$('[name=estado]','#form_CentroAtencionHijo').select2("val", value) : 
        							$('[name='+key+']', '#form_CentroAtencionHijo').val(value);	
        				if(key =='grupo')
        				{
        					$('[name=grupo]','#form_CentroAtencionHijo').select2("val", value);
        				}
        				  $("#optSubCentroAtencion").prop("checked", true);
        			}
        		}
        	});
			cargarPlantilla(cod_sistema);
			cargarSistemaFormHijo(cod_sistema);
			cargarSistema(cod_sistema);
		  
        	$('#modalAgregarCentroAtencion').modal('show');
		});
	});
	
	$('#tableNormal').on( 'click', 'span.icon-close', function (e) {
        e.preventDefault();
     
        cod_centro_atencion = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $('#deleteModalCentroAtencion').modal('show');
	});
	
	//Eliminar CentroAtencion
	$("#btnEliminarCentroAtencion").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		oTable.fnDeleteRow(row);
		
		var formData = $('#form_CentroAtencion').serialize();
		 $.post("eliminarCentroAtencion",{ cod_centro_atencion : cod_centro_atencion }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalCentroAtencion').modal('hide');
	});
	
	//Combo CentroAtencion en opcion SubCentroAtencion
	$("#btnAgregarCentroAtencion").click(function(e) {
		var centroAtencionPadres="";

		$("#padresList option").remove();
		$('#padresList').attr('placeholder', 'CentroAtencion').select2();
		
		centroAtencionPadres += '<option value=""></option>';
		$.get("listCentroAtencionsPadres", function(data, status) {
			 $.each(data, function(key, value) { 
        	 	 centroAtencionPadres += "<option value="+value.cod_centro_atencion + ">"+value.nombre+"</option>";
        	 });
			 
        	 $("#padresList").append(centroAtencionPadres);
        	 $("#padresList").select2().select2("val", cod_centro_atencion);
		 });
	});
	
	//Otros
	//Cambiar Diseño del modal para Padres e hijos
	$("#optCentroAtencion").click(function(e) {
		$("#form_CentroAtencionPadre").show();
		$("#form_CentroAtencionHijo").hide();
	});
	$("#optSubCentroAtencion").click(function(e) {
		$("#form_CentroAtencionHijo").show();
		$("#form_CentroAtencionPadre").hide();
	});
	
});