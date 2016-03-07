<div id="deleteModalUsuario" class="modal fade" tabindex="-1"
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
				<button id="btnEliminarUsuario" type="button" class="btn btn-danger">Eliminar</button>
			</div>
		</div>
	</div>
</div>
<div id="modalAsociarPerfil" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button id="btnClosePAsocPerfil" type="button" class="close"
					aria-hidden="true"></button>
				<h4 class="modal-title">Asociar Perfil</h4>
			</div>
			<form action="#" id="form_asociarPerfil" class="form-horizontal">
				<div class="modal-body">
					<div class="form-group">
						<label class="col-md-2 control-label">Usuario : </label>
						<div class="col-md-4">
							<label id="lblPerfilUsuario" class="control-label"> </label>
						</div>
						<label class="col-md-2 control-label">Sistema : </label>
						<div class="col-md-4">
							<label id="lblPerfilSistema" class="control-label sistema"></label>
						</div>
						<div class="col-md-4" hidden="true">
							<input id="AP_cod_sistema" class="control-label sistema"/>
						</div>
					</div>
					<div class="form-group last">
						<label class="control-label col-md-3">Lista de Perfiles</label>
						<div class="col-md-9">
							<select multiple="multiple" class="searchable"
								id="selectPerfiles">

							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="btnCerrarPAsocPerfil" type="button" class="btn red">
						Cerrar</button>
					<button id="btnAgregarPAsocPerfil" type="submit" class="btn green">
						Registrar</button>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="modalAsociarRol" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button id="btnClosePAsocRol" type="button" class="close"
					aria-hidden="true"></button>
				<h4 class="modal-title">Asociar Rol</h4>
			</div>
			<form action="#" id="form_asociarPerfil" class="form-horizontal">
				<div class="modal-body">
					<div class="form-group">
						<label class="col-md-2 control-label">Usuario : </label>
						<div class="col-md-4">
							<label id="lblRolUsuario" class="control-label"> </label>
						</div>
						<label class="col-md-2 control-label">Sistema : </label>
						<div class="col-md-4">
							<label id="lblRolSistema" class="control-label sistema"></label>
						</div>
						<div class="col-md-4" hidden="true">
							<input id="AR_cod_sistema" class="control-label sistema"/>
						</div>
					</div>
					<div class="form-group last">
						<label class="control-label col-md-3">Lista de Roles</label>
						<div class="col-md-9">
							<select multiple="multiple" class="searchable" id="selectRoles">

							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="btnCerrarPAsocRol" type="button" class="btn red">
						Cerrar
					</button>
					<button id="btnAgregarPAsocRol" type="submit" class="btn green">
						Registrar
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="modalAgregarUsuario" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button id="btnClosePUsuario" type="button" class="close"
					data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">Agregar</h4>
			</div>
			<form action="#" id="form_usuario" class="form-horizontal">
				<div class="modal-body">
					<div class="form-group" hidden="true">
						<div class="col-md-4">
							<input type="text" name="cod_usuario" class="form-control" />
						</div>
					</div>
					<div class="form-group" hidden="true">
							<div class="col-md-4">
								<input type="text" name="login" class="form-control" />
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
							<input type="text" name="apepat" placeholder="Apellido Paterno"
								data-required="1" class="form-control input-circle" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<input type="text" name="apemat" placeholder="Apellido Materno"
								data-required="1" class="form-control input-circle" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<input type="text" name="correo" placeholder="Correo"
								data-required="1" class="form-control input-circle" />
						</div>
					</div>
					<div id="divLogin" hidden="true" class="form-group">
						<div class="col-md-6">
							<span id="spanLogin" class="form-control-static"> </span>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<select id="sistemaList" name="sistema.cod_sistema"
								class="form-control select2me">
							</select>
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
				</div>
				<div class="modal-footer">
					<button id="btnCerrarPUsuario" type="button" data-dismiss="modal"
						class="btn red">Cerrar</button>
					<button id="btnAgregarPUsuario" type="submit" class="btn green">Registrar</button>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="page-head">
	<div class="page-title">
		<h1>Mantenimiento de Usuarios</h1>
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
					<a id="btnAgregarUsuario"
						class="btn btn-circle btn-icon-only green" data-toggle="modal"
						href="#modalAgregarUsuario"> <i class="icon-plus"></i>
					</a> <a id="buscarUsuario" class="btn btn-circle btn-icon-only blue">
						<i class="icon-magnifier"></i>
					</a>
				</div>
			</div>
			<div class="portlet-body">
				<form id="busquedaUsuario" class="margin-bottom-1">
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
						<div class="col-md-2">
							<div class="form-group form-md-line-input">
								<input type="text" class="form-control" name="apepat"
									placeholder="Apellido Paterno">
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group form-md-line-input">
								<input type="text" class="form-control" name="apemat"
									placeholder="Apellido Materno">
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="col-md-2">
							<div
								class="form-group form-md-line-input input-group input-large date-picker input-daterange"
								data-date-format="yyyy-mm-dd">
								<input type="text" class="form-control" name="fechaFiltroIni"
									placeholder="Desde"> <span class="input-group-addon">
									to </span> <input type="text" class="form-control"
									name="fechaFiltroFin" placeholder="Hasta">
							</div>
						</div>
					</div>
					<div class="form-inline margin-bottom-25">
						<div class="col-md-2">
							<div class="form-group form-md-line-input">
								<input type="text" class="form-control" name="correo"
									placeholder="Correo">
								<div class="form-control-focus"></div>
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
									<th>Sistema</th>
									<th>Nombre</th>
									<th>Ape.Pat</th>
									<th>Ape.Mat</th>
									<th>Correo</th>
									<th>Login</th>
									<th>Fecha Creacion</th>
									<th>Estado</th>
									<th class="nosort"></th>
									<th class="nosort"></th>
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