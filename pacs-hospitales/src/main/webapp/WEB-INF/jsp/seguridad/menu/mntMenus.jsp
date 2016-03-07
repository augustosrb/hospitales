<div id="deleteModalMenu" class="modal fade" tabindex="-1"
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
				<button id="btnEliminarMenu" type="button" class="btn btn-danger">Eliminar</button>
			</div>
		</div>
	</div>
</div>
<div id="modalAgregarMenu" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button id="btnClosePMenu" type="button" class="close"
					aria-hidden="true"></button>
				<h4 class="modal-title">Agregar</h4>
			</div>

			<div class="modal-body">
				<!-- BEGIN FORM-->
				<div class="form-body">
					<div class="form-group">
						<button id="btnMenu" type="button"
							class="btn blue">Menu</button>
						<button id="btnSubMenu" type="button" 
							class="btn blue">SubMenu</button>
					</div>
					<form action="#" id="form_menu_padre" class="form-horizontal">
						<div class="form-group" hidden="true">
							<div class="col-md-6">
								<input type="text" name="cod_menu" class="form-control input-circle" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-6">
								<input type="text" name="nombre" placeholder="Nombre"
									data-required="1" class="form-control input-circle" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-6">
								<input type="text" name="descripcion" placeholder="Descripcion"
									data-required="1" class="form-control input-circle" />
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
					</form>
					<form action="#" id="form_menu_hijo" class="form-horizontal">
						<div class="form-group" hidden="true">
							<div class="col-md-6">
								<input type="text" name="cod_menu" class="form-control input-circle" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-6">
								<input type="text" name="nombre" placeholder="Nombre"
									data-required="1" class="form-control input-circle" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-6">
								<input type="text" name="descripcion" placeholder="Descripcion"
									data-required="1" class="form-control input-circle" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-6">
								<input name="ruta" placeholder="Ruta" class="form-control input-circle" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-6">
								<select id="padresList" class="form-control select2me"
									data-placeholder="Menu" name="grupo">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-6">
								<select id="sistemaListHijo" name="sistema.cod_sistema"
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
					</form>
				</div>

				<!-- END FORM-->
			</div>
			<div class="modal-footer">
				<button id="btnCerrarPMenu" type="button" data-dismiss="modal"
					class="btn red">Cerrar</button>
				<button id="btnAgregarPMenu" type="submit" class="btn green">Registrar</button>
			</div>

		</div>
	</div>
</div>
<div class="page-head">
	<div class="page-title">
		<h1>Mantenimiento de Menus</h1>
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
					<a id="btnAgregarMenu" class="btn btn-circle btn-icon-only green"
						data-toggle="modal" href="#modalAgregarMenu"> <i
						class="icon-plus"></i>
					</a> <a id="buscarMenu" class="btn btn-circle btn-icon-only blue">
						<i class="icon-magnifier"></i>
					</a>
				</div>
			</div>
			<div class="portlet-body">
				<form id="busquedaMenu" class="margin-bottom-1">
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
									to </span> <input type="text" class="form-control"
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
									<!--<th></th>
									<th class="novis">Lista Hijos</th>-->
									<th>Id</th>
									<th class="novis">Codigo Sistema</th>
									<th>Nombre</th>
									<th>Descripcion</th>
									<th>Fecha Creación</th>
									<th>Estado</th>
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