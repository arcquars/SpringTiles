<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script>
    $("#coolMenu li").each(function(i, item){
        var href = $(item).children().attr('href');
        if(href.indexOf('categorypp') != -1) {
            $(item).children().addClass("coolMenuSelect");
        }else{
            $(item).children().removeClass("coolMenuSelect");
        }
    });
</script>
<div style="height: 47px;"></div>
<div style="background: #cc8632;">
    <br/>
    <button id="bCreateCategory" class="mbutton" style="width: 120px;">Crear Categoria</button>
    <div style="height: 5px;"></div>
    <button class="mbutton" style="width: 120px;">Busqueda</button>
    <br/>
    <br/>
</div>
<div style="height: 15px;"></div>
<div id="divCategoryCreate" title="Crear Categoria" style="display: none;">
    <form id="f_createcategory">
        <fieldset>
            <legend></legend>
            <table>
                <tr>
                    <td>
                        <label id="lname" for="inameCreate">Nombre:</label>
                    </td>
                    <td>
                        <input id="inameCreate" name="inameCreate" type="text" value="" maxlength="100" class="ui-corner-all minputtext"/>
                        <input type="hidden" id="iTextSearchNewCategory" name="iTextSearchNewCategory" />
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
    <p id="pErrorMensajeCrear" class="errorMenssage"></p>
</div>