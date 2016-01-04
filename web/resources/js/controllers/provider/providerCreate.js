// validate signup form on keyup and submit
var validator = $("#f_create_provider").validate({
    rules: {
        i_cprovider_businessname:{required:true, remote: 'providerpp/providerValidName.html'},
        inames: 'required',
        fp_i_firstname: 'required',
        fp_i_ci: {
            required:true, 
            digits:true, 
            range:[10000,10000000],
            remote: 'personalpp/personValidateCi.html'
        },
        fp_i_address: 'required',
        fp_i_phone_home: {
            digits:true, 
            range: [4000000, 4999999]
        },
        fp_i_phone_mobil: {
            required:true, 
            digits:true, 
            range:[50000000,89999999]
        },
        fp_i_email: 'email'
    },
    messages:{
        i_cprovider_businessname: {required: 'No puede estar vacio.', remote:'Ya existe el nombre.'},
        inames: 'No puede estar vacio.',
        fp_i_firstname: 'No puede estar vacio.',
        fp_i_ci: {
            required: 'No puede estar vacio.',
            digits: 'Solo digitos.', 
            range: 'Rango no permitido.',
            remote: 'Ci ya este registrado.'
        },
        fp_i_address: 'No puede estar vacio.',
        fp_i_phone_home: {
            digits: 'Solo digitos', 
            range: 'Rango no permitido'
        },
        fp_i_phone_mobil: {
            required: 'No puede estar vacio.', 
            digits: 'Solo digitos', 
            range: 'Rango no permitido'
        },
        fp_i_email: 'Formato de correo no valido'
    },
    submitHandler:function(){
        $.ajax({
            url: 'personalpp/employeeCreate.html',
            data: $('#f_createperson').serialize(),
            success: function(data){
                if(data==1){
                    $('#delete').trigger('click');
                }
                else
                    $('#errorDelete').html(data).css('color', '#f3410d');
            }
                        
        });
    }
});