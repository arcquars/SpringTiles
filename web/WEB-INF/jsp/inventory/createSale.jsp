<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="<c:url value='/resources/styles/PrintArea.css' />" type="text/css" />
<link rel="stylesheet" href="<c:url value='/resources/styles/createSale.css' />" type="text/css" media="all" />


<!--
<link rel="" href="empty.css" type="text/css" />
<link rel="noPrint" href="noPrint.css" type="text/css" />
<link rel="stylesheet" href="media_none.css" type="text/css" media="xyz" />
<link href="no_rel.css" type="text/css" media="print" />
<link href="no_rel_no_media.css" type="text/css" />
-->

<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/inventory/createSale.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/client/client.js'/>"></script>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script src="<c:url value='/resources/js/jquery.PrintArea.js'/>"></script>

<br/>
<c:set var="hidden" value="hidden" scope="page" />
<h1 style="padding-left: 0px;">Venta de Productos: </h1>
<div align="center">
    <form:form modelAttribute="listProductSale1" method="post" action="createSale.html" id="formCreateSale" >
        <div align="left" style="width: 75%;">
            <!--<p>Fecha de Venta: <input type="text" id="datepicker" readonly="true" style="width: 100px;" class="minputtext" /> </p>-->
            <table>
                <tr>
                    <td>
                        <p style="display: inline;"> Razon Social:</p>
                    </td>
                    <td colspan="4">
                        <input type="text" autofocus style="width: 100%; font-size: 10px;" class="ui-corner-all" id="namePersonId" name="namePerson" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <p style="display: inline;">Fecha de Venta:</p>
                    </td>
                    <td>
                        <form:input path="dateSale" readonly="true" style="width: 100px;" class="ui-corner-all minputtext" id="datepicker" />
                    </td>
                    <td>
                        <p style="display: inline;"> Client: </p>
                    </td>
                    <td>
                        <form:input path="clientId" style="width: 100px;" class="ui-corner-all" id="ciId" readonly="true" />
                    </td>                    
                    <td>
                        <form:select path="vendorId" id="vendorId" cssStyle="width: 100%;" cssClass="ui-corner-all" >
                            <c:forEach var="item" items="${listVendor}" >
                                <form:option value="${item.empId}">${item.names}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
            </table>
        </div>
        <br/>
        <table width="550px" style="width: 550px;" id="t_product">
            <thead width="550px" style="width: 550px;">
                <tr>
                    <th>Nombre Producto</th>
                    <th>Codigo</th>
                    <th>Descripcion</th>
                    <th>Fabricante</th>
                    <th>Q. Limite</th>
                    <th>Precio Minimo</th>
                    <th>Cantidad</th>
                    <th>Precio Venta</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody width="550px" style="width: 550px;">
                <c:forEach var="listRpd1" items="${listProductSale1.listProductSale}" varStatus="itemStatusRpd" >
                    <tr>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="listProductSale[${itemStatusRpd.index}].productName" cssStyle="width:100px;" readonly="true"/>
                            <form:hidden path="listProductSale[${itemStatusRpd.index}].productId" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="listProductSale[${itemStatusRpd.index}].productCodOrigin" cssStyle="width:100px;" readonly="true"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread toltipArcsys" path="listProductSale[${itemStatusRpd.index}].detail" cssStyle="width:100px;" readonly="true"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all onlyread" path="listProductSale[${itemStatusRpd.index}].fatoryName" readonly="true" cssStyle="width:100px;" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all minputtext" path="listProductSale[${itemStatusRpd.index}].limitedAmount" cssStyle="width:50px;" readonly="true" />
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all minputtext" path="listProductSale[${itemStatusRpd.index}].higherCost" cssStyle="width:50px;" readonly="true"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all" path="listProductSale[${itemStatusRpd.index}].amount" cssStyle="width:50px;"  onchange="validAmount(this);"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all" path="listProductSale[${itemStatusRpd.index}].price" cssStyle="width:50px;"  onchange="validPrice(this);"/>
                        </td>
                        <td>
                            <form:input cssClass="ui-corner-all minputtext" path="listProductSale[${itemStatusRpd.index}].total" cssStyle="width:50px;" onchange="trTotal();" readonly="true"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
        <div style="width: 580px;">
            <p class="error">${Error}</p>
        </div>
        <br/>
        <div style="width: 695px;">
            <div align="right" style="width: 100%;">
                <form:label path="total">TOTAL:</form:label>
                <form:input cssClass="ui-corner-all minputtext" readonly="true" path="total" cssStyle="width:50px;" id="totalBuy"/>
                <form:hidden path="branchId" />
            </div>
            <div align="right" style="width: 100%;">
                <form:label path="credit">Credito:</form:label>
                <form:checkbox cssClass="ui-corner-all minputtext" value="0" path="credit" id="creditC"/>
            </div>
        </div>
        <br/>

        <div style="width: 700px;">
            <div align="right" style="width: 100%;">
                <input id="i_save" type="submit" class="mbutton" style="width: 120px;" value="Grabar"/>
                <input type="button" class="mbutton" value="Grabar e Imprimir" onclick="saveAndPrint();
                        return false;"/>
                <input type="button" class="mbutton" value="Pro forma" onclick="proformaPrint(); return false;"/>
                <input type="button" class="mbutton" style="width: 120px;" value="Cancelar" onclick="redirectInventory();
                        return false;" />
            </div>
        </div>
    </form:form>
</div>
<div id="printSale" title="Imprimir venta" style="width: 450px; display: none;">
    <div id="printSaleEject" class="PrintArea area1 both" style="width: 95%;">
        <h1 style="text-align: center; font-size: 14px; color: #cc8632;" id="title_impri">Nota de Venta</h1>
        <table style="width: 100%;">
            <tr>
                <td style="width: 50%;">
                    <img width="160" src="<c:url value='/resources/images/'/><spring:message code="empresa.logo" />" />
                </td>
                <td style="width: 50%; text-align: right; padding: 0 0; margin: 0 0">
                    <p style="font-size: 12px; padding: 0 0; margin: 0 0">
                        <b>N° Nota: </b>
                        <span style="font-size: 12px;" id="prinNumSale"></span>
                    </p>
                    <p style="font-size: 12px; padding: 0 0; margin: 0 0">
                        <b>Fecha: </b>
                        <span style="font-size: 12px;" id="prinDate"></span>
                    </p>
                    <p style="font-size: 12px; padding: 0 0; margin: 0 0">
                        <b>N° Doc: </b>
                        <span style="font-size: 12px;" id="prinNumDoc"></span>
                    </p>
                    <p style="font-size: 12px; padding: 0 0; margin: 0 0">
                        <b>Pag.</b>
                        <span style="font-size: 12px;" id="prinNumDoc">1</span>
                    </p>
                    <p style="font-size: 12px; padding: 0 0; margin: 0 0">
                        <b>Vendedor</b>
                        <span style="font-size: 12px; padding: 0 0; margin: 0 0" id="printNameVendor"></span>
                    </p>
                </td>
            </tr>
        </table>
        <hr>
        <p style="font-size: 12px; margin: 0 0;"><b>Nombre: </b><span id="printFirstname"></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>Zona: </b><span id="zoneClient"></span></p>
        <p style="font-size: 12px; margin: 0 0;"><b>Direccion: </b><span id="printDireccion"></span></p>
        <p style="font-size: 12px; margin: 0 0;"><b>Telefono: </b><span id="printTele"></span></p>
        <p style="font-size: 12px; margin: 0 0;"><b>Factura a: </b><span id="printFacturaA"></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>Nit o Ci: </b><span id="printCi"></span></p>
        <table style="width: 100%; border: 1px solid #CC8632;" id="printTable">
            <thead>
                <tr>
                    <th style="text-align: center; font-size: 11px; border-bottom: 1px solid #CC8632;"><b>Articulo</b></th>
                    <th style="text-align: center; font-size: 11px; border-bottom: 1px solid #CC8632;"><b>Producto</b></th>
                    <th style="text-align: center; font-size: 11px; border-bottom: 1px solid #CC8632;"><b>Cant.</b></th>
                    <th style="text-align: center; font-size: 11px; border-bottom: 1px solid #CC8632;"><b>Precio</b></th>
                    <th style="text-align: center; font-size: 11px; border-bottom: 1px solid #CC8632;"><b>Importe</b></th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <div style="height: 1px;"></div>
            <table style="width: 100%;">
                <tr>
                    <td><p style="font-size: 12px;"><b>Son: </b><span id="sTotalLiteral"></span></p></td>
                    <td align="right" style="font-size: 12px; text-align: right !important; padding-right: 5px;"><p style="text-align: right;"><b>TOTAL: </b><span id="sTotal">0</span></p></td>
                </tr>
            </table>
        <div style="height: 2px;"></div>
        <table style="width: 100%;">
            <tr>
                <td colspan="3" style="font-size: 12px; padding-bottom: 25px;">Despachado por: ____________________.</td>
            </tr>
            <tr>
                <td style="text-align: center">____________________</td>
                <td style="text-align: center">____________________</td>
                <td style="text-align: center">____________________</td>
            </tr>
            <tr>
                <td style="font-size: 12px; text-align: center">Entregado por</td>
                <td style="font-size: 12px; text-align: center">Encargado</td>
                <td style="font-size: 12px; text-align: center">Recibi conforme</td>
            </tr>
        </table>
        <div style="font-size: 10px;"><b>Nota: </b>Por favor revise la mercaderia, no se acepta cambios ni devoluciones.</div>
    </div>
</div>

<div id="dialogRedirect" style="width: 250px; height: 200px; display: none;">
    <input type="hidden" id="iDialogHidden" />
    <p>Redireccionar</p>
</div>

                <div id="dialogClient" title="Crear Cliente" style="display: none;">
    <form id="f_client">
        <fieldset>
            <legend></legend>
            <input type="hidden" value="0" name="person" id="client_perId" />
            <input type="hidden" value="0" name="clientId" />
            <table>
                <tr>
                    <td><label for="nit">Nit:</label></td>
                    <td>
                        <input id="client_nit" name="nit" type="text" class="ui-corner-all minputtext" onblur="getFirstName1(this);
                                return false;" />
                    </td>
                </tr>
                <tr>
                    <td><label for="razonSocial">Razon Social:</label></td>
                    <td><input name="razonSocial" type="text" value="" class="ui-corner-all minputtext"/></td>
                </tr>
                <tr>
                    <td><label for="address">Direccion:</label></td>
                    <td><input name="address" type="text" class="ui-corner-all minputtext"/></td>
                </tr>
                <tr>
                    <td><label for="phone">Telefono:</label></td>
                    <td><input name="phone" type="text" class="ui-corner-all minputtext"/></td>
                </tr>
                <tr>
                    <td><label for="fax">Fax:</label></td>
                    <td><input name="fax" type="text" class="ui-corner-all minputtext"/></td>
                </tr>
                <tr>
                    <td><label for="url">Url:</label></td>
                    <td><input name="url" type="text" class="ui-corner-all minputtext"/></td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input name="email" type="text" class="ui-corner-all minputtext"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div id="map_canvas" style="width:100%; height: 200px;"></div>
                        <input type="hidden" id="client_lat" name="latitud" value="-17.39782091929972" />
                        <input type="hidden" id="client_lon" name="longitud" value="-66.1871396640625"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>
<div id="createPersonDialog" title="Crear Persona" style="display: none; width: 230 px;">
    <form id="f_createPeson">
        <input type="hidden" value="0" name="perId" />
        <table>
            <tr>
                <td><label for="ci">Ci o nit:</label></td>
                <td><input type="text" class="ui-corner-all minputtext" name="ci" id="p_ci" readonly /></td>
            </tr>
            <tr>
                <td><label for="names">Nombres:</label></td>
                <td><input type="text" class="ui-corner-all minputtext" name="names" /></td>
            </tr>
            <tr>
                <td><label for="firstname">Apellido Paterno:</label></td>
                <td><input type="text" class="ui-corner-all minputtext" name="firstname" /></td>
            </tr>
            <tr>
                <td><label for="lastname">Apellido Materno:</label></td>
                <td><input type="text" class="ui-corner-all minputtext" name="lastname" /></td>
            </tr>
            <tr>
                <td><label for="address">Direccion:</label></td>
                <td><input type="text" class="ui-corner-all minputtext" name="address" /></td>
            </tr>
            <tr>
                <td><label for="phoneAddress">Telefono:</label></td>
                <td><input type="text" class="ui-corner-all minputtext" name="phoneAddress" value="0" /></td>
            </tr>
            <tr>
                <td><label for="phoneMobil">Movil:</label></td>
                <td><input type="text" class="ui-corner-all minputtext" name="phoneMobil" value="0" /></td>
            </tr>
            <tr>
                <td><label for="email">Email:</label></td>
                <td><input type="text" class="ui-corner-all minputtext" name="email" /></td>
            </tr>
        </table>
    </form>
</div>
<script>
$(document).ready(function () {
    $(".toltipArcsys").each(function(){
            $(this).attr("title", $(this).val());
        });
        $(".toltipArcsys").tooltip({
          track: true
        });
        
    $( "#namePersonId" ).autocomplete({
      source: contextServices+"client/autocomplete",
      minLength: 2,
      select: function( event, ui ) {
          $("#ciId").val(ui.item.key);
        console.log( ui.item ?
          "Selected: " + ui.item.value + " aka " + ui.item.key :
          "Nothing selected, input was " + this.value );
      }
    });
    
});
</script>