<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="style.css" />
    <title>Nos RestOs</title>
</head>

<body >

<div style="text-align: center"> <h2>Nos magasins</h2></div>
<c:if test="${ !empty erreur }"><p style="color: red"><c:out value="${ erreur }"/></p></c:if>

<ul class="all" style="list-style-type:none;">
<c:forEach var="rest" items="${ restos }">
    <div class="card">
    <li>
        <div class="container"><c:out value="${ rest.nom }"/></div>
        <div class="container"><c:out value="${ rest.adresse }"/></div>
        <div class="container"><c:out value="${ rest.cp }"/></div>
        <div class="container"><c:out value="${ rest.ville }"/></div>
        <div class="container"><c:out value="${ rest.type }"/></div>
        <a href="Restaurant?id=${ rest.id }"><button>Voir</button></a>
        <a href="EditRestaurant?id=${ rest.id }"><button>Modifier</button></a>
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
<form method="post" action="Home">

  <div class="raw">
      <label for="nom">Nom : </label>
    <input type="text" name="nom" id="nom" placeholder="Nom du resto" required />
  </div>
    <div class="raw">
    <label for="adresse">Adresse : </label>

    <input type="text" name="adresse" id="adresse" placeholder="Adresse"/>
    </div>
    <div class="raw">
    <label for="cp">CP : </label>
    <input type="text" name="cp" id="cp" placeholder="Code postale"/>
    </div>
    <div class="raw">
        <label for="ville">Ville : </label>
    <input type="text" name="ville" id="ville" placeholder="Ville" required />
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
