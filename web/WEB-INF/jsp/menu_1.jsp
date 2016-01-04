<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<link rel="stylesheet" href="<c:url value='/resources/styles/coolMenu.css' />" type="text/css" />-->
<link rel="stylesheet" href="<c:url value='/resources/styles/menu_css3/style.css' />" type="text/css" />
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/menu.js'/>"></script>
<!--
<script src="<c:url value='/resources/js/menu/modernizr-1.6.min.js'/>"></script>
<script src="<c:url value='/resources/js/menu/scripts.js'/>"></script>
<div style="float: right;">
    <ul id="coolMenu">
<c:forEach var="menumap" items="${mList}" >
    <li><a href="${menumap.value}">${menumap.key}</a></li>
</c:forEach>
</ul>
</div>
-->
<div id='cssmenu'>
    <ul>
        <li class='has-sub'><a href='#' onclick="return false;"><span>Inventarios</span></a>
            <ul>
                <li><a href='#' onclick="redirectInventory();
                        return false;"><span>Inventario de Almacen</span></a></li>
                <li class='last'><a href='#' onclick="redirectInventoryBranch();
                        return false;"><span>Inventarios por tienda</span></a></li>
            </ul>
        </li>
        <li><a href='#' onclick="redirectReserveByBranch();
                return false;"><span>Reservas</span></a>
            <ul>
                <li><a href='#' onclick="redirectReserveInventory();
                        return false;"><span>Inventario Reservas</span></a></li>
                <li class='last'><a href='#' onclick="redirectReserveByBranch();
                        return false;"><span>Reservas por tienda</span></a></li>
            </ul>
        </li>
        <li class='has-sub'><a href='#' onclick="return false;"><span>Reportes</span></a>
            <ul>
                <li><a href='#' onclick="redirectDetailSale();
                        return false;"><span>Detalle de ventas X tienda</span></a></li>
                <li class='has-sub'><a href='#' onclick="redirectAccountBranch();
                        return false;"><span>Cuentas por Venta</span></a></li>
                <li class='last'><a href='#' onclick="redirectResportCredit();
                        return false;"><span>Reporte de Compras al Credito</span></a></li>
            </ul>
        </li>
        <li class='has-sub'><a href='#' onclick="redirectProduct();
                return false;"><span>Productos</span></a>
            <ul>
                <li class='last'><a href='#' onclick="redirectCategory();
                        return false;"><span>Categorias</span></a></li>
                <li class='last'><a href='#' onclick="createProvider();
                        return false;"><span>Crear Proveedor</span></a></li>
            </ul>
        </li>
        <li class='has-sub'><a href='#' onclick="redirectPayments();
                return false;"><span>Pagos</span></a>
            <ul></ul>
        </li>
        <li class='has-sub'><a href='#' onclick="redirectClients();
                return false;"><span>Clientes</span></a>
            <ul></ul>
        </li>
        <li class='has-sub'><a href='#' onclick="return false;"><span>Configure</span></a>
            <ul>
                <li class='last'><a href='#' onclick="redirectPersonal();
                        return false;"><span>Personal</span></a></li>
                <li class='last'><a href='#' onclick="redirectBranch();
                        return false;"><span>Tiendas</span></a></li>
            </ul>
        </li>
    </ul>
</div>
<div id="div_create_provider" title="Crear Proveedor" width="420px" style="display: none;">
    <form id="f_create_provider">
        <fieldset>
            <legend></legend>
            <table>
                <tbody>
                    <tr>
                        <td>
                            <label id="lname" for="i_cprovider_businessname">Nombre de la Empresa:</label>
                        </td>
                        <td>
                            <input id="i_cprovider_businessname" name="i_cprovider_businessname" type="text" value="" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="lnames" for="inames">Nombres del Representante:</label>
                        </td>
                        <td>
                            <input id="inames" name="inames" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                        </td>
                        <td><em></em></td>
                    </tr>
                    <tr>
                        <td>
                            <label for="fp_i_firstname">Apellido Paterno:</label>
                        </td>
                        <td>
                            <input id="fp_i_firstname" name="fp_i_firstname" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <label for="fp_i_lastname">Apellido Materno:</label>
                        </td>
                        <td>
                            <input id="fp_i_lastname" name="fp_i_lastname" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <label id="fp_l_ci" for="fp_i_ci">Ci:</label>
                        </td>
                        <td>
                            <input id="fp_i_ci" name="fp_i_ci" type="text" value="" maxlength="100" class="ui-corner-all minputtext" onchange="getPersonByci(this);"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <label for="fp_i_address">Direccion:</label>
                        </td>
                        <td>
                            <input id="fp_i_address" name="fp_i_address" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <label id="fp_l_phone_home" for="fp_i_phone_home">Tel. Domicilio:</label>
                        </td>
                        <td>
                            <input id="fp_i_phone_home" name="fp_i_phone_home" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <label for="fp_i_phone_mobil">Tel. Mobil:</label>
                        </td>
                        <td>
                            <input id="fp_i_phone_mobil" name="fp_i_phone_mobil" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <label id="fp_l_email" for="fp_i_email">Correo Electronico:</label>
                        </td>
                        <td>
                            <input id="fp_i_email" name="fp_i_email" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                        </td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <input id="fp_i_userId" name="fp_i_userId" type="hidden" value="-1"/>
            <input id="fp_i_del" name="fp_i_del" type="hidden" value="false"/>

        </fieldset>
    </form>
</div>