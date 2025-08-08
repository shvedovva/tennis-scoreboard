package com.example;

import com.example.model.MatchScore;
import com.example.model.Player;
import com.example.service.MatchScoreCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreCalculationServiceTest {
    private MatchScoreCalculationService service;
    private MatchScore matchScore;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp(){
    service = new MatchScoreCalculationService();
    player1 = new Player("John Silver");
    player1.setId(1);
    player2 = new Player("Jack Sparrow");
    player2.setId(2);
    matchScore = new MatchScore(player1, player2);
    }
    @Test
    void testPlayer1Wins15_0(){
        service.addPoint(matchScore, player1);
        assertEquals(1, matchScore.getPlayer1Points());
        assertEquals(0, matchScore.getPlayer2Points());
        assertEquals("15", matchScore.getPlayer1PointsDisplay());
        assertEquals("0", matchScore.getPlayer2PointsDisplay());
        assertFalse(matchScore.isFinished());
    }

    @Test
    void testPlayerWinsGame40_0(){
        matchScore.setPlayer1Points(3);
        matchScore.setPlayer2Points(0);

        service.addPoint(matchScore, player1);

        assertEquals(1, matchScore.getPlayer1Games());
        assertEquals(0, matchScore.getPlayer2Games());
        assertEquals(0, matchScore.getPlayer1Points());
        assertEquals(0, matchScore.getPlayer2Points());
        assertFalse(matchScore.isFinished());
    }

    @Test
    void testDeuce40_40DoesNotEndGame(){
        matchScore.setPlayer1Points(3);
        matchScore.setPlayer2Points(3);

        service.addPoint(matchScore, player1);

        assertEquals(0, matchScore.getPlayer1Games());
        assertEquals(0, matchScore.getPlayer2Games());
        assertEquals(4, matchScore.getPlayer1Points());
        assertEquals(3, matchScore.getPlayer2Points());
        assertEquals("AD", matchScore.getPlayer1PointsDisplay());
        assertEquals("40", matchScore.getPlayer2PointsDisplay());
        assertFalse(matchScore.isFinished());
    }

    @Test
    void testPlayer1WinsGameFromDeuce(){
        matchScore.setPlayer1Points(4);
        matchScore.setPlayer1Points(3);

        service.addPoint(matchScore, player1);

        assertEquals(1, matchScore.getPlayer1Games());
        assertEquals(0, matchScore.getPlayer2Games());
        assertEquals(0, matchScore.getPlayer1Points());
        assertEquals(0, matchScore.getPlayer2Points());
        assertFalse(matchScore.isFinished());
    }

    @Test
    void testBackToDeuce(){
        matchScore.setPlayer1Points(4);
        matchScore.setPlayer2Points(3);

        service.addPoint(matchScore, player2);

        assertEquals(0, matchScore.getPlayer1Games());
        assertEquals(0, matchScore.getPlayer2Games());
        assertEquals(4, matchScore.getPlayer1Points());
        assertEquals(4, matchScore.getPlayer2Points());
        assertEquals("40", matchScore.getPlayer1PointsDisplay());
        assertEquals("40", matchScore.getPlayer2PointsDisplay());
        assertFalse(matchScore.isFinished());
    }

    @Test
    void testTiebreakStart6_6(){
        matchScore.setPlayer1Games(6);
        matchScore.setPlayer2Games(6);

        service.addPoint(matchScore, player1);

        assertTrue(matchScore.isTiebreak());
        assertEquals(1, matchScore.getPlayer1TiebreakPoints());
        assertEquals(0, matchScore.getPlayer2TiebreakPoints());
        assertEquals("1", matchScore.getPlayer1PointsDisplay());
        assertEquals("0", matchScore.getPlayer2PointsDisplay());
    }

    @Test
    void testTiebreakWin7_0(){
        matchScore.setPlayer1Games(6);
        matchScore.setPlayer2Games(6);
        matchScore.setTiebreak(true);
        matchScore.setPlayer1TiebreakPoints(6);
        matchScore.setPlayer2TiebreakPoints(0);

        service.addPoint(matchScore, player1);

        assertFalse(matchScore.isTiebreak());
        assertEquals(1, matchScore.getPlayer1Sets());
        assertEquals(0, matchScore.getPlayer2Sets());
        assertEquals(0, matchScore.getPlayer1Games());
        assertEquals(0, matchScore.getPlayer2Games());
        assertEquals(0, matchScore.getPlayer1TiebreakPoints());
        assertEquals(0, matchScore.getPlayer2TiebreakPoints());
        assertFalse(matchScore.isFinished());

    }

    @Test
    void testTiebreakWin8_6(){
        matchScore.setPlayer1Games(6);
        matchScore.setPlayer2Games(6);
        matchScore.setTiebreak(true);
        matchScore.setPlayer1TiebreakPoints(7);
        matchScore.setPlayer2TiebreakPoints(6);
        service.addPoint(matchScore, player1);

        assertFalse(matchScore.isTiebreak());
        assertEquals(1, matchScore.getPlayer1Sets());
        assertEquals(0, matchScore.getPlayer2Sets());
        assertFalse(matchScore.isFinished());
    }

    @Test
    void testTiebreakInitialization() {
        // Устанавливаем счет 6-6 в геймах
        matchScore.setPlayer1Games(6);
        matchScore.setPlayer2Games(6);
        matchScore.setPlayer1Points(3); // 40
        matchScore.setPlayer2Points(0);

        service.addPoint(matchScore, player1);

        assertTrue(matchScore.isTiebreak());
        assertEquals(1, matchScore.getPlayer1TiebreakPoints());
        assertEquals(0, matchScore.getPlayer1Points()); // Очки обычного гейма сброшены
        assertEquals(0, matchScore.getPlayer2Points());
    }


}
