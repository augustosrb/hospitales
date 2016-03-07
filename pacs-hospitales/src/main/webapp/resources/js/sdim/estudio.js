$(document).ready(function() {
	var cod_estudio = null;
	var row=null;
	
	$("#buscarEstudio").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaUsuario').serialize();
		
		$.get("listEstudio",formData, function(data, status) {
			
			//alert("Data: " + data + "\nStatus: " + status);
		    
			oTable.fnClearTable();
            
            $.each(data, function(key, value) {
            	
                oTable.fnAddData([value.informe,
                                  value.cod_informe,
                                  value.nom_especialista,
                                  '<a>' +
                                  '<span id="editEstudio"  class="icon-pencil"></span> ' +
                                  '</a>'
                                  ]);
            });
            oTable.fnDraw();
		});
	});
	
	//para modalAgregarUsuario
	$("#btnClosePEstudio,#btnCerrarPEstudio").click(function(e) {
		var validator = $( "#form_diagnostico" ).validate();
		validator.resetForm();
		var my_editor_id = 'content';
		// set the content empty
		tinymce.get('diagnostico').setContent(''); 
		$('#modalAgregarEstudio').modal('hide');
	});
	
	
	$('#tableNormal').on( 'click', '#editEstudio', function (e) {
        e.preventDefault();
        var oTable = $('#tableNormal').dataTable();
        
        row = $(this).parents('tr')[0];
        
        cod_estudio  = oTable.fnGetData(row)[0];
        tinyMCE.get('diagnostico').setContent(cod_estudio);
        tinyMCE.activeEditor.getBody().setAttribute('contenteditable', false);
        
        /*$.get("getListaTrabajo",{ cod_estudio : cod_estudio }, function(data, status) {
        	
        });*/
        	
        $('#modalAgregarEstudio').modal('show');
	});
/*
	//Agregar/Modificar Usuarios
	$("#btnAgregarPEstudio").click(function(e) {
		e.preventDefault();
		
		diagnostico = tinyMCE.get('diagnostico').getContent();

		$.post("guardarEstudio",{ diagnostico: diagnostico, cod_estudio: cod_estudio }, function(data, status) {
			toastr.success(data.mensaje, 'Mensaje');
		});
		
			$('#modalAgregarEstudio').modal('hide');
	});
	
	*/
	
	
});