var block = $('<div class="block-loading" />');

$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})
	


function open_win(patientid,studyUID)
{
	//alert("http://190.117.179.149/kWebViewer/kWebViewer?patientID="+patientid+"&studyUID="+studyUID);
	window.open("http://ns520856.ip-158-69-224.net:8080/oviyam/oviyam?patientID="+patientid+"&studyUID="+studyUID,"mywindow");
}

function open_win2(patientid)
{
	//alert("http://190.117.179.149/kWebViewer/kWebViewer?patientID="+patientid+"&studyUID="+studyUID);
	window.open("http://ns520856.ip-158-69-224.net:8080/weasis-pacs-connector/viewer?patientID="+patientid,"mywindow");
}

var validateUnique = function (controller,form,codigo) {

	var res = false;
	var nombre = $.trim($(form).find('input[name="nombre"]').val());	
	var cod = $.trim($(form).find('input[name="'+codigo+'"]').val());	
	var formData = form.serialize();

	if(cod!='' & nombre!='')
	{
		$.get(controller,formData, function(data, status) {
			if(data.objeto==true)
			{
				toastr.info(data.mensaje, 'Mensaje');
				$(form).find("[name='nombre']").addClass('has-error');
				res = true;		
			}
		})
	}
	return res;
}

var activarValidacion = function(formulario)
{
	$("#" + formulario).each(function(){
		 $(this).find(':input').addClass('edited');
	});
}

var desactivarValidacion = function(formulario)
{
	var validator = $("#" + formulario).validate();
	validator.resetForm();
	
	$("#" + formulario).trigger("reset");
	
	$("#" + formulario).each(function(){
		 $(this).find(':input').removeClass('edited');
	});
}

var cargarSistema = function (cod_sistema) {
	var sistemas="";
	var name = "sistema.cod_sistema";

	$.get("listarSistemas", function(data, status) {
	 
		
		 $('select[name="' + name + '"] option').remove();
		 
		 sistemas += "<option value='" + "'></option>";
		 
		 $.each(data.objeto, function(key, value) { 
			 sistemas += "<option value="+value.cod_sistema + ">"+value.nombre+"</option>";
		 });
		 
		 $('select[name="' + name + '"]').append(sistemas);	 
		 $('select[name="' + name + '"]').select2().select2("val", cod_sistema);
		  
		 $('select[name="' + name + '"]').attr('placeholder', 'Sistema').select2();
	});
}

var cargarMenu = function (cod_menu) {
	
	var menuPadres="";
	
	$("#padresList option").remove();
	
	menuPadres += '<option value=""></option>';
	
	$.get("listarMenuPadres", function(data, status) {
		 $.each(data.objeto, function(key, value) { 
    	 	 menuPadres += "<option value="+value.cod_menu + ">"+value.nombre+"</option>";
    	 });
		 
    	 $("#padresList").append(menuPadres);
    	 $("#padresList").select2().select2("val", cod_menu);
    	 
    	 $('#padresList').attr('placeholder', 'Menu').select2();
	 });
}