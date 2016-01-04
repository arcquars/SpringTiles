$(document).ready(function() {
    var trs = $('#t_product tr');
    //alert($('.formInfo').html());
    $('#credit2').change(function() {
        if (!$('#credit2 :checked').val()) {
        }
    });

    $('.classTooltip').tooltip({html: true});
    //$('#fp_s_factory').
});

function changePayment(payment) {
    var total = parseFloat($('#totalBuy').val());
    var paymentFloat = parseFloat($(payment).val());
    if (!isNaN(paymentFloat) && paymentFloat >= 0) {
        if (paymentFloat > total) {
            alert('El pago de credito no puede ser mayor al Total de la compra');
            $(payment).val('0.0');
        }
    } else {
        $(payment).val('0.0');
    }
}

function changeVisible(radiobutton) {
    $('#payment').css('visibility', 'visible');
}
function changeHidden(radiobutton) {
    $('#payment').css('visibility', 'hidden');
}
function trsss(input) {
    var amount = parseInt($(input).val());
    $(input).val(amount);
    var price = parseFloat($(input).parent().next().children().val());

    if (!isNaN(amount) && !isNaN(price) && amount >= 0 && price >= 0) {
        $(input).parent().next().next().children().val(amount * price);
        $(input).parent().next().next().children().change();
        $('#payment').val('0.0');
    } else {
        $(input).val('0');
        $(input).parent().next().children().val('0');
        $(input).parent().next().next().children().val('0');
        $(input).parent().next().next().children().change();
        $('#payment').val('0.0');
    }
}

function treee(input) {
    var pricee = parseFloat($(input).val());
    var amountt = parseFloat($(input).parent().prev().children().val());

    if (!isNaN(amountt) && !isNaN(pricee) && amountt >= 0 && pricee >= 0) {
        $(input).parent().next().children().val(amountt * pricee);
        $(input).parent().next().children().change();
        $('#payment').val('0.0');
    } else {
        $(input).val('0');
        $(input).parent().prev().children().val('0');
        $(input).parent().next().children().val('0');
        $(input).parent().next().children().change();
        $('#payment').val('0.0');
    }

}

function trTotal() {
    var trses = $('#t_product tr');
    var totalS = 0;
    for (var i = 1; i < $(trses).length; i++) {
        var hijo = $($(trses)[i]).children().next().next().next().next().next().next().children();
        totalS = totalS + parseFloat($(hijo).val());
    }
    $('#totalBuy').val(totalS);
}

function redirectInventory() {
    window.location = contextPath + "/inventorypp.html";
}

function overTooltip(alink) {
    var contentTitle = $(alink).attr('title');
    if (contentTitle === undefined) {
        $.ajax({
            url: contextPath + "/loginaa/productAverageAjax.html",
            data: {"id": $(alink).attr('id')},
            type: 'POST',
            success: function(data) {
                $(alink).attr('title', data);
            }
        });
    }
}