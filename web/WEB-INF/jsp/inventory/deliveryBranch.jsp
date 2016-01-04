<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="no_rel.css" type="text/css" media="print" />
<script src="<c:url value='/resources/js/jquery.PrintArea.js'/>"></script>

<script src="<c:url value='/resources/js/controllers/inventory/inventoryController.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/delivery/deliveryBranch.js'/>"></script>
<c:set var="hidden" value="hidden" scope="page" />
<c:if test="${error != null}">
    <c:set var="hidden" value="visible" scope="page" />
</c:if>
<br/>
<h1 style="padding-left: 0px;">Entrega de productos a tienda: </h1>
<div align="center">
    <form:form modelAttribute="deliveryBranch" method="post" action="deliveryBranch.html" >
        <div style="width: 550px;">
            <div align="left" style="width: 100%;">
                <form:label path="branchId">Sucursal:</form:label>
                <form:select path="branchId" cssClass="ui-corner-all mselect">
                    <form:options items="${allBranch}" itemValue="key" itemLabel="value" />
                </form:select>
            </div>
        </div>
        <br/>
        <table width="550px" style="width: 550px;" id="t_product">
            <thead width="550px" style="width: 550px;">
                <tr>
                    <th>Nombre Producto</th>
                    <th>Codigo</th>
                    <th>Categoria</th>
                    <th>Fabricante</th>
                    <th>Cant. Disp.</th>
                    <th>Entrega</th>
                </tr>
            </thead>
            <tbody width="550px" style="width: 550px;">
                <c:forEach items="${deliveryBranch.deliveryProduct}" varStatus="itemStatus" >
                    <tr>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="deliveryProduct[${itemStatus.index}].productName" cssStyle="width:100px;" readonly="true"/>
                            <form:hidden path="deliveryProduct[${itemStatus.index}].productId" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="deliveryProduct[${itemStatus.index}].codOrigin" cssStyle="width:100px;" readonly="true"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="deliveryProduct[${itemStatus.index}].categoryName" cssStyle="width:100px;" readonly="true"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="deliveryProduct[${itemStatus.index}].factoryName" readonly="true" cssStyle="width:100px;" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all minputtext" path="deliveryProduct[${itemStatus.index}].availableStock" cssStyle="width:50px;" readonly="true" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all minputtext" path="deliveryProduct[${itemStatus.index}].amount" cssStyle="width:50px;" onchange="validAmount(this);" />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div style="width: 550px; visibility: ${hidden};">
                    <p class="error">${error}</p>
                    <p id="pError" class="error">sss</p>
        </div>
        <br/>

        <br/>
        <div style="width: 580px;">
            <div align="right" style="width: 100%;">
                <input id="i_save" type="submit" class="mbutton" value="Grabar" />
                <!--<input id="i_saveAndPrint" type="button" class="mbutton" value="Imprimir" />-->
                <input type="button" class="mbutton" value="Cancelar" onclick="clickCancelDelivery();
                        return false;" />
            </div>
        </div>
    </form:form>
</div>
<div title="entrega de Producto" id="d_deliveryProduct" style="display: none;" class="PrintArea area1 both">
    <div>
        <div align="left" style="width: 100%;">
            <p id="p_branch"></p>
            <p id="p_date"><b>Fecha: </b>${dateNow}</p>
        </div>
    </div>
    <br/>
    <table id="t_deliveryProductPrint" class="table table-bordered">
        <thead>
            <tr>
                <th>Nombre Producto</th>
                <th>Codigo</th>
                <th>Categoria</th>
                <th>Fabricante</th>
                <th>Cant. Disp.</th>
                <th>Entrega</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>