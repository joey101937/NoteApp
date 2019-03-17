<ul class="titleUl">
    <li class="titleLi">
        <form action="HomeServlet" method="post">
            <input type="hidden" name="action" value="createNote">
            <input required type="text" name ="noteName" placeholder=" New Note Name" class="newNoteField">
            <input type="submit" value="Create New Note" class="newNoteButton">
        </form>
    </li>
    <li>
        <form action="HomeServlet" method="post">
            <input type="hidden" name="action" value="logout">
            <input type="submit" value="Sign Out" class="signoutButton">
        </form>
    </li>
</ul>