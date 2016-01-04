<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/js/libs/templates.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/inventory/saleSelect.js'/>"></script>
<style>
    .ui-selectmenu-button{
        top: 9px;
    }
    .cupertino_icons {
        background-image: url(<c:url value='/resources/js/jquery-ui/images/cupertino/images/ui-icons_3d80b3_256x240.png'/>);
    }
</style>
<br/>
<h1 style="padding-left: 0px;">Ventas</h1>
<div style="text-align: center; width: 100%;">
    <label for="from">Sucursal:</label>
    <select name="branch" id="branch" style="width: 100px; top: 10px;" class="ui-corner-all ui-selectmenu-button">
        <c:forEach var="item" items="${allBranch}" >
            <option value="${item.key}">${item.value}</option>
        </c:forEach>
    </select>
    <label for="from">Desde</label>
    <input type="text" id="from" name="from" class="ui-corner-all minputtext">
    <label for="to">Hasta</label>
    <input type="text" id="to" name="to" class="ui-corner-all minputtext">
    <input type="submit" value="Buscar" id="buscar_s" onclick="reloadSearch(); return false;" />
</div>
<br>
<div style="width: 100%" align="center">
    <div style="width: 80%">
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="listSale">
            <thead>
            <th>#</th>
            <th>Fecha Venta</th>
            <th>Razon Social</th>
            <th>Reserve</th>
            <th>Credit</th>
            <th>Detalle</th>
            <th>Total</th>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
<div style="height: 10px;"></div>
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