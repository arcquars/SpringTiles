<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value='/resources/DataTables/media/js/fnReloadAjax.js'/>"></script>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/credit/reportCredit.js'/>"></script>
<br/>
<h1 style="padding-left: 0px;">Reporte de Pagos a Creditos: </h1>
<div align="center" style="width: 100%;">
    <div align="center" style="width: 70%">
        <form id="f_reportCredit">
            <table>
                <tr>
                    <td style="width: 25%;"></td>
                    <td style="width: 25%;"></td>
                    <td style="width: 25%;"></td>
                    <td style="width: 25%;"></td>
                </tr>
                <tr>
                    <td>Proveedor:</td>
                    <td>
                        <select style="width: 100%;" name="fReportCredit_provider" id="fReportCredit_provider" class="mselect ui-corner-all">
                            <c:forEach var="prov" items="${provider}">
                                <option value="<c:out value="${prov.key}"/>"><c:out value="${prov.value}"/></option> 
                            </c:forEach>
                        </select>
                    </td>
                    <td colspan="2" style="text-align: right;">
                        <div id="fdradio">
                            <input type="radio" name="f_cancel" id="fradioAll" value="0" checked /><label for="fradioAll">Todos</label>
                            <input type="radio" name="f_cancel" id="fradioCancel" value="1" /><label for="fradioCancel">Cancelados</label>
                            <input type="radio" name="f_cancel" id="fradioNoCalcel" value="2" /><label for="fradioNoCancel">No Cancelados</label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Fecha Inicio:</td>
                    <td><input readonly type="text" name="f_dateIni" id="f_dateIni" class="ui-corner-all minputtext" /></td>
                    <td style="text-align: right;">Fecha Fin:</td>
                    <td><input readonly type="text" name="f_dateEnd" id="f_dateEnd" class="ui-corner-all minputtext" /></td>
                </tr>
                <tr>
                    <td colspan="4" style="text-align: right;"><input type="button" class="mbutton" value="Buscar" onclick="fnsearchBuyCredit(); return false;" /></td>
                </tr>
            </table>
        </form>
        <br/>
        <div align="center">
            <table id="t_reportCredit">
                <thead>
                    <tr>
                        <th>Proveedor</th>
                        <th>Fecha Compra</th>
                        <th>Total</th>
                        <th>Saldo</th>
                        <th>Accion</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <div style="width: 70%; visibility: hidden;">
                <p id="pError" class="error"></p>
                <br/>
            </div>
        </div>
        <br/>
    </div>
</div>
<div id="dialog-payment" style="display: none;">
    <p>Proveedor: <b id="detailPaymentProvider"></b> &emsp;Fecha de Compra: <b id="detailPaymentDateBuy"></b></p>
    <table id='detailPaymentTable'>
        <thead>
            <tr>
                <th>Fecha de Pago</th>
                <th>Pago</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <div style="text-align: right;">
        <p>Total Pagado: <b id="detailPaymentTotalP"></b></p>
        <p>Total Compra: <b id="detailPaymentTotalBuy"></b></p>
        <p>Total Saldo: <b id="detailPaymentBalance"></b></p>
    </div>
</div>
<div id="dialog-buy" style="display: none;">
    <br>
    <p>Proveedor: <b id="detailBuyProvider"></b> &emsp;Fecha de Compra: <b id="detailBuyData">2014-05-201</b></p>
    <br>
    <table id='detailBuyTable'>
        <thead>
            <tr>
                <th>Nombre Product</th>
                <th>Codigo</th>
                <th>Categoria</th>
                <th>Fabricante</th>
                <th>Cantidad</th>
                <th>Precio U.</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody class="border bottom">
        </tbody>
    </table>
    <br>
    <div style="text-align: right;">
        <p>Total Compra: <b id="detailBuyTotal"></b></p>
    </div>
</div>