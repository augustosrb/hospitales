$(document).ready(function() {
	
	var row=null;
	
	//Buscar Sistemas
	$("#buscarTrabajo").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaTrabajo').serialize();
		
		$('#pageBody').prepend(block);
		$.get("findAllTrabajos",formData, function(data, status) {
			
			//alert("Data: " + data.objeto + "\nStatus: " + status);

			block.remove();
			
			oTable.fnClearTable();
            
            $.each(data.objeto, function(key, value) {
            	//alert("Value: " + value );
            	
            	oTable.fnAddData([value.cod_estudio,value.pat_id,value.pat_name,value.modality,value.fecha_registro,
                                  value.descEstado,
                                  '<a data-toggle="tooltip" title="Diagnosticar">' +
                                  	'<span  id="diagnosticar" class="icon-pencil"></span> ' +
                                  '</a>',
                                  
                                  '<a  data-toggle="tooltip" title="Ver Estudio" onclick=open_win('+ "'" + value.pat_id+ "'" +','+ "'" +value.study_iuid+ "'" +')>' +
  	                      		  	'<span  class="icon-magnifier"></span> ' +
  	                              '</a>',
  	                      	      '<a data-toggle="tooltip" title="Descargar Estudio" onclick=open_win2('+ "'" + value.pat_id + "'" + ')>' +
  	                      		  	'<span class="icon-cloud-download"></span> ' +
  	                              '</a>'
                                  ]);
            });
            
            oTable.fnDraw();
		});
	});
	
	$("#btnClosePDiagnostico,#btnCerrarPDiagnostico").click(function(e) {
		desbloquearEstudio(row);
		$('.form-group').removeClass('has-error');
		var validator = $( "#form_diagnostico" ).validate();
		validator.resetForm();
		$('#form_diagnostico').trigger("reset");
		//$('[name="diagnostico"]', '#form_diagnostico').text('')
		$('#modalDiagnosticar').modal('hide');
		
	});
	
	$('#tableNormal').on( 'click', '#diagnosticar', function (e) {
        e.preventDefault();
        
    	var oTable = $('#tableNormal').dataTable();
    	
        row = $(this).parents('tr')[0];
        
        var cod_estudio = oTable.fnGetData(row)[0]; 
        $.get("getEstudiobyID",{ cod_estudio: cod_estudio }, function(data, status) {
           
        	if(data.objeto.estado == 3)
        	{
        		$('#bloqueoModalMenu').modal('show');
        	}
        	else{        	
        		oTable.fnUpdate([
                                  data.objeto.cod_estudio,
                                  data.objeto.pat_id,
                                  data.objeto.pat_name,
                                  data.objeto.modality,
                                  data.objeto.fecha_registro,
                                  data.objeto.descNewEstado,
                                  '<a data-toggle="tooltip" title="Diagnosticar">' +
                                	'<span  id="diagnosticar" class="icon-pencil"></span> ' +
                                  '</a>',
                                  '<a  data-toggle="tooltip" title="Ver Estudio" onclick=open_win('+ "'" + data.objeto.pat_id+ "'" +','+ "'" +data.objeto.study_iuid+ "'" +')>' +
	                      		  	'<span  class="icon-magnifier"></span> ' +
	                              '</a>',
	                      	      '<a data-toggle="tooltip" title="Descargar Estudio" onclick=open_win2('+ "'" + data.objeto.pat_id + "'" + ')>' +
	                      		  	'<span class="icon-cloud-download"></span> ' +
	                              '</a>'
                                  ],row
                                );
        		//Cargar 	
				$.each(data.objeto, function(key, value){
	        			if(value != null){    			
	            			key =='diagnostico' ? 
	            						$('[name=diagnostico]','#form_diagnostico').val(value) : 
	            							$('[name='+key+']', '#form_diagnostico').text(value);
	            						
							if(key =='cod_estudio')
		        			{
								$('[name='+key+']','#form_diagnostico').val(value) 
		        			}				
	            	}
	        	});
	        	$('#modalDiagnosticar').modal('show');
        	}
		});
	});
	
	$("#form_diagnostico").submit(function(e) {
		e.preventDefault();
		
		var form = $('#form_diagnostico');
		var oTable = $('#tableNormal').dataTable();
		
		var formData = form.serialize();
		
		if(form.valid() == true){
			$.post("diagnosticar",formData, function(data, status){
				toastr.success(data.mensaje, 'Mensaje');
				
				oTable.fnDeleteRow(row);
				
			})
			$('#modalDiagnosticar').modal('hide');
		}	
	});
});


var desbloquearEstudio = function(row)
{
	
	var oTable = $('#tableNormal').dataTable();
	var form = $('#form_diagnostico');
	var formData = form.serialize();
	
	$.post("desbloqueoEstudio",formData, function(data, status){
		
		oTable.fnUpdate([
             data.objeto.cod_estudio,
             data.objeto.pat_id,
             data.objeto.pat_name,
             data.objeto.modality,
             data.objeto.fecha_registro,
             data.objeto.descEstado,
             '<a data-toggle="tooltip" title="Diagnosticar">' +
           	'<span  id="diagnosticar" class="icon-pencil"></span> ' +
             '</a>',
             '<a  data-toggle="tooltip" title="Ver Estudio" onclick=open_win('+ "'" + data.objeto.pat_id+ "'" +','+ "'" +data.objeto.study_iuid+ "'" +')>' +
     		  	'<span  class="icon-magnifier"></span> ' +
             '</a>',
     	      '<a data-toggle="tooltip" title="Descargar Estudio" onclick=open_win2('+ "'" + data.objeto.pat_id + "'" + ')>' +
     		  	'<span class="icon-cloud-download"></span> ' +
             '</a>'
             ],row
           );
		
	})
}


