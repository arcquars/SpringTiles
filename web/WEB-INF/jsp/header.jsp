<%@page import="org.lugubria.sys.domain.Person"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
    $(function() {
        $(".mbutton").button();
        $("a", ".demo").click(function() {
            return false;
        });
        
        $.fn.serializeObject = function () {
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

    $.fn.stripJsonToString = function (json)
    {
        return JSON.stringify(json).replace(',', ', ').replace('[', '').replace(']', '');
    };
    });
    var contextPath = "<%= session.getAttribute("nameProject")%>";
    var contextServices = "<%= session.getAttribute("urlServices")%>";
    var userId = "<%= session.getAttribute("userId")%>";
    var languajeSpanish = {
	"sProcessing":     "Procesando...",
	"sLengthMenu":     "Mostrar _MENU_ registros",
	"sZeroRecords":    "No se encontraron resultados",
	"sEmptyTable":     "Ningún dato disponible en esta tabla",
	"sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
	"sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
	"sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
	"sInfoPostFix":    "",
	"sSearch":         "Buscar:",
	"sUrl":            "",
	"sInfoThousands":  ",",
	"sLoadingRecords": "Cargando...",
	"oPaginate": {
		"sFirst":    "Primero",
		"sLast":     "Último",
		"sNext":     "Siguiente",
		"sPrevious": "Anterior"
	},
	"oAria": {
		"sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
		"sSortDescending": ": Activar para ordenar la columna de manera descendente"
	}
};
</script>
<table class="cleartable1" align="right">
    
    <tr>
        <td class="cleartd" style="text-align: right; width: 85px"><h3 class="clearhs h3color">Bienvenido: <strong><%= ((Person)session.getAttribute("Person")).getNames()%></strong></h3></td>
    </tr>
    <tr style="background: #CC8632;">
        <td style="background: #CC8632; text-align: right;" class="cleartd">
            <a href="/SpringTiles/loginaa.html">
                <span class="text link_logout">Salir</span>
            </a>
        </td>
    </tr>
</table>