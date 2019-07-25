<%@ page import="java.util.ArrayList" %>
<%@ page import="fersa_web.model.ApartmentRenterVisit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FERSA - Visite prenotate</title>
</head>
<body>
<h1 style="text-align: center">
    <b>Benvenuto <%= session.getAttribute("username")%></b>
    <br>
    Gestione visite prenotate
</h1>
<%
    ArrayList<ApartmentRenterVisit> apartmentRenterVisits = (ArrayList<ApartmentRenterVisit>)session.getAttribute("visitsList");
    int i = 0;
%>
    <table id="visitsTable">
        <tr><td>ID</td><td>Username locatore</td><td>Indirizzo</td><td>Città</td><td>Paese</td><td>Data/ora visita</td></tr>
        <%
            for (ApartmentRenterVisit apartment: apartmentRenterVisits){
        %>
                <tr id="<%= i%>" onclick="setButtonsValue(this.id)"><td><%=apartment.getId()%></td><td><%=apartment.getUsernameLessor()%></td>
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

<form action="renterModifyVisit.jsp" id="modForm" method="get">
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
