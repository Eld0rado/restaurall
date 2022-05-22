<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css" />

    <title>Start</title>
</head>
<body>

<br/>
<a href="Home" style="text-decoration: none;">
    <div class="starter">
        <p style="margin: auto; color: white; font-size: xx-large; font-weight: bold;">
            AnnuaiResto
        </p>
    </div>
</a>
<c:set var="show" value="resto" scope="request"/>

<c:out value="${ show }"/>

</body>
</html>