<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/inventory/createRefund.js'/>"></script>
<br/>
<c:set var="hidden" value="hidden" scope="page" />
<c:if test="${Error != null}">
    <c:set var="hidden" value="visible" scope="page" />
</c:if>
<h1 style="padding-left: 0px;">Devolucion de Productos: </h1>
<div align="center">
    <form:form modelAttribute="listProductRefund" method="post" action="createRefund.html" >
        <br/>
        <form:hidden path="branchId" />
        <table width="550px" style="width: 550px;" id="t_product">
            <thead width="550px" style="width: 550px;">
                <tr>
                    <th>Nombre Producto</th>
                    <th>Codigo</th>
                    <th>Categoria</th>
                    <th>Fabricante</th>
                    <th>Q. Limite</th>
                    <th>Cantidad</th>
                </tr>
            </thead>
            <tbody width="550px" style="width: 550px;">
                <c:forEach var="listRpd1" items="${listProductRefund.list}" varStatus="itemStatusRpd" >
                    <tr>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="list[${itemStatusRpd.index}].productName" cssStyle="width:100px;" readonly="true"/>
                            <form:hidden path="list[${itemStatusRpd.index}].productId" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="list[${itemStatusRpd.index}].productCodOrigin" cssStyle="width:100px;" readonly="true"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="list[${itemStatusRpd.index}].categoryName" cssStyle="width:100px;" readonly="true"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="list[${itemStatusRpd.index}].fatoryName" readonly="true" cssStyle="width:100px;" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all minputtext" path="list[${itemStatusRpd.index}].limitedAmount" cssStyle="width:50px;" readonly="true" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all" path="list[${itemStatusRpd.index}].amount" cssStyle="width:50px;"  onchange="validAmount(this);"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
        <div style="width: 580px; visibility: ${hidden}">
            <p class="error">${Error}</p>
        </div>
        <br/>
        <div style="width: 600px;">
            <div align="right" style="width: 100%;">
                <input type="submit" class="mbutton" style="width: 120px;" value="Grabar" />
                <input type="button" class="mbutton" style="width: 120px;" value="Cancelar" onclick="redirectInventory();" />
            </div>
        </div>
    </form:form>
</div>