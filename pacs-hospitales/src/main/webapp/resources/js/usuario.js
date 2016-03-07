$(document).ready(function() {
	var cod_usuario = null;
	var row=null;
	
	//Buscar Usuarioes
	$("#buscarUsuario").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaUsuario').serialize();
		
		$('#pageBody').prepend(block);
		$.get("listarUsuarios",formData, function(data, status) {
			
			//alert("Data: " + data.objeto + "\nStatus: " + status);

			block.remove();
			
			oTable.fnClearTable();
            
            $.each(data.objeto, function(key, value) {
            	//alert("Value: " + value );
            	
                oTable.fnAddData([value.cod_usuario,
                                  value.sistema.cod_sistema,
                                  value.sistema.nombre,
                                  value.nombre,
                                  value.apepat,
                                  value.apemat,
                                  value.correo,
                                  value.login,
                                  value.fecha_creacion,
                                  value.estado==0 ? "Activo" : "Inactivo",
                                  '<a><span id="editUsuario" class="icon-pencil"></span></a>',
                                  '<a><span  class="icon-close"></span></a>',
                                  '<a><span  class="icon-user"></span></a>',
                                  '<a><span  class="icon-users"></span></a>'
                                  ]);
            });
            
            oTable.fnDraw();
		});
	});
	
	//Bonotes Modal y Agregar Principal
	$("#btnClosePUsuario,#btnCerrarPUsuario,#btnAgregarUsuario").click(function(e) {
		$('.form-group').removeClass('has-error');
		var validator = $( "#form_usuario" ).validate();
		validator.resetForm();
		$('#form_usuario').trigger("reset");
		$('[name=estado]').select2("val", "");
		$('#modalAgregarUsuario').modal('hide');
		$("#form_usuario [name='nombre']").parent().removeClass('has-error');
		
		$("#spanLogin").empty();
		$('#divLogin').hide();
	});
	//Agregar Principal
	$("#btnAgregarUsuario").click(function(e) {
		cargarSistema();
	});

	//cerrar para modalAsociarPerfil
	$("#btnClosePAsocPerfil,#btnCerrarPAsocPerfil,#btnAgregarPAsocPerfil").click(function(e) {
		$('#modalAsociarPerfil').modal('hide');
	});

	//cerrar para modalAsociarRol
	$("#btnClosePAsocRol,#btnCerrarPAsocRol,#btnAgregarPAsocRol").click(function(e) {
		$('#modalAsociarRol').modal('hide');
	});
	
	//Editar Usuario
	$('#tableNormal').on( 'click', '#editUsuario', function (e) {
        e.preventDefault();
     
        cod_usuario = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        
        var oTable = $('#tableNormal').dataTable();
        var cod_sistema = oTable.fnGetData(row)[1];
        $.get("buscaUsuario",{ cod_usuario : cod_usuario }, function(data, status) {
           
			$.each(data.objeto, function(key, value){
        		if(value != null){    			
        			key =='estado' ? 
        						$('[name=estado]','#form_usuario').select2("val", value) : 
        							$('[name='+key+']', '#form_usuario').val(value);
					if(key =='login')
        			{
        				$("#spanLogin").text(value);
        				$('#divLogin').show();
        			}
        		}
        	});
			cargarSistema(cod_sistema);
        	$('#modalAgregarUsuario').modal('show');
		});
	});
	
	//Agregar Usuario
	$("#form_usuario").submit(function(e) {
		e.preventDefault();
		
		var form = $('#form_usuario');
		var oTable = $('#tableNormal').dataTable();
		
		var formData = form.serialize();
		
		if(form.valid() == true){
			$('[name=cod_usuario]', '#form_usuario').val() == "" ? 
				$.post("saveUsuario",formData, function(data, status){
					oTable.fnAddData( [
										data.objeto.cod_usuario,
										data.objeto.sistema.cod_sistema,
										data.objeto.sistema.nombre,
										data.objeto.nombre,
										data.objeto.apepat,
										data.objeto.apemat,
										data.objeto.correo,
										data.objeto.login,
										data.objeto.fecha_creacion,
										data.objeto.estado==0 ? "Activo" : "Inactivo",
										'<a><span id="editUsuario" class="icon-pencil"></span></a>',
										'<a><span  class="icon-close"></span></a>',
										'<a><span  class="icon-user"></span></a>',
										'<a><span  class="icon-users"></span></a>']
	                                 );
					toastr.success(data.mensaje, 'Mensaje');
				})
				:$.post("updateUsuario",formData, function(data, status){
					oTable.fnUpdate( [
										data.objeto.cod_usuario,
										data.objeto.sistema.cod_sistema,
										data.objeto.sistema.nombre,
										data.objeto.nombre,
										data.objeto.apepat,
										data.objeto.apemat,
										data.objeto.correo,
										data.objeto.login,
										data.objeto.fecha_creacion,
										data.objeto.estado==0 ? "Activo" : "Inactivo",
										'<a><span id="editUsuario" class="icon-pencil"></span></a>',
										'<a><span  class="icon-close"></span></a>',
										'<a><span  class="icon-user"></span></a>',
										'<a><span  class="icon-users"></span></a>'],row
	                                 );
					toastr.success(data.mensaje, 'Mensaje');
			    });	
			$('#modalAgregarUsuario').modal('hide');
		}	
	});
	
	//Eliminar Usuario
	$('#tableNormal').on( 'click', 'span.icon-close', function (e) {
        e.preventDefault();
     
        cod_usuario = $(this).parents('tr:first').find('td:first').text();
        row = $(this).parents('tr')[0];
        $('#deleteModalUsuario').modal('show');
	});
	
	//Eliminar Usuario en Modal
	$("#btnEliminarUsuario").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		oTable.fnDeleteRow(row);
		
		var formData = $('#form_usuario').serialize();
		 $.post("deleteUsuario",{ cod_usuario : cod_usuario }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalUsuario').modal('hide');
	});
	
	//Obtener login de usuario
	$("input[name='apepat']").blur(function() {
		  var formData = $('#form_usuario').serialize();
		  $.get("getLoginUsuario",formData, function(data, status) {
			  $("input[name='login']").val(data.objeto.login);
			  $("#spanLogin").text(data.objeto.login);
			  $('#divLogin').show();
		  });
	});
	
	//Agregar Perfiles
	$('#tableNormal').on( 'click', 'span.icon-user', function (e) {
		e.preventDefault(); 
		
			var oTable = $('#tableNormal').dataTable();
			
			cod_usuario = $(this).parents('tr:first').find('td:first').text();
			
			row = $(this).parents('tr')[0];
			
			var sistema = oTable.fnGetData(row)[2]; 

			var usuario = oTable.fnGetData(row)[3]; 
			
			var cod_sistema = oTable.fnGetData(row)[1]; 

			$("#lblPerfilSistema").text(sistema);
			$("#lblPerfilUsuario").text(usuario);
			
			$('[name="AP_cod_sistema"]', '#form_asociarPerfil').val(cod_sistema);
			
	        $.get("getPerfilUsuario", { cod_usuario : cod_usuario },function(data, status) {
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
	
	//Agregar Roles
	$('#tableNormal').on( 'click', 'span.icon-users', function (e) {
		e.preventDefault(); 
		
			var oTable = $('#tableNormal').dataTable();
			
			cod_usuario = $(this).parents('tr:first').find('td:first').text();
			
			row = $(this).parents('tr')[0];
			
			var sistema = oTable.fnGetData(row)[2]; 
	
			var usuario = oTable.fnGetData(row)[3]; 
			
			var cod_sistema = oTable.fnGetData(row)[1]; 
	
			$("#lblRolSistema").text(sistema);
			$("#lblRolUsuario").text(usuario);
			
			$('[name="AR_cod_sistema"]', '#form_asociarRol').val(cod_sistema);
			
	        $.get("getRolUsuario", { cod_usuario : cod_usuario, "sistema.cod_sistema" : cod_sistema },function(data, status) {
	        	if(data.objeto!=null)
	        	{	
	        		//limpiar seleccionados
	    	        $("#selectRoles option:selected").removeAttr("selected");
	    	        //limpiar multiselect y refresh control
	    	        $('#selectRoles').empty();
	    	        $('#selectRoles').multiSelect('refresh');
	    	        
		        		$.each(data.objeto, function(key, value) {
			        		//agregar hijo al grupo
			            	$('#selectRoles').multiSelect('addOption', { value: value.cod_rol, text: value.nombre, index: 0 });
		            	});
		        		
		        		$.each(data.objeto, function(key, value) {
		        			value.rolAsoc != null ? 
		        					$("#selectRoles option[value='" + value.cod_rol + "']").attr("selected", 1)
		    						: "";
		    			});
	        	    
		        	$('#selectRoles').multiSelect('refresh');
	    	        $('#modalAsociarRol').modal('show');
	        	}else
	        	{
	        		toastr.info(data.mensaje, 'Mensaje');
	        	}
			});
	});
	
	$("#btnAgregarPAsocPerfil").click(function(e) {
		guardarMultiSelect("perfil");
	});
	$("#btnAgregarPAsocRol").click(function(e) {
		guardarMultiSelect("rol");
	});
	
	var guardarMultiSelect = function (multiSelect) {
		
		if(multiSelect == "rol")
		{
			var roles = $('select#selectRoles').val() == null ? 0 : $('select#selectRoles').val() ;
			$.ajax({
				url : 'registrarAsocRol',
				data : "rolsInt=" + roles + "&cod_usuario=" + cod_usuario,
				type : "POST",
				success : function(responce) {
					toastr.success(responce.mensaje, 'Mensaje');
				}
			});					
		}else
		{	
			var perfiles = $('select#selectPerfiles').val() == null ? 0 : $('select#selectPerfiles').val();
			$.ajax({
				url : 'registrarAsocPerfil',
				data : "perfilsInt=" + perfiles + "&cod_usuario=" + cod_usuario,
				type : "POST",
				success : function(responce) {
					toastr.success(responce.mensaje, 'Mensaje');
				}
			});			
		}
    }

});