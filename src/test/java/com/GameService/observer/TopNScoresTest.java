package com.GameService.observer;

import com.GameService.comparator.score.ByScoreComparator;
import com.GameService.player.Player;
import com.GameService.score.Score;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TopNScoresTest {

    static TopNScores top2Scorers;
    @BeforeAll
    static public void initialise()
    {
        top2Scorers=new TopNScores(2);
    }

    @BeforeEach
    public void reset()
    {
        top2Scorers.scores.clear();
    }

    @Test
    public void updateGameResultsWhenScoresLessThanLimitTest(){

        List<Score> scores=new ArrayList<>();
        scores.add(new Score(new Player(1,"A",12,"noemai"),34));
        top2Scorers.updateGameResults(scores);
        assertEquals(1,top2Scorers.getResults().size());

    }

    @Test
    public void updateGameResultsWhenScoreEqualsLimitTest(){
        List<Score> scores=new ArrayList<>();
        scores.add(new Score(new Player(1,"A",12,"noemai"),34));
        scores.add(new Score(new Player(2,"B",16,"noemai"),114));
        top2Scorers.updateGameResults(scores);
        assertEquals(2,top2Scorers.getResults().size());
    }

    @Test
    public void updateGameResultsWhenScoresMoreThanLimitTest(){
        List<Score> scores=new ArrayList<>();
        scores.add(new Score(new Player(1,"A",12,"noemai"),34));
        scores.add(new Score(new Player(2,"B",16,"noemai"),114));
        scores.add(new Score(new Player(3,"C",16,"noemai"),234));
        this.top2Scorers.updateGameResults(scores);
        assertEquals(2,top2Scorers.getResults().size());
        assertFalse(top2Scorers.getResults().stream().map(it->it.getScore()).collect(Collectors.toList()).contains(34));
        assertTrue(top2Scorers.getResults().stream().map(it->it.getScore()).collect(Collectors.toList()).contains(114));
        assertTrue(top2Scorers.getResults().stream().map(it->it.getScore()).collect(Collectors.toList()).contains(234));

    }
}
