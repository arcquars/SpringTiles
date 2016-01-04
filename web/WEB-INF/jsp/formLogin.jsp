<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
    $(function() {
        $( "input:submit, a, button").button();
        $( "a", ".demo" ).click(function() { return false; });
    });
</script>
<form:form action="loginaa.html" commandName="loginForm">
    <table style="width: 310px">
        <tr>
            <td style="width: 155px"></td>
            <td style="width: 155px"></td>
        </tr>
        <tr>
            <td style="text-align: right;">Usuario:</td>
            <td>
                <form:input path="username" size="20" autofocus="autofocus"/>
                <form:errors path="username"/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right;">Password:</td>
            <td>
                <form:password path="password" size="20"/>
                <form:errors path="password"/>
                <span class="errorMenssage" >
                    <c:if test="${!empty errorUserVal}">
                        <c:out value="${errorUserVal}" />
                    </c:if>
                </span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td style="text-align: right;">
                <input type="submit" value="Iniciar"/>
            </td>
        </tr>
    </table>
    <div style="margin: 0 auto; text-align: right;">
        <c:if test="${sessionExpired != ''}">
            <strong style="color: red;">${sessionExpired}</strong>
        </c:if>
    </div>
</form:form>
