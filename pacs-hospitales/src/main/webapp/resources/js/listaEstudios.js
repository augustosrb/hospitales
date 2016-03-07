$(document).ready(function() {
	
	//Buscar Sistemas
	$("#buscarEstudio").click(function(e) {	
		e.preventDefault();
	
		var oTable = $('#tableNormal').dataTable();
		
		var formData = $('#busquedaEstudio').serialize();
		
		$('#pageBody').prepend(block);
		$.get("listarEstudios",formData, function(data, status) {
			
			//alert("Data: " + data.objeto + "\nStatus: " + status);

			block.remove();
			
			oTable.fnClearTable();
            
            $.each(data.objeto, function(key, value) {
            	//alert("Value: " + value );
            	
            	oTable.fnAddData([value.pat_id,value.pat_name,value.modality,value.fecha_registro,
                                  value.descEstado,
                                  value.diagnostico == null ? 
	                    		  '<a data-toggle="tooltip" enabled="false" title="No Disponible" >' +
	                            	'<i id="verDiagnostico" class="fa fa-file-pdf-o" ></i>' +
	                              '</a>':
                                  '<a href="downloadPDF/'+ value.cod_estudio +'" data-toggle="tooltip" title="Diagnostico" >' +
                                  	'<i id="verDiagnostico" class="fa fa-file-pdf-o" ></i>' +
                                  '</a>',
                                  '<a data-toggle="tooltip" title="Ver Estudio" onclick=open_win('+ "'" + value.pat_id+ "'" +','+ "'" +value.study_iuid+ "'" +')>' +
  	                      		  	'<span class="icon-magnifier"></span> ' +
  	                              '</a>',
  	                      	      '<a data-toggle="tooltip" title="Descargar Estudio" onclick=open_win2('+ "'" + value.pat_id + "'" + ')>' +
  	                      		  	'<span class="icon-cloud-download"></span> ' +
  	                              '</a>'
                                  ]);
            });
            oTable.fnDraw();
		});
	});
	

});

