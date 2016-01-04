$(function() {
    $(".mbutton").button();
    $("a", ".demo").click(function() {
        return false;
    });
});

var oTable;

$(document).ready(function() {

    $('#bcategoryEdit').button({
        disabled: true
    });
    $('#bcategoryDelete').button({
        disabled: true
    });

    selectTable();

    /* Init the table */
    oTable = $('#listCat').dataTable({
        "bFilter": false,
        "bLengthChange": false
    }
    );

    $('#bcategoryDelete').click(function() {
        $('#dialogDelete').dialog('open');
    });

    $('#dialogDelete').dialog({
        autoOpen: false,
        resizable: false,
        height: 140,
        width: 220,
        modal: true,
        buttons: {
            "Borrar": function() {
                var ddd = $(this);
                $.ajax({
                    url: 'categorypp/categoryDelete.html',
                    data: {
                        catId: $('#hdid').val()
                    },
                    success: function(data) {
                        if (data == 'true') {
                            $('#delete').trigger('click');
                            ddd.dialog('close');
                        }
                        else
                            $('#errorDelete').html(data).css('color', '#f3410d');
                    }

                });
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        }
    });

    $('#dialogUpdate').dialog({
        autoOpen: false,
        resizable: false,
        height: 180,
        width: 300,
        modal: true,
        buttons: {
            "Grabar": function() {
                var ddd = $(this);
                $.ajax({
                    url: 'categorypp/categoryUpdate.html',
                    data: $('#f_edit_cat').serialize(),
                    dataType: 'json',
                    success: function(data) {
                        if (data) {
                            oTable.fnClearTable();
                            $.each(data, function(i, item) {
                                oTable.fnAddData(["<p>" + item.categoryName + "<strong style='visibility: hidden;'>" + item.categoryId + "</p>"]);
                            });
                            oTable.fnDraw();
                            selectTable();
                            ddd.dialog('close');
                            $('#bcategoryEdit').button("option", "disabled", true);
                            $('#bcategoryDelete').button("option", "disabled", true);
                        }
                        else {
                            if ($('#pErrorMensaje').html() == '')
                                $('#pErrorMensaje').append('El nombre ya esta registrado');
                        }
                    }

                });
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        }
    });

    $('#divCategoryCreate').dialog({
        autoOpen: false,
        resizable: false,
        height: 175,
        width: 300,
        modal: true,
        buttons: {
            "Grabar": function() {
                var ddd = $(this);
                $.ajax({
                    url: 'categorypp/categoryCreate.html',
                    data: $('#f_createcategory').serialize(),
                    dataType: 'json',
                    success: function(data) {
                        if (data) {
                            oTable.fnClearTable();
                            $.each(data, function(i, item) {
                                oTable.fnAddData(["<p>" + item.categoryName + "<strong style='visibility: hidden;'>" + item.categoryId + "</p>"]);
                            });
                            oTable.fnDraw();
                            selectTable();
                            ddd.dialog('close');
                        }
                        else {
                            if ($('#pErrorMensajeCrear').html() == '')
                                $('#pErrorMensajeCrear').append('Ya Existe el nombre ' + $('#inameCreate').val());
                        }
                    }

                });
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        }
    });


    $('#bcategoryEdit').click(function() {
        $("#iTextSearch").val($("#textSearch").val());
        $('#dialogUpdate').dialog('open');
        $('#iname').val($('#hdname').val());
        $('#icatId').val($('#hdid').val());
        $('#pErrorMensaje').html('');
    });

    $('#bCreateCategory').click(function() {
        $("#iTextSearchNewCategory").val($("#textSearch").val());
        $('#divCategoryCreate').dialog('open');
    });
    
    $( "#cssmenu ul li" ).each(function( index ) {
        $(this).removeClass("active");
    });
    
    var ss = $( "#cssmenu ul" )[4];
    $(ss).parent().addClass("active");
});

function selectTable() {
    $("#listCat tbody tr").click(function(e) {
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
            $('#hdid').val('');
            $('#stnames').html('');
            $('#hdname').val('');
            $('#bcategoryEdit').button("option", "disabled", true);
            $('#bcategoryDelete').button("option", "disabled", true);
        }
        else {
            $('#hdid').val($(this).children().children().children().html());
            $('#stnames').html($(this).children().html());
            var nameCat = $(this).children().children().text().split($(this).children().children().children().html(), 1);
            $('#hdname').val(nameCat);
            $('#bcategoryEdit').button("option", "disabled", false);
            $('#bcategoryDelete').button("option", "disabled", false);
            oTable.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
        }
    });
}