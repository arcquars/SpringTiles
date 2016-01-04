var ciValid;
$(function () {
    $(".mbutton").button();
    $("a", ".demo").click(function () {
        return false;
    });
    /*
     $('#datepicker').datepicker({
     showOn: 'button',
     buttonImage: '../resources/images/calendar.gif',
     buttonImageOnly: true,
     dateFormat: 'yy-mm-dd',
     minDate: -7,
     maxDate: +7
     });
     */
});
$(document).ready(function () {

    ciValid = $("#formCreateSale").validate({
        rules: {
            "clienId": {
                required: true,
                number: true,
                min: 0
            },
            "namePerson": {
                required: true,
            }
        },
        messages: {
            "clienId": {
                required: 'Ci requerido',
                number: 'Solo numeros',
                min: 'Numeros mayores a 0'
            },
            "namePerson": {
                required: 'Razon Social Requerida',
            }
        }
    });

    validateCreatePerson();

});

function validNumCi(ci) {
    var validCi = false;
    if (!isNaN(ci) && isNumber(ci)) {
        var dd = parseInt(ci);
        if (dd > 0) {
            validCi = true;
        }
    }
    return validCi;
}

function getFirstName(ci) {
    if (validNumCi($(ci).val())) {
        $.ajax({
            url: contextPath + "/clientaa/getClientAjax.html",
            data: {"ci": $(ci).val()},
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                if (data != null) {
                    $("#namePersonId").val(data.razonSocial);
                    $("#printDireccion").empty();
                    $("#printDireccion").append(data.address);
                    $("#printTele").empty();
                    $("#printTele").append(data.phoneAddress);
                } else {
                    $("#namePersonId").val('');
                    $("#printDireccion").empty();
                    $("#dialogClient").dialog("open");
                    $("#client_nit").val($("#ciId").val());
                    $('#p_ci').val($('#ciId').val());
                }
            }
        });
    }
}
function validAmount(amount) {
    var amountFloat = parseInt($(amount).val());
    $(amount).val(amountFloat);
    var priceFloat = parseFloat($(amount).parent().next().children().val());
    var qlimt = parseFloat($(amount).parent().prev().prev().children().val());
    var pricemax = parseFloat($(amount).parent().prev().children().val());
    if (!isNaN(amountFloat) && !isNaN(priceFloat) && amountFloat > 0 && priceFloat >= 0) {
        if (amountFloat <= qlimt) {
            var rr = amountFloat * priceFloat;
            $(amount).parent().next().next().children().val(rr.toFixed(2));
            $(amount).parent().next().next().children().change();
        } else {
            $(amount).val('1');
            $(amount).parent().next().children().val(pricemax);
            $(amount).parent().next().next().children().val(pricemax);
            $(amount).parent().next().next().children().change();
            alert('solo puede vender ' + qlimt + ' unidades!!');
        }
    } else {
        $(amount).val('1');
        $(amount).parent().next().children().val(pricemax);
        $(amount).parent().next().next().children().val(pricemax);
        $(amount).parent().next().next().children().change();
    }
}

function validPrice(price) {
    var priceFloat = parseFloat($(price).val());
    priceFloat = Math.round(priceFloat * 100) / 100;
    $(price).val(priceFloat);
    var amountFloat = parseFloat($(price).parent().prev().children().val());
    var pricemax = parseFloat($(price).parent().prev().prev().children().val());
    var qlimt = parseFloat($(price).parent().prev().prev().prev().children().val());

    if (!isNaN(amountFloat) && !isNaN(priceFloat) && amountFloat > 0 && priceFloat >= 0) {
        if (priceFloat >= pricemax) {
            var rr = amountFloat * priceFloat;
            $(price).parent().next().children().val(rr.toFixed(2));
            $(price).parent().next().children().change();
        } else {
            $(price).val(pricemax);
            $(price).parent().prev().children().val('1');
            $(price).parent().next().children().val(pricemax);
            $(price).parent().next().children().change();
            alert('Precio minimo de venta ' + pricemax + ' !!');
        }
    } else {
        $(price).val(pricemax);
        $(price).parent().prev().children().val('1');
        $(price).parent().next().children().val(pricemax);
        $(price).parent().next().children().change();
    }

}
function trTotal() {
    var trses = $('#t_product tr');
    var totalS = 0;
    for (var i = 1; i < $(trses).length; i++) {
        var hijo = $($(trses)[i]).children().next().next().next().next().next().next().next().next().children();

        totalS = totalS + parseFloat($(hijo).val());
    }

    $('#totalBuy').val(totalS.toFixed(2));
}

function redirectInventory() {
    if ($("#branchId").val() == "0")
        window.location = contextPath + "/inventorypp.html";
    else {
        window.location = contextPath + "/inventorypp/inventoryBranch.html";
    }
}

function createPerson() {
    $.ajax({
        url: contextPath + "/inventorypp/createPersonAjax.html",
        type: 'POST',
        data: $("#f_createPeson").serialize(),
        success: function (data) {
            $("#namePersonId").val(data);
        }
    })
}

function validateCreatePerson() {
    $("#f_createPeson").validate({
        rules: {
            fp_i_ci: {
                required: true,
                digits: true,
                remote: {
                    url: contextPath + "/inventorypp/validateCiAjax.html",
                    type: "POST"
                }
            },
            p_firstname: "required"
        },
        messages: {
            fp_i_ci: {
                required: "Ci no puede estar en blanco.",
                digits: "Solo numeros.",
                remote: "Ci esta registrado."
            }
        }
    });
}

function isNumber(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function saveAndPrint() {
    if ($("#formCreateSale").valid()) {
        $.ajax({
            url: contextPath + "/inventorypp/saveSaleAjax.html",
            data: {"jsonSale": getJsonSale()},
            type: 'POST',
            success: function (data) {
                var obj = jQuery.parseJSON(data);
//                alert(obj.nit);
                $("#printSale").dialog({
                    resizable: false,
                    modal: true,
                    draggable: false,
                    closeOnEscape: false,
                    dialogClass: "no-close",
                    width: 450,
                    buttons: {
                        "Imprimir": function () {
                            saveSaleAjax(this);
                        },
                        "Salir": function () {
                            redirectInventory();
                        }
                    }

                });
                $("#prinDate").text($("#datepicker").val());
                $("#printCi").text(obj.nit);
                $("#printFirstname").text(obj.razonSocial);
                $("#printTele").empty();
                $("#printTele").text(obj.phone);
                $("#printDireccion").empty();
                $("#printDireccion").text(obj.address);
                $("#printTable tbody tr").remove();
                $("#sTotal").text('0');
                $("#sTotalLiteral").text(obj.literal);
                $("#zoneClient").text(obj.zone);
                $("#printFacturaA").text(obj.printFacturaA);
                $("#prinNumSale").empty();
                $("#prinNumSale").text(obj.salId);
                $("#printNameVendor").empty();
                $("#printNameVendor").text($("#vendorId option:selected").text());
                $("#t_product tbody tr").each(function () {
                    getTrData(this);
                    getTrTotal(this);
                });
            }
        });

    }
}

function proformaPrint() {
    if ($("#formCreateSale").valid()) {
        var clientId = $("#ciId").val();
        $.ajax({
            url: contextServices+"client/getClientById",
            data: {"id": clientId},
            type: 'GET',
            dataType: 'json',
            success: function (data) {
//                var obj = jQuery.parseJSON(data);
//                alert(obj.nit);
                $("#title_impri").text("Pro Forma");
                $("#printSale").dialog({
                    resizable: false,
                    modal: true,
                    draggable: false,
                    closeOnEscape: false,
                    dialogClass: "no-close",
                    width: 450,
                    buttons: {
                        "Imprimir": function () {
                            saveSaleAjax(this);
                        },
                        "Salir": function () {
                            redirectInventory();
                        }
                    }

                });
                $("#prinDate").text($("#datepicker").val());
                $("#printCi").text(data.nit);
                $("#printFirstname").text(data.razonSocial);
                $("#printTele").empty();
                $("#printTele").text(data.phone);
                $("#printDireccion").empty();
                $("#printDireccion").text(data.address);
                $("#printTable tbody tr").remove();
                $("#sTotal").text('0');
                $("#sTotalLiteral").text("");
                $("#zoneClient").text(data.zone);
                $("#printFacturaA").text("");
                $("#prinNumSale").empty();
                $("#prinNumSale").text("");
                $("#printNameVendor").empty();
                $("#printNameVendor").text($("#vendorId option:selected").text());
                $("#t_product tbody tr").each(function () {
                    getTrData(this);
                    getTrTotal(this);
                });
            }
        });
    }
}

function getTrData(tr) {
    var name = $(tr).children().children().val();
    var description = $(tr).children().next().next().children().val();
    var codigo = $(tr).children().next().children().val();
    var amount = $(tr).children().next().next().next().next().next().next().children().val();
    var priceSale = $(tr).children().next().next().next().next().next().next().next().children().val();
    var total = amount * priceSale;
    $("#printTable tbody:last").append("<tr><td style=' font-size: 10px;'>" +
            codigo + "</td><td style=' font-size: 10px;'>" +
            name + " - " + description + "</td><td style=' font-size: 10px; text-align: right; '>" +
            amount + "</td><td style='text-align: right; font-size: 10px;'>" +
            priceSale + "</td><td style='text-align: right;  font-size: 10px;'>" +
            total.toFixed(2) + "</td></tr>");

}
function getTrTotal(tr) {
    var priceTotal = $(tr).children().next().next().next().next().next().next().next().next().children().val();
    var total = $("#sTotal").text();
    var subtotal = 0;
    //var total = 0;
    if (!isNaN(parseFloat(total)) && isFinite(total)) {
        //total = parseFloat(total) + parseFloat(priceTotal);

        subtotal = Math.round(parseFloat(priceTotal) * 100) / 100;
        $("#sTotal").text(Math.round((parseFloat(total) + subtotal) * 100) / 100);
    } else {
        $("#sTotal").text('0');
    }
}

function clearFieldPrint() {
    $("#prinDate").text('');
    $("#printCi").text('0');
    $("#sTotal").text('0');
    $("#printFirstname").text('');
    $("#printTable tbody tr").remove();
}

function ItemsCollection(items) {
    this.items = items || [];
}

ItemsCollection.prototype.myfunction = function () {
    alert(this.items.length);
};

function ProductSale() {
    this.branchId = "";
    this.userId = "";
    this.dateSale = "";
    this.listProductSale = [];
    this.total = 0;
    this.clientId = "";
    this.credit = false;
}

function Product() {
    this.productId = 0;
    this.productName = "";
    this.productCodOrigin = "";
    this.categoryName = "";
    this.fatoryName = "";
    this.higherCost = 0;
    this.limitedAmount = 0;
    this.amount = 0;
    this.price = 0;
    this.total = 0;
}

ProductSale.prototype.setItems = function (items) {
    this.listProductSale = items;
}

function saveSaleAjax(dSavePrintSale) {
    var print = "div.PrintArea.both";
    $(print).printArea({
    });
    clearFieldPrint();
    //$("#iDialogHidden").val(data);
    $(dSavePrintSale).dialog("close");
    redirectInventory();
}

function getJsonSale() {
    var productSale = new ProductSale();
    productSale.branchId = $("#branchId").val();
    productSale.userId = 0;
    productSale.dateSale = $("#datepicker").val();
    productSale.total = $("#totalBuy").val();
    productSale.clientId = $("#ciId").val();
    productSale.credit = $('#creditC').is(":checked");

    var pSs = new Array();

    $("#t_product > tbody > tr").each(function (i, item) {
        var pS = new Product();
        pS.productId = $(item).children().children().next().val();
        pS.productName = $(item).children().children().val();
        pS.productCodOrigin = $(item).children().next().children().val();
        pS.categoryName = $(item).children().next().next().children().val();
        pS.fatoryName = $(item).children().next().next().next().children().val();
        pS.higherCost = $(item).children().next().next().next().next().next().children().val();
        pS.limitedAmount = $(item).children().next().next().next().next().children().val();
        pS.amount = $(item).children().next().next().next().next().next().next().children().val();
        pS.price = $(item).children().next().next().next().next().next().next().next().children().val();
        pS.total = $(item).children().next().next().next().next().next().next().next().next().children().val();

        pSs.push(pS);
    });

    productSale.setItems(pSs);

    return JSON.stringify(productSale);
}

function dialogRedirect() {
    $("#dialogRedirect").dialog({
        resizable: false,
        modal: true,
        draggable: false,
        width: 200,
        buttons: [
            {
                text: 'Redirecionar', click: function () {
                    redirectInventory();
                }
            }
        ]

    });
}