<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/js/controllers/branch/branchController.js'/>"></script>
<br/>
<h1 style="padding-left: 0px;">Buscar Sucursal</h1>
<input id="hdid" type="hidden" value="" />
<input id="hdname" type="hidden" value="" />
<input id="hdaddress" type="hidden" value="" />
<input id="hdphone" type="hidden" value="" />
<div  align="center" style="width: 100%;">
    <div style="width: 70%;">
        <form:form action="branchpp.html" commandName="categorySearchForm">
            <table>
                <tbody>
                    <tr>
                        <td style="width: 40%;"></td>
                        <td style="width: 40%;"></td>
                        <td style="width: 20%;"></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;"><p>Buscar por Nombre: </p></td>
                        <td>
                            <form:input path="textSearch" cssClass="ui-corner-all minputtext" cssStyle="width:99%;"/>
                            <form:errors path="textSearch" cssClass="errorMenssage" />
                        </td>
                        <td>
                            <input type="submit" value="Buscar" style="width: 100%;"/> 
                        </td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </div>
    <div style="width: 70%; text-align: right;">
        <br/>
        <p style="text-align: right; width: 25px; display: inline;"><button title="Nueva Tienda" class="mbutton"  onclick="createBranch();
                    return false;"><span style="text-align: right;" class="ui-icon ui-icon-circle-plus"></span></button></p>
    </div>
    <div style=" width: 70%" >
        <br/>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="listBranch">
            <thead>
            <th>Id</th>
            <th>Nombre de Sucursal</th>
            <th>Direccion</th>
            <th>Telefono</th>
            </thead>
            <tbody>
                <c:forEach var="valueBranch" items="${listBranch}" >
                    <tr>
                        <td>${valueBranch.branchId}</td>
                        <td><p>${valueBranch.name}<strong style="visibility: hidden;">${valueBranch.branchId}</strong></p></td>
                        <td><p>${valueBranch.address}</p></td>
                        <td><p>${valueBranch.phone}</p></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/>
        <div style="display: none;" id="divError">
        </div>
        <div align="right" style="margin-right: 5px;">
            <button id="bBranchEdit" class="mbutton" >Editar</button>
            <button id="bBranchDelete" class="mbutton">Eliminar</button>
        </div>
        <br/>
    </div>
    <div id="dialogDeleteBranch" title="Borrar Sucursal" >
        <p>¿Seguro que desea borrar la Sucursal</p>
        <strong id="stnames"></strong> ?
        <p id="errorDelete"></p>
    </div>

        <div id="dialogUpdateBranch" title="Editar Sucursal" style="display: none;">
        <form id="f_edit_branch" >
            <fieldset>
                <legend></legend>
                <table>
                    <tr>
                        <td>
                            <input id="icatId" name="icatId" type="hidden" value=""/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="lname" for="iname">Nombre:</label>
                        </td>
                        <td>
                            <input id="iname" name="iname" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="laddress" for="iaddress">Direccion:</label>
                        </td>
                        <td>
                            <input id="iaddress" name="iaddress" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="lphone" for="iphone">Telefono:</label>
                        </td>
                        <td>
                            <input id="iphone" name="iphone" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                            <input type="hidden" value="" id="icriteria" name="icriteria" >
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
        <p id="pErrorMensaje" class="errorMenssage"></p>
    </div>
</div>
<div id="divBranchCreate" title="Crear Sucursal" style="display: none;">
    <form id="f_createBranch">
        <fieldset>
            <legend></legend>
            <table>
                <tr>
                    <td>
                        <label id="l_create_name" for="i_create_name">Nombre:</label>
                    </td>
                    <td>
                        <input id="i_create_name" name="i_create_name" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label id="l_create_address" for="i_create_address">Direccion:</label>
                    </td>
                    <td>
                        <input id="i_create_address" name="i_create_address" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label id="l_create_phone" for="i_create_phone">Telefono:</label>
                    </td>
                    <td>
                        <input id="i_create_phone" name="i_create_phone" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
    <p id="pErrorMensajeCrear" class="errorMenssage"></p>
</div>