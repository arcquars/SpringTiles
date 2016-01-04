<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value='/resources/styles/blueprint/screen.css' />" type="text/css" media="screen, projection" />
        <link rel="stylesheet" href="<c:url value='/resources/styles/blueprint/print.css' />" type="text/css" media="print" />
        <link rel="stylesheet" href="<c:url value='/resources/styles/default.css' />" type="text/css" />
        <!--[if lt IE 8]>
                <link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css" />" type="text/css" media="screen, projection" />
        <![endif]-->
        
        <link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui/jquery-ui.css' />" type="text/css" />
        <link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui/jquery-ui.structure.css' />" type="text/css" />
        <link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui/jquery-ui.theme.css' />" type="text/css" />
        
        <script src="<c:url value='/resources/js/jquery-ui/external/jquery/jquery.js'/>"></script>
        <script src="<c:url value='/resources/js/jquery-ui/jquery-ui.js'/>"></script>
        
    </head>
    <body>
        <div class="container">
            <hr class="space"/>
            <hr class="space"/>
            <hr class="space"/>
            <div class="span-8 prepend-8 last" style="text-align: center;">
                <h1 style="background: #cc8632; color: #000;">Arc - Sys</h1>
            </div>
            <div class="span-8 prepend-8 last">
                <tiles:insertAttribute name="body"/>
            </div>
            <div class="span-8 prepend-8 last">
                Pie...
            </div>
        </div>
    </body>
</html>
