$(document).ready(function() {
    validateCreatePayment = validateFormPayment();
    
});

function validateFormPayment(){
    var total1 = parseFloat($('#total').html());
    var saldo1 = parseFloat($('#saldo').html());
    var resto = total1 - saldo1;
    return $("#f_createPayment").validate({
        rules: {
            i_payment: {
                required:true,
                number: true,
                range: [1, saldo1]
            }
        },
        messages:{
            i_payment: {
                required: 'No puede estar vacio.',
                number: 'Solo numeros',
                range: 'Fuera de rango'
            }
        }
    });
}
