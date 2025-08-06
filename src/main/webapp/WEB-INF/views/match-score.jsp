<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Счет матча - Tennis Scoreboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Счет матча</h1>
            <nav>
                <a href="${pageContext.request.contextPath}/">Главная</a>
                <a href="${pageContext.request.contextPath}/matches">Матчи</a>
            </nav>
        </header>

        <main>
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>

            <c:if test="${not empty matchScore}">
                <div class="scoreboard">
                    <table class="score-table">
                        <thead>
                            <tr>
                                <th>Игрок</th>
                                <th>Сеты</th>
                                <th>Геймы</th>
                                <th>Очки</th>
                                <c:if test="${not matchScore.finished}">
                                    <th>Действие</th>
                                </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="player-name">${matchScore.player1.name}</td>
                                <td class="sets">${matchScore.player1Sets}</td>
                                <td class="games">${matchScore.player1Games}</td>
                                <td class="points">${matchScore.player1PointsDisplay}</td>
                                <c:if test="${not matchScore.finished}">
                                    <td>
                                        <form method="post" style="display: inline;">
                                            <input type="hidden" name="uuid" value="${matchScore.matchId}">
                                            <input type="hidden" name="winnerId" value="${matchScore.player1.id}">
                                            <button type="submit" class="btn btn-point">+1</button>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                            <tr>
                                <td class="player-name">${matchScore.player2.name}</td>
                                <td class="sets">${matchScore.player2Sets}</td>
                                <td class="games">${matchScore.player2Games}</td>
                                <td class="points">${matchScore.player2PointsDisplay}</td>
                                <c:if test="${not matchScore.finished}">
                                    <td>
                                        <form method="post" style="display: inline;">
                                            <input type="hidden" name="uuid" value="${matchScore.matchId}">
                                            <input type="hidden" name="winnerId" value="${matchScore.player2.id}">
                                            <button type="submit" class="btn btn-point">+1</button>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </tbody>
                    </table>

                    <c:if test="${matchScore.tiebreak}">
                        <div class="tiebreak-indicator">
                            <strong>ТАЙ-БРЕЙК</strong>
                        </div>
                    </c:if>

                    <c:if test="${matchScore.finished}">
                        <div class="match-result">
                            <h2>Матч завершен!</h2>
                            <p><strong>Победитель: ${matchScore.winner.name}</strong></p>
                            <div class="actions">
                                <a href="${pageContext.request.contextPath}/new-match" class="btn btn-primary">Новый матч</a>
                                <a href="${pageContext.request.contextPath}/matches" class="btn btn-secondary">Все матчи</a>
                            </div>
                        </div>
                    </c:if>
                </div>
            </c:if>
        </main>
    </div>
</body>
</html>