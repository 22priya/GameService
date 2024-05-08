package com.GameService.game;

import com.GameService.exception.PlayerNotExistException;
import com.GameService.observer.GameObserver;
import com.GameService.player.Player;
import com.GameService.score.Score;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Entity
public class Shooting implements Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Transient
    private List<GameObserver> gameObserverList;

    @ManyToMany
    private List<Player> players;

    public Shooting() {
    }

    public Boolean getGameFinished() {
        return isGameFinished;
    }

    public void setGameFinished(Boolean gameFinished) {
        isGameFinished = gameFinished;
    }

    Boolean isGameFinished;

    public Shooting(String name) {
        this.name = name;
        this.players=new ArrayList<>();
        this.gameObserverList=new ArrayList<>();
        isGameFinished=false;
    }

    @Override
    public void addObserver(GameObserver gameObserver) {
        this.gameObserverList.add(gameObserver);
    }

    @Override
    public void removeObserver(GameObserver gameObserver) {
        this.gameObserverList.remove(gameObserver);
    }

    @Override
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    @Override
    public void gameFinishedProcessing(Map<Integer,Integer> scoreMapping) throws PlayerNotExistException {
        List<Score> scores=new ArrayList<>();
        if(scoreMapping ==null)
            throw new NullPointerException("input score mapping is null");

        List<Integer> playerIds=players.stream().map(it->it.getId()).collect(Collectors.toList());

        if(!scoreMapping.keySet().containsAll(playerIds))
            throw new IllegalArgumentException("input scores does not contain scores of all players");

        if(!playerIds.containsAll(scoreMapping.keySet()))
            throw new PlayerNotExistException("input scores contain some players not of this game");

        for(Map.Entry entry:scoreMapping.entrySet())
        {
            Integer playerId= (Integer) entry.getKey();
            Integer score= (Integer) entry.getValue();
            Optional<Player> p=players.stream().filter(it->it.getId().equals(playerId)).findFirst();
            if(p.isPresent())
            {
                Score score1=new Score(p.get(),score);
                scores.add(score1);
            }
        }
        isGameFinished=true;
        publishScores(scores);
        saveScores();
    }

    @Override
    public void saveScores() {

    }

    private void publishScores(List<Score> scores) {
        for(GameObserver observer:gameObserverList)
        {
            observer.updateGameResults(scores);
        }
    }

    @Override
    public String toString() {
        return "Shooting{" +
                "name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GameObserver> getGameObserverList() {
        return gameObserverList;
    }

    public void setGameObserverList(List<GameObserver> gameObserverList) {
        this.gameObserverList = gameObserverList;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
