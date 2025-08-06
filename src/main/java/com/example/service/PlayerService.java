package com.example.service;

import com.example.dao.PlayerDao;
import com.example.model.Player;

public class PlayerService {
    private final PlayerDao playerDao = new PlayerDao();

    public Player findOrCreatePlayer(String name){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Player name cannot be empty");
        }
        return playerDao.findOrCreate(name.trim());
    }

    public boolean isValidPlayerName(String name){
        return name != null && !name.trim().isEmpty() && name.trim().length() <100;
    }
}
