<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/inventory/saleGain.js'/>"></script>

<br/>
<h1 style="padding-left: 0px;">Detalle de ventas por Tienda</h1>
<div  align="center" style="width: 100%;">
    <div style="width: 70%;">
        <form id="f_search">
|            <table>
                <tbody>
                    <tr>
                        <td style="width: 25%"></td>
                        <td style="width: 25%"></td>
                        <td style="width: 25%"></td>
                        <td style="width: 25%"></td>
                    </tr>
                    <tr>
                        <td>
                            Seleccionar Sucursal: 
                            <select id="branchId" style="width: 100%" class="ui-corner-all mselect">
                                <c:forEach var="item" items="${allBranch1}" >
                                    <option value="${item.key}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            Fecha de Inicio: <input readonly="true" style="width: 98%;" class="ui-corner-all minputtext" id="i_from" />
                        </td>
                        <td>
                            Fecha de Fin: <input readonly="true" style="width: 98%;" class="ui-corner-all minputtext" id="i_to" />
                        </td>
                        <td>
                            <p>.
                                <button class="mbutton" style="width: 100%;" type="submit">Buscar</button>
                            </p>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>

    <div style="width: 70%" align="center">
        <br/>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="listSaleDetail">
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
            </tbody>
        </table>
        <br/>
        <br/>
        <table style="width: 70%;" align="right">
            <tr>
                <td>
                    Ganancia :
                </td>
                <td>
                    <input type="text" readonly id="i_gainTotal" class="minputtext" style="width: 60px;" />
                </td>
                <td>
                    Total :
                </td>
                <td>
                    <input type="text" id="i_granTotal" readonly class="minputtext" style="width: 60px;" />
                </td>
            </tr>
        </table>
    </div>
</div>