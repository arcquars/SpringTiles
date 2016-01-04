<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
    $(document).ready(function() {
        $("#function").selectmenu();
        $("#branch").selectmenu();
        $('#function').change(function(){
            $.ajax({
                url: "/SpringTiles/personalpp/branchJson.html",
                data: {valfunction: $(this).val(), branchId: $("#h_branchId").val()},
                datatype: "json",
                success: function(datos){
                    var resultBranchList = $.parseJSON(datos);
                    var htmloptions = "";
                    $.each(resultBranchList,function(i, item){
                        htmloptions = htmloptions + "<option value='"+item.key+"'>"+item.value+"</option>";
                    });
                    $('#branch').html('');
                    $('#branch').html(htmloptions);
                }
            });
        });
        
        $( "#cssmenu ul li" ).each(function( index ) {
            $(this).removeClass("active");
        });

        $( "#cssmenu ul" ).each(function( index ) {
            if(index === 6){
                $(this).parent().addClass("active");
            }
        });
    });
    
    function linkpersonSearch(){
        window.location.href = '../../personalpp.html';
        return false;
    }
    
</script>
<br/>
<div>
    <h1 style="padding-left: 0px;">Editar Persona</h1>
    <div align="center">

        <form:form action="personEdit.html" commandName="personForm">
            <table class="cleartable">
                <tbody style="width: 375px;">
                    <tr>
                        <td width="125px"></td>
                        <td width="125px"></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <form:errors path="names" cssClass="errorMenssage" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="names">Nombres:</form:label>
                            </td>
                            <td>
                            <form:input path="names" size="16" cssClass="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="125px" style="width: 125px;">
                            <form:label path="firstname" cssStyle="width:120px;">Apellido Paterno:</form:label>
                            </td>
                            <td>
                            <form:input path="firstname" size="16" cssClass="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <form:errors path="firstname" cssClass="errorMenssage" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="lastname">Apellido Materno:</form:label>
                            </td>
                            <td>
                            <form:input path="lastname" size="16" cssClass="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <form:errors path="lastname" cssClass="errorMenssage" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="ci" size="16" >Ci:</form:label>
                            </td>
                            <td>
                            <form:input path="ci" size="16" cssClass="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <form:errors path="ci" cssClass="errorMenssage" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="address" size="16" >Direccion:</form:label>
                            </td>
                            <td>
                            <form:input path="address" size="16" cssClass="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <form:errors path="lastname" cssClass="errorMenssage" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="phone_address" size="16" >Telefono Domicilio:</form:label>
                            </td>
                            <td>
                            <form:input path="phone_address" size="16" cssClass="ui-corner-all minputtext"/>
                        </td>
                        <td>
                            <form:errors path="phone_address" cssClass="errorMenssage" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="phone_mobil" size="16" >Telefono mobil:</form:label>
                            </td>
                            <td>
                            <form:input path="phone_mobil" size="16" cssClass="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <form:errors path="phone_mobil" cssClass="errorMenssage" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="email" size="16" >Correo electronico:</form:label>
                            </td>
                            <td>
                            <form:input path="email" size="16" cssClass="ui-corner-all minputtext"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <form:errors path="email" cssClass="errorMenssage" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="position" size="16" >Funcion:</form:label>
                            </td>
                            <td>
                            <form:select path="position" id="function" cssClass="ui-corner-all mselect" cssStyle="width: 100%;" >
                                <form:option label="Administrador" value="Administrador"></form:option>
                                <form:option label="Vendedor" value="Vendedor"></form:option>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="branch" size="16" >Sucursal:</form:label>
                            </td>
                            <td>
                            <form:select path="branch" id="branch" cssClass="ui-corner-all mselect" cssStyle="width: 100%;" >
                                <c:forEach var="item" items="${branchFree}" >
                                    <form:option value="${item.key}">${item.value}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <form:errors path="branch" cssClass="errorMenssage" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div align="right">
                                <input type="submit" value="Grabar" class="mbutton" style="width: 70px;" />
                                <input type="button" onclick="linkpersonSearch();" value="Cancelar" class="mbutton" style="width: 70px;"/>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
            <form:hidden path="perId" />
            <form:hidden path="del"/>
            <form:hidden path="branch" id="h_branchId"/>
        </form:form>
        <br>
    </div>       
</div>