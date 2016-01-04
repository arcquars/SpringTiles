<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <script src="<c:url value='/resources/DataTables/media/js/jquery.dataTables.js'/>"></script>
    <script src="<c:url value='/resources/DataTables/media/js/fnReloadAjax.js'/>"></script>
</head>
<html>
    <body>
        <div id="contpp">
            <p class="pTitle">Precio mas alto de compra: <i class="iData">${result[0]}</i></p>
            <p class="pTitle">Precio promedio de compra: <i class="iData">${result[1]}</i></p>
            <p class="pTitle">Precio mas bajo de compra: <i class="iData">${result[2]}</i></p>
        </div>
    </body>
</html>
