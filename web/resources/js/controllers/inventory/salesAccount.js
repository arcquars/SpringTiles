$(function () {
//    $("#branchId").val(0);
//    $("#branchId").selectmenu({
//        change: function( event, ui ) {
//            changeBranch(this);
//        }
//    });

    $('#tSalesAccount').dataTable({
        "paging": true,
        "iDisplayLength": 5,
        //"ordering": false,
        "order": [[0, "desc"]],
        "info": false
    });

    $('#tSalesReserve').dataTable({
        "paging": true,
        "iDisplayLength": 5,
        "order": [[0, "desc"]],
        "info": false
    });

    $('#tSalesReserveClose').dataTable({
        "paging": true,
        "iDisplayLength": 5,
        "order": [[0, "desc"]],
        "info": false
    });

    $('#tSalesCredit').dataTable({
        "paging": true,
        "iDisplayLength": 5,
        "order": [[0, "desc"]],
        "info": false
    });

    $("#tabs").tabs();

    $("#cssmenu ul li").each(function (index) {
        $(this).removeClass("active");
    });

    var ss = $("#cssmenu ul")[3];
    $(ss).parent().addClass("active");

//    $("#i_to").val(moment().format("YYYY-MM-DD"));
});

function validAmountSave() {
    var isValid = true;
    if ($('#branchId').val() === '-1') {
        $('#pErrorConforme').append('Seleccione una sucursal!</br>');
        isValid = false;
    } else {
        $('#pErrorConforme').empty();
    }
    var htotal = parseFloat($('#ihTotal').val());
    if (htotal > 0) {
        $('#pErrorConforme').empty();
    } else {
        $('#pErrorConforme').append('No hay dinero a recoger!');
        isValid = false;
    }
    return isValid
}

function ejectAgreed() {
    if (validAmountSave()) {
        $('#dialogDateStart').append($('#i_from').val());
        $('#dialogDateEnd').append($('#i_to').val());
        $('#dialogBranchName').empty();
        $('#dialogBranchName').append($('#pBranchId').html());

        $('#dDialogAmount').dialog({
            resizable: false,
            draggable: false,
            width: 350,
            modal: true,
            buttons: {
                "Grabar": function () {
                    //saveSalesAccount();
                    $('#dDialogAmount form').submit();
                    $(this).dialog("close");
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    }
}

function saveSalesAccount() {
    var branchID = $('#ihBranchId').val();
    var amount = $('#ihTotal').val();
    var dateStart = $('#i_from').val();
    var dateEnd = $('#i_to').val();
    $.ajax({
        url: contextPath + "/inventorypp/saleAmountSaveAjax.html",
        data: {"branchId": branchID, "amount": amount, "dateStart": dateStart, "dateEnd": dateEnd},
        type: 'POST',
        success: function (data) {
            if (data !== "") {
                alert('sssssssss:: ' + data);
            } else {
                window.location = contextPath;
            }
        }
    });
}

function changeBranch(select) {
    if ($(select).val() === '-1') {
        $('#i_from').val('');
        $('#i_to').val('');
    } else {
        $.ajax({
            url: contextPath + "/inventorypp/getDatesSalesAccoutnByBranchId.html",
            data: {"branchId": $(select).val()},
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                $('#i_from').val(data[0]);
                $('#i_to').val(data[1]);
            },
            error: function (data, textStatus, jqXHR) {
                window.location = contextPath;
            }
        });
    }

}

function setDates(select) {
    $.ajax({
        url: contextServices + "sale/lastSaleAmountDateByBranchId",
        data: {"branchId": $(select).val()},
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            $('#i_from').val(data[0].value);
            $('#i_to').val(data[1].value);
        },
        error: function (data, textStatus, jqXHR) {
            alert(data);
        }
    });
}