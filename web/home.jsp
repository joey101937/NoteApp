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

                <h2>${sessionScope.activeNote.name}<a href="HomeServlet?action=deleteNote&noteId=${sessionScope.activeNote.ID}"><button class="deleteButton">Delete</button></a></h2>

                <div>
                    <form action="HomeServlet" method="post" class="descrip">
                        <div class="saveDiv">
                        <input type="hidden" name="action" value="save">
                        <input type="hidden" name="noteId" value="${sessionScope.activeNote.ID}">
                        <input type="submit" value="Save" class="saveButton">
                        </div>
                        <div>
                            <textarea name="contents" cols="150" rows="50" placeholder="Enter Note Contents Here">${sessionScope.activeNote.contents}</textarea>
                        </div>
                    </form>    
                </div>
            </div> 
        </div>
    </body>
</html>
