<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script>
    $("#coolMenu li").each(function(i, item){
        var href = $(item).children().attr('href');
        if(href.indexOf('branchpp') != -1) {
            $(item).children().addClass("coolMenuSelect");
        }else{
            $(item).children().removeClass("coolMenuSelect");
        }
    });
</script>
<div style="height: 47px;"></div>
<div style="background: #cc8632;">
    <br/>
    <button id="bCreateBranch" class="mbutton" style="width: 120px;">Crear Sucursal</button>
    <div style="height: 5px;"></div>
    <button class="mbutton" style="width: 120px;">Busqueda</button>
    <br/>
    <br/>
</div>
<div style="height: 15px;"></div>
<div id="divBranchCreate" title="Crear Sucursal" style="display: none;">
    <form id="f_createBranch">
        <fieldset>
                <legend></legend>
                <table>
                    <tr>
                        <td>
                            <label id="l_create_name" for="i_create_name">Nombre:</label>
                        </td>
                        <td>
                            <input id="i_create_name" name="i_create_name" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="l_create_address" for="i_create_address">Direccion:</label>
                        </td>
                        <td>
                            <input id="i_create_address" name="i_create_address" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="l_create_phone" for="i_create_phone">Telefono:</label>
                        </td>
                        <td>
                            <input id="i_create_phone" name="i_create_phone" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
    </form>
    <p id="pErrorMensajeCrear" class="errorMenssage"></p>
</div>