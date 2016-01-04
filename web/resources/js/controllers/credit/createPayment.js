var urlReloadTableCredit;
var oTableCredit;
var validPp;
$(document).ready(function() {
    $("#providerId").selectmenu();
    $("#t_product tbody").click(function(event) {
        $(oTableCredit.fnSettings().aoData).each(function (){
            $(this.nTr).removeClass('row_selected');
        });
        $(event.target.parentNode).addClass('row_selected');
    });
    
    urlReloadTableCredit = contextPath+"/creditpp/getListCreditByProvider.html?providerId=0";
    oTableCredit = $('#t_product').dataTable({
        "bFilter": false,
        "bLengthChange": false,
        "aoColumnDefs": [
            {
                "asSorting": ["asc"],
                "aTargets": [1]
            }
        ],
        "bProcessing": true,
        "sAjaxSource": urlReloadTableCredit,
        "aoColumns": [
            {"mData": "creditId"},
            {"mData": "dateBuy"},
            {"mData": "total"},
            {"mData": "balance"}
        ]
    });
    
    $( "#cssmenu ul li" ).each(function( index ) {
        $(this).removeClass("active");
    });
    
    $( "#cssmenu ul" ).each(function( index ) {
        if(index === 5){
            $(this).parent().addClass("active");
        }
    });
});
function changePayment(input){
    var balance = parseFloat($(input).parent().prev().children().val());
    var payment = parseFloat($(input).val());
    if(!isNaN(payment) && payment <=balance){
    }
    else{
        alert('Solo numeros y menores a SALDO!!');
        $(input).val('0.0');
    }
}

function fnGetSelected( oTableCreditLocal )
{
    var aReturn = new Array();
    var aTrs = oTableCreditLocal.fnGetNodes();
	
    for ( var i=0 ; i<aTrs.length ; i++ )
    {
        if ( $(aTrs[i]).hasClass('row_selected') )
        {
            aReturn.push( aTrs[i] );
        }
    }
    return aReturn;
}

function fnPayment(){
    var trSelects = fnGetSelected(oTableCredit);
    if(trSelects.length>0){
        var creditId = $(trSelects).children().html();
        var saldo1 = parseFloat($(trSelects).children().next().next().next().html());
        var total = parseFloat($(trSelects).children().next().next().html());
        validPp = fngetValidCredit(saldo1);
        $("#i_payment").val('');
        $("#paymentTotal").children().remove();
        $("#paymentSaldo").children().remove();
        $("#paymentTotal").html("<b>"+total+"</b>");
        $("#paymentSaldo").html("<b>"+saldo1+"</b>");
        $('#pError').html('');
        $('#pError').parent().css("visibility", "hidden");
        var dialogBuyPro = $('#divPayment').dialog({
            autoOpen: false,
            resizable: false,
            width: 300,
            modal: true,
            buttons:{
                "Realizar Pago": function(){
                    if($("#f_createPayment").valid()){
                        $.ajax({
                            url: '/SpringTiles/creditpp/paymentSaveAjax.html',
                            data: {'i_creditid': creditId, 'i_saldo': $('#i_payment').val(), 'provider_id': $("#providerId").val()},
                            dataType: 'json',
                            success: function(data){
                                if(data != 'false'){
                                    dialogBuyPro.dialog('close');
                                    $('#errorpayment').children().remove();
                                    urlReloadTableCredit = contextPath+"/creditpp/getListCreditByProvider.html?providerId="+$("#providerId").val();
                                    oTableCredit.fnReloadAjax(urlReloadTableCredit);
                                    
                                }
                            }
                        
                        });                    
                    }
                },
                Cancel: function(){
                    closeDialog(dialogBuyPro);
                    $('#errorpayment').children().remove();
                }
            }
        });
        
        //var url = '/SpringTiles/creditpp/paymentCreteAjax.html?i_creditid='+creditId+'&i_saldo='+saldo1+'&i_payment='+total;
        //dialogBuyPro.load(url).dialog('open');
        dialogBuyPro.dialog('open');
    }else{
        $('#pError').html('Seleccione una Cuenta');
        $('#pError').parent().css("visibility", "visible");
    }
}

function closeDialog(dialogg){
    dialogg.dialog('close');
}

function searchCredit(){
    urlReloadTableCredit = contextPath+"/creditpp/getListCreditByProvider.html?providerId="+$("#providerId").val();
    oTableCredit.fnReloadAjax(urlReloadTableCredit);
}

function fngetValidCredit(saldo2){
    return $("#f_createPayment").validate({
        rules: {
            i_payment: {
                required:true,
                number: true,
                range: [1, saldo2]
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