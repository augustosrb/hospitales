<div id="deleteModalRol" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Eliminar</h4>
			</div>
			<div class="modal-body">Esta seguro de eliminar este Registro</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				<button id="btnEliminarRol" type="button" class="btn btn-danger">Eliminar</button>
			</div>
		</div>
	</div>
</div>
<div id="modalAgregarRol" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button id="btnClosePRol" type="button" class="close"
					data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">Agregar</h4>
			</div>
			<form action="#" id="form_rol" class="form-horizontal">
				<div class="modal-body">
					<div class="form-group" hidden="true">
						<div class="col-md-4">
							<input type="text" name="cod_rol" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<input class="form-control input-circle" name="nombre"
								placeholder="Nombre" data-required="1">
							<div class="form-control-focus"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<textarea class="form-control input-circle" name="descripcion"
								rows="2" placeholder="Descripción" data-required="1"></textarea>
							<div class="form-control-focus"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<select class="form-control select2me" data-placeholder="Estado"
								name="estado">
								<option value=""></option>
								<option value="0">Activo</option>
								<option value="1">Inactivo</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<select id="sistemaList" name="sistema.cod_sistema"
								data-placeholder="Sistema" class="form-control select2me">
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="btnCerrarPRol" type="button" data-dismiss="modal"
						class="btn red">Cerrar</button>
					<button id="btnAgregarPRol" type="submit" class="btn green">Registrar</button>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="page-head">
	<div class="page-title">
		<h1>Mantenimiento de Roles</h1>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE FORM PORTLET-->
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption font-green-haze">
					<span class="caption-subject bold uppercase">Búsqueda</span>
				</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"> </a>
				</div>
				<div class="actions">
					<a id="btnAgregarRol" class="btn btn-circle btn-icon-only green" data-toggle="modal"
						href="#modalAgregarRol"> <i class="icon-plus"></i>
					</a> <a id="buscarRol" class="btn btn-circle btn-icon-only blue"> <i
						class="icon-magnifier"></i>
					</a>
				</div>
			</div>
			<div class="portlet-body">
				<form id="busquedaRol" class="margin-bottom-1">
					<div class="row">
						<div class="col-md-2">
							<div class="form-group form-md-line-input">
								<select class="form-control select2me" data-placeholder="Estado"
									name="estado">
									<option value=""></option>
									<option value="0">Activo</option>
									<option value="1">Inactivo</option>
								</select>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group form-md-line-input ">
								<input type="text" class="form-control" name="nombre"
									placeholder="Nombre">
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group form-md-line-input">
								<input type="text" class="form-control" name="descripcion"
									placeholder="Descripcion">
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="col-md-3">
							<div
								class="form-group form-md-line-input input-group input-large date-picker input-daterange"
								data-date-format="yyyy-mm-dd">
								<input type="text" class="form-control" name="fechaFiltroIni"
									placeholder="Desde"> <span class="input-group-addon">
									- </span> <input type="text" class="form-control"
									name="fechaFiltroFin" placeholder="Hasta">
							</div>
						</div>

					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-12">
		<div class="portlet light">
			<div class="caption font-green-haze">
				<span class="caption-subject bold uppercase">Listado</span>
			</div>
			<div class="portlet-title">
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen"
						href="javascript:;" data-original-title="" title=""> </a>
				</div>
			</div>
			<div class="portlet-body">
				<div class="portlet box green-haze">
					<div class="portlet-body">
						<table class="table table-striped table-hover" id="tableNormal">
							<thead>
								<tr>
									<th>Id</th>
									<th class="novis">Codigo Sistema</th>
									<th>Nombre</th>
									<th>Descripcion</th>
									<th>Fecha Creación</th>
									<th>Estado</th>
									<th class="nosort"></th>
									<th class="nosort"></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>


	</div>
</div>