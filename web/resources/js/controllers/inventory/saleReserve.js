var oTableReserve;

$(document).ready(function() {
    $("#s_branchId").selectmenu();
    $(".b_reserve_table").button();
    var myDate = new Date();
    var month = myDate.getMonth() + 1;
    var prettyDate = myDate.getFullYear() + '-' + month + '-' + myDate.getDate();
    $('#i_dateReserveSale').val(prettyDate);

    $("#i_dateReserveSale").datepicker({
        dateFormat: 'yy-mm-dd',
        setDate: new Date()
    });

    var urlReloadTableReserve = contextPath + "/inventorypp/saleReserveListAjax.html?branchId=" + $('#s_branchId').val() + "&reserveDate=" + $('#i_dateReserveSale').val();

    oTableReserve = $('#t_listreserve').dataTable({
        "bFilter": false,
        "bLengthChange": false,
        "aoColumnDefs": [
            {
                "asSorting": ["asc"],
                "aTargets": [0]
            }
        ],
        "language": languajeSpanish,
        "sAjaxSource": urlReloadTableReserve,
        "bProcessing": true,
        "aoColumns": [
            {"mData": "dateReserve"},
            {"mData": "branchName"},
            {"mData": "person"},
            {"mData": "onAccount"},
            {"mData": "debit"},
            {"mData": "total"},
            {"mData": "pagoId"}
        ]
    });

    $( "#cssmenu ul li" ).each(function( index ) {
        $(this).removeClass("active");
    });
    
    var ss = $( "#cssmenu ul" )[2];
    $(ss).parent().addClass("active");
    
});

function recordReserve(idReserve) {
    var jqx = $.ajax({
        type: 'GET',
        url: contextPath + "/loginaa/sessionValid.html",
        dataType: "html"
    });
    jqx.done(function(msg) {
        if (msg === "true") {
            clearDreserver();
            $.ajax({
                type: "POST",
                url: 'reserveDetailAjax.html',
                data: "reserveId=" + idReserve,
                dataType: 'json',
                success: function(data) {
                    $('#dReserveClientName').append(data.nameClient);
                    $('#dReserverCi').append(data.nameCi);
                    $('#dReserveDateReserve').append(data.dateReserve);
                    $('#dReserverPick').append(data.datePick);
                    $('#dReserverBranch').append(data.branchName);
                    $('#dReserverOnAccount').append(data.onAmount);
                    $('#dReserverDebit').append(data.debit);
                    $('#dReserverTotal').append(data.total);
                    var rowProduct = "";
                    $.each(data.listSaleDetail, function(idx, obj) {
                        rowProduct = "<tr style='border: 1px solid black !important;'>";
                        rowProduct += "<td style='border-top: 1px solid black;'>" + obj.nameItem + "</td>";
                        rowProduct += "<td style='border-left: 1px solid black; border-top: 1px solid black;'>" + obj.code + "</td>";
                        rowProduct += "<td style='border-left: 1px solid black; border-top: 1px solid black;'>" + obj.price + "</td>";
                        rowProduct += "<td style='text-align: right; border-left: 1px solid black; border-top: 1px solid black;'>" + obj.amount + "</td>";
                        rowProduct += "<td style='text-align: right; border-left: 1px solid black; border-top: 1px solid black;'>" + obj.total + "</td>";
                        rowProduct += "</tr>";
                        $('#dReserveTableProducts tbody').append(rowProduct);
                    });
                }

            });
            $("#dialogReserve").dialog({
                resizable: false,
                modal: true,
                draggable: false,
                width: 500,
                buttons: [
                    {text: 'Realizar pago', click: function() {
                            $.ajax({
                                type: 'POST',
                                url: "reserveSaveAjax.html",
                                data: {reserveId: idReserve}
                            }).done(function(msg) {
                                if (msg === "")
                                    window.location = contextPath;
                                else {
                                    $("#dialogReserve").dialog('close');
                                    oTableReserve.fnReloadAjax(contextPath + "/inventorypp/saleReserveListAjax.html?branchId=" + $('#s_branchId').val() + "&reserveDate=" + $('#i_dateReserveSale').val());
                                }
                            });

                        }},
                    {text: 'Realizar pago e imprimir', click: function() {
                            $("#dialogReserveContent1").printArea();
                            $.ajax({
                                type: 'POST',
                                url: "reserveSaveAjax.html",
                                data: {reserveId: idReserve}
                            }).done(function(msg) {
                                if (msg === "")
                                    window.location = contextPath;
                                else {
                                    $("#dialogReserve").dialog('close');
                                    oTableReserve.fnReloadAjax(contextPath + "/inventorypp/saleReserveListAjax.html?branchId=" + $('#s_branchId').val() + "&reserveDate=" + $('#i_dateReserveSale').val());
                                }
                            });
                        }},
                    {text: 'Cancelar', click: function() {
                            $(this).dialog('close');
                        }
                    }
                ]

            });

        } else {
            window.location = contextPath;
        }
    });
}


function clearDreserver() {
    $('#dReserveClientName').empty();
    $('#dReserverCi').empty();
    $('#dReserveDateReserve').empty();
    $('#dReserverPick').empty();
    $('#dReserverBranch').empty();
    $('#dReserverOnAccount').empty();
    $('#dReserverDebit').empty();
    $('#dReserverTotal').empty();
    $('#dReserveTableProducts tbody').empty();

}
function reloadTableReserve() {
    var jqx = $.ajax({
        type: 'GET',
        url: contextPath + "/loginaa/sessionValid.html",
        dataType: "html"
    });
    jqx.done(function(msg) {
        if (msg === "true") {
            oTableReserve.fnReloadAjax(contextPath + "/inventorypp/saleReserveListAjax.html?branchId=" + $('#s_branchId').val() + "&reserveDate=" + $('#i_dateReserveSale').val());
        } else {
            window.location = contextPath;
        }
    });
}