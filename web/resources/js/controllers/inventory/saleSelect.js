var oTable;
$(document).ready(function () {
    $("#branch").selectmenu();
    $("#buscar_s").button();

    $("#from").datepicker({
        minDate: -30,
        maxDate: "+1M +10D",
        defaultDate: "-5w",
        dateFormat: "yy-mm-dd",
        changeMonth: true,
        onClose: function (selectedDate) {
            $("#to").datepicker("option", "minDate", selectedDate);
        }
    });
    $("#to").datepicker({
        minDate: -20,
        maxDate: "+1D",
        dateFormat: "yy-mm-dd",
        changeMonth: true,
        onClose: function (selectedDate) {
            $("#from").datepicker("option", "maxDate", selectedDate);
        }
    });

    $("#to").val(moment().format("YYYY-MM-DD"));
    $("#from").val(moment().subtract(1, 'days').format("YYYY-MM-DD"));

    /* Init the table */
    var urlReloadTable = contextServices + "sale/listSale?dateStart=" + $('#from').val() + "&dateEnd=" + $('#to').val() + "&branchId=" + $('#branch').val();
    
    oTable = $('#listSale').DataTable({
        "bFilter": false,
        "bLengthChange": false,
        "sAjaxSource": urlReloadTable,
        "aoColumnDefs": [
            {"bSortable": false, "aTargets": [0]},
            {"asSorting": ["asc"], "aTargets": [1]}
        ],
        "aoColumns": [
            {"mData": "saleId"},
            {"mData": "createDate"},
            {"mData": "razonSocial"},
            {"sClass": "dt_td", "mData": function (obj) {
                    return showCollReserve(obj);
                }},
            {"mData": function (obj) {
                    return showCollCredit(obj);
                }},
            {"mData": function (obj) {
                    return showCollDetalle(obj);
                }},
            {"mData": "total"}
        ],
    });

    $("#detailSale").dialog({
        resizable: false,
        autoOpen: false,
        closeOnEscape: false,
        dialogClass: "no-close",
        width: 650,
        modal: true,
        buttons: {
            "Close": function () {
                $(this).dialog("close");
//                $("#createPersonDialog").dialog("close");
            }
        }
    });
    
    $("#detailReivoce").dialog({
        resizable: false,
        autoOpen: false,
        closeOnEscape: false,
        dialogClass: "no-close",
        width: 450,
        modal: true,
        buttons: {
            "Close": function () {
                $(this).dialog("close");
//                $("#createPersonDialog").dialog("close");
            }
        }
    });
});

function showCollReserve(sale) {
    var html = "";
    if (sale.reserve != 0) {
        html += "<a href='#' onclick='openDetailReserve(this); return false;' data-id='" + sale.reserve + "'><spam style='display:block; margin: 0 auto;' class='ui-icon ui-icon-arrow-4-diag'></spam></a>";
    }
    return html;
}

function showCollCredit(sale) {
    var html = "";
    if (sale.credit != 0) {
        if(sale.creditCanceled == true)
            html += "<spam class='ui-icon ui-icon-check cupertino_icons' style='display:block; margin: 0 auto;'></spam>";
        else
            html += "<a href='#' onclick='openDetailCredit(this); return false;' data-salid='" + sale.saleId + "' data-creditid='" + sale.credit + "'><spam class='ui-icon ui-icon-arrow-4-diag' style='display:block; margin: 0 auto;'></spam></a>";
    }
    return html;
}

function showCollDetalle(sale) {
    var html = "";
    html += "<a href='#' onclick='openDetailSale(this); return false;' data-id='" + sale.saleId + "'><spam class='ui-icon ui-icon-arrow-4-diag' style='display:block; margin: 0 auto;'></spam></a>";
    return html;
}

function openDetailSale(link) {
    clearDetailSale();
    setDetailSale($(link).data("id"));
    $("#detailSale").dialog('open');
}

function setDetailSale(salId) {
    $.ajax({
        url: contextServices + "sale/listSaleDto",
        data: {salId: salId},
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            $("#ds_razonSocial").append(data.clientRazonSocial);
            $("#ds_nit").append(data.clientNit);
            $("#ds_createDate").append(data.dateSale);
            $("#ds_vendorName").append(data.vendorName);
            $("#ds_total").append(data.total);
            if (data.credit !== 0){
                $('#ds_credit').prop('checked', true);
            }
            if (data.reserve !== 0)
                $('#ds_reserve').prop('checked', true);

            var trs = "";
            $.each(data.listProductSale, function (key, value) {
                trs += "<tr>";
                trs += "<td>" + value.productName + "</td>";
                trs += "<td>" + value.productCodOrigin + "</td>";
                trs += "<td>" + value.detail + "</td>";
                trs += "<td style='text-align: right;'>" + value.amount + "</td>";
                trs += "<td style='text-align: right;'>" + value.price + "</td>";
                trs += "<td style='text-align: right;'>" + value.total + "</td>";
                trs += "</tr>";
            });

            $("#ds_table tbody").append(trs);
        }
    });
}

function clearDetailSale() {
    $("#ds_razonSocial").empty();
    $("#ds_nit").empty();
    $("#ds_createDate").empty();
    $("#ds_vendorName").empty();
    $("#ds_total").empty();
    $('#ds_credit').prop('checked', false);
    $('#ds_reserve').prop('checked', false);
    $("#ds_table tbody").empty();
}

function reloadSearch(){
    var url = contextServices + "sale/listSale?dateStart=" + $('#from').val() + "&dateEnd=" + $('#to').val() + "&branchId=" + $('#branch').val();
    oTable.ajax.url(url).load();
}

function openDetailCredit(link) {
    var salId = $(link).data("salid");
    var creditId = $(link).data("creditid");
    $.ajax({
        url: contextServices + "sale/salePayments",
        data: {salId: salId, creditId: creditId},
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            $("#detailReivoce").html(tempSaleRevoiceReadOnly(data));

            $("#detailReivoce").dialog('open');
        }
    });

}