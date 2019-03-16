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

                <h2>${sessionScope.activeNote.name}<a href="HomeServlet?action=save&noteId=${sessionScope.activeNote.ID}"><button class="saveButton">Save</button></a><a href="HomeServlet?action=delete&noteId=${sessionScope.activeNote.ID}"><button class="deleteButton">Delete</button></a></h2>
                
                <div>
                <span class="descrip">
                    <textarea cols="150" rows="50" placeholder="Enter Note Contents Here">${sessionScope.activeNote.contents}</textarea>
                </span>
                </div>
            </div> 
        </div>
    </body>
</html>
