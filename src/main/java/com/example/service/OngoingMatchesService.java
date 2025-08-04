package com.example.service;

import com.example.model.MatchScore;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static OngoingMatchesService instance;
    private final Map<UUID, MatchScore> ongoingMatches = new ConcurrentHashMap<>();

    public OngoingMatchesService() {
    }

    public static synchronized OngoingMatchesService getInstance(){
        if (instance == null){
            instance = new OngoingMatchesService();
        }
        return instance;
    }

    public void addMatch(MatchScore matchScore){
        ongoingMatches.put(matchScore.getMatchId(), matchScore);
    }

    public MatchScore getMatch(UUID matchId){
        return ongoingMatches.get(matchId);
    }

    public void removeMatch(UUID matchId){
        ongoingMatches.remove(matchId);
    }
    public boolean containsMatch(UUID matchId){
        return ongoingMatches.containsKey(matchId);
    }
}
