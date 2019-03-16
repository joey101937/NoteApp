<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="sidenav">
    <p>${sessionScope.theUser.username}'s Notes</p>
    <hr>
    <c:forEach var="note" items="${sessionScope.notes}">
        <a <c:if test="${sessionScope.activeNote==note}">
                class="active"
            </c:if>
            href="HomeServlet?action=goToNote&noteId=${note.ID}">${note.name}
            <br>
            <span class="sidenavDate">(${note.lastEditedDate})</span></a>
            </c:forEach>
</div>