$(document).ready(function() {
    $("#i_saveAndPrint").click(function() {
        clickSaveeImprDelivery();
        //clickSaveeImprDelivery();
    });
});
function validAmount(input) {
    var totalAvailable = parseInt($(input).parent().prev().children().val());
    var amount = parseInt($(input).val());
    if (isNaN(amount)) {
        $(input).val('0');
        $(input).focus();
        alert('Solo numeros enteros!!');
    } else {
        if (totalAvailable < amount) {
            $(input).val('0');
            $(input).focus();
            alert('Cantidad de Entrega tiene que ser menor al total de productos en almacen!');
        }
        if (amount < 0) {
            $(input).val('0');
            $(input).focus();
            alert('Solo numeros positivos!');
        }
    }

}

function clickCancelDelivery() {
    window.location = contextPath + "/inventorypp.html";
}

function clickSaveeImprDelivery() {
    if (validDeliveriPrint()) {
        loadDivPrint();
        var options = {mode: 'iframe'};
        $("#d_deliveryProduct").printArea(options);
    }


}

function validDeliveriPrint() {
    $('#pError').empty();
    $('#pError').parent().css('visibility', 'visible')
    var isValid = true;
    if ($('#branchId').val() === '-1') {
        isValid = false;
        $('#pError').html('Seleccion una sucursal.');
    }
    isValid = validTableProduct(isValid);
    return isValid;
}

function validTableProduct(isValid) {
    var isValid1 = isValid;
    $("#t_product tr").each(function() {
        if ($(this).find("td").eq(5).children().val() === '0') {
            isValid1 = false;
            $('#pError').html('Entrega de producto no puede ser CERO.');
        }
    });
    return isValid1;
}

function loadDivPrint() {
    $('#d_deliveryProduct').css('display', 'block');
    $("#t_deliveryProductPrint tbody").empty();
    $("#t_deliveryProductPrint").addClass("table table-bordered");
    var trString = "";
    $("#t_product tr").each(function() {
        if ($(this).find("td").eq(1).html() !== undefined) {
            trString = "<tr style='border: 1px solid black;'>";
            trString += "<td>" + $(this).find("td").eq(0).children().val() + "</td>";
            trString += "<td>" + $(this).find("td").eq(1).children().val() + "</td>";
            trString += "<td>" + $(this).find("td").eq(2).children().val() + "</td>";
            trString += "<td>" + $(this).find("td").eq(3).children().val() + "</td>";
            trString += "<td>" + $(this).find("td").eq(4).children().val() + "</td>";
            trString += "<td>" + $(this).find("td").eq(5).children().val() + "</td>";
            trString += "</tr>";
            $("#t_deliveryProductPrint tbody").append(trString);
        }
    });

    $("#p_branch").empty();
    $("#p_branch").append("<b>Sucursal: </b>" + $("#branchId option:selected").text());
}