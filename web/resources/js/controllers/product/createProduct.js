var validFactory1 = $("#f_createfactoryss").validate({
    rules: {
        i_factory_name: {
            required: true,
            remote: contextPath+'/factorypp/factoryValidName.html'
        }
    },
    messages: {
        i_factory_name: {
            required: 'No puede estar vacio.',
            remote: 'Ya existe el nombre.'
        }
    }
});

$(document).ready(function() {
    $("#iname_cat_create").focus();
    /*
     reloadFactory();
     reloadCategory();
     */

    $("#f_createcategory").validate({
        rules: {
            iname_cat_create: {
                required: true,
                remote: '/SpringTiles/categorypp/categoryValidName.html'
            }
        },
        messages: {
            iname_cat_create: {
                required: 'No puede estar vacio.',
                remote: 'Nombre ya existe.'
            }
        }
    });
});

function reloadFactory() {
    $.ajax({
        url: contextPath + "/factorypp/allFactoryAjax.html",
        type: 'get',
        success: function(data) {
            $('#fp_s_factory option').remove();
            $('#fp_s_factory').append(data);
        },
        beforeSend: function() {
            //
        }
    });
}

function reloadCategory() {
    $.ajax({
        url: contextPath + "/categorypp/allCategoryAjax.html",
        type: 'get',
        success: function(date) {
            $('#fp_s_category option').remove();
            $('#fp_s_category').append(date);
        },
        beforeSend: function() {
            //
        }
    });
}
var validCreateProduct = $("#f_createproduct").validate({
    rules: {
        iname: {
            required: true,
//            remote: 'productpp/productValidNameAjax.html'
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
  //          remote: 'Nombre ya existe.'
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
            min:'Mayor a Cero'
        }
    }
});
function reloadCategories() {
    $.ajax({
        url: contextPath + "/categorypp/allCategoryAjax.html",
        type: 'get',
        success: function(date) {
            $('#fp_s_category option').remove();
            $('#fp_s_category').append(date);
            return date;
        }
    });
}

function createCategory() {
    var dialogCategory = $('#divCreateCategory').dialog({
        autoOpen: false,
        resizable: false,
        width: 370,
        modal: true,
        buttons: {
            "Crear": function() {
                if ($("#f_createcategory").valid() === true) {
                    $.ajax({
                        url: contextPath + '/categorypp/categoryCreteAjax.html',
                        data: $('#f_createcategory').serialize(),
                        success: function(data) {
                            if (data !== '') {
                                //validatorCategory.resetForm();
                                $('#fp_s_categorypp option').remove();
                                $('#fp_s_categorypp').append(data);
                            }
                        }

                    });
                    dialogCategory.dialog("close");
                }
            },
            Cancel: function() {
                $('#f_createcategory').each(function() {
                    this.reset();
                });
                dialogCategory.dialog("close");
            }
        }
    });

    //dialogCategory.load('/SpringTiles/categorypp/createCategory.html').dialog('open');
    dialogCategory.dialog('open');
}
