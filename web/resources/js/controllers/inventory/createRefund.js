$(function() {
    $( ".mbutton").button();
    $( "a", ".demo" ).click(function() {
        return false;
    });
});
function validAmount(amount){
    var amountFloat = parseFloat($(amount).val());
    var qlimt = parseFloat($(amount).parent().prev().children().val());    
    if(!isNaN(amountFloat) && amountFloat>0 && amountFloat<=qlimt){
    }else{
        $(amount).val('1');
        alert('Solo numeros y menores a la cantidad limite.');
    }
}