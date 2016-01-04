<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="<c:url value='/resources/DataTables/media/js/fnReloadAjax.js'/>"></script>
<script src="<c:url value='/resources/js/jquery-validation/jquery.validate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/person/personCreate.js'/>"></script>
<script src="<c:url value='/resources/js/controllers/product/createProduct.js'/>"></script>
<script>
    $("#coolMenu li").each(function(i, item){
        var href = $(item).children().attr('href');
        if(href.indexOf('productpp') != -1) {
            $(item).children().addClass("coolMenuSelect");
        }else{
            $(item).children().removeClass("coolMenuSelect");
        }
    });
</script>
<div style="height: 47px;"></div>
<div style="background: #cc8632;">
    <br/>
    <button id="bCreateProduct" onclick="createProduct();return false;" class="mbutton" style="width: 120px;">Crear Producto</button>
    <div style="height: 5px;"></div>
    <br/>
</div>