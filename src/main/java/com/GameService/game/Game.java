package com.GameService.game;

import com.GameService.exception.PlayerNotExistException;
import com.GameService.observer.GameObserver;
import com.GameService.player.Player;

import java.util.Map;

public interface Game {
    void addObserver(GameObserver gameObserver);

    void removeObserver(GameObserver gameObserver);


    void addPlayer(Player player);

    void removePlayer(Player player);

    void gameFinishedProcessing(Map<Integer,Integer> scoreMapping) throws PlayerNotExistException;

    void saveScores();
}
