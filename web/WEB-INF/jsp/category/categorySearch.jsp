<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="<c:url value='/resources/js/controllers/category/categoryController.js'/>"></script>
<br/>
<h1 style="padding-left: 0px;">Buscar Categoria</h1>
<input id="hdid" type="hidden" value="" />
<input id="hdname" type="hidden" value="" />
<div  style="width: 100%;" align="center">
    <div style="width: 380px;">
        <form:form action="categorypp.html" commandName="categorySearchForm">
            <table>
                <tbody style="width: 380px !important;">
                    <tr>
                        <td style="width: 125px !important;"></td>
                        <td width="125px"></td>
                        <td width="125px"></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;"><p style="width: 120px;">Buscar por: </p></td>
                        <td>
                            <form:input path="textSearch" size="16px" cssClass="ui-corner-all minputtext"/>
                            <form:errors path="textSearch" cssClass="errorMenssage" />
                        </td>
                        <td>
                            <button class="mbutton" style="width: 120px;" type="submit">Buscar</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </div>
    <div style="width: 380px" >
        <br/>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="listCat">
            <thead>
            <th>Nombre de Categoria</th>
            </thead>
            <tbody>
                <c:forEach var="valueCat" items="${listCategory}" >
                    <tr>
                        <td><p>${valueCat.categoryName}<strong style="visibility: hidden;">${valueCat.categoryId}</strong></p></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br/>
        <br/>
        <div align="right" style="margin-right: 5px;">
            <button id="bcategoryEdit" class="mbutton" style="width: 60px;" >Editar</button>
            <button id="bcategoryDelete" class="mbutton" style="width: 60px;" >Eliminar</button>
        </div>
    </div>
    <div id="dialogDelete" title="Borrar Category" >
        <p>¿Seguro que desea borrar la categoria</p>
        <strong id="stnames"></strong> ?
        <p id="errorDelete"></p>
    </div>
    <div id="dialogUpdate" title="Editar Categoria" style="display: none;">
        <form id="f_edit_cat" >
            <fieldset>
                <legend></legend>
                <table>
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
                            <input id="icatId" name="icatId" type="hidden" value=""/>
                            <input id="iTextSearch" name="iTextSearch" type="hidden" value=""/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
        <p id="pErrorMensaje" class="errorMenssage"></p>
    </div>
</div>