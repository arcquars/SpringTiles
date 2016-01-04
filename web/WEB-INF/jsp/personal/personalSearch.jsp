<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<br/>
${literal}
<input id="hdid" type="hidden" value="" />
<input id="hdnames" type="hidden" value="" />
<h1 style="padding-left: 0px;">Buscar Persona</h1>
<div  align="center" style="width: 100%;">
    <div style="width: 70%;">
        <form:form action="personalpp.html" commandName="personSearchForm">
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
                            <form:select path="criteria" cssStyle="width: 100%;" cssClass="ui-corner-all mselect" id="s_searchBy" >
                                <c:forEach var="item" items="${mapcriteria}" >
                                    <form:option value="${item.key}">${item.value}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td>
                            <form:input path="textSearch" cssStyle="width:98%;" cssClass="ui-corner-all minputtext" id="i_textSearch" />
                            <form:errors path="textSearch" cssClass="errorMenssage" />
                        </td>
                        <td>
                            <input type="button" class="mbutton" value="Buscar" style="width: 100%;" onclick="personRefresh();"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </div>
    <div style="width: 70%;" align="right">
        <p style="text-align: right; width: 25px; display: inline;"><button title="Nuevo Producto" class="mbutton"  onclick="createEmployee();
            return false;"><span style="text-align: right;" class="ui-icon ui-icon-circle-plus"></span></button></p>
    </div>
    <div style="width: 70%;" align="center">
        <br/>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="listPer">
            <thead>
            <th></th>
            <th>Nombre</th>
            <th>Ape. Paterno</th>
            <th>Funcion</th>
            <th>Tienda</th>
            </thead>
            <tbody>
                <c:forEach var="person" items="${listPerson}">
                    <tr>
                        <td><input type="hidden" value="${person.perId}" name="idper"/></td>
                        <td>${person.names}</td>
                        <td>${person.firstname}</td>
                        <td>${person.position}</td>
                        <td>${person.branch}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <input id="hdid" type="hidden" value="" />
        <br/>
        <br/>
        <div align="right" style="margin-right: 5px;">
            <input type="button" id="bpersonEdit" onclick="linkpersonEdit();" value="Editar" />
            <input type="button" id="bpersonDelete" style="width: 60px;" value="Eliminar" />
        </div>
    </div>
    <div id="dialogDelete" title="Borrar Persona" style="display: none;" >
        <p>¿Seguro que desea borrar al usuario <strong id="stnames"></strong> ?</p>
        <p id="errorDelete"></p>
    </div>
    <div id="delete" class="errorMenssage"></div>
    <br/>
</div>

<div id="createEmployeeDialog" title="Crear Personal" style="display: none;">
    <div align="center">
        <form id="f_createEmployee">
            <table class="cleartable">
                <tbody>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <label for="ci" >Ci:</label>
                        </td>
                        <td>
                            <input type="text" name="ci" class="ui-corner-all minputtext" onblur="getPerson(this); return false;"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="names">Nombres:</label>
                        </td>
                        <td>
                            <input type="text" name="names" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="firstname">Apellido Paterno:</label>
                        </td>
                        <td>
                            <input type="text" name="firstname" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="lastname">Apellido Materno:</label>
                            </td>
                        <td>
                            <input type="text" name="lastname" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <label for="password">Password:</label>
                            </td>
                        <td>
                            <input name="password" type="password" id="password" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="passwordRepit">Repetir password:</label>
                            </td>
                        <td>
                            <input name="passwordRepit" type="password" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="address" >Direccion:</label>
                            </td>
                            <td>
                            <input type="text" name="address" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="phone_address" >Telefono Domicilio:</label>
                            </td>
                            <td>
                            <input type="text" name="phone_address" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="phone_mobil" >Telefono mobil:</label>
                            </td>
                            <td>
                            <input type="text" name="phone_mobil" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="email" >Correo electronico:</label>
                            </td>
                            <td>
                            <input type="text" name="email" class="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="position" >Funcion:</label>
                        </td>
                        <td>
                            <select name="position" class="ui-corner-all mselect" style="width: 100%;">
                                <c:forEach var="item" items="${allRols}" >
                                    <option value="${item.key}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="branch" >Sucursal:</label>
                        </td>
                        <td>
                            <select name="branch" class="ui-corner-all mselect" style="width: 100%;">
                                <c:forEach var="item" items="${branchFree}" >
                                    <option value="${item.key}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <br>
    </div>       
</div>
<script src="<c:url value='/resources/js/controllers/personal/personalSearch.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/personal/employee.js'/>"></script>