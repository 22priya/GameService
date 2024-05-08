package com.GameService.score;


import com.GameService.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAllScoresByPlayer(Integer playerId)
    {
        return scoreRepository.findByPlayerId(playerId);
    }
}
