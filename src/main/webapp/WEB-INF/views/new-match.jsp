<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Новый матч - Tennis Scoreboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Новый матч</h1>
            <nav>
                <a href="${pageContext.request.contextPath}/">Главная</a>
                <a href="${pageContext.request.contextPath}/matches">Матчи</a>
            </nav>
        </header>

        <main>
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>

            <div class="form-container">
                <form method="post" action="${pageContext.request.contextPath}/new-match">
                    <div class="form-group">
                        <label for="player1Name">Имя игрока 1:</label>
                        <input type="text"
                               id="player1Name"
                               name="player1Name"
                               maxlength="100"
                               required
                               value="${param.player1Name}">
                    </div>

                    <div class="form-group">
                        <label for="player2Name">Имя игрока 2:</label>
                        <input type="text"
                               id="player2Name"
                               name="player2Name"
                               maxlength="100"
                               required
                               value="${param.player2Name}">
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Начать матч</button>
                        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Отмена</a>
                    </div>
                </form>
            </div>
        </main>
    </div>
</body>
</html>