<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/js/ui/jquery.ui.draggable.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.position.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.resizable.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.dialog.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.effects.core.js'/>"></script>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/factory/factoryCreate.js'/>"></script>
<form id="f_createfactory">
    <fieldset>
        <legend></legend>
        <table>
            <tr>
                <td>
                    <label id="lname" for="i_factory_name">Nombre:</label>
                </td>
                <td>
                    <input id="i_factory_name_id" name="i_factory_name" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                </td>
            </tr>
        </table>
    </fieldset>
</form>
<p id="pErrorMensajeCrear" class="errorMenssage"></p>