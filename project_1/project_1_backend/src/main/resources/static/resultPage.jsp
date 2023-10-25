<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Result Page</title>
</head>
<body>
<pre>
    <%-- Iterate over the tickets and display them as plain text --%>
    <c:forEach items="${tickets}" var="ticket">
        Ticket ID: ${ticket.id}
        Description: ${ticket.description}
        <%-- Add more fields as needed --%>
    </c:forEach>
    <div>${tickets}</div>
</pre>
</body>
</html>
