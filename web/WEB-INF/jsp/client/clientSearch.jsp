
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/DataTables/media/js/fnReloadAjax.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/client/client.js'/>"></script>
<!--<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>-->
<br/>
<h1 style="padding-left: 0px;">Buscar Cliente</h1>
<div  align="center" style="width: 100%;">
    <div style="width: 70%;">
        <form id="f_search">
            <table>
                <tbody>
                    <tr>
                        <td width="25%"></td>
                        <td width="25%"></td>
                        <td width="25%"></td>
                        <td width="25%"></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;"><p>Buscar por: </p></td>
                        <td>
                            <select style="width: 100%;" class="ui-corner-all mselect" id="s_searchBy" >
                                <c:forEach var="item" items="${mapcriteria}" >
                                    <option value="${item.key}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input id="i_textSearch" style="width:98%;" class="ui-corner-all minputtext"/>
                            <div cssClass="errorMenssage" />
                        </td>
                        <td>
                            <input type="submit" class="mbutton" value="Buscar" style="width: 100%;" onclick="clientRefresh();"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>
<div align="center">
    <br/>
    <div style="width: 70%;">
        <div style="" align="right">
        <p style="text-align: right; width: 25px; display: inline;"><button title="Nuevo Producto" class="mbutton"  onclick="createClient();
            return false;"><span style="text-align: right;" class="ui-icon ui-icon-circle-plus"></span></button></p>
        </div>
        <div style="height: 10px;"></div>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="listClient">
            <thead>
            <th>Razon Social</th>
            <th>NIT</th>
            <th>Direccion</th>
            <th>Tel</th>
            <th>Representante</th>
            </thead>
            <tbody>
            </tbody>
        </table>
        <input id="hdid" type="hidden" value="" />
        <br/>
        <div align="right" style="margin-right: 5px;">
            <!--<button id="bproductEdit" class="mbutton" onclick="editProduct();">Editar</button>-->
            <button id="bClientEdit" class="mbutton" onclick="openDialogClientEdit();
                return false;">Editar</button>
        </div>
    </div>
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
                        <input id="client_nit" name="nit" type="text" class="ui-corner-all minputtext" />
                    </td>
                </tr>
                <tr>
                    <td><label for="razonSocial">Razon Social:</label></td>
                    <td><input name="razonSocial" type="text" value="" class="ui-corner-all minputtext"/></td>
                </tr>
                <tr>
                    <td><label for="nameInvoice">Factura a Nombre:</label></td>
                    <td><input name="nameInvoice" type="text" value="" class="ui-corner-all minputtext"/></td>
                </tr>
                <tr>
                    <td><label for="attendant">Encargado de Compras</label></td>
                    <td><input name="attendant" type="text" value="" class="ui-corner-all minputtext"/></td>
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
                    <td><label for="zone">Zona:</label></td>
                    <td><input name="zone" type="text" class="ui-corner-all minputtext"/></td>
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
<div id="dialogClientE" title="Editar Cliente" style="display: none;">
    <form id="f_clientE">
        <fieldset>
            <legend></legend>
            <input type="hidden" name="clientId" id="clientIdEdit" />
            <table>
                <tr>
                    <td><label for="nit">Nit:</label></td>
                    <td>
                        <input id="client_nitE" name="nit" type="text" class="ui-corner-all minputtext"/>
                    </td>
                </tr>
                <tr>
                    <td><label for="razonSocial">Razon Social:</label></td>
                    <td><input name="razonSocial" autofocus type="text" value="" class="ui-corner-all minputtext"/></td>
                </tr>
                <tr>
                    <td><label for="nameInvoice">Factura a Nombre:</label></td>
                    <td><input name="nameInvoice" type="text" value="" class="ui-corner-all minputtext"/></td>
                </tr>
                <tr>
                    <td><label for="attendant">Encargado de Compras</label></td>
                    <td><input name="attendant" type="text" value="" class="ui-corner-all minputtext"/></td>
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
                    <td><label for="zone">Zona:</label></td>
                    <td><input name="zone" type="text" class="ui-corner-all minputtext"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div id="map_canvasE" style="width:100%; height: 200px;"></div>
                        <input type="hidden" id="client_latE" name="latitud" value="-17.39782091929972" />
                        <input type="hidden" id="client_lonE" name="longitud" value="-66.1871396640625"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>
<div id="createPersonDialog" title="Crear Representante" style="display: none; width: 230 px;">
    <form id="f_createPeson">
        <input type="hidden" value="0" name="perId" />
        <table>
            <tr>
                <td><label for="ci">Ci o nit:</label></td>
                <td><input type="text" class="ui-corner-all minputtext" name="ci" id="p_ci" onchange="getPersonObject(this); return false;" /></td>
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
<br>
<script>
    var oTable12 = null;
    $(document).ready(function () {
        /* Init the table */
        var urlReloadTable = "clientaa/getListClientByCriteria.html?criteria=" + $('#s_searchBy').val() + "&textSearch=" + $('#i_textSearch').val();
        oTable12 = $('#listClient').DataTable({
            "bFilter": false,
            "bLengthChange": false,
            "aoColumnDefs": [
                {
                    "asSorting": ["asc"],
                    "aTargets": [0]
                }
            ],
            "bProcessing": true,
            "sAjaxSource": urlReloadTable,
            "aoColumns": [
                {"mData": "razonSocial"},
                {"mData": "nit"},
                {"mData": "address"},
                {"mData": "phone"},
                {"mData": "attendant"}
            ],
            "fnDrawCallback": function (oSettings) {
                /* Add a click handler to the rows - this could be used as a callback */
                //selectProduct();
            }
        });

        $('#listClient tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            }
            else {
                oTable12.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
        
        $('#bClientEdit').click( function () {
            var nit = oTable12.row('.selected').data().nit;
            $("#dialogClientE").dialog("open");
            getFirstName1($("#client_nitE").val(nit));
        } );
        
        $("#f_search").submit(function( event ) {
            clientRefresh();
            event.preventDefault();
        });
    });

    function clientRefresh() {
        //oTable12.api().ajax.reload();
        oTable12.ajax.url("clientaa/getListClientByCriteria.html?criteria=" + $('#s_searchBy').val() + "&textSearch=" + $('#i_textSearch').val()).load();
    }

    function openDialogClientEdit(){
        //alert(oTable12.row());
//        oTable12.row('.selected').remove().draw( false );
    }

</script>
