<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link rel="stylesheet" href="<c:url value='/resources/styles/jquery-ui/humanity/jquery.ui.dialog.css' />" type="text/css" />

<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/inventory/salesAccount.js'/>"></script>
<script>
    var branchIdS = '';
    <c:if test="${not empty branchIPost}">
    branchIdS = '${branchIPost}';
    </c:if>
</script>
<link rel="stylesheet" href="<c:url value='/resources/styles/salesAccount.css' />" type="text/css" />

<br/>
<h1 style="padding-left: 0px;">Cuentas por tienda</h1>
<div  align="center" style="width: 100%;">
    <div style="width: 650px;">
        <form:form action="salesAccount.html" modelAttribute="salesAcountForm">
            <table>
                <tbody style="width: 630px !important;">
                    <tr>
                        <td>
                            <p>
                                Seleccionar Sucursal: 
                                <form:select path="branchId" cssClass="ui-corner-all mselect" onchange="setDates(this); return false;" >
                                    <form:options items="${allBranch1}" itemValue="key" itemLabel="value"/>
                                </form:select>
                                <form:errors path="branchId" cssClass="errorMenssage" />
                            </p>
                        </td>
                        <td>
                            <p>Ultimo Recojo: <form:input path="dateStart" class="minputtext" id="i_from"  /> </p>
                        </td>
                        <td>
                            <p>Fecha Actual: <form:input path="dateEnd" class="minputtext" id="i_to"  /> </p>
                        </td>
                        <td>
                            <p>.
                                <button class="mbutton" style="width: 120px;" type="submit">Buscar</button>
                            </p>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </div>
    <div style="height: 5px;"></div>
    <div id="tabs">
        <div style="text-align: left; padding-left: 10px;">
            <p id="pBranchId"><b>Sucursal: </b></p>
            <p><b>Total Ventas: </b><fmt:formatNumber value="${lsdg.totalSale + lsdg.totalReserveClose + lsdg.totalReserve + lsdg.totalCreditPayment}" pattern="0.00"/></p>
            <input type="hidden" value="${lsdg.totalSale + lsdg.totalReserveClose + lsdg.totalReserve + lsdg.totalCreditPayment}" id="ihTotal" />
            <input type="hidden" value="${branchIPost}" id="ihBranchId" />
        </div>
        <ul>
            <li><a href="#tabs-1">Ventas al Contado</a></li>
            <li><a href="#tabs-2">Reservas</a></li>
            <li><a href="#tabs-3">Cancelado de Reservas</a></li>
            <li><a href="#tabs-4">Pagos de Creditos</a></li>
        </ul>
        <div id="tabs-1">
            <table id="tSalesAccount">
                <thead>
                <th style="width: 60px;">Fecha Venta</th>    
                <th>Nombre</th>
                <th>Categoria</th>
                <th>Fabricante</th>
                <th>Costo</th>
                <th>Precio Venta</th>
                <th>Cant.</th>
                <th>Ganancia</th>
                <th>Total</th>
                </thead>
                <tbody>
                    <c:forEach var="valueProduct" items="${lsdg.listSales}" >
                        <tr>
                            <td>${valueProduct.dateSale}</td>
                            <td>${valueProduct.productName}</td>
                            <td>${valueProduct.productCategory}</td>
                            <td>${valueProduct.productFactory}</td>
                            <td>${valueProduct.costeBuy}</td>
                            <td>${valueProduct.priceSale}</td>
                            <td>${valueProduct.amountSale}</td>
                            <td>${valueProduct.gain}</td>
                            <td>${valueProduct.total}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div style="height: 5px;"></div>
            <div style="text-align: right;">
                <p>Total Ventas al Contado: <b>${lsdg.totalSale}</b></p>
            </div>
        </div>
        <div id="tabs-2">
            <table id="tSalesReserve">
                <thead>
                <th>Reserve</th>
                <th>Fecha de recojo</th>
                <th>A cuenta</th>
                <th>Saldo</th>
                <th>Total</th>
                </thead>
                <tbody>
                    <c:forEach var="valueProduct" items="${lsdg.listReserve}" >
                        <tr>
                            <td>${valueProduct.reserveId}</td>
                            <td>${valueProduct.dateReserve}</td>
                            <td>${valueProduct.onAccount}</td>
                            <td>${valueProduct.debit}</td>
                            <td>${valueProduct.total}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div style="height: 5px;"></div>
            <div style="text-align: right;">
                <p>Total nuevas Reservas: <b>${lsdg.totalReserve}</b></p>
            </div>
        </div>
        <div id="tabs-3">
            <table id="tSalesReserveClose">
                <thead>
                <th>Reserve</th>
                <th>Fecha de recojo</th>
                <th>A cuenta</th>
                <th>Saldo</th>
                <th>Total</th>
                </thead>
                <tbody>
                    <c:forEach var="valueProduct" items="${lsdg.listReserveClose}" >
                        <tr>
                            <td>${valueProduct.reserveId}</td>
                            <td>${valueProduct.dateReserve}</td>
                            <td>${valueProduct.onAccount}</td>
                            <td>${valueProduct.debit}</td>
                            <td>${valueProduct.total}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div style="height: 5px;"></div>
            <div style="text-align: right;">
                <p>Total Reservas Cerradas: <b>${lsdg.totalReserveClose}</b></p>
            </div>
        </div>
        <div id="tabs-4">
            <table id="tSalesCredit">
                <thead>
                <th>Fecha de Pago</th>
                <th>RazonSocial</th>
                <th>Total</th>
                <th>Pago</th>
                </thead>
                <tbody>
                    <c:forEach var="valueProduct" items="${lsdg.listCreditPayment}" >
                        <tr>
                            <td>${valueProduct.date}</td>
                            <td>${valueProduct.razonSocial}</td>
                            <td>${valueProduct.total}</td>
                            <td>${valueProduct.totalPayment}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div style="height: 5px;"></div>
            <div style="text-align: right;">
                <p>Total Reservas Cerradas: <b>${lsdg.totalCreditPayment}</b></p>
            </div>
        </div>
        <div style="text-align: right; margin-right: 18px;">
            <div style="height: 5px;"></div> 
            <input id="iAgreed" type="button" onclick="ejectAgreed();" value="Conforme" class="mbutton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" />
            <p class="error" id="pErrorConforme"><b></b></p>
            <div style="height: 5px;"></div> 
        </div>
    </div>
    <div style="height: 5px;"></div>
</div>
<div id="dDialogAmount" style="display: none;">
    <form:form action="salesAccountSave.html" modelAttribute="saleAmountForm" method="POST" >
        <table>
            <tr>
                <td>
                    <p><b>Usuario: </b></p>
                </td>
                <td style="text-align: right;">
                    ${saleAmountForm.userId}
                    <form:hidden path="userId" class="minputtext" />
                </td>
            </tr>
            <tr>
                <td>
                    <p><b>Sucursal: </b></p>
                </td>
                <td style="text-align: right;">
                    ${saleAmountForm.branchId}
                    <form:hidden path="branchId" class="minputtext"  />
                </td>
            </tr>
            <tr>
                <td>
                    <p id="dialogDateStart"><b>Fecha Inicio: </b></p>
                </td>
                <td style="text-align: right;">
                    ${saleAmountForm.dateStart}
                    <form:hidden path="dateStart" class="minputtext" />
                </td>
            </tr>
            <tr>
                <td>
                    <p id="dialogDateStart"><b>Fecha Fin: </b></p>
                </td>
                <td style="text-align: right;">
                    ${saleAmountForm.dateEnd}
                    <form:hidden path="dateEnd" class="minputtext" />
                </td>
            </tr>
            <tr>
                <td>
                    <b>Ventas al contado:</b>
                </td>
                <td style="text-align: right;">
                    ${saleAmountForm.totalSale}
                    <form:hidden path="totalSale" class="minputtext" />
                </td>
            </tr>
            <tr>
                <td>
                    <b>Pagos de ventas al credito:</b>
                </td>
                <td style="text-align: right;">
                    ${saleAmountForm.totalSaleCredit}
                    <form:hidden path="totalSaleCredit" class="minputtext" />
                </td>
            </tr>
            <tr>
                <td>
                    <b>Recervas Abiertas:</b>
                </td>
                <td style="text-align: right;">
                    ${saleAmountForm.totalReserveOpen}
                    <form:hidden path="totalReserveOpen" class="minputtext" />
                </td>
            </tr>
            <tr>
                <td>
                    <b>Recervas Close:</b>
                </td>
                <td style="text-align: right;">
                    ${saleAmountForm.totalReserveClose}
                    <form:hidden path="totalReserveClose" class="minputtext" />
                </td>
            </tr>
            <tr>
                <td>
                    <b>TOTAL:</b>
                </td>
                <td style="text-align: right;">
                    <b><fmt:formatNumber value="${saleAmountForm.amount}" pattern="0.00"/></b>
                    <form:hidden path="amount" class="minputtext" />
                </td>
            </tr>
        </table>
    </form:form>
</div>