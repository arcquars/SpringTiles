$(function() {
    $( ".mbutton").button();
    $( "a", ".demo" ).click(function() {
        return false;
    });
});
$(document).ready(function() {
    
    // validate signup form on keyup and submit
    var validator = $("#f_createperson").validate({
        rules: {
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
        
    $( "#dialog:ui-dialog" ).dialog( "destroy" );
    $('#divPersonCreate').dialog({
        autoOpen: false,
        title: 'Crear Persona',
        modal: true,
        height: 400,
        width: 600,
        
        buttons: {
            "Grabar": function(){
                var ddd = $(this);
                if($('#f_createperson').valid() == true){
                    //validateCi();
                    $('#f_createperson').submit();
                    $('#f_createperson').each(function(){
                        this.reset(); 
                    });
                    $(this).dialog("close");
                }
            },
            Cancel: function(){
                $('#f_createperson').each(function(){
                    this.reset(); 
                });
                $(this).dialog("close");
            }
        }
    });
        
    $('#bCreatePerson').click(function(){
        $('#divPersonCreate').dialog('open');
    });
    
    
    
});