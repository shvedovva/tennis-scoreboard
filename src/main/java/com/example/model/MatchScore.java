package com.example.model;

import java.io.Serializable;
import java.util.UUID;

public class MatchScore implements Serializable  {
    private final UUID uuid;
    private final Player player1;
    private final Player player2;

    public MatchScore(UUID uuid, Player player1, Player player2) {
        this.uuid = uuid;
        this.player1 = player1;
        this.player2 = player2;
    }
}
