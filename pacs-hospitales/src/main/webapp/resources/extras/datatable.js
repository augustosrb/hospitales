var TableAplicacion = function () {

    var tableNormal = function () {

    	if ( $.fn.dataTable.isDataTable('#tableNormal') ) {
    	    table = $('#tableNormal').DataTable();
    	    return;
    	}
    	else {
    	    table = $('#tableNormal');
    	}
        var table = $('#tableNormal');

        /* Fixed header extension: http://datatables.net/extensions/scroller/ */

        var oTable = table.dataTable({
            "dom": "<'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // datatable layout without  horizobtal scroll
            "scrollY": "300",
            "iDisplayLength": 5,
            "bScrollCollapse": true,
            "deferRender": true,
            "order": [
                [0, 'asc']
            ],
            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            "pageLength": 10 , // set the initial value
            "aoColumnDefs": [{
                "bSortable": false,
                "aTargets": ['nosort']
            },
            {
            	"bVisible": false,
                "aTargets": ['novis']
            },
            ]           
        });

        var tableWrapper = $('#tableNormal_wrapper'); // datatable creates the table wrapper by adding with id {your_table_jd}_wrapper
        tableWrapper.find('.dataTables_length select').select2(); // initialize select2 dropdown
    }

    return {

        //main function to initiate the module
        init: function () {

            if (!jQuery().dataTable) {
                return;
            }

            console.log('me 1');
            tableNormal();
            console.log('me 2');
        }

    };

}();