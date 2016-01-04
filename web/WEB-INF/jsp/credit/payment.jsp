<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/credit/payment.js'/>"></script>
<form id="f_createPayment">
    <fieldset>
        <legend></legend>
        <table>
            <tr>
                <td>Total</td>
                <td>Saldo</td>
                <td>Pago</td>
            </tr>
            <tr>
                <td>
                    <p id="total">${total}</p>
                </td>
                <td>
                    <p id="saldo">${saldo}</p>
                </td>
                <td>
                    <input id="i_payment" name="i_payment" type="text" value="" maxlength="100" class="ui-corner-all minputtext" style="width: 60px; text-align: right;"/>
                </td>
            </tr>
        </table>
    </fieldset>
</form>
<p id="pErrorMensajeCrear" class="errorMenssage"></p>