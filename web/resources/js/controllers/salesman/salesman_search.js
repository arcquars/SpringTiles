$(function() {
    $( ".mbutton").button();
    $( "a", ".demo" ).click(function() {
        return false;
    });
    
    oTable = $('#listPro').dataTable({
        "bFilter": false,
        "bLengthChange": false
        
    });
    
    oTable.$('tr').click(function(){
        $(this).toggleClass('row_selected');
    });
    
    
});

$(document).ready(function() {
    $('#criteria').change(function(){
        if($('#criteria option:selected').val() == '3'){
            $('#category option').eq(0).attr('selected', 'selected');
            $('#textSearch').css('display', 'none');
            $('#category').css('display', 'block');
        }else{
            $('#category option').eq(1).attr('selected', 'selected');
            $('#textSearch').css('display', 'block');
            $('#category').css('display', 'none');
        }
    });
});

function fnGetSelected(oTable){
    var aReturn = new Array();
    var aTrs = oTable.fnGetNodes();
    for(var i=0; i<aTrs.length; i++){
        if($(aTrs[i]).hasClass('row_selected')){
            aReturn.push(aTrs[i]);
        }
    }
    return aReturn;
}

function fnViewSelectForSaleInventory(){
    var anSelect = fnGetSelected(oTable);
    var ids = '';
    var notCero = true;
    var itemNameCero = "";
    for(var i=0; i<anSelect.length; i++){
        var iRow = anSelect[i];
        var ceroIs = parseFloat($(iRow).children().next().next().next().next().html());
        if(ceroIs == 0){
            notCero = false;
            var slipt = $(iRow).children().children().html().split('<', 1);
            itemNameCero = slipt[0];
            break;
        }
        ids = ids+$(iRow).children().children().children().html()+',';
    }
    
    if(anSelect.length>0 && notCero == true){
        var criteriaR = parseInt($('#criteria').val());
        var searchText = "";
        if(criteriaR == 3){
            searchText = $('#category').val();
        }else{
            searchText = $('#textSearch').val();
        }
        window.location="inventorypp/createSale.html?ids="+ids+"&branchId="+$('#branchId').val()+"&criteriaR="+criteriaR+"&searchText="+searchText;
    }else{
        $('#pError').html('');
        if(anSelect.length<=0)
            $('#pError').html('No hay Productos seleccionados para venta.');
        if(notCero == false)
            $('#pError').html('El producto <strong style="font-size:13px;">'+itemNameCero+'</strong> tiene 0 cantidad para la Venta.');
        $('#pError').parent().css("visibility", 'visible');
    }
    
    
}