var oTable;
$(document).ready(function () {
    $("#branchId").val(0);
    $("#branchId").selectmenu();

    $('#branchId option').filter(function () {
        return $(this).val() == -1
    }).attr('selected', true);

    $("#cssmenu ul li").each(function (index) {
        $(this).removeClass("active");
    });

    var ss = $("#cssmenu ul")[3];
    $(ss).parent().addClass("active");

    $("#i_from").datepicker({
        minDate: -30,
        maxDate: "+1M +10D",
        defaultDate: "-5w",
        dateFormat: "yy-mm-dd",
        changeMonth: true,
        onClose: function (selectedDate) {
            $("#i_to").datepicker("option", "minDate", selectedDate);
        }
    });
    $("#i_to").datepicker({
        minDate: -20,
        maxDate: "+1D",
        defaultDate: "+1d",
        dateFormat: "yy-mm-dd",
        changeMonth: true,
        onClose: function (selectedDate) {
            $("#i_from").datepicker("option", "maxDate", selectedDate);
            
            $("#i_from").datepicker("option", "minDate", moment(selectedDate).subtract(30, 'days').format("YYYY-MM-DD"));
        }
    });

    $("#i_to").val(moment().format("YYYY-MM-DD"));
    $("#i_from").val(moment().subtract(30, 'days').format("YYYY-MM-DD"));

    refreshTable();
    
    $("#f_search").submit(function (event) {
        refreshTable();
        event.preventDefault();
    });
});
$(function () {
    $(".mbutton").button();
    $("a", ".demo").click(function () {
        return false;
    });
});

function refreshTable() {
    /* Init the table */
    if (oTable !== undefined || oTable === null) {
        alert("ssss");
        oTable.destroy();
    };
    
    var urlReloadTable = contextServices + "sale/getListByDates?branchId=" + $('#branchId').val() + "&dateStart=" + $('#i_from').val() + "&dateEnd=" + $('#i_to').val();
    $.ajax({
        type: 'GET',
        url: urlReloadTable,
        data: "json",
        success: function (data) {
            $('#listSaleDetail').DataTable({
                "bFilter": false,
                "bLengthChange": false,
                "aoColumnDefs": [
                    {"bSortable": false, "aTargets": [0]},
                    {"asSorting": ["asc"], "aTargets": [1]}
                ],
                "bDestroy": true,
                "aaData": data.list,
                "aoColumns": [
                    {"mData": "dateSale"},
                    {"mData": "productName"},
                    {"mData": "productCategory"},
                    {"mData": "productFactory"},
                    {"mData": "costeBuy"},
                    {"mData": "priceSale"},
                    {"mData": "amountSale"},
                    {"mData": "gain"},
                    {"mData": "total"}
                ],
            });

            $("#i_gainTotal").val(data.totalGain);
            $("#i_granTotal").val(data.totalSale);
        }
    });
}
