<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<c:url value='/resources/DataTables/media/js/fnReloadAjax.js'/>"></script>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/credit/createPayment.js'/>"></script>
<br/>
<script>
    $("#coolMenu li").each(function(i, item) {
        var href = $(item).children().attr('href');
        if (href.indexOf('creditpp') != -1) {
            $(item).children().addClass("coolMenuSelect");
        } else {
            $(item).children().removeClass("coolMenuSelect");
        }
    });
</script>
<c:set var="divResult" value="none" scope="page" />
<c:set var="divNoResult" value="block" scope="page" />
<c:if test="${listValid != null}">
    <c:set var="divResult" value="block" scope="page" />
    <c:set var="divNoResult" value="none" scope="page" />
</c:if>
<h1 style="padding-left: 0px;">Pago a Proveedor: </h1>
<div align="center" style="width: 100%;">
    <div align="center" style="width: 70%">
        <form:form modelAttribute="creditForm" method="post" action="creditpp.html" >
            <table id="t_provider" style="width: 70%">
                <tr>
                    <td style="width: 25%"></td>
                    <td style="width: 55%"></td>
                    <td style="width: 20%"></td>
                </tr>
                <tr>
                    <td>
                        <form:label path="providerId">Buscar Proveedor:</form:label>
                        </td>
                        <td>
                        <form:select path="providerId" cssStyle="width:100%;" cssClass="ui-corner-all mselect">
                            <form:options items="${allProvider}" itemValue="key" itemLabel="value"/>
                        </form:select>
                        <form:errors path="providerId" cssClass="errorMenssage" />
                    </td>
                    <td>
                        <input type="button" onclick="searchCredit(); return false;" class="mbutton" style="width: 100%;" value="Buscar" />
                    </td>
                </tr>
            </table>
        </form:form>
        <br/>
        <div align="center">
            <table id="t_product">
                <thead>
                    <tr>
                        <th>Credit num</th>
                        <th>Fecha Compra</th>
                        <th>Total</th>
                        <th>Saldo</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <div style="height: 30px;"></div>
            <div style="width: 70%; visibility: hidden;">
                <p id="pError" class="error"></p>
                <br/>
            </div>
            <div align="right">
                <input type="button" value="Pagar" class="mbutton" onclick="fnPayment();" />
            </div>
        </div>
        <br/>
    </div>
</div>
<div id="divPayment" title="Hacer Pago" style="display: none;">
    <form id="f_createPayment">
        <fieldset>
            <legend></legend>
            <table>
                <tr>
                    <td style="width: 40%;">Total</td>
                    <td style="width: 30%;">Saldo</td>
                    <td style="width: 30%;">Pago</td>
                </tr>
                <tr>
                    <td>
                        <p id="paymentTotal"></p>
                    </td>
                    <td>
                        <p id="paymentSaldo"></p>
                    </td>
                    <td>
                        <input id="i_payment" name="i_payment" type="text" value="" maxlength="100" class="ui-corner-all minputtext" style="text-align: right; width: 100%;"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
    <p id="pErrorMensajeCrear" class="errorMenssage"></p>
</div>
