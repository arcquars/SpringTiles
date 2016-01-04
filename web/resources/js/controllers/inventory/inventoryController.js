$(function() {
    $(".mbutton").button();
    $("#criteria").selectmenu({
        change: function( event, ui ) {
            var select = parseInt($('#criteria option:selected').val());
            if (select === 3) {
                $('#category').css("display", "inline");
                $( "#category" ).selectmenu();
                $('#textSearch').css("display", "none");
            } else {
                $( "#category" ).selectmenu();
                $( "#category" ).selectmenu( "destroy" );
                $('#category').css("display", "none");
                $('#textSearch').css("display", "inline");
            }
        }
    });
            
    $("a", ".demo").click(function() {
        return false;
    });
    $("input[type=submit], button").button();
    
    $( "#cssmenu ul li" ).each(function( index ) {
        $(this).removeClass("active");
    });
    $("#cssmenu ul li").first().addClass("active");
});
var dialogCreateCategory;
var validatorProvider;
var validateCreateProduct;
var dialogCreateFactory;
var oTable;
var myProduct = new Array(10);
$(document).ready(function() {
    if ($('#criteria option:selected').val() == 3) {
        $('#category').css("display", "inline");
        $( "#category" ).selectmenu();
        $('#textSearch').css("display", "none");
    }

    oTable = $('#listPro').dataTable({
        "bFilter": false,
        "bLengthChange": false

    });

    oTable.$('tr').click(function() {
        $(this).toggleClass('row_selected');
    });

    enablePayment();
    changeRadio();
    calculeTotal();


});

function enablePayment() {
    $('#trPayment').hide();
}

function changeRadio() {
    $("input[type='radio']").change(function() {
        if ($('#r_spot').is(':checked'))
            $('#trPayment').hide();
        else
            $('#trPayment').show();
    });
}

function calculeTotal() {
    $('#fp_i_amount').change(function() {
        if ($('#fp_i_amount').val() != '' && $('#fp_i_priceunit').val() != '') {
            var amount = parseFloat($('#fp_i_amount').val());
            var priceunit = parseFloat($('#fp_i_priceunit').val());
            var total = amount * priceunit;
            if (!isNaN(total))
                $('#fp_i_pricetotal').val(total.toFixed(2));
            else
                $('#fp_i_pricetotal').val('');
        } else {
            $('#fp_i_pricetotal').val('');
        }
    });
    $('#fp_i_priceunit').change(function() {
        if ($('#fp_i_amount').val() != '' && $('#fp_i_priceunit').val() != '') {
            var amount = parseFloat($('#fp_i_amount').val());
            var priceunit = parseFloat($('#fp_i_priceunit').val());
            var total = amount * priceunit;
            if (!isNaN(total))
                $('#fp_i_pricetotal').val(total.toFixed(2));
            else
                $('#fp_i_pricetotal').val('');
        } else {
            $('#fp_i_pricetotal').val('');
        }
    });

}

function fnGetSelected(oTable) {
    var aReturn = new Array();
    var aTrs = oTable.fnGetNodes();
    for (var i = 0; i < aTrs.length; i++) {
        if ($(aTrs[i]).hasClass('row_selected')) {
            //alert('pepepep:::'+$(aTrs[i]).html());
            aReturn.push(aTrs[i]);
        }
    }
    return aReturn;
}

function fnViewSelect() {
    var anSelect = fnGetSelected(oTable);
    var ids = '';
    for (var i = 0; i < anSelect.length; i++) {
        var iRow = anSelect[i];
        ids = ids + $(iRow).children().children().children().html() + ',';
    }
    if (anSelect.length > 0) {
        window.location = "inventorypp/createBuy.html?ids=" + ids;
    } else {
        $('#pError').html('');
        $('#pError').html('No hay Productos seleccionados para comprar');
        $('#pError').parent().css("visibility", 'visible');
    }

}

function fnRedirectDeliveryBranch() {
    var anSelect = fnGetSelected(oTable);
    var ids = '';
    var notCero = true;
    var itemNameCero = "";
    for (var i = 0; i < anSelect.length; i++) {
        var iRow = anSelect[i];
        var ceroIs = parseFloat($(iRow).children().next().next().next().next().html());
        if (ceroIs == 0) {
            notCero = false;
            var slipt = $(iRow).children().children().html().split('<', 1);
            itemNameCero = slipt[0];
            break;
        }
        ids = ids + $(iRow).children().children().children().html() + ',';
    }
    if (anSelect.length > 0 && notCero == true) {
        window.location = "inventorypp/deliveryBranch.html?ids=" + ids;
    } else {
        $('#pError').html('');
        if (anSelect.length <= 0)
            $('#pError').html('No hay Productos seleccionados para entregar');
        if (notCero == false)
            $('#pError').html('El producto <strong style="font-size:13px;">' + itemNameCero + '</strong> tiene 0 cantidad para la Entrega.');
        $('#pError').parent().css("visibility", 'visible');
    }
}

function fnViewSelectForSaleInventory() {
    var anSelect = fnGetSelected(oTable);
    var ids = '';
    var notCero = true;
    var itemNameCero = "";
    for (var i = 0; i < anSelect.length; i++) {
        var iRow = anSelect[i];
        var ceroIs = parseFloat($(iRow).children().next().next().next().next().html());
        if (ceroIs == 0) {
            notCero = false;
            var slipt = $(iRow).children().children().html().split('<', 1);
            itemNameCero = slipt[0];
            break;
        }
        ids = ids + $(iRow).children().children().children().html() + ',';
    }

    if (anSelect.length > 0 && notCero == true) {
        window.location = "inventorypp/createSale.html?ids=" + ids + "&branchId=0";
    } else {
        $('#pError').html('');
        if (anSelect.length <= 0)
            $('#pError').html('No hay Productos seleccionados para venta.');
        if (notCero == false)
            $('#pError').html('El producto <strong style="font-size:13px;">' + itemNameCero + '</strong> tiene 0 cantidad para la Venta.');
        $('#pError').parent().css("visibility", 'visible');
    }
}

function fnViewSelectForReserveInventory() {
    var anSelect = fnGetSelected(oTable);
    var ids = '';
    var notCero = true;
    var itemNameCero = "";
    for (var i = 0; i < anSelect.length; i++) {
        var iRow = anSelect[i];
        var ceroIs = parseFloat($(iRow).children().next().next().next().next().html());
        if (ceroIs == 0) {
            notCero = false;
            var slipt = $(iRow).children().children().html().split('<', 1);
            itemNameCero = slipt[0];
            break;
        }
        ids = ids + $(iRow).children().children().children().html() + ',';
    }

    if (anSelect.length > 0 && notCero == true) {
        window.location = "inventorypp/createReserve.html?ids=" + ids + "&branchId=0";
    } else {
        $('#pError').html('');
        if (anSelect.length <= 0)
            $('#pError').html('No hay Productos seleccionados para venta.');
        if (notCero == false)
            $('#pError').html('El producto <strong style="font-size:13px;">' + itemNameCero + '</strong> tiene 0 cantidad para la Venta.');
        $('#pError').parent().css("visibility", 'visible');
    }
}

function printInventory() {
    $("#printTable > tbody > tr").remove();
    getRecordProduct();
    $("#printSale").dialog({
        resizable: false,
        modal: true,
        draggable: false,
        width: 500,
        height: 200,
        buttons: [
            {
                text: 'Imprimir', click: function() {
                    
                    var print = "div.PrintAreaInventory";
                    $(print).printArea({
                        extraCss: "resources/styles/print1.css"
                    });
                   $(this).dialog("close"); 
                    
                }
            }
        ]

    });
}

function getRecordProduct() {
    var criteria = $("#criteria").val();
    var category = $("#category").val();
    var textSearch = $("#textSearch").val();
    $.ajax({
        url: contextPath + "/inventorypp/recordProductAjax.html",
        data: {"criteria": criteria, "category": category, "textSearch": textSearch},
        type: 'POST',
        dataType: 'json',
        success: function(data) {
            if(data != null){
                $.each(data, function(i, item) {
                    $("#printTable tbody:last").append("<tr><td>" +
                            item.productName + "</td><td>" +
                            item.codOrigin + "</td><td>" +
                            item.categoryName + "</td><td>" +
                            item.factoryName + "</td><td>" +
                            item.total + "</td></tr>");
                });
            }else{
                window.location = contextPath;
            }
        }
    });
}

function printElem(options){
    $("#printSaleEject").printElement(options);
}

function exportExcel(){
    var type = $("#criteria").val();
    var criteria = null;
    if(type == "3"){
        criteria = $("#category").val();
    }else{
        criteria = $("#textSearch").val();
    }
    window.open(contextServices+"recordProduct/findByCriteriaForExport?type="+type+"&criteria="+criteria, "_newtab");
}