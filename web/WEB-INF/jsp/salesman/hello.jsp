<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/js/ui/jquery.ui.draggable.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.position.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.resizable.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.dialog.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.effects.core.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/salesman/salesman_search.js'/>"></script>
<script>
    var strsearch = '${searchText}';
</script>
<c:choose>
    <c:when test="${visibleTable == 'true'}">
        <c:set var="dTable" value="inline" />
        <c:set var="dTableEmpty" value="none" />
    </c:when>
    <c:otherwise>
        <c:set var="dTable" value="none" />
        <c:set var="dTableEmpty" value="inline" />
    </c:otherwise>
</c:choose>
<br/>
<input type="hidden" value="${sessionScope.branch.branchId}" id="branchId" />
<div  align="center" style="width: 100%;">
    <div style="width: 520px;">
        <form:form action="salesman.html" commandName="productSearchForm">
            <table>
                <tbody style="width: 500px !important;">
                    <tr>
                        <td style="width: 125px !important;"></td>
                        <td width="125px"></td>
                        <td width="125px"></td>
                        <td width="125px"></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;"><p style="width: 120px;">Buscar Producto por: </p></td>
                        <td>
                            <form:select path="criteria" cssStyle="width: 120px" cssClass="ui-corner-all mselect" >
                                <c:forEach var="item" items="${mapcriteria}" >
                                    <form:option value="${item.key}">${item.value}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td>
                            <form:input path="textSearch" size="16px" cssClass="ui-corner-all minputtext"/>
                            <form:select path="category" cssStyle="width: 130px; display:none" cssClass="ui-corner-all mselect">
                                <c:forEach var="item" items="${mapCategory}" >
                                    <form:option value="${item.key}">${item.value}</form:option>
                                </c:forEach>
                            </form:select>
                            <form:errors path="textSearch" cssClass="errorMenssage" />
                        </td>
                        <td>
                            <button class="mbutton" style="width: 120px;" type="submit">Buscar</button>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </form:form>
        <div style="display: ${dTable}; width: 520px" align="center">
            <br/>
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="listPro" width="520">
                <thead>
                <th>Nombre</th>
                <th>Codigo Origen</th>
                <th>Categoria</th>
                <th>Fabricante</th>
                <th>Total</th>
                </thead>
                <tbody>
                    <c:forEach var="valueProduct" items="${listProduct}" >
                        <tr>
                            <td><p>${valueProduct.productName}<strong style="visibility: hidden;">${valueProduct.productId}</strong></p></td>
                            <td>${valueProduct.productCodOrigin}</td>
                            <td>${valueProduct.category}</td>
                            <td>${valueProduct.factoryName}</td>
                            <td>${valueProduct.total}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br/>
            <br/>
            <div style="width: 500px; visibility: hidden;">
                <p id="pError" class="error"></p>
                <br/>
            </div>
            <div align="right">
                <input type="button" value="Vender" class="mbutton" onclick="fnViewSelectForSaleInventory();return false;" />
            </div>
        </div>
        <div style="display: ${dTableEmpty}; width: 520px" align="center">
            <p class="error">Sin datos en la busqueda!!</p>
        </div>
        <br/>
    </div>

</div>
<br/>