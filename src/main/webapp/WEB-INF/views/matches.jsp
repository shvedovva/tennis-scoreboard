<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Завершенные матчи - Tennis Scoreboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Завершенные матчи</h1>
            <nav>
                <a href="${pageContext.request.contextPath}/">Главная</a>
                <a href="${pageContext.request.contextPath}/new-match">Новый матч</a>
            </nav>
        </header>

        <main>
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>

            <!-- Форма поиска -->
            <div class="search-form">
                <form method="get" action="${pageContext.request.contextPath}/matches">
                    <div class="search-group">
                        <label for="filter_by_player_name">Поиск по имени игрока:</label>
                        <input type="text"
                               id="filter_by_player_name"
                               name="filter_by_player_name"
                               value="${playerNameFilter}"
                               placeholder="Введите имя игрока">
                        <button type="submit" class="btn btn-search">Искать</button>
                        <c:if test="${not empty playerNameFilter}">
                            <a href="${pageContext.request.contextPath}/matches" class="btn btn-clear">Очистить</a>
                        </c:if>
                    </div>
                </form>
            </div>

            <!-- Список матчей -->
            <c:if test="${empty matches}">
                <div class="no-matches">
                    <p>Матчи не найдены.</p>
                    <c:if test="${empty playerNameFilter}">
                        <a href="${pageContext.request.contextPath}/new-match" class="btn btn-primary">Создать первый матч</a>
                    </c:if>
                </div>
            </c:if>

            <c:if test="${not empty matches}">
                <div class="matches-list">
                    <table class="matches-table">
                        <thead>
                            <tr>
                                <th>Матч #</th>
                                <th>Игрок 1</th>
                                <th>Игрок 2</th>
                                <th>Победитель</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="match" items="${matches}">
                                <tr>
                                    <td>${match.id}</td>
                                    <td class="player-name">${match.player1.name}</td>
                                    <td class="player-name">${match.player2.name}</td>
                                    <td class="winner">${match.winner.name}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Пагинация -->
                <c:if test="${totalPages > 1}">
                    <div class="pagination">
                        <c:if test="${currentPage > 1}">
                            <a href="?page=${currentPage - 1}<c:if test='${not empty playerNameFilter}'>&filter_by_player_name=${playerNameFilter}</c:if>"
                               class="btn btn-pagination">← Предыдущая</a>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <c:choose>
                                <c:when test="${i == currentPage}">
                                    <span class="current-page">${i}</span>
                                </c:when>
                                <c:otherwise>
                                    <a href="?page=${i}<c:if test='${not empty playerNameFilter}'>&filter_by_player_name=${playerNameFilter}</c:if>"
                                       class="btn btn-pagination">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <a href="?page=${currentPage + 1}<c:if test='${not empty playerNameFilter}'>&filter_by_player_name=${playerNameFilter}</c:if>"
                               class="btn btn-pagination">Следующая →</a>
                        </c:if>
                    </div>
                </c:if>
            </c:if>
        </main>
    </div>
</body>
</html>