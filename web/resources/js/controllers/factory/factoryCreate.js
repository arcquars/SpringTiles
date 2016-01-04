// validate signup form on keyup and submit
var validFactory = $("#f_createfactoryss").validate({
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