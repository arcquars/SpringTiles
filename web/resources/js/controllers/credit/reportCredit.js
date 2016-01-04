$(document).ready(function() {
    //$("#fReportCredit_provider").selectmenu();
    //$( "#fdradio" ).buttonset();
    $("#f_dateIni").datepicker({
        defaultDate: "-1w",
        dateFormat: 'yy-mm-dd',
        changeMonth: true,
        onClose: function(selectedDate) {
            $("#f_dateEnd").datepicker("option", "minDate", selectedDate);
        }
    });
    $("#f_dateEnd").datepicker({
        defaultDate: "+1w",
        dateFormat: 'yy-mm-dd',
        changeMonth: true,
        onClose: function(selectedDate) {
            $("#f_dateIni").datepicker("option", "maxDate", selectedDate);
        }
    });

    urlReloadTableCredit = contextPath + "/creditpp/getListBuyCredit.html?" + $("#f_reportCredit").serialize();
    oTableCredit = $('#t_reportCredit').dataTable({
        "bFilter": false,
        "bLengthChange": false,
        "aoColumnDefs": [
            {
                "asSorting": ["asc"],
                "aTargets": [1]
            }
        ],
        "bProcessing": true,
        "sAjaxSource": urlReloadTableCredit,
        "aoColumns": [
            {"mData": "nameProvider"},
            {"mData": "dateBuy"},
            {"mData": "total"},
            {"mData": "balance"},
            {"mData": "action"}
        ]
    });

    $('#dialog-payment').dialog({
        autoOpen: false,
        title: 'Pagos Realizados',
        modal: true,
        width: 400,
        buttons: {
            Cerrar: function() {
                $(this).dialog("close");
            }
        }
    });
    $('#dialog-buy').dialog({
        autoOpen: false,
        title: 'Detalle de Compra',
        modal: true,
        width: 600,
        buttons: {
            Cerrar: function() {
                $(this).dialog("close");
            }
        }
    });
});

function fnsearchBuyCredit() {
    fnValidateSearch();
    if ($("#f_reportCredit").valid()) {
        var jqx = $.ajax({
            type: 'GET',
            url: contextPath + "/loginaa/sessionValid.html",
            dataType: "html"
        });
        jqx.done(function(msg) {
            if (msg !== "true") {
                window.location = contextPath;
            }
        });
        urlReloadTableCredit = contextPath + "/creditpp/getListBuyCredit.html?" + $("#f_reportCredit").serialize();
        oTableCredit.fnReloadAjax(urlReloadTableCredit);
    }
}

function fnValidateSearch() {
    $("#f_reportCredit").validate({
        rules: {
            fReportCredit_provider: {
                required: true
            },
            f_dateIni: {
                required: true
            },
            f_dateEnd: {
                required: true
            },
        },
        messages: {
            fReportCredit_provider: {
                required: 'Eliga un proveedor.',
            },
            f_dateIni: {
                required: 'Seleccione fecha de Inicio'
            },
            f_dateEnd: {
                required: 'Seleccione fecha de Fin'
            },
        }
    });
}

function detailPayment(creditId) {
    clearDialogPayments();
    $.ajax({
        url: contextPath+'/creditpp/getListPaymentAjax.html',
        type: 'POST',
        dataType: 'json',
        data: {
            creditId: creditId
        },
        success: function(data) {
            $('#detailPaymentProvider').text(data.providerName);
            $('#detailPaymentDateBuy').text(data.dateBuy);
            $('#detailPaymentTotalP').text(data.totalPayment);
            $('#detailPaymentTotalBuy').text(data.totalBuy);
            
            var buyT = parseFloat(data.totalBuy);
            var paymentT = parseFloat(data.totalPayment)
            $('#detailPaymentBalance').text(buyT-paymentT);
            var trs = "";
            $.each(data.list, function(index, element){
                trs += "<tr>";
                trs += "<td>"+element.datePayment+"</td>";
                trs += "<td style='text-align: right;'>"+element.amount+"</td>";
                trs += "</tr>";
            });
            $('#detailPaymentTable > tbody').append(trs);
        }

    });
    $('#dialog-payment').dialog("open");
}

function clearDialogPayments(){
    $('#detailPaymentProvider').empty();
    $('#detailPaymentDateBuy').empty();
    $('#detailPaymentTotalP').empty();
    $('#detailPaymentTotalBuy').empty();
    $('#detailPaymentBalance').empty();
    $('#detailPaymentTable > tbody > tr').remove();
}

function detailBuy(creditId){
    $.ajax({
        url: contextPath+'/buypp/getListBuyProductByCreditId.html',
        type: 'POST',
        dataType: 'json',
        data: {
            creditId: creditId
        },
        success: function(data) {
            clearDialogBuy();
            $('#detailBuyProvider').text(data.providerName);
            $('#detailBuyData').text(data.dateBuy);
            $('#detailBuyTotal').text(data.totalBuy);
            
            var trs = "";
            $.each(data.lisRpd, function(index, element){
                trs += "<tr class='border bottom'>";
                trs += "<td class='border bottom'>"+element.productName+"</td>";
                trs += "<td class='border'>"+element.codOrigin+"</td>";
                trs += "<td class='border'>"+element.categoryName+"</td>";
                trs += "<td class='border'>"+element.factoryName+"</td>";
                trs += "<td class='border' style='text-align: right;'>"+element.amount+"</td>";
                trs += "<td class='border' style='text-align: right;'>"+element.price+"</td>";
                trs += "<td class='border' style='text-align: right;'>"+element.total+"</td>";
                trs += "</tr>";
            });
            $('#detailBuyTable > tbody').append(trs);
        }
    });
    $('#dialog-buy').dialog("open");
}

function clearDialogBuy(){
    $('#detailBuyProvider').empty();
    $('#detailBuyData').empty();
    $('#detailBuyTotal').empty();
    $('#detailBuyTable > tbody > tr').remove();
}