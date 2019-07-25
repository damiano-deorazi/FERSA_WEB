<%@ page import="fersa_web.model.ApartmentLessorVisit" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FERSA - Modifica Visita</title>
</head>
<body>
<%
    int i = Integer.parseInt(request.getParameter("modifyIndex"));
    ArrayList<ApartmentLessorVisit> apartments = (ArrayList<ApartmentLessorVisit>)session.getAttribute("visitsList");
    ApartmentLessorVisit apartment = apartments.get(i);
    int idApartment = apartment.getId();
    LocalDate dateVisit = apartment.getDateVisit();
    LocalTime timeVisit = apartment.getTimeVisit();
    String renterUsername = apartment.getUsernameRenterVisit();
%>
<h1 style="text-align: center">
    <b>Modifica Visita</b>
</h1>
<b>ID Appartamento: </b> <%= idApartment%>
<br>
<b>Data/orario visita: </b> <%= dateVisit%> <%= timeVisit%>
<br>
<b>Username locatario: </b> <%= renterUsername%>
<br>
<br>

<form action="modifyVisitServlet" onsubmit="return confirm('Vuoi davvero modificare la visita?')" method="get">
    <input type="hidden" id="modIndex" name="modifyIndex" value="<%= i%>">
    Data: <input type="date" name="modDate" value="null">
    <input type="hidden" id="timeHidd" name="modTime" value="08:00">
    <br>
    Ora:
    <select id="timeList" onchange="setHiddenValue()">
        <option value="08:00">08:00</option>
        <option value="08:30">08:30</option>
        <option value="09:00">09:00</option>
        <option value="09:30">09:30</option>
        <option value="10:00">10:00</option>
        <option value="10:30">10:30</option>
        <option value="11:00">11:00</option>
        <option value="11:30">11:30</option>
        <option value="12:00">12:00</option>
        <option value="12:30">12:30</option>
        <option value="13:00">13:00</option>
        <option value="13:30">13:30</option>
        <option value="14:00">14:00</option>
        <option value="14:30">14:30</option>
        <option value="15:00">15:00</option>
        <option value="15:30">15:30</option>
        <option value="16:00">16:00</option>
        <option value="16:30">16:30</option>
        <option value="17:00">17:00</option>
        <option value="17:30">17:30</option>
        <option value="18:00">18:00</option>
        <option value="18:30">18:30</option>
        <option value="19:00">19:00</option>
    </select>
    <br>
    <button type="submit">Modifica</button>
</form>

<script>
    function setHiddenValue() {
        document.getElementById("timeHidd").value = document.getElementById("timeList").value;
    }
</script>
</body>
</html>
