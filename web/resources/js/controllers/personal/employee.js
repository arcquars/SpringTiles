var employee = null;
var view = null;

$(document).ready(function () {
    $("#createEmployeeDialog").dialog({
        resizable: false,
        autoOpen: false,
        closeOnEscape: false,
        dialogClass: "no-close",
        modal: true,
        buttons: {
            "Crear/Editar": function () {
                $("#f_createEmployee").submit();
            },
            Cancel: function () {
                $(this).dialog("close");
            }
        },
        open: function (event, ui) {
            clearInputs();
        }
    });

    $("#f_createEmployee").validate({
        rules: {
            ci: {
                number: true,
                minlength: 5,
                required: true,
                remote: contextPath + "/personalpp/ciExistEmployee.html"
            },
            names: "required",
            firstname: "required",
            password: {
                required: true,
                minlength: 5
            },
            passwordRepit: {
                minlength: 5,
                equalTo: "#password"
            },
            phone_address: {
                number: true
            },
            phone_mobil: {
                number: true
            },
            email: {
                email: true
            },
        },
        messages: {
            ci: {
                number: "Solo numeros",
                required: "No puede estar vacio",
                remote: "Existe este CI como empleado",
                minlength: "Inserte minimo 5 caracteres"
            },
            names: "No puede estar vacio",
            firstname: "No puede estar vacio",
            password: {
                required: "No puede estar vacio",
                minlength: "Inserte minimo 5 caracteres"
            },
            passwordRepit: {
                minlength: "Inserte minimo 5 caracteres",
                equalTo: "Cadena direrente"
            },
            phone_address: {
                number: "Solo numeros"
            },
            phone_mobil: {
                number: "Solo numeros"
            },
            email: "Formato de correo no valido",
        }
    });

    $("#f_createEmployee").submit(function (event) {
        if ($(this).valid()) {
//            $.ajax({
//                url: contextPath + "/personalpp/uploadEmployeeAjax.html",
//                data: {client: $().stripJsonToString($(this).serializeObject())},
//                type: 'POST',
//                dataType: 'html',
//                success: function (data) {
//                    if (typeof employeeRefresh === 'function') {
//                        employeeRefresh();
//                        $("#createEmployeeDialog").dialog("close");
//                    }
//                }
//            });
        }
        event.preventDefault();
    });

    var Vista = Backbone.View.extend({
        el: '#createEmployeeDialog',
        events: {"submit #f_createEmployee": "save1"},
        initialize: function () {
//        _.bindAll(this, 'save');
//        this.listenTo(this.model, "change", this.render);
//        _.bindAll(this, 'save1');
        },
        render: function () {
//        this.$el.html(this.template(this.model.attributes));
            return this;
        },
        save1: function (event) {
            this.model.set("ci", $("#f_createEmployee input[name=ci]").val());
            this.model.set("names", $("#f_createEmployee input[name=names]").val());
            this.model.set("firstname", $("#f_createEmployee input[name=firstname]").val());
            this.model.set("lastname", $("#f_createEmployee input[name=lastname]").val());
            this.model.set("password", $("#f_createEmployee input[name=password]").val());
            this.model.set("address", $("#f_createEmployee input[name=address]").val());
            this.model.set("phoneAddress", $("#f_createEmployee input[name=phone_address]").val());
            this.model.set("phoneMobil", $("#f_createEmployee input[name=phone_mobil]").val());
            this.model.set("email", $("#f_createEmployee input[name=email]").val());
            this.model.set("position", $("#f_createEmployee select[name=position] option:selected").text());
            this.model.set("branchId", $("#f_createEmployee select[name=branch]").val());
            
            if (this.model.isValid()) {
                this.model.save();
                employeeRefresh();
                $("#createEmployeeDialog").dialog("close");
            }
            event.preventDefault();
        }
    });

    employee = new Employee();

    if (!employee.isValid()) {
        alert(employee.get("names") + " " + employee.validationError);
    }
    
    view = new Vista({model: employee});
    //employee.save();

});

function createEmployee() {
    $("#createEmployeeDialog").dialog("open");
}

function getPerson(inputCi) {
    var ci = $(inputCi).val();
    if (isNumeric(ci)) {
        $.ajax({
            url: contextPath + "/personalpp/getPerson.html",
            data: {"ci": ci},
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                if (data !== null) {
                    $("input[name=names]").val(data.names);
                    $("input[name=firstname]").val(data.firstname);
                    $("input[name=lastname]").val(data.lastname);
                    $("input[name=address]").val(data.address);
                    $("input[name=phone_address]").val(data.phoneAddress);
                    $("input[name=phone_mobil]").val(data.phoneMobil);
                    $("input[name=email]").val(data.email);
                } else {
                    console.log("elseeeeee");
                }
            }
        });
    }
}

function clearInputs() {
    $('#f_createEmployee')[0].reset();
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

var Employee = Backbone.Model.extend({
    default: {
        perId: null,
        names: "",
        firstname: null,
        lastname: null,
        ci: 0,
        address: null,
        phoneAddress: 0,
        phoneMobil: 0,
        email: null,
        position: null,
        branchId: null,
        del: 0
    },
    url: function () {
        return this.perId ? contextServices + 'employee/' + this.perId : contextServices + 'employee';
    }
});