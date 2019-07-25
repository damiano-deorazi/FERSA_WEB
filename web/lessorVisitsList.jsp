<%@ page import="fersa_web.model.ApartmentLessorVisit" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FERSA - Visite prenotate</title>
</head>
<body>
<h1 style="text-align: center">
    Gestione visite prenotate
</h1>
<%
    ArrayList<ApartmentLessorVisit> apartmentLessorVisits = (ArrayList<ApartmentLessorVisit>)session.getAttribute("visitsList");
    int i = 0;
%>
<table id="visitsTable">
    <tr><td>ID</td><td>Username locatario</td><td>Indirizzo</td><td>Città</td><td>Paese</td><td>Data/ora visita</td></tr>
    <%
        for (ApartmentLessorVisit apartment: apartmentLessorVisits){
    %>
    <tr id="<%= i%>" onclick="setButtonsValue(this.id)"><td><%=apartment.getId()%></td><td><%=apartment.getUsernameRenterVisit()%></td>
        <td><%=apartment.getAddress()%></td><td><%=apartment.getCity()%></td><td><%=apartment.getCountry()%></td>
        <td><%=apartment.getDateVisit()%> <%=apartment.getTimeVisit()%></td>
    </tr>
    <%
            i++;
        }
    %>
</table>

<form action="deleteVisitServlet" onsubmit="return confirm('Vuoi davvero cancellare la visita selezionata?')" id="delForm" method="get">
    <input type="hidden" id="delIndex" name="deleteIndex" value="-1">
    <button type="submit">CANCELLA</button>
</form>

<form action="lessorModifyVisit.jsp" id="modForm" method="get">
    <input type="hidden" id="modIndex" name="modifyIndex" value="-1">
    <button type="submit">MODIFICA</button>
</form>

<form action="logoutServlet" id="modForm" method="get">
    <button type="submit">LOGOUT</button>
</form>

<script>
    function setButtonsValue(id) {
        document.getElementById("delIndex").value = id;
        document.getElementById("modIndex").value = id;
    }
</script>
</body>
</html>
