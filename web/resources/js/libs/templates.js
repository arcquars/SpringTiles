/*
 * Template para hacer pagos de ventas al credito
 */
var htmlSaleRevoice = "<p><b>Razon Social: </b><span><%- razonSocial %></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>Nit: </b><span><%- nit %></span></p>";
htmlSaleRevoice += "<p><b>Fecha de venta: </b><span ><%- dateCreation %></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>Vendedor: </b><span><%- nameVendor %></span></p>";
htmlSaleRevoice += "<table id='ds_table'><thead><th>Fecha de pago</th><th>A cuenta</th></thead>";
htmlSaleRevoice += "<tbody><% var totalPayment=0;  _.each(listPayment, function(data){ totalPayment += data.amount; %>";
htmlSaleRevoice += "<tr><td><%- data.createDate %></td><td style='text-align: right;'><%- data.amount %></td></tr>";
htmlSaleRevoice += "<% }); %>";
htmlSaleRevoice += "</tbody></table>";
htmlSaleRevoice += "<div style='text-align: right;' >";
htmlSaleRevoice += "<p><b>Total pagado: <%- totalPayment %></b></p>";
htmlSaleRevoice += "<p><b>Total Venta: <%- total %></b></p>";
htmlSaleRevoice += "<form id='s_savePaymentSave'>";
htmlSaleRevoice += "<input type='number' name='quantity' min='<%- 1 %>' max='<%- total-totalPayment %>' data-creditid='<%- creditId %>' data-max='<%- total-totalPayment %>' data-min='<%- 1 %>' value='<%- total-totalPayment %>'>";
htmlSaleRevoice += "<p class='error' id='p_error_payment'></p>";
htmlSaleRevoice += "</form>";

htmlSaleRevoice += "</div>";
htmlSaleRevoice += "";
var tempSaleRevoice = _.template(htmlSaleRevoice);

/*
 * Template para Reportes de pagos de ventas al credito
 */
var htmlSaleRevoiceReport = "<p><b>Razon Social: </b><span><%- razonSocial %></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>Nit: </b><span><%- nit %></span></p>";
htmlSaleRevoiceReport += "<p><b>Fecha de venta: </b><span ><%- dateCreation %></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>Vendedor: </b><span><%- nameVendor %></span></p>";
htmlSaleRevoiceReport += "<table id='ds_table'><thead><th>Fecha de pago</th><th>A cuenta</th></thead>";
htmlSaleRevoiceReport += "<tbody><% var totalPayment=0;  _.each(listPayment, function(data){ totalPayment += data.amount; %>";
htmlSaleRevoiceReport += "<tr><td><%- data.createDate %></td><td style='text-align: right;'><%- data.amount %></td></tr>";
htmlSaleRevoiceReport += "<% }); %>";
htmlSaleRevoiceReport += "</tbody></table>";
htmlSaleRevoiceReport += "<div style='text-align: right;' >";
htmlSaleRevoiceReport += "<p><b>Total pagado: <%- totalPayment %></b></p>";
htmlSaleRevoiceReport += "<p><b>Total Venta: <%- total %></b></p>";
htmlSaleRevoiceReport += "</div>";
htmlSaleRevoiceReport += "";
var tempSaleRevoiceReport = _.template(htmlSaleRevoiceReport);

/*
 * Template para ver (radonly) pagos de ventas al credito
 */
var htmlSaleRevoiceReadOnly = "<p><b>Razon Social: </b><span><%- razonSocial %></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>Nit: </b><span><%- nit %></span></p>";
htmlSaleRevoiceReadOnly += "<p><b>Fecha de venta: </b><span ><%- dateCreation %></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>Vendedor: </b><span><%- nameVendor %></span></p>";
htmlSaleRevoiceReadOnly += "<table id='ds_table'><thead><th>Fecha de pago</th><th>A cuenta</th></thead>";
htmlSaleRevoiceReadOnly += "<tbody><% var totalPayment=0;  _.each(listPayment, function(data){ totalPayment += data.amount; %>";
htmlSaleRevoiceReadOnly += "<tr><td><%- data.createDate %></td><td style='text-align: right;'><%- data.amount %></td></tr>";
htmlSaleRevoiceReadOnly += "<% }); %>";
htmlSaleRevoiceReadOnly += "</tbody></table>";
htmlSaleRevoiceReadOnly += "<div style='text-align: right;' >";
htmlSaleRevoiceReadOnly += "<p><b>Total pagado: <%- totalPayment %></b></p>";
htmlSaleRevoiceReadOnly += "<p><b>Total Venta: <%- total %></b></p>";
htmlSaleRevoiceReadOnly += "<p><b>SALDO: <%- total-totalPayment %></b></p>";
htmlSaleRevoiceReadOnly += "</div>";
htmlSaleRevoiceReadOnly += "";
var tempSaleRevoiceReadOnly = _.template(htmlSaleRevoiceReadOnly);
