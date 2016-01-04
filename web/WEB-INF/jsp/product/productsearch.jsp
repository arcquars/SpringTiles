<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="<c:url value='/resources/DataTables/media/js/jquery.dataTables.js'/>"></script>
<script src="<c:url value='/resources/DataTables/media/js/fnReloadAjax.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/product/productSearch.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/product/createProduct.js'/>"></script>
<br/>
<h1 style="padding-left: 0px;">Buscar Producto </h1>
<div align="center" style="width: 100%;">
    <div style="width: 70%;">
        <form:form action="productpp.html" commandName="productSearchForm">
            <table>
                <tbody>
                    <tr>
                        <td style="width: 15%;"></td>
                        <td style="width: 25%;"></td>
                        <td style="width: 35%;"></td>
                        <td style="width: 25%;"></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;"><p>Buscar por: </p></td>
                        <td>
                            <form:select path="criteria" cssStyle="width: 100%" cssClass="ui-corner-all mselect" id="s_searchBy" >
                                <c:forEach var="item" items="${mapcriteria}" >
                                    <form:option value="${item.key}">${item.value}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td>
                            <form:input id="i_textSearch" path="textSearch" cssClass="ui-corner-all minputtext" cssStyle="width: 98%"/>
                            <form:errors path="textSearch" cssClass="errorMenssage" />
                        </td>
                        <td>
                            <button style="width: 100%;" class="mbutton" id="b_search_product" onclick="reloadTableProducts();
                                    return false;">Buscar</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </div>
    <div style="width: 70%; text-align: right;">
        <br/>
        <p style="text-align: right; width: 25px; display: inline;"><button title="Nuevo Producto" class="mbutton"  onclick="createProduct();
                    return false;"><span style="text-align: right;" class="ui-icon ui-icon-circle-plus"></span></button></p>
    </div>
    <div style="visibility: ${visibleDiv}; width: 70%" align="center">
        <br/>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="listPro">
            <thead>
            <th>Nombre</th>
            <th>Codigo Origen</th>
            <th>Categoria</th>
            <th>Fabicante</th>
            <th>Descripcion</th>
            </thead>
            <tbody>
            </tbody>
        </table>

        <input id="hdid" type="hidden" value="" />
        <br/>
        <div align="right" style="margin-right: 5px;">
            <!--<button id="bproductEdit" class="mbutton" onclick="editProduct();">Editar</button>-->
            <button id="bproductEdit" class="mbutton" onclick="openDialogEdit();
                    return false;">Editar</button>
            <button id="bproductDelete" class="mbutton" >Eliminar</button>
        </div>
    </div>
    <div id="dialogDelete" title="Borrar Producto" style="display: none;" >
        <p>¿Seguro que desea borrar al Producto <strong id="stnames"></strong> ?</p>
        <p id="errorDelete"></p>
    </div>
    <div id="dialogInvalid" title="Elegir Producto" style="display: none;" >
        <p>Eliga un Producto!</p>
        <p id="errorDelete"></p>
    </div>
    <div id="delete" class="errorMenssage"></div>
</div>
<br />
<div id="divBuyProductEdit" style="width: 400px; display: none;" title="Producto a Editar">
    <form id="f_editproduct">
        <fieldset>
            <legend></legend>
            <table width="100%">
                <tr>
                    <td>
                        <label id="lnames" for="iproductName">Nombre Producto:</label>
                        <input id="h_prodID" type="hidden" />
                    </td>
                    <td>
                        <input id="iproductName" name="iproductName" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label id="fp_l_codorigen" for="iproductCodOrigen">Codigo Origen:</label>
                    </td>
                    <td>
                        <input id="iproductCodOrigen" name="iproductCodOrigen" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="fp_s_category">Categoria:</label>
                    </td>
                    <td>
                        <table width="100%">
                            <tr>
                                <td style="width: 140px;">
                                    <select id="fp_s_category" name="fp_s_category" class="ui-corner-all mselect" style="width: 140px;">
                                        <c:forEach var="itemCategory" items="${allCategory}" >
                                            <c:if test="${pDto.categoryId==itemCategory.key}">
                                                <option value="${itemCategory.key}" selected>${itemCategory.value}</option>
                                            </c:if>
                                            <c:if test="${pDto.categoryId!=itemCategory.key}">
                                                <option value="${itemCategory.key}" >${itemCategory.value}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
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
                                    <select id="fp_s_factory_id" name="fp_s_factory" class="ui-corner-all mselect" style="width: 140px;">
                                        <c:forEach var="itemFactory" items="${allFactory}" >
                                            <c:if test="${pDto.factoryId==itemFactory.key}">
                                                <option value="${itemFactory.key}" selected>${itemFactory.value}</option>
                                            </c:if>
                                            <c:if test="${pDto.factoryId!=itemFactory.key}">
                                                <option value="${itemFactory.key}" >${itemFactory.value}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="fp_i_description">Descripcion:</label>
                    </td>
                    <td>
                        <input id="fp_i_description_id" name="fp_i_description" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="fp_i_price_sale">Precio de Venta:</label>
                    </td>
                    <td>
                        <input id="fp_i_price_sale" name="fp_i_price_sale" type="text" style="float:left" class="ui-corner-all minputtext"/>
                        <a id='a_price_sale' style="float: left; margin: 0 2px;" href="#" onclick="return false;"  title="test de toltip!!!"><span style="vertical-align: middle;" class="ui-icon ui-icon-lightbulb"> </span></a>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><p id="p_coste_average" style="font-size: 10px; float: left; margin: 0px 0px !important"></p></td>
                </tr>
            </table>
        </fieldset>

    </form>
</div>
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
