<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value='/resources/styles/default.css' />" type="text/css" />
        <!--[if lt IE 8]>
                <link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css" />" type="text/css" media="screen, projection" />
        <![endif]-->

        <link rel="stylesheet" href="<c:url value='/resources/styles/jquery-ui/humanity/jquery-ui-1.10.4.custom.min.css' />" type="text/css" />

        <script src="<c:url value='/resources/js/jquery-1.11.1.min.js'/>"></script>
        <script src="<c:url value='/resources/js/jquery-ui-1.10.4.custom.min.js'/>"></script>

        <link rel="stylesheet" href="<c:url value='/resources/styles/jquery-ui/demos.css' />" type="text/css" />

    </head>
    <body>
        <tiles:insertAttribute name="body"/>
    </body>
</html>
