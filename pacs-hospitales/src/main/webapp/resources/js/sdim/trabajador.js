$(document).ready(function() {
	var cod_trabajador = null;
	var row=null;
	
	//para modalAgregarTrabajador
	$("#btnClosePTrabajador,#btnCerrarPTrabajador").click(function(e) {
		$('.form-group').removeClass('has-error');
		var validator = $( "#form_trabajador" ).validate();
		validator.resetForm();
		$('#form_trabajador').trigger("reset");
		$('[name=estado]').select2("val", "");
		$('#modalAgregarTrabajador').modal('hide');
	});
	
	//para modalAgregarTrabajador
	$("#btnCloseEditTrabajador,#btnCerrarEditTrabajador").click(function(e) {
		$('.form-group').removeClass('has-error');
		var validator = $( "#form_trabajador" ).validate();
		validator.resetForm();
		$('#form_trabajador').trigger("reset");
		$('[name=estado]').select2("val", "");
		$('#modalEditarTrabajador').modal('hide');
	});
	
	$("#btnAgregarTrabajador").click(function(e) {
		$.get("getUsuariosTrab",function(data, status) {
        	if(data.objeto!=null)
        	{	
        		 //limpiar seleccionados
    	        $("#selectUsuarios option:selected").removeAttr("selected");
    	        //limpiar multiselect y refresh control
    	        $('#selectUsuarios').empty();
    	        $('#selectUsuarios').multiSelect('refresh');
    	        
	        		$.each(data.objeto, function(key, value) {
		        		//agregar hijo al grupo
	        			$('#selectUsuarios').multiSelect('addOption', { value: value.cod_trabajador + "/" +
	        																   value.nombre + "/" +
													        				   value.apepat + "/" +
																			   value.apemat + "/" +
																			   value.correo,
	        															text: value.nombre + " " + 
	        																  value.apepat + " " +
	        																  value.apemat  , index: 0 });
	            	});
	        		
	        		$.each(data.objeto, function(key, value) {
	        			value.trabajador_registrado != 1 ? 
	    						$("#selectUsuarios option[value='" + value.cod_trabajador + '/' +
										   value.nombre + '/' +
				        				   value.apepat + '/' +
										   value.apemat + '/' +
										   value.correo
	    								+ "']").attr("selected", 1)
	    						: "";
	    			});
        		
        	    $('#selectUsuarios').multiSelect('refresh');
    	        $('#modalAgregarTrabajador').modal('show');
        	}else
        	{
        		toastr.info(data.mensaje, 'Mensaje');
        	}
		});
	});
	
	$("#btnAgregarPTrabajador").click(function(e) {
		var trabajador = $('select#selectUsuarios').val() == null ? null : $('select#selectUsuarios').val();
		$.ajax({
			url : 'agregarTrabajador',
			data : trabajador == null ? "" : "trabajador=" + trabajador,
			type : "POST",
			success : function(responce) {
				toastr.success(responce.mensaje, 'Mensaje');
			}
		});	
		$('#modalEditarTrabajador').modal('hide');
	});
	
	$("#buscarTrabajador").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaTrabajador').serialize();
		
		$.get("listTrabajador",formData, function(data, status) {
			
			//alert("Data: " + data + "\nStatus: " + status);
		    
			oTable.fnClearTable();
            
            $.each(data, function(key, value) {
            	
                oTable.fnAddData([value.cod_trabajador,
                                  value.nombre,
                                  value.apepat,
                                  value.apemat,
                                  value.correo,
                                  value.fecha_creacion,
                                  value.estado==0 ? "Activo" : "Inactivo",
                                  '<a>' +
                                  '<span id="editTrabajador"  class="icon-pencil"></span> ' +
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
	
	//Agregar/Modificar Trabajadors
	$("#btnEditarPTrabajador").click(function(e) {
		e.preventDefault();
		var form = $('#form_trabajador');
		var oTable = $('#tableNormal').dataTable();
		
		if(form.valid()== true){
			
			var formData = $('#form_trabajador').serialize();
			
				$.post("actualizarTrabajador",formData, function(data, status){
					
					oTable.fnUpdate( [
	                                   data.objeto.cod_trabajador,
	                                   data.objeto.nombre,
	                                   data.objeto.apepat,
	                                   data.objeto.apemat,
	                                   data.objeto.correo,
	                                   data.objeto.fecha_creacion,
	                                   data.objeto.estado==0 ? "Activo" : "Inactivo",
	                        		   '<a>' +
	                                   '<span id="editTrabajador" class="icon-pencil"></span> ' +
	                                   '</a>',
	                                   '<a>' + 
	                                   '<span  class="icon-close"></span></a>'],row
	                                 );
					toastr.success(data.mensaje, 'Mensaje');
			    });	
	         
			$('#modalEditarTrabajador').modal('hide');
		}
	});
	
	$('#tableNormal').on( 'click', '#editTrabajador', function (e) {
        e.preventDefault();
        var oTable = $('#tableNormal').dataTable();
        
        cod_trabajador = $(this).parents('tr:first').find('td:first').text();
        
        row = $(this).parents('tr')[0];
       
        var cod_sistema = oTable.fnGetData(row)[1]; 

        $.get("getTrabajador",{ cod_trabajador : cod_trabajador }, function(data, status) {
           
    		$.each(data, function(key, value){
        		if(value != null){    			
        			key =='estado' ? 
        						$('[name=estado]','#form_trabajador').select2("val", value) : 
        							$('[name='+key+']', '#form_trabajador').val(value);
        		}
        	});
    		$('#modalEditarTrabajador').modal('show');
    	});		   
        cargarCentroAtencion();
	});
	
	$("#btnEliminarTrabajador").click(function(e) {
		e.preventDefault();
		var oTable = $('#tableNormal').dataTable();
		oTable.fnDeleteRow(row);
		
		var formData = $('#form_trabajador').serialize();
		 $.post("eliminarTrabajador",{ cod_trabajador : cod_trabajador }, function(data, status){
		        toastr.success(data.mensaje, 'Mensaje');
		    });
		$('#deleteModalTrabajador').modal('hide');
	});
});