<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/DataTables/media/js/fnReloadAjax.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.draggable.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.position.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.resizable.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.ui.dialog.js'/>"></script>
<script src="<c:url value='/resources/js/ui/jquery.effects.core.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/inventory/menuInventory.js'/>"></script>
<script>
    $("#coolMenu li").each(function(i, item) {
        var href = $(item).children().attr('href');
        if (href.indexOf('inventorypp') != -1) {
            $(item).children().addClass("coolMenuSelect");
        } else {
            $(item).children().removeClass("coolMenuSelect");
        }
    });
</script>
<div style="height: 20px;"></div>
<div style="background: #cc8632;">
    <br/>
    <button id="bInvAlmacen" class="mbutton" style="width: 120px;" onclick="redirectInventorymain();">Inventario Almacen</button>
    <div style="height: 5px;"></div>
    <button id="bInvTienda" class="mbutton" style="width: 120px;" onclick="inventoryBranch();">Inventario x Tienda</button>
    <div style="height: 5px;"></div>
    <button id="bCreateProvider" class="mbutton" style="width: 120px;" onclick="createProvider();">Crear Proveedor</button>
    <div style="height: 5px;"></div>
    <button id="bSaleGain" class="mbutton" style="width: 120px;" onclick="saleGain();">Detalle Venta x Tienda</button>
    <div style="height: 5px;"></div>
    <button id="bReverve" class="mbutton" style="width: 120px;" onclick="saleReserve();">Reservas</button>
    <div style="height: 5px;"></div>
    <button id="bSalesAccount" class="mbutton" style="width: 120px;" onclick="salesAccount();">Cuenta de Ventas</button>
    <div style="height: 5px;"></div>
</div>
<div style="height: 20px;"></div>
<div id="divBuyProductNew" style="display: none;" title="Producto nuevo">
    <form id="f_createproduct">
        <fieldset>
            <legend></legend>
            <table width="100%">
                <tr>
                    <td>
                        <label id="lnames" for="iname">Nombre Producto:</label>
                    </td>
                    <td>
                        <input id="iname" name="iname" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_codorigen" for="fp_i_codorigen">Codigo Origen:</label>
                    </td>
                    <td>
                        <input id="fp_i_codorigen" name="fp_i_codorigen" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="fp_i_price">Precio para Venta:</label>
                    </td>
                    <td>
                        <input id="fp_i_price_id" name="fp_i_price" type="text" value="" class="ui-corner-all minputtext"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_category" for="fp_i_category">Categoria:</label>
                    </td>
                    <td>
                        <table width="100%">
                            <tr>
                                <td style="width: 140px;">
                                    <select id="fp_s_categorypp" name="fp_s_category" class="ui-corner-all mselect" style="width: 140px;">
                                        <c:forEach var="item" items="${allCategory}" >
                                            <option value="${item.key}">${item.value}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <a href="#" onclick="createCategory();
                                            return false;"><span class="ui-button-icon-primary ui-icon ui-icon-plusthick"></span></a>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_factory" for="fp_s_factory">Fabricante:</label>
                    </td>
                    <td>
                        <table width="100%">
                            <tr>
                                <td style="width: 140px;">
                                    <select id="fp_s_factory" name="fp_s_factory" class="ui-corner-all mselect" style="width: 140px;">
                                        <c:forEach var="item" items="${allFactory}" >
                                            <option value="${item.key}">${item.value}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <a onclick="createFactory();" href="#"><span class="ui-button-icon-primary ui-icon ui-icon-plusthick"></span></a>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_description" for="fp_i_description">Descripcion:</label>
                    </td>
                    <td>
                        <input id="fp_i_description" name="fp_i_description" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>
<div id="divCreateCategory" title="Crear Categoria" style="display: none;">
    <form id="f_createcategory">
        <fieldset>
            <legend></legend>
            <table>
                <tr>
                    <td>
                        <label id="lname" for="iname_cat_create">Nombre:</label>
                    </td>
                    <td>
                        <input id="iname_cat_create" name="iname_cat_create" type="text" class="ui-corner-all minputtext"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
    <p id="pErrorMensajeCrear" class="errorMenssage"></p>
</div>
<div id="div_create_factory" title="Crear Fabricante" width="370px" style="display: none;">
    <form id="f_createfactoryss">
        <fieldset>
            <legend></legend>
            <table>
                <tr>
                    <td>
                        <label id="lname" for="i_factory_name">Nombre:</label>
                    </td>
                    <td>
                        <input id="i_factory_name_id" name="i_factory_name" type="text" required class="ui-corner-all minputtext"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
    <p id="pErrorMensajeCreateFactory" class="errorMenssage"></p>
</div>