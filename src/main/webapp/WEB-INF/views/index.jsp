<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Tennis Scoreboard</h1>
        </header>

        <main>
            <div class="welcome">
                <h2>Добро пожаловать в систему теннисного табло!</h2>
                <p>Здесь вы можете создать новый матч и отслеживать счет, а также просмотреть результаты завершенных матчей.</p>
            </div>

            <div class="actions">
                <a href="${pageContext.request.contextPath}/new-match" class="btn btn-primary">
                    Новый матч
                </a>

                <a href="${pageContext.request.contextPath}/matches" class="btn btn-secondary">
                    Завершенные матчи
                </a>
            </div>
        </main>

        <footer>
            <p>&copy; 2025 Tennis Scoreboard</p>
        </footer>
    </div>
</body>
</html>