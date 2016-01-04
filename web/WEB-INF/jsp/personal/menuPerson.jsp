<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/person/personCreate.js'/>"></script>
<script>
    $("#coolMenu li").each(function(i, item){
        var href = $(item).children().attr('href');
        if(href.indexOf('personalpp') != -1) {
            $(item).children().addClass("coolMenuSelect");
        }else{
            $(item).children().removeClass("coolMenuSelect");
        }
    });
</script>

<div style="height: 47px;"></div>
<div style="background: #cc8632;">
    <br/>
    <button id="bCreatePerson" class="mbutton" style="width: 120px;">Crear Empleado</button>
    <div style="height: 5px;"></div>
    <button class="mbutton" style="width: 120px;">Busqueda</button>
    <br/>
    <br/>
</div>
<div id="divPersonCreate" title="Nuevo Personal" style="display: none;">
    <form id="f_createperson">
        <fieldset>
            <legend></legend>
            <table>
                <tr>
                    <td>
                        <label id="lnames" for="inames">Nombres:</label>
                    </td>
                    <td>
                        <input id="inames" name="inames" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_firstname" for="fp_i_firstname">Apellido Paterno:</label>
                    </td>
                    <td>
                        <input id="fp_i_firstname" name="fp_i_firstname" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_lastname" for="fp_i_lastname">Apellido Materno:</label>
                    </td>
                    <td>
                        <input id="fp_i_lastname" name="fp_i_lastname" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_ci" for="fp_i_ci">Ci:</label>
                    </td>
                    <td>
                        <input id="fp_i_ci" name="fp_i_ci" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_address" for="fp_i_address">Direccion:</label>
                    </td>
                    <td>
                        <input id="fp_i_address" name="fp_i_address" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_phone_home" for="fp_i_phone_home">Tel. Domicilio:</label>
                    </td>
                    <td>
                        <input id="fp_i_phone_home" name="fp_i_phone_home" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_phone_mobil" for="fp_i_phone_mobil">Tel. Mobil:</label>
                    </td>
                    <td>
                        <input id="fp_i_phone_mobil" name="fp_i_phone_mobil" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_email" for="fp_i_email">Correo Electronico:</label>
                    </td>
                    <td>
                        <input id="fp_i_email" name="fp_i_email" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_position" for="fp_s_position">Funcion:</label>
                    </td>
                    <td>
                        <select id="fp_s_position" name="fp_s_position" style="width: 135px" class="ui-corner-all minputtext">
                            <c:forEach var="valueMap" items="${allPosition}">
                                <option value="${valueMap.key}">${valueMap.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_branch" for="fp_s_branch">Tienda:</label>
                    </td>
                    <td>
                        <select id="fp_s_branch" name="fp_s_branch" style="width: 135px" class="ui-corner-all minputtext">
                            <c:forEach var="valueMap" items="${allBranch}">
                                <option value="${valueMap.key}">${valueMap.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
        </fieldset>

    </form>
</div>