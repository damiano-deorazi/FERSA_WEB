<%@ page import="fersa_web.model.MaintenanceRequest" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FERSA - Richieste di manutenzione</title>
</head>
<body>
<h1 style="text-align: center">
    Gestione richieste di manutenzione
</h1>
<%
    ArrayList<MaintenanceRequest> maintenanceRequests = (ArrayList<MaintenanceRequest>)session.getAttribute("maintenanceList");
    int i = 0;
%>
<table id="visitsTable">
    <tr><td>ID richiesta</td><td>ID appartamento</td><td>Username richiedente</td><td>Data richiesta</td></tr>
    <%
        for (MaintenanceRequest maintenance: maintenanceRequests){
    %>
    <tr id="<%= i%>" onclick="setButtonsValue(this.id)" ondblclick="showDetails(this.id)"><td><%=maintenance.getId()%></td>
        <td><%=maintenance.getIdApartment()%></td><td><%=maintenance.getUsernameRenter()%></td>
        <td><%=maintenance.getDateRequest()%></td>
    </tr>
    <%
            i++;
        }
    %>
</table>

<form action="manageMaintenanceReqServlet" id="reqForm" method="get">
    <input type="hidden" id="reqIndex" name="reqIndex" value="-1">
    <input type="hidden" id="operation" name="op">
</form>

<button onclick="acceptRequest()">ACCETTA</button>
<button onclick="rejectRequest()">RIFIUTA</button>

<form action="detailsMaintenanceRequest.jsp" id="detForm" method="get">
    <input type="hidden" id="detIndex" name="detIndex">
</form>

<form action="logoutServlet" id="modForm" method="get">
    <button type="submit">LOGOUT</button>
</form>

<button onclick="location.href='lessorOpt.jsp';">INDIETRO</button>

<script>
    function setButtonsValue(id) {
        document.getElementById("reqIndex").value = id;
    }

    function acceptRequest() {
        if (confirm("Vuoi davvero accettare la richiesta di manutenzione selezionata?")){
            document.getElementById("operation").value = 1;
            document.getElementById("reqForm").submit();
        }
    }

    function rejectRequest() {
        if (confirm("Vuoi davvero rifiutare la richiesta di manutenzione selezionata?")){
            document.getElementById("operation").value = 0;
            document.getElementById("reqForm").submit();
        }
    }

    function showDetails(id) {
        document.getElementById("detIndex").value = id;
        document.getElementById("detForm").submit();
    }
</script>
</body>
</html>
