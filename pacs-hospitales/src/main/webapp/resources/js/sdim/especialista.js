$(document).ready(function() {
	var cod_especialista = null;
	var row=null;
	
	//para modalAgregarEspecialista
	$("#btnClosePEspecialista,#btnCerrarPEspecialista").click(function(e) {
		$('.form-group').removeClass('has-error');
		var validator = $( "#form_especialista" ).validate();
		validator.resetForm();
		$('#form_especialista').trigger("reset");
		$('[name=estado]').select2("val", "");
		$('#modalAgregarEspecialista').modal('hide');
		$("#spanLogin").empty();
		$('#divLogin').hide();
	});
	
	$("#btnAgregarEspecialista").click(function(e) {
		$.get("getUsuariosEsp",function(data, status) {
        	if(data.objeto!=null)
        	{	
        		 //limpiar seleccionados
    	        $("#selectUsuarios option:selected").removeAttr("selected");
    	        //limpiar multiselect y refresh control
    	        $('#selectUsuarios').empty();
    	        $('#selectUsuarios').multiSelect('refresh');
    	        
	        		$.each(data.objeto, function(key, value) {
		        		//agregar hijo al grupo
	        			$('#selectUsuarios').multiSelect('addOption', { value: value.cod_especialista + "/" +
	        																   value.nombre + "/" +
													        				   value.apepat + "/" +
																			   value.apemat + "/" +
																			   value.correo,
	        															text: value.nombre + " " + 
	        																  value.apepat + " " +
	        																  value.apemat  , index: 0 });
	            	});
	        		
	        		$.each(data.objeto, function(key, value) {
	    				value.especialista_registrado != 1 ? 
	    						$("#selectUsuarios option[value='" + value.cod_especialista + '/' +
										   value.nombre + '/' +
				        				   value.apepat + '/' +
										   value.apemat + '/' +
										   value.correo
	    								+ "']").attr("selected", 1)
	    						: "";
	    			});
        		
        	    $('#selectUsuarios').multiSelect('refresh');
    	        $('#modalAgregarEspecialista').modal('show');
        	}else
        	{
        		toastr.info(data.mensaje, 'Mensaje');
        	}
		});
	});
	
	$("#btnAgregarPEspecialista").click(function(e) {
		var especialista = $('select#selectUsuarios').val() == null ? null : $('select#selectUsuarios').val();
		$.ajax({
			url : 'agregarEspecialista',
			data : especialista == null ? "" : "especialista=" + especialista,
			type : "POST",
			success : function(responce) {
				toastr.success(responce.mensaje, 'Mensaje');
			}
		});	
		$('#modalEditarEspecialista').modal('hide');
	});
	
	$("#buscarEspecialista").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaEspecialista').serialize();
		
		$.get("listEspecialistas",formData, function(data, status) {
			
			//alert("Data: " + data + "\nStatus: " + status);
		    
			oTable.fnClearTable();
            
            $.each(data, function(key, value) {
            	
                oTable.fnAddData([value.cod_especialista,
                                  value.nombre,
                                  value.apepat,
                                  value.apemat,
                                  value.correo,
                                  value.fecha_creacion,
                                  value.estado==0 ? "Activo" : "Inactivo",
                                  '<a>' +
                                  '<span id="editEspecialista"  class="icon-pencil"></span> ' +
                                  '</a>',
                                  '<a>' + 
                                  '<span  class="icon-close"></span></a>',
                                  '<a>' + 
                                  '<span  class="icon-user"></span></a>',
                                  '<a>' + 
                                  '<span  class="icon-users"></span></a>'
                                  ]);
            });
            
            oTable.fnDraw();
           
		});
	});
	
	//Agregar/Modificar Especialistas
	$("#btnEditarPEspecialista").click(function(e) {
		e.preventDefault();
		var form = $('#form_especialista');
		var oTable = $('#tableNormal').dataTable();
		
		if(form.valid()== true){
			
			var formData = $('#form_especialista').serialize();
			
				$.post("actualizarEspecialista",formData, function(data, status){
					
					oTable.fnUpdate( [
	                                   data.objeto.cod_especialista,
	                                   data.objeto.nombre,
	                                   data.objeto.apepat,
	                                   data.objeto.apemat,
	                                   data.objeto.correo,
	                                   data.objeto.fecha_creacion,
	                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
	                        		   '<a>' +
	                                   '<span id="editEspecialista" class="icon-pencil"></span> ' +
	                                   '</a>',
	                                   '<a>' + 
	                                   '<span  class="icon-close"></span></a>'],row
	                                 );
					toastr.success(data.mensaje, 'Mensaje');
			    });	
	         
			$('#modalEditarEspecialista').modal('hide');
		}
	});
	
	$('#tableNormal').on( 'click', '#editEspecialista', function (e) {
        e.preventDefault();
        var oTable = $('#tableNormal').dataTable();
        
        cod_especialista = $(this).parents('tr:first').find('td:first').text();
        
        row = $(this).parents('tr')[0];
       
        var cod_sistema = oTable.fnGetData(row)[1]; 

        $.get("getEspecialista",{ cod_especialista : cod_especialista }, function(data, status) {
           
    		$.each(data, function(key, value){
        		if(value != null){    			
        			key =='estado' ? 
        						$('[name=estado]','#form_especialista').select2("val", value) : 
        							$('[name='+key+']', '#form_especialista').val(value);
        		}
        	});
    		$('#modalEditarEspecialista').modal('show');
    	});		    
	});
	
	$("#btnEliminarEspecialista").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		oTable.fnDeleteRow(row);
		
		var formData = $('#form_especialista').serialize();
		 $.post("eliminarEspecialista",{ cod_especialista : cod_especialista }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalEspecialista').modal('hide');
	});
	
	//Buscar Especialistas
	/*
	
	
	
	//cerrar para modalAsociarPerfil
	$("#btnClosePAsocPerfil,#btnCerrarPAsocPerfil,#btnAgregarPAsocPerfil").click(function(e) {
		$('#modalAsociarPerfil').modal('hide');
	});

	//cerrar para modalAsociarRol
	$("#btnClosePAsocRol,#btnCerrarPAsocRol,#btnAgregarPAsocRol").click(function(e) {
		$('#modalAsociarRol').modal('hide');
	});
	
	
	
	
	
	
	$('#tableNormal').on( 'click', 'span.icon-close', function (e) {
        e.preventDefault();
     
        cod_especialista = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $('#deleteModalEspecialista').modal('show');
	});
	
	//Eliminar Especialista
	$("#btnEliminarEspecialista").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		oTable.fnDeleteRow(row);
		
		var formData = $('#form_especialista').serialize();
		 $.post("eliminarEspecialista",{ cod_especialista : cod_especialista }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalEspecialista').modal('hide');
	});
	//Obtener login de especialista
	$("input[name='apepat']").blur(function() {
		  var formData = $('#form_especialista').serialize();
		  $.get("getLoginEspecialista",formData, function(data, status) {
			  $("input[name='login']").val(data.objeto.login);
			  $("#spanLogin").text(data.objeto.login);
			  $('#divLogin').show();
		  });
	});
	
	//Agregar Perfiles
	$('#tableNormal').on( 'click', 'span.icon-user', function (e) {
		e.preventDefault(); 
		
			var oTable = $('#tableNormal').dataTable();
			
			cod_especialista = $(this).parents('tr:first').find('td:first').text();
			
			row = $(this).parents('tr')[0];
			
			var sistema = oTable.fnGetData(row)[2]; 

			var especialista = oTable.fnGetData(row)[3]; 

			$("#lblPerfilSistema").text(sistema);
			$("#lblPerfilEspecialista").text(especialista);
			
	        $.get("getPerfilEspecialista", { cod_especialista : cod_especialista },function(data, status) {
	        	if(data.objeto!=null)
	        	{	
	        		 //limpiar seleccionados
	    	        $("#selectPerfiles option:selected").removeAttr("selected");
	    	        //limpiar multiselect y refresh control
	    	        $('#selectPerfiles').empty();
	    	        $('#selectPerfiles').multiSelect('refresh');
	    	        
		        		$.each(data.objeto, function(key, value) {
			        		//agregar hijo al grupo
		        			$('#selectPerfiles').multiSelect('addOption', { value: value.cod_perfil, text: value.nombre, index: 0 });
		            	});
		        		
		    			$.each(data.objeto, function(key, value) {
		    				value.perfilAsoc != null ? 
		    						$("#selectPerfiles option[value='" + value.cod_perfil + "']").attr("selected", 1)
		    						: "";
		    			});
	        		
	        	    $('#selectPerfiles').multiSelect('refresh');
	    	        $('#modalAsociarPerfil').modal('show');
	        	}else
	        	{
	        		toastr.info(data.mensaje, 'Mensaje');
	        	}
	        	
			});
	});
	
	*/

});