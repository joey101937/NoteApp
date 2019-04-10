<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="sidenav">
    <p><c:out value="${sessionScope.theUser.username}"/>'s Notes</p>
    <hr>
    <c:forEach var="note" items="${sessionScope.notes}">
        <a <c:if test="${sessionScope.activeNote.ID==note.ID}">
                class="active"
            </c:if>
                href="HomeServlet?action=goToNote&noteId=${note.ID}"><c:out value="${note.name}"/>
            <br>
            <span class="sidenavDate">(${note.lastEditedDate})</span></a>
            </c:forEach>
</div>