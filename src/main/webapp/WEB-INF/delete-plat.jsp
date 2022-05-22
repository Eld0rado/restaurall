
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css" />
    <title>SuppPlat</title>
</head>
<body>
<h2 class="tcenter">Suppression de plat</h2>
<c:if test="${ !empty erreur }"><p style="color: red"><c:out value="${ erreur }"/></p></c:if>

<div style="width: 50%;
    background-color: ghostwhite;
    border-radius: 10px;
    padding: 10px;
    display: flex;
    justify-content: center;
    margin: auto">
    <form method="post" action="DeletePlat?id=${ plat.idPlat }">
        <h3 style="text-align: center;">Suppression de</h3>
        <p class="tcenter"><c:out value="${ plat.nom }"></c:out></p>
    <button type="submit">Supprimer</button>
    </form>
</div>
</body>
</html>
