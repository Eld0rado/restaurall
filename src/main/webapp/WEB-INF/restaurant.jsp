<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <link rel="stylesheet" type="text/css" href="style.css" />
    <meta charset="utf-8">
    <title>Restaurant</title>

</head>
<body>

<h2 style="text-align: center;">Restaurant : <c:out value="${ resto.nom }"></c:out></h2>
<c:if test="${ !empty erreur }"><p style="color: red"><c:out value="${ erreur }"/></p></c:if>

<p class="tcenter"><c:out value="${ resto.adresse }"></c:out></p>
<p class="tcenter"><c:out value="${ resto.cp }"></c:out> <c:out value="${ resto.ville }"></c:out></p>
<p class="tcenter"> <c:out value="${ resto.type }"></c:out></p>
<ul class="all" style="list-style-type:none;">
    <c:forEach var="plat" items="${ allprix }">
        <div class="card">
        <li>
            <div cl ass="container"><c:out value="${ plat.nom }"></c:out></div>
            <div class="container"><c:out value="${ plat.type }"></c:out></div>
            <div class="container"><c:out value="${ plat.prix } €"></c:out></div>
            <div class="container">
                <a href="EditPlat?id=${plat.idPlat}"><button>Modifier</button></a>
                <a href="DeletePlat?id=${ plat.idPlat }"><button type="submit"> Supprimer</button></a>
            </div>
        </li>

    </div>
    </c:forEach>
</ul>
<div style="width: 50%;
    background-color: ghostwhite;
    border-radius: 10px;
    padding: 10px;
    display: flex;
    justify-content: center;
    margin: auto">

<form method="post" action="Restaurant?id=${ resto.id }">
    <h3 class="tcenter">Ajout d'un plat</h3>

    <c:if test="${ !empty erreur }"><p style="color: red"><c:out value="${ erreur }"/></p></c:if>

    <div class="raw">
        <label for="nom">Nom : </label>
        <input type="text" name="nom" id="nom" placeholder="Nom du plat" required />
    </div>
    <div class="raw">
        <label for="prix">Prix : </label>
        <input type="text" name="prix" id="prix" placeholder="prix" required />
    </div>
    <div class="raw">
        <label for="type">Type : </label>
        <input type="text" name="type" id="type" placeholder="Type" required />
    </div>
    <div class="raw" style="display: flex; justify-content: center;">
        <input type="submit" name="add"/>
    </div>
</form>
</div>
</body>
</html>
