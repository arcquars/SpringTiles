<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/js/ui/jquery.ui.draggable.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.position.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.resizable.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.dialog.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.effects.core.js'/>"></script>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/provider/providerCreate.js'/>"></script>
<script>
    
</script>

<form id="f_create_provider">
    <fieldset>
        <legend></legend>
        <table>
            <tbody>
                <tr>
                    <td>
                        <label id="lname" for="i_cprovider_businessname">Nombre de la Empresa:</label>
                    </td>
                    <td>
                        <input id="i_cprovider_businessname" name="i_cprovider_businessname" type="text" value="" class="ui-corner-all minputtext"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label id="lnames" for="inames">Nombres del Representante:</label>
                    </td>
                    <td>
                        <input id="inames" name="inames" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                    <td><em></em></td>
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
            </tbody>
        </table>
    </fieldset>
</form>
