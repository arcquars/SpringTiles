<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/js/libs/templates.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/inventory/saleReceive.js'/>"></script>
<style>
    .ui-selectmenu-button{
        top: 9px;
    }
</style>
<br/>
<h1 style="padding-left: 0px;">Cobro de Ventas al Credito</h1>
<div style="text-align: center; width: 100%;">
    <label for="from">Sucursal:</label>
    <select name="branch" id="branch" style="width: 100px;" class="ui-corner-all ui-selectmenu-button arcsys-select">
        <c:forEach var="item" items="${allBranch}" >
            <option value="${item.key}">${item.value}</option>
        </c:forEach>
    </select>
    <select name="type" id="type" style="width: 150px;" class="ui-corner-all ui-selectmenu-button arcsys-select">
        <option value="0">Razon Social</option>
        <option value="1">Nit</option>
    </select>
    <input type="text" id="search" name="search" class="ui-corner-all minputtext">
    <input type="submit" value="Buscar" id="buscar_s" onclick="reloadSearch();
            return false;" />
</div>
<br>
<div style="width: 100%" align="center">
    <div style="width: 80%">
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="listSale">
            <thead>
            <th>#</th>
            <th>Fecha Venta</th>
            <th>Razon Social</th>
            <th>Credit</th>
            <th>Detalle</th>
            <th>Total</th>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<div id="detailSale" title="Detalle de Venta" style="display: none;">
    <p><b>Razon Social: </b><span id="ds_razonSocial"></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>Nit: </b><span id="ds_nit"></span></p>
    <p><b>Fecha de venta: </b><span id="ds_createDate"></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>Vendedor: </b><span id="ds_vendorName"></span></p>
    <div style="height: 10px;"></div>
    <table id="ds_table">
        <thead>
            <th>Nombre Producto</th>
            <th>Codigo</th>
            <th>Descripcion</th>
            <th>Cantidad</th>
            <th>Precio Venta</th>
            <th>Total</th>
        </thead>
        <tbody>
        </tbody>
    </table>
    <p style="text-align: right;"><b>TOTAL: </b><span id="ds_total"></span></p>
    <p style="text-align: right;"><b>Reserva: </b><input type="checkbox" id="ds_reserve" disabled/>&nbsp;&nbsp;&nbsp;&nbsp;<b>Credito: </b><input type="checkbox" id="ds_credit" disabled /></p>
</div>
<div id="detailReivoce" title="Pago de Venta" style="display: none;">    
</div>