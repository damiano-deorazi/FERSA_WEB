<%--
  Created by IntelliJ IDEA.
  User: damiano
  Date: 07/06/19
  Time: 16.07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FERSA</title>
</head>
<body>
<h1 style="text-align: center">
    <b>Benvenuto <%= session.getAttribute("username")%></b>
    <br>
    Cosa vuoi fare?
</h1>
<button onclick="location.href='logout_service';" id="logoutBtn">Logout</button>

<div class="option-buttons">
    <button onclick="location.href = 'visitsListServlet';" id="manageVisitsBtn">Gestisci Visite</button>
    <br>
    <button onclick="location.href = 'maintenanceListServlet';" id="maintBtn">Gestisci Richieste Manutenzione</button>
</div>
</body>
</html>
