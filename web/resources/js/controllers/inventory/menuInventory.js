function redirectInventorymain() {
    $.ajax({
        url: contextPath + '/productpp/validateSession.html',
        success: function(data) {
            if (data == 'false') {
                window.location = contextPath + "/loginaa.html";
            } else {
                window.location = contextPath + "/inventorypp.html";
            }
        }
    });
}

function inventoryBranch() {
    $.ajax({
        url: contextPath + '/productpp/validateSession.html',
        success: function(data) {
            if (data == 'false') {
                window.location = contextPath + "/loginaa.html";
            } else {
                window.location = contextPath + "/inventorypp/inventoryBranch.html";
            }
        }
    });
}


function createFactory() {
    dialogCreateFactory = $('#div_create_factory').dialog({
        autoOpen: false,
        resizable: false,
        height: 160,
        width: 370,
        modal: true,
        buttons: {
            "Crear": function() {
                var dddd = $(this);
                if ($('#f_createfactory').valid() == true) {
                    $.ajax({
                        url: contextPath + 'factorypp/factoryCreate.html',
                        data: $('#f_createfactory').serialize(),
                        success: function(data) {
                            if (data == 'true') {
                                reloadFactory();
                                closeDialog(dialogCreateFactory);
                            }
                            else
                                alert('No pudo crear fabricante!!!');
                        }

                    });
                }
            },
            Cancel: function() {
                //validatorCategory.resetForm();
                closeDialog(dialogCreateFactory);
            }
        }
    });

    dialogCreateFactory.load(contextPath + '/inventorypp/createFactory.html').dialog('open');
}


function reloadFactory() {
    $.get(contextPath + '/factorypp/allFactoryAjax.html', function(data) {
        $('#fp_s_factory option').remove();
        $('#fp_s_factory').html(data);
    });
}


function saleGain() {
    $.ajax({
        url: contextPath + '/productpp/validateSession.html',
        success: function(data) {
            if (data == 'false') {
                window.location = contextPath + "/loginaa.html";
            } else {
                window.location = contextPath + "/inventorypp/saleGain.html";
            }
        }
    });
}

function saleReserve() {
    $.ajax({
        url: contextPath + '/productpp/validateSession.html',
        success: function(data) {
            if (data == 'false') {
                window.location = contextPath + "/loginaa.html";
            } else {
                window.location = contextPath + "/inventorypp/saleReserve.html";
            }
        }
    });
}

function salesAccount() {
    $.ajax({
        url: contextPath + '/productpp/validateSession.html',
        success: function(data) {
            if (data == 'false') {
                window.location = contextPath + "/loginaa.html";
            } else {
                window.location = contextPath + "/inventorypp/salesAccount.html";
            }
        }
    });
}

function validatePayment() {
    var payment = parseFloat($('#fp_i_payment').val());
    var total = parseFloat($('#fp_i_pricetotal').val());
    var resss = false;
    if (!isNaN(payment) && !isNaN(total) && payment < total) {
        resss = true;
    } else {
        if (isNaN(payment)) {
            resss = true;
        }
        else
            $('#errorpayment').html('<em>Tiene que ser numeros o el pago tiene que ser menor</em>');

    }
    return resss;
}

