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
        
        <!--<link rel="stylesheet" href="<c:url value='/resources/styles/jquery-ui/humanity/jquery.ui.all.css' />" type="text/css" />-->
        
        <link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui/jquery-ui.css' />" type="text/css" />
        <link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui/jquery-ui.structure.css' />" type="text/css" />
        <link rel="stylesheet" href="<c:url value='/resources/js/jquery-ui/jquery-ui.theme.css' />" type="text/css" />
        
        <script src="<c:url value='/resources/js/jquery-ui/external/jquery/jquery.js'/>"></script>
        <script src="<c:url value='/resources/js/jquery-ui/jquery-ui.js'/>"></script>
        
        <script src="<c:url value='/resources/js/libs/underscore.js'/>"></script>
        <script src="<c:url value='/resources/js/libs/backbone-min.js'/>"></script>
        
        <link rel="stylesheet" href="<c:url value='/resources/DataTables/media/css/jquery.dataTables.min.css' />" type="text/css" />
        <link rel="stylesheet" href="<c:url value='/resources/DataTables/media/css/jquery.dataTables_themeroller.css' />" type="text/css" />
        <script src="<c:url value='/resources/DataTables/media/js/jquery.dataTables.js'/>"></script>
        
    </head> 
    <body>
        <div class="container">
            <hr class="space"/>
            <div class="span-24 last" style="background: #CC8632;">
                <div class="span-18" style="margin-right: 0px;">
                    <img src="<c:url value='/resources/images/Logo-punto-ventas.png'/>" />
                </div>
                <div class="span-6 last" style="margin-top: 10px;">
                    <tiles:insertAttribute name="header"/>
                </div>
            </div>
                <div class="span-24 last" style="background: #3b6166;">
                <tiles:insertAttribute name="menu"/>
            </div>
            <div class="span-24 last">
                <tiles:insertAttribute name="body"/>
            </div>
            <div class="span-24 last" style="background: #fdf9eb;">
                <div align="center">
                    arc sys 
                </div>
            </div>
        </div>
    </body>
</html>
