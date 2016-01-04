<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="divEmpty" value="block" />
<c:set var="divResult" value="none"/>
<c:if test="${visible}">
    <c:out value="${validate}" />
    <c:set var="divEmpty" value="none" />
    <c:set var="divResult" value="block"/>
</c:if>
<script src="<c:url value='/resources/js/controllers/inventory/reserveStore.js'/>"></script>
<script src="<c:url value='/resources/js/jquery.PrintArea.js'/>"></script>
<% java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");%>

<br/>
<h1 style="padding-left: 0px;">Inventario de Productos en Reservas</h1>
<div  align="center" style="width: 100%;">
    <div style="width: 60%;">
        <form:form action="reserveStore.html" commandName="formBranch">
            <!--
            <form:label path="branchId">Seleccionar Sucursal: </form:label>
            <form:select path="branchId">
                <form:options items="${allBranch1}" itemValue="key" itemLabel="value"/>
            </form:select>
            <form:errors path="branchId" cssClass="errorMenssage" />
            <button id="b_submit_reserve" type="submit">Buscar</button>
            -->
            <table>
                <tbody style="width: 100%;">
                    <tr>
                        <td style="text-align: right; width: 30%;"><p>Seleccionar Sucursal: </p></td>
                        <td style="width: 40%;">
                            <form:select path="branchId" cssStyle="width: 100%;">
                                <form:options items="${allBranch1}" itemValue="key" itemLabel="value"/>
                            </form:select>
                            <form:errors path="branchId" cssClass="errorMenssage" />
                        </td>
                        <td style="text-align: left; width: 30%;">
                            <button class="mbutton" type="submit">Buscar</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            
        </form:form>
    </div>
    <div style="display: ${divResult}; width: 60%;" align="center">
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
                        <td>${valueProduct.codOrigin}</td>
                        <td>${valueProduct.categoryName}</td>
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
    </div>
    <div style="display: ${divEmpty}; width: 60%" align="center">
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