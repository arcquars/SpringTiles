var oTable;
$(document).ready(function() {
    if($('#criteria option:selected').val() ==='3'){
        //$('#category option').prop("selected", false); 
        $('#category').css("display", "inline");
        $( "#category" ).selectmenu();
        $('#textSearch').css("display", "none");
    }else{
        $('#category option').eq(1).attr('selected', 'selected');
        $( "#category" ).selectmenu();
        $( "#category" ).selectmenu( "destroy" );
        $('#category').css("display", "none");
        $('#textSearch').css("display", "inline");
    }
    
    $('#branchId').selectmenu();
    $('#criteria').selectmenu({
        change: function( event, ui ) {
            var select = parseInt($('#criteria option:selected').val());
            if (select === 3) {
                //$('#category option').eq(0).attr('selected', 'selected');
                $('#category option').prop("selected", false); 
                $('#category').css("display", "inline");
                $( "#category" ).selectmenu();
                $('#textSearch').css("display", "none");
            } else {
                $( "#category" ).selectmenu();
                $( "#category" ).selectmenu( "destroy" );
                $('#category').css("display", "none");
                $('#textSearch').css("display", "inline");
            }
        }
    });
    
    oTable = $('#listPro').dataTable({
        "bFilter": false,
        "bLengthChange": false
        
    });
    
    oTable.$('tr').click(function(){
        $(this).toggleClass('row_selected');
    });
    
    $("#imprime").click(function (){
        $("div#printpp").printArea();
    });
    
    $( "#cssmenu ul li" ).each(function( index ) {
        $(this).removeClass("active");
    });
    $("#cssmenu ul li").first().addClass("active");
});
$(function() {
    $( ".mbutton").button();
    $( "a", ".demo" ).click(function() {
        return false;
    });
    
});

function criteriaChange(){
    var criteriaId = parseInt($('#criteria option:selected').val());
    if(criteriaId !=3){
        $('#category option').eq(1).attr('selected', 'selected');
        $('#textSearch').css('display', 'block');
        $('#category').css('display', 'none');
    }else{
        $('#category option').eq(0).attr('selected', 'selected');
        $('#textSearch').css('display', 'none');
        $('#category').css('display', 'block');
    }
}

function fnGetSelected(oTable){
    var aReturn = new Array();
    var aTrs = oTable.fnGetNodes();
    for(var i=0; i<aTrs.length; i++){
        if($(aTrs[i]).hasClass('row_selected')){
            //alert('pepepep:::'+$(aTrs[i]).html());
            aReturn.push(aTrs[i]);
        }
    }
    return aReturn;
}

function fnViewSelectForSale(){
    var anSelect = fnGetSelected(oTable);
    var ids = '';
    var isCero = true;
    var itemNameCero = "";
    for(var i=0; i<anSelect.length; i++){
        var iRow = anSelect[i];
        var parseF = parseFloat($(iRow).children().next().next().next().next().html());
        if(parseF <= 0){
            isCero = false;
            var slipt = $(iRow).children().children().html().split('<', 1);
            itemNameCero = slipt[0];
            break;
        }
        ids = ids+$(iRow).children().children().children().html()+',';
    }
    if(anSelect.length>0 && isCero == true){
        window.location="createSale.html?ids="+ids+"&branchId="+$('#branchId option:selected').val();
    }else{
        $('#pError').html('');
        if(anSelect.length<=0)
            $('#pError').html('No hay Productos seleccionados para venta.');
        if(isCero == false)
            $('#pError').html('El producto <strong style="font-size:13px;">'+itemNameCero+'</strong> tiene 0 cantidad para la Venta.');
        $('#pError').parent().css("visibility", 'visible');
    }
    
}

function fnViewSelectForReserve(){
    var anSelect = fnGetSelected(oTable);
    var ids = '';
    var isCero = true;
    var itemNameCero = "";
    for(var i=0; i<anSelect.length; i++){
        var iRow = anSelect[i];
        var parseF = parseFloat($(iRow).children().next().next().next().next().html());
        if(parseF <= 0){
            isCero = false;
            var slipt = $(iRow).children().children().html().split('<', 1);
            itemNameCero = slipt[0];
            break;
        }
        ids = ids+$(iRow).children().children().children().html()+',';
    }
    if(anSelect.length>0 && isCero == true){
        window.location="createReserve.html?ids="+ids+"&branchId="+$('#branchId option:selected').val();
    }else{
        $('#pError').html('');
        if(anSelect.length<=0)
            $('#pError').html('No hay Productos seleccionados para venta.');
        if(isCero == false)
            $('#pError').html('El producto <strong style="font-size:13px;">'+itemNameCero+'</strong> tiene 0 cantidad para la Venta.');
        $('#pError').parent().css("visibility", 'visible');
    }
    
}

function fnViewSelectForRefund(){
    var anSelect = fnGetSelected(oTable);
    var ids = '';
    var isCero = true;
    var itemNameCero = "";
    for(var i=0; i<anSelect.length; i++){
        var iRow = anSelect[i];
        var parseF = parseFloat($(iRow).children().next().next().next().next().html());
        if(parseF <= 0){
            isCero = false;
            var slipt = $(iRow).children().children().html().split('<', 1);
            itemNameCero = slipt[0];
            break;
        }
        ids = ids+$(iRow).children().children().children().html()+',';
    }
    if(anSelect.length>0 && isCero){
        window.location="createRefund.html?ids="+ids+"&branchId="+$('#branchId option:selected').val();
    }else{
        $('#pError').html('');
        if(anSelect.length<=0)
            $('#pError').html('No hay Productos seleccionados para devolver a almacen.');
        if(isCero == false)
            $('#pError').html('El producto <strong style="font-size:13px;">'+itemNameCero+'</strong> tiene 0 cantidad para Devolucion.');
        $('#pError').parent().css("visibility", 'visible');
    }
    
}

function printInventory() {
    $("#printTable > tbody > tr").remove();
    $("#s_sucursalId").text($("#branchId").find(":selected").text());
    getRecordProductByBranchId();
    $("#printSale").dialog({
        resizable: false,
        modal: true,
        draggable: false,
        width: 500,
        height: 200,
        buttons: [
            {
                text: 'Imprimir', click: function() {
                    
                    var print = "div.PrintAreaInventory";
                    $(print).printArea({
                        extraCss: "resources/styles/print1.css"
                    });
                    
                    /*
                   printElem({overrideElementCSS: true,
                           printBodyOptions:
                        {
                        styleToAdd:'padding:10px;margin:10px;color:#FFFFFF !important;',
                        classNameToAdd : 'test'
                        }});
                   $(this).dialog("close");
                    */
                    
                }
            }
        ]

    });
}

function getRecordProductByBranchId() {
    var criteria = $("#criteria").val();
    var category = $("#category").val();
    var textSearch = $("#textSearch").val();
    var branchId = $("#branchId").val();
    $.ajax({
        url: contextPath + "/inventorypp/recordProductByBranchIdAjax.html",
        data: {"branchId": branchId, "criteria": criteria, "category": category, "textSearch": textSearch},
        type: 'POST',
        dataType: 'json',
        success: function(data) {
            $.each(data, function(i, item) {
                $("#printTable tbody:last").append("<tr><td>" +
                        item.productName + "</td><td>" +
                        item.productCodOrigin + "</td><td>" +
                        item.category + "</td><td>" +
                        item.factoryName + "</td><td>" +
                        item.total + "</td></tr>");
            });
        }
    });
}

function printElem(options){
    $("#printSaleEject").printElement(options);
}