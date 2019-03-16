<%-- 
    Document   : index
    Created on : Mar 16, 2019, 1:21:59 PM
    Author     : Joseph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="base.css">
        <link rel="icon" href="images/note.png">

        <title>Notes Login</title>
    </head>
    <body class="login-background">
        <h1>Notes</h1>
        <form action="HomeServlet" method="post">
            <input type="hidden" name="action" value="signin">
            <input type="text" name="username" placeholder="Username">
            <br>
            <input class="loginButton" type="submit" value="Login">
        </form>
    </body>
</html>
