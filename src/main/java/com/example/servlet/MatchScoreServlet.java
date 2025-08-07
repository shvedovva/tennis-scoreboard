package com.example.servlet;

import com.example.model.Match;
import com.example.model.MatchScore;
import com.example.model.Player;
import com.example.service.FinishedMatchesPersistenceService;
import com.example.service.MatchScoreCalculationService;
import com.example.service.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends BaseServlet{
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private final MatchScoreCalculationService calculationService = new MatchScoreCalculationService();
    private final FinishedMatchesPersistenceService persistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidStr = getStringParameter(req, "uuid");
        if (uuidStr == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Match UUID is required");
            return;
        }

        try {
            UUID matchId = UUID.fromString(uuidStr);
            MatchScore matchScore = ongoingMatchesService.getMatch(matchId);

            if (matchScore == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Match not found");
                return;
            }
            req.setAttribute("matchScore", matchScore);
            forwardToJsp(req, resp, "/WEB-INF/views/match-score.jsp");
        } catch (IllegalArgumentException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid UUID format");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("entry");
        String uuidStr = getStringParameter(req, "uuid");
        String winnerIdStr = getStringParameter(req, "winnerId");

        if (uuidStr == null || winnerIdStr == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Match UUID and winner ID are required");
            return;
        }

        try {
            UUID matchId = UUID.fromString(uuidStr);
            int winnerId = Integer.parseInt(winnerIdStr);

            MatchScore matchScore = ongoingMatchesService.getMatch(matchId);
            if (matchScore == null){
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Match not found");
                return;
            }

            Player winner;
            if (winnerId == matchScore.getPlayer1().getId()){
                winner = matchScore.getPlayer1();
            } else if (winnerId == matchScore.getPlayer2().getId()) {
                winner = matchScore.getPlayer2();
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid winner ID");
                return;
            }

            // Обновляем счет
            calculationService.addPoint(matchScore, winner);

            if (matchScore.isFinished()){
                persistenceService.saveMatch(matchScore);
                ongoingMatchesService.removeMatch(matchId);
            }
            req.setAttribute("matchScore", matchScore);
            forwardToJsp(req, resp, "/WEB-INF/views/match-score.jsp");
        } catch (IllegalArgumentException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
        } catch (Exception e) {
            req.setAttribute("error", "Error updating match score: " + e.getMessage());
            req.setAttribute("matchScore", ongoingMatchesService.getMatch(UUID.fromString(uuidStr)));
            forwardToJsp(req, resp, "/WEB-INF/views/match-score.jsp");
        }
    }
}
