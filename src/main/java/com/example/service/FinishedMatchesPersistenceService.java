package com.example.service;

import com.example.dao.MatchDao;
import com.example.model.Match;
import com.example.model.MatchScore;

import java.util.List;

public class FinishedMatchesPersistenceService {
    private final MatchDao matchDao = new MatchDao();

    public Match saveMatch(MatchScore matchScore){
        if (!matchScore.isFinished() || matchScore.getWinner() == null){
            throw new IllegalArgumentException("Match must be finished to save");
        }
        Match match = new Match(matchScore.getPlayer1()
                                ,matchScore.getPlayer2()
                                ,matchScore.getWinner());
        return matchDao.save(match);
    }
     public List<Match> getMatches(int page, int pageSize){
        return matchDao.findAll(page, pageSize);
     }

     public List<Match> getMatchesByPlayerName(String playerName, int page, int pageSize){
        return matchDao.findByPlayerName(playerName, page,pageSize);
     }

     public long getTotalMatchesCount(){
        return matchDao.countAll();
     }
     public  long getTotalMatchesCountByPlayerName(String playerName){
        return matchDao.countByPlayerName(playerName);
     }

}
