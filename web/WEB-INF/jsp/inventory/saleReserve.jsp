<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="<c:url value='/resources/DataTables/media/css/jquery.dataTables.min.css' />" type="text/css" />
<link rel="stylesheet" href="<c:url value='/resources/DataTables/media/css/jquery.dataTables_themeroller.css' />" type="text/css" />

<link href="no_rel.css" type="text/css" media="print" />
<script src="<c:url value='/resources/js/jquery.PrintArea.js'/>"></script>

<script src="<c:url value='/resources/DataTables/media/js/jquery.dataTables.js'/>"></script>
<script src="<c:url value='/resources/DataTables/media/js/fnReloadAjax.js'/>"></script>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/inventory/saleReserve.js'/>"></script>

<br/>
<h1 style="padding-left: 0px;">Reservas</h1>
<div  align="center" style="width: 100%;">
    <div style="width: 60%;">
        <form>
            <table>
                <tbody>
                    <tr>
                        <td style="width: 40%"></td>
                        <td style="width: 30%"></td>
                        <td style="width: 30%"></td>
                    </tr>
                    <tr>
                        <td>Seleccionar Sucursal: </td>
                        <td>Fecha de Reserva: </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <select id="s_branchId" style="width: 100%;" class="ui-corner-all mselect" >
                                <c:forEach var="item" items="${allBranch}" >
                                    <option value="${item.key}">${item.value}</option>
                                </c:forEach>
                                <option value="0">Principal</option>
                            </select>
                            <errors path="branchId" cssClass="errorMenssage" />
                        </td>
                        <td>
                            <input id="i_dateReserveSale" readonly style="width: 98%;" class="minputtext ui-corner-all" type="text"/>
                        </td>
                        <td>
                            <button style="width: 100%;" class="b_reserve_table" type="button" onclick="reloadTableReserve();
                                        return false;">Buscar</button>
                        </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>

    <div style="width: 60%;" align="center">
        <br/>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="t_listreserve">
            <thead>
            <th>Fecha Reserva</th> 
            <th>Sucursal</th> 
            <th>Nombre Cliente</th>
            <th>A cuenta</th>
            <th>Saldo</th>
            <th>Total</th>
            <th>Pago</th>
            </thead>
            <tbody>
            </tbody>
        </table>
        <br/>
    </div>
    <div id="dialogReserve" style="display: none;">
        <div id="dialogReserveContent1">
            <p><b>Cliente: </b><i id="dReserveClientName"></i>&emsp;&emsp;<b>Ci: </b><i id="dReserverCi"></i></p>
            <p><b>Fecha de entrega: </b><i id="dReserveDateReserve"></i>&emsp;&emsp;<b>Fecha de recojo: </b><i id="dReserverPick"></i></p>
            <p><b>Sucursal: </b><i id="dReserverBranch"></i></p>
            <br/>
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="dReserveTableProducts" style="width: 100%; border: 1px solid black;">
                <thead>
                    <th>item</th>
                    <th>Codigo</th>
                    <th>Precio de venta</th>
                    <th>Cantidad</th>
                    <th>Total</th>
                </thead>
                <tbody>
                </tbody>
            </table>
            <br/>
            <div style="text-align: right !important; width: 98%;">
                <p><b>A cuenta: </b><i id="dReserverOnAccount"></i></p>
                <p style="color: #CC8632;"><b>Debe: </b><i id="dReserverDebit"></i></p>
                <p><b>TOTAL: </b><i id="dReserverTotal"></i></p>
            </div>
        </div>
    </div>
</div>