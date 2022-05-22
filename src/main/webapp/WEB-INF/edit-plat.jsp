<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css" />

    <title>ModifPlat</title>
</head>
<body>
<c:if test="${ !empty erreur }"><p style="color: red"><c:out value="${ erreur }"/></p></c:if>

<h2 class="tcenter"><c:out value="${ plat.nom }"></c:out></h2>


<div style="width: 50%;
    background-color: ghostwhite;
    border-radius: 10px;
    padding: 10px;
    display: flex;
    justify-content: center;
    margin: auto">

    <form method="post" action="EditPlat?id=${ plat.idPlat }">
    <h3 class="tcenter">Modidification</h3>
        <div class="raw">
            <label for="nom">Nom : </label>
            <input type="text" name="nom" id="nom" value="${ plat.nom }" placeholder="nom du plât" required />
        </div>
        <div class="raw">
            <label for="prix">Prix : </label>
            <input type="text" name="prix" id="prix" value="${plat.prix}" placeholder="prix" required />
        </div>
        <div class="raw">
            <label for="type">Type : </label>
            <input type="text" name="type" id="type" value="${ plat.type }" placeholder="Type" required />
        </div>
        <div class="raw" style="display: flex; justify-content: center;">
            <input type="submit" name="add"/>
        </div>
    </form>
</div>


</body>
</html>
