<!DOCTYPE html>
<html lang="en-US">
    <head>
        <title>Note Page</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="base.css">
        <link rel="icon" href="images/note.png">

    </head>
    <body>
        <div class="fontFamily">
            <%@ include file="notes-navigation.jsp" %>
            <div class="main">

                <%@ include file="user-navigation.jsp" %>

                <h2>${sessionScope.activeNote.name}</h2>
                <span class="descrip">
                    <textarea cols="150" rows="50" placeholder="Enter Note Contents Here">${sessionScope.activeNote.contents}</textarea>
                </span>
                
            </div> 
        </div>
    </body>
</html>
