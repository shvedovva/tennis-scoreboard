<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h1>Tennis Scoreboard</h1>
    </header>

    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/new-match">New Match</a></li>
            <li><a href="${pageContext.request.contextPath}/matches">Finished Matches</a></li>
        </ul>
    </nav>

    <main>
        <h2>Welcome to Tennis Scoreboard</h2>
        <p>Track your tennis matches with our simple scoreboard.</p>
        <div class="links">
            <a href="${pageContext.request.contextPath}/new-match" class="btn">Start New Match</a>
            <a href="${pageContext.request.contextPath}/matches" class="btn">View Finished Matches</a>
        </div>
    </main>
</body>
</html>