$(document).ready(function() {
	var cod_estudio = null;
	var row=null;
	
	$("#buscarListaTrabajo").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaUsuario').serialize();
		
		cargarListaTrabajo(formData,oTable);
	});
	
	
	$("#buscarListaInforme").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaUsuario').serialize();
		
		$.get("listInforme",formData, function(data, status) {
			
			//alert("Data: " + data + "\nStatus: " + status);
		    
			oTable.fnClearTable();
	        var estado = "";
	        $.each(data, function(key, value) {
	        	if (estado == 0)	
	        		estado = "Asignado"
	            if (estado == 1)	
	            	estado = "Reasignado"            			
	            if (estado == 2)	
	            	estado = "Diagnosticado"            	
	            if (estado == 3)	
	            	estado = "Informado"
	            		
	            oTable.fnAddData([value.informe,
	                              value.cod_informe,
	                              value.nom_paciente,
	                              value.nom_especialista,
	                              '<a>' +
	                              '<span id="editListaInforme"  class="icon-pencil" data-toggle="modal"></span> ' +
	                              '</a>'
	                              ]);
	        });
	        oTable.fnDraw();
		});

	});
	
	//para modalAgregarUsuario
	$("#btnClosePDiagnostico,#btnCerrarPDiagnostico").click(function(e) {
		var validator = $( "#form_diagnostico" ).validate();
		validator.resetForm();
		var my_editor_id = 'content';
		// set the content empty
		tinymce.get('diagnostico').setContent(''); 
		$('#modalAgregarDiagnostico').modal('hide');
	});
	
	$("#btnClosePInforme,#btnCerrarPInforme").click(function(e) {
		var my_editor_id = 'content';
		// set the content empty
		tinymce.get('informe').setContent(''); 
		$('#modalAgregarInforme').modal('hide');
	});
	
	
	$('#tableNormal').on( 'click', '#editListaInforme', function (e) {
        e.preventDefault();
        var oTable = $('#tableNormal').dataTable();
        
        row = $(this).parents('tr')[0];
        
        cod_estudio  = oTable.fnGetData(row)[0];
        tinyMCE.get('informe').setContent(cod_estudio);
        tinyMCE.activeEditor.getBody().setAttribute('contenteditable', false);
        /* $.get("getListaTrabajo",{ cod_estudio : cod_estudio }, function(data, status) {
        	tinyMCE.get('informe').setContent(data.diagnostico);
        });*/
        	
        $('#modalAgregarInforme').modal('show');
	});
	
	$('#tableNormal').on( 'click', '#editListaTrabajo', function (e) {
        e.preventDefault();
        var oTable = $('#tableNormal').dataTable();
        
        row = $(this).parents('tr')[0];
        
        cod_estudio  = oTable.fnGetData(row)[0];
        
        $.get("getListaTrabajo",{ cod_estudio : cod_estudio }, function(data, status) {
        	tinyMCE.get('diagnostico').setContent(data.diagnostico);
        });
        	
        $('#modalAgregarDiagnostico').modal('show');
	});
	
	//Agregar/Modificar Usuarios
	$("#btnAgregarPDiagnostico").click(function(e) {
		e.preventDefault();
		
		diagnostico = tinyMCE.get('diagnostico').getContent();

		var body = diagnostico.match(/<p[^>]*>[\s\S]*<\/p>/gi);
		
		if(body==null)
		{
			toastr.warning("Ingrese su diagnostico", 'Mensaje');
		}else{
			cargarConfirmaDiagnostico();
		}			
	});

	//Eliminar Sistema
	$("#btnCofirmaDiagnostico").click(function(e) {
		e.preventDefault();
		
		diagnostico = tinyMCE.get('diagnostico').getContent();
		
		$.post("guardarDiagnostico",{ diagnostico: diagnostico, cod_estudio: cod_estudio }, function(data, status) {
			toastr.success(data.mensaje, 'Mensaje');
			cargarListaTrabajo(formData,oTable);
		});
		$('#confimaDiagnosticoModal').modal('hide');
		$('#modalAgregarDiagnostico').modal('hide');
	});
});
var cargarConfirmaDiagnostico = function (formData,oTable) {
	$('#confimaDiagnosticoModal').modal('show');
}

var cargarListaTrabajo = function (formData,oTable) {
	$.get("listTrabajo",formData, function(data, status) {
		
		//alert("Data: " + data + "\nStatus: " + status);
	    
		oTable.fnClearTable();
	    var estado = "";
	    $.each(data, function(key, value) {
	    	if (estado == 0)	
	    		estado = "Asignado"
	        if (estado == 1)	
	        	estado = "Reasignado"            			
	        if (estado == 2)	
	        	estado = "Diagnosticado"            	
	        if (estado == 3)	
	        	estado = "Informado"
	        		
	        oTable.fnAddData([value.cod_estudio,
	                          value.descripcion,
	                          value.nombre,
	                          value.tiempo_estimado,
	                          estado,
	                          '<a>' +
	                          '<span id="editListaTrabajo"  class="icon-pencil"></span> ' +
	                          '</a>',
	                      	  '<a onclick=open_win('+ "'" + value.login+ "'" +','+ "'" +value.study_iuid+ "'" +')>' +
	                      		'<span   class="icon-magnifier"></span> ' +
	                          '</a>'
	                          ]);
	    });
	    oTable.fnDraw();
	});   
}
function open_win(patientid,studyUID)
{
	//alert("http://190.117.179.149/kWebViewer/kWebViewer?patientID="+patientid+"&studyUID="+studyUID);
	window.open("http://190.117.179.149/oviyam/oviyam?patientID="+patientid+"&studyUID="+studyUID,"mywindow");
}