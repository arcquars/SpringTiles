<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/category/createCategory.js'/>"></script>
<form id="f_createcategory">
    <fieldset>
        <legend></legend>
        <table>
            <tr>
                <td>
                    <label id="lname" for="iname_cat_create">Nombre:</label>
                </td>
                <td>
                    <input id="iname_cat_create" name="iname_cat_create" type="text" class="ui-corner-all minputtext"/>
                </td>
            </tr>
        </table>
    </fieldset>
</form>
<p id="pErrorMensajeCrear" class="errorMenssage"></p>