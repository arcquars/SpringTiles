var dialogCreateProvider;
$(document).ready(function() {
    $("#f_create_provider").validate({
        rules: {
            i_cprovider_businessname: "required",
            inames: "required",
            fp_i_firstname: "required",
            fp_i_ci: {
                number: true,
            },
            fp_i_phone_home: {
                number: true
            },
            fp_i_phone_mobil: {
                number: true
            },
            fp_i_email: {
                email: true
            }

        },
        messages: {
            i_cprovider_businessname: "Campo requerido.",
            inames: "Campo requerido.",
            fp_i_firstname: "Campo requerido.",
            fp_i_ci: {
                number: "Numeros Solamente.",
            },
            fp_i_phone_home: {
                number: "Numeros Solamente."
            },
            fp_i_phone_mobil: {
                number: "Numeros Solamente."
            },
            fp_i_email: {
                email: "Correo mal escrito."
            }
        }
    });
});
  
function redirectPersonal(){
    window.location = contextPath+"/personalpp.html";
}

function redirectBranch(){
    window.location = contextPath+"/branchpp.html";
}

function redirectPayments(){
    window.location = contextPath+"/creditpp.html";
}

function redirectClients(){
    window.location = contextPath+"/clientaa.html";
}

function redirectCategory(){
    window.location = contextPath+"/categorypp.html";
}

function redirectProduct(){
    window.location = contextPath+"/productpp.html";
}

function redirectDetailSale(){
    window.location = contextPath+"/inventorypp/saleGain.html";
}

function redirectReserveInventory(){
    window.location = contextPath+"/inventorypp/reserveStore.html";
}

function redirectAccountBranch(){
    window.location = contextPath+"/categorypp.html";
}

function redirectResportCredit(){
    window.location = contextPath+"/creditpp/reportCredit.html";
}

function redirectInventory(){
    window.location = contextPath+"/inventorypp.html";
}

function redirectSales(){
    window.location = contextPath+"/inventorypp/saleSelect.html";
}

function redirectInventoryBranch(){
    window.location = contextPath+"/inventorypp/inventoryBranch.html";
}

function redirectReserveByBranch(){
    window.location = contextPath+"/inventorypp/saleReserve.html";
}

function redirectSaleReceive(){
    window.location = contextPath+"/inventorypp/saleReceive.html";
}

function redirectSaleReport(){
    window.location = contextPath+"/inventorypp/saleReport.html";
}

function redirectAccountBranch(){
    window.location = contextPath+"/inventorypp/salesAccount.html";
}

function createProvider() {
    $.ajax({
        url: contextPath + '/productpp/validateSession.html',
        success: function(data) {
            if (data == 'false') {
                window.location = contextPath + "/loginaa.html";
            } else {
                dialogCreateProvider = $('#div_create_provider').dialog({
                    autoOpen: false,
                    resizable: false,
                    height: 380,
                    width: 460,
                    modal: true,
                    buttons: {
                        "Crear": function() {
                            if ($("#f_create_provider").valid() == true) {
                                $.ajax({
                                    url: contextPath + '/providerpp/createNewProvider.html',
                                    data: $('#f_create_provider').serialize(),
                                    success: function(data) {
                                        if (data == 'true') {
                                            alert('gravo!!!!');
                                            reloadProvider();
                                        }
                                        else
                                            alert('no gravo provider');
                                    }

                                });
                                closeDialog(dialogCreateProvider);
                            }

                        },
                        Cancel: function() {
                            closeDialog(dialogCreateProvider);

                        }
                    }
                });

                //dialogCreateProvider.load(contextPath+'/inventorypp/createProvider.html').dialog('open');
                dialogCreateProvider.dialog('open');
                resetPersonForm();

            }
        }
    });
}

function closeDialog(dialogCreateProvider) {
    dialogCreateProvider.dialog('close');
}

function resetPersonForm() {
    $("#i_cprovider_businessname").val('');
    $("#inames").val('');
    $("#fp_i_ci").val('');
    $("#fp_i_firstname").val('');
    $("#fp_i_lastname").val('');
    $("#fp_i_address").val('');
    $("#fp_i_phone_home").val('');
    $("#fp_i_phone_mobil").val('');
    $("#fp_i_email").val('');
    $("#fp_i_userId").val('-1');
    $("#fp_i_del").val('false');
}

function getPersonByci(ci) {
    $.ajax({
        url: contextPath + '/inventorypp/getPersonAjax.html',
        type: 'POST',
        data: {ci: $(ci).val()},
        dataType: 'json',
        success: function(data) {
            setPersonForm(data);
        },
        error: function(request, status, error) {
            window.location = contextPath;
        }
    });

}

function setPersonForm(person) {
    if (person !== null) {
        $("#inames").val(person.names);
        $("#fp_i_firstname").val(person.firstname);
        $("#fp_i_lastname").val(person.lastname);
        $("#fp_i_address").val(person.address);
        $("#fp_i_phone_home").val(person.phoneAddress);
        $("#fp_i_phone_mobil").val(person.phoneMobil);
        $("#fp_i_email").val(person.email);
        $("#fp_i_userId").val(person.perId);
        $("#fp_i_del").val(person.del);
    }
}

function reloadProvider() {
    $.get(contextPath+'/providerpp/allProviderAjax.html', function(data) {
        $('#fp_s_provider option').remove();
        $('#fp_s_provider').html(data);
        
        $('#providerId option').remove();
        $('#providerId').html(data);
    });
}