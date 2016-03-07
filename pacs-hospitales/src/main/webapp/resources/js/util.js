var cargarSistema = function (cod_sistema) {
	var sistemas="";
	$.get("listCboSistemas", function(data, status) {
	 $("#sistemaList option").remove();
	 
	 $.each(data.objeto, function(key, value) { 
		 sistemas += "<option value="+value.cod_sistema + ">"+value.nombre+"</option>";
	 });
	 
	 $("#sistemaList").append(sistemas);	 
	 $("#sistemaList").select2().select2("val", cod_sistema);
	});
}

var cargarPlantilla = function (cod_plantilla) {
	var plantillas="";
	$.get("listCboPlantillas", function(data, status) {
	 $("#plantillaList option").remove();
	 
	 $.each(data.objeto, function(key, value) { 
		 plantillas += "<option value="+value.cod_plantilla + ">"+value.nombre+"</option>";
	 });
	 
	 $("#plantillaList").append(plantillas);	 
	 $("#plantillaList").select2().select2("val", cod_plantilla);
	});
}

var cargarCentroAtencion = function (cod_centroAtencion) {
	var centros="";
	$.get("listCboCentros", function(data, status) {
	 $("#centroList option").remove();
	 
	 $.each(data.objeto, function(key, value) { 
		 centros += "<option value="+value.cod_centro_atencion + ">"+value.nombre+"</option>";
	 });
	 
	 $("#centroList").append(centros);	 
	 $("#centroList").select2().select2("val", cod_centroAtencion);
	});
}

var cargarSistemaFormHijo = function (cod_sistema) {
	var sistemas="";
	$.get("listCboSistemas", function(data, status) {
	 $("#sistemaListHijo option").remove();
	 
	 $.each(data.objeto, function(key, value) { 
		 sistemas += "<option value="+value.cod_sistema + ">"+value.nombre+"</option>";
	 });
	 
	 $("#sistemaListHijo").append(sistemas);	 
	 $("#sistemaListHijo").select2().select2("val", cod_sistema);
	});
}


