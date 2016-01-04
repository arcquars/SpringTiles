var oTable12;

var validFactory1 = $("#f_createfactoryss").validate({
    rules: {
        i_factory_name: {
            required: true,
            remote: '/SpringTiles/factorypp/factoryValidName.html'
        }
    },
    messages: {
        i_factory_name: {
            required: 'No puede estar vacio.',
            remote: 'Ya existe el nombre.'
        }
    }
});

var validCreateProduct = null;

$(document).ready(function() {
    $('#s_searchBy').selectmenu();
    
    $('#bproductEdit').button({
        disabled: true
    });

    $('#bproductDelete').button({
        disabled: true
    });

    $("#dialog:ui-dialog").dialog("destroy");
    $('#divProductCreate').dialog({
        autoOpen: false,
        title: 'Crear Producto',
        modal: true,
        height: 250,
        width: 400,
        buttons: {
            "Grabar": function() {
                if ($('#f_createproduct').valid() == true) {
                    $('#f_createproduct').submit();
                    $(this).dialog("close");
                }

            },
            Cancel: function() {
                $(this).dialog("close");
            }
        }
    });

    /* Init the table */
    var urlReloadTable = "productpp/getListProductByCriteria.html?criteria=" + $('#s_searchBy').val() + "&textSearch=" + $('#i_textSearch').val();
    oTable12 = $('#listPro').dataTable({
        "bFilter": false,
        "bLengthChange": false,
        "aoColumnDefs": [
            {
                "asSorting": ["asc"],
                "aTargets": [0]
            }
        ],
        "bProcessing": true,
        "sAjaxSource": urlReloadTable,
        "aoColumns": [
            {"mData": "name"},
            {"mData": "codOrigin"},
            {"mData": "categoryName"},
            {"mData": "factoryName"},
            {"mData": "description"}
        ],
        "fnDrawCallback": function(oSettings) {
            /* Add a click handler to the rows - this could be used as a callback */
            //selectProduct();
        }
    });

    selectProduct(oTable12);
    $('#bproductDelete').click(function() {
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

        var anSelected = fnGetSelected(oTable12);
        if (anSelected.length !== 0) {
            $('#dialogDelete').dialog({
                resizable: false,
                width: 250,
                modal: true,
                buttons: {
                    "Borrar": function() {
                        var ddd = $(this);
                        $.ajax({
                            url: 'productpp/productDelete.html',
                            data: {
                                proId: $('#hdid').val()
                            },
                            success: function(data) {
                                if (data == 1) {
                                    //$('#delete').trigger('click');
                                    oTable12.fnReloadAjax();
                                    ddd.dialog('close');
                                }
                                else
                                    $('#errorDelete').html(data).css('color', '#f3410d');
                            }

                        });
                    },
                    Cancel: function() {
                        $(this).dialog('close');
                    }
                }
            });
        } else {
            $('#dialogInvalid').dialog({
                modal: true,
                buttons: {
                    Ok: function() {
                        $(this).dialog("close");
                    }
                }
            });
        }
    });
    
    $( "#cssmenu ul li" ).each(function( index ) {
        $(this).removeClass("active");
    });
    
    var ss = $( "#cssmenu ul" )[4];
    $(ss).parent().addClass("active");

});

function fnGetSelected(oTable12Local)
{
    var aReturn = new Array();
    var aTrs = oTable12Local.fnGetNodes();

    for (var i = 0; i < aTrs.length; i++)
    {
        if ($(aTrs[i]).hasClass('selected'))
        {
            aReturn.push(aTrs[i]);
        }
    }
    return aReturn;
}

function linkproductEdit() {
    //window.location.href = 'personalpp/personEdit/'+$('#hdid').val()+'.html';
    return false;
}

function createProduct() {
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

    var dialogBuyPro = $('#divBuyProductNew').dialog({
        autoOpen: false,
        resizable: false,
        width: 400,
        modal: true,
        buttons: {
            "Crear": function() {
                if ($("#f_createproduct").valid() && validatePayment()) {
                    $.ajax({
                        url: contextPath + '/productpp/productCreate.html',
                        data: $('#f_createproduct').serialize(),
                        success: function(data) {
                            if (data === 'true') {
                                dialogBuyPro.dialog('close');
                                $('#s_searchBy').val(1);
                                $('#i_textSearch').val("");
                                oTable12.fnReloadAjax("productpp/getListProductByCriteria.html?criteria=" + $('#s_searchBy').val() + "&textSearch=" + $('#i_textSearch').val());
                                validCreateProduct.resetForm();
                                $('#errorpayment').children().remove();
                                $("#f_createproduct").each(function() {
                                    this.reset();
                                });
                            }
                        }

                    });

                }
            },
            Cancel: function() {
                closeDialog(dialogBuyPro);
                $('#errorpayment').children().remove();
            }
        }
    });
    //dialogBuyPro.load(contextPath + '/productpp/createProduct.html').dialog('open');
    dialogBuyPro.dialog('open');
    validCreateProduct = $("#f_createproduct").validate({
        rules: {
            iname: {
                required: true,
//                remote: 'productpp/productValidNameAjax.html'
            },
            fp_s_category: {
                min: 1
            },
            fp_s_factory: {
                min: 1
            },
            fp_i_price: {
                required: true,
                number: true,
                min: 0
            }
        },
        messages: {
            iname: {
                required: 'No puede estar vacio.',
//                remote: 'Nombre ya existe.'
            },
            fp_s_category: {
                min: 'Seleccione una Categoria'
            },
            fp_s_factory: {
                min: 'Seleccione una Fabrica'
            },
            fp_i_price: {
                required: 'No puede estar vacio.',
                number: 'Numeros solamente.',
                min: 'Mayor a Cero'
            }
        }
    });
    $('#iname_cat_create').val('');
}

function closeDialog(dialogCreateProvider) {
    dialogCreateProvider.dialog('close');
}

// Edit PRODUCT 
function editProduct() {
    validateEditProduct = validateFormEditProduct();
    var dialogBuyProEdit = $('#divBuyProductEdit').dialog({
        autoOpen: false,
        resizable: false,
        height: 270,
        width: 400,
        modal: true,
        buttons: {
            "Grabar": function() {
                alert("xxxxxxxx");
                if ($("#f_editproduct").valid()) {
                    $.ajax({
                        url: '/SpringTiles/productpp/productEdit.html',
                        data: $('#f_editproduct').serialize(),
                        success: function(data) {
                            if (data === 'true') {
                                
                                dialogBuyProEdit.dialog('close');
                                validateEditProduct.resetForm();
                                $('#errorpayment').children().remove();
                            }
                        }

                    });
                }
            },
            Cancel: function() {
                closeDialog(dialogBuyProEdit);
                $('#errorpayment').children().remove();
            }
        }
    });
    var proId = $('#hdid').val();
    dialogBuyProEdit.load('/SpringTiles/productpp/editProduct.html?prodId=' + proId).dialog('open');
    $('#iname_cat_create').val('');
}

function validateFormEditProduct() {
    var ss = $("#f_editproduct").validate({
        rules: {
            iproductName: {
                required: true,
                remote: {
                    url: contextPath + '/productpp/productValidNameEditAjax.html',
                    data: {
                        prodId: function() {
                            return $('#hdid').val();
                        },
                        iproductName: function() {
                            return $("#iproductName").val();
                        }
                    }
                }
            },
            fp_s_category: {
                min: 1
            },
            fp_s_factory: {
                min: 1
            }
        },
        messages: {
            iproductName: {
                required: 'No puede estar vacio.',
                remote: 'Nombre ya existe.'
            },
            fp_s_category: {
                min: 'Seleccione una Categoria'
            },
            fp_s_factory: {
                min: 'Seleccione una Fabrica'
            }
        }
    });
    return ss;
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

function createFactory() {
    dialogCreateFactory = $('#div_create_factory').dialog({
        autoOpen: false,
        resizable: false,
        width: 370,
        modal: true,
        buttons: {
            "Crear": function() {
                if ($("#f_createfactoryss").valid() === true) {
                    $.ajax({
                        url: '/SpringTiles/factorypp/factoryCreate.html',
                        data: $('#f_createfactoryss').serialize(),
                        success: function(data) {
                            if (data === 'true') {
                                reloadFactory();
                                closeDialog(dialogCreateFactory);
                            }
                            else {
                                $('#pErrorMensajeCreateFactory').html('');
                                $('#pErrorMensajeCreateFactory').html('No pudo crear fabricante!!!');
                            }
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

    //dialogCreateFactory.load('/SpringTiles/inventorypp/createFactory.html').dialog('open');
    dialogCreateFactory.dialog('open');
}
/*
function reloadFactory() {
    alert('xxxx');
    $.get('/SpringTiles/factorypp/allFactoryAjax.html', function(data) {
        alert(data);
        $('#fp_s_factory_id option').remove();
        $('#fp_s_factory_id').append(data);
    });
}
*/
function reloadTableProducts() {
    oTable12.fnClearTable();
    //oTable12.fnAddData([{'name': 'ase', "codOrigin": 'dd', "categoryName": 'ddd', "factoryName": 'ioio', "description": 'sasa'}]);
    oTable12.fnReloadAjax("productpp/getListProductByCriteria.html?criteria=" + $('#s_searchBy').val() + "&textSearch=" + $('#i_textSearch').val());

}

function openDialogEdit() {
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

    var ss1 = validateFormEditProduct();
    var dialogProductEdit = $("#divBuyProductEdit").dialog({
        resizable: false,
        modal: true,
        draggable: false,
        width: 500,
        buttons: {
            "Grabar": function() {
                if (ss1.valid()) {
                    $.ajax({
                        url: contextPath + "/productpp/productSaveEditAjax.html",
                        type: 'POST',
                        data: JSON.stringify({name: $("#iproductName").val(), productId: $("#hdid").val(),
                            codOrigin: $("#iproductCodOrigen").val(), description: $("#fp_i_description_id").val(),
                            categoryId: $("#fp_s_category").val(), factoryId: $("#fp_s_factory_id").val(),
                            priceSale: $("#fp_i_price_sale").val()
                        }),
                        contentType: 'application/json',
                        success: function(data) {
                            if (data === "true") {
                                dialogProductEdit.dialog('close');
                                //oTable12 = $('#listPro').dataTable();
                                oTable12.fnReloadAjax();
                            } else {
                                alert("No se pudo guardar!");
                                dialogProductEdit.dialog('close');
                                window.location.href = contextPath;
                            }
                        },
                        error: function(data, status, er) {
                            alert("error: " + data + " status: " + status + " er:" + er);
                            dialogProductEdit.dialog('close');
                        }
                    });

                } else {
                    alert("xxxxxx else");
                }
            },
            Cancel: function() {
                dialogProductEdit.dialog('close');
            }
        }
    });

    $.ajax({
        url: contextPath + '/productpp/editProductAjax.html',
        data: {prodId: $("#hdid").val()},
        dataType: "json",
        success: function(data) {
            if (data !== '') {
                $("#iproductName").val(data.name);
                $("#iproductCodOrigen").val(data.codOrigin);
                $("#fp_s_category").val(data.categoryId);
                $("#fp_s_factory_id").val(data.factoryId);
                $("#fp_i_description_id").val(data.description);
                $("#fp_i_price_sale").val(data.priceSale);
                //$("#a_price_sale").attr("href", "loginaa/productAverage.html?width=200&id="+data.productId);
                $("#h_prodID").val($("#hdid").val());
            }
        }

    });
    $.ajax({
        url: contextPath + '/productpp/priceSaleAverageAjax.html',
        data: {prodId: $("#hdid").val()},
        success: function(data) {
            if (data !== '') {
                $("#a_price_sale").attr("title", data);
            }
        }

    });
    $.ajax({
        url: contextPath + '/loginaa/productCosteAverageJson.html',
        data: {prodId: $("#hdid").val()},
        dataType: "json",
        success: function(data) {
            if (data !== '') {
                $("#p_coste_average").html("Costo Max: <b>" + data[0] + "</b>; Costo Pro: <b>" + data[1] + "</b>; Costo Min: <b>" + data[2] + "</b>");
            }
        }

    });
}

function selectProduct(table) {
    $('#listPro tbody').on('click', 'tr', function() {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            $('#hdid').val('');
            $('#stnames').html('');
            $('#bproductEdit').button('option', 'disabled', true);
            $('#bproductDelete').button('option', 'disabled', true);
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            $('#hdid').val($(this).children().children().html());
            $('#stnames').html($(this).children().next().html());
            $('#bproductEdit').button('option', 'disabled', false);
            $('#bproductDelete').button('option', 'disabled', false);
        }
    });
}

