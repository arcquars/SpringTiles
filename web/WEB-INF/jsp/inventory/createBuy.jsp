<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!--
--> 
<script src="<c:url value='/resources/js/jquery-tooltip/js/jtip.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/resources/js/jquery-tooltip/css/global.css' />" type="text/css" />
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/inventory/createProduct.js'/>"></script>
<br/>
<c:set var="hidden" value="hidden" scope="page" />
<c:if test="${Error != null}">
    <c:set var="hidden" value="visible" scope="page" />
</c:if>
<h1 style="padding-left: 0px;">Compra de Productos: </h1>
<div align="center">
    <form:form modelAttribute="lisRpdObject" method="post" action="createBuy.html" >
        <table width="550px" style="width: 550px;" id="t_product">
            <thead width="550px" style="width: 550px;">
                <tr>
                    <th>Nombre Producto</th>
                    <th>Codigo</th>
                    <th>Descripcion</th>
                    <th>Fabricante</th>
                    <th>Cantidad</th>
                    <th>Precio U.</th>
                    <th>Total</th>
                    <th>Hist. Precio</th>
                </tr>
            </thead>
            <tbody width="550px" style="width: 550px;">
                <c:forEach var="listRpd1" items="${lisRpdObject.lisRpd}" varStatus="itemStatusRpd" >
                    <tr>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="lisRpd[${itemStatusRpd.index}].productName" cssStyle="width:100px;" readonly="true"/>
                            <form:hidden path="lisRpd[${itemStatusRpd.index}].productId" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="lisRpd[${itemStatusRpd.index}].codOrigin" cssStyle="width:100px;" readonly="true"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread toltipArcsys" path="lisRpd[${itemStatusRpd.index}].detail" cssStyle="width:100px;" readonly="true" title="" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="lisRpd[${itemStatusRpd.index}].factoryName" readonly="true" cssStyle="width:100px;" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all minputtext" path="lisRpd[${itemStatusRpd.index}].amount" cssStyle="width:50px;" onchange="trsss(this);" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all minputtext" path="lisRpd[${itemStatusRpd.index}].price" cssStyle="width:50px;" onchange="treee(this);" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="lisRpd[${itemStatusRpd.index}].total" cssStyle="width:50px;"  onchange="trTotal();" readonly="true"/>
                        </td>
                        <td>
                            <span class="formInfo">
                                <!--<a onmouseover="overTooltip();" href="../loginaa/productAverage.html?width=200&id=${listRpd1.productId}" class="jTip" id="${listRpd1.productId}" name="historia de compras:">?</a>-->
                                <a onmouseover="overTooltip(this);" href="#" id="${listRpd1.productId}" class="classTooltip">?</a>
                                <input type="hidden" value="${listRpd1.productId}" />
                            </span>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
        <div style="width: 580px; visibility: ${hidden}">
            <p class="error">${Error}</p>
        </div>
        <br/>
        <div style="width: 580px;">
            <div align="right" style="width: 100%;">
                <form:label path="providerId">Proveedor:</form:label>
                <form:select path="providerId" cssClass="ui-corner-all mselect">
                    <form:options items="${allProvider}" itemValue="key" itemLabel="value"/>
                </form:select>
                <form:label path="totalBuy">TOTAL:</form:label>
                <form:input cssClass="ui-corner-all onlyread" readonly="true" path="totalBuy" cssStyle="width:50px;" id="totalBuy"/>
            </div>
            <div align="right">
                <form:radiobutton path="credit" value="F" htmlEscape="checked" onchange="changeHidden(this);" />Contado
                <form:radiobutton path="credit" value="T" onchange="changeVisible(this);" />Credito
                <form:input path="payment" cssClass="ui-corner-all minputtext" cssStyle="width:50px; visibility:hidden;" onchange="changePayment(this);" />
            </div>
        </div>
            <br/>
        <div style="width: 580px;">
            <div align="right" style="width: 100%;">
                <input type="submit" class="mbutton" style="width: 120px;" value="Grabar" />
                <input type="button" class="mbutton" style="width: 120px;" value="Cancelar" onclick="redirectInventory();" />
            </div>
        </div>
        <div style="height: 10px;"></div>
    </form:form>
</div>
<script>
    $(function() {
        $(".toltipArcsys").each(function(){
            $(this).attr("title", $(this).val());
        });
        $(".toltipArcsys").tooltip({
          track: true
        });
        
        
  });
</script>