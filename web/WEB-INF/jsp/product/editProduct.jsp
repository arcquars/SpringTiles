<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/js/ui/jquery.ui.draggable.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.position.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.resizable.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.dialog.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.effects.core.js'/>"></script>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/product/createProduct.js'/>"></script>
<form id="f_editproduct">
    <fieldset>
        <legend></legend>
        <table width="100%">
            <tr>
                <td>
                    <label id="lnames" for="iname">Nombre Producto:</label>
                </td>
                <td>
                    <input id="iname" name="iname" type="text" value="${pDto.name}" maxlength="100" class="ui-corner-all minputtext"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label id="fp_l_codorigen" for="fp_i_codorigen">Codigo Origen:</label>
                </td>
                <td>
                    <input id="fp_i_codorigen" name="fp_i_codorigen" type="text" value="${pDto.codOrigin}" maxlength="100" class="ui-corner-all minputtext"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label id="fp_l_category" for="fp_i_category">Categoria:</label>
                </td>
                <td>
                    <table width="100%">
                        <tr>
                            <td style="width: 140px;">
                                <select id="fp_s_category" name="fp_s_category" class="ui-corner-all mselect" style="width: 140px;">
                                    <c:forEach var="itemCategory" items="${mapCategory}" >
                                        <c:if test="${pDto.categoryId==itemCategory.key}">
                                            <option value="${itemCategory.key}" selected>${itemCategory.value}</option>
                                        </c:if>
                                        <c:if test="${pDto.categoryId!=itemCategory.key}">
                                            <option value="${itemCategory.key}" >${itemCategory.value}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label id="fp_l_factory" for="fp_s_factory">Fabricante:</label>
                </td>
                <td>
                    <table width="100%">
                        <tr>
                            <td style="width: 140px;">
                                <select id="fp_s_factory" name="fp_s_factory" class="ui-corner-all mselect" style="width: 140px;">
                                    <c:forEach var="itemFactory" items="${mapFactory}" >
                                        <c:if test="${pDto.factoryId==itemFactory.key}">
                                            <option value="${itemFactory.key}" selected>${itemFactory.value}</option>
                                        </c:if>
                                        <c:if test="${pDto.factoryId!=itemFactory.key}">
                                            <option value="${itemFactory.key}" >${itemFactory.value}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label id="fp_l_description" for="fp_i_description">Descripcion:</label>
                </td>
                <td>
                    <input id="fp_i_description" name="fp_i_description" type="text" value="${pDto.description}" maxlength="100" class="ui-corner-all minputtext"/>
                </td>
            </tr>
        </table>
    </fieldset>

</form>