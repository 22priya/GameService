package com.GameService.comparator.score;

import com.GameService.player.Player;
import com.GameService.score.Score;

import java.util.Comparator;

public class ByScoreComparator implements Comparator<Score> {
    @Override
    public int compare(Score o1, Score o2) {
        return o1.getScore()-o2.getScore();
    }
}
