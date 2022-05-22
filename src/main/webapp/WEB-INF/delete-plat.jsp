
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css" />
    <title>SuppPlat</title>
</head>
<style>

    .ulnav {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #333;
    }

    .linav {
        float: left;
    }

    .linav a {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    .linav a:hover {
        background-color: #111;
    }
    .notif {
        text-align: center;
        font-weight: bold;
        padding: 10px;
        border-radius: 20px;
        background-color: blanchedalmond;
    }
</style>
<body>
<%@ include file="navbar.jsp" %>
<h2 class="tcenter">Suppression de plat</h2>
<c:if test="${ !empty erreur }"><p style="color: red" class="notif"><c:out value="${ erreur }"/></p></c:if>
<c:if test="${ !empty success }"><p style="color: green" class="notif"><c:out value="${ success }"/></p></c:if>

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
