<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="<c:url value='/resources/styles/PrintArea.css' />" type="text/css" />

<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/inventory/createReserve.js'/>"></script>
<script src="<c:url value='/resources/js/jquery.PrintArea.js'/>"></script>
<br/>
<c:set var="hidden" value="hidden" scope="page" />
<c:if test="${Error != null}">
    <c:set var="hidden" value="visible" scope="page" />
</c:if>
<input type="hidden" value="${branchName}" id="hBranchName" />
<h1 style="padding-left: 0px;">Reserva para la sucursal: ${branchName}</h1>
<div align="center">
    <form:form modelAttribute="listProductReserve" method="post" action="createReserve.html" >
        <div align="left" style="padding-left: 45px;">
            <!--<p>Fecha de Venta: <input type="text" id="datepicker" readonly="true" style="width: 100px;" class="minputtext" /> </p>-->
            <table style="width: 80%;" class="cleartable">
                <tr>
                    <td style="text-align: right;">
                        Cliente:
                    </td>
                    <td colspan="3">
                        <input type="text" style="width: 100%;" class="ui-corner-all minputtext" id="namePersonId" />
                        <form:hidden path="clientId" style="width: 100px;" class="ui-corner-all minputtext" id="clientId" />
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">
                        Fecha de Venta:
                    </td>
                    <td>
                        <form:input path="dateSale" readonly="true" style="width: 100%;" class="minputtext ui-corner-all" id="datepicker" /> 
                    </td>
                    <td style="text-align: right;">
                        Fecha de recojo:
                    </td>
                    <td style="text-align: right;">
                        <form:input path="dateClose" readonly="true" style="width: 100%;" class="minputtext ui-corner-all" id="datepickerClose" />
                    </td>
                </tr>
            </table>
        </div>
        <br/>
        <table width="550px" style="width: 550px;" id="t_product">
            <thead width="550px" style="width: 550px;">
                <tr>
                    <th>Nombre Producto</th>
                    <th>Codigo</th>
                    <th>Descripcion</th>
                    <th>Fabricante</th>
                    <th>Q. Limite</th>
                    <th>Precio Minimo</th>
                    <th>Cantidad</th>
                    <th>Precio Venta</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody width="550px" style="width: 550px;">
                <c:forEach var="listRpd1" items="${listProductReserve.listProductSale}" varStatus="itemStatusRpd" >
                    <tr>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="listProductSale[${itemStatusRpd.index}].productName" cssStyle="width:100px;" readonly="true"/>
                            <form:hidden path="listProductSale[${itemStatusRpd.index}].productId" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="listProductSale[${itemStatusRpd.index}].productCodOrigin" cssStyle="width:100px;" readonly="true"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread toltipArcsys" path="listProductSale[${itemStatusRpd.index}].detail" cssStyle="width:100px;" readonly="true"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="listProductSale[${itemStatusRpd.index}].fatoryName" readonly="true" cssStyle="width:100px;" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all minputtext" path="listProductSale[${itemStatusRpd.index}].limitedAmount" cssStyle="width:50px;" readonly="true" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all minputtext" path="listProductSale[${itemStatusRpd.index}].higherCost" cssStyle="width:50px;" readonly="true"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all" path="listProductSale[${itemStatusRpd.index}].amount" cssStyle="width:50px;"  onchange="validAmount(this);"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all" path="listProductSale[${itemStatusRpd.index}].price" cssStyle="width:50px;"  onchange="validPrice(this);"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all minputtext" path="listProductSale[${itemStatusRpd.index}].total" cssStyle="width:50px;" onchange="trTotal();" readonly="true"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
        <div style="width: 580px; visibility: ${hidden}">
            <p class="error">${Error}</p>
        </div>
        <br/>
        <div style="width: 695px;">
            <div align="right" style="width: 100%;">
                <form:label path="total">TOTAL:</form:label>
                <form:input cssClass="ui-corner-all minputtext" readonly="true" path="total" cssStyle="width:50px; text-align: right;" id="totalBuy"/>
                <form:hidden path="branchId" />
            </div>
            <div style="height: 3px;"></div>
            <div align="right" style="width: 100%;">
                <form:label path="total">A cuenta:</form:label>
                <form:input onchange="changeResiduary(this); return false;" cssClass="ui-corner-all minputtext" path="onAccount" cssStyle="width:50px; text-align: right;" id="onAccount"/>
            </div>
            <div style="height: 3px;"></div>
            <div align="right" style="width: 100%;">
                <label for="residuaryId">Restante: </label>
                <input  type="text" readonly class="ui-corner-all minputtext" style="width:50px; text-align: right;" name="residuaryId" id="residuaryId" value="0.0" />
            </div>
        </div>
        <br/>
        
        <div style="width: 700px;">
            <div align="right" style="width: 100%;">
                <input type="submit" class="mbutton" style="width: 120px;" value="Grabar" id="reserveSaveId" />
                <input type="button" class="mbutton" value="Grabar e Imprimir" onclick="saveAndPrint(); return false;"/>
                <input type="button" class="mbutton" style="width: 120px;" value="Cancelar" onclick="redirectInventory();" />
            </div>
        </div>
    </form:form>
        <br/>
</div>

<div id="createPersonDialog" title="Crear Persona" style="display: none; width: 230 px;">
    <form id="f_createPeson">
        <p>
            <label for="fp_i_ci">Ci o nit:</label>
            <input type="text" class="ui-corner-all minputtext" name="fp_i_ci" id="p_ci" />
        </p>
        <div style="height: 5px;"></div>
        <p>
            <label for="p_firstname">Apellido:</label>
            <input type="text" class="ui-corner-all minputtext" name="p_firstname" id="p_firstname" />
        </p>
    </form>
</div>

<div id="printSale" style="width: 250px; height: 200px; display: none;">
    <div id="printSaleEject" class="PrintArea area1 both">
        <p><b>Fecha de Venta: </b><span id="prinDate"></span></p>
        <p><b>Fecha de Entrega: </b><span id="prinDateReserve"></span></p>
        <b>Datos: </b><span id="printFirstname"></span></p>
        <p><b>Sucursal: </b><span id="printBranch"></span></p>
        <table style="width: 100%; border: 1px solid #CC8632;" id="printTable">
            <thead>
                <tr>
                    <th style="text-align: center;"><b>Nombre</b></th>
                    <th style="text-align: center;"><b>Cant</b></th>
                    <th style="text-align: center;"><b>Precio</b></th>
                    <th style="text-align: center;"><b>Total</b></th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <div style="width: 99%; text-align: right; padding-right: 5px;">
            <p><b>TOTAL: </b><span id="sTotal">0</span></p>
            <p><b>A cuenta: </b><span id="sAccount">0</span></p>
            <p><b>Restante: </b><span id="sRestante">0</span></p>
        </div>
    </div>
</div>

<div id="dialogRedirect" style="width: 250px; height: 200px; display: none;">
    <input type="hidden" id="iDialogHidden" />
    <p>Redireccionar</p>
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
