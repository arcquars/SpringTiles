$(document).ready(function() {
    $("#iname_cat_create").focus();
});

var validatorCategory = $("#f_createcategory").validate({
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