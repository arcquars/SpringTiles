<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="<c:url value='/resources/styles/PrintArea.css' />" type="text/css" />
<link href="no_rel.css" type="text/css" media="print" />

<script src="<c:url value='/resources/js/controllers/inventory/inventoryController.js'/>"></script>
<script src="<c:url value='/resources/js/jquery.PrintArea.js'/>"></script>
<script src="<c:url value='/resources/js/jquery.printElement.js'/>"></script>
<% java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");%>
<br/>

<h1 style="padding-left: 0px;">Inventario de Almacen</h1>
<div  align="center" style="width: 100%;">
    <div style="width: 60%;">
        <form:form action="inventorypp.html" commandName="productSearchForm">
            <table>
                <tbody>
                    <tr>
                        <td style="width: 25%;"></td>
                        <td style="width: 25%;"></td>
                        <td style="width: 30%;"></td>
                        <td style="width: 20%;"></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;"><p>Buscar Producto por: </p></td>
                        <td>
                            <form:select path="criteria" cssStyle="width: 100%;" cssClass="ui-corner-all" >
                                <c:forEach var="item" items="${mapcriteria}" >
                                    <form:option value="${item.key}">${item.value}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td>
                            <form:input path="textSearch" cssClass="ui-corner-all minputtext"/>
                            <form:select path="category" cssStyle="display:none; width:100%;" cssClass="ui-corner-all mselect">
                                <c:forEach var="item" items="${mapCategory}" >
                                    <form:option value="${item.key}">${item.value}</form:option>
                                </c:forEach>
                            </form:select>
                            <form:errors path="textSearch" cssClass="errorMenssage" />
                        </td>
                        <td>
                            <input style="width: 100%;" type="submit" value="Buscar" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </div>
    <div style="display: ${visibleTable}; width: 60%" align="center">
        <div style="height: 5px;"></div>
        <div style="width: 98%;" align="right">
            <p>Gran Total: <b>${grantotal}</b></p>
            <p style="text-align: right; width: 25px; display: inline;"><button title="Exportar a Excel" onclick="exportExcel();
                    return false;"><span style="text-align: right;" class="ui-icon ui-icon-disk"></span></button></p>
            <p style="text-align: right; width: 25px; display: inline;"><button title="Imprimir" onclick="printInventory();
                    return false;"><span style="text-align: right;" class="ui-icon ui-icon-print"></span></button></p>
            <!--<p style="text-align: right; width: 25px; display: inline;"><button title="Control de Inventario"><span style="text-align: right;" class="ui-icon ui-icon-note"></span></button></p>-->
        </div>
        <div style="height: 10px;"></div>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="listPro">
            <thead>
            <th>Nombre</th>
            <th>Descripcion</th>
            <th>Codigo Origen</th>
            <th>Categoria</th>
            <th>Fabricante</th>
            <th>Cant</th>
            <th>Costo</th>
            <th>Costo Total</th>
            </thead>
            <tbody>
                <c:forEach var="valueProduct" items="${listProduct}" >
                    <tr>
                        <td><p>${valueProduct.productName}<strong style="visibility: hidden;">${valueProduct.productId}</strong></p></td>
                        <td>${valueProduct.detail}</td>
                        <td>${valueProduct.codOrigin}</td>
                        <td>${valueProduct.categoryName}</td>
                        <td>${valueProduct.factoryName}</td>
                        <td>${valueProduct.total}</td>
                        <td>${valueProduct.coste}</td>
                        <td><fmt:formatNumber var="formattedBillableTime" type="number" minFractionDigits="2" maxFractionDigits="2" value="${valueProduct.coste*valueProduct.total}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/>
        <div style="width: 500px; visibility: hidden;">
            <p id="pError" class="error"></p>
        </div>
        <div align="right">
            <input type="button" value="Vender" class="mbutton" onclick="fnViewSelectForSaleInventory();
                    return false;" />
            <input type="button" value="Reserva" class="mbutton" onclick="fnViewSelectForReserveInventory();
                    return false;" />
            <input type="button" value="Comprar" class="mbutton" onclick="fnViewSelect();
                    return false;" />
            <input type="button" value="Entrega a Tienda" onclick="fnRedirectDeliveryBranch();" class="mbutton" />
            <!--<input type="button" value="Imprimir" onclick="printInventory();" class="mbutton" />-->
        </div>
        <!--<a href="#" onclick="fnViewSelect();return false;">xxxxxx</a>-->
    </div>
    <div style="display: ${visibleTableEmpty}; width: 520px" align="center">
        <p class="error">Sin datos en la busqueda!!</p>
    </div>
    <br/>
</div>
<div id="printSale" style="width: 420px; height: 200px; overflow-x: hidden; display: none;" title="Inventario">
    <div id="printSaleEject" class="PrintAreaInventory area1 both" style="">
        <p><b>Fecha de Venta: </b><span><%= df.format(new java.util.Date())%></span></p>
        <table style="width: 92%; border: 1px solid #CC8632;" id="printTable">
            <thead>
                <tr>
                    <th style="text-align: left;"><b>Producto</b></th>
                    <th style="text-align: left;"><b>Codigo</b></th>
                    <th style="text-align: left;"><b>Categoria</b></th>
                    <th style="text-align: left;"><b>Fabricante</b></th>
                    <th style="text-align: left;"><b>Total</b></th>
                </tr>
            </thead>
            <tbody style="overflow-y: auto; overflow-x: hidden; height: 50px;">
            </tbody>
        </table>
    </div>
</div>