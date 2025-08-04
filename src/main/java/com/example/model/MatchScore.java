package com.example.model;

import java.io.Serializable;
import java.util.UUID;

public class MatchScore {
    private final UUID matchId;
    private final Player player1;
    private final Player player2;

    // Счет по сетам
    private int player1Sets;
    private int player2Sets;

    // Счет по геймам в текущем сете
    private int player1Games;
    private int player2Games;

    // Очки в текущем гейме
    private int player1Points;
    private int player2Points;

    // Тай-брейк
    private boolean isTiebreak;
    private int player1TiebreakPoints;
    private int player2TiebreakPoints;

    // Состояние матча
    private boolean isFinished;
    private Player winner;

    public MatchScore(Player player1, Player player2) {
        this.matchId = UUID.randomUUID();
        this.player1 = player1;
        this.player2 = player2;
        this.player1Sets = 0;
        this.player2Sets = 0;
        this.player1Games = 0;
        this.player2Games = 0;
        this.player1Points = 0;
        this.player2Points = 0;
        this.isTiebreak = false;
        this.player1TiebreakPoints = 0;
        this.player2TiebreakPoints = 0;
        this.isFinished = false;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getPlayer1Sets() {
        return player1Sets;
    }

    public void setPlayer1Sets(int player1Sets) {
        this.player1Sets = player1Sets;
    }

    public int getPlayer2Sets() {
        return player2Sets;
    }

    public void setPlayer2Sets(int player2Sets) {
        this.player2Sets = player2Sets;
    }

    public int getPlayer1Games() {
        return player1Games;
    }

    public void setPlayer1Games(int player1Games) {
        this.player1Games = player1Games;
    }

    public int getPlayer2Games() {
        return player2Games;
    }

    public void setPlayer2Games(int player2Games) {
        this.player2Games = player2Games;
    }

    public int getPlayer1Points() {
        return player1Points;
    }

    public void setPlayer1Points(int player1Points) {
        this.player1Points = player1Points;
    }

    public int getPlayer2Points() {
        return player2Points;
    }

    public void setPlayer2Points(int player2Points) {
        this.player2Points = player2Points;
    }

    public boolean isTiebreak() {
        return isTiebreak;
    }

    public void setTiebreak(boolean tiebreak) {
        isTiebreak = tiebreak;
    }

    public int getPlayer1TiebreakPoints() {
        return player1TiebreakPoints;
    }

    public void setPlayer1TiebreakPoints(int player1TiebreakPoints) {
        this.player1TiebreakPoints = player1TiebreakPoints;
    }

    public int getPlayer2TiebreakPoints() {
        return player2TiebreakPoints;
    }

    public void setPlayer2TiebreakPoints(int player2TiebreakPoints) {
        this.player2TiebreakPoints = player2TiebreakPoints;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
    // Метод для получения строкового представления очков (0, 15, 30, 40, AD)
    public String getPlayer1PointsDisplay(){
        if (isTiebreak){
            return String.valueOf(player1TiebreakPoints);
        }
        // Обычный гейм
        if (player1Points >= 3 && player2Points >= 3){
            if (player1Points == player2Points){
                return "40";
            } else if (player1Points > player2Points) {
                return "AD";
            } else {
                return "40";
            }
        }
        return convertPointsToDisplay(player1Points);
    }

    public String getPlayer2PointsDisplay(){
        if (isTiebreak) {
            return String.valueOf(player2TiebreakPoints);
        }
        // Обычный гейм
        if (player1Points >= 3 && player2Points >= 3){
            if (player1Points == player2Points){
                return "40";
            } else if (player2Points > player1Points){
                return "AD";
            } else {
                return "40";
            }
        }

        return convertPointsToDisplay(player2Points);
    }

    private String convertPointsToDisplay(int points) {
        switch (points) {
            case 0: return "0";
            case 1: return "15";
            case 2: return "30";
            case 3: return "40";
            default: return "40";
        }
    }
}
