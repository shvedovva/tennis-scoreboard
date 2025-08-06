package com.example.servlet;

import com.example.model.MatchScore;
import com.example.model.Player;
import com.example.service.OngoingMatchesService;
import com.example.service.PlayerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends BaseServlet{
    private final PlayerService playerService = new PlayerService();
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToJsp(req, resp, "/WEB-INF/views/new-match.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String player1Name = getStringParameter(req, "player1Name");
        String player2Name = getStringParameter(req, "player2Name");

        if (!playerService.isValidPlayerName(player1Name)){
            req.setAttribute("error", "Player 1 name is required and must be valid");
            forwardToJsp(req, resp, "/WEB-INF/views/new-match.jsp");
            return;
        }

        if (!playerService.isValidPlayerName(player2Name)){
            req.setAttribute("error", "Player 2 name is required and must be valid");
            forwardToJsp(req, resp, "/WEB-INF/views/new-match.jsp");
            return;
        }

        if (player1Name.equalsIgnoreCase(player2Name)){
            req.setAttribute("error", "Players must have different names");
            forwardToJsp(req, resp, "/WEB-INF/views/new-match.jsp");
            return;
        }

        try {
            Player player1 = playerService.findOrCreatePlayer(player1Name);
            Player player2 = playerService.findOrCreatePlayer(player2Name);

            MatchScore matchScore = new MatchScore(player1, player2);
            ongoingMatchesService.addMatch(matchScore);

            redirectTo(resp,  "/match-score?uuid=" + matchScore.getMatchId());
        } catch (Exception e){
            req.setAttribute("error", "Error creating match: " + e.getMessage());
            forwardToJsp(req, resp, "/WEB-INF/views/new-match.jsp");
        }




    }
}
