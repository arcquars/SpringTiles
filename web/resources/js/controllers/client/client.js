var cllientIdE = 0;
$(document).ready(function () {
    $("#f_client").validate({
        rules: {
            razonSocial: "required",
            phone: {
                number: true
            },
            fax: {
                number: true
            },
            url: {
                url: 5
            },
            email: {
                email: true
            },
            nit: {
                number: true,
                min: 1,
                required: true,
                remote: contextServices+"client/validateNit"
            },
            agree: "required"
        },
        messages: {
            razonSocial: "No puede estar vacio",
            phone: {
                number: "Solo numeros"
            },
            fax: {
                number: "Solo numeros"
            },
            url: {
                url: "Direccion url invalida"
            },
            email: "Formato de correo no valido",
            nit: {
                number: "Solo numeros",
                required: "No puede estar vacio",
                remote: "Nit ya existe",
                min: "Numeros mayores a 1"
            },
        }
    });
    
    $("#f_clientE").validate({
        rules: {
            nit: {
                required: true,
                number: true,
                remote: {
                    url: contextServices+"client/validateNitE",
                    type: "post",
                    data: {nit: $("#client_nitHE").val(), clientId: function () { return $("#clientIdEdit").val()}}
                }
            },
            razonSocial: "required",
            phone: {
                number: true
            },
            fax: {
                number: true
            },
            url: {
                url: 5
            },
            email: {
                email: true
            },
            agree: "required"
        },
        messages: {
            razonSocial: "No puede estar vacio",
            phone: {
                number: "Solo numeros"
            },
            fax: {
                number: "Solo numeros"
            },
            url: {
                url: "Direccion url invalida"
            },
            email: "Formato de correo no valido",
            nit: {
                required: "No puede estar vacio",
                number: "Solo numeros",
                remote: "Nit existente"
            },
        }
    });

    $("#f_createPeson").validate({
        rules: {
            ci: {
                number: true,
                required: true
            },
            firstname: {
                required: true
            },
            phoneAddress: {
                number: true,
                required: true,
                min: 0
            },
            phoneMobil: {
                number: true,
                required: true,
                min: 0
            },
            email: {
                email: true
            },
            agree: "required"
        },
        messages: {
            ci: {
                number: "Solo numeros",
                required: "No puede estar vacio"
            },
            firstname: {
                required: "No puede estar vacio"
            },
            razonSocial: "No puede estar vacio",
            phoneAddress: {
                number: "Solo numeros"
            },
            phoneMobil: {
                number: "Solo numeros"
            },
            email: "Formato de correo no valido",
        }
    });

    $("#dialogClient").dialog({
        resizable: false,
        autoOpen: false,
        closeOnEscape: false,
        dialogClass: "no-close",
        modal: true,
        buttons: {
            "Crear/Editar": function () {
                $("#f_client").submit();
            },
            Cancel: function () {
                $(this).dialog("close");
//                $("#createPersonDialog").dialog("close");
            }
        },
        open: function (event, ui) {
            $('#f_client').each(function () {
                $('input[name=nit]').val('');
                this.reset();
            });
            //initialize();
        }
    });
    
    $("#dialogClientE").dialog({
        resizable: false,
        autoOpen: false,
        closeOnEscape: false,
        dialogClass: "no-close",
        modal: true,
        buttons: {
            "Editar": function () {
                $("#f_clientE").submit();
            },
            Cancel: function () {
                $(this).dialog("close");
//                $("#createPersonDialog").dialog("close");
            }
        },
        open: function (event, ui) {
            $('#f_client').each(function () {
                $('input[name=nit]').val('');
                this.reset();
            });
            //initialize();
        }
    });

    $("#f_client").submit(function (event) {
//        var myObj = {person: "", clientId: "35", nit: "119", razonSocial: "Ferreteria Pp"};
        if ($(this).valid()) {
            $.ajax({
                url: contextServices + "client",
                data: $().stripJsonToString($(this).serializeObject()),
//                data: JSON.stringify($(this).serialize()),
                contentType: "application/json",
                type: 'POST',
//                processData: false,
                dataType: 'json',
                success: function (data) {
                    if (typeof clientRefresh === 'function') {
                        clientRefresh();
                    }
                    
                    $("#namePersonId").val($("input[name='razonSocial']").val());
                    
                    $("#dialogClient").dialog("close");
                }
            });
        }
        event.preventDefault();
    });
    
    $("#f_clientE").submit(function (event) {
        if ($(this).valid()) {
            $.ajax({
                url: contextServices + "client/upload",
                data: $().stripJsonToString($(this).serializeObject()),
                contentType: "application/json",
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    if (typeof clientRefresh === 'function') {
                        clientRefresh();
                    }
                    
                    $("#namePersonId").val($("input[name='razonSocial']").val());
                    
                    $("#dialogClientE").dialog("close");
                }
            });
        }
        event.preventDefault();
    });

    $("#f_createPeson").submit(function (event) {
        if ($(this).valid()) {
            console.log($(this).serializeObject());
            $.ajax({
                url: contextPath + "/personalpp/personCreate.html",
                data: {person: $().stripJsonToString($(this).serializeObject())},
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    $("#client_perId").val(data.perId);
                    $("input[name=razonSocial]").val("");
                    $("input[name=address]").val(data.address);
                    $("input[name=phone]").val(data.phoneAddress);
                    $("input[name=fax]").val(0);
                    $("input[name=url]").val("");
                    $("input[name=email]").val(data.email);
//                        $("input[name=latitud]").val(data.latitud);
//                        $("input[name=longitud]").val(data.longitud);
//                        $("input[name=person]").val(data.person);
                    $("input[name=clientId]").val(0);
                    $("#createPersonDialog").dialog("close");
                }
            });
            
            event.preventDefault();
        }else{
            console.log("elseee");
            
        }
    });

});

(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

var Client = Backbone.Model.extend({
    defaults: {
        clientId: null,
        razonSocial: null,
        address: null,
        latitud: null,
        longitud: null,
        phone: "",
        fax: "",
        url: null,
        email: null,
        nit: 0,
        personId: 0,
        names: "",
        firtsName: "",
        lastname: "",
        ci: 0,
        addressP: "",
        phoneAddressP: 0,
        phoneMobilP: 0,
        emailP: 0,
    }
});

function createClient() {
    $("#dialogClient").dialog("open");
}

function initialize() {
    var lat = $('#client_lat').val();
    var lon = $('#client_lon').val();
    ;
    var mapOptions = {
        center: new google.maps.LatLng(lat, lon),
        zoom: 13,
//          mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_canvas"),
            mapOptions);

    var myLatlng = new google.maps.LatLng(lat, lon);
    var marker = new google.maps.Marker({
        position: myLatlng,
        map: map,
        title: 'Hello World!',
        draggable: true,
        animation: google.maps.Animation.DROP
    });

    google.maps.event.addListener(marker, 'dragend', function () {
        //updateMarkerStatus('Drag ended');
        console.log(marker.getPosition());
        $('#client_lat').val(this.getPosition().lat());
        $('#client_lon').val(this.getPosition().lng());
    });
}

function initializeE() {
    var lat = $('#client_latE').val();
    var lon = $('#client_lonE').val();
    ;
    var mapOptions = {
        center: new google.maps.LatLng(lat, lon),
        zoom: 13,
//          mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_canvasE"),
            mapOptions);

    var myLatlng = new google.maps.LatLng(lat, lon);
    var marker = new google.maps.Marker({
        position: myLatlng,
        map: map,
        title: 'Hello World!',
        draggable: true,
        animation: google.maps.Animation.DROP
    });

    google.maps.event.addListener(marker, 'dragend', function () {
        //updateMarkerStatus('Drag ended');
        console.log(marker.getPosition());
        $('#client_latE').val(this.getPosition().lat());
        $('#client_lonE').val(this.getPosition().lng());
    });
}

function getFirstName1(ci) {
    if (validNumCi($(ci).val())) {
//        var dd = {"ci": $(ci).val()};
        $.ajax({
            url: contextServices + "client/getClient",
            data: {"nit": $(ci).val()},
//            data: {person: $().stripJsonToString($(this).serializeObject())},
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (data != null) {
                    $("#f_clientE input[name=razonSocial]").val(data.razonSocial);
                    $("#f_clientE input[name=address]").val(data.address);
                    $("#f_clientE input[name=phone]").val(data.phone);
                    $("#f_clientE input[name=fax]").val(data.fax);
                    $("#f_clientE input[name=url]").val(data.url);
                    $("#f_clientE input[name=email]").val(data.email);
                    $("#f_clientE input[name=latitud]").val(data.latitud);
                    $("#f_clientE input[name=longitud]").val(data.longitud);
                    $("#f_clientE input[name=person]").val(data.person);
                    $("#f_clientE input[name=clientId]").val(data.clientId);
                    cllientIdE = data.clientId;
                    
                    $("#f_clientE input[name=nameInvoice]").val(data.nameInvoice);
                    $("#f_clientE input[name=attendant]").val(data.attendant);
                    $("#f_clientE input[name=zone]").val(data.zone);
                    //initializeE();
                } else {
                    $('#').val(ci);
                    $('#f_createPeson').each(function () {
                        this.reset();
                    });
                }
            }
        });
    }
}

function getPersonObject(ci){
    getPerson($(ci).val());
}
function getPerson(ci) {
    $.ajax({
        url: contextPath + "/personalpp/getPerson.html",
        data: {"ci": ci},
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            alert("sss:: "+ci);
            if (data !== null) {
                $("input[name=ci]").val(data.ci);
                $("input[name=names]").val(data.names);
                $("input[name=firstname]").val(data.firstname);
                $("input[name=lastname]").val(data.lastname);
                $("input[name=address]").val(data.address);
                $("input[name=phoneAddress]").val(data.phoneAddress);
                $("input[name=phoneMobil]").val(data.phoneMobil);
                $("input[name=email]").val(data.email);
                $("input[name=email]").val(data.email);
                $("input[name=perId]").val(data.perId);
            } else {
                $("input[name=names]").val("");
                $("input[name=firstname]").val("");
                $("input[name=lastname]").val("");
                $("input[name=address]").val("");
                $("input[name=phoneAddress]").val("");
                $("input[name=phoneMobil]").val("");
                $("input[name=email]").val("");
                $("input[name=email]").val("");
                $("input[name=perId]").val("");
            }
        }
    });
}

function validNumCi(ci) {
    var validCi = false;
    if (!isNaN(ci) && isNumber(ci)) {
        var dd = parseInt(ci);
        if (dd >= 0) {
            validCi = true;
        }
    }
    return validCi;
}

function isNumber(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}