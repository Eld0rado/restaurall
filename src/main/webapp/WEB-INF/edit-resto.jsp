<%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 21-05-22
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css"/>

    <title>ModifResto</title>
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

<h2 style="text-align: center;"><c:out value="${ resto.nom }"></c:out></h2>
<c:if test="${ !empty erreur }"><p style="color: red;" class="notif"><c:out value="${ erreur }"/></p></c:if>
<c:if test="${ !empty success }"><p style="color: green;" class="notif"><c:out value="${ success }"/></p></c:if>

<div style="width: 50%;
    background-color: ghostwhite;
    border-radius: 10px;
    padding: 10px;
    display: flex;
    justify-content: center;
    margin: auto">
    <form method="post" action="EditRestaurant?id=${ resto.id }">
        <h3 style="text-align: center;">Modification</h3>
        <div class="raw">
            <label for="nom">Nom : </label>
            <input type="text" name="nom" id="nom" value="${ resto.nom }" required/>
        </div>
        <div class="raw">
            <label for="adresse">Adresse : </label>

            <input type="text" name="adresse" id="adresse" value="${ resto.adresse }" placeholder="Adresse"/>
        </div>
        <div class="raw">
            <label for="cp">CP : </label>
            <input type="text" name="cp" id="cp" value="${ resto.cp }"/>
        </div>
        <div class="raw">
            <label for="ville">Ville : </label>
            <input type="text" name="ville" id="ville" value="${ resto.ville }" required/>
        </div>
        <div class="raw">
            <label for="type">Type : </label>
            <input type="text" name="type" id="type" value="${ resto.type }" required/>
        </div>
        <div class="raw" style="display: flex; justify-content: center;">
            <input type="submit" name="add"/>
        </div>
    </form>
</div>
</body>
</html>
