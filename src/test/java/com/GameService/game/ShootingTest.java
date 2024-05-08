package com.GameService.game;

import com.GameService.exception.PlayerNotExistException;
import com.GameService.player.Player;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShootingTest {

    Shooting shootingGame=new Shooting("round1");

    @Test
    public void setGameFinishedNullTest() {
        Exception exception=assertThrows(NullPointerException.class, ()->shootingGame.gameFinishedProcessing(null));
        assertEquals("input score mapping is null",exception.getMessage());
    }

    @Test
    public void setGameFinishedWhenScoresOfAllPlayersNotGivenTest(){
        shootingGame.addPlayer(new Player(1,"tom",12,"tom@email"));
        shootingGame.addPlayer(new Player(2,"tomy",12,"tomy@email"));
        Map<Integer,Integer> map=new HashMap<>();
        map.put(1,13);
        Exception exception=assertThrows(IllegalArgumentException.class,()->shootingGame.gameFinishedProcessing(map));
        assertEquals("input scores does not contain scores of all players",exception.getMessage());
    }

    @Test
    public void setGameFinishedWhenScoresOfExtraPlayersGivenTest(){
        shootingGame.addPlayer(new Player(1,"tom",12,"tom@email"));
        shootingGame.addPlayer(new Player(2,"tomy",12,"tomy@email"));
        Map<Integer,Integer> map=new HashMap<>();
        map.put(1,13);
        map.put(2,20);
        map.put(3,16);
        Exception exception=assertThrows(IllegalArgumentException.class,()->shootingGame.gameFinishedProcessing(map));
        assertEquals("input scores contain some players not of this game",exception.getMessage());
    }

    @Test
    public void setGameFinishedWhenProperScoresGivenTest(){
        shootingGame.addPlayer(new Player(1,"tom",12,"tom@email"));
        shootingGame.addPlayer(new Player(2,"tomy",12,"tomy@email"));
        Map<Integer,Integer> map=new HashMap<>();
        map.put(1,13);
        map.put(2,20);
        try {
            shootingGame.gameFinishedProcessing(map);
        } catch (PlayerNotExistException e) {
            throw new RuntimeException(e);
        }
        assertEquals(true,shootingGame.isGameFinished);
        //assertEquals(2,shootingGame.ge);
    }
}
