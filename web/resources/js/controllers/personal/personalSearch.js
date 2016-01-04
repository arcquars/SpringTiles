var oTable;
$(document).ready(function() {
    $("#criteria").selectmenu();
    $('#bpersonEdit').button({disabled: false});
    $('#bpersonDelete').button({disabled: false});


    /* Add a click handler for the delete row */
    $('#delete').click(function() {
        var anSelected = fnGetSelected(oTable);
        if (anSelected.length !== 0) {
            oTable.fnDeleteRow(anSelected[0]);
        }
    });

    /* Init the table */
    var urlReloadTable = "personalpp/getListPersonByCriteria.html?criteria=" + $('#s_searchBy').val() + "&textSearch=" + $('#i_textSearch').val();
    oTable = $('#listPer').DataTable({
        "bFilter": false,
        "bLengthChange": false,
        "sAjaxSource": urlReloadTable,
        "aoColumnDefs": [
            {"bSortable": false, "aTargets": [0]},
            {"asSorting": ["asc"], "aTargets": [1]}
        ],
        "aoColumns": [
                {"mData": "perId"},
                {"mData": "names"},
                {"mData": "firstname"},
                {"mData": "position"},
                {"mData": "branch"}
            ],
    }
            
    );
    
    $('#listPer tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected row_selected')) {
                $(this).removeClass('selected row_selected');
            }
            else {
                oTable.$('tr.selected').removeClass('selected row_selected');
                $(this).addClass('selected row_selected');
            }
    });
        

    $('#bpersonDelete').click(function() {
        $('#dialogDelete').dialog('open');
        $('#dialogDelete').css('visibility', 'visible');
    });

    $('#dialogDelete').dialog({
        autoOpen: false,
        resizable: false,
        height: 140,
        width: 140,
        modal: true,
        buttons: {
            "Borrar": function() {
                var ddd = $(this);
                $.ajax({
                    url: 'personalpp/personDelete.html',
                    data: {
                        perId: $('#hdid').val()
                    },
                    success: function(data) {
                        if (data == 1) {
                            $('#delete').trigger('click');
                            ddd.dialog('close');
                        }
                        else
                            $('#errorDelete').html(data).css('color', '#f3410d');
                    }

                });
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        }
    });
    
    $( "#cssmenu ul li" ).each(function( index ) {
        $(this).removeClass("active");
    });
    
    $( "#cssmenu ul" ).each(function( index ) {
        if(index === 6){
            $(this).parent().addClass("active");
        }
    });
});
function linkpersonEdit() {
    var perId = oTable.row('.selected').data().perId;
    window.location.href = 'personalpp/personEdit/' + perId + '.html';
//    alert("perId: "+perId);
//    return false;
}

function fnGetSelected(oTableLocal)
{
    var aReturn = new Array();
    var aTrs = oTableLocal.fnGetNodes();

    for (var i = 0; i < aTrs.length; i++)
    {
        if ($(aTrs[i]).hasClass('row_selected'))
        {
            aReturn.push(aTrs[i]);
        }
    }
    return aReturn;
}

function employeeRefresh() {
    //oTable12.api().ajax.reload();
    oTable.ajax.url("personalpp/getListPersonByCriteria.html?criteria=" + $('#s_searchBy').val() + "&textSearch=" + $('#i_textSearch').val()).load();
}