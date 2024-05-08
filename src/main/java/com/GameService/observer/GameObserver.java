package com.GameService.observer;

import com.GameService.score.Score;


import java.util.List;
import java.util.PriorityQueue;

public interface GameObserver {

    PriorityQueue<Score> getResults();

    void updateGameResults(List<Score> scores);

}
