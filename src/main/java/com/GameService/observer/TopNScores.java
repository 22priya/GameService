package com.GameService.observer;

import com.GameService.comparator.score.ByScoreComparator;
import com.GameService.player.Player;
import com.GameService.score.Score;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

public class TopNScores implements GameObserver{
    PriorityQueue<Score> scores;

    Integer limit;

    public TopNScores(Integer limit) {
        this.limit = limit;
        scores=new PriorityQueue<>(limit,new ByScoreComparator());
    }

    @Override
    public PriorityQueue<Score> getResults() {
        return scores;
    }

    @Override
    public void updateGameResults(List<Score> scores) {
        this.scores.clear();
        this.scores.addAll(scores);
        while(this.scores.size()>limit)
        {
            this.scores.poll();
        }
    }
}
