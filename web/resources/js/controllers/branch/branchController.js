$(function() {
    $(".mbutton").button();
    $("a", ".demo").click(function() {
        return false;
    });
    $("input[type=submit], button").button();
});

var oTable;

$(document).ready(function() {

    $('#bBranchEdit').button({
        disabled: true
    });
    $('#bBranchDelete').button({
        disabled: true
    });


    /* Add a click handler to the rows - this could be used as a callback */
    selectTable();

    /* Init the table */
    oTable = $('#listBranch').dataTable({
        "bFilter": false,
        "bLengthChange": false,
        "aoColumnDefs": [
            {
                "sTitle": "id",
                "bVisible": false,
                "aTargets": [0]
            },
            {"sTitle": "name", "aTargets": [1]},
            {"sTitle": "address", "aTargets": [2]},
            {"sTitle": "phone", "aTargets": [3]},
        ]
    }
    );

    $('#bBranchDelete').click(function() {
        $.ajax({
            url: contextPath + '/branchpp/branchValidDelete.html',
            data: {
                branchId: $('#hdid').val()
            },
            type: 'POST',
            success: function(data) {
                if (data == 'true') {
                    $('#dialogDeleteBranch').dialog('open');
                    $('#divError').css("display","none");
                    $('#divError').children().remove();
                }
                else{
                    $('#divError').css("display","block");
                    $('#divError').html("<p class='errorMenssage'>Esta Sucursal tiene productos.</p>");
                }
            }

        });
    });

    $('#dialogDeleteBranch').dialog({
        autoOpen: false,
        resizable: false,
        width: 220,
        modal: true,
        buttons: {
            "Borrar": function() {
                var ddd = $(this);
                $.ajax({
                    url: contextPath + '/branchpp/branchDelete.html',
                    data: {
                        branchId: $('#hdid').val()
                    },
                    success: function(data) {
                        $('#errorDelete').children().remove();
                        if (data == 'true') {
                            var anSelected = fnGetSelected(oTable);
                            if (anSelected.length !== 0) {
                                oTable.fnDeleteRow(anSelected[0]);
                            }
                            oTable.fnDraw();
                            selectTable();
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

    $('#dialogUpdateBranch').dialog({
        autoOpen: false,
        resizable: false,
        height: 220,
        width: 300,
        modal: true,
        buttons: {
            "Grabar": function() {
                var ddd = $(this);
                if ($("#f_edit_branch").valid() == true) {
                    $.ajax({
                        url: 'branchpp/branchUpdate.html',
                        data: $('#f_edit_branch').serialize(),
                        dataType: 'json',
                        success: function(data) {
                            if (data) {
                                oTable.fnClearTable();
                                $.each(data, function(i, item) {
                                    oTable.fnAddData([
                                        item.branchId,
                                        "<p>" + item.name + "<strong style='visibility: hidden;'>" + item.branchId + "</p>",
                                        "<p>" + item.address + "</p>",
                                        "<p>" + item.phone + "</p>"
                                    ]);

                                });
                                oTable.fnDraw();
                                selectTable();
                                ddd.dialog('close');
                            }
                            else {
                                if ($('#pErrorMensaje').html() == '')
                                    $('#pErrorMensaje').append('No se puede modificar los datos de la tienda');
                            }
                        }

                    });
                }
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        }
    });

    $('#divBranchCreate').dialog({
        autoOpen: false,
        resizable: false,
        height: 220,
        width: 400,
        modal: true,
        buttons: {
            "Grabar": function() {
                var dddd = $(this);

                if ($('#f_createBranch').valid() == true) {
                    if ($("#i_create_phone").val() == '')
                        $("#i_create_phone").val("0");
                    $.ajax({
                        url: 'branchpp/branchCreate.html',
                        data: $('#f_createBranch').serialize(),
                        dataType: 'json',
                        success: function(data) {
                            if (data) {
                                oTable.fnClearTable();
                                $.each(data, function(i, item) {
                                    oTable.fnAddData([
                                        item.branchId,
                                        "<p>" + item.name + "<strong style='visibility: hidden;'>" + item.branchId + "</p>",
                                        "<p>" + item.address + "</p>",
                                        "<p>" + item.phone + "</p>"
                                    ]);

                                });
                                oTable.fnDraw();
                                selectTable();
                                $("#textSearch").val("");
                                dddd.dialog('close');
                            }
                            else {
                                if ($('#pErrorMensaje').html() == '')
                                    $('#pErrorMensaje').append('No se puede crear los datos de la tienda');
                            }
                        }

                    });
                }
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        }
    });


    $('#bBranchEdit').click(function() {
        $('#dialogUpdateBranch').dialog('open');
        $('#icriteria').val($('#textSearch').val());
        $('#iname').val($('#hdname').val());
        $('#icatId').val($('#hdid').val());
        $('#iaddress').val($('#hdaddress').val());
        $('#iphone').val($('#hdphone').val());
        $('#pErrorMensaje').html('');
    });

    $('#bCreateBranch').click(function() {
        $('#divBranchCreate').dialog('open');
    });

    var validatorBranch = $("#f_createBranch").validate({
        rules: {
            i_create_name: {
                required: true,
                remote: 'branchpp/branchValidName.html'
            },
            i_create_address: {
                required: true
            },
            i_create_phone: {
                digits: true,
                range: [4000000, 4999999]
            }
        },
        messages: {
            i_create_name: {
                required: 'No puede estar vacio.',
                remote: 'Nombre ya existe.'
            },
            i_create_address: 'No puede estar vacio.',
            i_create_phone: {
                digits: 'Solo digitos',
                range: 'Rango no permitido'
            }
        },
        submitHandler: function() {

        }
    });

    var validatorBranch = $("#f_edit_branch").validate({
        rules: {
            iname: {
                required: true,
                remote: {
                    url: 'branchpp/branchValidNameUpdate.html',
                    type: 'get',
                    data: {
                        iname: function() {
                            return $('#iname').val();
                        },
                        branchId: function() {
                            return $('#icatId').val();
                        }
                    }
                }
            },
            iaddress: {
                required: true
            },
            iphone: {
                digits: true,
                range: [4000000, 4999999]
            }
        },
        messages: {
            i_create_name: {
                required: 'No puede estar vacio.',
                remote: 'Nombre ya existe.'
            },
            i_create_address: 'No puede estar vacio.',
            i_create_phone: {
                digits: 'Solo digitos',
                range: 'Rango no permitido'
            }
        },
        submitHandler: function() {

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

function selectTable() {
    $("#listBranch tbody tr").click(function(e) {
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
            $('#hdid').val('');
            $('#stnames').html('');
            $('#hdname').val('');
            $('#hdaddres').val('');
            $('#hdphone').val('');
        }
        else {
            //alert($(this).children().next().children().html()+" || "+$(this).children().next().next().children().html());
            //alert($(this).children().children().children().html());
            $('#hdid').val($(this).children().children().children().html());
            $('#stnames').html($(this).children().html());
            var nameSplit = $(this).children().children().html().split('<strong style="visibility: hidden;">', 1);
            $('#hdname').val(nameSplit[0]);
            $('#hdaddress').val($(this).children().next().children().html());
            $('#hdphone').val($(this).children().next().next().children().html());
            $('#bBranchEdit').button('option', 'disabled', false);
            $('#bBranchDelete').button('option', 'disabled', false);
            oTable.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
        }
    });
}

function createBranch() {
    $("#f_createBranch")[0].reset();
    $('#divBranchCreate').dialog('open');
}