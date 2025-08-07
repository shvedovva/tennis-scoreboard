package com.example.servlet;

import com.example.model.Match;
import com.example.service.FinishedMatchesPersistenceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends BaseServlet{
    private static final int PAGE_SIZE = 10;
    private final FinishedMatchesPersistenceService persistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer page = getIntParameter(req, "page");
        if (page == null || page < 1) {
            page = 1;
        }

        String playerNameFilter = getStringParameter(req, "filter_by_player_name");

        try {
            List<Match> matches;
            long totalMatches;
            if (playerNameFilter != null && !playerNameFilter.isEmpty()){
                matches = persistenceService.getMatchesByPlayerName(playerNameFilter, page, PAGE_SIZE);
                totalMatches = persistenceService.getTotalMatchesCountByPlayerName(playerNameFilter);
                req.setAttribute("playerNameFilter", playerNameFilter);
            } else {
                matches = persistenceService.getMatches(page, PAGE_SIZE);
                totalMatches = persistenceService.getTotalMatchesCount();
            }

            int totalPages = (int) Math.ceil(totalMatches / PAGE_SIZE);

            req.setAttribute("matches", matches);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("pageSize", PAGE_SIZE);

            forwardToJsp(req, resp, "/WEB-INF/views/matches.jsp");
        } catch (Exception e) {
            req.setAttribute("error", "Error loading matches: " + e.getMessage());
            forwardToJsp(req, resp, "/WEB-INF/views/matches.jsp");
        }
    }
}
