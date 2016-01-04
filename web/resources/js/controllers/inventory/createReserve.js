$(function() {
    $(".mbutton").button();
    $("a", ".demo").click(function() {
        return false;
    });
    /*
    $('#datepicker').datepicker({
        dateFormat: 'yy-mm-dd',
        minDate: -1,
        onClose: function(selectedDate) {
            $("#datepickerClose").datepicker("option", "minDate", selectedDate);
        }
    });
    */

    $( "#namePersonId" ).autocomplete({
      source: contextServices+"client/autocomplete",
      minLength: 2,
      select: function( event, ui ) {
          $("#clientId").val(ui.item.key);
        console.log( ui.item ?
          "Selected: " + ui.item.value + " aka " + $("#clientId").val() :
          "Nothing selected, input was " + this.value );
      }
    });
    
    $("#datepickerClose").datepicker({
        dateFormat: 'yy-mm-dd',
        minDate: +1, 
        maxDate: "+1M +10D",
        changeMonth: true,
        onClose: function(selectedDate) {
            $("#datepicker").datepicker("option", "maxDate", selectedDate);
        }
    });
});
$(document).ready(function() {
    $("#reserveSaveId").button("option", "disabled", true);

    $("#listProductReserve").validate({
        rules: {
            dateSale: "required",
            dateClose: "required",
            onAccount: {
                number: true,
                min: 1
            },
            perCi:{
                min: 1
            }
            
        },
        messages: {
            dateSale: "Fecha de venta requerido",
            dateClose: "Fecha de recojo requerido",
            onAccount: {
                number: "Solo numeros",
                min: "Fuera de rango"
            },
            perCi: {
                min: "Fuera de rango"
            }
        }
    });

    validateCreatePerson();
});
function validAmount(amount) {
    $(amount).val(parseInt($(amount).val()));
    var amountFloat = parseInt($(amount).val());
    var priceFloat = parseFloat($(amount).parent().next().children().val());
    var qlimt = parseFloat($(amount).parent().prev().prev().children().val());
    var pricemax = parseFloat($(amount).parent().prev().children().val());
    if (!isNaN(amountFloat) && !isNaN(priceFloat) && amountFloat > 0 && priceFloat >= 0) {
        if (amountFloat <= qlimt) {
            $(amount).parent().next().next().children().val(amountFloat * priceFloat);
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
    
    $('#onAccount').val("0");
}

function validPrice(price) {
    var priceFloat = parseFloat($(price).val());
    var amountFloat = parseFloat($(price).parent().prev().children().val());
    var pricemax = parseFloat($(price).parent().prev().prev().children().val());
    var qlimt = parseFloat($(price).parent().prev().prev().prev().children().val());

    if (!isNaN(amountFloat) && !isNaN(priceFloat) && amountFloat > 0 && priceFloat >= 0) {
        if (priceFloat >= pricemax) {
            $(price).parent().next().children().val(amountFloat * priceFloat);
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
    
    $('#onAccount').val("0");

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
    var branchId = $("#branchId").val();
    if(branchId == 0)
        window.location = "/SpringTiles/inventorypp.html";    
    else
        window.location = "/SpringTiles/inventorypp/inventoryBranch.html";
}

function changeResiduary(residuary) {
    var onAccount = parseFloat($(residuary).val());
    var granTotal = parseFloat($("#totalBuy").val());
    if (onAccount > 0 && onAccount < granTotal) {
        $("#residuaryId").val((granTotal - onAccount).toFixed(2));
        $("#reserveSaveId").button("option", "disabled", false);
    } else {
        alert("A cuenta fuera de rango.");
        $(residuary).val('0.0');
        $("#residuaryId").val('0.0');
        $("#reserveSaveId").button("option", "disabled", true);
    }
}

function getFirstName(ci) {
    var valid = true;
    var ciVal = 0;
    if (!isNumber($(ci).val())) {
        valid = false;
    } else {
        var ci1 = parseInt($(ci).val());
        if (ci1 <= 0)
            valid = false;
        else
            ciVal = $(ci).val();
    }
    if (valid) {
        $.ajax({
            url: contextPath + "/inventorypp/getPersonName.html",
            data: {"ci": ciVal},
            type: "post",
            success: function(data) {
                if (data != '') {
                    $("#namePersonId").val(data);
                } else {
                    $("#namePersonId").val('');
//                $("#p_ci").val($("#ciId").val());
//                $("#ciId").val('');
                    $("#createPersonDialog").dialog({
                        resizable: false,
                        modal: true,
                        draggable: false,
                        width: 230,
                        buttons: [
                            {
                                text: 'Grabar', click: function() {
                                    if ($("#f_createPeson").valid()) {
                                        createPerson();
                                        $(this).dialog('close');
                                    }
                                }
                            },
                            {
                                text: 'Cancelar', click: function() {
                                    $(this).dialog('close');
                                }
                            }
                        ]
                    });
                    $('#p_ci').val($('#ciId').val());
                    //$('#ciId').val(null);
                }
            }
        });
    }
}

function createPerson() {
    $.ajax({
        url: contextPath + "/inventorypp/createPersonAjax.html",
        type: 'POST',
        data: $("#f_createPeson").serialize(),
        success: function(data) {
            $("#namePersonId").val(data);
        }
    })
}

function isNumber(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function saveAndPrint() {
    if ($("#listProductReserve").valid()) {
        $("#printSale").dialog({
            resizable: false,
            modal: true,
            draggable: false,
            width: 300,
            buttons: [
                {
                    text: 'Imprimir', click: function() {
                        saveSaleAjax(this);
                    }
                }
            ]

        });
        $("#prinDate").text($("#datepicker").val());
        $("#prinDateReserve").text($("#datepickerClose").val());
        $("#printCi").text($("#ciId").val());
        $("#printFirstname").text($("#namePersonId").val());
        $("#printBranch").text($("#hBranchName").val());

        $("#sAccount").text($("#onAccount").val());
        $("#sRestante").text($("#residuaryId").val());

        $("#t_product tbody tr").each(function() {
            getTrData(this);
            getTrTotal(this);
        });
    }
}

function getTrData(tr) {
    var name = $(tr).children().children().val();
    var amount = $(tr).children().next().next().next().next().next().next().children().val();
    var priceSale = $(tr).children().next().next().next().next().next().next().next().children().val();
    var total = amount * priceSale;
    $("#printTable tbody:last").append("<tr><td>" +
            name + "</td><td style='text-align: right;'>" +
            amount + "</td><td style='text-align: right;'>" +
            priceSale + "</td><td style='text-align: right;'>" +
            total + "</td></tr>");

}
function getTrTotal(tr) {
    var priceTotal = $(tr).children().next().next().next().next().next().next().next().next().children().val();
    var total = $("#sTotal").text();
    if (!isNaN(parseFloat(total)) && isFinite(total)) {
        $("#sTotal").text(parseFloat(total) + parseFloat(priceTotal));
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

ItemsCollection.prototype.myfunction = function() {
    alert(this.items.length);
};

function ProductSale() {
    this.branchId = "";
    this.userId = "";
    this.clientId = "";
    this.dateSale = "";
    this.dateClose = "";
    this.onAccount = "";
    this.listProductSale = [];
    this.total = 0;
    
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

ProductSale.prototype.setItems = function(items) {
    this.listProductSale = items;
};

function saveSaleAjax(dSavePrint) {
    $.ajax({
        url: contextPath + "/inventorypp/saveReserveAjax.html",
        data: {"jsonSale": getJsonSale()},
        type: 'POST',
        success: function(data) {
            var print = "div.PrintArea.both";
            $(print).printArea({
            });
            clearFieldPrint();
            $("#iDialogHidden").val(data);
            $(dSavePrint).dialog("close");
            redirectInventory();
            //dialogRedirect();
        }
    });
}

function getJsonSale() {
    var productSale = new ProductSale();
    productSale.branchId = $("#branchId").val();
    productSale.userId = 0;
    productSale.dateClose = $("#datepickerClose").val();
    productSale.dateSale = $("#datepicker").val();
    productSale.onAccount = $("#onAccount").val();
    productSale.total = $("#totalBuy").val();
    productSale.clientId = $("#clientId").val();

    var pSs = new Array();

    $("#t_product > tbody > tr").each(function(i, item) {
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
                text: 'Redirecionar', click: function() {
                    redirectInventory();
                }
            }
        ]

    });
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