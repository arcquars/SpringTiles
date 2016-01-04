<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="divEmpty" value="block" />
<c:set var="divResult" value="none"/>
<c:if test="${visible}">
    <c:out value="${validate}" />
    <c:set var="divEmpty" value="none" />
    <c:set var="divResult" value="block"/>
</c:if>
<script src="<c:url value='/resources/js/controllers/inventory/inventoryBranch.js'/>"></script>
<script src="<c:url value='/resources/js/jquery.PrintArea.js'/>"></script>
<% java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");%>

<br/>
<h1 style="padding-left: 0px;">Inventario de Tienda</h1>
<div  align="center" style="width: 100%;">
    <div style="width: 70%;">
        <form:form action="inventoryBranch.html" commandName="formBranch">
            <table>
                <tbody style="width: 100%;">
                    <tr>
                        <td style="width: 20%;"></td>
                        <td style="width: 22%;"></td>
                        <td style="width: 22%;"></td>
                        <td style="width: 20%;"></td>
                        <td style="width: 16%;"></td>
                    </tr>
                    <tr>
                        <td style="text-align: right; width: 20%;"><p>Sucursal: </p></td>
                        <td style="width: 20%;">
                            <form:select path="branchId" cssStyle="width: 100%" cssClass="ui-corner-all mselect" >
                                <form:options items="${allBranch}" itemValue="key" itemLabel="value"/>
                            </form:select>
                            <form:errors path="branchId" cssClass="errorMenssage" />
                        </td>
                        <td style="width: 20%;">
                            <form:select path="criteria" cssStyle="width: 100%" cssClass="ui-corner-all mselect" onchange="criteriaChange();">
                                <c:forEach var="item" items="${mapcriteria}" >
                                    <form:option value="${item.key}">${item.value}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td style="width: 20%;">
                            <form:input path="textSearch" cssClass="ui-corner-all minputtext"/>
                            <form:select path="category" cssStyle="width: 100%; display:none" cssClass="ui-corner-all mselect">
                                <c:forEach var="item" items="${mapCategory}" >
                                    <form:option value="${item.key}">${item.value}</form:option>
                                </c:forEach>
                            </form:select>
                            <form:errors path="category" cssClass="errorMenssage" />
                        </td>
                        <td style="width: 20%;">
                            <button class="mbutton" style="width: 100%;" type="submit">Buscar</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </div>
    <div style="display: ${divResult}; width: 70%" align="center">
        <br/>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="listPro">
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
        <div style="width: 90%; visibility: hidden;">
            <p id="pError" class="error"></p>
        </div>
        <div align="right">
            <input type="button" value="Venta" class="mbutton" onclick="fnViewSelectForSale();" />
            <input type="button" value="Reserva" class="mbutton" onclick="fnViewSelectForReserve();" />
            <input type="button" value="Devolucion" onclick="fnViewSelectForRefund();" class="mbutton" />
            <input type="button" value="Imprimir" onclick="printInventory();" class="mbutton" />
        </div>
        <br>
    </div>
    <div style="display: ${divEmpty}; width: 90%" align="center">
        <p class="error">Sin datos en la busqueda!!</p>
    </div>
</div>
<div id="printSale" style="width: 420px; height: 200px; display: none; overflow-x: hidden;" title="Inventario">
    <div id="printSaleEject" class="PrintAreaInventory area1 both" style="">
        <p><b>Fecha de Venta: </b><span><%= df.format(new java.util.Date())%></span></p>
        <p><b>Sucursal: </b><span id="s_sucursalId"></span></p>
        <div style="height: 8px;"></div>
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