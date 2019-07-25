<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login Page</title>
</head>
<body>
<div class="title">
    <h1>Benvenuto in FERSA</h1>
</div>
<form action="loginServlet" method="post">
    <div class="imgcontainer">
        <img src="default-avatar.jpg" alt="Avatar" class="avatar">
    </div>

    <div class="container">
        <label for="uname"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" id="uname" name="username" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" id="psw" name="password" required>

        <button type="submit">Login</button>
    </div>
</form>
<%
    if(request.getAttribute("errorMessage") != null){
        %>
        <p style="color:red;"> <%= request.getAttribute("errorMessage")%> </p>
        <%
    } else if (request.getAttribute("invalidMessage") != null){
        %>
        <p style="color:red;"> <%= request.getAttribute("invalidMessage")%> </p>
        <%
    }
%>
</body>
</html>
