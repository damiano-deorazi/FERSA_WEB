<%@ page import="fersa_web.model.MaintenanceRequest" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FERSA - Dettagli richiesta</title>
</head>
<body>
<%
    int index = Integer.parseInt(request.getParameter("detIndex"));
    MaintenanceRequest maintenanceRequest = ((ArrayList<MaintenanceRequest>)session.getAttribute("maintenanceList")).get(index);
%>
<h1 style="text-align: center">
    Dettagli richiesta di manutenzione
</h1>

<p>ID Richiesta: <%= maintenanceRequest.getId()%></p>
<br>
<p>Username richiedente: <%= maintenanceRequest.getUsernameRenter()%></p>
<br>
<p>Manutenzione richiesta in data <%= maintenanceRequest.getDateRequest()%> alle ore <%= maintenanceRequest.getTimeRequest()%></p>
<br>
<p>Descrizione del problema: <%= maintenanceRequest.getDescription()%></p>
<br>

<button onclick="location.href='maintenanceList.jsp';">INDIETRO</button>

</body>
</html>
