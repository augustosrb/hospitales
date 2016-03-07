<div id="bloqueoModalMenu" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Bloqueado</h4>
			</div>
			<div class="modal-body">Este estudio esta siendo diagnosticado.</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
			</div>
		</div>
	</div>
</div>
<div id="modalDiagnosticar" class="modal fade" tabindex="-1"
	data-backdrop="static" data-keyboard="false" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button id="btnClosePDiagnostico" type="button" class="close"
					data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">Diagnosticar</h4>
			</div>
			<form action="#" id="form_diagnostico" class="form-horizontal">
				<div class="modal-body">
					<div class="form-group" hidden="true">
						<div class="col-md-4">
							<input type="text" name="cod_estudio"   class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<label class="control-label" name="pat_name"></label>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<textarea class="form-control input-circle" name="diagnostico"
								rows="6" placeholder="Diagnostico" data-required="1"></textarea>
							<div class="form-control-focus"></div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="btnCerrarPDiagnostico" type="button" data-dismiss="modal"
						class="btn red">Cerrar</button>
					<button id="btnAgregarPDiagnostico" type="submit" class="btn green">Registrar</button>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="page-head">
	<div class="page-title">
		<h1>Lista de Trabajo</h1>
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
					<a id="buscarTrabajo" class="btn btn-circle btn-icon-only blue">
						<i class="icon-magnifier"></i>
					</a>
				</div>
			</div>
			<div class="portlet-body">
				<form id="busquedaTrabajo" class="margin-bottom-1">
					<div class="row">
						<div class="col-md-2">
							<div class="form-group form-md-line-input ">
								<input type="text" class="form-control" name="pat_id"
									placeholder="Id Paciente">
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group form-md-line-input ">
								<input type="text" class="form-control" name="pat_name"
									placeholder="Nom.Paciente">
								<div class="form-control-focus"></div>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group form-md-line-input">
								<select class="form-control select2me"
									data-placeholder="Tipo Estudios" name="modality">
									<option value=""></option>
									<option value="CT">CT</option>
									<option value="MG">MG</option>
									<option value="XA">XA</option>
									<option value="CR">CR</option>
								</select>
							</div>
						</div>
						<div class="col-md-3">
							<div
								class="form-group form-md-line-input input-group input-large date-picker input-daterange"
								data-date-format="yyyy-mm-dd">
								<input type="text" class="form-control" name="fechaRegistroIni"
									placeholder="Desde"> <span class="input-group-addon">
									- </span> <input type="text" class="form-control"
									name="fechaRegistroFin" placeholder="Hasta">
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
								    <th class="novis">Codigo Estudio</th>
									<th>Id Paciente</th>
									<th>Nom.Paciente</th>
									<th>Modalidad</th>
									<th>Fecha Recepción</th>
									<th>Tipo Estudios</th>
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