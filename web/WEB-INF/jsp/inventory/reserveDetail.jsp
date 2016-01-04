<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link href="no_rel.css" type="text/css" media="print" />
<script src="<c:url value='/resources/js/jquery.PrintArea.js'/>"></script>

<script src="<c:url value='/resources/DataTables/media/js/jquery.dataTables.js'/>"></script>
<script src="<c:url value='/resources/DataTables/media/js/fnReloadAjax.js'/>"></script>
<h1 style="padding-left: 0px;">Reserva</h1>
<div  align="center" style="width: 100%;">
    <div style="width: 100%;" align="left">
        <p><b>Cliente: </b>${reserveDetail.nameClient}&emsp;&emsp;<b>Ci: </b>${reserveDetail.nameCi}</p>
        <p><b>Fecha de entrega: </b>${reserveDetail.dateReserve}&emsp;&emsp;<b>Fecha de recojo: </b>${dateNow}</p>
        <p><b>Sucursal: </b>${reserveDetail.branchName}</p>
        <br/>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="t_listproducReserve" style="width: 100%; border: 1px solid black;">
            <thead>
            <th>item</th>
            <th>Codigo</th>
            <th>Precio de venta</th>
            <th>Cantidad</th>
            <th>Total</th>
            </thead>
            <tbody>
                <c:forEach var="valueReserve" items="${reserveDetail.listSaleDetail}" >
                    <tr style="border: 1px solid black !important;">
                        <td style="border-top: 1px solid black;">${valueReserve.nameItem}</td>
                        <td style="border-left: 1px solid black; border-top: 1px solid black;">${valueReserve.code}</td>
                        <td style="border-left: 1px solid black; border-top: 1px solid black;">${valueReserve.price}</td>
                        <td style="text-align: right; border-left: 1px solid black; border-top: 1px solid black;">${valueReserve.amount}</td>
                        <td style="text-align: right; border-left: 1px solid black; border-top: 1px solid black;">${valueReserve.total}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/>
        <div style="text-align: right !important; width: 98%;">
            <p><b>A cuenta: </b>${reserveDetail.onAmount}</p>
            <p style="color: #CC8632;"><b>Debe: </b>${reserveDetail.debit}</p>
            <p><b>TOTAL: </b>${reserveDetail.total}</p>
        </div>
    </div>
</div>