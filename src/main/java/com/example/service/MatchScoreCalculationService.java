package com.example.service;

import com.example.model.MatchScore;
import com.example.model.Player;

public class MatchScoreCalculationService {

    public void addPoint(MatchScore matchScore, Player player) {
        if (matchScore.isFinished()) {
            return;
        }
        if (!matchScore.isTiebreak() && isTiebreakNeeded(matchScore)){
            matchScore.setTiebreak(true);
        }
        if (matchScore.isTiebreak()) {
            addTiebreakPoint(matchScore, player);
        } else {
            addRegularPoint(matchScore, player);
        }
    }

    private void addRegularPoint(MatchScore matchScore, Player player) {
        boolean isPlayer1 = player.equals(matchScore.getPlayer1());
//          перенос логики проверки в метод addPoint
//        if (isTiebreakNeeded(matchScore)){
//            matchScore.setTiebreak(true);
//            addTiebreakPoint(matchScore, player);
//            return;
//        }

        if (isPlayer1) {
            matchScore.setPlayer1Points(matchScore.getPlayer1Points() + 1);
        } else {
            matchScore.setPlayer2Points(matchScore.getPlayer2Points() + 1);
        }

        // Проверяем, выиграл ли кто-то гейм
        if (isGameWon(matchScore)) {
            if (isPlayer1) {
                matchScore.setPlayer1Games(matchScore.getPlayer1Games() + 1);
            } else {
                matchScore.setPlayer2Games(matchScore.getPlayer2Games() + 1);
            }

            // Сбрасываем очки
            matchScore.setPlayer1Points(0);
            matchScore.setPlayer2Points(0);

            // Проверяем, выиграл ли кто-то сет
            if (isSetWon(matchScore)) {
                if (isPlayer1) {
                    matchScore.setPlayer1Sets(matchScore.getPlayer1Sets() + 1);
                } else {
                    matchScore.setPlayer2Sets(matchScore.getPlayer2Sets() + 1);
                }
                // Сбрасываем геймы
                matchScore.setPlayer1Games(0);
                matchScore.setPlayer2Games(0);

                if (isMatchWon(matchScore)) {
                    matchScore.setFinished(true);
                    matchScore.setWinner(player);
                }
            } else if (isTiebreakNeeded(matchScore)) {
                matchScore.setTiebreak(true);
            }
        }
    }

    private void addTiebreakPoint(MatchScore matchScore, Player player) {
        boolean isPlayer1 = player.equals(matchScore.getPlayer1());

        // Добавляем очко в тай-брейке
        if (isPlayer1) {
            matchScore.setPlayer1TiebreakPoints(matchScore.getPlayer1TiebreakPoints() + 1);
        } else {
            matchScore.setPlayer2TiebreakPoints(matchScore.getPlayer2TiebreakPoints() + 1);
        }
        // Проверяем, выиграл ли кто-то тай-брейк
        if (isTiebreakWon(matchScore)) {
            if (isPlayer1) {
//                matchScore.setPlayer1Games(matchScore.getPlayer1Games() + 1);
                matchScore.setPlayer1Sets(matchScore.getPlayer1Sets() + 1);
            } else {
//                matchScore.setPlayer2Games(matchScore.getPlayer2Games() + 1);
                matchScore.setPlayer2Sets(matchScore.getPlayer2Sets() + 1);
            }

            // Сбрасываем тай-брейк
            matchScore.setTiebreak(false);
            matchScore.setPlayer1TiebreakPoints(0);
            matchScore.setPlayer2TiebreakPoints(0);
            matchScore.setPlayer1Games(0);
            matchScore.setPlayer2Games(0);

            // Проверяем, выиграл ли кто-то матч
            if (isMatchWon(matchScore)) {
                matchScore.setFinished(true);
                matchScore.setWinner(player);
            }
        }
    }

    private boolean isTiebreakWon(MatchScore matchScore) {
        int p1Points = matchScore.getPlayer1TiebreakPoints();
        int p2Points = matchScore.getPlayer2TiebreakPoints();

        if (p1Points >= 7 || p2Points >= 7) {
            return Math.abs(p1Points - p2Points) >= 2;
        }
        return false;
    }


    private boolean isGameWon(MatchScore matchScore) {
        int p1Points = matchScore.getPlayer1Points();
        int p2Points = matchScore.getPlayer2Points();

        // Игрок должен выиграть как минимум 4 очка
        if (p1Points >= 4 || p2Points >= 4) {
            // Разница должна быть минимум 2
            return Math.abs(p1Points - p2Points) >= 2;
        }
        return false;
    }

    private boolean isSetWon(MatchScore matchScore) {
        int p1Games = matchScore.getPlayer1Games();
        int p2Games = matchScore.getPlayer2Games();
        // Игрок должен выиграть как минимум 6 геймов
        if (p1Games >= 6 || p2Games >= 6) {
            // Разница должна быть минимум 2
            return Math.abs(p1Games - p2Games) >= 2;
        }

        return false;
    }

    private boolean isTiebreakNeeded(MatchScore matchScore) {
        return matchScore.getPlayer1Games() == 6 && matchScore.getPlayer2Games() == 6;
    }

    private boolean isMatchWon(MatchScore matchScore) {
        // Матч играется до 2 выигранных сетов
        return matchScore.getPlayer1Sets() == 2 || matchScore.getPlayer2Sets() == 2;
    }

}
